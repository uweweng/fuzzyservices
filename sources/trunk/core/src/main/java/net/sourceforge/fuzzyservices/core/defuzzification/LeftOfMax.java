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
package net.sourceforge.fuzzyservices.core.defuzzification;

import net.sourceforge.fuzzyservices.core.AbstractDefuzzificator;
import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;
import net.sourceforge.fuzzyservices.core.MembershipFunction;
import java.io.Serializable;
import java.util.Iterator;


/**
 * The class <code>LeftOfMax</code> represents a defuzzification method. In
 * this case, the minimum defined x value with the highest degree of membership
 * is the crisp value.
 *
 * @author Uwe Weng
 * @since 1.0
 */
public class LeftOfMax extends AbstractDefuzzificator implements Serializable {
    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    @Override
    public float defuzzify(MembershipFunction membershipFunction) {
        float retfloat = Float.NaN;

        if (membershipFunction != null) {
            float x;
            float dom;
            float maxDoM = 0.0f;
            
            for (Iterator<Float> it = membershipFunction.iterator(); it.hasNext();) {
                x = it.next();
                dom = membershipFunction.getDegreeOfMembership(x);

                if (dom > maxDoM) {
                    maxDoM = dom;
                    retfloat = x;

                    if (maxDoM == 1.0f) {
                        return x;
                    }
                }
            }
        }

        return retfloat;
    }

    @Override
    public String toString() {
        return FuzzyResourceManager.getString(this, "DEFUZZIFICATOR_LEFT_OF_MAX");
    }
}
