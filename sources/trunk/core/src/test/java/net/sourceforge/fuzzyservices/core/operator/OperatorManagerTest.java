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

import java.util.Collection;
import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.core.FuzzySet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class OperatorManager.
 *
 * @author Uwe Weng
 */
public class OperatorManagerTest {

    public OperatorManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getOperators method, of class OperatorManager.
     */
    @Test
    public void getOperators() {
        System.out.println("getOperators");
        OperatorManager.registerOperator(new TestOperator());
        Collection<AbstractOperator> result = OperatorManager.getOperators();
        assertTrue((result != null) && (result.size() > 0));
    }

    /**
     * Test of getOperator method, of class OperatorManager.
     */
    @Test
    public void getOperator() {
        System.out.println("getOperator");
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
    public void registerOperator() {
        System.out.println("registerOperator");
        AbstractOperator operator = new TestOperator();
        int sizeBefore = OperatorManager.getOperators().size();
        System.out.println(sizeBefore);
        OperatorManager.registerOperator(operator);
        int sizeAfter = OperatorManager.getOperators().size();
        System.out.println(sizeAfter);
        assertTrue(sizeAfter == sizeBefore + 1);
        OperatorManager.registerOperator(operator);
        int sizeAfter2 = OperatorManager.getOperators().size();
        assertTrue(sizeAfter2 == sizeAfter);
    }

    /**
     * Operator for tests.
     *
     * @version	1.0
     */
    public class TestOperator extends AbstractOperator {

        /**
         * Default serial version UID
         */
        private static final long serialVersionUID = 1L;

        public TestOperator() {
        }

        @Override
        public FuzzySet combine(FuzzySet fs1, FuzzySet fs2) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isValidTNorm() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isValidSNorm() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public float compute(float a, float b) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public String toString() {
            return "TestOperator";
        }

        @Override
        public String getName() {
            return "TestOperator";
        }
    }
}
