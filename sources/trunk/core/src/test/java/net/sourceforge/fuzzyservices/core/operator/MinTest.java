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
package net.sourceforge.fuzzyservices.core.operator;

import net.sourceforge.fuzzyservices.utils.FuzzyManager;
import net.sourceforge.fuzzyservices.core.FuzzySet;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test of class Min.
 *
 * @author Uwe Weng
 */
public class MinTest {

    /**
     * Test of combine method, of class Min.
     */
    @Test
    public final void combine() {
        FuzzySet fs1 = null;
        FuzzySet fs2 = null;
        Min instance = new Min();
        FuzzySet expResult = null;
        FuzzySet result = instance.combine(fs1, fs2);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidTNorm method, of class Min.
     */
    @Test
    public final void isValidTNorm() {
        Min instance = new Min();
        boolean expResult = true;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidSNorm method, of class Min.
     */
    @Test
    public final void isValidSNorm() {
        Min instance = new Min();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
    }

    /**
     * Test of compute method, of class Min.
     */
    @Test
    public final void compute() {
        float a = 0.0F;
        float b = 0.0F;
        Min instance = new Min();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result, FuzzyManager.getDelta(expResult));
    }

    /**
     * Test of toString method, of class Min.
     */
    @Test
    public final void testToString() {
        Min instance = new Min();
        String result = instance.toString();
        assertNotNull(result);
    }

    /**
     * Test of isValidTNorm method, of class Min.
     */
    @Test
    public void testIsValidTNorm() {
        System.out.println("isValidTNorm");
        Min instance = new Min();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidSNorm method, of class Min.
     */
    @Test
    public void testIsValidSNorm() {
        System.out.println("isValidSNorm");
        Min instance = new Min();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compute method, of class Min.
     */
    @Test
    public void testCompute() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        Min instance = new Min();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Min.
     */
    @Test
    public void testToString1() {
        System.out.println("toString");
        Min instance = new Min();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Min.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Min instance = new Min();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTNorm method, of class Min.
     */
    @Test
    public void testIsValidTNorm1() {
        System.out.println("isValidTNorm");
        Min instance = new Min();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidSNorm method, of class Min.
     */
    @Test
    public void testIsValidSNorm1() {
        System.out.println("isValidSNorm");
        Min instance = new Min();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compute method, of class Min.
     */
    @Test
    public void testCompute_float_float() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        Min instance = new Min();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Min.
     */
    @Test
    public void testToString2() {
        System.out.println("toString");
        Min instance = new Min();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Min.
     */
    @Test
    public void testGetName1() {
        System.out.println("getName");
        Min instance = new Min();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTNorm method, of class Min.
     */
    @Test
    public void testIsValidTNorm2() {
        System.out.println("isValidTNorm");
        Min instance = new Min();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidSNorm method, of class Min.
     */
    @Test
    public void testIsValidSNorm2() {
        System.out.println("isValidSNorm");
        Min instance = new Min();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of compute method, of class Min.
     */
    @Test
    public void testCompute_float_float_2args() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        Min instance = new Min();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Min.
     */
    @Test
    public void testToString3() {
        System.out.println("toString");
        Min instance = new Min();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Min.
     */
    @Test
    public void testGetName2() {
        System.out.println("getName");
        Min instance = new Min();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTNorm method, of class Min.
     */
    @Test
    public void testIsValidTNorm3() {
        System.out.println("isValidTNorm");
        Min instance = new Min();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidSNorm method, of class Min.
     */
    @Test
    public void testIsValidSNorm3() {
        System.out.println("isValidSNorm");
        Min instance = new Min();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of compute method, of class Min.
     */
    @Test
    public void testCompute_2args_1() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        Min instance = new Min();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Min.
     */
    @Test
    public void testToString4() {
        System.out.println("toString");
        Min instance = new Min();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Min.
     */
    @Test
    public void testGetName3() {
        System.out.println("getName");
        Min instance = new Min();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTNorm method, of class Min.
     */
    @Test
    public void testIsValidTNorm4() {
        System.out.println("isValidTNorm");
        Min instance = new Min();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidSNorm method, of class Min.
     */
    @Test
    public void testIsValidSNorm4() {
        System.out.println("isValidSNorm");
        Min instance = new Min();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of compute method, of class Min.
     */
    @Test
    public void testCompute_2args_2() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        Min instance = new Min();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Min.
     */
    @Test
    public void testToString5() {
        System.out.println("toString");
        Min instance = new Min();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Min.
     */
    @Test
    public void testGetName4() {
        System.out.println("getName");
        Min instance = new Min();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
}
