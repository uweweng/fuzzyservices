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
package net.sourceforge.fuzzyservices.core.operator;

import net.sourceforge.fuzzyservices.core.FuzzySet;
import java.io.Serializable;
import java.util.Enumeration;


/**
 * The <strong>abstract</strong> class <code>AbstractComplexParameteredOperator
 * </code> is the base for the definition of parametrized fuzzy operators, 
 * which process complex relationships between degree of memberships. 
 * Therefore, the x axis is iterated in order to compute the new degree of 
 * membership.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public abstract class AbstractComplexParameteredOperator extends AbstractParameteredOperator
        implements Serializable {
    /**
     * Combines two fuzzy sets to a new fuzzy set.
     *
     * @param fs1
     *            The first operand
     * @param fs2
     *            The second operand
     * @return the result of this operation. It is a new fuzzy set.
     */
    public FuzzySet combine(final FuzzySet fs1, final FuzzySet fs2) {
        if ((fs1 != null) && (fs2 != null)) {
            FuzzySet fs = new FuzzySet();
            float granularity;
            int numSteps;
            float x;
            
            if ((fs1.isDefined()) && (fs2.isDefined())) {
                granularity = fs1.getGranularity(fs2);
                numSteps = fs1.getNumSteps(fs2);
                x = ((fs1.getMinDefinedX() < fs2.getMinDefinedX())
                ? fs1.getMinDefinedX() : fs2.getMinDefinedX());
            } else {
                // Exception handling: At least one fuzzy set has got an 
                // undefined membership function.
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
                    fs.set(x, compute(fs1.getDegreeOfMembership(x),
                            fs2.getDegreeOfMembership(x)));
                    x += granularity;
                }
            }
            
            // Finally, we are iterating all points on x axis
            int size1 = fs1.size();
            int size2 = fs2.size();
            Enumeration elements;
            
            // For better performance we war iterating the fuzzy set with the most points
            if (size1 >= size2) {
                elements = fs1.elements();
                
                while (elements.hasMoreElements()) {
                    x = ((Float) elements.nextElement()).floatValue();
                    fs.set(x,
                            compute(fs1.getDegreeOfMembership(x),
                            fs2.getDegreeOfMembership(x)));
                }
                
                elements = fs2.elements();
                
                while (elements.hasMoreElements()) {
                    x = ((Float) elements.nextElement()).floatValue();
                    fs.set(x,
                            compute(fs1.getDegreeOfMembership(x),
                            fs2.getDegreeOfMembership(x)));
                }
            }
            
            if (size1 < size2) { // it is the else-clause of if-clause before
                elements = fs2.elements();
                
                while (elements.hasMoreElements()) {
                    x = ((Float) elements.nextElement()).floatValue();
                    fs.set(x,
                            compute(fs1.getDegreeOfMembership(x),
                            fs2.getDegreeOfMembership(x)));
                }
                
                elements = fs1.elements();
                
                while (elements.hasMoreElements()) {
                    x = ((Float) elements.nextElement()).floatValue();
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
