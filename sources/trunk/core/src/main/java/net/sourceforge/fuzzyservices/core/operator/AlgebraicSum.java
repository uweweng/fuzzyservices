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
import java.io.Serializable;


/**
 * This class represents a fuzzy operator with the calculation rule
 * <tt>c = a+b-a*b</tt>.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class AlgebraicSum extends AbstractComplexOperator
        implements Serializable {
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
     * <tt>c = a+b-a*b</tt>.
     * @param a a degree of membership
     * @param b a degree of membership
     * @return the calculated value
     */
    public float compute(final float a, final float b) {
        return ((a + b) - (a * b));
    }

    /**
     * Returns a textual representation of the operator
     * @return a string representation of the operator
     */
    public String toString() {
        return FuzzyResourceManager.getString(this, "OPERATOR_ALGEBRAIC_SUM");
    }

    /**
     * Indicates whether some other object is "equal to" this operator
     * @param obj the reference object with which to compare
     * @return <code>true</code> if this operator is the same as the <code>obj
     * </code> argument, <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if ((obj != null) && (obj instanceof AlgebraicSum)) {
            isEqual = true;
        }
        return isEqual;
    }
}
