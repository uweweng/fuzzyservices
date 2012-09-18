/*
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
package net.sourceforge.fuzzyservices.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;


/**
 *
 * @author Uwe Weng
 */
public class SimpleAttribute {

    /**
     * Holds value of property text.
     */
    private SimpleDay day;
    public static final String PROP_DAY = "day";

    /**
     * Get the value of day
     *
     * @return the value of day
     */
    public SimpleDay getDay() {
        return this.day;
    }

    /**
     * Set the value of day
     *
     * @param newday new value of day
     */
    public void setDay(SimpleDay newday) {
        SimpleDay oldday = day;
        this.day = newday;
        propertyChangeSupport.firePropertyChange(PROP_DAY, oldday, newday);
    }

    private String text;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    /**
     * Add VetoableChangeListener.
     *
     * @param listener
     */
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    /**
     * Remove VetoableChangeListener.
     *
     * @param listener
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    /** Creates a new instance of SimpleAttribute */
    public SimpleAttribute() {
    }

    /**
     * Getter for property text.
     * @return Value of property text.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Setter for property text.
     * @param text New value of property text.
     */
    public final void setText(String text) {
        this.text = text;
    }
}
