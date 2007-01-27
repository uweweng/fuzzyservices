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
package net.sourceforge.fuzzyservices.core.operator;

import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;
import net.sourceforge.fuzzyservices.core.FuzzySet;
import java.io.Serializable;


/**
 * This class represents a fuzzy operator with the calculation rule
 * <tt>c = min(1,(a^p + b^p)^(1/p)), with p>=1.0</tt>.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class YagerUnion extends AbstractComplexParameteredOperator
        implements Serializable {
    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor. The first parameter is the default parameter.
     * @see #getDefaultParameter
     */
    public YagerUnion() {
        super();
    }
    
    /**
     * Constructor with the first parameter for the operator
     * @param param the first valid parameter
     * @exception IllegalArgumentException if <code>param</code> is an invalid
     * parameter
     */
    public YagerUnion(final float param) throws IllegalArgumentException {
        if (isValidParameter(param)) {
            this.parameter = param;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_OPERATOR_YAGER_UNION_INVALID_PARAMETER"));
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
            BoundedSum bs = new BoundedSum();
            
            return bs.combine(fs1, fs2);
        }
        
        if (parameter == Float.MAX_VALUE) { // nach Tilli, S. 52
            
            Max max = new Max();
            
            return max.combine(fs1, fs2);
        }
        
        return super.combine(fs1, fs2);
    }
    
    /**
     * Indicates whether an operator fullfils the t-norm.
     * @return <code>false</code> because this operator does not fullfil the
     * t-norm.
     */
    public boolean isValidTNorm() {
        return false;
    }
    
    /**
     * Indicates whether an operator fullfils the s-norm.
     * @return <code>true</code> because this operator fullfils the s-norm.
     */
    public boolean isValidSNorm() {
        return true;
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
     * <tt>c = min(1,(a^p + b^p)^(1/p)), with p>=1.0</tt>.
     * @param a a degree of membership
     * @param b a degree of membership
     * @return the calculated value
     */
    public float compute(final float a, final float b) {
        return Math.min(1.0f,
                (float) Math.pow(((float) (Math.pow(a, parameter)) +
                (float) (Math.pow(b, parameter))), (1.0f / parameter)));
    }
    
    /**
     * Returns a textual representation of the operator
     * @param withParameter Decides whether the parameter is part of the
     * representation
     * @return a string representation of the operator
     */
    public String toString(final boolean withParameter) {
        String str = FuzzyResourceManager.getString(this, "OPERATOR_YAGER_UNION");
        if (withParameter) {
            str = FuzzyResourceManager.getString(this,
                    "OPERATOR_YAGER_UNION_WITH_PARAMETER",
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
        if (((obj != null) && (obj instanceof YagerUnion)) &&
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
