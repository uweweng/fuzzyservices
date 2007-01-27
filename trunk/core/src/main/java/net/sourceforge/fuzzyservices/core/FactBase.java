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
package net.sourceforge.fuzzyservices.core;

import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;
import java.util.Enumeration;
import java.util.Hashtable;


/**
 * A fact base contains a set of facts to different linguistic variables. It is
 * the base in combination with a rule base for approximate reasoning.
 * <p>
 * Optionally, a fact base can have a name.
 *
 * @see Fact
 * @see RuleBase
 * @see FuzzyController
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class FactBase implements java.lang.Cloneable, java.io.Serializable {
    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * The facts are organized by a hashtable. The key is the name of the
     * corresponding linguistic variable. The value is the fact itself.
     */
    protected Hashtable facts = new Hashtable(); // Set of facts

    /** The fact base can have a name. */
    protected String name = null;

    /** Default constructor which creates a new empty fact base. */
    public FactBase() {
        // do nothing
    }

    /**
     * Constructs a fact base with the first fact.
     *
     * @param fact
     *            the first fact
     */
    public FactBase(final Fact fact) {
        if (fact != null)
            facts.put(fact.getLinguisticVariableName(), fact);
    }

    /**
     * Adds a fact to the fact base.
     *
     * @param fact
     *            the new fact of the fact base.
     */
    public synchronized void add(final Fact fact) {
        put(fact);
    }

    /** Removes all facts from this fact base. */
    public synchronized void clear() {
        facts.clear();
    }

    /**
     * Checks whether the fact base contains a fact to a linguistic variable.
     *
     * @param lv
     *            the linguistic variable to which a fact may exist in the fact
     *            base
     * @return <code>true</code> if a fact exists, <code>false</code>
     *         otherwise.
     */
    public synchronized boolean contains(final LinguisticVariable lv) {
        if (lv != null)
            return facts.containsKey(lv.getName());
        else

            return false;
    }

    /**
     * Checks whether the fact base contains a fact to a linguistic variable.
     *
     * @param lingVarName
     *            the name of a linguistic variable to which a fact may exist in
     *            the fact base
     * @return <code>true</code> if a fact exists, <code>false</code>
     *         otherwise.
     */
    public synchronized boolean contains(final String lingVarName) {
        if (lingVarName != null)
            return facts.containsKey(lingVarName);
        else

            return false;
    }

    /**
     * Returns all facts as enumeration.
     *
     * @return an enumeration with all facts of this fact base
     */
    public synchronized Enumeration elements() {
        return facts.elements();
    }

    /**
     * Returns the fact to a linguistic variable.
     *
     * @param lv
     *            the linguistic variable
     * @return the fact to the linguistic variable, <code>null</code>
     *         otherwise
     */
    public synchronized Fact get(final LinguisticVariable lv) {
        if (lv != null) {
            Object retfact = facts.get(lv.getName());

            if (retfact != null)
                return (Fact) retfact;
        }

        return null;
    }

    /**
     * Returns the fact to a linguistic variable name.
     *
     * @param lingVarName
     *            the name of a linguistic variable
     * @return the fact to the linguistic variable, <code>null</code>
     *         otherwise
     */
    public synchronized Fact get(final String lingVarName) {
        if (lingVarName != null) {
            Object retfact = facts.get(lingVarName);

            if (retfact != null)
                return (Fact) retfact;
        }

        return null;
    }

    /**
     * Returns the name of this fact base.
     *
     * @return the <code>name</code> property
     * @see #setName
     */
    public synchronized String getName() {
        return name;
    }

    /**
     * Checks whether fact base contains any facts.
     *
     * @return <code>true</code> if fact base has not got any facts,
     *         <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return facts.isEmpty();
    }

    /**
     * Puts a new fact into the fact base.
     *
     * @param fact
     *            the new fact
     * @return the old fact, if it exists to the same linguistic variable,
     *         <code>null</code> otherwise
     */
    public synchronized Fact put(final Fact fact) {
        if (fact == null) {
            return null;
        }

        Object retfact = facts.put(fact.getLinguisticVariable().getName(), fact);

        if (retfact != null)
            return (Fact) retfact;
        else

            return null;
    }

    /**
     * Removes the <code>fact</code> from the fact base.
     *
     * @param fact
     *            the fact to be deleted
     * @return <code>true</code> if the fact was removed successfully,
     *         <code>false</code> otherwise.
     */
    public synchronized boolean remove(final Fact fact) {
        if (fact != null) {
            return ((facts.remove(fact.getLinguisticVariable().getName()) != null)
            ? true : false);
        }

        return false;
    }

    /**
     * Removes the fact of the linguistic variable <code>lv</code> from the
     * fact base.
     *
     * @param lv
     *            the linguistic variable of a fact to be deleted
     * @return the fact identified by the linguistic variable, <code>null</code>
     *         otherwise.
     */
    public synchronized Fact remove(final LinguisticVariable lv) {
        if (lv != null) {
            Object retfact = facts.remove(lv.getName());

            if (retfact != null)
                return (Fact) retfact;
        }

        return null;
    }

    /**
     * Removes the fact of a linguistic variable with the name
     * <code>lingVarName</code> from the fact base.
     *
     * @param lingVarName
     *            the name of a linguistic variable of a fact to be deleted
     * @return the fact identified by the name of a linguistic variable,
     *         <code>null</code> otherwise.
     */
    public synchronized Fact remove(final String lingVarName) {
        if (lingVarName != null) {
            Object retfact = facts.remove(lingVarName);

            if (retfact != null)
                return (Fact) retfact;
        }

        return null;
    }

    /**
     * Sets the name of this fact base.
     *
     * @param name
     *            The new value for the property.
     * @see #getName
     */
    public synchronized void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the number of facts within the fact base.
     *
     * @return the size of the fact base
     */
    public int size() {
        return facts.size();
    }

    /**
     * Creates and returns a copy of this fact base.
     *
     * @return a copy of this fact base
     */
    public Object clone() {
        try {
            FactBase newObj = (FactBase) super.clone();
            // Eintraege physisch duplizieren.
            newObj.facts = new java.util.Hashtable();

            Enumeration elements = facts.elements();

            while (elements.hasMoreElements()) {
                Fact fact = (Fact) elements.nextElement();
                newObj.facts.put(fact.getLinguisticVariableName(), fact.clone());
            }

            return newObj;
        } catch (java.lang.CloneNotSupportedException e) {
            // it is impossible
            throw new InternalError(e.toString());
        }
    }

    /**
     * Indicates whether some other object is "equal to" this fact base
     *
     * @param obj
     *            the reference object with which to compare
     * @return <code>true</code> if this fact base is the same as the
     *         <code>obj</code> argument, <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof FactBase)) {
            // Comparing number of facts
            // The names are not compared because it is optional
            if (this.facts.size() != ((FactBase) obj).facts.size())
                return false;

            // Comparing facts
            Enumeration elementsObj = ((FactBase) obj).facts.keys();

            while (elementsObj.hasMoreElements()) {
                Object key = elementsObj.nextElement();

                if (!this.facts.containsKey(key))
                    return false;

                if (!this.facts.get(key).equals(((FactBase) obj).facts.get(key)))
                    return false;
            }

            return true;
        }

        return false;
    }

    /**
     * Returns a textual representation of the fact base
     *
     * @return a string representation of the fact base
     */
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
            String text = "{";
            Enumeration elements = facts.elements();

            if (elements.hasMoreElements())
                text = text + ((Fact) elements.nextElement()).toString();

            while (elements.hasMoreElements()) {
                text = text + ", " +
                    ((Fact) elements.nextElement()).toString();
            }

            text = text + "}";

            return text;
        }

        return FuzzyResourceManager.getString(this, "FACT_BASE_WITHOUT_FACTS",
            new Object[] { Integer.toString(facts.size()) });
    }
}
