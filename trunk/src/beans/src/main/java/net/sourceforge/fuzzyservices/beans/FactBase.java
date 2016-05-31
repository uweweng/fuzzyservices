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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This class represents a fact base according to JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.FactBase
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class FactBase implements VetoableChangeListener, Serializable, Cloneable {

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
    /** Bound property name for <code>facts</code>. */
    public static final String FACTS_PROPERTY = "facts";
    /** Name property. */
    private String name = null;
    /** Facts property. A fact base contains an array of facts. It is internal an array list because of JPA support.*/
    private List<Fact> facts = new ArrayList<Fact>();
    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /** Default <code>FactBase</code> constructor. */
    public FactBase() {
        // Do nothing
    }

    /**
     * Constructs a new fact base with given name.
     * @param newName The new value for the property.
     */
    public FactBase(final String newName) {
        this();
        this.name = newName;
    }

    /**
     * Returns the technical identifier (e.g. within a database).
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of this fact base.
     * @return the <code>name</code> property
     * @see #setName
     */
    public String getName() {
        return name;
    }

    /**
     * Returns all facts of this fact base.
     * @return the <code>facts</code> property
     * @see #setFacts(Fact[])
     */
    public Fact[] getFacts() {
        return facts.toArray(new Fact[facts.size()]);
    }

    /**
     * Gets the fact located at the specified <code>index</code> of this fact
     * base.
     * @param     index the position of the fact to be returned.
     * @return    the fact located at the specified index.
     * @see #setFacts(Fact[])
     */
    public Fact getFacts(final int index) {
        return facts.get(index);
    }

    /**
     * Sets the name of this fact base.
     * @param newName The new value for the property.
     * @see #getName
     */
    public void setName(String newName) {
        String oldValue = this.name;
        this.name = newName;
        propertyChangeSupport.firePropertyChange(NAME_PROPERTY, oldValue,
                newName);
    }

    /**
     * Fills the fact base with facts.
     * @param newFacts An array with facts.
     * @exception IllegalArgumentException if a linguistic variable of a fact
     * is not defined
     * or if at least two facts relates to one linguistic variable
     * @see #getFacts()
     */
    public void setFacts(Fact[] newFacts) throws IllegalArgumentException {
        // Check facts
        if (newFacts != null) {
            // Duplicate facts to one linguistic variable?
            for (int i = 0; i < (newFacts.length - 1); i++) {
                if (newFacts[i] != null) {
                    if (newFacts[i].getLinguisticVariableName() == null) {
                        throw new IllegalArgumentException(FuzzyResourceManager.getString(
                                this, "EXCEPTION_FACT_WITHOUT_LV"));
                    }

                    for (int j = i + 1; j < newFacts.length; j++) {
                        if ((newFacts[j] != null)
                                && (newFacts[j].getLinguisticVariableName() != null)) {
                            if (newFacts[j].getLinguisticVariableName().equals(newFacts[i].getLinguisticVariableName()) == true) {
                                throw new IllegalArgumentException(FuzzyResourceManager.getString(
                                        this,
                                        "EXCEPTION_FACT_BASE_WITH_DUPLICATE_LV"));
                            }
                        }
                    }
                }
            }
        }

        Fact[] oldValue = getFacts();

        // Remove vetoable change listener
        for (Iterator<Fact> it = facts.iterator(); it.hasNext();) {
            Fact fact = it.next();
            if (fact != null) {
                fact.removeVetoableChangeListener(this);
            }
        }

        if (newFacts != null) {
            this.facts = new ArrayList(Arrays.asList(newFacts));
            
            // Add vetoable change listener
            for (Iterator<Fact> it = facts.iterator(); it.hasNext();) {
                Fact fact = it.next();
                if (fact != null) {
                    fact.addVetoableChangeListener(this);
                }
            }
        } else {
            this.facts = new ArrayList<Fact>();
        }
        propertyChangeSupport.firePropertyChange(FACTS_PROPERTY, oldValue,
                newFacts);
    }

    /**
     * Replaces the fact at the position <code>index</code> of the fact base.
     * @param index The index value into the property array.
     * @param newFacts The new fact for the fact base.
     * @exception ArrayIndexOutOfBoundsException if <code>index</code> is an
     * invalid position
     * @exception IllegalArgumentException if a linguistic variable of a fact
     * is not defined
     * or if at least two facts relates to one linguistic variable
     * @see #getFacts()
     */
    public void setFacts(int index, Fact newFacts)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        // Check facts
        if (newFacts != null) {
            if (newFacts.getLinguisticVariableName() == null) {
                throw new IllegalArgumentException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_FACT_WITHOUT_LV"));
            }

            // Duplicate facts to one linguistic variable?
            for (Iterator<Fact> it = facts.iterator(); it.hasNext();) {
                Fact fact = it.next();
                if ((fact != null) && (facts.indexOf(facts) != index)) {
                    if (newFacts.getLinguisticVariableName().equals(fact.getLinguisticVariableName()) == true) {
                        throw new IllegalArgumentException(FuzzyResourceManager.getString(
                                this, "EXCEPTION_FACT_BASE_WITH_DUPLICATE_LV"));
                    }
                }
            }
        }

        Fact oldValue = facts.get(index);

        if (oldValue != null) {
            // Remove vetoable change listener
            oldValue.removeVetoableChangeListener(this);
        }

        facts.set(index, newFacts);

        if (newFacts != null) {
            // Add vetoable change listener
            newFacts.addVetoableChangeListener(this);
        }

        if ((oldValue != null) && !oldValue.equals(newFacts)) {
            propertyChangeSupport.firePropertyChange(FACTS_PROPERTY, null,
                    facts.toArray());
        }
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt)
            throws PropertyVetoException {
        String prop = evt.getPropertyName();

        if (prop.equals(Fact.LINGUISTIC_VARIABLE_NAME_PROPERTY) == true) {
            if (evt.getNewValue() == null) {
                throw new PropertyVetoException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_FACT_WITHOUT_LV"), evt);
            }

            // Check for duplicate facts to one linguistic variable (name)
            String newLinguisticVariableName = (String) evt.getNewValue();

            for (Iterator<Fact> it = facts.iterator(); it.hasNext();) {
                Fact fact = it.next();
                if ((fact != null)
                        && (newLinguisticVariableName.equals(
                        fact.getLinguisticVariableName()) == true)) {
                    throw new PropertyVetoException(FuzzyResourceManager.getString(
                            this, "EXCEPTION_FACT_BASE_WITH_DUPLICATE_LV"), evt);
                }
            }
        }
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
        FactBase factbase = (FactBase) obj;
        return new EqualsBuilder().append(this.id, factbase.id).append(this.name, factbase.name).append(this.facts, factbase.facts).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 21).append(this.id).append(this.name).append(this.facts).toHashCode();
    }

    @Override
    public String toString() {
        return toString(false);
    }

    /**
     * Returns a textual representation of the fact base
     *
     * @param withFacts
     *            <code>true</code> if all fact definitions are also returned,
     *            <code>false</code> otherwise.
     * @return a string representation of the fact base
     * @see Fact#toString
     */
    public String toString(final boolean withFacts) {
        if (withFacts) {
            return FuzzyResourceManager.getString(this,
                    "FACT_BASE_WITH_FACTS",
                    new Object[]{
                        id,
                        name,
                        Integer.toString(facts.size()),
                        facts.toString()});
        } else {
            return FuzzyResourceManager.getString(this,
                    "FACT_BASE_WITHOUT_FACTS",
                    new Object[]{
                        id,
                        name,
                        Integer.toString(facts.size()),
                        facts.toString()});
        }
    }
}
