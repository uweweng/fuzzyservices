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
package net.sourceforge.fuzzyservices.core;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class FuzzyLRIntervalTest.
 *
 * @author Uwe Weng
 */
public class FuzzyLRIntervalTest {

    /**
     * Test of getAlpha method, of class FuzzyLRInterval.
     */
    @Test
    public final void testGetAlpha() {
        System.out.println("getAlpha");
        FuzzyLRInterval instance = new FuzzyLRInterval();
        float expResult = 0.0F;
        float result = instance.getAlpha();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBeta method, of class FuzzyLRInterval.
     */
    @Test
    public final void testGetBeta() {
        System.out.println("getBeta");
        FuzzyLRInterval instance = new FuzzyLRInterval();
        float expResult = 0.0F;
        float result = instance.getBeta();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of invert method, of class FuzzyLRInterval.
     */
    @Test
    public final void testInvert() {
        System.out.println("invert");
        FuzzyLRInterval instance = new FuzzyLRInterval();
        instance.invert();
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNegative method, of class FuzzyLRInterval.
     */
    @Test
    public final void testIsNegative() {
        System.out.println("isNegative");
        FuzzyLRInterval instance = new FuzzyLRInterval();
        boolean expResult = false;
        boolean result = instance.isNegative();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPositive method, of class FuzzyLRInterval.
     */
    @Test
    public final void testIsPositive() {
        System.out.println("isPositive");
        FuzzyLRInterval instance = new FuzzyLRInterval();
        boolean expResult = false;
        boolean result = instance.isPositive();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of negate method, of class FuzzyLRInterval.
     */
    @Test
    public final void testNegate() {
        System.out.println("negate");
        FuzzyLRInterval instance = new FuzzyLRInterval();
        instance.negate();
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class FuzzyLRInterval.
     */
    @Test
    public final void testRemove() {
        System.out.println("remove");
        float x = 0.0F;
        FuzzyLRInterval instance = new FuzzyLRInterval();
        float expResult = 0.0F;
        float result = instance.remove(x);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class FuzzyLRInterval.
     */
    @Test
    public final void testSet() {
        System.out.println("set");
        float x = 0.0F;
        float dom = 0.0F;
        FuzzyLRInterval instance = new FuzzyLRInterval();
        float expResult = 0.0F;
        float result = instance.set(x, dom);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class FuzzyLRInterval.
     */
    @Test
    public final void testToString() {
        System.out.println("toString");
        boolean withPoints = false;
        FuzzyLRInterval instance = new FuzzyLRInterval();
        String expResult = "";
        String result = instance.toString(withPoints);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}