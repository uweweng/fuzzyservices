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

import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This class represents a linguistic variable according to JavaBeans
 * conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.LinguisticVariable
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class LinguisticVariable implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Unique technical identifier. Only for persistence is used.
     */
    private int id;
    //
    // Bound property names
    //
    /** Bound property name for <code>name</code>. */
    public static final String NAME_PROPERTY = "name";
    /** Bound property name for <code>linguisticTerms</code>. */
    public static final String LINGUISTIC_TERMS_PROPERTY = "linguisticTerms";
    /** Name property. */
    private String name = null;
    /** A linguistic variable consists of an array of linguistic terms. It is internal an array list because of JPA support.*/
    private List<LinguisticTerm> linguisticTerms = new ArrayList<LinguisticTerm>();
    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    /** Support for any VetoableChangeListeners which have been registered. */
    private transient VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    /** Default <code>LinguisticVariable</code> constructor. */
    public LinguisticVariable() {
        // Do nothing
    }

    /**
     * Returns the technical identifier (e.g. within a database).
     * @return the ID
     */
    public int getId() {
        return id;
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
     * @see #setLinguisticTerms(LinguisticTerm[])
     */
    public LinguisticTerm[] getLinguisticTerms() {
        return linguisticTerms.toArray(new LinguisticTerm[linguisticTerms.size()]);
    }

    /**
     * Gets the linguistic term located at the specified <code>index</code> of
     * this linguistic variable.
     * @param     index the position of the linguistic term to be returned.
     * @return    the linguistic term located at the specified index.
     * @exception ArrayIndexOutOfBoundsException if <code>index</code> is an
     * invalid position
     * @see #setLinguisticTerms(LinguisticTerm[])
     */
    public LinguisticTerm getLinguisticTerms(int index)
            throws ArrayIndexOutOfBoundsException {
        return linguisticTerms.get(index);
    }

    /**
     * Gets the linguistic term with the specified <code>name</code> of this
     * linguistic variable.
     * @param     aName the name of the linguistic term to be returned.
     * @return    the linguistic term with the specified name.
     * @see #setLinguisticTerms(LinguisticTerm[])
     */
    public LinguisticTerm getLinguisticTerm(String aName) {
        for (Iterator<LinguisticTerm> it = linguisticTerms.iterator(); it.hasNext();) {
            LinguisticTerm linguisticTerm = it.next();
            if (linguisticTerm != null) {
                if ((aName != null)
                        && (aName.equals(linguisticTerm.getName()) == true)) {
                    return linguisticTerm;
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
    public final void setLinguisticTerms(LinguisticTerm[] newLinguisticTerms)
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
                                throw new IllegalArgumentException(FuzzyResourceManager.getString(
                                        this,
                                        "EXCEPTION_INVALID_LINGUISTIC_TERM"));
                            }
                        }
                    }
                }
            }
        }

        LinguisticTerm[] oldValue = getLinguisticTerms();
        vetoableChangeSupport.fireVetoableChange(LINGUISTIC_TERMS_PROPERTY,
                oldValue, newLinguisticTerms);
        this.linguisticTerms = Arrays.asList(newLinguisticTerms);
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
    public final void setLinguisticTerms(int index,
            LinguisticTerm newLinguisticTerms)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException,
            PropertyVetoException {
        // Check linguistic terms
        if (newLinguisticTerms != null) {
            // Duplicate linguistic terms to one linguistic variable?
            int oldIndex = linguisticTerms.indexOf(newLinguisticTerms);
            if ((oldIndex != -1) && (oldIndex == index)) {
                throw new IllegalArgumentException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_INVALID_LINGUISTIC_TERM"));
            }
        }

        LinguisticTerm oldValue = linguisticTerms.get(index);

        if ((oldValue != null) && !oldValue.equals(newLinguisticTerms)) {
            vetoableChangeSupport.fireVetoableChange(LINGUISTIC_TERMS_PROPERTY,
                    null, this.linguisticTerms.toArray());
        }

        linguisticTerms.set(index, newLinguisticTerms);

        if ((oldValue != null) && !oldValue.equals(newLinguisticTerms)) {
            propertyChangeSupport.firePropertyChange(LINGUISTIC_TERMS_PROPERTY,
                    null, linguisticTerms.toArray());
        }
    }

    /**
     * Sets the name of this linguistic variable.
     * @param newName The new value for the property.
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     * @see #getName
     */
    public final void setName(String newName) throws PropertyVetoException {
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
     * @see #setLinguisticTerms(LinguisticTerm[])
     */
    public final boolean contains(LinguisticTerm linguisticTerm) {
        return linguisticTerms.contains(linguisticTerm);
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

    @Override
    public Object clone() {
        return SerializationUtils.clone(this);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        LinguisticVariable linguisticVariable = (LinguisticVariable) obj;
        return new EqualsBuilder().append(this.id, linguisticVariable.id).append(this.name, linguisticVariable.name).append(this.linguisticTerms, linguisticVariable.linguisticTerms).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 21).append(this.id).append(this.name).append(this.linguisticTerms).toHashCode();
    }

    @Override
    public String toString() {
        return toString(false);
    }

    /**
     * Returns a textual representation of the linguistic variable
     *
     * @param withTerms
     *            <code>true</code> if all linguistic term definitions are also returned,
     *            <code>false</code> otherwise.
     * @return a string representation of the linguistic variable
     * @see LinguisticTerm#toString
     */
    public String toString(final boolean withTerms) {
        if (withTerms) {
            return FuzzyResourceManager.getString(this,
                    "LINGUISTIC_VARIABLE_WITH_TERMS",
                    new Object[]{
                        id,
                        name,
                        Integer.toString(linguisticTerms.size()),
                        linguisticTerms.toString()});
        } else {
            return FuzzyResourceManager.getString(this,
                    "LINGUISTIC_VARIABLE_WITHOUT_TERMS",
                    new Object[]{
                        id,
                        name,
                        Integer.toString(linguisticTerms.size()),
                        linguisticTerms.toString()});
        }
    }
}
