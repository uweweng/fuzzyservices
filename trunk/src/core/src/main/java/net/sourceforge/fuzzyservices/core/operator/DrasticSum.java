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

import java.io.Serializable;

/**
 * This class represents a fuzzy operator with the calculation rule <pre><tt>
 *     c = a, if b=0
 *     c = b, if a=0
 *     c = 1, otherwise</tt></pre>
 *
 * @see #compute(float, float)
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class DrasticSum
        extends AbstractDrasticOperator
        implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public final float getDefaultValue() {
        return 1.0f;
    }

    @Override
    public final float getConditionValue() {
        return 0.0f;
    }

    @Override
    public final boolean isValidTNorm() {
        return false;
    }

    @Override
    public final boolean isValidSNorm() {
        return true;
    }

    @Override
    public final float compute(final float a, final float b) {
        float calc = 1.0f;

        if (a == 0.0f) {
            calc = b;
        }

        if (b == 0.0f) {
            calc = a;
        }

        return calc;
    }

    @Override
    public String toString() {
        return FuzzyResourceManager.getString(this, "OPERATOR_DRASTIC_SUM");
    }

    @Override
    public String getName() {
        return FuzzyResourceManager.getString(this, "OPERATOR_DRASTIC_SUM");
    }
}
