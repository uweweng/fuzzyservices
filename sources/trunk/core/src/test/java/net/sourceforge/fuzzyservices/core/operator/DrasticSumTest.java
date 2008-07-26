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
 * Test of class DrasticSum.
 *
 * @author Uwe Weng
 */
public class DrasticSumTest {

    /**
     * Test of combine method, of class DrasticSum.
     */
    @Test
    public final void combine() {
        FuzzySet fs1 = null;
        FuzzySet fs2 = null;
        DrasticSum instance = new DrasticSum();
        FuzzySet expResult = null;
        FuzzySet result = instance.combine(fs1, fs2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDefaultValue method, of class DrasticSum.
     */
    @Test
    public final void getDefaultValue() {
        DrasticSum instance = new DrasticSum();
        float expResult = 1.0F;
        float result = instance.getDefaultValue();
        assertEquals(expResult,
                result,
                FuzzyManager.getDelta(expResult));
    }

    /**
     * Test of getConditionValue method, of class DrasticSum.
     */
    @Test
    public final void getConditionValue() {
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.getConditionValue();
        assertEquals(expResult,
                result,
                FuzzyManager.getDelta(expResult));
    }

    /**
     * Test of isValidTNorm method, of class DrasticSum.
     */
    @Test
    public final void isValidTNorm() {
        DrasticSum instance = new DrasticSum();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidSNorm method, of class DrasticSum.
     */
    @Test
    public final void isValidSNorm() {
        DrasticSum instance = new DrasticSum();
        boolean expResult = true;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
    }

    /**
     * Test of compute method, of class DrasticSum.
     */
    @Test
    public final void compute() {
        float a = 0.0F;
        float b = 0.0F;
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result, FuzzyManager.getDelta(expResult));
    }

    /**
     * Test of toString method, of class DrasticSum.
     */
    @Test
    public final void testToString() {
        DrasticSum instance = new DrasticSum();
        String result = instance.toString();
        assertNotNull(result);
    }

    /**
     * Test of getDefaultValue method, of class DrasticSum.
     */
    @Test
    public void testGetDefaultValue() {
        System.out.println("getDefaultValue");
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.getDefaultValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConditionValue method, of class DrasticSum.
     */
    @Test
    public void testGetConditionValue() {
        System.out.println("getConditionValue");
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.getConditionValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTNorm method, of class DrasticSum.
     */
    @Test
    public void testIsValidTNorm() {
        System.out.println("isValidTNorm");
        DrasticSum instance = new DrasticSum();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidSNorm method, of class DrasticSum.
     */
    @Test
    public void testIsValidSNorm() {
        System.out.println("isValidSNorm");
        DrasticSum instance = new DrasticSum();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compute method, of class DrasticSum.
     */
    @Test
    public void testCompute() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DrasticSum.
     */
    @Test
    public void testToString1() {
        System.out.println("toString");
        DrasticSum instance = new DrasticSum();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class DrasticSum.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DrasticSum instance = new DrasticSum();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultValue method, of class DrasticSum.
     */
    @Test
    public void testGetDefaultValue1() {
        System.out.println("getDefaultValue");
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.getDefaultValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConditionValue method, of class DrasticSum.
     */
    @Test
    public void testGetConditionValue1() {
        System.out.println("getConditionValue");
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.getConditionValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTNorm method, of class DrasticSum.
     */
    @Test
    public void testIsValidTNorm1() {
        System.out.println("isValidTNorm");
        DrasticSum instance = new DrasticSum();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidSNorm method, of class DrasticSum.
     */
    @Test
    public void testIsValidSNorm1() {
        System.out.println("isValidSNorm");
        DrasticSum instance = new DrasticSum();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compute method, of class DrasticSum.
     */
    @Test
    public void testCompute_float_float() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DrasticSum.
     */
    @Test
    public void testToString2() {
        System.out.println("toString");
        DrasticSum instance = new DrasticSum();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class DrasticSum.
     */
    @Test
    public void testGetName1() {
        System.out.println("getName");
        DrasticSum instance = new DrasticSum();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultValue method, of class DrasticSum.
     */
    @Test
    public void testGetDefaultValue2() {
        System.out.println("getDefaultValue");
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.getDefaultValue();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConditionValue method, of class DrasticSum.
     */
    @Test
    public void testGetConditionValue2() {
        System.out.println("getConditionValue");
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.getConditionValue();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTNorm method, of class DrasticSum.
     */
    @Test
    public void testIsValidTNorm2() {
        System.out.println("isValidTNorm");
        DrasticSum instance = new DrasticSum();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidSNorm method, of class DrasticSum.
     */
    @Test
    public void testIsValidSNorm2() {
        System.out.println("isValidSNorm");
        DrasticSum instance = new DrasticSum();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of compute method, of class DrasticSum.
     */
    @Test
    public void testCompute_float_float_2args() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DrasticSum.
     */
    @Test
    public void testToString3() {
        System.out.println("toString");
        DrasticSum instance = new DrasticSum();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class DrasticSum.
     */
    @Test
    public void testGetName2() {
        System.out.println("getName");
        DrasticSum instance = new DrasticSum();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultValue method, of class DrasticSum.
     */
    @Test
    public void testGetDefaultValue3() {
        System.out.println("getDefaultValue");
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.getDefaultValue();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConditionValue method, of class DrasticSum.
     */
    @Test
    public void testGetConditionValue3() {
        System.out.println("getConditionValue");
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.getConditionValue();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTNorm method, of class DrasticSum.
     */
    @Test
    public void testIsValidTNorm3() {
        System.out.println("isValidTNorm");
        DrasticSum instance = new DrasticSum();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidSNorm method, of class DrasticSum.
     */
    @Test
    public void testIsValidSNorm3() {
        System.out.println("isValidSNorm");
        DrasticSum instance = new DrasticSum();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of compute method, of class DrasticSum.
     */
    @Test
    public void testCompute_2args_1() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DrasticSum.
     */
    @Test
    public void testToString4() {
        System.out.println("toString");
        DrasticSum instance = new DrasticSum();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class DrasticSum.
     */
    @Test
    public void testGetName3() {
        System.out.println("getName");
        DrasticSum instance = new DrasticSum();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultValue method, of class DrasticSum.
     */
    @Test
    public void testGetDefaultValue4() {
        System.out.println("getDefaultValue");
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.getDefaultValue();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConditionValue method, of class DrasticSum.
     */
    @Test
    public void testGetConditionValue4() {
        System.out.println("getConditionValue");
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.getConditionValue();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTNorm method, of class DrasticSum.
     */
    @Test
    public void testIsValidTNorm4() {
        System.out.println("isValidTNorm");
        DrasticSum instance = new DrasticSum();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidSNorm method, of class DrasticSum.
     */
    @Test
    public void testIsValidSNorm4() {
        System.out.println("isValidSNorm");
        DrasticSum instance = new DrasticSum();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of compute method, of class DrasticSum.
     */
    @Test
    public void testCompute_2args_2() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        DrasticSum instance = new DrasticSum();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DrasticSum.
     */
    @Test
    public void testToString5() {
        System.out.println("toString");
        DrasticSum instance = new DrasticSum();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class DrasticSum.
     */
    @Test
    public void testGetName4() {
        System.out.println("getName");
        DrasticSum instance = new DrasticSum();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
}
