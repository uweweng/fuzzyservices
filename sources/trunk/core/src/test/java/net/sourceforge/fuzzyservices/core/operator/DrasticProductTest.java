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

import net.sourceforge.fuzzyservices.core.FuzzyManager;
import net.sourceforge.fuzzyservices.core.FuzzySet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class DrasticProduct.
 *
 * @author Uwe Weng
 */
public class DrasticProductTest {

    public DrasticProductTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of combine method, of class DrasticProduct.
     */
    @Test
    public void combine() {
        System.out.println("combine");
        FuzzySet fs1 = null;
        FuzzySet fs2 = null;
        DrasticProduct instance = new DrasticProduct();
        FuzzySet expResult = null;
        FuzzySet result = instance.combine(fs1, fs2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of getDefaultValue method, of class DrasticProduct.
     */
    @Test
    public void getDefaultValue() {
        System.out.println("getDefaultValue");
        DrasticProduct instance = new DrasticProduct();
        float expResult = 0.0F;
        float result = instance.getDefaultValue();
        assertEquals(expResult, result, FuzzyManager.getDelta(result));
    }

    /**
     * Test of getConditionValue method, of class DrasticProduct.
     */
    @Test
    public void getConditionValue() {
        System.out.println("getConditionValue");
        DrasticProduct instance = new DrasticProduct();
        float expResult = 1.0F;
        float result = instance.getConditionValue();
        assertEquals(expResult, result, FuzzyManager.getDelta(result));
    }

    /**
     * Test of isValidTNorm method, of class DrasticProduct.
     */
    @Test
    public void isValidTNorm() {
        System.out.println("isValidTNorm");
        DrasticProduct instance = new DrasticProduct();
        boolean expResult = true;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidSNorm method, of class DrasticProduct.
     */
    @Test
    public void isValidSNorm() {
        System.out.println("isValidSNorm");
        DrasticProduct instance = new DrasticProduct();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
    }

    /**
     * Test of compute method, of class DrasticProduct.
     */
    @Test
    public void compute() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        DrasticProduct instance = new DrasticProduct();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DrasticProduct.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DrasticProduct instance = new DrasticProduct();
        String result = instance.toString();
        assertNotNull(result);
    }

}