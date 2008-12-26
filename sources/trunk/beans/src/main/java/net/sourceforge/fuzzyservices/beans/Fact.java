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
 * This class represents a fact according to JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.Fact
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class Fact implements Serializable {
    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    //
    // Bound property names
    //

    /** Bound property name for <code>linguisticVariableName</code>. */
    public static final String LINGUISTIC_VARIABLE_NAME_PROPERTY = "linguisticVariableName";

    /** Bound property name for <code>value</code>. */
    public static final String VALUE_PROPERTY = "value";

    /** The name of a linguistic variable as reference for this fact. */
    private String linguisticVariableName = null;

    /** The value is stored as a fuzzy set. */
    private FuzzySet value = new FuzzySet();

    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /** Support for any VetoableChangeListeners which have been registered. */
    private transient VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    /** Default <code>Fact</code> constructor. */
    public Fact() {
        // Do nothing
    }

    /**
     * Constructs a new Fact based on the name of a linguistic variable
     * and a value described as fuzzy set.
     * @param newLinguisticVariableName The new value for the property.
     * @param newValue The new value for the property.
     */
    public Fact(final String newLinguisticVariableName, final FuzzySet newValue) {
        this.linguisticVariableName = newLinguisticVariableName;
        this.value = newValue;
    }

    /**
     * Returns the linguistic variable name of this fact.
     * @return the <code>linguisticVariableName</code> property
     * @see #setLinguisticVariableName
     */
    public String getLinguisticVariableName() {
        return linguisticVariableName;
    }

    /**
     * Returns the value of this fact which is represented by a fuzzy set.
     * @return the <code>value</code> property
     * @see #setValue
     */
    public FuzzySet getValue() {
        return value;
    }

    /**
     * Sets the linguistic variable name of this fact.
     * @param newLinguisticVariableName The new value for the property.
     * @exception java.beans.PropertyVetoException when the attempt to set the
     * property is vetoed by a listener
     * @see #getLinguisticVariableName
     */
    public final void setLinguisticVariableName(String newLinguisticVariableName)
        throws PropertyVetoException {
        String oldLinguisticVariableName = this.linguisticVariableName;
        vetoableChangeSupport.fireVetoableChange(LINGUISTIC_VARIABLE_NAME_PROPERTY,
            oldLinguisticVariableName, newLinguisticVariableName);
        this.linguisticVariableName = newLinguisticVariableName;
        propertyChangeSupport.firePropertyChange(LINGUISTIC_VARIABLE_NAME_PROPERTY,
            oldLinguisticVariableName, newLinguisticVariableName);
    }

    /**
     * Defines a new value for this fact.
     * @param newValue The new value for the property.
     * @see #getValue
     */
    public final void setValue(FuzzySet newValue) {
        FuzzySet oldValue = this.value;
        this.value = newValue;
        propertyChangeSupport.firePropertyChange(VALUE_PROPERTY, oldValue,
            newValue);
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
