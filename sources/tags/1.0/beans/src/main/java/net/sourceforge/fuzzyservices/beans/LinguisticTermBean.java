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
package net.sourceforge.fuzzyservices.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

/**
 * This class represents a term of a linguistic variable
 * according to JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.LinguisticVariable
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class LinguisticTermBean implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    //
    // Bound property names
    //

    /** Bound property name for <code>name</code>. */
    public static final String NAME_PROPERTY = "name";

    /** Bound property name for <code>fuzzySet</code>. */
    public static final String PROPERTY_FUZZY_SET = "fuzzySet";

    /** Name property. */
    private String name = null;

    /** Fuzzy set property. */
    private FuzzySetBean fuzzySet = null;

    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);

    /** Support for any VetoableChangeListeners which have been registered. */
    private transient VetoableChangeSupport vetoableChangeSupport
            = new VetoableChangeSupport(this);

    /** Default <code>LinguisticTermBean</code> constructor. */
    public LinguisticTermBean() {
        // Do nothing
    }

    /**
     * Returns the name of this linguistic term.
     * @return the <code>name</code> property
     * @see #setName
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the fuzzy set of this linguistic term.
     * @return the <code>fuzzySet</code> property
     * @see #setFuzzySet
     */
    public FuzzySetBean getFuzzySet() {
        return fuzzySet;
    }

    /**
     * Sets a new fuzzy set.
     * @param newFuzzySet The new value for the property.
     * @see #getFuzzySet
     */
    public void setFuzzySet(FuzzySetBean newFuzzySet) {
        FuzzySetBean oldValue = this.fuzzySet;
        this.fuzzySet = newFuzzySet;
        propertyChangeSupport.firePropertyChange(PROPERTY_FUZZY_SET, oldValue,
                newFuzzySet);
    }

    /**
     * Sets the name of this linguistic term.
     * @param newName The new value for the property.
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     * @see #getName
     */
    public void setName(String newName) throws PropertyVetoException {
        String oldValue = this.name;
        vetoableChangeSupport.fireVetoableChange(NAME_PROPERTY, oldValue,
                newName);
        this.name = newName;
        propertyChangeSupport.firePropertyChange(NAME_PROPERTY, oldValue,
                newName);
    }

    /**
     * Adds a <code>VetoableChangeListener</code> to the listener list. The
     * listener is registered for all properties.
     * @param listener  the <code>VetoableChangeListener</code> to be added
     */
    public synchronized void addVetoableChangeListener(
            VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    /**
     * Removes a <code>VetoableChangeListener</code> from the listener list.
     * This removes a <code>VetoableChangeListener</code> that was registered
     * for all properties.
     * @param listener  the <code>VetoableChangeListener</code> to be removed
     */
    public synchronized void removeVetoableChangeListener(
            VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    /**
     * Adds a <code>PropertyChangeListener</code> to the listener list. The
     * listener is registered for all properties. <p>
     * A <code>PropertyChangeEvent</code> will get fired in response to setting
     * a bound property. <p>
     * @param listener  the <code>PropertyChangeListener</code> to be added
     */
    public synchronized void addPropertyChangeListener(
            final PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a <code>PropertyChangeListener</code> from the listener list.
     * This removes a <code>PropertyChangeListener</code> that was registered
     * for all properties.
     * @param listener  the <code>PropertyChangeListener</code> to be removed
     */
    public synchronized void removePropertyChangeListener(
            final PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}


