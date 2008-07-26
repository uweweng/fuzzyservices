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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class LinguisticVariableTest.
 *
 * @author Uwe Weng
 */
public class LinguisticVariableTest {

    public LinguisticVariableTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of clear method, of class LinguisticVariable.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        LinguisticVariable instance = new LinguisticVariable();
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class LinguisticVariable.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        String aName = "";
        LinguisticVariable instance = new LinguisticVariable();
        boolean expResult = false;
        boolean result = instance.contains(aName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFuzzySet method, of class LinguisticVariable.
     */
    @Test
    public void testGetFuzzySet() {
        System.out.println("getFuzzySet");
        String name = "";
        LinguisticVariable instance = new LinguisticVariable();
        FuzzySet expResult = null;
        FuzzySet result = instance.getFuzzySet(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxDefinedX method, of class LinguisticVariable.
     */
    @Test
    public void testGetMaxDefinedX() {
        System.out.println("getMaxDefinedX");
        LinguisticVariable instance = new LinguisticVariable();
        float expResult = 0.0F;
        float result = instance.getMaxDefinedX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinDefinedX method, of class LinguisticVariable.
     */
    @Test
    public void testGetMinDefinedX() {
        System.out.println("getMinDefinedX");
        LinguisticVariable instance = new LinguisticVariable();
        float expResult = 0.0F;
        float result = instance.getMinDefinedX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class LinguisticVariable.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        LinguisticVariable instance = new LinguisticVariable();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class LinguisticVariable.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String newName = "";
        LinguisticVariable instance = new LinguisticVariable();
        instance.setName(newName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNames method, of class LinguisticVariable.
     */
    @Test
    public void testGetNames() {
        System.out.println("getNames");
        LinguisticVariable instance = new LinguisticVariable();
        Iterator<String> expResult = null;
        Iterator<String> result = instance.getNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDefined method, of class LinguisticVariable.
     */
    @Test
    public void testIsDefined() {
        System.out.println("isDefined");
        LinguisticVariable instance = new LinguisticVariable();
        boolean expResult = false;
        boolean result = instance.isDefined();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class LinguisticVariable.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        LinguisticVariable instance = new LinguisticVariable();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class LinguisticVariable.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        String name = "";
        LinguisticVariable instance = new LinguisticVariable();
        FuzzySet expResult = null;
        FuzzySet result = instance.remove(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class LinguisticVariable.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        String name = "";
        FuzzySet fs = null;
        LinguisticVariable instance = new LinguisticVariable();
        FuzzySet expResult = null;
        FuzzySet result = instance.set(name, fs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class LinguisticVariable.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        LinguisticVariable instance = new LinguisticVariable();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class LinguisticVariable.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        LinguisticVariable instance = new LinguisticVariable();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class LinguisticVariable.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        LinguisticVariable instance = new LinguisticVariable();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class LinguisticVariable.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        LinguisticVariable instance = new LinguisticVariable();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class LinguisticVariable.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        LinguisticVariable instance = new LinguisticVariable();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}