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
package net.sourceforge.fuzzyservices.core.operator;

import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;
import net.sourceforge.fuzzyservices.core.FuzzySet;
import java.io.Serializable;


/**
 * This class represents a fuzzy operator with the calculation rule
 * <tt>c = 1-min(1,((1-a)^p+(1-b)^p)^(1/p)), with p>=1.0</tt>.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class YagerIntersection extends AbstractComplexParameteredOperator
        implements Serializable {
    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor. The first parameter is the default parameter.
     * @see #getDefaultParameter
     */
    public YagerIntersection() {
        super();
    }

    /**
     * Constructor with the first parameter for the operator
     * @param param the first valid parameter
     * @exception IllegalArgumentException if <code>param</code> is an invalid
     * parameter
     */
    public YagerIntersection(final float param) throws IllegalArgumentException {
        if (isValidParameter(param))
            this.parameter = param;
        else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_OPERATOR_YAGER_INTERSECTION_INVALID_PARAMETER"));
    }

    /**
     * Combines two fuzzy sets to a new fuzzy set.
     * @param fs1 The first operand
     * @param fs2 The second operand
     * @return the result of this operation. It is a new fuzzy set.
     */
    public FuzzySet combine(final FuzzySet fs1, final FuzzySet fs2) {
        // Special cases
        if (parameter == 1.0f) {
            BoundedDifference bd = new BoundedDifference();

            return bd.combine(fs1, fs2);
        }

        if (parameter == Float.MAX_VALUE) { // nach Tilli, S. 52

            Min min = new Min();

            return min.combine(fs1, fs2);
        }

        return super.combine(fs1, fs2);
    }

    /**
     * Indicates whether an operator fullfils the t-norm.
     * @return <code>true</code> because this operator fullfils the t-norm.
     */
    public boolean isValidTNorm() {
        return true;
    }

    /**
     * Indicates whether an operator fullfils the s-norm.
     * @return <code>false</code> because this operator does not fullfil the
     * s-norm.
     */
    public boolean isValidSNorm() {
        return false;
    }

    /**
     * Indicates whether the argument is a valid parameter for this operator.
     * @param param the value to be checked
     * @return <code>true</code> if argument is a valid parameter,
     * <code>false>/code> otherwise.
     */
    public boolean isValidParameter(final float param) {
        return ((param >= 1.0f) ? true : false);
    }

    /**
     * Computes the new degree of membership using the calculation rule
     * <tt>c = 1-min(1,((1-a)^p+(1-b)^p)^(1/p)), with p>=1.0</tt>.
     * @param a a degree of membership
     * @param b a degree of membership
     * @return the calculated value
     */
    public float compute(final float a, final float b) {
        return (1.0f -
                Math.min(1.0f,
                (float) Math.pow(((float) (Math.pow((1.0f - a), parameter)) +
                (float) (Math.pow((1.0f - b), parameter))), (1.0f / parameter))));
    }

    /**
     * Returns a textual representation of the operator
     * @param withParameter Decides whether the parameter is part of the
     * representation
     * @return a string representation of the operator
     */
    public String toString(final boolean withParameter) {
        String str = FuzzyResourceManager.getString(this,
                "OPERATOR_YAGER_INTERSECTION");
        if (withParameter) {
            str = FuzzyResourceManager.getString(this,
                    "OPERATOR_YAGER_INTERSECTION_WITH_PARAMETER",
                    new Object[] { Float.toString(parameter) });
        }
        return str;
    }

    /**
     * Indicates whether some other object is "equal to" this operator
     * @param obj the reference object with which to compare
     * @return <code>true</code> if this operator is the same as the
     * <code>obj</code> argument, <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (((obj != null) && (obj instanceof YagerIntersection)) &&
                (this.parameter == ((AbstractParameteredOperator) obj).parameter)){
            isEqual = true;
        }
        return isEqual;
    }

    /**
     * Returns the default parameter. In this case, it is <code>1.0</code>.
     * @return The default parameter for this operator.
     */
    public float getDefaultParameter() {
        return 1.0f;
    }
}
