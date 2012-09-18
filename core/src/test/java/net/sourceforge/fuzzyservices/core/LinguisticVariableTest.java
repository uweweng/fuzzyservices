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
 * Test of class LinguisticVariable.
 *
 * @author Uwe Weng
 */
public class LinguisticVariableTest {

    /**
     * Test of clear method, of class LinguisticVariable.
     */
    @Test
    public final void testClear() {
        System.out.println("clear");
        LinguisticVariable instance = new LinguisticVariable();
        assertEquals(instance.size(), 0);
        instance.clear();
        assertEquals(instance.size(), 0);

        instance = new LinguisticVariable();
        instance.set("Foo", new FuzzySet());
        assertEquals(instance.size(), 1);
        instance.clear();
        assertEquals(instance.size(), 0);
    }

    /**
     * Test of contains method, of class LinguisticVariable.
     */
    @Test
    public final void testContains() {
        System.out.println("contains");
        String name = null;
        LinguisticVariable instance = new LinguisticVariable();
        boolean expResult = false;
        boolean result = instance.contains(name);
        assertEquals(expResult, result);

        name = "";
        instance = new LinguisticVariable();
        expResult = false;
        result = instance.contains(name);
        assertEquals(expResult, result);

        name = "term1";
        instance = new LinguisticVariable();
        expResult = false;
        result = instance.contains(name);
        assertEquals(expResult, result);
        expResult = true;
        instance.set(name, new FuzzySet());
        result = instance.contains(name);
        assertEquals(expResult, result);

        // Operation is case-sensitive.
        name = "term1";
        instance = new LinguisticVariable();
        expResult = false;
        instance.set("Term1", new FuzzySet());
        result = instance.contains(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFuzzySet method, of class LinguisticVariable.
     */
    @Test
    public final void testGetFuzzySet() {
        System.out.println("getFuzzySet");
        String name = null;
        LinguisticVariable instance = new LinguisticVariable();
        FuzzySet expResult = null;
        FuzzySet result = instance.getFuzzySet(name);
        assertSame(expResult, result);

        name = "Foo";
        FuzzySet fs = new FuzzySet(1.0f);
        instance = new LinguisticVariable();
        expResult = null;
        result = instance.getFuzzySet(name);
        assertSame(expResult, result);
        instance.set(name, fs);
        expResult = fs;
        result = instance.getFuzzySet(name);
        assertSame(expResult, result);
    }

    /**
     * Test of getMaxDefinedX method, of class LinguisticVariable.
     */
    @Test
    public final void testGetMaxDefinedX() {
        System.out.println("getMaxDefinedX");
        LinguisticVariable instance = new LinguisticVariable();
        float expResult = Float.NaN;
        float result = instance.getMaxDefinedX();
        assertEquals(expResult, result, 0.0F);

        instance = new LinguisticVariable();
        expResult = 0.0f;
        instance.set("term1", new FuzzySet(expResult));
        result = instance.getMaxDefinedX();
        assertEquals(expResult, result, 0.0F);

        instance = new LinguisticVariable();
        expResult = 1.0f;
        instance.set("term1", new FuzzySet(expResult - Float.POSITIVE_INFINITY));
        instance.set("term2", new FuzzySet(expResult));
        result = instance.getMaxDefinedX();
        assertEquals(expResult, result, 0.0F);

        instance = new LinguisticVariable();
        expResult = 1.0f;
        instance.set("term1", new FuzzySet(expResult));
        instance.set("term2", new FuzzySet(expResult - Float.POSITIVE_INFINITY));
        result = instance.getMaxDefinedX();
        assertEquals(expResult, result, 0.0F);

        instance = new LinguisticVariable();
        expResult = -1.0f;
        instance.set("term1", new FuzzySet(expResult));
        instance.set("term2", new FuzzySet(expResult - Float.POSITIVE_INFINITY));
        result = instance.getMaxDefinedX();
        assertEquals(expResult, result, 0.0F);

        instance = new LinguisticVariable();
        expResult = 1.0f;
        instance.set("term1", new FuzzySet(expResult - Float.POSITIVE_INFINITY));
        instance.set("term2", new FuzzySet(expResult));
        result = instance.getMaxDefinedX();
        assertEquals(expResult, result, 0.0F);
    }

    /**
     * Test of getMinDefinedX method, of class LinguisticVariable.
     */
    @Test
    public final void testGetMinDefinedX() {
        System.out.println("getMinDefinedX");
        LinguisticVariable instance = new LinguisticVariable();
        float expResult = Float.NaN;
        float result = instance.getMinDefinedX();
        assertEquals(expResult, result, 0.0F);

        instance = new LinguisticVariable();
        expResult = 0.0f;
        instance.set("term1", new FuzzySet(expResult));
        result = instance.getMinDefinedX();
        assertEquals(expResult, result, 0.0F);

        instance = new LinguisticVariable();
        expResult = 1.0f;
        instance.set("term1", new FuzzySet(expResult + Float.POSITIVE_INFINITY));
        instance.set("term2", new FuzzySet(expResult));
        result = instance.getMinDefinedX();
        assertEquals(expResult, result, 0.0F);

        instance = new LinguisticVariable();
        expResult = 1.0f;
        instance.set("term1", new FuzzySet(expResult));
        instance.set("term2", new FuzzySet(expResult + Float.POSITIVE_INFINITY));
        result = instance.getMinDefinedX();
        assertEquals(expResult, result, 0.0F);

        instance = new LinguisticVariable();
        expResult = -1.0f;
        instance.set("term1", new FuzzySet(expResult));
        instance.set("term2", new FuzzySet(expResult + Float.POSITIVE_INFINITY));
        result = instance.getMinDefinedX();
        assertEquals(expResult, result, 0.0F);

        instance = new LinguisticVariable();
        expResult = 1.0f;
        instance.set("term1", new FuzzySet(expResult + Float.POSITIVE_INFINITY));
        instance.set("term2", new FuzzySet(expResult));
        result = instance.getMinDefinedX();
        assertEquals(expResult, result, 0.0F);
    }

    /**
     * Test of getName method, of class LinguisticVariable.
     */
    @Test
    public final void testGetName() {
        System.out.println("getName");
        LinguisticVariable instance = new LinguisticVariable();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);

        instance = new LinguisticVariable("Foo");
        expResult = "Foo";
        result = instance.getName();
        assertEquals(expResult, result);

        instance = new LinguisticVariable();
        expResult = "Foo";
        instance.setName(expResult);
        result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class LinguisticVariable.
     */
    @Test
    public final void testSetName() {
        System.out.println("setName");
        String name = null;
        LinguisticVariable instance = new LinguisticVariable();
        instance.setName(name);
        assertNull(instance.getName());

        name = "Foo";
        instance = new LinguisticVariable();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getNames method, of class LinguisticVariable.
     */
    @Test
    public final void testGetNames() {
        System.out.println("getNames");
        LinguisticVariable instance = new LinguisticVariable();
        Iterator<String> result = instance.getNames();
        assertNotNull(result);
    }

    /**
     * Test of isDefined method, of class LinguisticVariable.
     */
    @Test
    public final void testIsDefined() {
        System.out.println("isDefined");
        LinguisticVariable instance = new LinguisticVariable();
        boolean expResult = false;
        boolean result = instance.isDefined();
        assertEquals(expResult, result);

        instance = new LinguisticVariable();
        assertFalse(instance.isDefined());
        instance.set("Foo", new FuzzySet());
        expResult = false;
        result = instance.isDefined();
        assertEquals(expResult, result);

        instance = new LinguisticVariable();
        assertFalse(instance.isDefined());
        instance.set("Foo", new FuzzySet(0.0f));
        expResult = true;
        result = instance.isDefined();
        assertEquals(expResult, result);

        instance = new LinguisticVariable();
        assertFalse(instance.isDefined());
        instance.set("Foo", new FuzzySet(0.0f));
        expResult = true;
        result = instance.isDefined();
        assertEquals(expResult, result);
        instance.remove("Foo");
        expResult = false;
        result = instance.isDefined();
        assertEquals(expResult, result);

        instance = new LinguisticVariable();
        assertFalse(instance.isDefined());
        instance.set("Foo", new FuzzySet(0.0f));
        expResult = true;
        result = instance.isDefined();
        assertEquals(expResult, result);
        instance.getFuzzySet("Foo").clear();
        expResult = false;
        result = instance.isDefined();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class LinguisticVariable.
     */
    @Test
    public final void testIsEmpty() {
        System.out.println("isEmpty");
        LinguisticVariable instance = new LinguisticVariable();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);

        instance = new LinguisticVariable();
        assertTrue(instance.isEmpty());
        instance.set("Foo", new FuzzySet());
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class LinguisticVariable.
     */
    @Test
    public final void testRemove() {
        System.out.println("remove");
        String name = null;
        LinguisticVariable instance = new LinguisticVariable();
        FuzzySet expResult = null;
        FuzzySet result = instance.remove(name);
        assertEquals(expResult, result);

        name = "Foo";
        instance = new LinguisticVariable();
        expResult = null;
        result = instance.remove(name);
        assertEquals(expResult, result);

        FuzzySet fuzzyset = new FuzzySet(0.0f);
        name = "Foo";
        instance = new LinguisticVariable();
        expResult = fuzzyset;
        instance.set(name, fuzzyset);
        result = instance.remove(name);
        assertEquals(expResult, result);

        fuzzyset = new FuzzySet(0.0f);
        name = "Foo";
        instance = new LinguisticVariable();
        expResult = null;
        instance.set(name, fuzzyset);
        result = instance.remove(name);
        result = instance.remove(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of set method, of class LinguisticVariable.
     */
    @Test
    public final void testSet() {
        System.out.println("set");
        String name = "Foo";
        FuzzySet fs = new FuzzySet();
        LinguisticVariable instance = new LinguisticVariable();
        FuzzySet expResult = null;
        FuzzySet result = instance.set(name, fs);
        assertEquals(expResult, result);

        name = "Foo";
        fs = new FuzzySet();
        instance = new LinguisticVariable();
        expResult = null;
        result = instance.set(name, fs);
        assertEquals(expResult, result);
        expResult = fs;
        result = instance.set(name, fs);
        assertEquals(expResult, result);
    }

    /**
     * Test of set method, of class LinguisticVariable.
     */
    @Test(expected = NullPointerException.class)
    public final void testSet1() {
        System.out.println("set");
        String name = "Foo";
        FuzzySet fs = null;
        LinguisticVariable instance = new LinguisticVariable();
        FuzzySet result = instance.set(name, fs);
        assertNull(result);
    }

    /**
     * Test of set method, of class LinguisticVariable.
     */
    @Test(expected = NullPointerException.class)
    public final void testSet2() {
        System.out.println("set");
        String name = null;
        FuzzySet fs = new FuzzySet();
        LinguisticVariable instance = new LinguisticVariable();
        FuzzySet result = instance.set(name, fs);
        assertNull(result);
    }

    /**
     * Test of size method, of class LinguisticVariable.
     */
    @Test
    public final void testSize() {
        System.out.println("size");
        LinguisticVariable instance = new LinguisticVariable();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);

        // Adding increases size.
        instance = new LinguisticVariable();
        instance.set("Foo", new FuzzySet());
        expResult = 1;
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of clone method, of class LinguisticVariable.
     */
    @Test
    public final void testClone() {
        System.out.println("clone");
        LinguisticVariable instance = new LinguisticVariable();
        Object expResult = new LinguisticVariable();
        Object result = instance.clone();
        assertEquals(expResult, result);

        instance = new LinguisticVariable("foo");
        expResult = new LinguisticVariable("foo");
        result = instance.clone();
        assertEquals(expResult, result);

        instance = new LinguisticVariable("foo");
        instance.set("term1", new FuzzySet(0.0f));
        expResult = instance;
        result = instance.clone();
        assertNotSame(expResult, result);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class LinguisticVariable.
     */
    @Test
    public final void testEquals() {
        Object obj = null;
        LinguisticVariable instance = new LinguisticVariable();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new LinguisticVariable();
        instance = new LinguisticVariable();
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new LinguisticVariable("Foo");
        instance = new LinguisticVariable("Foo");
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        FuzzySet fs = new FuzzySet();
        obj = new LinguisticVariable();
        ((LinguisticVariable) obj).set("term1", fs);
        instance = new LinguisticVariable();
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        fs = new FuzzySet();
        obj = new LinguisticVariable();
        ((LinguisticVariable) obj).set("term1", fs);
        instance = new LinguisticVariable();
        instance.set("term1", fs);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class LinguisticVariable.
     */
    @Test
    public final void testHashCode() {
        System.out.println("hashCode");
        LinguisticVariable instance = new LinguisticVariable();
        int expResult = new LinguisticVariable().hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new LinguisticVariable("foo");
        expResult = new LinguisticVariable("foo").hashCode();
        result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new LinguisticVariable("foo");
        expResult = new LinguisticVariable("FOO").hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        instance = new LinguisticVariable();
        expResult = new LinguisticVariable("FOO").hashCode();
        instance.set("foo", new FuzzySet());
        result = instance.hashCode();
        assertTrue(expResult != result);
    }

    /**
     * Test of toString method, of class LinguisticVariable.
     */
    @Test
    public final void testToString() {
        System.out.println("toString");
        LinguisticVariable instance = new LinguisticVariable(null);
        String result = instance.toString();
        assertNotNull(result);
    }
}