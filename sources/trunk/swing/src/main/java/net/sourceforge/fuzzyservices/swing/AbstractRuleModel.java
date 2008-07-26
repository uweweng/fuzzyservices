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
 * AbstractRuleModel
 *
 * @author Uwe Weng
 */
public abstract class AbstractRuleModel implements RuleModel {
    /** Stores the listeners on this model. */
    protected EventListenerList listenerList = new EventListenerList();

    @Override
    public final void addRuleModelListener(RuleModelListener l) {
        listenerList.add(RuleModelListener.class, l);
    }

    @Override
    public final void removeRuleModelListener(RuleModelListener l) {
        listenerList.remove(RuleModelListener.class, l);
    }

    public RuleModelListener[] getRuleModelListeners() {
        return listenerList.getListeners(RuleModelListener.class);
    }

    public <T extends EventListener> T[] getListeners(Class<T> listenerType) {
        return listenerList.getListeners(listenerType);
    }

    /**
     * Notifies all listeners that have registered interest for
     * notification on this event type.
     *
     * @param source the model that changed, typically "this"
     * @see EventListenerList
     */
    protected void fireCertaintyChanged(Object source) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == RuleModelListener.class) {
                RuleModelEvent event = new RuleModelEvent(source,
                        RuleModelEvent.CERTAINTY_CHANGED);
                ((RuleModelListener) listeners[i + 1]).ruleChanged(event);
            }
        }
    }

    /**
     * Notifies all listeners that have registered interest for
     * notification on this event type.
     *
     * @param source the model that changed, typically "this"
     * @see EventListenerList
     */
    protected void fireAntecedentsChanged(Object source) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == RuleModelListener.class) {
                RuleModelEvent event = new RuleModelEvent(source,
                        RuleModelEvent.ANTECEDENTS_CHANGED);
                ((RuleModelListener) listeners[i + 1]).ruleChanged(event);
            }
        }
    }

    /**
     * Notifies all listeners that have registered interest for
     * notification on this event type.
     *
     * @param source the model that changed, typically "this"
     * @see EventListenerList
     */
    protected void fireConsequentsChanged(Object source) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == RuleModelListener.class) {
                RuleModelEvent event = new RuleModelEvent(source,
                        RuleModelEvent.CONSEQUENTS_CHANGED);
                ((RuleModelListener) listeners[i + 1]).ruleChanged(event);
            }
        }
    }

    /**
     * Notifies all listeners that have registered interest for
     * notification on this event type.
     *
     * @param source the model that changed, typically "this"
     * @see EventListenerList
     */
    protected void fireCertaintyOperatorChanged(Object source) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == RuleModelListener.class) {
                RuleModelEvent event = new RuleModelEvent(source,
                        RuleModelEvent.CERTAINTY_OPERATOR_CHANGED);
                ((RuleModelListener) listeners[i + 1]).ruleChanged(event);
            }
        }
    }

    /**
     * Notifies all listeners that have registered interest for
     * notification on this event type.
     *
     * @param source the model that changed, typically "this"
     * @see EventListenerList
     */
    protected void fireInferenceOperatorChanged(Object source) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == RuleModelListener.class) {
                RuleModelEvent event = new RuleModelEvent(source,
                        RuleModelEvent.INFERENCE_OPERATOR_CHANGED);
                ((RuleModelListener) listeners[i + 1]).ruleChanged(event);
            }
        }
    }

    /**
     * Notifies all listeners that have registered interest for
     * notification on this event type.
     *
     * @param source the model that changed, typically "this"
     * @see EventListenerList
     */
    protected void fireAggregationOperatorChanged(Object source) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == RuleModelListener.class) {
                RuleModelEvent event = new RuleModelEvent(source,
                        RuleModelEvent.AGGREGATION_OPERATOR_CHANGED);
                ((RuleModelListener) listeners[i + 1]).ruleChanged(event);
            }
        }
    }
}
