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
 * Test of class MembershipFunctionPoint.
 *
 * @author Uwe Weng
 */
public class MembershipFunctionPointTest {

    /**
     * Test of getDegreeOfMembership method, of class MembershipFunctionPoint.
     */
    @Test
    public final void testGetDegreeOfMembership() {
        System.out.println("getDegreeOfMembership");
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        float expResult = 0.0F;
        float result = instance.getDegreeOfMembership();
        assertEquals(expResult, result, 0.0F);

        // Test via constructor
        expResult = 1.0F;
        instance = new MembershipFunctionPoint(0.0F, expResult);
        result = instance.getDegreeOfMembership();
        assertEquals(expResult, result, 0.0F);
    }

    /**
     * Test of getX method, of class MembershipFunctionPoint.
     */
    @Test
    public final void testGetX() {
        System.out.println("getX");
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        float expResult = Float.NaN; // initial value
        float result = instance.getX();
        assertEquals(expResult, result, 0.0F);

        // Test via constructor
        expResult = 1.0F;
        instance = new MembershipFunctionPoint(expResult, 0.0F);
        result = instance.getX();
        assertEquals(expResult, result, 0.0F);
    }

    /**
     * Test of setDegreeOfMembership method, of class MembershipFunctionPoint.
     */
    @Test
    public final void testSetDegreeOfMembership() {
        System.out.println("setDegreeOfMembership");
        float aDegreeOfMembership = 0.0F;
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        float expResult = 0.0F;
        float result = instance.setDegreeOfMembership(aDegreeOfMembership);
        assertEquals(expResult, result, 0.0F);

        // Test via constructor
        expResult = 1.0F;
        instance = new MembershipFunctionPoint(0.0F, expResult);
        result = instance.setDegreeOfMembership(0.0F);
        assertEquals(expResult, result, 0.0F);
    }

    /**
     * Test of setDegreeOfMembership method, of class MembershipFunctionPoint.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetDegreeOfMembership1() {
        System.out.println("setDegreeOfMembership");
        float aDegreeOfMembership = Float.NaN;
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        instance.setDegreeOfMembership(aDegreeOfMembership);
    }

    /**
     * Test of setDegreeOfMembership method, of class MembershipFunctionPoint.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetDegreeOfMembership2() {
        System.out.println("setDegreeOfMembership");
        float aDegreeOfMembership = -1.0F - Float.NEGATIVE_INFINITY;
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        instance.setDegreeOfMembership(aDegreeOfMembership);
    }

    /**
     * Test of setDegreeOfMembership method, of class MembershipFunctionPoint.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetDegreeOfMembership3() {
        System.out.println("setDegreeOfMembership");
        float aDegreeOfMembership = 1.0F + Float.POSITIVE_INFINITY;
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        instance.setDegreeOfMembership(aDegreeOfMembership);
    }

    /**
     * Test of clone method, of class MembershipFunctionPoint.
     */
    @Test
    public final void testClone() {
        System.out.println("clone");
        MembershipFunctionPoint instance = new MembershipFunctionPoint(1.0f, 1.0f);
        Object expResult = new MembershipFunctionPoint(1.0f, 1.0f);
        Object result = instance.clone();
        assertEquals(expResult, result);

        // Degree of Membership at not defined x value
        instance = new MembershipFunctionPoint();
        expResult = new MembershipFunctionPoint();
        result = instance.clone();
        assertTrue(!expResult.equals(result));
    }

    /**
     * Test of equals method, of class MembershipFunctionPoint.
     */
    @Test
    public final void testEquals() {
        System.out.println("equals");
        Object obj = null;
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

        // Degree of Membership at not defined x value
        obj = new MembershipFunctionPoint();
        instance = new MembershipFunctionPoint();
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        // Membership point with identical x value and degree of membership
        obj = new MembershipFunctionPoint(1.0f, 0.0f);
        instance = new MembershipFunctionPoint(1.0f, 0.0f);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        // Membership point with identical x value but different degree of membership
        obj = new MembershipFunctionPoint(1.0f, 0.0f);
        instance = new MembershipFunctionPoint(1.0f, 1.0f);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class MembershipFunctionPoint.
     */
    @Test
    public final void testHashCode() {
        System.out.println("hashCode");
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        int expResult = new MembershipFunctionPoint().hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new MembershipFunctionPoint(1.0f, 1.0f);
        expResult = new MembershipFunctionPoint(1.0f, 1.0f).hashCode();
        result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new MembershipFunctionPoint(1.0f, 1.0f);
        expResult = new MembershipFunctionPoint(1.0f, 0.9f).hashCode();
        result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class MembershipFunctionPoint.
     */
    @Test
    public final void testToString() {
        System.out.println("toString");
        MembershipFunctionPoint instance = new MembershipFunctionPoint();
        String result = instance.toString();
        assertNotNull(result);
    }
}