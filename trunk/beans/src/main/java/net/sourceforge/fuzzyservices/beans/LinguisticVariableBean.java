/*******************************************************************************
 *
 *  Copyright (C) 2007  Uwe Weng
 *
 *  This file is part of JFuzzy, a library for processing fuzzy information.
 *
 *  JFuzzy is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  JFuzzy is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with JFuzzy; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  The license is also available at http://www.gnu.org/licenses/gpl.txt
 *
 ******************************************************************************/
package net.sourceforge.fuzzyservices.beans;

import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

/**
 * This class represents a linguistic variable according to JavaBeans
 * conventions.
 *
 * @see com.jfuzzy.core.LinguisticVariable
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class LinguisticVariableBean implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    //
    // Bound property names
    //

    /** Bound property name for <code>name</code>. */
    public static final String NAME_PROPERTY = "name";

    /** Bound property name for <code>linguisticTerms</code>. */
    public static final String LINGUISTIC_TERMS_PROPERTY = "linguisticTerms";

    /** Name property. */
    private String name = null;

    /** A linguistic variable consists of an array of linguistic terms. */
    private LinguisticTermBean[] linguisticTerms = null;

    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);

    /** Support for any VetoableChangeListeners which have been registered. */
    private transient VetoableChangeSupport vetoableChangeSupport
            = new VetoableChangeSupport(this);

    /** Default <code>LinguisticVariableBean</code> constructor. */
    public LinguisticVariableBean() {
        // Do nothing
    }

    /**
     * Returns the name of this linguistic variable.
     * @return the <code>name</code> property
     * @see #setName
     */
    public String getName() {
        return name;
    }

    /**
     * Returns all linguistic terms of this linguistic variable.
     * @return the <code>linguisticTerms</code> property
     * @see #setLinguisticTerms(LinguisticTermBean[])
     */
    public LinguisticTermBean[] getLinguisticTerms() {
        return linguisticTerms;
    }

    /**
     * Gets the linguistic term located at the specified <code>index</code> of
     * this linguistic variable.
     * @param     index the position of the linguistic term to be returned.
     * @return    the linguistic term located at the specified index.
     * @exception ArrayIndexOutOfBoundsException if <code>index</code> is an
     * invalid position
     * @see #setLinguisticTerms(LinguisticTermBean[])
     */
    public LinguisticTermBean getLinguisticTerms(int index)
    throws ArrayIndexOutOfBoundsException {
        return getLinguisticTerms()[index];
    }

    /**
     * Gets the linguistic term with the specified <code>name</code> of this
     * linguistic variable.
     * @param     aName the name of the linguistic term to be returned.
     * @return    the linguistic term with the specified name.
     * @see #setLinguisticTerms(LinguisticTermBean[])
     */
    public LinguisticTermBean getLinguisticTerm(String aName) {
        if (this.linguisticTerms != null) {
            for (int i = 0; i < linguisticTerms.length; i++) {
                if (this.linguisticTerms[i] != null) {
                    if ((aName == this.linguisticTerms[i].getName()) ||
                            ((aName != null) &&
                            (aName.equals(linguisticTerms[i].getName()) == true))) {
                        return linguisticTerms[i];
                    }
                }
            }
        }

        return null;
    }

    /**
     * Defines new linguistic terms for this linguistic variable.
     * @param newLinguisticTerms An array with linguistic terms
     * @exception IllegalArgumentException if there are identical terms
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     * @see #getLinguisticTerms()
     */
    public void setLinguisticTerms(LinguisticTermBean[] newLinguisticTerms)
    throws IllegalArgumentException, PropertyVetoException {
        // Check linguistic terms
        if (newLinguisticTerms != null) {
            // Duplicate linguistic terms to one linguistic variable?
            for (int i = 0; i < (newLinguisticTerms.length - 1); i++) {
                if (newLinguisticTerms[i] != null) {
                    for (int j = i + 1; j < newLinguisticTerms.length; j++) {
                        if (newLinguisticTerms[j] != null) {
                            if (newLinguisticTerms[j].equals(
                                    newLinguisticTerms[i]) == true) {
                                throw new IllegalArgumentException(
                                        FuzzyResourceManager.getString(this,
                                        "EXCEPTION_INVALID_LINGUISTIC_TERM"));
                            }
                        }
                    }
                }
            }
        }

        net.sourceforge.fuzzyservices.beans.LinguisticTermBean[] oldValue = this.linguisticTerms;
        vetoableChangeSupport.fireVetoableChange(LINGUISTIC_TERMS_PROPERTY,
                oldValue, newLinguisticTerms);
        this.linguisticTerms = newLinguisticTerms;
        propertyChangeSupport.firePropertyChange(LINGUISTIC_TERMS_PROPERTY,
                oldValue, newLinguisticTerms);
    }

    /**
     * Replaces a linguistic term of this linguistic variable.
     * @param index The index value into the property array.
     * @param newLinguisticTerms A new linguistic term for this linguistic
     * variable
     * @exception ArrayIndexOutOfBoundsException if <code>index</code> is an
     * invalid position
     * @exception IllegalArgumentException if there are identical terms
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     * @see #getLinguisticTerms()
     */
    public void setLinguisticTerms(int index,
            LinguisticTermBean newLinguisticTerms)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException,
            PropertyVetoException {
        // Check linguistic terms
        if (newLinguisticTerms != null) {
            // Duplicate linguistic terms to one linguistic variable?
            for (int i = 0; i < this.linguisticTerms.length; i++) {
                if ((this.linguisticTerms[i] != null) && (i != index)) {
                    if (newLinguisticTerms.equals(this.linguisticTerms[i]) == true) {
                        throw new IllegalArgumentException(
                                FuzzyResourceManager.getString(this,
                                "EXCEPTION_INVALID_LINGUISTIC_TERM"));
                    }
                }
            }
        }

        LinguisticTermBean oldValue = this.linguisticTerms[index];

        if ((oldValue != null) && !oldValue.equals(newLinguisticTerms)) {
            vetoableChangeSupport.fireVetoableChange(LINGUISTIC_TERMS_PROPERTY,
                    null, this.linguisticTerms);
        }

        ;
        this.linguisticTerms[index] = newLinguisticTerms;

        if ((oldValue != null) && !oldValue.equals(newLinguisticTerms)) {
            propertyChangeSupport.firePropertyChange(LINGUISTIC_TERMS_PROPERTY,
                    null, this.linguisticTerms);
        }

        ;
    }

    /**
     * Sets the name of this linguistic variable.
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
     * Tests whether or not the <code>linguisticTerm</code> is part of this
     * linguistic variable.
     * @param linguisticTerm The linguistic term to check
     * @return    <code>true</code> if the <code>linguisticTerm</code> is part
     * of this linguistic variable, <code>false</code> otherwise.
     * @see #setLinguisticTerms(LinguisticTermBean[])
     */
    public boolean contains(LinguisticTermBean linguisticTerm) {
        if ((linguisticTerms != null) && (linguisticTerm != null)) {
            for (int i = 0; i < linguisticTerms.length; i++) {
                if ((linguisticTerms[i] != null) &&
                        (linguisticTerms[i].equals(linguisticTerm) == true)) {
                    return true;
                }
            }
        }

        return false;
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


