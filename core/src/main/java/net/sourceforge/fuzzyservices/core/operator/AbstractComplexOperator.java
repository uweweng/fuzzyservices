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

import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.core.FuzzySet;

import java.io.Serializable;
import java.util.Iterator;

/**
 * The <strong>abstract</strong> class <code>AbstractComplexOperator</code> is the
 * base class for all fuzzy operators which process a complex relationship
 * between membership functions. A relationship is complex if an iteration of
 * the x-axis is necessary.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public abstract class AbstractComplexOperator
        extends AbstractOperator
        implements Serializable {

    @Override
    public FuzzySet combine(final FuzzySet fs1, final FuzzySet fs2) {
        if ((fs1 != null) && (fs2 != null)) {
            FuzzySet fs = new FuzzySet();
            float granularity;
            int numSteps;
            float x;

            if ((fs1.isDefined()) && (fs2.isDefined())) {
                granularity = fs1.getGranularity(fs2);
                numSteps = fs1.getNumSteps(fs2);
                x = ((fs1.getMinDefinedX() < fs2.getMinDefinedX()) ? fs1.getMinDefinedX()
                        : fs2.getMinDefinedX());
            } else {
                // Exception: At least one fuzzy set has got an undefined
                // membership function
                if ((!fs1.isDefined()) && (!fs2.isDefined())) {
                    return fs;
                }

                if (fs1.isDefined()) {
                    granularity = fs2.getGranularity(fs2);
                    numSteps = fs2.getNumSteps(fs2);
                    x = fs2.getMinDefinedX();
                } else {
                    granularity = fs1.getGranularity(fs1);
                    numSteps = fs1.getNumSteps(fs1);
                    x = fs1.getMinDefinedX();
                }
            }

            if (granularity > 0.0f) {
                for (int i = 0; i < numSteps; i++) {
                    fs.set(x,
                            compute(fs1.getDegreeOfMembership(x),
                            fs2.getDegreeOfMembership(x)));
                    x += granularity;
                }
            }

            // Finally, we iterate all membership function points
            int size1 = fs1.size();
            int size2 = fs2.size();

            // Due to performance we iterate the fuzzy set with the most points
            // at first.
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

            if (size1 < size2) { // the else-clause of the prior if-clause

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

            fs.reduce();

            return fs;
        }

        return null;
    }
}
