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
package net.sourceforge.fuzzyservices.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class DiscreteFuzzySet.
 *
 * @author Uwe Weng
 */
public class DiscreteFuzzySetTest {

    /**
     * Test of add method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testAdd() {
        System.out.println("add");
        Object expResult = new Object();
        float dom = 1.0F;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.add(expResult, dom);
        Float result = instance.getDegreeOfMembership(expResult);
        assertEquals(result, result, 0.0F);

        // What will happen when degree of membership is 0.0?
        dom = 0.1F;
        instance.add(expResult, dom);
        result = instance.getDegreeOfMembership(expResult);
        assertEquals(result, dom, 0.0F);

        // What will happen when object is null?
        dom = 1.0F;
        instance.add(null, dom);
        result = instance.getDegreeOfMembership(null);
        assertEquals(result, 0.0F, 0.0F);

    }

    /**
     * Test of add method, of class DiscreteFuzzySet.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testAdd1() {
        System.out.println("add");
        Object expResult = new Object();
        float dom = 1.0F + Float.POSITIVE_INFINITY;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.add(expResult, dom);
    }

    /**
     * Test of add method, of class DiscreteFuzzySet.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testAdd2() {
        System.out.println("add");
        Object expResult = new Object();
        float dom = 0.0F - Float.NEGATIVE_INFINITY;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.add(expResult, dom);
    }

    /**
     * Test of clear method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testClear() {
        System.out.println("clear");
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.clear();

        instance.add(new Object(), 1.0F);
        instance.clear();
        int size = instance.size();
        assertEquals(size, 0);
    }

    /**
     * Test of combine method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testCombine() {
        System.out.println("combine");
        DiscreteFuzzySet<Object> dfs = null;
        AbstractOperator op = null;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        DiscreteFuzzySet<Object> expResult = null;
        DiscreteFuzzySet<Object> result = instance.combine(dfs, op);
        assertEquals(expResult, result);
    // @todo Fail("The test case is a prototype.");
    }

    /**
     * Test of concentrate method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testConcentrate() {
        System.out.println("concentrate");
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.concentrate();

        Object obj1 = new Object();
        float degreeOfMembershipOfObj1 = 0.0f;
        float expResult = 0.0f;
        instance = new DiscreteFuzzySet<Object>();
        instance.add(obj1, degreeOfMembershipOfObj1);
        instance.concentrate();
        assertEquals(expResult, instance.getDegreeOfMembership(obj1), 0.0f);

        obj1 = new Object();
        degreeOfMembershipOfObj1 = 1.0f;
        expResult = 1.0f;
        instance = new DiscreteFuzzySet<Object>();
        instance.add(obj1, degreeOfMembershipOfObj1);
        instance.concentrate();
        assertEquals(expResult, instance.getDegreeOfMembership(obj1), 0.0f);

        obj1 = new Object();
        degreeOfMembershipOfObj1 = 0.6f;
        expResult = 0.36f;
        instance = new DiscreteFuzzySet<Object>();
        instance.add(obj1, degreeOfMembershipOfObj1);
        instance.concentrate();
        assertEquals(expResult, instance.getDegreeOfMembership(obj1), 0.0f);
    }

    /**
     * Test of contains method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testContains() {
        System.out.println("contains");
        Object obj = null;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        boolean expResult = false;
        boolean result = instance.contains(obj);
        assertEquals(expResult, result);

        obj = new Object();
        expResult = false;
        instance.add(obj, 0.0F);
        result = instance.contains(obj);
        assertEquals(expResult, result);

        obj = new Object();
        expResult = true;
        instance.add(obj, 1.0F);
        result = instance.contains(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of dilate method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testDilate() {
        System.out.println("dilate");
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.dilate();

        Object obj1 = new Object();
        float degreeOfMembershipOfObj1 = 0.0f;
        float expResult = 0.0f;
        instance = new DiscreteFuzzySet<Object>();
        instance.add(obj1, degreeOfMembershipOfObj1);
        instance.dilate();
        assertEquals(expResult, instance.getDegreeOfMembership(obj1), 0.0f);

        obj1 = new Object();
        degreeOfMembershipOfObj1 = 1.0f;
        expResult = 1.0f;
        instance = new DiscreteFuzzySet<Object>();
        instance.add(obj1, degreeOfMembershipOfObj1);
        instance.dilate();
        assertEquals(expResult, instance.getDegreeOfMembership(obj1), 0.0f);

        obj1 = new Object();
        degreeOfMembershipOfObj1 = 0.3f;
        expResult = 0.55f;
        instance = new DiscreteFuzzySet<Object>();
        instance.add(obj1, degreeOfMembershipOfObj1);
        instance.dilate();
        assertEquals(expResult, instance.getDegreeOfMembership(obj1), 0.0f);
    }

    /**
     * Test of iterator method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testIterator() {
        System.out.println("iterator");
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        Iterator<Object> result = instance.iterator();
        assertNotNull(result);
    }

    /**
     * Test of getAlphaCut method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testGetAlphaCut() {
        System.out.println("getAlphaCut");
        float alpha = 0.0F;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        Collection<Object> expResult = new ArrayList<Object>();
        Collection<Object> result = instance.getAlphaCut(alpha);
        assertNotNull(result);

        alpha = 0.6f;
        instance = new DiscreteFuzzySet<Object>();
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        instance.add(obj1, alpha + 0.1f);
        instance.add(obj2, alpha);
        instance.add(obj3, alpha - 0.1f);
        expResult = new HashSet<Object>();
        expResult.add(obj2);
        expResult.add(obj1);
        result = instance.getAlphaCut(alpha);
        assertEquals(expResult, result);
        assertEquals(expResult.size(), 2);
    }

    /**
     * Test of getAlphaCut method, of class DiscreteFuzzySet.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testGetAlphaCut1() {
        System.out.println("getAlphaCut");
        float alpha = 0.0F - Float.POSITIVE_INFINITY;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.getAlphaCut(alpha);
    }

    /**
     * Test of getAlphaCut method, of class DiscreteFuzzySet.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testGetAlphaCut2() {
        System.out.println("getAlphaCut");
        float alpha = 1.0F + Float.POSITIVE_INFINITY;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.getAlphaCut(alpha);
    }

    /**
     * Test of getStrictAlphaCut method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testGetStrictAlphaCut() {
        System.out.println("getStrictAlphaCut");
        float alpha = 0.0F;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        Collection<Object> expResult = new ArrayList<Object>();
        Collection<Object> result = instance.getAlphaCut(alpha);
        assertNotNull(result);

        alpha = 0.6f;
        instance = new DiscreteFuzzySet<Object>();
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        instance.add(obj1, alpha + 0.1f);
        instance.add(obj2, alpha);
        instance.add(obj3, alpha - 0.1f);
        expResult = new ArrayList<Object>();
        expResult.add(obj1);
        result = instance.getStrictAlphaCut(alpha);
        assertEquals(expResult, result);
        assertEquals(expResult.size(), 1);
    }

    /**
     * Test of getAlphaCut method, of class DiscreteFuzzySet.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testGetStrictAlphaCut1() {
        System.out.println("getAlphaCut");
        float alpha = 0.0F - Float.POSITIVE_INFINITY;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.getStrictAlphaCut(alpha);
    }

    /**
     * Test of getAlphaCut method, of class DiscreteFuzzySet.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testGetStrictAlphaCut2() {
        System.out.println("getAlphaCut");
        float alpha = 1.0F + Float.POSITIVE_INFINITY;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.getStrictAlphaCut(alpha);
    }

    /**
     * Test of getDegreeOfMembership method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testGetDegreeOfMembership() {
        System.out.println("getDegreeOfMembership");
        Object obj = null;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        float expResult = 0.0F;
        float result = instance.getDegreeOfMembership(obj);
        assertEquals(expResult, result, 0.0f);

        obj = new Object();
        instance = new DiscreteFuzzySet<Object>();
        expResult = 0.0F;
        result = instance.getDegreeOfMembership(obj);
        assertEquals(expResult, result, 0.0f);

        obj = new Object();
        instance = new DiscreteFuzzySet<Object>();
        expResult = 0.0F;
        instance.add(obj, expResult);
        result = instance.getDegreeOfMembership(obj);
        assertEquals(expResult, result, 0.0f);

        obj = new Object();
        instance = new DiscreteFuzzySet<Object>();
        expResult = 1.0F;
        instance.add(obj, expResult);
        result = instance.getDegreeOfMembership(obj);
        assertEquals(expResult, result, 0.0f);
    }

    /**
     * Test of getMaximumDegreeOfMembership method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testGetMaximumDegreeOfMembership() {
        System.out.println("getMaximumDegreeOfMembership");
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        float expResult = 0.0F;
        float result = instance.getMaximumDegreeOfMembership();
        assertEquals(expResult, result, 0.0f);

        instance = new DiscreteFuzzySet<Object>();
        expResult = 0.0F;
        instance.add(new Object(), expResult);
        result = instance.getMaximumDegreeOfMembership();
        assertEquals(expResult, result, 0.0f);

        instance = new DiscreteFuzzySet<Object>();
        expResult = 1.0F;
        instance.add(new Object(), expResult);
        result = instance.getMaximumDegreeOfMembership();
        assertEquals(expResult, result, 0.0f);

        instance = new DiscreteFuzzySet<Object>();
        expResult = 0.8F;
        instance.add(new Object(), expResult - 0.1f);
        instance.add(new Object(), expResult);
        result = instance.getMaximumDegreeOfMembership();
        assertEquals(expResult, result, 0.0f);

        instance = new DiscreteFuzzySet<Object>();
        expResult = 0.8F;
        instance.add(new Object(), expResult);
        instance.add(new Object(), expResult - 0.1f);
        result = instance.getMaximumDegreeOfMembership();
        assertEquals(expResult, result, 0.0f);
    }

    /**
     * Test of isEmpty method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testIsEmpty() {
        System.out.println("isEmpty");
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);

        Object obj = new Object();
        expResult = true;
        instance.add(obj, 0.0F);
        result = instance.isEmpty();
        assertEquals(expResult, result);

        obj = new Object();
        expResult = false;
        instance.add(obj, 1.0F);
        result = instance.isEmpty();
        assertEquals(expResult, result);

        expResult = true;
        instance.clear();
        result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of put method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testPut() {
        System.out.println("put");
        Object expResult = new Object();
        float dom = 1.0F;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.put(expResult, dom);
        Float result = instance.getDegreeOfMembership(expResult);
        assertEquals(result, result, 0.0F);

        // What will happen when degree of membership is 0.0?
        dom = 0.1F;
        instance.put(expResult, dom);
        result = instance.getDegreeOfMembership(expResult);
        assertEquals(result, dom, 0.0F);

        // What will happen when object is null?
        dom = 1.0F;
        instance.put(null, dom);
        result = instance.getDegreeOfMembership(null);
        assertEquals(result, 0.0F, 0.0F);
    }

    /**
     * Test of put method, of class DiscreteFuzzySet.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testPut1() {
        System.out.println("put");
        Object expResult = new Object();
        float dom = 1.0F + Float.POSITIVE_INFINITY;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.put(expResult, dom);
    }

    /**
     * Test of put method, of class DiscreteFuzzySet.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testPut2() {
        System.out.println("put");
        Object expResult = new Object();
        float dom = 0.0F - Float.NEGATIVE_INFINITY;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.put(expResult, dom);
    }

    /**
     * Test of reciproce method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testReciproce() {
        System.out.println("reciproce");
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        instance.reciproce();

        Object obj1 = new Object();
        float degreeOfMembershipOfObj1 = 0.0f;
        float expResult = 0.0f;
        instance = new DiscreteFuzzySet<Object>();
        instance.add(obj1, degreeOfMembershipOfObj1);
        instance.reciproce();
        assertEquals(expResult, instance.getDegreeOfMembership(obj1), 0.0f);

        obj1 = new Object();
        degreeOfMembershipOfObj1 = 1.0f;
        expResult = 0.0f;
        instance = new DiscreteFuzzySet<Object>();
        instance.add(obj1, degreeOfMembershipOfObj1);
        instance.reciproce();
        assertEquals(expResult, instance.getDegreeOfMembership(obj1), 0.0f);

        obj1 = new Object();
        degreeOfMembershipOfObj1 = 0.3f;
        expResult = 0.7f;
        instance = new DiscreteFuzzySet<Object>();
        instance.add(obj1, degreeOfMembershipOfObj1);
        instance.reciproce();
        assertEquals(expResult, instance.getDegreeOfMembership(obj1), 0.0f);
    }

    /**
     * Test of remove method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testRemove() {
        System.out.println("remove");
        Object obj = null;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        float expResult = 0.0F;
        float result = instance.remove(obj);
        assertEquals(expResult, result, 0.0f);

        obj = new Object();
        instance = new DiscreteFuzzySet<Object>();
        expResult = 0.0F;
        result = instance.remove(obj);
        assertEquals(expResult, result, 0.0f);

        obj = new Object();
        instance = new DiscreteFuzzySet<Object>();
        expResult = 1.0F;
        instance.add(obj, expResult);
        result = instance.remove(obj);
        assertEquals(expResult, result, 0.0f);

        obj = new Object();
        instance = new DiscreteFuzzySet<Object>();
        expResult = 0.0F;
        instance.add(obj, 1.0f);
        instance.remove(obj);
        result = instance.remove(obj);
        assertEquals(expResult, result, 0.0f);
    }

    /**
     * Test of size method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testSize() {
        System.out.println("size");
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);

        Object obj = new Object();
        expResult = 1;
        instance.add(obj, 1.0F);
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testEquals() {
        System.out.println("equals");
        Object obj = null;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new DiscreteFuzzySet<Object>();
        instance = new DiscreteFuzzySet<Object>();
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new DiscreteFuzzySet<Object>();
        instance = new DiscreteFuzzySet<Object>(new Object(), 0.0f);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new DiscreteFuzzySet<Object>();
        instance = new DiscreteFuzzySet<Object>(new Object(), 0.1f);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new DiscreteFuzzySet<Object>(new Integer(10), 1.0f);
        instance = new DiscreteFuzzySet<Object>(new Integer(10), 0.1f);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new DiscreteFuzzySet<Object>(new Integer(10), 1.0f);
        instance = new DiscreteFuzzySet<Object>(new Integer(10), 1.0f);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of clone method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testClone() {
        System.out.println("clone");
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        Object expResult = new DiscreteFuzzySet<Object>();
        Object result = instance.clone();
        assertEquals(expResult, result);

        instance = new DiscreteFuzzySet<Object>(new Object(), 1.0f);
        expResult = new DiscreteFuzzySet<Object>();
        result = instance.clone();
        assertFalse(result.equals(expResult));

        instance = new DiscreteFuzzySet<Object>(new Integer(10), 1.0f);
        expResult = new DiscreteFuzzySet<Object>(new Integer(10), 1.0f);
        result = instance.clone();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testHashCode() {
        System.out.println("hashCode");
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new DiscreteFuzzySet<Object>(new Integer(10), 1.0f);
        expResult = new DiscreteFuzzySet<Object>(new Integer(10), 1.0f).hashCode();
        result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new DiscreteFuzzySet<Object>(new Integer(10), 0.9f);
        expResult = new DiscreteFuzzySet<Object>(new Integer(10), 1.0f).hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        instance = new DiscreteFuzzySet<Object>(new Integer(10), 0.0f);
        expResult = new DiscreteFuzzySet<Object>(new Integer(10), 0.0f).hashCode();
        result = instance.hashCode();
        assertTrue(expResult == result);

        instance = new DiscreteFuzzySet<Object>(new Object(), 1.0f);
        expResult = new DiscreteFuzzySet<Object>().hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);
    }

    /**
     * Test of toString method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testToString() {
        System.out.println("toString");
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        String result = instance.toString();
        assertNotNull(result);
    }

    /**
     * Test of toString method, of class DiscreteFuzzySet.
     */
    @Test
    public final void testToString_boolean() {
        System.out.println("toString");
        boolean withObjects = false;
        DiscreteFuzzySet<Object> instance = new DiscreteFuzzySet<Object>();
        String result = instance.toString(withObjects);
        assertNotNull(result);

        withObjects = true;
        instance = new DiscreteFuzzySet<Object>();
        result = instance.toString(withObjects);
        assertNotNull(result);
    }
}