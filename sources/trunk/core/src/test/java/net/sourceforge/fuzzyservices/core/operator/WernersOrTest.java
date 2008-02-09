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
 * Test of class WernersOr.
 *
 * @author Uwe Weng
 */
public class WernersOrTest {

    public WernersOrTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of combine method, of class WernersOr.
     */
    @Test
    public void combine() {
        System.out.println("combine");
        FuzzySet fs1 = null;
        FuzzySet fs2 = null;
        WernersOr instance = new WernersOr();
        FuzzySet expResult = null;
        FuzzySet result = instance.combine(fs1, fs2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTNorm method, of class WernersOr.
     */
    @Test
    public void isValidTNorm() {
        System.out.println("isValidTNorm");
        WernersOr instance = new WernersOr();
        assertEquals(false, instance.isValidTNorm());
        instance.setParameter(0.0f);
        assertEquals(false, instance.isValidTNorm());
        instance.setParameter(1.0f);
        assertEquals(false, instance.isValidTNorm());
    }

    /**
     * Test of isValidSNorm method, of class WernersOr.
     */
    @Test
    public void isValidSNorm() {
        System.out.println("isValidSNorm");
        WernersOr instance = new WernersOr();
        instance.setParameter(0.0f);
        assertEquals(false, instance.isValidSNorm());
        instance.setParameter(1.0f);
        assertEquals(true, instance.isValidSNorm());
    }

    /**
     * Test of isValidParameter method, of class WernersOr.
     */
    @Test
    public void isValidParameter() {
        System.out.println("isValidParameter");
        WernersOr instance = new WernersOr();
        assertEquals(true, instance.isValidParameter(0.0f));
        assertEquals(true, instance.isValidParameter(0.5f));
        assertEquals(true, instance.isValidParameter(1.0f));
        assertEquals(false, instance.isValidParameter(0.0f - FuzzyManager.getStepwidth()));
        assertEquals(false, instance.isValidParameter(1.0f + FuzzyManager.getStepwidth()));
    }

    /**
     * Test of compute method, of class WernersOr.
     */
    @Test
    public void compute() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        WernersOr instance = new WernersOr();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class WernersOr.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        boolean withParameter = false;
        WernersOr instance = new WernersOr();
        String result = instance.toString(withParameter);
        assertNotNull(result);
    }

    /**
     * Test of getDefaultParameter method, of class WernersOr.
     */
    @Test
    public void getDefaultParameter() {
        System.out.println("getDefaultParameter");
        WernersOr instance = new WernersOr();
        float result = instance.getDefaultParameter();
        assertTrue((0.0f <= result) && (result <= 1.0f));
    }
}
