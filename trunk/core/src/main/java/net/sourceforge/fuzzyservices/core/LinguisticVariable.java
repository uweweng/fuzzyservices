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
 * A linguistic variable describes a fuzzy feature, which has got multiple
 * values. Every value is called linguistic term and is represented by a name and a fuzzy set.
 * Linguistic variable can be used in facts.
 * <p>
 * The class <code>LinguisticVariable</code> is the base of defining linguistic variables.
 * The name is required at instantiation time. It is the identifier in a fact base and rules.
 *
 * @see Fact
 * @see FactBase
 * @see Rule
 * @see FuzzySet
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class LinguisticVariable implements java.lang.Cloneable,
    java.io.Serializable {
    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /** The name of the linguistic variable */
    protected String name;

    /**
     * The linguistic terms are stored in a hashtable with name as key and fuzzy
     * set as value.
     */
    protected Hashtable terms = new Hashtable();

    /**
     * The default constructor is private because a linguistic variable must
     * have a name.
     */
    private LinguisticVariable() {
        // Not allowed
    }

    /**
     * Constructs a linguistic variable with this <code>name</code>.
     *
     * @param newName
     *            the name of the linguistic variable
     * @exception NullPointerException
     *                if name is <code>null</code>
     * @exception IllegalArgumentException
     *                if name is an empty string
     */
    public LinguisticVariable(final String newName) throws NullPointerException, IllegalArgumentException{
        if (newName.length() > 0) {
            this.name = newName;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, 
                    "EXCEPTION_LINGUISTIC_VARIABLE_INVALID_NAME",new String[] {newName}));
    }

    /** Removes all linguistic terms from this linguistic variable. */
    public synchronized void clear() {
        terms.clear();
    }

    /*
     * public boolean equals(Object obj) { if ( (obj != null) && ( obj
     * instanceof LinguisticVariable ) && (this.name ==
     * ((LinguisticVariable)obj).name) && (
     * terms.equals(((LinguisticVariable)obj).terms) )) return true; else return
     * false; }
     */

    /**
     * Checks whether the linguistic variable contains a term with this
     * <code>name</code>.
     *
     * @param aName
     *            the name of a linguistic term which belongs to this variable
     * @return <code>true</code> if a term exists, <code>false</code>
     *         otherwise.
     */
    public synchronized boolean contains(String aName) {
        if (aName != null)
            return terms.containsKey(aName);
        else

            return false;
    }

    /**
     * Returns the fuzzy set belonging to the linguistic term with this
     * <code>name</code>
     *
     * @return fuzzy set if a linguistic term with the name exists,
     *         <code>null</code> otherwise
     * @param name the name of the linguistic variable which fuzzy set will be returned
     * @see LinguisticVariable
     */
    public synchronized FuzzySet getFuzzySet(String name) {
        if (name != null) {
            Object fset = terms.get(name);

            if (fset != null)
                return (FuzzySet) fset;
        }

        return null;
    }

    /**
     * Returns the maximum defined x-value of all linguistic terms.
     *
     * @return the maximum x-value
     */
    public synchronized float getMaxDefinedX() {
        float maxX = Float.NaN;
        float maxTermX;
        FuzzySet fs;
        Enumeration elements = terms.elements();

        while (elements.hasMoreElements()) {
            fs = (FuzzySet) elements.nextElement();

            if (fs.isDefined()) {
                maxTermX = fs.getMaxDefinedX();

                if (maxX == maxX) { // that means not Float.NaN

                    if (maxTermX > maxX)
                        maxX = maxTermX;
                } else
                    maxX = maxTermX;
            }
        }

        return maxX;
    }

    /**
     * Returns the minimum defined x-value of all linguistic terms.
     *
     * @return the minimum x-value
     */
    public synchronized float getMinDefinedX() {
        float minX = Float.NaN;
        float minTermX;
        FuzzySet fs;
        Enumeration elements = terms.elements();

        while (elements.hasMoreElements()) {
            fs = (FuzzySet) elements.nextElement();

            if (fs.isDefined()) {
                minTermX = fs.getMinDefinedX();

                if (minX == minX) { // that means not Float.NaN

                    if (minTermX < minX)
                        minX = minTermX;
                } else
                    minX = minTermX;
            }
        }

        return minX;
    }

    /**
     * Returns the name of this linguistic variable.
     *
     * @return the <code>name</code> property
     */
    public synchronized String getName() {
        return name;
    }

    /**
     * Returns an enumeration of all names of linguistic terms
     *
     * @return an enumeration of names
     */
    public synchronized Enumeration getNames() {
        return terms.keys();
    }

    /**
     * Checks whether at least one membership function of all linguistic terms
     * is defined.
     *
     * @return <code>true</code>, if linguistic variable is defined,
     *         <code>false</code> otherwise
     */
    public synchronized boolean isDefined() {
        Enumeration elements = terms.elements();

        while (elements.hasMoreElements()) {
            if (((FuzzySet) elements.nextElement()).isDefined())
                return true;
        }

        return false;
    }

    /**
     * Checks whether linguistic variable contains any terms.
     *
     * @return <code>true</code> if linguistic variable has not got any terms,
     *         <code>false</code> otherwise.
     */
    public synchronized boolean isEmpty() {
        return terms.isEmpty();
    }

    /**
     * Removes the linguistic term with the <code>name</code>
     *
     * @param name
     *            the name of the removing term
     * @return fuzzy set which has described the removed linguistic term
     */
    public synchronized FuzzySet remove(String name) {
        Object retfset = terms.remove(name);

        if (retfset != null)
            return (FuzzySet) retfset;
        else

            return null;
    }

    /**
     * Defines a new linguistic term consisting of a unique name and a fuzzy
     * set.
     *
     * @param name
     *            the name of the linguistic term
     * @param fs
     *            the fuzzy set describing the term
     * @return fuzzy set which belongs to an existing term with this name
     * @exception NullPointerException
     *                if <code>name</code> or <code>fs</code> is
     *                <code>null</code>
     */
    public synchronized FuzzySet set(String name, FuzzySet fs) throws NullPointerException{
        if ((name != null) && (fs != null)) {
            Object retfs = terms.put(name, fs);

            if (retfs == null)
                return null;
            else

                return (FuzzySet) retfs;
        } else
            throw new NullPointerException();
    }

    /**
     * Returns the number of linguistic terms.
     *
     * @return the number of linguistic terms
     */
    public synchronized int size() {
        return terms.size();
    }

    /**
     * Creates and returns a copy of this linguistic variable.
     *
     * @return a copy of this linguistic variable
     */
    public Object clone() {
        try {
            LinguisticVariable newObj = (LinguisticVariable) super.clone();
            // Duplicate terms physically
            newObj.terms = new java.util.Hashtable();

            Enumeration elements = terms.keys();

            while (elements.hasMoreElements()) {
                String aName = (String) elements.nextElement();
                newObj.terms.put(aName, ((FuzzySet) terms.get(aName)).clone());
            }

            return newObj;
        } catch (java.lang.CloneNotSupportedException e) {
            // It is impossible
            throw new InternalError(e.toString());
        }
    }

    /**
     * Indicates whether some other object is "equal to" this linguistic
     * variable
     *
     * @param obj
     *            the reference object with which to compare
     * @return <code>true</code> if this linguistic variable is the same as
     *         the <code>obj</code> argument, <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof LinguisticVariable)) {
            // Compare name and number of terms
            if ((!this.name.equals(((LinguisticVariable) obj).name)) ||
                    (this.terms.size() != ((LinguisticVariable) obj).terms.size()))
                return false;

            // Compare linuistic terms
            Enumeration elementsObj = ((LinguisticVariable) obj).terms.keys();

            while (elementsObj.hasMoreElements()) {
                Object key = elementsObj.nextElement();

                if (!this.terms.containsKey(key))
                    return false;

                if (!this.terms.get(key)
                                   .equals(((LinguisticVariable) obj).terms.get(
                                key)))
                    return false;
            }

            return true;
        }

        return false;
    }

    /**
     * Returns a textual representation of the linguistic variable
     *
     * @return a string representation of the linguistic variable
     */
    public String toString() {
        return FuzzyResourceManager.getString(this, 
                    "LINGUISTIC_VARIABLE",new String[] {name, terms.toString()});
    }
}
