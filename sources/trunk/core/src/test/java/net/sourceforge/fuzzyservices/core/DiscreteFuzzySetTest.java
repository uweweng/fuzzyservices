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

import java.util.Collection;
import java.util.Iterator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class DiscreteFuzzySetTest.
 *
 * @author Uwe Weng
 */
public class DiscreteFuzzySetTest {

    public DiscreteFuzzySetTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of add method, of class DiscreteFuzzySet.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Object obj = null;
        float dom = 0.0F;
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        instance.add(obj, dom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class DiscreteFuzzySet.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class DiscreteFuzzySet.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of combine method, of class DiscreteFuzzySet.
     */
    @Test
    public void testCombine() {
        System.out.println("combine");
        DiscreteFuzzySet<Object> dfs = null;
        AbstractOperator op = null;
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        DiscreteFuzzySet<Object> expResult = null;
        DiscreteFuzzySet<Object> result = instance.combine(dfs, op);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of concentrate method, of class DiscreteFuzzySet.
     */
    @Test
    public void testConcentrate() {
        System.out.println("concentrate");
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        instance.concentrate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class DiscreteFuzzySet.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Object obj = null;
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        boolean expResult = false;
        boolean result = instance.contains(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dilate method, of class DiscreteFuzzySet.
     */
    @Test
    public void testDilate() {
        System.out.println("dilate");
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        instance.dilate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class DiscreteFuzzySet.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        Iterator<Object> expResult = null;
        Iterator<Object> result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAlphaCut method, of class DiscreteFuzzySet.
     */
    @Test
    public void testGetAlphaCut() {
        System.out.println("getAlphaCut");
        float alpha = 0.0F;
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        Collection<Object> expResult = null;
        Collection<Object> result = instance.getAlphaCut(alpha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStrictAlphaCut method, of class DiscreteFuzzySet.
     */
    @Test
    public void testGetStrictAlphaCut() {
        System.out.println("getStrictAlphaCut");
        float alpha = 0.0F;
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        Collection<Object> expResult = null;
        Collection<Object> result = instance.getStrictAlphaCut(alpha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDegreeOfMembership method, of class DiscreteFuzzySet.
     */
    @Test
    public void testGetDegreeOfMembership() {
        System.out.println("getDegreeOfMembership");
        Object obj = null;
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        float expResult = 0.0F;
        float result = instance.getDegreeOfMembership(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaximumDegreeOfMembership method, of class DiscreteFuzzySet.
     */
    @Test
    public void testGetMaximumDegreeOfMembership() {
        System.out.println("getMaximumDegreeOfMembership");
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        float expResult = 0.0F;
        float result = instance.getMaximumDegreeOfMembership();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class DiscreteFuzzySet.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of put method, of class DiscreteFuzzySet.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Object obj = null;
        float dom = 0.0F;
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        float expResult = 0.0F;
        float result = instance.put(obj, dom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reciproce method, of class DiscreteFuzzySet.
     */
    @Test
    public void testReciproce() {
        System.out.println("reciproce");
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        instance.reciproce();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class DiscreteFuzzySet.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Object obj = null;
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        float expResult = 0.0F;
        float result = instance.remove(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class DiscreteFuzzySet.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DiscreteFuzzySet.
     */
    @Test
    public void testToString_0args() {
        System.out.println("toString");
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DiscreteFuzzySet.
     */
    @Test
    public void testToString_boolean() {
        System.out.println("toString");
        boolean withObjects = false;
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        String expResult = "";
        String result = instance.toString(withObjects);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class DiscreteFuzzySet.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class DiscreteFuzzySet.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        DiscreteFuzzySet instance = new DiscreteFuzzySet();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}