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
package net.sourceforge.fuzzyservices.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class FuzzyManager.
 *
 * @author Uwe Weng
 */
public class FuzzyManagerTest {

    /**
     * Test parameter.
     */
    private static final int MAX_NUM_STEP = 100;
    /**
     * Test parameter.
     */
    private static final float STEP_WIDTH = 0.05f;

    /**
     * Test of getStepwidth method, of class FuzzyManager.
     */
    @Test
    public final void testGetStepwidth() {
        System.out.println("getStepwidth");
        float expResult = STEP_WIDTH;
        FuzzyManager.setStepwidth(expResult);
        float result = FuzzyManager.getStepwidth();
        assertEquals(expResult, result, 0.0f);
    }

    /**
     * Test of setStepwidth method, of class FuzzyManager.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetStepwidth() {
        System.out.println("setStepwidth");
        float width = 0.0F;
        FuzzyManager.setStepwidth(width);
    }

    /**
     * Test of setStepwidth method, of class FuzzyManager.
     */
    @Test
    public final void testSetStepwidth2() {
        System.out.println("setStepwidth");
        float width = STEP_WIDTH;
        FuzzyManager.setStepwidth(width);
        assertEquals(width, FuzzyManager.getStepwidth(), 0.0f);
    }

    /**
     * Test of getMaxNumStep method, of class FuzzyManager.
     */
    @Test
    public final void testGetMaxNumStep() {
        System.out.println("getMaxNumStep");
        int expResult = FuzzyManager.DEFAULT_MAX_NUM_STEP;
        int result = FuzzyManager.getMaxNumStep();
        assertEquals(expResult, result);

        expResult = MAX_NUM_STEP;
        FuzzyManager.setMaxNumStep(expResult);
        result = FuzzyManager.getMaxNumStep();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaxNumStep method, of class FuzzyManager.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetMaxNumStep() {
        System.out.println("setMaxNumStep");
        int max = 0;
        FuzzyManager.setMaxNumStep(max);
    }

    /**
     * Test of setMaxNumStep method, of class FuzzyManager.
     */
    @Test
    public final void testSetMaxNumStep2() {
        System.out.println("setMaxNumStep");
        int max = MAX_NUM_STEP;
        FuzzyManager.setMaxNumStep(max);
        assertEquals(max, FuzzyManager.getMaxNumStep());
    }

    /**
     * Test of round method, of class FuzzyManager.
     */
    @Test
    public final void testRound() {
        System.out.println("round");
        float x = 0.0F;
        float expResult = 0.0F;
        float result = FuzzyManager.round(x);
        assertEquals(expResult, result, FuzzyManager.getDelta(x));

        x = 0.001F;
        result = FuzzyManager.round(x);
        assertEquals(expResult, result, FuzzyManager.getDelta(x));

        x = -0.001F;
        result = FuzzyManager.round(x);
        assertEquals(expResult, result, FuzzyManager.getDelta(x));
    }

    /**
     * Test of getDelta method, of class FuzzyManager.
     */
    @Test
    public final void testGetDelta() {
        System.out.println("getDelta");
        float x = 0.0F;
        float expResult = 0.0F;
        float result = FuzzyManager.getDelta(x);
        assertEquals(expResult, result, FuzzyManager.round(x));
    }
}