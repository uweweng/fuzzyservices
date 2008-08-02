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
 * Test of class RuleBaseTest.
 *
 * @author Uwe Weng
 */
public class RuleBaseTest {

    /**
     * Test of add method, of class RuleBase.
     */
    @Test
    public final void testAdd() {
        System.out.println("add");
        Rule rule = null;
        RuleBase instance = new RuleBase();
        instance.add(rule);
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class RuleBase.
     */
    @Test
    public final void testClear() {
        System.out.println("clear");
        RuleBase instance = new RuleBase();
        instance.clear();
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class RuleBase.
     */
    @Test
    public final void testContains() {
        System.out.println("contains");
        Rule rule = null;
        RuleBase instance = new RuleBase();
        boolean expResult = false;
        boolean result = instance.contains(rule);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsLinguisticVariable method, of class RuleBase.
     */
    @Test
    public final void testContainsLinguisticVariable() {
        System.out.println("containsLinguisticVariable");
        String lingVarName = "";
        RuleBase instance = new RuleBase();
        boolean expResult = false;
        boolean result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class RuleBase.
     */
    @Test
    public final void testIterator() {
        System.out.println("iterator");
        RuleBase instance = new RuleBase();
        Iterator<Rule> expResult = null;
        Iterator<Rule> result = instance.iterator();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccumulationOperator method, of class RuleBase.
     */
    @Test
    public final void testGetAccumulationOperator() {
        System.out.println("getAccumulationOperator");
        RuleBase instance = new RuleBase();
        AbstractOperator expResult = null;
        AbstractOperator result = instance.getAccumulationOperator();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultAccumulationOperator method, of class RuleBase.
     */
    @Test
    public final void testGetDefaultAccumulationOperator() {
        System.out.println("getDefaultAccumulationOperator");
        AbstractOperator expResult = null;
        AbstractOperator result = RuleBase.getDefaultAccumulationOperator();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class RuleBase.
     */
    @Test
    public final void testGetName() {
        System.out.println("getName");
        RuleBase instance = new RuleBase();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class RuleBase.
     */
    @Test
    public final void testIsEmpty() {
        System.out.println("isEmpty");
        RuleBase instance = new RuleBase();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class RuleBase.
     */
    @Test
    public final void testRemove() {
        System.out.println("remove");
        Rule rule = null;
        RuleBase instance = new RuleBase();
        boolean expResult = false;
        boolean result = instance.remove(rule);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccumulationOperator method, of class RuleBase.
     */
    @Test
    public final void testSetAccumulationOperator() {
        System.out.println("setAccumulationOperator");
        AbstractOperator accOp = null;
        RuleBase instance = new RuleBase();
        instance.setAccumulationOperator(accOp);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDefaultAccumulationOperator method, of class RuleBase.
     */
    @Test
    public final void testSetDefaultAccumulationOperator() {
        System.out.println("setDefaultAccumulationOperator");
        AbstractOperator accOp = null;
        RuleBase.setDefaultAccumulationOperator(accOp);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class RuleBase.
     */
    @Test
    public final void testSetName() {
        System.out.println("setName");
        String newName = "";
        RuleBase instance = new RuleBase();
        instance.setName(newName);
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class RuleBase.
     */
    @Test
    public final void testSize() {
        System.out.println("size");
        RuleBase instance = new RuleBase();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class RuleBase.
     */
    @Test
    public final void testClone() {
        System.out.println("clone");
        RuleBase instance = new RuleBase();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class RuleBase.
     */
    @Test
    public final void testEquals() {
        System.out.println("equals");
        Object obj = null;
        RuleBase instance = new RuleBase();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class RuleBase.
     */
    @Test
    public final void testHashCode() {
        System.out.println("hashCode");
        RuleBase instance = new RuleBase();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class RuleBase.
     */
    @Test
    public final void testToString_0args() {
        System.out.println("toString");
        RuleBase instance = new RuleBase();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class RuleBase.
     */
    @Test
    public final void testToString_boolean() {
        System.out.println("toString");
        boolean withRules = false;
        RuleBase instance = new RuleBase();
        String expResult = "";
        String result = instance.toString(withRules);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}