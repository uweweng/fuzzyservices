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
package net.sourceforge.fuzzyservices.ejb3;

import net.sourceforge.fuzzyservices.beans.FuzzyInterval;
import net.sourceforge.fuzzyservices.beans.FuzzyLRInterval;
import net.sourceforge.fuzzyservices.beans.FuzzyLRNumber;
import net.sourceforge.fuzzyservices.beans.FuzzyNumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class FuzzyCalculatorBean.
 *
 * @author Uwe Weng
 */
public class FuzzyCalculatorBeanTest {

    /**
     * Default constructor.
     */
    public FuzzyCalculatorBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of add method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testAdd_2args_1() {
        System.out.println("add");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyInterval expResult = null;
        FuzzyInterval result = instance.add(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testAdd_2args_2() {
        System.out.println("add");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRInterval expResult = null;
        FuzzyLRInterval result = instance.add(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testAdd_2args_3() {
        System.out.println("add");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRNumber expResult = null;
        FuzzyLRNumber result = instance.add(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testAdd_2args_4() {
        System.out.println("add");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyNumber expResult = null;
        FuzzyNumber result = instance.add(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testDivide_2args_1() {
        System.out.println("divide");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyInterval expResult = null;
        FuzzyInterval result = instance.divide(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testDivide_2args_2() {
        System.out.println("divide");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRInterval expResult = null;
        FuzzyLRInterval result = instance.divide(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testDivide_2args_3() {
        System.out.println("divide");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRNumber expResult = null;
        FuzzyLRNumber result = instance.divide(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testDivide_2args_4() {
        System.out.println("divide");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyNumber expResult = null;
        FuzzyNumber result = instance.divide(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testMultiply_2args_1() {
        System.out.println("multiply");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyInterval expResult = null;
        FuzzyInterval result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testMultiply_2args_2() {
        System.out.println("multiply");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRInterval expResult = null;
        FuzzyLRInterval result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testMultiply_2args_3() {
        System.out.println("multiply");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRNumber expResult = null;
        FuzzyLRNumber result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testMultiply_2args_4() {
        System.out.println("multiply");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyNumber expResult = null;
        FuzzyNumber result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testSubtract_2args_1() {
        System.out.println("subtract");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyInterval expResult = null;
        FuzzyInterval result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testSubtract_2args_2() {
        System.out.println("subtract");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRInterval expResult = null;
        FuzzyLRInterval result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testSubtract_2args_3() {
        System.out.println("subtract");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRNumber expResult = null;
        FuzzyLRNumber result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testSubtract_2args_4() {
        System.out.println("subtract");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyNumber expResult = null;
        FuzzyNumber result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testAdd_2args_5() {
        System.out.println("add");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyInterval expResult = null;
        FuzzyInterval result = instance.add(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testAdd_2args_6() {
        System.out.println("add");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRInterval expResult = null;
        FuzzyLRInterval result = instance.add(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testAdd_2args_7() {
        System.out.println("add");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRNumber expResult = null;
        FuzzyLRNumber result = instance.add(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testAdd_2args_8() {
        System.out.println("add");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyNumber expResult = null;
        FuzzyNumber result = instance.add(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testDivide_2args_5() {
        System.out.println("divide");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyInterval expResult = null;
        FuzzyInterval result = instance.divide(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testDivide_2args_6() {
        System.out.println("divide");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRInterval expResult = null;
        FuzzyLRInterval result = instance.divide(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testDivide_2args_7() {
        System.out.println("divide");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRNumber expResult = null;
        FuzzyLRNumber result = instance.divide(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testDivide_2args_8() {
        System.out.println("divide");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyNumber expResult = null;
        FuzzyNumber result = instance.divide(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testMultiply_2args_5() {
        System.out.println("multiply");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyInterval expResult = null;
        FuzzyInterval result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testMultiply_2args_6() {
        System.out.println("multiply");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRInterval expResult = null;
        FuzzyLRInterval result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testMultiply_2args_7() {
        System.out.println("multiply");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRNumber expResult = null;
        FuzzyLRNumber result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testMultiply_2args_8() {
        System.out.println("multiply");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyNumber expResult = null;
        FuzzyNumber result = instance.multiply(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testSubtract_2args_5() {
        System.out.println("subtract");
        FuzzyInterval operand1 = null;
        FuzzyInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyInterval expResult = null;
        FuzzyInterval result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testSubtract_2args_6() {
        System.out.println("subtract");
        FuzzyLRInterval operand1 = null;
        FuzzyLRInterval operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRInterval expResult = null;
        FuzzyLRInterval result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testSubtract_2args_7() {
        System.out.println("subtract");
        FuzzyLRNumber operand1 = null;
        FuzzyLRNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyLRNumber expResult = null;
        FuzzyLRNumber result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class FuzzyCalculatorBean.
     */
    @Test
    public void testSubtract_2args_8() {
        System.out.println("subtract");
        FuzzyNumber operand1 = null;
        FuzzyNumber operand2 = null;
        FuzzyCalculatorBean instance = new FuzzyCalculatorBean();
        FuzzyNumber expResult = null;
        FuzzyNumber result = instance.subtract(operand1, operand2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}