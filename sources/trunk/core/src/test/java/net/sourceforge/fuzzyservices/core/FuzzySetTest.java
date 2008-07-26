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
 * Test of class FuzzySetTest.
 *
 * @author Uwe Weng
 */
public class FuzzySetTest {

    /**
     * Test of clear method, of class FuzzySet.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        FuzzySet instance = new FuzzySet();
        instance.clear();
        fail("The test case is a prototype.");
    }

    /**
     * Test of combine method, of class FuzzySet.
     */
    @Test
    public void testCombine_3args() {
        System.out.println("combine");
        FuzzySet fs1 = null;
        FuzzySet fs2 = null;
        AbstractOperator op = null;
        FuzzySet expResult = null;
        FuzzySet result = FuzzySet.combine(fs1, fs2, op);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of combine method, of class FuzzySet.
     */
    @Test
    public void testCombine_FuzzySet_AbstractOperator() {
        System.out.println("combine");
        FuzzySet fs = null;
        AbstractOperator op = null;
        FuzzySet instance = new FuzzySet();
        FuzzySet expResult = null;
        FuzzySet result = instance.combine(fs, op);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of concentrate method, of class FuzzySet.
     */
    @Test
    public void testConcentrate() {
        System.out.println("concentrate");
        FuzzySet instance = new FuzzySet();
        instance.concentrate();
        fail("The test case is a prototype.");
    }

    /**
     * Test of dilate method, of class FuzzySet.
     */
    @Test
    public void testDilate() {
        System.out.println("dilate");
        FuzzySet instance = new FuzzySet();
        instance.dilate();
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGranularity method, of class FuzzySet.
     */
    @Test
    public void testGetGranularity() {
        System.out.println("getGranularity");
        FuzzySet fs = null;
        FuzzySet instance = new FuzzySet();
        float expResult = 0.0F;
        float result = instance.getGranularity(fs);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class FuzzySet.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        FuzzySet instance = new FuzzySet();
        float expResult = 0.0F;
        float result = instance.getHeight();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumSteps method, of class FuzzySet.
     */
    @Test
    public void testGetNumSteps() {
        System.out.println("getNumSteps");
        FuzzySet fs = null;
        FuzzySet instance = new FuzzySet();
        int expResult = 0;
        int result = instance.getNumSteps(fs);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isConvex method, of class FuzzySet.
     */
    @Test
    public void testIsConvex() {
        System.out.println("isConvex");
        FuzzySet instance = new FuzzySet();
        boolean expResult = false;
        boolean result = instance.isConvex();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNormalized method, of class FuzzySet.
     */
    @Test
    public void testIsNormalized() {
        System.out.println("isNormalized");
        FuzzySet instance = new FuzzySet();
        boolean expResult = false;
        boolean result = instance.isNormalized();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidFuzzyInterval method, of class FuzzySet.
     */
    @Test
    public void testIsValidFuzzyInterval() {
        System.out.println("isValidFuzzyInterval");
        FuzzySet instance = new FuzzySet();
        boolean expResult = false;
        boolean result = instance.isValidFuzzyInterval();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidFuzzyLRInterval method, of class FuzzySet.
     */
    @Test
    public void testIsValidFuzzyLRInterval() {
        System.out.println("isValidFuzzyLRInterval");
        FuzzySet instance = new FuzzySet();
        boolean expResult = false;
        boolean result = instance.isValidFuzzyLRInterval();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidFuzzyLRNumber method, of class FuzzySet.
     */
    @Test
    public void testIsValidFuzzyLRNumber() {
        System.out.println("isValidFuzzyLRNumber");
        FuzzySet instance = new FuzzySet();
        boolean expResult = false;
        boolean result = instance.isValidFuzzyLRNumber();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidFuzzyNumber method, of class FuzzySet.
     */
    @Test
    public void testIsValidFuzzyNumber() {
        System.out.println("isValidFuzzyNumber");
        FuzzySet instance = new FuzzySet();
        boolean expResult = false;
        boolean result = instance.isValidFuzzyNumber();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of normalize method, of class FuzzySet.
     */
    @Test
    public void testNormalize() {
        System.out.println("normalize");
        FuzzySet instance = new FuzzySet();
        instance.normalize();
        fail("The test case is a prototype.");
    }

    /**
     * Test of reciproce method, of class FuzzySet.
     */
    @Test
    public void testReciproce() {
        System.out.println("reciproce");
        FuzzySet instance = new FuzzySet();
        instance.reciproce();
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class FuzzySet.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        float x = 0.0F;
        FuzzySet instance = new FuzzySet();
        float expResult = 0.0F;
        float result = instance.remove(x);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class FuzzySet.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        float x = 0.0F;
        float dom = 0.0F;
        FuzzySet instance = new FuzzySet();
        float expResult = 0.0F;
        float result = instance.set(x, dom);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class FuzzySet.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        boolean withPoints = false;
        FuzzySet instance = new FuzzySet();
        String expResult = "";
        String result = instance.toString(withPoints);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}