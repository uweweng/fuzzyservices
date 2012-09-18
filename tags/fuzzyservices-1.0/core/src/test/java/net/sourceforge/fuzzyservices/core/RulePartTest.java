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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class RulePart.
 *
 * @author Uwe Weng
 */
public class RulePartTest {

    /**
     * Test of setLinguisticVariableName method, of class RulePart.
     */
    @Test
    public void testSetLinguisticVariableName() {
        System.out.println("setLinguisticVariableName");
        String lv = "foo";
        RulePart instance = new RulePart();
        instance.setLinguisticVariableName(lv);
        assertEquals(lv, instance.getLinguisticVariableName());

        lv = null;
        instance = new RulePart();
        instance.setLinguisticVariableName(lv);
        assertEquals(lv, instance.getLinguisticVariableName());

        lv = "";
        instance = new RulePart();
        instance.setLinguisticVariableName(lv);
        assertEquals(lv, instance.getLinguisticVariableName());

    }

    /**
     * Test of getLinguisticVariableName method, of class RulePart.
     */
    @Test
    public void testGetLinguisticVariableName() {
        System.out.println("getLinguisticVariableName");
        RulePart instance = new RulePart("foo", "term1");
        String expResult = "foo";
        String result = instance.getLinguisticVariableName();
        assertEquals(expResult, result);

        instance = new RulePart();
        expResult = null;
        result = instance.getLinguisticVariableName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLinguisticTermName method, of class RulePart.
     */
    @Test
    public void testGetLinguisticTermName() {
        System.out.println("getLinguisticTermName");
        RulePart instance = new RulePart("foo", "term1");
        String expResult = "term1";
        String result = instance.getLinguisticTermName();
        assertEquals(expResult, result);

        instance = new RulePart();
        expResult = null;
        result = instance.getLinguisticTermName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLinguisticTermName method, of class RulePart.
     */
    @Test
    public void testSetLinguisticTermName() {
        System.out.println("setLinguisticTermName");
        String name = "foo";
        RulePart instance = new RulePart();
        instance.setLinguisticTermName(name);
        assertEquals(name, instance.getLinguisticTermName());

        name = null;
        instance = new RulePart();
        instance.setLinguisticTermName(name);
        assertEquals(name, instance.getLinguisticTermName());

        name = "";
        instance = new RulePart();
        instance.setLinguisticTermName(name);
        assertEquals(name, instance.getLinguisticTermName());
    }

    /**
     * Test of equals method, of class RulePart.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        RulePart instance = new RulePart();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new RulePart();
        instance = new RulePart();
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new RulePart();
        instance = new RulePart("foo", null);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new RulePart();
        instance = new RulePart(null, "term1");
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new RulePart("foo", "term1");
        instance = new RulePart("foo", "term1");
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new RulePart("foo", "term1");
        instance = new RulePart("foo", "term2");
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new RulePart("FOO", "term1");
        instance = new RulePart("foo", "term1");
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class RulePart.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        RulePart instance = new RulePart();
        int expResult = new RulePart().hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new RulePart("foo", "term1");
        expResult = new RulePart("foo", "term1").hashCode();
        result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new RulePart("foo", "term1");
        expResult = new RulePart("foo", "term2").hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        instance = new RulePart("foo", "term1");
        expResult = new RulePart("FOO", "term1").hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);
    }

    /**
     * Test of clone method, of class RulePart.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        RulePart instance = new RulePart();
        Object expResult = new RulePart();
        Object result = instance.clone();
        assertEquals(expResult, result);

        instance = new RulePart("foo", "term1");
        expResult = new RulePart("foo", "term1");
        result = instance.clone();
        assertEquals(expResult, result);

        instance = new RulePart("foo", "term1");
        expResult = new RulePart("FOO", "term1");
        result = instance.clone();
        assertFalse(expResult.equals(result));
    }

    /**
     * Test of toString method, of class RulePart.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        RulePart instance = new RulePart();
        String result = instance.toString();
        assertNotNull(result);

        instance = new RulePart("foo", null);
        result = instance.toString();
        assertNotNull(result);

        instance = new RulePart("foo", "term1");
        result = instance.toString();
        assertNotNull(result);
    }
}