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
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
    /**
     * Unique technical identifier. Only for persistence is used.
     */
    private int id;
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
     * Returns the technical identifier (e.g. within a database).
     * @return the ID
     */
    public int getId() {
        return id;
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
        LinguisticTerm linguisticTerm = (LinguisticTerm) obj;
        return new EqualsBuilder().append(this.id, linguisticTerm.id).append(this.name, linguisticTerm.name).append(this.fuzzySet, linguisticTerm.fuzzySet).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 21).append(this.id).append(this.name).append(this.fuzzySet).toHashCode();
    }

    @Override
    public String toString() {
        String nameText = "";
        if (name != null) {
            nameText = FuzzyResourceManager.getString(this,
                    "LINGUISTIC_TERM_NAME",
                    new Object[]{
                        name
                    });
        } else {
            nameText = FuzzyResourceManager.getString(this,
                    "LINGUISTIC_TERM_UNKNOWN_NAME");
        }

        String fuzzySetText = "";
        if (fuzzySet != null) {
            Defuzzificator def = new Defuzzificator(Defuzzificator.TYPE_CENTER_OF_AREA);
            fuzzySetText = FuzzyResourceManager.getString(this,
                    "LINGUISTIC_TERM_FUZZY_SET",
                    new Object[]{
                        fuzzySet.toString(false),
                        fuzzySet.toString(true),
                        Float.toString(def.defuzzify(fuzzySet))
                    });
        } else {
            fuzzySetText = FuzzyResourceManager.getString(this,
                    "LINGUISTIC_TERM_UNKNOWN_FUZZY_SET");
        }

        return FuzzyResourceManager.getString(this,
                "LINGUISTIC_TERM",
                new Object[]{
                    id,
                    nameText,
                    fuzzySetText
                });
    }
}
