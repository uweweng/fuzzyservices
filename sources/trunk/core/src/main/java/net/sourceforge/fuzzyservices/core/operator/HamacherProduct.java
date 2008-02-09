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
 * <tt>c = (a*b)/(a+b-a*b)</tt>.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class HamacherProduct extends AbstractMultiplyingOperator
        implements Serializable {

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    @Override
    public boolean isValidTNorm() {
        return true;
    }

    @Override
    public boolean isValidSNorm() {
        return false;
    }

    @Override
    public float compute(final float a, final float b) {
        return ((a * b) / ((a + b) - (a * b)));
    }

    @Override
    public String toString() {
        return FuzzyResourceManager.getString(this, "OPERATOR_HAMACHER_PRODUCT");
    }

    @Override
    public String getName() {
        return FuzzyResourceManager.getString(this, "OPERATOR_HAMACHER_PRODUCT");
    }
}
