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

import net.sourceforge.fuzzyservices.core.FuzzySet;
import net.sourceforge.fuzzyservices.core.MembershipFunction;
import net.sourceforge.fuzzyservices.utils.FuzzyManager;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class CenterOfArea.
 *
 * @author Uwe Weng
 */
public class CenterOfAreaTest {

    /**
     * Test of defuzzify method, of class CenterOfArea.
     */
    @Test
    public void testDefuzzify() {
        System.out.println("defuzzify");
        MembershipFunction membershipFunction = null;
        CenterOfArea instance = new CenterOfArea();
        float expResult = Float.NaN;
        float result = instance.defuzzify(membershipFunction);
        assertEquals(expResult, result, 0.0F);

        // Test f(x) = 0
        membershipFunction = new FuzzySet();
        expResult = Float.NaN;
        result = instance.defuzzify(membershipFunction);
        assertEquals(result, expResult, Float.NaN);

        // Test f(0.0) = 0.5
        membershipFunction = new FuzzySet();
        membershipFunction.set(0.0F, 0.5F);
        expResult = 0.0F;
        result = instance.defuzzify(membershipFunction);
        assertEquals(result, expResult, 0.0F);

        // Test f(x) = 1, x = 0
        membershipFunction = new FuzzySet(0.0F);
        expResult = 0.0F;
        result = instance.defuzzify(membershipFunction);
        assertEquals(result, expResult, 0.0F);

        // Test f(x) = 1, x <= 2
        membershipFunction = new FuzzySet();
        membershipFunction.set(2.0F, 1.0F);
        // Minimal defined x value at -1.0
        membershipFunction.set(-1.0F, 1.0F);
        expResult = 0.5F;
        result = instance.defuzzify(membershipFunction);
        assertEquals(result, expResult, 0.0F);

        // Test f(x) = 1, x >= 2
        membershipFunction = new FuzzySet();
        membershipFunction.set(2.0F, 1.0F);
        membershipFunction.set(0.0F, 0.0F);
        expResult = 1.33F;
        result = instance.defuzzify(membershipFunction);
        assertEquals(result, expResult, FuzzyManager.getDelta(expResult));

        // Test f(0.0) = 1 (spread = 2.0)
        membershipFunction = new FuzzySet(0.0F, 2.0F);
        expResult = 0.0F;
        result = instance.defuzzify(membershipFunction);
        assertEquals(result, expResult, 0.0F);

        // Test f(-1.0) = 1 (spread = 2.0)
        membershipFunction = new FuzzySet(-1.0F, 2.0F);
        expResult = -1.0F;
        result = instance.defuzzify(membershipFunction);
        assertEquals(result, expResult, 0.0F);

        // Test f(1.0) = 1 (spread = 2.0)
        membershipFunction = new FuzzySet(1.0F, 2.0F);
        expResult = 1.0F;
        result = instance.defuzzify(membershipFunction);
        assertEquals(result, expResult, 0.0F);

        // Test Plateau
        membershipFunction = new FuzzySet(-1.0F, 1.0F, 2.0F, 2.0F);
        expResult = 0.0F;
        result = instance.defuzzify(membershipFunction);
        assertEquals(result, expResult, 0.0F);

        // Test f(x) = ...---...---...
        membershipFunction = new FuzzySet();
        membershipFunction.set(-1.0F, 0.0F);
        membershipFunction.set(-0.5F, 1.0F);
        membershipFunction.set(0.0F, 0.0F);
        membershipFunction.set(1.0F, 1.0F);
        membershipFunction.set(2.0F, 0.0F);
        expResult = 0.5F;
        result = instance.defuzzify(membershipFunction);
        assertEquals(result, expResult, 0.0F);
    }

    /**
     * Test of toString method, of class CenterOfArea.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CenterOfArea instance = new CenterOfArea();
        String result = instance.toString();
        assertNotNull(result);
    }
}