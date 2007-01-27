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

import java.io.Serializable;
import java.util.Enumeration;


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
 * @see com.jfuzzy.core.operator.AbstractComplexOperator
 * @see com.jfuzzy.core.operator.AbstractDrasticOperator
 * @see com.jfuzzy.core.operator.AbstractLinearOperator
 * @see com.jfuzzy.core.operator.AbstractParameteredOperator
 * @see com.jfuzzy.core.operator.AbstractComplexParameteredOperator
 * @see com.jfuzzy.core.operator.AbstractMultiplyingParameteredOperator
 *
 * @since 1.0
 * @author Uwe Weng
 */
public abstract class AbstractOperator implements Serializable {
    /**
     * Combines two fuzzy sets to a new fuzzy set.
     * @param fs1 The first operand
     * @param fs2 The second operand
     * @return the result of this operation. It is a new fuzzy set.
     */
    public abstract FuzzySet combine(final FuzzySet fs1, final FuzzySet fs2);

    /**
     * Combines two discrete fuzzy sets to a new discrete fuzzy set.
     * @param dfs1 The first operand
     * @param dfs2 The second operand
     * @return the result of this operation. It is a new discrete fuzzy set.
     * @exception IllegalArgumentException if the operands contain objects with
     *                                     different types
     */
    public final DiscreteFuzzySet combine(final DiscreteFuzzySet dfs1,
            final DiscreteFuzzySet dfs2) throws IllegalArgumentException {
        if ((dfs1 != null) && (dfs2 != null)) {
            if (dfs1.getClass().equals(dfs2.getClass())) {
                DiscreteFuzzySet dfs =
                        new DiscreteFuzzySet(dfs1.getClassOfObjects());

                // Iterating both discrete fuzzy sets and combinding the
                // elements.
                Object obj;
                Enumeration elements = dfs1.elements();

                while (elements.hasMoreElements()) {
                    obj = elements.nextElement();
                    dfs.put(obj,
                            compute(dfs1.getDegreeOfMembership(obj),
                            dfs2.getDegreeOfMembership(obj)));
                }

                elements = dfs2.elements();

                while (elements.hasMoreElements()) {
                    obj = elements.nextElement();
                    dfs.put(obj,
                            compute(dfs1.getDegreeOfMembership(obj),
                            dfs2.getDegreeOfMembership(obj)));
                }

                return dfs;
            } else
                throw new IllegalArgumentException(
                        FuzzyResourceManager.getString(this,
                        "EXCEPTION_OPERATOR_INVALID_DISCRETE_FUZZY_SET_TYPE"));
        }

        return null;
    }

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
     * Indicates whether an operator needs a parameter for calculations.
     * @return <code>true</code>, if the operator needs a parameter,
     * <code>false</code> otherwise
     * @see com.jfuzzy.core.operator.AbstractParameteredOperator
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

    /**
     * Returns a textual representation of the operator.
     * @return a string representation of the operator
     */
    public abstract String toString();

    /**
     * Indicates whether some other object is "equal to" this operator.
     * @param obj the reference object with which to compare
     * @return <code>true</code> if this operator is the same as the
     * <code>obj</code> argument, <code>false</code> otherwise.
     */
    public abstract boolean equals(Object obj);
}

