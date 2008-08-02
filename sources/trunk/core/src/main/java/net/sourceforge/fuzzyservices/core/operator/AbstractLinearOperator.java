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

import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.utils.FuzzyManager;
import net.sourceforge.fuzzyservices.core.FuzzySet;

import java.io.Serializable;
import java.util.Iterator;

/**
 * The <strong>abstract</strong> class <code>AbstractLinearOperator</code> is
 * the base class for all fuzzy operators which suppose a linear relationship
 * between membership functions.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public abstract class AbstractLinearOperator
        extends AbstractOperator
        implements Serializable {

    @Override
    public FuzzySet combine(final FuzzySet fs1, final FuzzySet fs2) {
        if ((fs1 != null) && (fs2 != null)) {
            FuzzySet fs = new FuzzySet();
            int size1 = fs1.size();
            int size2 = fs2.size();
            float x;

            // Because of better performance we iterate the fuzzy set with most
            // points at first.
            if (size1 >= size2) {
                for (Iterator<Float> it = fs1.iterator(); it.hasNext();) {
                    x = it.next();
                    fs.set(x,
                            compute(fs1.getDegreeOfMembership(x),
                            fs2.getDegreeOfMembership(x)));
                }

                for (Iterator<Float> it = fs2.iterator(); it.hasNext();) {
                    x = it.next();
                    fs.set(x,
                            compute(fs1.getDegreeOfMembership(x),
                            fs2.getDegreeOfMembership(x)));
                }
            }

            if (size1 < size2) { // it is the else-clause of if-clause above

                for (Iterator<Float> it = fs2.iterator(); it.hasNext();) {
                    x = it.next();
                    fs.set(x,
                            compute(fs1.getDegreeOfMembership(x),
                            fs2.getDegreeOfMembership(x)));
                }

                for (Iterator<Float> it = fs1.iterator(); it.hasNext();) {
                    x = it.next();
                    fs.set(x,
                            compute(fs1.getDegreeOfMembership(x),
                            fs2.getDegreeOfMembership(x)));
                }
            }

            // Singleton requires a special treatment.
            if (size1 == 1) {
                Iterator<Float> it = fs1.iterator();
                x = it.next();
                fs.set(x - FuzzyManager.getStepwidth(),
                        compute(0.0f,
                        fs2.getDegreeOfMembership(x - FuzzyManager.getStepwidth())));
                fs.set(x + FuzzyManager.getStepwidth(),
                        compute(0.0f,
                        fs2.getDegreeOfMembership(x + FuzzyManager.getStepwidth())));
            }

            if (size2 == 1) {
                Iterator<Float> it = fs2.iterator();
                x = it.next();
                fs.set(x - FuzzyManager.getStepwidth(),
                        compute(fs1.getDegreeOfMembership(x - FuzzyManager.getStepwidth()),
                        0.0f));
                fs.set(x + FuzzyManager.getStepwidth(),
                        compute(fs1.getDegreeOfMembership(x + FuzzyManager.getStepwidth()),
                        0.0f));
            }

            // Or: Jetzt fehlen nur noch die Schnittpunkte.
            if ((size1 > 1) && (size2 > 1)) {
                float minX1 = fs1.getMinDefinedX();
                float maxX1 = fs1.getMaxDefinedX();
                float minX2 = fs2.getMinDefinedX();
                float maxX2 = fs2.getMaxDefinedX();
                boolean notCutting =
                        (((minX1 <= minX2) && (maxX1 <= minX2)) || ((minX1 >= minX2) && (minX1 >= maxX2)));

                if (!notCutting) {
                    /**
                     * Folglich liegen Ueberschneidungen vor. Es wird
                     * ausgenutzt, dass die neue Fuzzy-Menge bereits die
                     * spezifizierten Punkte beider Fuzzy-Mengen beinhaltet.
                     * Es reicht jetzt aus zu ueberpruefen, ob ein
                     * Vorzeichenwechsel bei der Zugehoerigkeitsdifferenz
                     * zwischen zwei Punkten vorliegt.
                     */
                    Iterator<Float> it = fs.iterator();

                    float xLeft;
                    float xRight;
                    float domLeft1;
                    float domLeft2;
                    float domRight1;
                    float domRight2;
                    float slope1;
                    float slope2;
                    xLeft = it.next();
                    domLeft1 = fs1.getDegreeOfMembership(xLeft);
                    domLeft2 = fs2.getDegreeOfMembership(xLeft);

                    while (it.hasNext()) {
                        xRight = it.next();
                        domRight1 = fs1.getDegreeOfMembership(xRight);
                        domRight2 = fs2.getDegreeOfMembership(xRight);

                        if (((domLeft1 > domRight1) && (domLeft2 < domRight2)) ||
                                ((domLeft1 < domRight1) && (domLeft2 > domRight2))) {
                            /**
                             * Es liegt ein Vorzeichenwechsel vor.
                             * Der genaue x-Wert, bei dem sich die
                             * Zugehoerigkeitsfunktionen ueberschneiden, wird
                             * mittels Steigungsdreieck ermittelt.
                             * y = a + b * x  und y' = a' + b' * x
                             * -> b = (y - a) / x, mit x = x2 - x1
                             * bzw.
                             * b' = (y' - a') / x, mit x = x2 - x1
                             * slope1 = fs1.getSlope(xLeft, xRight);
                             * slope2 = fs2.getSlope(xLeft, xRight);
                             * identisch mit ...
                             */
                            slope1 = ((domRight1 - domLeft1) / (xRight - xLeft));
                            slope2 = ((domRight2 - domLeft2) / (xRight - xLeft));
                            // Fuer y = y' gilt: x = (a - a') / (b' - b)
                            x = xLeft + ((domLeft2 - domLeft1) / (slope1 - slope2));
                            fs.set(x,
                                    compute(fs1.getDegreeOfMembership(x),
                                    fs2.getDegreeOfMembership(x)));
                        }

                        xLeft = xRight;
                        domLeft1 = domRight1;
                        domLeft2 = domRight2;
                    }
                }
            }

            fs.reduce();

            return fs;
        }

        return null;
    }
}
