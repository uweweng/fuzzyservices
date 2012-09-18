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

import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class FactBase.
 *
 * @author Uwe Weng
 */
public class FactBaseTest {

    /**
     * Test of add method, of class FactBase.
     */
    @Test
    public final void testAdd() {
        System.out.println("add");
        Fact fact = null;
        FactBase instance = new FactBase();
        assertEquals(instance.size(), 0);
        instance.add(fact);
        assertEquals(instance.size(), 0);

        fact = new Fact(new LinguisticVariable("Foo"), 0);
        instance = new FactBase();
        assertEquals(instance.size(), 0);
        instance.add(fact);
        assertEquals(instance.size(), 1);
    }

    /**
     * Test of clear method, of class FactBase.
     */
    @Test
    public final void testClear() {
        System.out.println("clear");
        FactBase instance = new FactBase();
        assertEquals(instance.size(), 0);
        instance.clear();
        assertEquals(instance.size(), 0);

        Fact fact = new Fact(new LinguisticVariable("Foo"), 0);
        instance = new FactBase();
        instance.add(fact);
        assertEquals(instance.size(), 1);
        instance.clear();
        assertEquals(instance.size(), 0);
    }

    /**
     * Test of contains method, of class FactBase.
     */
    @Test
    public final void testContains_LinguisticVariable() {
        System.out.println("contains");
        LinguisticVariable lv = null;
        FactBase instance = new FactBase();
        boolean expResult = false;
        boolean result = instance.contains(lv);
        assertEquals(expResult, result);

        lv = new LinguisticVariable("Foo");
        instance = new FactBase();
        expResult = true;
        instance.add(new Fact(lv, 0));
        result = instance.contains(lv);
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class FactBase.
     */
    @Test
    public final void testContains_String() {
        System.out.println("contains");
        LinguisticVariable lv = null;
        String lingVarName = null;
        FactBase instance = new FactBase();
        boolean expResult = false;
        boolean result = instance.contains(lingVarName);
        assertEquals(expResult, result);

        lingVarName = "Foo";
        lv = new LinguisticVariable(lingVarName);
        instance = new FactBase();
        expResult = true;
        instance.add(new Fact(lv, 0));
        result = instance.contains(lingVarName);
        assertEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class FactBase.
     */
    @Test
    public final void testIterator() {
        System.out.println("iterator");
        FactBase instance = new FactBase();
        Iterator<Fact> result = instance.iterator();
        assertNotNull(result);
    }

    /**
     * Test of get method, of class FactBase.
     */
    @Test
    public final void testGet_LinguisticVariable() {
        System.out.println("get");
        LinguisticVariable lv = null;
        FactBase instance = new FactBase();
        Fact expResult = null;
        Fact result = instance.get(lv);
        assertEquals(expResult, result);

        lv = new LinguisticVariable("Foo");
        Fact fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = fact;
        instance.add(fact);
        result = instance.get(lv);
        assertSame(expResult, result);

        // Changed name after adding to fact base
        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = fact;
        instance.add(fact);
        lv.setName("newFoo");
        result = instance.get(lv);
        assertNull(result);
    }

    /**
     * Test of get method, of class FactBase.
     */
    @Test
    public final void testGet_String() {
        System.out.println("get");
        String lingVarName = null;
        LinguisticVariable lv = null;
        FactBase instance = new FactBase();
        Fact expResult = null;
        Fact result = instance.get(lingVarName);
        assertEquals(expResult, result);

        lingVarName = "Foo";
        lv = new LinguisticVariable(lingVarName);
        Fact fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = fact;
        instance.add(fact);
        result = instance.get(lingVarName);
        assertSame(expResult, result);

        // Changed name after adding to fact base
        lingVarName = "Foo";
        lv = new LinguisticVariable(lingVarName);
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = fact;
        instance.add(fact);
        lv.setName("newFoo");
        result = instance.get(lv);
        assertNull(result);
    }

    /**
     * Test of getName method, of class FactBase.
     */
    @Test
    public final void testGetName() {
        System.out.println("getName");
        FactBase instance = new FactBase();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);

        instance = new FactBase("Foo");
        expResult = "Foo";
        result = instance.getName();
        assertEquals(expResult, result);

        instance = new FactBase(null, null);
        expResult = null;
        result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class FactBase.
     */
    @Test
    public final void testIsEmpty() {
        System.out.println("isEmpty");
        FactBase instance = new FactBase();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);

        instance = new FactBase();
        assertTrue(instance.isEmpty());
        instance.add(new Fact(new LinguisticVariable("Foo"), 0));
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);

        instance = new FactBase();
        assertTrue(instance.isEmpty());
        instance.put(new Fact(new LinguisticVariable("Foo"), 0));
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of put method, of class FactBase.
     */
    @Test
    public final void testPut() {
        System.out.println("put");
        LinguisticVariable lv = null;
        Fact fact = null;
        FactBase instance = new FactBase();
        Fact expResult = null;
        Fact result = instance.put(fact);
        assertEquals(expResult, result);

        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = null;
        result = instance.put(fact);
        assertEquals(expResult, result);

        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = fact;
        instance.put(fact);
        result = instance.put(fact);
        assertSame(expResult, result);

        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = null;
        instance.put(fact);
        lv.setName("newFoo");
        result = instance.put(fact);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class FactBase.
     */
    @Test
    public final void testRemove_Fact() {
        System.out.println("remove");
        LinguisticVariable lv = null;
        Fact fact = null;
        FactBase instance = new FactBase();
        boolean expResult = false;
        boolean result = instance.remove(fact);
        assertEquals(expResult, result);

        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = false;
        result = instance.remove(fact);
        assertEquals(expResult, result);

        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = true;
        instance.add(fact);
        result = instance.remove(fact);
        assertEquals(expResult, result);

        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = false;
        instance.add(fact);
        instance.remove(fact);
        result = instance.remove(fact);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class FactBase.
     */
    @Test
    public final void testRemove_LinguisticVariable() {
        System.out.println("remove");
        LinguisticVariable lv = null;
        Fact fact = null;
        FactBase instance = new FactBase();
        Fact expResult = null;
        Fact result = instance.remove(lv);
        assertEquals(expResult, result);

        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = null;
        result = instance.remove(lv);
        assertEquals(expResult, result);

        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = fact;
        instance.add(fact);
        result = instance.remove(lv);
        assertSame(expResult, result);

        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = null;
        instance.add(fact);
        instance.remove(fact);
        result = instance.remove(lv);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class FactBase.
     */
    @Test
    public final void testRemove_String() {
        System.out.println("remove");
        String lingVarName = "";
        LinguisticVariable lv = null;
        Fact fact = null;
        FactBase instance = new FactBase();
        Fact expResult = null;
        Fact result = instance.remove(lingVarName);
        assertEquals(expResult, result);

        lingVarName = "Foo";
        lv = new LinguisticVariable(lingVarName);
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = null;
        result = instance.remove(lingVarName);
        assertEquals(expResult, result);

        lingVarName = "Foo";
        lv = new LinguisticVariable(lingVarName);
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = fact;
        instance.add(fact);
        result = instance.remove(lingVarName);
        assertSame(expResult, result);

        lingVarName = "Foo";
        lv = new LinguisticVariable(lingVarName);
        fact = new Fact(lv, 0);
        instance = new FactBase();
        expResult = null;
        instance.add(fact);
        instance.remove(fact);
        result = instance.remove(lingVarName);
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class FactBase.
     */
    @Test
    public final void testSetName() {
        System.out.println("setName");
        String name = null;
        FactBase instance = new FactBase();
        instance.setName(name);
        assertNull(instance.getName());

        name = "Foo";
        instance = new FactBase();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of size method, of class FactBase.
     */
    @Test
    public final void testSize() {
        System.out.println("size");
        LinguisticVariable lv = null;
        Fact fact = null;
        FactBase instance = new FactBase();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);

        // Adding increases size.
        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase();
        instance.add(fact);
        expResult = 1;
        result = instance.size();
        assertEquals(expResult, result);

        // Putting increases size, too.
        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase();
        instance.put(fact);
        expResult = 1;
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of clone method, of class FactBase.
     */
    @Test
    public final void testClone() {
        System.out.println("clone");
        FactBase instance = new FactBase();
        Object expResult = new FactBase();
        Object result = instance.clone();
        assertEquals(expResult, result);

        instance = new FactBase("foo");
        expResult = new FactBase("foo");
        result = instance.clone();
        assertEquals(expResult, result);

        LinguisticVariable lv = new LinguisticVariable("Foo");
        Fact fact = new Fact(lv, 0);
        instance = new FactBase("foo", fact);
        expResult = new FactBase("foo", fact);
        result = instance.clone();
        assertEquals(expResult, result);

        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase("foo", null);
        expResult = new FactBase("foo", fact);
        result = instance.clone();
        assertFalse(result.equals(expResult));
    }

    /**
     * Test of equals method, of class FactBase.
     */
    @Test
    public final void testEquals() {
        System.out.println("equals");
        Object obj = null;
        FactBase instance = new FactBase();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new FactBase();
        instance = new FactBase();
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new FactBase(null, null);
        instance = new FactBase("Foo");
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new FactBase("Foo");
        instance = new FactBase("Foo");
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        LinguisticVariable lv = new LinguisticVariable("Foo");
        Fact fact = new Fact(lv, 0);
        obj = new FactBase(fact);
        instance = new FactBase(fact);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class FactBase.
     */
    @Test
    public final void testHashCode() {
        System.out.println("hashCode");
        FactBase instance = new FactBase();
        int expResult = new FactBase().hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new FactBase("foo");
        expResult = new FactBase("foo").hashCode();
        result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new FactBase("foo");
        expResult = new FactBase("FOO").hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        LinguisticVariable lv = new LinguisticVariable("Foo");
        Fact fact = new Fact(lv, 0);
        instance = new FactBase("foo", fact);
        expResult = new FactBase("foo", fact).hashCode();
        result = instance.hashCode();
        assertTrue(expResult == result);

        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase("foo", null);
        expResult = new FactBase("foo", fact).hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        lv = new LinguisticVariable("Foo");
        fact = new Fact(lv, 0);
        instance = new FactBase("foo", fact);
        expResult = new FactBase("Foo", fact).hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);
    }

    /**
     * Test of toString method, of class FactBase.
     */
    @Test
    public final void testToString() {
        System.out.println("toString");
        FactBase instance = new FactBase();
        String result = instance.toString();
        assertNotNull(result);
    }

    /**
     * Test of toString method, of class FactBase.
     */
    @Test
    public final void testToString_boolean() {
        System.out.println("toString");
        boolean withFacts = false;
        FactBase instance = new FactBase();
        String result = instance.toString(withFacts);
        assertNotNull(result);

        withFacts = true;
        instance = new FactBase();
        result = instance.toString(withFacts);
        assertNotNull(result);
    }
}