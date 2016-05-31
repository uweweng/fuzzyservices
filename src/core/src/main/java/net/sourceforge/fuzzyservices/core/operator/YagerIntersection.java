/*
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
 * <tt>c = 1-min(1,((1-a)^p+(1-b)^p)^(1/p)), with p>=1.0</tt>.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class YagerIntersection
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
    public YagerIntersection() {
        super();
    }

    /**
     * Constructor with the first parameter for the operator
     * @param param the first valid parameter
     * @exception IllegalArgumentException if <code>param</code> is an invalid
     * parameter
     */
    public YagerIntersection(final float param)
            throws IllegalArgumentException {
        if (isValidParameter(param)) {
            this.parameter = param;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this,
                    "EXCEPTION_OPERATOR_YAGER_INTERSECTION_INVALID_PARAMETER"));
        }
    }

    @Override
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

    @Override
    public final boolean isValidTNorm() {
        return true;
    }

    @Override
    public final boolean isValidSNorm() {
        return false;
    }

    @Override
    public final boolean isValidParameter(final float param) {
        return ((param >= 1.0f) ? true : false);
    }

    @Override
    public final float compute(final float a, final float b) {
        return (1.0f -
                Math.min(1.0f,
                (float) Math.pow(((float) (Math.pow((1.0f - a), parameter)) +
                (float) (Math.pow((1.0f - b), parameter))), (1.0f / parameter))));
    }

    @Override
    public String toString(final boolean withParameter) {
        String str = FuzzyResourceManager.getString(this, "OPERATOR_YAGER_INTERSECTION");

        if (withParameter) {
            str = FuzzyResourceManager.getString(this,
                    "OPERATOR_YAGER_INTERSECTION_WITH_PARAMETER",
                    new Object[]{Float.toString(parameter)});
        }

        return str;
    }

    @Override
    public final float getDefaultParameter() {
        return 1.0f;
    }

    @Override
    public String getName() {
        return FuzzyResourceManager.getString(this, "OPERATOR_YAGER_INTERSECTION");
    }
}
