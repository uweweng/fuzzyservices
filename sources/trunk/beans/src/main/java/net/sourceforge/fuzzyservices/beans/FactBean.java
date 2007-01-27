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
 * This class represents a fact according to JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.Fact
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class FactBean implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    //
    // Bound property names
    //

    /** Bound property name for <code>linguisticVariable</code>. */
    public static final String LINGUISTIC_VARIABLE_PROPERTY
            = "linguisticVariable";

    /** Bound property name for <code>value</code>. */
    public static final String VALUE_PROPERTY = "value";

    /** The linguistic variable as reference for this fact. */
    private LinguisticVariableBean linguisticVariable = null;

    /** The value is stored as a fuzzy set. */
    private FuzzySetBean value = new FuzzySetBean();

    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);

    /** Support for any VetoableChangeListeners which have been registered. */
    private transient VetoableChangeSupport vetoableChangeSupport
            = new VetoableChangeSupport(this);

    /** Default <code>FactBean</code> constructor. */
    public FactBean() {
        // Do nothing
    }

    /**
     * Returns the linguistic variable of this fact.
     * @return the <code>linguisticVariable</code> property
     * @see #setLinguisticVariable
     */
    public LinguisticVariableBean getLinguisticVariable() {
        return linguisticVariable;
    }

    /**
     * Returns the value of this fact which is represented by a fuzzy set.
     * @return the <code>value</code> property
     * @see #setValue
     */
    public FuzzySetBean getValue() {
        return value;
    }

    /**
     * Sets the linguistic variable of this fact.
     * @param newLinguisticVariable The new value for the property.
     * @exception java.beans.PropertyVetoException when the attempt to set the
     * property is vetoed by a listener
     * @see #getLinguisticVariable
     */
    public void setLinguisticVariable(
            LinguisticVariableBean newLinguisticVariable)
            throws PropertyVetoException {
        LinguisticVariableBean oldValue = this.linguisticVariable;
        vetoableChangeSupport.fireVetoableChange(LINGUISTIC_VARIABLE_PROPERTY,
                oldValue, newLinguisticVariable);
        this.linguisticVariable = newLinguisticVariable;
        propertyChangeSupport.firePropertyChange(LINGUISTIC_VARIABLE_PROPERTY,
                oldValue, newLinguisticVariable);
    }

    /**
     * Defines a new value for this fact.
     * @param newValue The new value for the property.
     * @see #getValue
     */
    public void setValue(FuzzySetBean newValue) {
        FuzzySetBean oldValue = this.value;
        this.value = newValue;
        propertyChangeSupport.firePropertyChange(VALUE_PROPERTY, oldValue,
                newValue);
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


