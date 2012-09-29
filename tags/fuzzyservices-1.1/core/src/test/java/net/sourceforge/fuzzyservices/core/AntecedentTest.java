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
 * Test of class Antecedent.
 *
 * @author Uwe Weng
 */
public class AntecedentTest extends RulePartTest {

    /**
     * Operator for tests.
     *
     * @version        1.0
     */
    public static class ValidCompatibilityTestOperator extends AbstractOperator {

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
            return true;
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
            return "ValidTestOperator";
        }

        @Override
        public String getName() {
            return "ValidTestOperator";
        }
    }

    /**
     * Operator for tests.
     *
     * @version        1.0
     */
    public static class InvalidCompatibilityTestOperator extends AbstractOperator {

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
            return false;
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
            return "InvalidTestOperator";
        }

        @Override
        public String getName() {
            return "InvalidTestOperator";
        }
    }

    /**
     * Test of getCompatibilityOperator method, of class Antecedent.
     */
    @Test
    public final void testGetCompatibilityOperator() {
        System.out.println("getCompatibilityOperator");
        Antecedent instance = new Antecedent();
        AbstractOperator expResult = new ValidCompatibilityTestOperator();
        instance.setCompatibilityOperator(expResult);
        AbstractOperator result = instance.getCompatibilityOperator();
        assertSame(expResult, result);
    }

    /**
     * Test of getDefaultCompatibilityOperator method, of class Antecedent.
     */
    @Test
    public final void testGetDefaultCompatibilityOperator() {
        System.out.println("getDefaultCompatibilityOperator");
        AbstractOperator result = Antecedent.getDefaultCompatibilityOperator();
        assertNotNull(result);
        // Operator has to fulfill the t-norm
        assertTrue(result.isValidTNorm());
    }

    /**
     * Test of setCompatibilityOperator method, of class Antecedent.
     */
    @Test
    public final void testSetCompatibilityOperator() {
        System.out.println("setCompatibilityOperator");
        AbstractOperator compOp = new ValidCompatibilityTestOperator();
        Antecedent instance = new Antecedent();
        instance.setCompatibilityOperator(compOp);
        assertSame(instance.getCompatibilityOperator(), compOp);
    }

    /**
     * Test of setCompatibilityOperator method, of class Antecedent.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetCompatibilityOperator1() {
        System.out.println("setCompatibilityOperator");
        AbstractOperator compOp = new InvalidCompatibilityTestOperator();
        Antecedent instance = new Antecedent();
        instance.setCompatibilityOperator(compOp);
    }

    /**
     * Test of setCompatibilityOperator method, of class Antecedent.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetCompatibilityOperator2() {
        System.out.println("setCompatibilityOperator");
        AbstractOperator compOp = null;
        Antecedent instance = new Antecedent();
        instance.setCompatibilityOperator(compOp);
    }

    /**
     * Test of setDefaultCompatibilityOperator method, of class Antecedent.
     */
    @Test
    public final void testSetDefaultCompatibilityOperator() {
        System.out.println("setDefaultCompatibilityOperator");
        AbstractOperator compOp = new ValidCompatibilityTestOperator();
        Antecedent.setDefaultCompatibilityOperator(compOp);
        assertSame(Antecedent.getDefaultCompatibilityOperator(), compOp);
    }

    /**
     * Test of setDefaultCompatibilityOperator method, of class Antecedent.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetDefaultCompatibilityOperator1() {
        System.out.println("setDefaultCompatibilityOperator");
        AbstractOperator compOp = new InvalidCompatibilityTestOperator();
        Antecedent.setDefaultCompatibilityOperator(compOp);
    }

    /**
     * Test of setDefaultCompatibilityOperator method, of class Antecedent.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetDefaultCompatibilityOperator2() {
        System.out.println("setDefaultCompatibilityOperator");
        AbstractOperator compOp = null;
        Antecedent.setDefaultCompatibilityOperator(compOp);
    }
}