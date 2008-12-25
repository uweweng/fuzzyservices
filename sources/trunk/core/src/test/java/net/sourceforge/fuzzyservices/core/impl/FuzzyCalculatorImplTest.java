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
package net.sourceforge.fuzzyservices.core.impl;

import net.sourceforge.fuzzyservices.core.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class FuzzyCalculatorImpl.
 *
 * @author Uwe Weng
 */
public class FuzzyCalculatorImplTest {

    /**
     * Test of getInstance method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testGetInstance() {
        System.out.println("getInstance");
        FuzzyCalculator result = FuzzyCalculatorImpl.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of add method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testAdd_FuzzyInterval_FuzzyInterval() {
        System.out.println("add");
        FuzzyInterval operand1 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyInterval operand2 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyInterval expResult = new FuzzyInterval(2.0f, 4.0f, 2.0f, 2.0f);
        FuzzyInterval result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        operand1 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(2.0f, 4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(3.0f, 6.0f, 3.0f, 3.0f);
        result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(2.0f, 4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(3.0f, 6.0f, 3.0f, 3.0f);
        result = instance.add(operand2, operand1);
        assertEquals(expResult, result);

        // and negative
        operand1 = new FuzzyInterval(-2.0f, -1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(-6.0f, -3.0f, 3.0f, 3.0f);
        result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyInterval(-2.0f, -1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(-6.0f, -3.0f, 3.0f, 3.0f);
        result = instance.add(operand2, operand1);
        assertEquals(expResult, result);

        // and over zero
        operand1 = new FuzzyInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(-5.0f, -1.0f, 3.0f, 3.0f);
        result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(-5.0f, -1.0f, 3.0f, 3.0f);
        result = instance.add(operand2, operand1);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testAdd_FuzzyInterval_FuzzyInterval1() {
        System.out.println("add");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.add(operand1, operand2);
    }

    /**
     * Test of add method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testAdd_FuzzyInterval_FuzzyInterval2() {
        System.out.println("add");
        FuzzyInterval operand1 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyInterval operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.add(operand1, operand2);
    }

    /**
     * Test of add method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testAdd_FuzzyLRInterval_FuzzyLRInterval() {
        System.out.println("add");
        FuzzyLRInterval operand1 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyLRInterval operand2 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyLRInterval expResult = new FuzzyLRInterval(2.0f, 4.0f, 2.0f, 2.0f);
        FuzzyLRInterval result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        operand1 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(2.0f, 4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(3.0f, 6.0f, 3.0f, 3.0f);
        result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(2.0f, 4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(3.0f, 6.0f, 3.0f, 3.0f);
        result = instance.add(operand2, operand1);
        assertEquals(expResult, result);

        // and negative
        operand1 = new FuzzyLRInterval(-2.0f, -1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(-6.0f, -3.0f, 3.0f, 3.0f);
        result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRInterval(-2.0f, -1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(-6.0f, -3.0f, 3.0f, 3.0f);
        result = instance.add(operand2, operand1);
        assertEquals(expResult, result);

        // and over zero
        operand1 = new FuzzyLRInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(-5.0f, -1.0f, 3.0f, 3.0f);
        result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(-5.0f, -1.0f, 3.0f, 3.0f);
        result = instance.add(operand2, operand1);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testAdd_FuzzyLRInterval_FuzzyLRInterval1() {
        System.out.println("add");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.add(operand1, operand2);
    }

    /**
     * Test of add method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testAdd_FuzzyLRInterval_FuzzyLRInterval2() {
        System.out.println("add");
        FuzzyLRInterval operand1 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyLRInterval operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.add(operand1, operand2);
    }

    /**
     * Test of add method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testAdd_FuzzyLRNumber_FuzzyLRNumber() {
        System.out.println("add");
        FuzzyLRNumber operand1 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyLRNumber operand2 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyLRNumber expResult = new FuzzyLRNumber(2.0f, 2.0f, 2.0f);
        FuzzyLRNumber result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        operand1 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(3.0f, 3.0f, 3.0f);
        result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(3.0f, 3.0f, 3.0f);
        result = instance.add(operand2, operand1);
        assertEquals(expResult, result);

        // and negative
        operand1 = new FuzzyLRNumber(-2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(-4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(-6.0f, 3.0f, 3.0f);
        result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRNumber(-2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(-4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(-6.0f, 3.0f, 3.0f);
        result = instance.add(operand2, operand1);
        assertEquals(expResult, result);

        // and over zero
        operand1 = new FuzzyLRNumber(0.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(0.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(0.0f, 3.0f, 3.0f);
        result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRNumber(0.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(0.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(0.0f, 3.0f, 3.0f);
        result = instance.add(operand2, operand1);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testAdd_FuzzyLRNumber_FuzzyLRNumber1() {
        System.out.println("add");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.add(operand1, operand2);
    }

    /**
     * Test of add method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testAdd_FuzzyLRNumber_FuzzyLRNumber2() {
        System.out.println("add");
        FuzzyLRNumber operand1 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyLRNumber operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.add(operand1, operand2);
    }

    /**
     * Test of add method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testAdd_FuzzyNumber_FuzzyNumber() {
        System.out.println("add");
        FuzzyNumber operand1 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyNumber operand2 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyNumber expResult = new FuzzyNumber(2.0f, 2.0f, 2.0f);
        FuzzyNumber result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        operand1 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(3.0f, 3.0f, 3.0f);
        result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(3.0f, 3.0f, 3.0f);
        result = instance.add(operand2, operand1);
        assertEquals(expResult, result);

        // and negative
        operand1 = new FuzzyNumber(-2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(-4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(-6.0f, 3.0f, 3.0f);
        result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyNumber(-2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(-4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(-6.0f, 3.0f, 3.0f);
        result = instance.add(operand2, operand1);
        assertEquals(expResult, result);

        // and over zero
        operand1 = new FuzzyNumber(0.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(0.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(0.0f, 3.0f, 3.0f);
        result = instance.add(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyNumber(0.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(0.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(0.0f, 3.0f, 3.0f);
        result = instance.add(operand2, operand1);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testAdd_FuzzyNumber_FuzzyNumber1() {
        System.out.println("add");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.add(operand1, operand2);
    }

    /**
     * Test of add method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testAdd_FuzzyNumber_FuzzyNumber2() {
        System.out.println("add");
        FuzzyNumber operand1 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyNumber operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.add(operand1, operand2);
    }

    /**
     * Test of divide method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testDivide_FuzzyInterval_FuzzyInterval() {
        System.out.println("divide");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyInterval expResult = null;
//        FuzzyInterval result = instance.divide(operand1, operand2);
//        assertEquals(expResult, result);
    // @todo Fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testDivide_FuzzyInterval_FuzzyInterval1() {
        System.out.println("devide");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.divide(operand1, operand2);
    }

    /**
     * Test of divide method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testDivide_FuzzyInterval_FuzzyInterval2() {
        System.out.println("devide");
        FuzzyInterval operand1 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyInterval operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.divide(operand1, operand2);
    }

    /**
     * Test of divide method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testDivide_FuzzyLRInterval_FuzzyLRInterval() {
        System.out.println("divide");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyLRInterval expResult = null;
//        FuzzyLRInterval result = instance.divide(operand1, operand2);
//        assertEquals(expResult, result);
    // @todo Fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testDivide_FuzzyLRInterval_FuzzyLRInterval1() {
        System.out.println("devide");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.divide(operand1, operand2);
    }

    /**
     * Test of divide method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testDivide_FuzzyLRInterval_FuzzyLRInterval2() {
        System.out.println("devide");
        FuzzyLRInterval operand1 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyLRInterval operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.divide(operand1, operand2);
    }

    /**
     * Test of divide method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testDivide_FuzzyLRNumber_FuzzyLRNumber() {
        System.out.println("divide");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyLRNumber expResult = null;
//        FuzzyLRNumber result = instance.divide(operand1, operand2);
//        assertEquals(expResult, result);
    // @todo Fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testDivide_FuzzyLRNumber_FuzzyLRNumber1() {
        System.out.println("devide");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.divide(operand1, operand2);
    }

    /**
     * Test of divide method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testDivide_FuzzyLRNumber_FuzzyLRNumber2() {
        System.out.println("devide");
        FuzzyLRNumber operand1 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyLRNumber operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.divide(operand1, operand2);
    }

    /**
     * Test of divide method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testDivide_FuzzyNumber_FuzzyNumber() {
        System.out.println("divide");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyNumber expResult = null;
//        FuzzyNumber result = instance.divide(operand1, operand2);
//        assertEquals(expResult, result);
    // @todo Fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testDivide_FuzzyNumber_FuzzyNumber1() {
        System.out.println("devide");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.divide(operand1, operand2);
    }

    /**
     * Test of divide method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testDivide_FuzzyNumber_FuzzyNumber2() {
        System.out.println("devide");
        FuzzyNumber operand1 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyNumber operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.divide(operand1, operand2);
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testMultiply_FuzzyInterval_FuzzyInterval() {
        System.out.println("multiply");
        FuzzyInterval operand1 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyInterval operand2 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyInterval expResult = new FuzzyInterval(1.0f, 4.0f, 1.0f, 5.0f);
        FuzzyInterval result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        operand1 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(2.0f, 4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(2.0f, 8.0f, 2.0f, 10.0f);
        result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(2.0f, 4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(2.0f, 8.0f, 2.0f, 10.0f);
        result = instance.multiply(operand2, operand1);
        assertEquals(expResult, result);

        // and negative
        operand1 = new FuzzyInterval(-2.0f, -1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(2.0f, 8.0f, 2.0f, 10.0f);
        result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyInterval(-2.0f, -1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(2.0f, 8.0f, 2.0f, 10.0f);
        result = instance.multiply(operand2, operand1);
        assertEquals(expResult, result);

        // and over zero
        operand1 = new FuzzyInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        result = instance.multiply(operand2, operand1);
        assertEquals(expResult, result);
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testMultiply_FuzzyInterval_FuzzyInterval1() {
        System.out.println("multiply");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.multiply(operand1, operand2);
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testMultiply_FuzzyInterval_FuzzyInterval2() {
        System.out.println("multiply");
        FuzzyInterval operand1 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyInterval operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.multiply(operand1, operand2);
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testMultiply_FuzzyLRInterval_FuzzyLRInterval() {
        System.out.println("multiply");
        FuzzyLRInterval operand1 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyLRInterval operand2 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyLRInterval expResult = new FuzzyLRInterval(1.0f, 4.0f, 1.0f, 5.0f);
        FuzzyLRInterval result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        operand1 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(2.0f, 4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(2.0f, 8.0f, 2.0f, 10.0f);
        result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(2.0f, 4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(2.0f, 8.0f, 2.0f, 10.0f);
        result = instance.multiply(operand2, operand1);
        assertEquals(expResult, result);

        // and negative
        operand1 = new FuzzyLRInterval(-2.0f, -1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(2.0f, 8.0f, 2.0f, 10.0f);
        result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRInterval(-2.0f, -1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(2.0f, 8.0f, 2.0f, 10.0f);
        result = instance.multiply(operand2, operand1);
        assertEquals(expResult, result);

        // and over zero
        operand1 = new FuzzyLRInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        result = instance.multiply(operand2, operand1);
        assertEquals(expResult, result);
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testMultiply_FuzzyLRInterval_FuzzyLRInterval1() {
        System.out.println("multiply");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.multiply(operand1, operand2);
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testMultiply_FuzzyLRInterval_FuzzyLRInterval2() {
        System.out.println("multiply");
        FuzzyLRInterval operand1 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyLRInterval operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.multiply(operand1, operand2);
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testMultiply_FuzzyLRNumber_FuzzyLRNumber() {
        System.out.println("multiply");
        FuzzyLRNumber operand1 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyLRNumber operand2 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyLRNumber expResult = new FuzzyLRNumber(1.0f, 1.0f, 3.0f);
        FuzzyLRNumber result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        operand1 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(2.0f, 2.0f, 6.0f);
        result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(2.0f, 2.0f, 6.0f);
        result = instance.multiply(operand2, operand1);
        assertEquals(expResult, result);

        // and negative
        operand1 = new FuzzyLRNumber(-2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(-4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(8.0f, 6.0f, 10.0f);
        result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRNumber(-2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(-4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(8.0f, 6.0f, 10.0f);
        result = instance.multiply(operand2, operand1);
        assertEquals(expResult, result);

        // and over zero
        operand1 = new FuzzyLRNumber(0.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(0.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(0.0f, 2.0f, 2.0f);
        result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRNumber(0.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(0.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(0.0f, 2.0f, 2.0f);
        result = instance.multiply(operand2, operand1);
        assertEquals(expResult, result);
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testMultiply_FuzzyLRNumber_FuzzyLRNumber1() {
        System.out.println("multiply");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.multiply(operand1, operand2);
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testMultiply_FuzzyLRNumber_FuzzyLRNumber2() {
        System.out.println("multiply");
        FuzzyLRNumber operand1 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyLRNumber operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.multiply(operand1, operand2);
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testMultiply_FuzzyNumber_FuzzyNumber() {
        System.out.println("multiply");
        FuzzyNumber operand1 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyNumber operand2 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyNumber expResult = new FuzzyNumber(1.0f, 1.0f, 3.0f);
        FuzzyNumber result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        operand1 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(2.0f, 2.0f, 6.0f);
        result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(2.0f, 2.0f, 6.0f);
        result = instance.multiply(operand2, operand1);
        assertEquals(expResult, result);

        // and negative
        operand1 = new FuzzyNumber(-2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(-4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(8.0f, 6.0f, 10.0f);
        result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyNumber(-2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(-4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(8.0f, 6.0f, 10.0f);
        result = instance.multiply(operand2, operand1);
        assertEquals(expResult, result);

        // and over zero
        operand1 = new FuzzyNumber(0.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(0.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(0.0f, 2.0f, 2.0f);
        result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyNumber(0.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(0.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(0.0f, 2.0f, 2.0f);
        result = instance.multiply(operand2, operand1);
        assertEquals(expResult, result);
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testMultiply_FuzzyNumber_FuzzyNumber1() {
        System.out.println("multiply");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.multiply(operand1, operand2);
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testMultiply_FuzzyNumber_FuzzyNumber2() {
        System.out.println("multiply");
        FuzzyNumber operand1 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyNumber operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.multiply(operand1, operand2);
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testSubtract_FuzzyInterval_FuzzyInterval() {
        System.out.println("subtract");
        FuzzyInterval operand1 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyInterval operand2 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyInterval expResult = new FuzzyInterval(-1.0f, 1.0f, 2.0f, 2.0f);
        FuzzyInterval result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        operand1 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(2.0f, 4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(-3.0f, 0.0f, 3.0f, 3.0f);
        result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(2.0f, 4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(0.0f, 3.0f, 3.0f, 3.0f);
        result = instance.subtract(operand2, operand1);
        assertEquals(expResult, result);

        // and negative
        operand1 = new FuzzyInterval(-2.0f, -1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(0.0f, 3.0f, 3.0f, 3.0f);
        result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyInterval(-2.0f, -1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(-3.0f, 0.0f, 3.0f, 3.0f);
        result = instance.subtract(operand2, operand1);
        assertEquals(expResult, result);

        // and over zero
        operand1 = new FuzzyInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(1.0f, 5.0f, 3.0f, 3.0f);
        result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyInterval(-5.0f, -1.0f, 3.0f, 3.0f);
        result = instance.subtract(operand2, operand1);
        assertEquals(expResult, result);
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testSubtract_FuzzyInterval_FuzzyInterval1() {
        System.out.println("subtract");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.subtract(operand1, operand2);
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testSubtract_FuzzyInterval_FuzzyInterval2() {
        System.out.println("subtract");
        FuzzyInterval operand1 = new FuzzyInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyInterval operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.subtract(operand1, operand2);
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testSubtract_FuzzyLRInterval_FuzzyLRInterval() {
        System.out.println("subtract");
        FuzzyLRInterval operand1 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyLRInterval operand2 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyLRInterval expResult = new FuzzyLRInterval(-1.0f, 1.0f, 2.0f, 2.0f);
        FuzzyLRInterval result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        operand1 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(2.0f, 4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(-3.0f, 0.0f, 3.0f, 3.0f);
        result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(2.0f, 4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(0.0f, 3.0f, 3.0f, 3.0f);
        result = instance.subtract(operand2, operand1);
        assertEquals(expResult, result);

        // and negative
        operand1 = new FuzzyLRInterval(-2.0f, -1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(0.0f, 3.0f, 3.0f, 3.0f);
        result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRInterval(-2.0f, -1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(-3.0f, 0.0f, 3.0f, 3.0f);
        result = instance.subtract(operand2, operand1);
        assertEquals(expResult, result);

        // and over zero
        operand1 = new FuzzyLRInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(1.0f, 5.0f, 3.0f, 3.0f);
        result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRInterval(-1.0f, 1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRInterval(-4.0f, -2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRInterval(-5.0f, -1.0f, 3.0f, 3.0f);
        result = instance.subtract(operand2, operand1);
        assertEquals(expResult, result);
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testSubtract_FuzzyLRInterval_FuzzyLRInterval1() {
        System.out.println("subtract");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.subtract(operand1, operand2);
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testSubtract_FuzzyLRInterval_FuzzyLRInterval2() {
        System.out.println("subtract");
        FuzzyLRInterval operand1 = new FuzzyLRInterval(1.0f, 2.0f, 1.0f, 1.0f);
        FuzzyLRInterval operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.subtract(operand1, operand2);
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testSubtract_FuzzyLRNumber_FuzzyLRNumber() {
        System.out.println("subtract");
        FuzzyLRNumber operand1 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyLRNumber operand2 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyLRNumber expResult = new FuzzyLRNumber(0.0f, 2.0f, 2.0f);
        FuzzyLRNumber result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        operand1 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(-1.0f, 3.0f, 3.0f);
        result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(1.0f, 3.0f, 3.0f);
        result = instance.subtract(operand2, operand1);
        assertEquals(expResult, result);

        // and negative
        operand1 = new FuzzyLRNumber(-2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(-4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(2.0f, 3.0f, 3.0f);
        result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRNumber(-2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(-4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(-2.0f, 3.0f, 3.0f);
        result = instance.subtract(operand2, operand1);
        assertEquals(expResult, result);

        // and over zero
        operand1 = new FuzzyLRNumber(0.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(0.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(0.0f, 3.0f, 3.0f);
        result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyLRNumber(0.0f, 1.0f, 1.0f);
        operand2 = new FuzzyLRNumber(0.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyLRNumber(0.0f, 3.0f, 3.0f);
        result = instance.subtract(operand2, operand1);
        assertEquals(expResult, result);
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testSubtract_FuzzyLRNumber_FuzzyLRNumber1() {
        System.out.println("subtract");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.subtract(operand1, operand2);
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testSubtract_FuzzyLRNumber_FuzzyLRNumber2() {
        System.out.println("subtract");
        FuzzyLRNumber operand1 = new FuzzyLRNumber(1.0f, 1.0f, 1.0f);
        FuzzyLRNumber operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.subtract(operand1, operand2);
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorImpl.
     */
    @Test
    public final void testSubtract_FuzzyNumber_FuzzyNumber() {
        System.out.println("subtract");
        FuzzyNumber operand1 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyNumber operand2 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        FuzzyNumber expResult = new FuzzyNumber(0.0f, 2.0f, 2.0f);
        FuzzyNumber result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        operand1 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(-1.0f, 3.0f, 3.0f);
        result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(2.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(1.0f, 3.0f, 3.0f);
        result = instance.subtract(operand2, operand1);
        assertEquals(expResult, result);

        // and negative
        operand1 = new FuzzyNumber(-2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(-4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(2.0f, 3.0f, 3.0f);
        result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyNumber(-2.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(-4.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(-2.0f, 3.0f, 3.0f);
        result = instance.subtract(operand2, operand1);
        assertEquals(expResult, result);

        // and over zero
        operand1 = new FuzzyNumber(0.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(0.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(0.0f, 3.0f, 3.0f);
        result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);

        // vice versa
        operand1 = new FuzzyNumber(0.0f, 1.0f, 1.0f);
        operand2 = new FuzzyNumber(0.0f, 2.0f, 2.0f);
        instance = FuzzyCalculatorImpl.getInstance();
        expResult = new FuzzyNumber(0.0f, 3.0f, 3.0f);
        result = instance.subtract(operand2, operand1);
        assertEquals(expResult, result);
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testSubtract_FuzzyNumber_FuzzyNumber1() {
        System.out.println("subtract");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.subtract(operand1, operand2);
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorImpl.
     */
    @Test(expected = NullPointerException.class)
    public final void testSubtract_FuzzyNumber_FuzzyNumber2() {
        System.out.println("subtract");
        FuzzyNumber operand1 = new FuzzyNumber(1.0f, 1.0f, 1.0f);
        FuzzyNumber operand2 = null;
        FuzzyCalculator instance = FuzzyCalculatorImpl.getInstance();
        instance.subtract(operand1, operand2);
    }
}