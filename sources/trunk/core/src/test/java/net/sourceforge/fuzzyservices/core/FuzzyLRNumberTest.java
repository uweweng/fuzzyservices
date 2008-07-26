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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class FuzzyLRNumberTest.
 *
 * @author Uwe Weng
 */
public class FuzzyLRNumberTest {

    public FuzzyLRNumberTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getAlpha method, of class FuzzyLRNumber.
     */
    @Test
    public void testGetAlpha() {
        System.out.println("getAlpha");
        FuzzyLRNumber instance = new FuzzyLRNumber();
        float expResult = 0.0F;
        float result = instance.getAlpha();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBeta method, of class FuzzyLRNumber.
     */
    @Test
    public void testGetBeta() {
        System.out.println("getBeta");
        FuzzyLRNumber instance = new FuzzyLRNumber();
        float expResult = 0.0F;
        float result = instance.getBeta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of invert method, of class FuzzyLRNumber.
     */
    @Test
    public void testInvert() {
        System.out.println("invert");
        FuzzyLRNumber instance = new FuzzyLRNumber();
        instance.invert();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNegative method, of class FuzzyLRNumber.
     */
    @Test
    public void testIsNegative() {
        System.out.println("isNegative");
        FuzzyLRNumber instance = new FuzzyLRNumber();
        boolean expResult = false;
        boolean result = instance.isNegative();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPositive method, of class FuzzyLRNumber.
     */
    @Test
    public void testIsPositive() {
        System.out.println("isPositive");
        FuzzyLRNumber instance = new FuzzyLRNumber();
        boolean expResult = false;
        boolean result = instance.isPositive();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of negate method, of class FuzzyLRNumber.
     */
    @Test
    public void testNegate() {
        System.out.println("negate");
        FuzzyLRNumber instance = new FuzzyLRNumber();
        instance.negate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class FuzzyLRNumber.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        float x = 0.0F;
        FuzzyLRNumber instance = new FuzzyLRNumber();
        float expResult = 0.0F;
        float result = instance.remove(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class FuzzyLRNumber.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        float x = 0.0F;
        float dom = 0.0F;
        FuzzyLRNumber instance = new FuzzyLRNumber();
        float expResult = 0.0F;
        float result = instance.set(x, dom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class FuzzyLRNumber.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        boolean withPoints = false;
        FuzzyLRNumber instance = new FuzzyLRNumber();
        String expResult = "";
        String result = instance.toString(withPoints);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}