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
 * Test of class FuzzySet.
 *
 * @author Uwe Weng
 */
public class FuzzySetTest {

    /**
     * Test of clear method, of class FuzzySet.
     */
    @Test
    public final void testClear() {
        System.out.println("clear");
        FuzzySet instance = new FuzzySet();
        instance.clear();
        assertTrue(instance.size() == 0);
        instance = new FuzzySet();
        instance.set(0.0f, 1.0f);
        assertTrue(instance.size() > 0);
        instance.clear();
        assertTrue(instance.size() == 0);
    }

    /**
     * Test of combine method, of class FuzzySet.
     */
    @Test
    public final void testCombine_3args() {
        System.out.println("combine");
        FuzzySet fs1 = null;
        FuzzySet fs2 = null;
        AbstractOperator op = null;
        FuzzySet expResult = null;
        FuzzySet result = FuzzySet.combine(fs1, fs2, op);
        assertEquals(expResult, result);
        // @todo Fail("The test case is a prototype.");
    }

    /**
     * Test of combine method, of class FuzzySet.
     */
    @Test
    public final void testCombine_FuzzySet_AbstractOperator() {
        System.out.println("combine");
        FuzzySet fs = null;
        AbstractOperator op = null;
        FuzzySet instance = new FuzzySet();
        FuzzySet expResult = null;
        FuzzySet result = instance.combine(fs, op);
        assertEquals(expResult, result);
        // @todo Fail("The test case is a prototype.");
    }

    /**
     * Test of concentrate method, of class FuzzySet.
     */
    @Test
    public final void testConcentrate() {
        System.out.println("concentrate");
        FuzzySet instance = new FuzzySet();
        FuzzySet expResult = new FuzzySet();
        instance.concentrate();
        assertTrue(instance.equals(expResult));

        instance = new FuzzySet();
        instance.set(1.0f, 1.0f);
        expResult = new FuzzySet();
        expResult.set(1.0f, 1.0f);
        instance.concentrate();
        assertEquals(instance.getDegreeOfMembership(1.0f), expResult.getDegreeOfMembership(1.0f), 0.0f);

        instance = new FuzzySet();
        instance.set(0.5f, 0.5f);
        expResult = new FuzzySet();
        expResult.set(0.5f, 0.25f);
        instance.concentrate();
        assertEquals(instance.getDegreeOfMembership(0.5f), expResult.getDegreeOfMembership(0.5f), Float.POSITIVE_INFINITY);

        instance = new FuzzySet();
        instance.set(-0.5f, 0.5f);
        expResult = new FuzzySet();
        expResult.set(-0.5f, 0.25f);
        instance.concentrate();
        assertEquals(instance.getDegreeOfMembership(-0.5f), expResult.getDegreeOfMembership(-0.5f), Float.POSITIVE_INFINITY);

        instance = new FuzzySet();
        instance.set(0.3f, 0.3f);
        expResult = new FuzzySet();
        expResult.set(0.3f, (float) Math.pow(0.3f, 2.0));
        instance.concentrate();
        assertEquals(instance.getDegreeOfMembership(0.3f), expResult.getDegreeOfMembership(0.3f), Float.POSITIVE_INFINITY);

        instance = new FuzzySet();
        instance.set(0.0f, 0.0f);
        expResult = new FuzzySet();
        expResult.set(0.0f, 0.0f);
        instance.concentrate();
        assertEquals(instance.getDegreeOfMembership(0.0f), expResult.getDegreeOfMembership(0.0f), 0.0f);
    }

    /**
     * Test of dilate method, of class FuzzySet.
     */
    @Test
    public final void testDilate() {
        System.out.println("dilate");
        FuzzySet instance = new FuzzySet();
        FuzzySet expResult = new FuzzySet();
        instance.dilate();
        assertTrue(instance.equals(expResult));

        instance = new FuzzySet();
        instance.set(1.0f, 1.0f);
        expResult = new FuzzySet();
        expResult.set(1.0f, 1.0f);
        instance.dilate();
        assertEquals(instance.getDegreeOfMembership(1.0f), expResult.getDegreeOfMembership(1.0f), 0.0f);

        instance = new FuzzySet();
        instance.set(0.5f, 0.5f);
        expResult = new FuzzySet();
        expResult.set(0.5f, 0.70f);
        instance.dilate();
        assertEquals(instance.getDegreeOfMembership(0.5f), expResult.getDegreeOfMembership(0.5f), Float.POSITIVE_INFINITY);

        instance = new FuzzySet();
        instance.set(-0.5f, 0.5f);
        expResult = new FuzzySet();
        expResult.set(-0.5f, 0.70f);
        instance.dilate();
        assertEquals(instance.getDegreeOfMembership(-0.5f), expResult.getDegreeOfMembership(-0.5f), Float.POSITIVE_INFINITY);

        instance = new FuzzySet();
        instance.set(0.3f, 0.3f);
        expResult = new FuzzySet();
        expResult.set(0.3f, (float) Math.pow(0.3f, 0.5));
        instance.dilate();
        assertEquals(instance.getDegreeOfMembership(0.3f), expResult.getDegreeOfMembership(0.3f), Float.POSITIVE_INFINITY);

        instance = new FuzzySet();
        instance.set(0.0f, 0.0f);
        expResult = new FuzzySet();
        expResult.set(0.0f, 0.0f);
        instance.dilate();
        assertEquals(instance.getDegreeOfMembership(0.0f), expResult.getDegreeOfMembership(0.0f), 0.0f);
    }

    /**
     * Test of getGranularity method, of class FuzzySet.
     */
    @Test
    public final void testGetGranularity() {
        System.out.println("getGranularity");
        FuzzySet fs = null;
        FuzzySet instance = new FuzzySet();
        float expResult = 0.0F;
        float result = instance.getGranularity(fs);
        assertEquals(expResult, result);
        // @todo Fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class FuzzySet.
     */
    @Test
    public final void testGetHeight() {
        System.out.println("getHeight");
        FuzzySet instance = new FuzzySet();
        float expResult = 0.0F;
        float result = instance.getHeight();
        assertEquals(expResult, result, 0.0F);

        // Singleton has a height of 1.0
        instance = new FuzzySet(2.0f);
        expResult = 1.0F;
        result = instance.getHeight();
        assertEquals(expResult, result, 0.0F);

        instance = new FuzzySet();
        instance.set(1.0f, 0.8f);
        expResult = 0.8F;
        result = instance.getHeight();
        assertEquals(expResult, result, 0.0F);

        instance = new FuzzySet();
        instance.set(1.0f, 0.3f);
        instance.set(2.0f, 0.8f);
        instance.set(3.0f, 0.7f);
        instance.set(4.0f, 0.9f);
        expResult = 0.9F;
        result = instance.getHeight();
        assertEquals(expResult, result, 0.0F);
    }

    /**
     * Test of getNumSteps method, of class FuzzySet.
     */
    @Test
    public final void testGetNumSteps() {
        System.out.println("getNumSteps");
        FuzzySet fs = null;
        FuzzySet instance = new FuzzySet();
        int expResult = 0;
        int result = instance.getNumSteps(fs);
        assertEquals(expResult, result);
        // @todo Fail("The test case is a prototype.");
    }

    /**
     * Test of isConvex method, of class FuzzySet.
     */
    @Test
    public final void testIsConvex() {
        System.out.println("isConvex");
        FuzzySet instance = new FuzzySet();
        boolean expResult = false;
        boolean result = instance.isConvex();
        assertEquals(expResult, result);

        // Singleton is convex
        instance = new FuzzySet(0.0f);
        expResult = true;
        result = instance.isConvex();
        assertEquals(expResult, result);

        // Interval is convex
        instance = new FuzzySet();
        instance.set(1.0f, 1.0f);
        instance.set(2.0f, 1.0f);
        expResult = true;
        result = instance.isConvex();
        assertEquals(expResult, result);

        instance = new FuzzySet();
        instance.set(1.0f, 0.5f);
        instance.set(2.0f, 1.0f);
        expResult = true;
        result = instance.isConvex();
        assertEquals(expResult, result);

        instance = new FuzzySet();
        instance.set(1.0f, 1.0f);
        instance.set(2.0f, 0.5f);
        expResult = true;
        result = instance.isConvex();
        assertEquals(expResult, result);

        // Two changes of signs
        instance = new FuzzySet();
        instance.set(1.0f, 0.5f);
        instance.set(2.0f, 1.0f);
        instance.set(3.0f, 0.9f);
        instance.set(4.0f, 1.0f);
        expResult = false;
        result = instance.isConvex();
        assertEquals(expResult, result);
    }

    /**
     * Test of isNormalized method, of class FuzzySet.
     */
    @Test
    public final void testIsNormalized() {
        System.out.println("isNormalized");
        FuzzySet instance = new FuzzySet();
        boolean expResult = false;
        boolean result = instance.isNormalized();
        assertEquals(expResult, result);

        instance = new FuzzySet();
        instance.set(1.0f, 1.0f);
        expResult = true;
        result = instance.isNormalized();
        assertEquals(expResult, result);

        instance = new FuzzySet();
        instance.set(1.0f, 0.9f);
        expResult = false;
        result = instance.isNormalized();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidFuzzyInterval method, of class FuzzySet.
     */
    @Test
    public final void testIsValidFuzzyInterval() {
        System.out.println("isValidFuzzyInterval");
        FuzzySet instance = new FuzzySet();
        boolean expResult = false;
        boolean result = instance.isValidFuzzyInterval();
        assertEquals(expResult, result);

        // Singleton is not a valid fuzzy interval
        instance = new FuzzySet();
        instance.set(0.0F, 1.0F);
        expResult = false;
        result = instance.isValidFuzzyInterval();
        assertEquals(expResult, result);

        // Not convex
        instance = new FuzzySet();
        instance.set(0.0F, 1.0F);
        instance.set(0.5F, 1.0F);
        instance.set(1.0F, 0.5F);
        instance.set(2.0F, 0.8F);
        expResult = false;
        result = instance.isValidFuzzyInterval();
        assertEquals(expResult, result);

        // Not normalized
        instance = new FuzzySet();
        instance.set(0.0F, 0.5F);
        instance.set(1.0F, 0.5F);
        expResult = false;
        result = instance.isValidFuzzyInterval();
        assertEquals(expResult, result);

        // Classic fuzzy interval
        instance = new FuzzySet();
        instance.set(0.0F, 0.0F);
        instance.set(1.0F, 1.0F);
        instance.set(2.0F, 1.0F);
        instance.set(3.0F, 0.0F);
        expResult = true;
        result = instance.isValidFuzzyInterval();
        assertEquals(expResult, result);

        instance = new FuzzySet();
        instance.set(0.0F, 0.0F);
        instance.set(-1.0F, 1.0F);
        instance.set(-2.0F, 1.0F);
        instance.set(-3.0F, 0.0F);
        expResult = true;
        result = instance.isValidFuzzyInterval();
        assertEquals(expResult, result);

        instance = new FuzzySet();
        instance.set(-1.0F, 0.0F);
        instance.set(-0.5F, 1.0F);
        instance.set(0.5F, 1.0F);
        instance.set(1.0F, 0.0F);
        expResult = true;
        result = instance.isValidFuzzyInterval();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidFuzzyLRInterval method, of class FuzzySet.
     */
    @Test
    public final void testIsValidFuzzyLRInterval() {
        System.out.println("isValidFuzzyLRInterval");
        FuzzySet instance = new FuzzySet();
        boolean expResult = false;
        boolean result = instance.isValidFuzzyLRInterval();
        assertEquals(expResult, result);

        // Singleton is not a valid fuzzy interval of type LR
        instance = new FuzzySet();
        instance.set(0.0F, 1.0F);
        expResult = false;
        result = instance.isValidFuzzyLRInterval();
        assertEquals(expResult, result);

        // Not convex
        instance = new FuzzySet();
        instance.set(0.0F, 1.0F);
        instance.set(0.5F, 1.0F);
        instance.set(1.0F, 0.5F);
        instance.set(2.0F, 0.8F);
        expResult = false;
        result = instance.isValidFuzzyLRInterval();
        assertEquals(expResult, result);

        // Not normalized
        instance = new FuzzySet();
        instance.set(0.0F, 0.5F);
        instance.set(1.0F, 0.5F);
        expResult = false;
        result = instance.isValidFuzzyLRInterval();
        assertEquals(expResult, result);

        // Not LR
        instance = new FuzzySet();
        instance.set(0.0F, 0.0F);
        instance.set(0.5F, 0.6F);
        instance.set(1.0F, 1.0F);
        instance.set(2.0F, 1.0F);
        instance.set(2.5F, 0.7F);
        instance.set(3.0F, 0.0F);
        expResult = false;
        result = instance.isValidFuzzyLRInterval();
        assertEquals(expResult, result);

        // Classic fuzzy interval of type LR
        instance = new FuzzySet();
        instance.set(0.0F, 0.0F);
        instance.set(1.0F, 1.0F);
        instance.set(2.0F, 1.0F);
        instance.set(3.0F, 0.0F);
        expResult = true;
        result = instance.isValidFuzzyLRInterval();
        assertEquals(expResult, result);

        instance = new FuzzySet();
        instance.set(0.0F, 0.0F);
        instance.set(0.5F, 0.5F);
        instance.set(1.0F, 1.0F);
        instance.set(2.0F, 1.0F);
        instance.set(2.5F, 0.5F);
        instance.set(3.0F, 0.0F);
        expResult = true;
        result = instance.isValidFuzzyLRInterval();
        assertEquals(expResult, result);

        instance = new FuzzySet();
        instance.set(0.0F, 0.0F);
        instance.set(-1.0F, 1.0F);
        instance.set(-2.0F, 1.0F);
        instance.set(-3.0F, 0.0F);
        expResult = true;
        result = instance.isValidFuzzyLRInterval();
        assertEquals(expResult, result);

        instance = new FuzzySet();
        instance.set(-1.0F, 0.0F);
        instance.set(-0.5F, 1.0F);
        instance.set(0.5F, 1.0F);
        instance.set(1.0F, 0.0F);
        expResult = true;
        result = instance.isValidFuzzyLRInterval();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidFuzzyLRNumber method, of class FuzzySet.
     */
    @Test
    public final void testIsValidFuzzyLRNumber() {
        System.out.println("isValidFuzzyLRNumber");
        FuzzySet instance = new FuzzySet();
        boolean expResult = false;
        boolean result = instance.isValidFuzzyLRNumber();
        assertEquals(expResult, result);

        // Singleton is not a valid fuzzy number of type LR
        instance = new FuzzySet();
        instance.set(0.0F, 1.0F);
        expResult = false;
        result = instance.isValidFuzzyLRNumber();
        assertEquals(expResult, result);

        // Not convex
        instance = new FuzzySet();
        instance.set(-1.0F, 0.0F);
        instance.set(0.0F, 1.0F);
        instance.set(1.0F, 0.5F);
        instance.set(2.0F, 0.8F);
        expResult = false;
        result = instance.isValidFuzzyLRNumber();
        assertEquals(expResult, result);

        // Not normalized
        instance = new FuzzySet();
        instance.set(0.0F, 0.0F);
        instance.set(1.0F, 0.5F);
        instance.set(2.0F, 0.0F);
        expResult = false;
        result = instance.isValidFuzzyLRNumber();
        assertEquals(expResult, result);

        // Not normalized in one point
        instance = new FuzzySet();
        instance.set(-1.0F, 0.0F);
        instance.set(0.0F, 1.0F);
        instance.set(1.0F, 1.0F);
        instance.set(2.0F, 0.0F);
        expResult = false;
        result = instance.isValidFuzzyLRNumber();
        assertEquals(expResult, result);

        // Classic fuzzy number of type LR
        instance = new FuzzySet();
        instance.set(0.0F, 0.0F);
        instance.set(1.0F, 1.0F);
        instance.set(2.0F, 0.0F);
        expResult = true;
        result = instance.isValidFuzzyLRNumber();
        assertEquals(expResult, result);

        instance = new FuzzySet();
        instance.set(0.0F, 0.0F);
        instance.set(-1.0F, 1.0F);
        instance.set(-2.0F, 0.0F);
        expResult = true;
        result = instance.isValidFuzzyLRNumber();
        assertEquals(expResult, result);

        instance = new FuzzySet();
        instance.set(-1.0F, 0.0F);
        instance.set(0.0F, 1.0F);
        instance.set(1.0F, 0.0F);
        expResult = true;
        result = instance.isValidFuzzyLRNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidFuzzyNumber method, of class FuzzySet.
     */
    @Test
    public final void testIsValidFuzzyNumber() {
        System.out.println("isValidFuzzyNumber");
        FuzzySet instance = new FuzzySet();
        boolean expResult = false;
        boolean result = instance.isValidFuzzyNumber();
        assertEquals(expResult, result);

        // Singleton is a valid fuzzy number
        instance = new FuzzySet();
        instance.set(0.0F, 1.0F);
        expResult = true;
        result = instance.isValidFuzzyNumber();
        assertEquals(expResult, result);

        // Not convex
        instance = new FuzzySet();
        instance.set(0.0F, 1.0F);
        instance.set(1.0F, 0.5F);
        instance.set(2.0F, 0.8F);
        expResult = false;
        result = instance.isValidFuzzyNumber();
        assertEquals(expResult, result);

        // Not normalized
        instance = new FuzzySet();
        instance.set(0.0F, 0.5F);
        expResult = false;
        result = instance.isValidFuzzyNumber();
        assertEquals(expResult, result);

        // Not normalized in one point
        instance = new FuzzySet();
        instance.set(0.0F, 1.0F);
        instance.set(1.0F, 1.0F);
        expResult = false;
        result = instance.isValidFuzzyNumber();
        assertEquals(expResult, result);

        // Classic fuzzy number
        instance = new FuzzySet();
        instance.set(0.0F, 0.0F);
        instance.set(1.0F, 1.0F);
        instance.set(2.0F, 0.0F);
        expResult = true;
        result = instance.isValidFuzzyNumber();
        assertEquals(expResult, result);

        instance = new FuzzySet();
        instance.set(0.0F, 0.0F);
        instance.set(-1.0F, 1.0F);
        instance.set(-2.0F, 0.0F);
        expResult = true;
        result = instance.isValidFuzzyNumber();
        assertEquals(expResult, result);

        instance = new FuzzySet();
        instance.set(-1.0F, 0.0F);
        instance.set(0.0F, 1.0F);
        instance.set(1.0F, 0.0F);
        expResult = true;
        result = instance.isValidFuzzyNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of normalize method, of class FuzzySet.
     */
    @Test
    public final void testNormalize() {
        System.out.println("normalize");
        FuzzySet instance = new FuzzySet();
        instance.normalize();
        assertTrue(instance.getHeight() == 0.0f);

        instance = new FuzzySet(0.0f);
        instance.normalize();
        assertTrue(instance.getHeight() == 1.0f);
        assertTrue(instance.isNormalized());

        instance = new FuzzySet();
        instance.set(1.0f, 0.5f);
        instance.normalize();
        assertTrue(instance.getHeight() == 1.0f);
        assertTrue(instance.isNormalized());
    }

    /**
     * Test of reciproce method, of class FuzzySet.
     */
    @Test
    public final void testReciproce() {
        System.out.println("reciproce");
        FuzzySet instance = new FuzzySet();
        instance.reciproce();
        // @todo Fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class FuzzySet.
     */
    @Test
    public final void testRemove() {
        System.out.println("remove");
        float x = 0.0F;
        FuzzySet instance = new FuzzySet();
        float expResult = 0.0F;
        float result = instance.remove(x);
        assertEquals(expResult, result, 0.0F);

        instance = new FuzzySet();
        x = 0.0f;
        expResult = 1.0F;
        instance.set(x, 1.0F);
        result = instance.remove(x);
        assertEquals(expResult, result, 0.0F);

        // Remove undefined point
        instance = new FuzzySet();
        x = 0.0f;
        expResult = 0.0F;
        result = instance.remove(x);
        assertEquals(expResult, result, 0.0F);
    }

    /**
     * Test of set method, of class FuzzySet.
     */
    @Test
    public final void testSet() {
        System.out.println("set");
        float x = 0.0F;
        float dom = 0.0F;
        FuzzySet instance = new FuzzySet();
        float expResult = 0.0F;
        float result = instance.set(x, dom);
        assertEquals(expResult, result, 0.0F);

        instance = new FuzzySet();
        dom = 1.0F;
        expResult = 0.0F;
        result = instance.set(x, dom);
        assertEquals(expResult, result, 0.0F);
    }

    /**
     * Test of set method, of class FuzzySet.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSet1() {
        System.out.println("set");
        float x = 0.0F;
        float dom = Float.NaN;
        FuzzySet instance = new FuzzySet();
        instance.set(x, dom);
    }

    /**
     * Test of set method, of class FuzzySet.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSet2() {
        System.out.println("set");
        float x = 0.0F;
        float dom = 1.0F + Float.POSITIVE_INFINITY;
        FuzzySet instance = new FuzzySet();
        instance.set(x, dom);
    }

    /**
     * Test of set method, of class FuzzySet.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSet3() {
        System.out.println("set");
        float x = 0.0F;
        float dom = 0.0F - Float.NEGATIVE_INFINITY;
        FuzzySet instance = new FuzzySet();
        instance.set(x, dom);
    }

    /**
     * Test of toString method, of class FuzzySet.
     */
    @Test
    public final void testToString() {
        System.out.println("toString");
        boolean withPoints = false;
        FuzzySet instance = new FuzzySet();
        String result = instance.toString(withPoints);
        assertNotNull(result);
        withPoints = true;
        result = instance.toString(withPoints);
        assertNotNull(result);
    }
}