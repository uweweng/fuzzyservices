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

import net.sourceforge.fuzzyservices.core.operator.Max;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A rule base consists of rules which makes statements about relations of linguistic variables.
 * Additionally, a fuzzy operator for accumulation is required which has to fulfillthe s-norm.
 * Its aim is to aggregate the partial results of inferencing. Optionally, a rule base can have a name.
 *
 * @see Rule
 * @see AbstractOperator
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class RuleBase implements Cloneable, Serializable {

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;
    /**
     * By default, a MAX-AbstractOperator is used for accumulation.
     */
    private static AbstractOperator defaultAccumulationOperator = new Max(); // s-Norm
    /**
     * For accumulation an operator which fulfills the s-norm, is required.
     * Accumulation means the aggregation of partial results of inferencing.
     */
    private AbstractOperator accumulationOp = defaultAccumulationOperator; // accumulation-AbstractOperator (s-Norm)
    /**
     * All rules are stored in a vector.
     */
    private List<Rule> rules = new ArrayList<Rule>();
    /** A rule base can have a name. */
    protected String name = null;

    /** Default constructor which creates an empty rule base. */
    public RuleBase() {
    // Default constructor
    }

    /**
     * Constructs a rule base containing the first rule.
     * @param rule a rule
     */
    public RuleBase(final Rule rule) {
        if (rule != null) {
            rules.add(rule);
        }
    }

    /**
     * Adds a rule to the rule base.
     * @param rule the new rule of the rule base.
     */
    public synchronized void add(final Rule rule) {
        if (rule == null) {
            return;
        }

        if (!rules.contains(rule)) {
            rules.add(rule);
        }
    }

    /** Removes all rules from this rule base. */
    public synchronized void clear() {
        rules.clear();
    }

    /**
     * Checks, whether a certain rule is member of the rule base.
     * @param rule the rule which may be part of the rule base
     * @return <code>true</code> if the rule is member of the rule base, <code>false</code> otherwise
     */
    public synchronized boolean contains(final Rule rule) {
        if (rule != null) {
            return rules.contains(rule);
        }

        return false;
    }

    /**
     * Checks whether a linguistic variable with <code>name</code> is referenced in one of the rules within this rule base.
     * @param lingVarName the name of the linguistic variable for checking
     * @return <code>true</code> if at least one rule makes a statement about the linguistic variable
     */
    public synchronized boolean containsLinguisticVariable(
            final String lingVarName) {
        if (lingVarName != null) {
            for (Rule rule : rules) {
                if (rule.containsLinguisticVariable(lingVarName) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns all rules as iterator.
     * @return an iterator with all rules of this rule base
     */
    public synchronized Iterator<Rule> iterator() {
        return rules.iterator();
    }

    /**
     * Returns the accumulation operator
     * @return the operator for accumulation of partial results
     */
    public AbstractOperator getAccumulationOperator() {
        return accumulationOp;
    }

    /**
     * Returns the default accumulation operator
     * @return the operator for accumulation of partial results
     */
    public static AbstractOperator getDefaultAccumulationOperator() {
        return defaultAccumulationOperator;
    }

    /**
     * Returns the name of this rule base.
     * @return the <code>name</code> property
     * @see #setName
     */
    public synchronized String getName() {
        return name;
    }

    /**
     * Returns the rule a index <code>i</code> in rule base.
     * @param i the index
     * @return die rule at this position
     * @exception IndexOutOfBoundsException if index <code>i</code> does not exist in rule base
     */
    protected synchronized Rule getRule(final int i) throws IndexOutOfBoundsException {
        return rules.get(i);
    }

    /**
     * Checks whether rule base contains any rules.
     * @return <code>true</code> if rule base has not got any rules, <code>false</code> otherwise.
     */
    public synchronized boolean isEmpty() {
        return rules.isEmpty();
    }

    /**
     * Removes the <code>rule</code> from the rule base.
     * @param rule the rule to be deleted
     * @return <code>true</code> if the rule was removed successfully, <code>false</code> otherwise.
     */
    public synchronized boolean remove(final Rule rule) {
        if (rule != null) {
            return rules.remove(rule);
        }

        return false;
    }

    /**
     * Sets the fuzzy operator for accumulation. It has to fulfill the s-norm.
     * @param accOp the new accumulation operator
     * @exception NullPointerException if <code>accOp</code> is <code>null</code>
     * @exception IllegalArgumentException if the operator does not fulfill the s-norm
     */
    public synchronized void setAccumulationOperator(final AbstractOperator accOp) throws NullPointerException, IllegalArgumentException {
        if (accOp.isValidSNorm()) {
            accumulationOp = accOp;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_S_NORM_OPERATOR"));
        }
    }

    /**
     * Sets the default fuzzy operator for accumulation. It has to fulfill the s-norm.
     * @param accOp the new accumulation operator
     * @exception NullPointerException if <code>accOp</code> is <code>null</code>
     * @exception IllegalArgumentException if the operator does not fulfill the s-norm
     */
    public static synchronized void setDefaultAccumulationOperator(
            final AbstractOperator accOp) throws NullPointerException, IllegalArgumentException {
        if (accOp.isValidSNorm()) {
            defaultAccumulationOperator = accOp;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    RuleBase.class, "EXCEPTION_INVALID_S_NORM_OPERATOR"));
        }
    }

    /**
     * Sets the name of this rule base.
     * @param newName The new value for the property.
     * @see #getName
     */
    public synchronized void setName(String newName) {
        this.name = newName;
    }

    /**
     * Defines a new rule at index <code>i</code>.
     * @param rule the new rule
     * @param i the index within the rule base
     * @exception ArrayIndexOutOfBoundsException if the index does not exist in rule base
     */
    protected synchronized void setRule(final Rule rule, final int i) throws ArrayIndexOutOfBoundsException {
        rules.set(i, rule);
    }

    /**
     * Returns the number of rules within the rule base.
     * @return the size of the rule base
     */
    public synchronized int size() {
        return rules.size();
    }

    @Override
    public Object clone() {
        try {
            RuleBase newObj = (RuleBase) super.clone();
            // Duplicate the rules
            newObj.rules = new ArrayList<Rule>(rules.size());

            for (Rule rule : rules) {
                newObj.rules.add((Rule) rule.clone());
            }
            return newObj;
        } catch (java.lang.CloneNotSupportedException e) {
            // it is impossible
            throw new InternalError(e.toString());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof RuleBase)) {
            // Compare only the operator and rules, not the name of the rule base
            if ((!accumulationOp.equals(((RuleBase) obj).accumulationOp)) ||
                    (this.rules.size() != ((RuleBase) obj).rules.size()) ||
                    (this.rules.equals(((RuleBase) obj).rules) == false)) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.accumulationOp != null ? this.accumulationOp.hashCode() : 0);
        hash = 29 * hash + (this.rules != null ? this.rules.hashCode() : 0);
        // hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return toString(false);
    }

    /**
     * Returns a textual representation of the rule base
     * @param withRules <code>true</code> if all rule definitions are also returned, <code>false</code> otherwise.
     * @return a string representation of the rule base
     * @see Rule#toString
     */
    public String toString(final boolean withRules) {
        if (withRules) {
            return rules.toString();
        } else {
            return FuzzyResourceManager.getString(this, "RULE_BASE_WITHOUT_RULES",
                    new Object[]{Integer.toString(rules.size())});
        }
    }
}
