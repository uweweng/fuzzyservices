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
public abstract class AbstractRulePart implements Cloneable {

    /** The linguistic variable is the basis for the definition of a rule part. */
    protected String lingVarName;
    /** The name of a linguistic name which has to fulfill. */
    protected String lingTermName;

    /** Constructor which can not be used. */
    private AbstractRulePart() {
    // Not allowed
    }

    /**
     * Constructs a new rule part consisting of a linguistic variable and a name of its linguistic terms.
     * @param lvName the linguistic variable name
     * @param lingTermName the name of a linguistic term
     */
    public AbstractRulePart(final String lvName, final String lingTermName) {
        this.lingVarName = lvName;
        this.lingTermName = lingTermName;
    }

    /**
     * Defines the linguistic variable name.
     * @param lv the new linguistic variable name
     */
    public synchronized void setLinguisticVariableName(final String lv) {
        lingVarName = lv;
    }

    /**
     * Returns the name of the linguistic variable
     * @return the name of the linguistic variable
     */
    public String getLinguisticVariableName() {
        return lingVarName;
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
     */
    public synchronized void setLinguisticTermName(final String name) {
        lingTermName = name;
    }
  
    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof AbstractRulePart)) {
            return ((lingVarName.equals(((AbstractRulePart) obj).lingVarName)) &&
                    (lingTermName.equals(((AbstractRulePart) obj).lingTermName)));
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.lingVarName != null ? this.lingVarName.hashCode() : 0);
        hash = 53 * hash + (this.lingTermName != null ? this.lingTermName.hashCode() : 0);
        return hash;
    }

    @Override
    public Object clone() {
        try {
            AbstractRulePart newObj = (AbstractRulePart) super.clone();

            return newObj;
        } catch (java.lang.CloneNotSupportedException e) {
            // kann nicht auftreten
            throw new InternalError(e.toString());
        }
    }

    @Override
    public String toString() {
        return lingVarName + " = " + lingTermName;
    }
}
