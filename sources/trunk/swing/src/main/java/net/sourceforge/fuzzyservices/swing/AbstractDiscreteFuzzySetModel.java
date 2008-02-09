/*******************************************************************************
 *
 *  Copyright (C) 2007  Uwe Weng
 *
 *  This file is part of Fuzzy Services, a library for processing fuzzy
 *  information.
 *
 *  Fuzzy Services are free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  Fuzzy Services are distributed in the hope that they will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Fuzzy Services; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  The license is also available at http://www.gnu.org/licenses/gpl.txt
 *
 ******************************************************************************/
package net.sourceforge.fuzzyservices.swing;

import java.util.EventListener;
import javax.swing.event.EventListenerList;

/**
 * AbstractDiscreteFuzzySetModel
 *
 * @author Uwe Weng
 */
public abstract class AbstractDiscreteFuzzySetModel implements DiscreteFuzzySetModel {

    /** Stores the listeners on this model. */
    protected EventListenerList listenerList = new EventListenerList();

    @Override
    public void addDiscreteFuzzySetModelListener(DiscreteFuzzySetModelListener l) {
        listenerList.add(DiscreteFuzzySetModelListener.class, l);
    }
    
    @Override
    public void removeDiscreteFuzzySetModelListener(DiscreteFuzzySetModelListener l) {
        listenerList.remove(DiscreteFuzzySetModelListener.class, l);
    }
    public DiscreteFuzzySetModelListener[] getDiscreteFuzzySetModelListeners() {
        return listenerList.getListeners(DiscreteFuzzySetModelListener.class);
    }
    
    public <T extends EventListener> T[] getListeners(Class<T> listenerType) {
	return listenerList.getListeners(listenerType); 
    }
    
    /**
     * Notifies all listeners that have registered interest for
     * notification on this event type.
     *
     * @param source the model that changed, typically "this"
     * @param obj affected object
     * @see EventListenerList
     */
    protected void fireObjectAdded(Object source, Object obj) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==DiscreteFuzzySetModelListener.class) {
                DiscreteFuzzySetModelEvent event = new DiscreteFuzzySetModelEvent(source, DiscreteFuzzySetModelEvent.OBJECT_ADDED, obj);
                ((DiscreteFuzzySetModelListener)listeners[i+1]).discreteFuzzySetChanged(event);
            }
        }
    }
    
    /**
     * Notifies all listeners that have registered interest for
     * notification on this event type.
     *
     * @param source the model that changed, typically "this"
     * @param obj affected object
     * @see EventListenerList
     */
    protected void fireObjectRemoved(Object source, Object obj) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==DiscreteFuzzySetModelListener.class) {
                DiscreteFuzzySetModelEvent event = new DiscreteFuzzySetModelEvent(source, DiscreteFuzzySetModelEvent.OBJECT_REMOVED, obj);
                ((DiscreteFuzzySetModelListener)listeners[i+1]).discreteFuzzySetChanged(event);
            }
        }
    }

    /**
     * Notifies all listeners that have registered interest for
     * notification on this event type.
     *
     * @param source the model that changed, typically "this"
     * @param obj affected object
     * @see EventListenerList
     */
    protected void fireDegreeOfMembershipChanged(Object source, Object obj) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==DiscreteFuzzySetModelListener.class) {
                DiscreteFuzzySetModelEvent event = new DiscreteFuzzySetModelEvent(source, DiscreteFuzzySetModelEvent.DEGREE_OF_MEMBERSHIP_CHANGED, obj);
                ((DiscreteFuzzySetModelListener)listeners[i+1]).discreteFuzzySetChanged(event);
            }
        }
    }
    
}
