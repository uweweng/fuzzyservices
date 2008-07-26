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
 ******************************************************************************/package net.sourceforge.fuzzyservices.core.defuzzification;

import net.sourceforge.fuzzyservices.core.MembershipFunction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class CenterOfArea.
 *
 * @author Uwe Weng
 */
public class CenterOfAreaTest {

    public CenterOfAreaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of defuzzify method, of class CenterOfArea.
     */
    @Test
    public void testDefuzzify() {
        System.out.println("defuzzify");
        MembershipFunction membershipFunction = null;
        CenterOfArea instance = new CenterOfArea();
        float expResult = 0.0F;
        float result = instance.defuzzify(membershipFunction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class CenterOfArea.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CenterOfArea instance = new CenterOfArea();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of defuzzify method, of class CenterOfArea.
     */
    @Test
    public void testDefuzzify_MembershipFunction() {
        System.out.println("defuzzify");
        MembershipFunction membershipFunction = null;
        CenterOfArea instance = new CenterOfArea();
        float expResult = 0.0F;
        float result = instance.defuzzify(membershipFunction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class CenterOfArea.
     */
    @Test
    public void testToString1() {
        System.out.println("toString");
        CenterOfArea instance = new CenterOfArea();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of defuzzify method, of class CenterOfArea.
     */
    @Test
    public void testDefuzzify_MembershipFunction_1args() {
        System.out.println("defuzzify");
        MembershipFunction membershipFunction = null;
        CenterOfArea instance = new CenterOfArea();
        float expResult = 0.0F;
        float result = instance.defuzzify(membershipFunction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class CenterOfArea.
     */
    @Test
    public void testToString2() {
        System.out.println("toString");
        CenterOfArea instance = new CenterOfArea();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}