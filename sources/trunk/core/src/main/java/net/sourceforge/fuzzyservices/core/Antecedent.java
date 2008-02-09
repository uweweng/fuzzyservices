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

import java.io.Serializable;
import net.sourceforge.fuzzyservices.core.operator.Min;

/**
 * The class <code>Antecedent</code> represents the if-clause of a rule. In
 * opposite to the then-class (consequent) an antecedent requires a fuzzy
 * operator additionally, in order to measure the degree of compatibility
 * between fact and premise described as antecedent.
 *
 * @see Consequent
 * @see Rule
 * @see LinguisticVariable
 * @see Fact
 *
 * @since 1.0
 * @author Uwe Weng
 */
public final class Antecedent extends AbstractRulePart implements Serializable {

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;
    /**
     * By default, a min-operator is used to determine the degree of
     * compatibility.
     *
     * @see Min
     */
    private static AbstractOperator defaultCompatibilityOperator = new Min(); // t-Norm
    /**
     * This operator measures the compatibility between premise and fact. At
     * initialization time it is the default operator. It has always to fulfill
     * the t-norm.
     */
    private AbstractOperator compatibilityOp = defaultCompatibilityOperator; // compatibility-AbstractOperator
    // (t-Norm)
    /**
     * Constructs a new premise of a rule.
     *
     * @param lvName
     *            name of a linguistic variable for describing an antecedent of a
     *            if-then-clause
     * @param lingTermName
     *            the name of a linguistic term which is part of the linguistic
     *            variable representing the premise.
     */
    public Antecedent(final String lvName, final String lingTermName) {
        this(lvName, lingTermName, null);
    }

    /**
     * Constructs a new premise of a rule consisting of a linguistic variable, a
     * term, and a fuzzy operator to determine the compatibility. 
     * If <code>compOp</code> is <code>null</code> the default operator is used instead.
     *
     * @param lvName
     *            name of linguistic variable for describing an antecedent of a
     *            if-then-clause
     * @param lingTermName
     *            the name of a linguistic term which is part of the linguistic
     *            variable representing the premise.
     * @param compOp
     *            the fuzzy operator which is used to measure the compatibility
     *            to a fact
     * @exception IllegalArgumentException
     *                if the fuzzy operator does not fulfill the t-norm
     */
    public Antecedent(final String lvName, final String lingTermName,
            final AbstractOperator compOp) throws IllegalArgumentException {
        super(lvName, lingTermName);
        if (compOp != null) {
            if (compOp.isValidTNorm()) {
                this.compatibilityOp = compOp;
            } else {
                throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_T_NORM_OPERATOR"));
            }
        } else {
            this.compatibilityOp = defaultCompatibilityOperator;
        }
    }

    /**
     * Returns the compatibility operator.
     *
     * @return the fuzzy operator to calculate the degree of compatibility.
     */
    public AbstractOperator getCompatibilityOperator() {
        return compatibilityOp;
    }

    /**
     * Returns the default compatibility operator.
     *
     * @return the default compatibility operator
     */
    public static AbstractOperator getDefaultCompatibilityOperator() {
        return defaultCompatibilityOperator;
    }

    /**
     * Defines the fuzzy operator for measuring the compatibility to a fact
     * while approximate reasoning.
     *
     * @param compOp
     *            the new operator which has to fulfill the t-norm.
     * @exception NullPointerException
     *                if parameter is <code>null</code>
     * @exception IllegalArgumentException
     *                if fuzzy operator does not fulfill the t-norm
     */
    public synchronized void setCompatibilityOperator(final AbstractOperator compOp) throws NullPointerException, IllegalArgumentException {
        if (compOp.isValidTNorm()) {
            this.compatibilityOp = compOp;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_T_NORM_OPERATOR"));
        }
    }

    /**
     * Defines a new default fuzzy operator to determine the compatibility
     * between this premise and a fact at reasoning time. It has to fulfill the
     * t-norm.
     *
     * @param compOp
     *            the new operator which is used by default
     * @exception NullPointerException
     *                if parameter is <code>null</code>
     * @exception IllegalArgumentException
     *                if fuzzy operator does not fulfill the t-norm
     */
    public static void setDefaultCompatibilityOperator(final AbstractOperator compOp) throws NullPointerException, IllegalArgumentException {
        if (compOp.isValidTNorm()) {
            defaultCompatibilityOperator = compOp;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(Antecedent.class, "EXCEPTION_INVALID_T_NORM_OPERATOR"));
        }
    }
}
