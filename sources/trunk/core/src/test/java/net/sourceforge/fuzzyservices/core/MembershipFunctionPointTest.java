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
 * Test of class MembershipFunctionPointTest.
 *
 * @author Uwe Weng
 */
public class MembershipFunctionPointTest {

    /**
     * Test of getDegreeOfMembership method, of class MembershipFunctionPoint.
     */
    @Test
    public void testGetDegreeOfMembership() {
        System.out.println("getDegreeOfMembership");
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        float expResult = 0.0F;
        float result = instance.getDegreeOfMembership();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class MembershipFunctionPoint.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        float expResult = 0.0F;
        float result = instance.getX();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDegreeOfMembership method, of class MembershipFunctionPoint.
     */
    @Test
    public void testSetDegreeOfMembership() {
        System.out.println("setDegreeOfMembership");
        float aDegreeOfMembership = 0.0F;
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        float expResult = 0.0F;
        float result = instance.setDegreeOfMembership(aDegreeOfMembership);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class MembershipFunctionPoint.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class MembershipFunctionPoint.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class MembershipFunctionPoint.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class MembershipFunctionPoint.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}