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
 * Test of class Fact.
 *
 * @author Uwe Weng
 */
public class FactTest {

    /**
     * Test of evaluate method, of class Fact.
     */
    @Test
    public final void testEvaluate() {
        System.out.println("evaluate");
        Fact instance = new Fact();
        DiscreteFuzzySet expResult = new DiscreteFuzzySet();
        DiscreteFuzzySet result = instance.evaluate();
        //assertEquals(expResult, result);
    // @todo Fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class Fact.
     */
    @Test
    public final void testGet() {
        System.out.println("get");
        Fact instance = new Fact();
        FuzzySet expResult = new FuzzySet();
        FuzzySet result = instance.get();
        assertEquals(expResult, result);

        instance = new Fact();
        expResult = new FuzzySet(0.0f);
        result = instance.get();
        assertFalse(expResult.equals(result));

        instance = new Fact();
        expResult = new FuzzySet(0.0f);
        instance.set(expResult);
        result = instance.get();
        assertEquals(result, expResult);

        // Changing after setting
        instance = new Fact();
        expResult = new FuzzySet();
        instance.set(expResult);
        expResult.set(0.0f, 1.0f);
        result = instance.get();
        assertFalse(expResult.equals(result));
    }

    /**
     * Test of getLinguisticVariable method, of class Fact.
     */
    @Test
    public final void testGetLinguisticVariable() {
        System.out.println("getLinguisticVariable");
        Fact instance = new Fact();
        LinguisticVariable expResult = null;
        LinguisticVariable result = instance.getLinguisticVariable();
        assertNotNull(result);

        LinguisticVariable lv = new LinguisticVariable("Foo");
        instance = new Fact(lv, 0);
        expResult = lv;
        result = instance.getLinguisticVariable();
        assertSame(result, expResult);
    }

    /**
     * Test of getLinguisticVariableName method, of class Fact.
     */
    @Test
    public final void testGetLinguisticVariableName() {
        System.out.println("getLinguisticVariableName");
        Fact instance = new Fact();
        String expResult = null;
        String result = instance.getLinguisticVariableName();
        assertNotNull(result);

        LinguisticVariable lv = new LinguisticVariable("Foo");
        instance = new Fact(lv, 0);
        expResult = lv.getName();
        result = instance.getLinguisticVariableName();
        assertEquals(result, expResult);
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test
    public final void testSet_byte() {
        System.out.println("set");
        byte newValue = 0;
        Fact instance = new Fact();
        instance.set(newValue);
        FuzzySet expResult = new FuzzySet(newValue);
        assertEquals(expResult, instance.get());

        newValue = Byte.MIN_VALUE;
        instance = new Fact();
        instance.set(newValue);
        expResult = new FuzzySet(newValue);
        assertEquals(expResult, instance.get());

        newValue = Byte.MAX_VALUE;
        instance = new Fact();
        instance.set(newValue);
        expResult = new FuzzySet(newValue);
        assertEquals(expResult, instance.get());
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test
    public final void testSet_float() {
        System.out.println("set");
        float newValue = 0.0f;
        Fact instance = new Fact();
        instance.set(newValue);
        FuzzySet expResult = new FuzzySet(newValue);
        assertEquals(expResult, instance.get());

        newValue = Float.MIN_VALUE;
        instance = new Fact();
        instance.set(newValue);
        expResult = new FuzzySet(newValue);
        assertEquals(expResult, instance.get());

        newValue = Float.MAX_VALUE;
        instance = new Fact();
        instance.set(newValue);
        expResult = new FuzzySet(newValue);
        assertEquals(expResult, instance.get());
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test
    public final void testSet_int() {
        System.out.println("set");
        int newValue = 0;
        Fact instance = new Fact();
        instance.set(newValue);
        FuzzySet expResult = new FuzzySet(newValue);
        assertEquals(expResult, instance.get());

        newValue = Integer.MIN_VALUE;
        instance = new Fact();
        instance.set(newValue);
        expResult = new FuzzySet(newValue);
        assertEquals(expResult, instance.get());

        newValue = Integer.MAX_VALUE;
        instance = new Fact();
        instance.set(newValue);
        expResult = new FuzzySet(newValue);
        assertEquals(expResult, instance.get());
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test
    public final void testSet_short() {
        System.out.println("set");
        short newValue = 0;
        Fact instance = new Fact();
        instance.set(newValue);
        FuzzySet expResult = new FuzzySet(newValue);
        assertEquals(expResult, instance.get());

        newValue = Short.MIN_VALUE;
        instance = new Fact();
        instance.set(newValue);
        expResult = new FuzzySet(newValue);
        assertEquals(expResult, instance.get());

        newValue = Short.MAX_VALUE;
        instance = new Fact();
        instance.set(newValue);
        expResult = new FuzzySet(newValue);
        assertEquals(expResult, instance.get());
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test
    public final void testSet_MembershipFunction() {
        System.out.println("set");
        MembershipFunction newValue = new FuzzySet();
        Fact instance = new Fact();
        instance.set(newValue);
        FuzzySet expResult = new FuzzySet();
        assertEquals(expResult, instance.get());

        // New value is null
        newValue = null;
        instance = new Fact();
        instance.set(newValue);
        expResult = new FuzzySet();
        assertEquals(expResult, instance.get());

        // New value is empty fuzzy set
        newValue = new FuzzySet();
        instance = new Fact();
        instance.set(newValue);
        expResult = new FuzzySet();
        assertEquals(expResult, instance.get());

        // New value is membership function without peak
        newValue = new FuzzySet();
        newValue.set(0.0f, 0.7f);
        instance = new Fact();
        instance.set(newValue);
        expResult = new FuzzySet();
        expResult.set(0.0f, 0.7f);
        assertEquals(expResult, instance.get());

        // New value is membership function with peak
        newValue = new FuzzySet(0.0f);
        instance = new Fact();
        instance.set(newValue);
        expResult = new FuzzySet(0.0f);
        assertEquals(expResult, instance.get());

        // New value is membership function
        newValue = new FuzzySet(0.0f, 1.0f);
        instance = new Fact();
        instance.set(newValue);
        expResult = new FuzzySet(0.0f, 1.0f);
        expResult.set(0.0f, 1.0f);
        assertEquals(expResult, instance.get());
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test
    public final void testSet_String() {
        System.out.println("set");
        String newValue = "term1";
        FuzzySet term = new FuzzySet(1.0f);
        LinguisticVariable lv = new LinguisticVariable("foo");
        lv.set(newValue, term);
        Fact instance = new Fact(lv, 0);
        instance.set(newValue);
        assertNotSame(instance.get(), term);
        assertEquals(instance.get(), term);

        instance.set(1.0f);
        assertEquals(instance.get(), term);

        instance.set(0.0f);
        assertTrue(term.equals(instance.get()) == false);
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSet_String1() {
        System.out.println("set");
        String newValue = null;
        Fact instance = new Fact();
        instance.set(newValue);
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSet_String2() {
        System.out.println("set");
        String newValue = "";
        Fact instance = new Fact();
        instance.set(newValue);
    }

    /**
     * Test of set method, of class Fact.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSet_String3() {
        System.out.println("set");
        String newValue = "term1";
        LinguisticVariable lv = new LinguisticVariable("Foo");
        Fact instance = new Fact(lv, newValue);
        instance.set(newValue);
    }

    /**
     * Test of equals method, of class Fact.
     */
    @Test
    public final void testEquals() {
        Object obj = null;
        Fact instance = new Fact();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Fact();
        instance = new Fact();
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        LinguisticVariable lv = new LinguisticVariable("foo");
        obj = new Fact();
        instance = new Fact(lv, 0);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        lv = new LinguisticVariable("foo");
        obj = new Fact(lv, 0);
        instance = new Fact(lv, 0);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        lv = new LinguisticVariable("foo");
        obj = new Fact(lv, new FuzzySet(0.0f));
        instance = new Fact(lv, new FuzzySet(0.0f));
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        lv = new LinguisticVariable("foo");
        lv.set("term1", new FuzzySet(0.0f));
        obj = new Fact(lv, new FuzzySet(0.0f));
        instance = new Fact(lv, "term1");
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Fact.
     */
    @Test
    public final void testHashCode() {
        System.out.println("hashCode");
        Fact instance = new Fact();
        int expResult = new Fact().hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

        LinguisticVariable lv = new LinguisticVariable();
        instance = new Fact(lv, 0);
        expResult = new Fact().hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);
    }

    /**
     * Test of clone method, of class Fact.
     */
    @Test
    public final void testClone() {
        System.out.println("clone");
        Fact instance = new Fact();
        Object expResult = new Fact();
        Object result = instance.clone();
        assertEquals(expResult, result);

        LinguisticVariable lv = new LinguisticVariable("Foo");
        instance = new Fact(lv, 0);
        expResult = new Fact();
        result = instance.clone();
        assertFalse(result.equals(expResult));

        lv = new LinguisticVariable("Foo");
        instance = new Fact(lv, new FuzzySet());
        expResult = new Fact();
        result = instance.clone();
        assertFalse(result.equals(expResult));

        lv = new LinguisticVariable("Foo");
        lv.set("term1", new FuzzySet());
        instance = new Fact(lv, "term1");
        expResult = new Fact();
        result = instance.clone();
        assertFalse(result.equals(expResult));
    }

    /**
     * Test of toString method, of class Fact.
     */
    @Test
    public final void testToString() {
        System.out.println("toString");
        Fact instance = new Fact();
        String result = instance.toString();
        assertNotNull(result);
    }

    /**
     * Test of toString method, of class Fact.
     */
    @Test
    public final void testToString_boolean() {
        System.out.println("toString");
        boolean withLingVar = false;
        Fact instance = new Fact();
        String result = instance.toString(withLingVar);
        assertNotNull(result);

        withLingVar = true;
        instance = new Fact();
        result = instance.toString(withLingVar);
        assertNotNull(result);
    }
}