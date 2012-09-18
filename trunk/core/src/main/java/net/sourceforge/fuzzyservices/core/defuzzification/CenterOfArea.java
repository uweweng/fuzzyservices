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
package net.sourceforge.fuzzyservices.core.defuzzification;

import net.sourceforge.fuzzyservices.core.AbstractDefuzzificator;
import net.sourceforge.fuzzyservices.utils.FuzzyManager;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;
import net.sourceforge.fuzzyservices.core.MembershipFunction;

import java.io.Serializable;
import java.util.Iterator;

/**
 * The class <code>CenterOfArea</code> represents a defuzzification method. In
 * this case, the area below the membership function is calculated. The center
 * of gravity of this area is the crisp value.
 *
 * @author Uwe Weng
 * @version 1.0
 */
public class CenterOfArea extends AbstractDefuzzificator implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public final float defuzzify(final MembershipFunction membershipFunction) {
        if (membershipFunction != null) {
            for (Iterator<Float> it = membershipFunction.iterator(); it.hasNext();) {
                float x1 = it.next().floatValue();
                float y1 = membershipFunction.getDegreeOfMembership(x1);

                if (it.hasNext()) {
                    float x2;
                    float y2;
                    float diff;
                    float slope;
                    float quaddiff;
                    float tridiff;
                    float zaehler = 0.0f;
                    float nenner = 0.0f;

                    while (it.hasNext()) {
                        x2 = it.next().floatValue();
                        y2 = membershipFunction.getDegreeOfMembership(x2);

                        slope = (y2 - y1) / (x2 - x1);
                        diff = y1 - (slope * x1);
                        // Hilfsrechnungen
                        quaddiff = (new Float((Math.pow(x2, 2.0) - Math.pow(x1, 2.0)))).floatValue();
                        tridiff = (new Float((Math.pow(x2, 3.0) - Math.pow(x1, 3.0)))).floatValue();
                        // Calculate moment
                        // zaehler += 0.33f * slope * tridiff + 0.5f * diff
                        // * quaddiff;
                        zaehler += (((float) (1.0 / 3.0) * slope * tridiff) +
                                ((float) (1.0 / 2.0) * diff * quaddiff));
                        // Calculate trapez.
                        // nenner += 0.5f * slope * quaddiff + diff * (x2 - x1);
                        nenner += (((float) (1.0 / 2.0) * slope * quaddiff) + (diff * (x2 - x1)));
                        x1 = x2;
                        y1 = y2;
                    }

                    return FuzzyManager.round(zaehler / nenner);
                }

                return x1;
            }
        }

        return Float.NaN;
    }

    @Override
    public String toString() {
        return FuzzyResourceManager.getString(this, "DEFUZZIFICATOR_CENTER_OF_AREA");
    }

    @Override
    public String getName() {
        return FuzzyResourceManager.getString(this, "DEFUZZIFICATOR_CENTER_OF_AREA");
    }
}
