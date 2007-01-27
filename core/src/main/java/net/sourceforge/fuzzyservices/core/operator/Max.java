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
import java.io.Serializable;


/**
 * This class represents a fuzzy operator with the calculation rule
 * <tt>c = max(a,b)</tt>.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class Max extends AbstractLinearOperator implements Serializable {
    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;
    
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
     * Computes the new degree of membership using the calculation rule
     * <tt>c = max(a,b)</tt>.
     * @param a a degree of membership
     * @param b a degree of membership
     * @return the calculated value
     */
    public float compute(final float a, final float b) {
        return (a >= b) ? a : b; // identisch mit Math.max(a, b);
    }
    
    /**
     * Returns a textual representation of the operator
     * @return a string representation of the operator
     */
    public String toString() {
        return FuzzyResourceManager.getString(this, "OPERATOR_MAX");
    }
    
    /**
     * Indicates whether some other object is "equal to" this operator
     * @param obj the reference object with which to compare
     * @return <code>true</code> if this operator is the same as the
     * <code>obj</code> argument, <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if ((obj != null) && (obj instanceof Max)) {
            isEqual = true;
        }
        return isEqual;
    }
}
