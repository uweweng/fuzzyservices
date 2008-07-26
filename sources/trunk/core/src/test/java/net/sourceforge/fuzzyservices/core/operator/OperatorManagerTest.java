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
package net.sourceforge.fuzzyservices.core.operator;

import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.core.FuzzySet;

import org.junit.AfterClass;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;

/**
 * Test of class OperatorManager.
 *
 * @author Uwe Weng
 */
public class OperatorManagerTest {

    @BeforeClass
    public static void setUpClass()
            throws Exception {
    }

    @AfterClass
    public static void tearDownClass()
            throws Exception {
    }

    /**
     * Test of getOperators method, of class OperatorManager.
     */
    @Test
    public final void getOperators() {
        OperatorManager.registerOperator(new TestOperator());

        Collection<AbstractOperator> result = OperatorManager.getOperators();
        assertTrue((result != null) && (result.size() > 0));
    }

    /**
     * Test of getOperator method, of class OperatorManager.
     */
    @Test
    public final void getOperator() {
        AbstractOperator expResult = new TestOperator();
        String name = expResult.toString();
        OperatorManager.registerOperator(expResult);

        AbstractOperator result = OperatorManager.getOperator(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerOperator method, of class OperatorManager.
     */
    @Test
    public final void registerOperator() {
        int sizeBefore = OperatorManager.getOperators().size();
        AbstractOperator operator = new TestOperator();
        OperatorManager.registerOperator(operator);
        int sizeAfter = OperatorManager.getOperators().size();
        assertEquals(sizeAfter, sizeBefore);
        OperatorManager.registerOperator(operator);

        int sizeAfter2 = OperatorManager.getOperators().size();
        assertEquals(sizeAfter2, sizeAfter);
    }

    /**
     * Operator for tests.
     *
     * @version        1.0
     */
    public class TestOperator
            extends AbstractOperator {

        /**
         * Default serial version UID.
         */
        private static final long serialVersionUID = 1L;

        @Override
        public FuzzySet combine(FuzzySet fs1, FuzzySet fs2) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public final boolean isValidTNorm() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public final boolean isValidSNorm() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public final float compute(float a, float b) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public final String toString() {
            return "TestOperator";
        }

        @Override
        public String getName() {
            return "TestOperator";
        }
    }

    /**
     * Test of getOperators method, of class OperatorManager.
     */
    @Test
    public void testGetOperators() {
        System.out.println("getOperators");
        Collection<AbstractOperator> expResult = null;
        Collection<AbstractOperator> result = OperatorManager.getOperators();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOperator method, of class OperatorManager.
     */
    @Test
    public void testGetOperator() {
        System.out.println("getOperator");
        String name = "";
        AbstractOperator expResult = null;
        AbstractOperator result = OperatorManager.getOperator(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerOperator method, of class OperatorManager.
     */
    @Test
    public void testRegisterOperator() {
        System.out.println("registerOperator");
        AbstractOperator op = null;
        AbstractOperator expResult = null;
        AbstractOperator result = OperatorManager.registerOperator(op);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOperators method, of class OperatorManager.
     */
    @Test
    public void testGetOperators1() {
        System.out.println("getOperators");
        Collection<AbstractOperator> expResult = null;
        Collection<AbstractOperator> result = OperatorManager.getOperators();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOperator method, of class OperatorManager.
     */
    @Test
    public void testGetOperator_String() {
        System.out.println("getOperator");
        String name = "";
        AbstractOperator expResult = null;
        AbstractOperator result = OperatorManager.getOperator(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerOperator method, of class OperatorManager.
     */
    @Test
    public void testRegisterOperator_AbstractOperator() {
        System.out.println("registerOperator");
        AbstractOperator op = null;
        AbstractOperator expResult = null;
        AbstractOperator result = OperatorManager.registerOperator(op);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
