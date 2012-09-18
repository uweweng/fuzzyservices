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
package net.sourceforge.fuzzyservices.core.operator;

import net.sourceforge.fuzzyservices.utils.FuzzyManager;
import net.sourceforge.fuzzyservices.core.FuzzySet;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test of class BoundedDifference.
 *
 * @author Uwe Weng
 */
public class BoundedDifferenceTest {

    /**
     * Test of combine method, of class BoundedDifference.
     */
    @Test
    public final void testCombine() {
        System.out.println("combine");
        FuzzySet fs1 = null;
        FuzzySet fs2 = null;
        BoundedDifference instance = new BoundedDifference();
        FuzzySet expResult = null;
        FuzzySet result = instance.combine(fs1, fs2);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidTNorm method, of class BoundedDifference.
     */
    @Test
    public final void testIsValidTNorm() {
        System.out.println("isValidTNorm");
        BoundedDifference instance = new BoundedDifference();
        boolean expResult = true;
        boolean result = instance.isValidTNorm();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidSNorm method, of class BoundedDifference.
     */
    @Test
    public final void testIsValidSNorm() {
        System.out.println("isValidSNorm");
        BoundedDifference instance = new BoundedDifference();
        boolean expResult = false;
        boolean result = instance.isValidSNorm();
        assertEquals(expResult, result);
    }

    /**
     * Test of compute method, of class BoundedDifference.
     */
    @Test
    public final void compute() {
        System.out.println("compute");
        float a = 0.0F;
        float b = 0.0F;
        BoundedDifference instance = new BoundedDifference();
        float expResult = 0.0F;
        float result = instance.compute(a, b);
        assertEquals(expResult, result, FuzzyManager.getDelta(expResult));
    }

    /**
     * Test of toString method, of class BoundedDifference.
     */
    @Test
    public final void testToString() {
        System.out.println("toString");
        BoundedDifference instance = new BoundedDifference();
        String result = instance.toString();
        assertNotNull(result);
    }

    /**
     * Test of getName method, of class BoundedDifference.
     */
    @Test
    public final void testGetName() {
        System.out.println("getName");
        BoundedDifference instance = new BoundedDifference();
        String result = instance.getName();
        assertNotNull(result);
    }
}
