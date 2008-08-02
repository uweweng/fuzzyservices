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

/**
 * Fuzzy logic has got many more operators for combining values than classic
 * logic.The reason is that two degree of memberships to the same x value are be
 * able to aggregated in many ways.
 * Depending on the calculation rule a fuzzy operator fulfills the so-called
 * s-norm or t-norm. The membership of one of the two categories is important
 * for inference. Sometimes the membership depends on a parameter (parametered
 * operator).<p>
 * The <strong>abstract</strong> class <code>AbstractOperator</code> is the
 * bases for defining fuzzy operators. It contains abstract methods because the
 * computation rule and attributes are different between operators.
 *
 * @see net.sourceforge.fuzzyservices.core.operator.AbstractComplexOperator
 * @see net.sourceforge.fuzzyservices.core.operator.AbstractDrasticOperator
 * @see net.sourceforge.fuzzyservices.core.operator.AbstractLinearOperator
 * @see net.sourceforge.fuzzyservices.core.operator.AbstractParameteredOperator
 * @see net.sourceforge.fuzzyservices.core.operator.AbstractComplexParameteredOperator
 * @see net.sourceforge.fuzzyservices.core.operator.AbstractMultiplyingParameteredOperator
 *
 * @version 1.0
 * @author Uwe Weng
 */
public abstract class AbstractOperator
        implements Serializable {

    /**
     * Combines two fuzzy sets to a new fuzzy set.
     * @param fs1 The first operand
     * @param fs2 The second operand
     * @return the result of this operation. It is a new fuzzy set.
     */
    public abstract FuzzySet combine(final FuzzySet fs1, final FuzzySet fs2);

    /**
     * Indicates whether an operator fullfils the t-norm.
     * @return <code>true</code> if the operator fullfils the t-norm,
     * <code>false</code> otherwise.
     */
    public abstract boolean isValidTNorm();

    /**
     * Indicates whether an operator fullfils the s-norm.
     * @return <code>true</code> if the operator fullfils the s-norm,
     * <code>false</code> otherwise.
     */
    public abstract boolean isValidSNorm();

    /**
     * Returns the name property.
     * @return name of operator
     */
    public abstract String getName();

    /**
     * Indicates whether an operator needs a parameter for calculations.
     * @return <code>true</code>, if the operator needs a parameter,
     * <code>false</code> otherwise
     * @see net.sourceforge.fuzzyservices.core.operator.AbstractParameteredOperator
     */
    public boolean requiresParameter() {
        return false;
    }

    /**
     * Checks whether an operator needs a parameter for calculation.
     * @param op the operator which has to be checked
     * @return <code>true</code>, if the operator <code>op</code> needs a
     * parameter, <code>false</code> otherwise
     */
    public static boolean requiresParameter(final AbstractOperator op) {
        return op.requiresParameter();
    }

    /**
     * Computes the new degree of membership using a operator specific
     * calculation rule.
     * @param a a degree of membership
     * @param b a degree of membership
     * @return the calculated value
     */
    public abstract float compute(final float a, final float b);
}
