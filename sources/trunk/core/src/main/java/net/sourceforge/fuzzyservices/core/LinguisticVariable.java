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
package net.sourceforge.fuzzyservices.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
    protected String name = "";
    /**
     * The linguistic terms are stored in a hashtable with name as key and fuzzy
     * set as value.
     */
    protected Map<String, FuzzySet> terms = new HashMap<String, FuzzySet>();

    /**
     * Default constructor. Name is unknown.
     */
    public LinguisticVariable() {
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
    public LinguisticVariable(final String newName) throws NullPointerException, IllegalArgumentException {
        if (newName.length() > 0) {
            this.name = newName;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this,
                    "EXCEPTION_LINGUISTIC_VARIABLE_INVALID_NAME", new String[]{newName}));
        }
    }

    /** Removes all linguistic terms from this linguistic variable. */
    public void clear() {
        synchronized (this) {
            terms.clear();
        }
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
        return (aName != null) ? terms.containsKey(aName) : false;
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

            if (fset != null) {
                return (FuzzySet) fset;
            }
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

        for (Iterator<FuzzySet> it = terms.values().iterator(); it.hasNext();) {
            fs = it.next();
            if (fs.isDefined() == true) {
                maxTermX = fs.getMaxDefinedX();

                if (maxX == maxX) { // that means not Float.NaN

                    if (maxTermX > maxX) {
                        maxX = maxTermX;
                    }
                } else {
                    maxX = maxTermX;
                }
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
        for (Iterator<FuzzySet> it = terms.values().iterator(); it.hasNext();) {
            fs = it.next();

            if (fs.isDefined()) {
                minTermX = fs.getMinDefinedX();

                if (minX == minX) { // that means not Float.NaN

                    if (minTermX < minX) {
                        minX = minTermX;
                    }
                } else {
                    minX = minTermX;
                }
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
     * Sets the name of this linguistic variable.
     * @param newName The new value for the property.
     * @see #getName
     */
    public synchronized void setName(String newName) {
        this.name = newName;
    }

    /**
     * Returns an iterator of all names of linguistic terms
     *
     * @return an iterator of names
     */
    public synchronized Iterator<String> getNames() {
        return terms.keySet().iterator();
    }

    /**
     * Checks whether at least one membership function of all linguistic terms
     * is defined.
     *
     * @return <code>true</code>, if linguistic variable is defined,
     *         <code>false</code> otherwise
     */
    public synchronized boolean isDefined() {
        for (Iterator<FuzzySet> it = terms.values().iterator(); it.hasNext();) {
            FuzzySet fs = it.next();
            if (fs.isDefined() == true) {
                return true;
            }
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

        if (retfset != null) {
            return (FuzzySet) retfset;
        } else {
            return null;
        }
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
    public synchronized FuzzySet set(String name, FuzzySet fs) throws NullPointerException {
        if ((name != null) && (fs != null)) {
            Object retfs = terms.put(name, fs);

            if (retfs == null) {
                return null;
            } else {
                return (FuzzySet) retfs;
            }
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Returns the number of linguistic terms.
     *
     * @return the number of linguistic terms
     */
    public synchronized int size() {
        return terms.size();
    }

    @Override
    public Object clone() {
        try {
            LinguisticVariable newObj = (LinguisticVariable) super.clone();
            // Duplicate terms physically
            newObj.terms = new HashMap<String, FuzzySet>();

            for (Iterator<String> it = terms.keySet().iterator(); it.hasNext();) {
                String termName = it.next();
                newObj.terms.put(termName, (FuzzySet) terms.get(termName).clone());

            }

            return newObj;
        } catch (java.lang.CloneNotSupportedException e) {
            // It is impossible
            throw new InternalError(e.toString());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof LinguisticVariable)) {
            
            LinguisticVariable lingVar = (LinguisticVariable) obj;
            
            // Compare name and number of terms
            if ((!this.name.equals(lingVar.name)) ||
                    (this.terms.size() != ((LinguisticVariable) obj).terms.size())) {
                return false;
            }

            // Compare linguistic terms
            for (Iterator<String> it = terms.keySet().iterator(); it.hasNext();) {
                String termName = it.next();

                if (!lingVar.terms.containsKey(termName)) {
                    return false;
                }

                if (!lingVar.terms.get(termName).equals(this.terms.get(termName))) {
                    return false;
                }
            }

            for (Iterator<String> it = lingVar.terms.keySet().iterator(); it.hasNext();) {
                String termName = it.next();
                if (!this.terms.containsKey(termName)) {
                    return false;
                }

                if (!this.terms.get(termName).equals(lingVar.terms.get(termName))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 47 * hash + (this.terms != null ? this.terms.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return FuzzyResourceManager.getString(this,
                "LINGUISTIC_VARIABLE", new String[]{name, terms.toString()});
    }
}
