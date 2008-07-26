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
 * Test of class AntecedentTest.
 *
 * @author Uwe Weng
 */
public class AntecedentTest {

    /**
     * Test of getCompatibilityOperator method, of class Antecedent.
     */
    @Test
    public void testGetCompatibilityOperator() {
        System.out.println("getCompatibilityOperator");
        Antecedent instance = null;
        AbstractOperator expResult = null;
        AbstractOperator result = instance.getCompatibilityOperator();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultCompatibilityOperator method, of class Antecedent.
     */
    @Test
    public void testGetDefaultCompatibilityOperator() {
        System.out.println("getDefaultCompatibilityOperator");
        AbstractOperator expResult = null;
        AbstractOperator result = Antecedent.getDefaultCompatibilityOperator();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCompatibilityOperator method, of class Antecedent.
     */
    @Test
    public void testSetCompatibilityOperator() {
        System.out.println("setCompatibilityOperator");
        AbstractOperator compOp = null;
        Antecedent instance = null;
        instance.setCompatibilityOperator(compOp);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDefaultCompatibilityOperator method, of class Antecedent.
     */
    @Test
    public void testSetDefaultCompatibilityOperator() {
        System.out.println("setDefaultCompatibilityOperator");
        AbstractOperator compOp = null;
        Antecedent.setDefaultCompatibilityOperator(compOp);
        fail("The test case is a prototype.");
    }

}