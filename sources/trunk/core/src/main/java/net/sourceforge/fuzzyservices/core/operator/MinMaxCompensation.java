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

import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;
import net.sourceforge.fuzzyservices.core.FuzzySet;

import java.io.Serializable;

/**
 * This class represents a fuzzy operator with the calculation rule
 * <tt>c = min(a,b)^(1-p) * max(a,b)^p, with 0.0<=p<=1.0</tt>.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class MinMaxCompensation
        extends AbstractComplexParameteredOperator
        implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor. The first parameter is the default parameter.
     * @see #getDefaultParameter
     */
    public MinMaxCompensation() {
        super();
    }

    /**
     * Constructor with the first parameter for the operator
     * @param param the first valid parameter
     * @exception IllegalArgumentException if <code>param</code> is an invalid
     * parameter
     */
    public MinMaxCompensation(final float param)
            throws IllegalArgumentException {
        if (isValidParameter(param)) {
            this.parameter = param;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this,
                    "EXCEPTION_OPERATOR_MIN_MAX_COMPENSATION_INVALID_PARAMETER"));
        }
    }

    @Override
    public FuzzySet combine(final FuzzySet fs1, final FuzzySet fs2) {
        // Special cases
        if (parameter == 0.0f) {
            Min min = new Min();

            return min.combine(fs1, fs2);
        }

        if (parameter == 1.0f) {
            Max max = new Max();

            return max.combine(fs1, fs2);
        }

        return super.combine(fs1, fs2);
    }

    @Override
    public final boolean isValidTNorm() {
        return ((parameter < (1.0 / 2.0)) ? true : false);
    }

    @Override
    public final boolean isValidSNorm() {
        return ((parameter > (1.0 / 2.0)) ? true : false);
    }

    @Override
    public final boolean isValidParameter(final float param) {
        return (((param >= 0.0f) && (param <= 1.0f)) ? true : false);
    }

    @Override
    public final float compute(final float a, final float b) {
        return (float) (Math.pow(Math.min(a, b),
                (1 - parameter)) * Math.pow(Math.max(a, b),
                parameter));
    }

    @Override
    public String toString(final boolean withParameter) {
        String str = FuzzyResourceManager.getString(this, "OPERATOR_MIN_MAX_COMPENSATION");

        if (withParameter) {
            str = FuzzyResourceManager.getString(this,
                    "OPERATOR_MIN_MAX_COMPENSATION_WITH_PARAMETER",
                    new Object[]{Float.toString(parameter)});
        }

        return str;
    }

    @Override
    public final float getDefaultParameter() {
        return 0.0f;
    }

    @Override
    public String getName() {
        return FuzzyResourceManager.getString(this, "OPERATOR_MIN_MAX_COMPENSATION");
    }
}
