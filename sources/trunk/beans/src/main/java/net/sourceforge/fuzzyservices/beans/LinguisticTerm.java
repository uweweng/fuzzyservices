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
 * @version 1.0
 * @author Uwe Weng
 */
public class LinguisticTerm implements Serializable {

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
    private FuzzySet fuzzySet = new FuzzySet();
    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    /** Support for any VetoableChangeListeners which have been registered. */
    private transient VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    /** Default <code>LinguisticTerm</code> constructor. */
    public LinguisticTerm() {
        // Do nothing
    }

    /**
     * Constructs a new linguistic Term based on a name and a fuzzy set.
     * @param newName The new value for the property.
     * @param newFuzzySet The new value for the property.
     */
    public LinguisticTerm(final String newName, final FuzzySet newFuzzySet) {
        this.name = newName;
        this.fuzzySet = newFuzzySet;
    }

    /**
     * Returns the name of this linguistic term.
     * @return the <code>name</code> property
     * @see #setName
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the fuzzy set of this linguistic term.
     * @return the <code>fuzzySet</code> property
     * @see #setFuzzySet
     */
    public final FuzzySet getFuzzySet() {
        return fuzzySet;
    }

    /**
     * Sets a new fuzzy set.
     * @param newFuzzySet The new value for the property.
     * @see #getFuzzySet
     */
    public final void setFuzzySet(final FuzzySet newFuzzySet) {
        FuzzySet oldValue = this.fuzzySet;
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
    public final void setName(final String newName) throws PropertyVetoException {
        String oldValue = this.name;
        vetoableChangeSupport.fireVetoableChange(NAME_PROPERTY, oldValue, newName);
        this.name = newName;
        propertyChangeSupport.firePropertyChange(NAME_PROPERTY, oldValue, newName);
    }

    /**
     * Adds a <code>VetoableChangeListener</code> to the listener list. The
     * listener is registered for all properties.
     * @param listener  the <code>VetoableChangeListener</code> to be added
     */
    public final synchronized void addVetoableChangeListener(
            final VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    /**
     * Removes a <code>VetoableChangeListener</code> from the listener list.
     * This removes a <code>VetoableChangeListener</code> that was registered
     * for all properties.
     * @param listener  the <code>VetoableChangeListener</code> to be removed
     */
    public final synchronized void removeVetoableChangeListener(
            final VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    /**
     * Adds a <code>PropertyChangeListener</code> to the listener list. The
     * listener is registered for all properties. <p>
     * A <code>PropertyChangeEvent</code> will get fired in response to setting
     * a bound property. <p>
     * @param listener  the <code>PropertyChangeListener</code> to be added
     */
    public final synchronized void addPropertyChangeListener(
            final PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a <code>PropertyChangeListener</code> from the listener list.
     * This removes a <code>PropertyChangeListener</code> that was registered
     * for all properties.
     * @param listener  the <code>PropertyChangeListener</code> to be removed
     */
    public final synchronized void removePropertyChangeListener(
            final PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
