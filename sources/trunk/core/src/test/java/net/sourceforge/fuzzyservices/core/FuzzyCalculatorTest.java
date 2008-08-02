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
 * Test of class FuzzyCalculatorTest.
 *
 * @author Uwe Weng
 */
public class FuzzyCalculatorTest {

    /**
     * Test of getInstance method, of class FuzzyCalculator.
     */
    @Test
    public final void testGetInstance() {
        System.out.println("getInstance");
        FuzzyCalculator expResult = null;
        FuzzyCalculator result = FuzzyCalculator.getInstance();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class FuzzyCalculator.
     */
    @Test
    public final void testAdd_FuzzyInterval_FuzzyInterval() {
        System.out.println("add");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyInterval expResult = null;
        FuzzyInterval result = instance.add(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class FuzzyCalculator.
     */
    @Test
    public final void testAdd_FuzzyLRInterval_FuzzyLRInterval() {
        System.out.println("add");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyLRInterval expResult = null;
        FuzzyLRInterval result = instance.add(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class FuzzyCalculator.
     */
    @Test
    public final void testAdd_FuzzyLRNumber_FuzzyLRNumber() {
        System.out.println("add");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyLRNumber expResult = null;
        FuzzyLRNumber result = instance.add(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class FuzzyCalculator.
     */
    @Test
    public final void testAdd_FuzzyNumber_FuzzyNumber() {
        System.out.println("add");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyNumber expResult = null;
        FuzzyNumber result = instance.add(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculator.
     */
    @Test
    public final void testDivide_FuzzyInterval_FuzzyInterval() {
        System.out.println("divide");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyInterval expResult = null;
        FuzzyInterval result = instance.divide(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculator.
     */
    @Test
    public final void testDivide_FuzzyLRInterval_FuzzyLRInterval() {
        System.out.println("divide");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyLRInterval expResult = null;
        FuzzyLRInterval result = instance.divide(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculator.
     */
    @Test
    public final void testDivide_FuzzyLRNumber_FuzzyLRNumber() {
        System.out.println("divide");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyLRNumber expResult = null;
        FuzzyLRNumber result = instance.divide(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculator.
     */
    @Test
    public final void testDivide_FuzzyNumber_FuzzyNumber() {
        System.out.println("divide");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyNumber expResult = null;
        FuzzyNumber result = instance.divide(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class FuzzyCalculator.
     */
    @Test
    public final void testMultiply_FuzzyInterval_FuzzyInterval() {
        System.out.println("multiply");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyInterval expResult = null;
        FuzzyInterval result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class FuzzyCalculator.
     */
    @Test
    public final void testMultiply_FuzzyLRInterval_FuzzyLRInterval() {
        System.out.println("multiply");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyLRInterval expResult = null;
        FuzzyLRInterval result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class FuzzyCalculator.
     */
    @Test
    public final void testMultiply_FuzzyLRNumber_FuzzyLRNumber() {
        System.out.println("multiply");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyLRNumber expResult = null;
        FuzzyLRNumber result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class FuzzyCalculator.
     */
    @Test
    public final void testMultiply_FuzzyNumber_FuzzyNumber() {
        System.out.println("multiply");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyNumber expResult = null;
        FuzzyNumber result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class FuzzyCalculator.
     */
    @Test
    public final void testSubtract_FuzzyInterval_FuzzyInterval() {
        System.out.println("subtract");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyInterval expResult = null;
        FuzzyInterval result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class FuzzyCalculator.
     */
    @Test
    public final void testSubtract_FuzzyLRInterval_FuzzyLRInterval() {
        System.out.println("subtract");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyLRInterval expResult = null;
        FuzzyLRInterval result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class FuzzyCalculator.
     */
    @Test
    public final void testSubtract_FuzzyLRNumber_FuzzyLRNumber() {
        System.out.println("subtract");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyLRNumber expResult = null;
        FuzzyLRNumber result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class FuzzyCalculator.
     */
    @Test
    public final void testSubtract_FuzzyNumber_FuzzyNumber() {
        System.out.println("subtract");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = null;
        FuzzyCalculator instance = new FuzzyCalculator();
        FuzzyNumber expResult = null;
        FuzzyNumber result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}