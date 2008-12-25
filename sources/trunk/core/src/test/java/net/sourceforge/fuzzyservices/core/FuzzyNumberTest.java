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
 * Test of class FuzzyNumber.
 *
 * @author Uwe Weng
 */
public class FuzzyNumberTest {

    /**
     * Test of invert method, of class FuzzyNumber.
     */
    @Test
    public final void testInvert() {
        System.out.println("invert");
        FuzzyNumber instance = new FuzzyNumber(2.0f, 1.0f);
        FuzzyNumber expResult = new FuzzyNumber(0.5f, 0.17f, 0.5f);
        instance.invert();
        assertEquals(expResult, instance);
        // TODO testInvert um weitere Beispiele ergaenzen
    }

    /**
     * Test of isNegative method, of class FuzzyNumber.
     */
    @Test
    public final void testIsNegative() {
        System.out.println("isNegative");
        FuzzyNumber instance = new FuzzyNumber(1.0f, 1.0f);
        boolean expResult = false;
        boolean result = instance.isNegative();
        assertEquals(expResult, result);

        instance = new FuzzyNumber(-1.0f, 1.0f);
        expResult = true;
        result = instance.isNegative();
        assertEquals(expResult, result);

        instance = new FuzzyNumber(0.0f, 1.0f);
        expResult = false;
        result = instance.isNegative();
        assertEquals(expResult, result);
    }

    /**
     * Test of isPositive method, of class FuzzyNumber.
     */
    @Test
    public final void testIsPositive() {
        System.out.println("isPositive");
        FuzzyNumber instance = new FuzzyNumber(1.0f, 1.0f);
        boolean expResult = true;
        boolean result = instance.isPositive();
        assertEquals(expResult, result);

        instance = new FuzzyNumber(-1.0f, 1.0f);
        expResult = false;
        result = instance.isPositive();
        assertEquals(expResult, result);

        instance = new FuzzyNumber(0.0f, 1.0f);
        expResult = false;
        result = instance.isPositive();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidFuzzyLRNumber method, of class FuzzyNumber.
     */
    @Test
    public final void testIsValidFuzzyLRNumber() {
        System.out.println("isValidFuzzyLRNumber");
        FuzzyNumber instance = new FuzzyNumber(1.0f, 1.0f);
        boolean expResult = true;
        boolean result = instance.isValidFuzzyLRNumber();
        assertEquals(expResult, result);

        // Classic fuzzy number of type LR
        instance = new FuzzyNumber(-1.0f, 1.0f);
        expResult = true;
        result = instance.isValidFuzzyLRNumber();
        assertEquals(expResult, result);

        instance = new FuzzyNumber(0.0f, 1.0f);
        expResult = true;
        result = instance.isValidFuzzyLRNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of negate method, of class FuzzyNumber.
     */
    @Test
    public final void testNegate() {
        System.out.println("negate");
        FuzzyNumber instance = new FuzzyNumber(2.0f, 1.0f);
        FuzzyNumber expResult = new FuzzyNumber(-2.0f, 1.0f);
        instance.negate();
        assertEquals(expResult, instance);

        // vice versa
        instance = new FuzzyNumber(-2.0f, 1.0f);
        expResult = new FuzzyNumber(2.0f, 1.0f);
        instance.negate();
        assertEquals(expResult, instance);

        // negating at zero
        instance = new FuzzyNumber(0.0f, 1.0f);
        expResult = new FuzzyNumber(0.0f, 1.0f);
        instance.negate();
        assertEquals(expResult, instance);

        // asymmetric
        instance = new FuzzyNumber(-4.0f, 1.0f, 2.0f);
        expResult = new FuzzyNumber(4.0f, 2.0f, 1.0f);
        instance.negate();
        assertEquals(expResult, instance);

        // asymmetric
        instance = new FuzzyNumber(3.0f, 1.0f, 2.0f);
        expResult = new FuzzyNumber(-3.0f, 2.0f, 1.0f);
        instance.negate();
        assertEquals(expResult, instance);

        // negating over zero
        instance = new FuzzyNumber(1.0f, 3.0f, 4.0f);
        expResult = new FuzzyNumber(-1.0f, 4.0f, 3.0f);
        instance.negate();
        assertEquals(expResult, instance);
    }

    /**
     * Test of remove method, of class FuzzyNumber.
     */
    @Test
    public final void testRemove() {
        System.out.println("remove");
        float x = 0.5F;
        FuzzyNumber instance = new FuzzyNumber(1.0f, 1.0f);
        float expResult = 0.5F;
        float result = instance.remove(x);
        assertEquals(expResult, result, 0.0F);

        // Remove undefined point
        instance = new FuzzyNumber(1.0f, 1.0f);
        x = 0.0f;
        expResult = 0.0F;
        result = instance.remove(x);
        assertEquals(expResult, result, 0.0F);
    }

    /**
     * Test of remove method, of class FuzzyNumber.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testRemove1() {
        System.out.println("remove");
        float x = 1.0F;
        FuzzyNumber instance = new FuzzyNumber(1.0f, 0.0f);
        instance.remove(x);
    }

    /**
     * Test of set method, of class FuzzyNumber.
     */
    @Test
    public final void testSet() {
        System.out.println("set");
        float x = 0.0F;
        float dom = 1.0F;
        FuzzyNumber instance = new FuzzyNumber(x, 1.0f);
        float expResult = 1.0F;
        float result = instance.set(x, dom);
        assertEquals(expResult, result, 0.0F);
    }

    /**
     * Test of set method, of class FuzzyNumber.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSet1() {
        System.out.println("set");
        float x = 0.0F;
        float dom = Float.NaN;
        FuzzyNumber instance = new FuzzyNumber(x, 0.0f);
        instance.set(x, dom);
    }

    /**
     * Test of set method, of class FuzzyNumber.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSet2() {
        System.out.println("set");
        float x = 0.0F;
        float dom = 1.0F + Float.POSITIVE_INFINITY;
        FuzzyNumber instance = new FuzzyNumber(x, 0.0f);
        instance.set(x, dom);
    }

    /**
     * Test of set method, of class FuzzyNumber.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSet3() {
        System.out.println("set");
        float x = 0.0F;
        float dom = 0.0F - Float.NEGATIVE_INFINITY;
        FuzzyNumber instance = new FuzzyNumber(x, 0.0f);
        instance.set(x, dom);
    }

    /**
     * Test of set method, of class FuzzyNumber.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSet4() {
        System.out.println("set");
        float x = 0.0F;
        float dom = 1.0f - Float.POSITIVE_INFINITY;
        FuzzyNumber instance = new FuzzyNumber(x, 0.0f);
        instance.set(x, dom);
    }


    /**
     * Test of toString method, of class FuzzyNumber.
     */
    @Test
    public final void testToString() {
        System.out.println("toString");
        boolean withPoints = false;
        FuzzyNumber instance = new FuzzyNumber(1.0f, 1.0f);
        String result = instance.toString(withPoints);
        assertNotNull(result);
        withPoints = true;
        result = instance.toString(withPoints);
        assertNotNull(result);
    }

}