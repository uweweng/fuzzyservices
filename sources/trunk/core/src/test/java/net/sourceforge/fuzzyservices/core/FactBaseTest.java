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

import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class FactBaseTest.
 *
 * @author Uwe Weng
 */
public class FactBaseTest {

    /**
     * Test of add method, of class FactBase.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Fact fact = null;
        FactBase instance = new FactBase();
        instance.add(fact);
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class FactBase.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        FactBase instance = new FactBase();
        instance.clear();
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class FactBase.
     */
    @Test
    public void testContains_LinguisticVariable() {
        System.out.println("contains");
        LinguisticVariable lv = null;
        FactBase instance = new FactBase();
        boolean expResult = false;
        boolean result = instance.contains(lv);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class FactBase.
     */
    @Test
    public void testContains_String() {
        System.out.println("contains");
        String lingVarName = "";
        FactBase instance = new FactBase();
        boolean expResult = false;
        boolean result = instance.contains(lingVarName);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class FactBase.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        FactBase instance = new FactBase();
        Iterator<Fact> expResult = null;
        Iterator<Fact> result = instance.iterator();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class FactBase.
     */
    @Test
    public void testGet_LinguisticVariable() {
        System.out.println("get");
        LinguisticVariable lv = null;
        FactBase instance = new FactBase();
        Fact expResult = null;
        Fact result = instance.get(lv);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class FactBase.
     */
    @Test
    public void testGet_String() {
        System.out.println("get");
        String lingVarName = "";
        FactBase instance = new FactBase();
        Fact expResult = null;
        Fact result = instance.get(lingVarName);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class FactBase.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        FactBase instance = new FactBase();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class FactBase.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        FactBase instance = new FactBase();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of put method, of class FactBase.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Fact fact = null;
        FactBase instance = new FactBase();
        Fact expResult = null;
        Fact result = instance.put(fact);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class FactBase.
     */
    @Test
    public void testRemove_Fact() {
        System.out.println("remove");
        Fact fact = null;
        FactBase instance = new FactBase();
        boolean expResult = false;
        boolean result = instance.remove(fact);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class FactBase.
     */
    @Test
    public void testRemove_LinguisticVariable() {
        System.out.println("remove");
        LinguisticVariable lv = null;
        FactBase instance = new FactBase();
        Fact expResult = null;
        Fact result = instance.remove(lv);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class FactBase.
     */
    @Test
    public void testRemove_String() {
        System.out.println("remove");
        String lingVarName = "";
        FactBase instance = new FactBase();
        Fact expResult = null;
        Fact result = instance.remove(lingVarName);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class FactBase.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        FactBase instance = new FactBase();
        instance.setName(name);
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class FactBase.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        FactBase instance = new FactBase();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class FactBase.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        FactBase instance = new FactBase();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class FactBase.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        FactBase instance = new FactBase();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class FactBase.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        FactBase instance = new FactBase();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class FactBase.
     */
    @Test
    public void testToString_0args() {
        System.out.println("toString");
        FactBase instance = new FactBase();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class FactBase.
     */
    @Test
    public void testToString_boolean() {
        System.out.println("toString");
        boolean withFacts = false;
        FactBase instance = new FactBase();
        String expResult = "";
        String result = instance.toString(withFacts);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}