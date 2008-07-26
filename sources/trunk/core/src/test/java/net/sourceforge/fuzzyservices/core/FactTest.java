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
 * Test of class FactTest.
 *
 * @author Uwe Weng
 */
public class FactTest {

    /**
     * Test of evaluate method, of class Fact.
     */
    @Test
    public void testEvaluate() {
        System.out.println("evaluate");
        Fact instance = new Fact();
        DiscreteFuzzySet expResult = null;
        DiscreteFuzzySet result = instance.evaluate();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class Fact.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Fact instance = new Fact();
        FuzzySet expResult = null;
        FuzzySet result = instance.get();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLinguisticVariable method, of class Fact.
     */
    @Test
    public void testGetLinguisticVariable() {
        System.out.println("getLinguisticVariable");
        Fact instance = new Fact();
        LinguisticVariable expResult = null;
        LinguisticVariable result = instance.getLinguisticVariable();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLinguisticVariableName method, of class Fact.
     */
    @Test
    public void testGetLinguisticVariableName() {
        System.out.println("getLinguisticVariableName");
        Fact instance = new Fact();
        String expResult = "";
        String result = instance.getLinguisticVariableName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test
    public void testSet_byte() {
        System.out.println("set");
        byte newValue = 0;
        Fact instance = new Fact();
        instance.set(newValue);
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test
    public void testSet_double() {
        System.out.println("set");
        double newValue = 0.0;
        Fact instance = new Fact();
        instance.set(newValue);
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test
    public void testSet_float() {
        System.out.println("set");
        float newValue = 0.0F;
        Fact instance = new Fact();
        instance.set(newValue);
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test
    public void testSet_int() {
        System.out.println("set");
        int newValue = 0;
        Fact instance = new Fact();
        instance.set(newValue);
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test
    public void testSet_short() {
        System.out.println("set");
        short newValue = 0;
        Fact instance = new Fact();
        instance.set(newValue);
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test
    public void testSet_MembershipFunction() {
        System.out.println("set");
        MembershipFunction newValue = null;
        Fact instance = new Fact();
        instance.set(newValue);
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test
    public void testSet_String() {
        System.out.println("set");
        String newValue = "";
        Fact instance = new Fact();
        instance.set(newValue);
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Fact.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Fact instance = new Fact();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Fact.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Fact instance = new Fact();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Fact.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Fact instance = new Fact();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Fact.
     */
    @Test
    public void testToString_0args() {
        System.out.println("toString");
        Fact instance = new Fact();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Fact.
     */
    @Test
    public void testToString_boolean() {
        System.out.println("toString");
        boolean withLingVar = false;
        Fact instance = new Fact();
        String expResult = "";
        String result = instance.toString(withLingVar);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}