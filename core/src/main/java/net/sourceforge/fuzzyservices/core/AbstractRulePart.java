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


/**
 * The <strong>abstract</strong> class <code>AbstractRulePart</code> describes one of two parts of
 * a if-then-clause. Every part consists of a linguistic variable and one of its terms
 * at least. The if-clause is called antecedent, too. The then-clause is the consequent of approximate reasoning.
 *
 * @see Antecedent
 * @see Consequent
 * @see Rule
 * @see LinguisticVariable
 *
 * @since 1.0
 * @author Uwe Weng
 */
abstract class AbstractRulePart implements java.lang.Cloneable, java.io.Serializable {
    /** The linguistic variable is the basis for the definition of a rule part. */
    protected LinguisticVariable lingVar;

    /** The name of a linguistic name which has to fulfill. */
    protected String lingTermName;

    /** Constructor which can not be used. */
    private AbstractRulePart() {
        // Not allowed
    }

    /**
     * Constructs a new rule part consisting of a linguistic variable and a name of its linguistic terms.
     * @param lv the linguistic variable
     * @param lingTermName the name of a linguistic term
     * @exception NullPointerException if one of the parameter is <code>null</code> at least
     * @exception IllegalArgumentException if the linguistic variable does not have a linguistic term with this name
     */
    public AbstractRulePart(final LinguisticVariable lv, final String lingTermName) throws NullPointerException, IllegalArgumentException {
        if (lv.contains(lingTermName)) {
            this.lingVar = lv; // Referenz uebergeben
            this.lingTermName = lingTermName;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_UNKNOWN_LINGUISTIC_TERM",
                    new Object[] { lv.getName(), lingTermName }));
    }

    /**
     * Returns the linguistic variable.
     * @return the linguistic variable
     */
    public LinguisticVariable getLinguisticVariable() {
        return lingVar;
    }

    /**
     * Defines the linguistic variable.
     * @param lv the new linguistic variable
     * @exception NullPointerException if <code>lv</code> is <code>null</code>
     */
    protected synchronized void setLinguisticVariable(
        final LinguisticVariable lv) throws NullPointerException{
        if (lv != null)
            lingVar = lv; // Referenz uebergeben
        else
            throw new NullPointerException();
    }

    /**
     * Returns the name of the linguistic variable
     * @return the name of the linguistic variable
     */
    public String getLinguisticVariableName() {
        return lingVar.getName();
    }

    /**
     * Returns the name of the linguistic term which belongs to the linguistic variable
     * @return the name of a linguistic term which is part of the linguistic variable
     */
    public String getLinguisticTermName() {
        return lingTermName;
    }

    /**
     * Defines the name of a linguistic term which belongs to the linguistic variable
     * @param name the name of a linguistic term
     * @exception NullPointerException if <code>name</code> is <code>null</code>
     * @exception IllegalArgumentException if the linguistic variable does not have a linguistic term with this name
     */
    public synchronized void setLinguisticTermName(final String name) throws NullPointerException, IllegalArgumentException{
        if (lingVar.contains(name))
            lingTermName = name;
        else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_UNKNOWN_LINGUISTIC_TERM",
                    new Object[] { lingVar.getName(), name }));
    }

    /**
     * Indicates whether some other object is "equal to" this rule part
     * @param obj the reference object with which to compare
     * @return <code>true</code> if this rule part is the same as the <code>obj</code> argument, <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof AbstractRulePart)) {
            return ((lingVar.equals(((AbstractRulePart) obj).lingVar)) &&
            (lingTermName.equals(((AbstractRulePart) obj).lingTermName)));
        }

        return false;
    }

    /**
     * Creates and returns a copy of this rule part.
     * @return a copy of this rule part
     */
    public Object clone() {
        try {
            AbstractRulePart newObj = (AbstractRulePart) super.clone();

            return newObj;
        } catch (java.lang.CloneNotSupportedException e) {
            // kann nicht auftreten
            throw new InternalError(e.toString());
        }
    }

    /**
     * Returns a textual representation of the rule part.
     * @return a string representation of the rule part
     */
    public String toString() {
        return lingVar.getName() + " = " + lingTermName;
    }
}
