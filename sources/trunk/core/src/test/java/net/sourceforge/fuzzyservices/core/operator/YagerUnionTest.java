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

import net.sourceforge.fuzzyservices.core.FuzzySet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class YagerUnion.
 *
 * @author Uwe Weng
 */
public class YagerUnionTest {

    public YagerUnionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of combine method, of class YagerUnion.
     */
    @Test
    public void combine() {
        System.out.println("combine");
        FuzzySet fs1 = null;
        FuzzySet fs2 = null;
        YagerUnion instance = new YagerUnion();
        FuzzySet expResult = null;
        FuzzySet result = instance.combine(fs1, fs2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTNorm method, of class YagerUnion.
     */
    @Test
    public void isValidTNorm() {
        System.out.println("isValidTNorm");
        YagerUnion instance = new YagerUnion();
        boolean expResult = false;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidSNorm method, of class YagerUnion.
     */
    @Test
    public void isValidSNorm() {
        System.out.println("isValidSNorm");
        YagerUnion instance = new YagerUnion();
        boolean expResult = true;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidParameter method, of class YagerUnion.
     */
    @Test
    public void isValidParameter() {
        System.out.println("isValidParameter");
        YagerUnion instance = new YagerUnion();
        assertEquals(false, instance.isValidParameter(0.0f));
        assertEquals(true, instance.isValidParameter(1.0f));
    }

    /**
     * Test of compute method, of class YagerUnion.
     */
    @Test
    public void compute() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        YagerUnion instance = new YagerUnion();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class YagerUnion.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        boolean withParameter = false;
        YagerUnion instance = new YagerUnion();
        String result = instance.toString(withParameter);
        assertNotNull(result);
    }

    /**
     * Test of getDefaultParameter method, of class YagerUnion.
     */
    @Test
    public void getDefaultParameter() {
        System.out.println("getDefaultParameter");
        YagerUnion instance = new YagerUnion();
        float result = instance.getDefaultParameter();
        assertTrue(result >= 1.0f);
    }

}