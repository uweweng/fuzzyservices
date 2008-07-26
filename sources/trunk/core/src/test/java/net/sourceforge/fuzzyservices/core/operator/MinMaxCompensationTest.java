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

import org.junit.AfterClass;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test of class MinMaxCompensation.
 *
 * @author Uwe Weng
 */
public class MinMaxCompensationTest {

    @BeforeClass
    public static void setUpClass()
            throws Exception {
    }

    @AfterClass
    public static void tearDownClass()
            throws Exception {
    }

    /**
     * Test of combine method, of class MinMaxCompensation.
     */
    @Test
    public final void combine() {
        FuzzySet fs1 = null;
        FuzzySet fs2 = null;
        MinMaxCompensation instance = new MinMaxCompensation();
        FuzzySet expResult = null;
        FuzzySet result = instance.combine(fs1, fs2);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidTNorm method, of class MinMaxCompensation.
     */
    @Test
    public final void isValidTNorm() {
        MinMaxCompensation instance = new MinMaxCompensation();
        instance.setParameter(0.0f);
        assertEquals(true,
                instance.isValidTNorm());
        instance.setParameter(0.4f);
        assertEquals(true,
                instance.isValidTNorm());
        instance.setParameter(0.5f);
        assertEquals(false,
                instance.isValidTNorm());
        instance.setParameter(0.6f);
        assertEquals(false,
                instance.isValidTNorm());
        instance.setParameter(1.0f);
        assertEquals(false,
                instance.isValidTNorm());
    }

    /**
     * Test of isValidSNorm method, of class MinMaxCompensation.
     */
    @Test
    public final void isValidSNorm() {
        MinMaxCompensation instance = new MinMaxCompensation();
        instance.setParameter(0.0f);
        assertEquals(false,
                instance.isValidSNorm());
        instance.setParameter(0.4f);
        assertEquals(false,
                instance.isValidSNorm());
        instance.setParameter(0.5f);
        assertEquals(false,
                instance.isValidSNorm());
        instance.setParameter(0.6f);
        assertEquals(true,
                instance.isValidSNorm());
        instance.setParameter(1.0f);
        assertEquals(true,
                instance.isValidSNorm());
    }

    /**
     * Test of isValidParameter method, of class MinMaxCompensation.
     */
    @Test
    public final void isValidParameter() {
        MinMaxCompensation instance = new MinMaxCompensation();
        assertEquals(true,
                instance.isValidParameter(0.0f));
        assertEquals(true,
                instance.isValidParameter(0.5f));
        assertEquals(true,
                instance.isValidParameter(1.0f));
        assertEquals(false,
                instance.isValidParameter(0.0f - FuzzyManager.getStepwidth()));
        assertEquals(false,
                instance.isValidParameter(1.0f + FuzzyManager.getStepwidth()));
    }

    /**
     * Test of compute method, of class MinMaxCompensation.
     */
    @Test
    public final void compute() {
        float a = 0.0F;
        float b = 0.0F;
        MinMaxCompensation instance = new MinMaxCompensation();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result, FuzzyManager.getDelta(expResult));
    }

    /**
     * Test of toString method, of class MinMaxCompensation.
     */
    @Test
    public final void testToString() {
        boolean withParameter = false;
        MinMaxCompensation instance = new MinMaxCompensation();
        String result = instance.toString(withParameter);
        assertNotNull(result);
    }

    /**
     * Test of getDefaultParameter method, of class MinMaxCompensation.
     */
    @Test
    public final void getDefaultParameter() {
        MinMaxCompensation instance = new MinMaxCompensation();
        float result = instance.getDefaultParameter();
        assertTrue((0.0f <= result) && (result <= 1.0f));
    }

    /**
     * Test of combine method, of class MinMaxCompensation.
     */
    @Test
    public void testCombine() {
        System.out.println("combine");
        FuzzySet fs1 = null;
        FuzzySet fs2 = null;
        MinMaxCompensation instance = new MinMaxCompensation();
        FuzzySet expResult = null;
        FuzzySet result = instance.combine(fs1, fs2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTNorm method, of class MinMaxCompensation.
     */
    @Test
    public void testIsValidTNorm() {
        System.out.println("isValidTNorm");
        MinMaxCompensation instance = new MinMaxCompensation();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidSNorm method, of class MinMaxCompensation.
     */
    @Test
    public void testIsValidSNorm() {
        System.out.println("isValidSNorm");
        MinMaxCompensation instance = new MinMaxCompensation();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidParameter method, of class MinMaxCompensation.
     */
    @Test
    public void testIsValidParameter() {
        System.out.println("isValidParameter");
        float param = 0.0F;
        MinMaxCompensation instance = new MinMaxCompensation();
        boolean expResult = false;
        boolean result = instance.isValidParameter(param);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compute method, of class MinMaxCompensation.
     */
    @Test
    public void testCompute() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        MinMaxCompensation instance = new MinMaxCompensation();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class MinMaxCompensation.
     */
    @Test
    public void testToString_boolean() {
        System.out.println("toString");
        boolean withParameter = false;
        MinMaxCompensation instance = new MinMaxCompensation();
        String expResult = "";
        String result = instance.toString(withParameter);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultParameter method, of class MinMaxCompensation.
     */
    @Test
    public void testGetDefaultParameter() {
        System.out.println("getDefaultParameter");
        MinMaxCompensation instance = new MinMaxCompensation();
        float expResult = 0.0F;
        float result = instance.getDefaultParameter();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class MinMaxCompensation.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        MinMaxCompensation instance = new MinMaxCompensation();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of combine method, of class MinMaxCompensation.
     */
    @Test
    public void testCombine_FuzzySet_FuzzySet() {
        System.out.println("combine");
        FuzzySet fs1 = null;
        FuzzySet fs2 = null;
        MinMaxCompensation instance = new MinMaxCompensation();
        FuzzySet expResult = null;
        FuzzySet result = instance.combine(fs1, fs2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTNorm method, of class MinMaxCompensation.
     */
    @Test
    public void testIsValidTNorm1() {
        System.out.println("isValidTNorm");
        MinMaxCompensation instance = new MinMaxCompensation();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidSNorm method, of class MinMaxCompensation.
     */
    @Test
    public void testIsValidSNorm1() {
        System.out.println("isValidSNorm");
        MinMaxCompensation instance = new MinMaxCompensation();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidParameter method, of class MinMaxCompensation.
     */
    @Test
    public void testIsValidParameter_float() {
        System.out.println("isValidParameter");
        float param = 0.0F;
        MinMaxCompensation instance = new MinMaxCompensation();
        boolean expResult = false;
        boolean result = instance.isValidParameter(param);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compute method, of class MinMaxCompensation.
     */
    @Test
    public void testCompute_float_float() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        MinMaxCompensation instance = new MinMaxCompensation();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class MinMaxCompensation.
     */
    @Test
    public void testToString_boolean_1args() {
        System.out.println("toString");
        boolean withParameter = false;
        MinMaxCompensation instance = new MinMaxCompensation();
        String expResult = "";
        String result = instance.toString(withParameter);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultParameter method, of class MinMaxCompensation.
     */
    @Test
    public void testGetDefaultParameter1() {
        System.out.println("getDefaultParameter");
        MinMaxCompensation instance = new MinMaxCompensation();
        float expResult = 0.0F;
        float result = instance.getDefaultParameter();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class MinMaxCompensation.
     */
    @Test
    public void testGetName1() {
        System.out.println("getName");
        MinMaxCompensation instance = new MinMaxCompensation();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
