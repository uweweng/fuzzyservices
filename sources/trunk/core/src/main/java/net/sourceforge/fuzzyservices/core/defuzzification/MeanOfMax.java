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
package net.sourceforge.fuzzyservices.core.defuzzification;

import net.sourceforge.fuzzyservices.core.AbstractDefuzzificator;
import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;
import net.sourceforge.fuzzyservices.core.MembershipFunction;
import java.io.Serializable;


/**
 * The class <code>MeanOfMax</code> represents a defuzzification method. In
 * this case, the medium defined x value with the highest degree of membership
 * is the crisp value.
 *
 * @author Uwe Weng
 * @since 1.0
 */
public class MeanOfMax extends AbstractDefuzzificator implements Serializable {
    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Defuzzifies the membership function object.
     * @return The crisp value as result of the defuzzification
     * @param f The membership function to be defuzzified
     */
    public float defuzzify(MembershipFunction f) {
        float value = Float.NaN;
        if (f != null) {
            value = (((new LeftOfMax()).defuzzify(f) +
                    (new RightOfMax()).defuzzify(f)) / 2.0f);
        }
        return value;
    }
    
    /**
     * Returns a textual representation of the defuzzificator
     * @return a string representation of the defzzificator
     */
    public String toString() {
        return FuzzyResourceManager.getString(this, 
                "DEFUZZIFICATOR_MEAN_OF_MAX");
    }
    
    /**
     * Indicates whether some other object is "equal to" this defuzzificator
     * @param obj the reference object with which to compare
     * @return <code>true</code> if this defuzzificator is the same as the 
     * <code>obj</code> argument, <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if ((obj != null) && (obj instanceof MeanOfMax)) {
            isEqual = true;
        }
        return isEqual;
    }
}
