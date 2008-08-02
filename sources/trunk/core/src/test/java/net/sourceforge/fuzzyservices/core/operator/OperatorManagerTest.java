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

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Collection;

/**
 * Test of class OperatorManager.
 *
 * @author Uwe Weng
 */
public class OperatorManagerTest {

    /**
     * Operator for tests.
     *
     * @version        1.0
     */
    public class TestOperator extends AbstractOperator {

        /**
         * Default serial version UID.
         */
        private static final long serialVersionUID = 1L;

        @Override
        public FuzzySet combine(final FuzzySet fs1, final FuzzySet fs2) {
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
        public final float compute(final float a, final float b) {
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
    public final void testGetOperators() {
        System.out.println("getOperators");
        // Any operators already exist
        Collection<AbstractOperator> expResult = null;
        Collection<AbstractOperator> result = OperatorManager.getOperators();
        assertNotNull(result);
        int size = result.size();
        AbstractOperator operator = new TestOperator();
        OperatorManager.registerOperator(operator);
        result = OperatorManager.getOperators();
        assertNotNull(result);
        int newSize = result.size();
        assertEquals(size + 1, newSize);
    }

    /**
     * Test of getOperator method, of class OperatorManager.
     */
    @Test
    public final void testGetOperator() {
        System.out.println("getOperator");
        AbstractOperator expResult = new TestOperator();
        String name = expResult.getName();
        OperatorManager.registerOperator(expResult);
        AbstractOperator result = OperatorManager.getOperator(name);
        assertNotNull(result);
        assertEquals(result, expResult);

        // What happens when parameter is null?
        expResult = null;
        name = null;
        result = OperatorManager.getOperator(name);
        assertEquals(expResult, result);

        // What happens when operator is unknown?
        expResult = null;
        name = "";
        result = OperatorManager.getOperator(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerOperator method, of class OperatorManager.
     */
    @Test
    public final void testRegisterOperator() {
        System.out.println("registerOperator");
        AbstractOperator expResult = new TestOperator();
        OperatorManager.registerOperator(expResult);
        AbstractOperator result = OperatorManager.getOperator(expResult.getName());
        assertEquals(expResult, result);
        
        // What happens when paramater is null?
        OperatorManager.registerOperator(null);
    }
}
