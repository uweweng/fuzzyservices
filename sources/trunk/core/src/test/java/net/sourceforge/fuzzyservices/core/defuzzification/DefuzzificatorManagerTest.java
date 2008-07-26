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
package net.sourceforge.fuzzyservices.core.defuzzification;

import java.util.Collection;
import net.sourceforge.fuzzyservices.core.AbstractDefuzzificator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class DefuzzificatorManager.
 *
 * @author Uwe Weng
 */
public class DefuzzificatorManagerTest {

    public DefuzzificatorManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getDefuzzificators method, of class DefuzzificatorManager.
     */
    @Test
    public void testGetDefuzzificators() {
        System.out.println("getDefuzzificators");
        Collection<AbstractDefuzzificator> expResult = null;
        Collection<AbstractDefuzzificator> result = DefuzzificatorManager.getDefuzzificators();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefuzzificator method, of class DefuzzificatorManager.
     */
    @Test
    public void testGetDefuzzificator() {
        System.out.println("getDefuzzificator");
        String name = "";
        AbstractDefuzzificator expResult = null;
        AbstractDefuzzificator result = DefuzzificatorManager.getDefuzzificator(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerDefuzzificator method, of class DefuzzificatorManager.
     */
    @Test
    public void testRegisterDefuzzificator() {
        System.out.println("registerDefuzzificator");
        AbstractDefuzzificator defuzzy = null;
        AbstractDefuzzificator expResult = null;
        AbstractDefuzzificator result = DefuzzificatorManager.registerDefuzzificator(defuzzy);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefuzzificators method, of class DefuzzificatorManager.
     */
    @Test
    public void testGetDefuzzificators1() {
        System.out.println("getDefuzzificators");
        Collection<AbstractDefuzzificator> expResult = null;
        Collection<AbstractDefuzzificator> result = DefuzzificatorManager.getDefuzzificators();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefuzzificator method, of class DefuzzificatorManager.
     */
    @Test
    public void testGetDefuzzificator_String() {
        System.out.println("getDefuzzificator");
        String name = "";
        AbstractDefuzzificator expResult = null;
        AbstractDefuzzificator result = DefuzzificatorManager.getDefuzzificator(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerDefuzzificator method, of class DefuzzificatorManager.
     */
    @Test
    public void testRegisterDefuzzificator_AbstractDefuzzificator() {
        System.out.println("registerDefuzzificator");
        AbstractDefuzzificator defuzzy = null;
        AbstractDefuzzificator expResult = null;
        AbstractDefuzzificator result = DefuzzificatorManager.registerDefuzzificator(defuzzy);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefuzzificators method, of class DefuzzificatorManager.
     */
    @Test
    public void testGetDefuzzificators2() {
        System.out.println("getDefuzzificators");
        Collection<AbstractDefuzzificator> expResult = null;
        Collection<AbstractDefuzzificator> result = DefuzzificatorManager.getDefuzzificators();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefuzzificator method, of class DefuzzificatorManager.
     */
    @Test
    public void testGetDefuzzificator_String_1args() {
        System.out.println("getDefuzzificator");
        String name = "";
        AbstractDefuzzificator expResult = null;
        AbstractDefuzzificator result = DefuzzificatorManager.getDefuzzificator(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerDefuzzificator method, of class DefuzzificatorManager.
     */
    @Test
    public void testRegisterDefuzzificator_AbstractDefuzzificator_1args() {
        System.out.println("registerDefuzzificator");
        AbstractDefuzzificator defuzzy = null;
        AbstractDefuzzificator expResult = null;
        AbstractDefuzzificator result = DefuzzificatorManager.registerDefuzzificator(defuzzy);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}