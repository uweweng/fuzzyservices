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
import net.sourceforge.fuzzyservices.core.AntecedentTest.ValidCompatibilityTestOperator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class Rule.
 *
 * @author Uwe Weng
 */
public class RuleTest {

    /**
     * Operator for tests.
     *
     * @version        1.0
     */
    public static class ValidAggregationTestOperator extends AbstractOperator {

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
    public static class ValidCertaintyTestOperator extends AbstractOperator {

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
    public static class InvalidCertaintyTestOperator extends AbstractOperator {

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
     * Operator for tests.
     *
     * @version        1.0
     */
    public static class ValidInferenceTestOperator extends AbstractOperator {

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
    public static class InvalidInferenceTestOperator extends AbstractOperator {

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
     * Test of addAntecedent method, of class Rule.
     */
    @Test
    public final void testAddAntecedent_Antecedent() {
        System.out.println("addAntecedent");
        Antecedent ante = null;
        Rule instance = new Rule();
        instance.addAntecedent(ante);
        assertEquals(instance.getSizeOfAntecedents(), 1);

        ante = new Antecedent("Foo", "term1");
        instance = new Rule();
        instance.addAntecedent(ante);
        assertEquals(instance.getSizeOfAntecedents(), 1);
        assertEquals(instance.getAntecedentAt(0), ante);
    }

    /**
     * Test of addAntecedent method, of class Rule.
     */
    @Test
    public final void testAddAntecedent_String_String() {
        System.out.println("addAntecedent");
        String lvName = null;
        String lingTermName = null;
        Rule instance = new Rule();
        instance.addAntecedent(lvName, lingTermName);
        assertEquals(instance.getSizeOfAntecedents(), 1);

        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        instance.addAntecedent(lvName, lingTermName);
        assertEquals(instance.getSizeOfAntecedents(), 1);
        assertEquals(instance.getAntecedentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getAntecedentAt(0).getLinguisticTermName(), lingTermName);

        // Adding identical definitions twice. The result is the same.
        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        instance.addAntecedent(lvName, lingTermName);
        instance.addAntecedent(lvName, lingTermName);
        assertEquals(instance.getSizeOfAntecedents(), 1);
        assertEquals(instance.getAntecedentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getAntecedentAt(0).getLinguisticTermName(), lingTermName);
    }

    /**
     * Test of addAntecedent method, of class Rule.
     */
    @Test
    public final void testAddAntecedent_3args() {
        System.out.println("addAntecedent");
        String lvName = null;
        String lingTermName = null;
        AbstractOperator compOp = null;
        Rule instance = new Rule();
        instance.addAntecedent(lvName, lingTermName, compOp);
        assertEquals(instance.getSizeOfAntecedents(), 1);

        lvName = "Foo";
        lingTermName = "term1";
        compOp = new ValidCompatibilityTestOperator();
        instance = new Rule();
        instance.addAntecedent(lvName, lingTermName, compOp);
        assertEquals(instance.getSizeOfAntecedents(), 1);
        assertEquals(instance.getAntecedentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getAntecedentAt(0).getLinguisticTermName(), lingTermName);
        assertEquals(instance.getAntecedentAt(0).getCompatibilityOperator(), compOp);

        // Adding identical definitions twice. The result is the same.
        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        instance.addAntecedent(lvName, lingTermName, compOp);
        instance.addAntecedent(lvName, lingTermName, compOp);
        assertEquals(instance.getSizeOfAntecedents(), 1);
        assertEquals(instance.getAntecedentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getAntecedentAt(0).getLinguisticTermName(), lingTermName);
        assertEquals(instance.getAntecedentAt(0).getCompatibilityOperator(), compOp);

        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        instance.addAntecedent(lvName, lingTermName, compOp);
        instance.addAntecedent(lvName, lingTermName, null);
        assertEquals(instance.getSizeOfAntecedents(), 1);
        assertEquals(instance.getAntecedentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getAntecedentAt(0).getLinguisticTermName(), lingTermName);
        assertEquals(instance.getAntecedentAt(0).getCompatibilityOperator(), compOp);
    }

    /**
     * Test of addConsequent method, of class Rule.
     */
    @Test
    public final void testAddConsequent_Consequent() {
        System.out.println("addConsequent");
        Consequent cons = null;
        Rule instance = new Rule();
        instance.addConsequent(cons);
        assertEquals(instance.getSizeOfConsequents(), 1);

        cons = new Consequent("Foo", "term1");
        instance = new Rule();
        instance.addConsequent(cons);
        assertEquals(instance.getSizeOfConsequents(), 1);
        assertEquals(instance.getConsequentAt(0), cons);
    }

    /**
     * Test of addConsequent method, of class Rule.
     */
    @Test
    public final void testAddConsequent_String_String() {
        System.out.println("addConsequent");
        String lvName = null;
        String lingTermName = null;
        Rule instance = new Rule();
        instance.addConsequent(lvName, lingTermName);
        assertEquals(instance.getSizeOfConsequents(), 1);

        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        instance.addConsequent(lvName, lingTermName);
        assertEquals(instance.getSizeOfConsequents(), 1);
        assertEquals(instance.getConsequentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getConsequentAt(0).getLinguisticTermName(), lingTermName);

        // Adding identical definitions twice. The result is the same.
        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        instance.addConsequent(lvName, lingTermName);
        instance.addConsequent(lvName, lingTermName);
        assertEquals(instance.getSizeOfConsequents(), 1);
        assertEquals(instance.getConsequentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getConsequentAt(0).getLinguisticTermName(), lingTermName);
    }

    /**
     * Test of clear method, of class Rule.
     */
    @Test
    public final void testClear() {
        System.out.println("clear");
        Rule instance = new Rule();
        assertEquals(instance.getSizeOfAntecedents(), 0);
        assertEquals(instance.getSizeOfConsequents(), 0);
        instance.clear();
        assertEquals(instance.getSizeOfAntecedents(), 0);
        assertEquals(instance.getSizeOfConsequents(), 0);

        instance = new Rule();
        instance.addAntecedent("foo", "term1");
        instance.addConsequent("foo", "term1");
        assertEquals(instance.getSizeOfAntecedents(), 1);
        assertEquals(instance.getSizeOfConsequents(), 1);
        instance.clear();
        assertEquals(instance.getSizeOfAntecedents(), 0);
        assertEquals(instance.getSizeOfConsequents(), 0);
    }

    /**
     * Test of containsAntecedent method, of class Rule.
     */
    @Test
    public final void testContainsAntecedent_String_String() {
        System.out.println("containsAntecedent");
        String lvName = null;
        String lingTermName = null;
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.containsAntecedent(lvName, lingTermName);
        assertEquals(expResult, result);

        lvName = null;
        lingTermName = null;
        instance = new Rule();
        expResult = false;
        instance.addAntecedent(lvName, lingTermName);
        result = instance.containsAntecedent(lvName, lingTermName);
        assertEquals(expResult, result);

        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        expResult = true;
        instance.addAntecedent(lvName, lingTermName);
        result = instance.containsAntecedent(lvName, lingTermName);
        assertEquals(expResult, result);

        // Case sensitive
        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        expResult = false;
        instance.addAntecedent(lvName, lingTermName.toUpperCase());
        result = instance.containsAntecedent(lvName, lingTermName);
        assertEquals(expResult, result);

        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        expResult = false;
        instance.addAntecedent(lvName, lingTermName);
        instance.removeAntecedent(lvName, lingTermName);
        result = instance.containsAntecedent(lvName, lingTermName);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsAntecedent method, of class Rule.
     */
    @Test
    public final void testContainsAntecedent_3args() {
        System.out.println("containsAntecedent");
        String lvName = null;
        String lingTermName = null;
        AbstractOperator compOp = null;
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.containsAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);

        lvName = null;
        lingTermName = null;
        compOp = null;
        instance = new Rule();
        expResult = false;
        instance.addAntecedent(lvName, lingTermName, compOp);
        result = instance.containsAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);

        lvName = null;
        lingTermName = null;
        compOp = new ValidCompatibilityTestOperator();
        instance = new Rule();
        expResult = false;
        instance.addAntecedent(lvName, lingTermName, compOp);
        result = instance.containsAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);

        lvName = "Foo";
        lingTermName = "term1";
        compOp = null;
        instance = new Rule();
        expResult = false; // because of defaut compatobility operator
        instance.addAntecedent(lvName, lingTermName, compOp);
        result = instance.containsAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);

        lvName = "Foo";
        lingTermName = "term1";
        compOp = new ValidCompatibilityTestOperator();
        instance = new Rule();
        expResult = true;
        instance.addAntecedent(lvName, lingTermName, compOp);
        result = instance.containsAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);

        lvName = "Foo";
        lingTermName = "term1";
        compOp = null;
        instance = new Rule();
        expResult = false;
        instance.addAntecedent(lvName, lingTermName, compOp);
        result = instance.containsAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);

        // Case sensitive
        lvName = "Foo";
        lingTermName = "term1";
        compOp = new ValidCompatibilityTestOperator();
        instance = new Rule();
        expResult = false;
        instance.addAntecedent(lvName, lingTermName.toUpperCase(), compOp);
        result = instance.containsAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);

        lvName = "Foo";
        lingTermName = "term1";
        compOp = new ValidCompatibilityTestOperator();
        instance = new Rule();
        expResult = false;
        instance.addAntecedent(lvName, lingTermName, compOp);
        instance.removeAntecedent(lvName, lingTermName);
        result = instance.containsAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);

    }

    /**
     * Test of containsConsequent method, of class Rule.
     */
    @Test
    public final void testContainsConsequent() {
        System.out.println("containsConsequent");
        String lvName = null;
        String lingTermName = null;
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.containsConsequent(lvName, lingTermName);
        assertEquals(expResult, result);

        lvName = null;
        lingTermName = null;
        instance = new Rule();
        expResult = false;
        instance.addConsequent(lvName, lingTermName);
        result = instance.containsConsequent(lvName, lingTermName);
        assertEquals(expResult, result);

        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        expResult = true;
        instance.addConsequent(lvName, lingTermName);
        result = instance.containsConsequent(lvName, lingTermName);
        assertEquals(expResult, result);

        // Case sensitive
        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        expResult = false;
        instance.addConsequent(lvName, lingTermName.toUpperCase());
        result = instance.containsConsequent(lvName, lingTermName);
        assertEquals(expResult, result);

        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        expResult = false;
        instance.addConsequent(lvName, lingTermName);
        instance.removeConsequent(lvName, lingTermName);
        result = instance.containsConsequent(lvName, lingTermName);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsLinguisticVariable method, of class Rule.
     */
    @Test
    public final void testContainsLinguisticVariable() {
        System.out.println("containsLinguisticVariable");
        String lingVarName = null;
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);

        lingVarName = null;
        instance = new Rule();
        expResult = false;
        result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);

        lingVarName = null;
        instance = new Rule();
        instance.addAntecedent(lingVarName, null);
        expResult = true;
        result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);

        lingVarName = null;
        instance = new Rule();
        instance.addConsequent(lingVarName, null);
        expResult = true;
        result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);

        lingVarName = "Foo";
        instance = new Rule();
        instance.addAntecedent(lingVarName, null);
        expResult = true;
        result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);

        lingVarName = "Foo";
        instance = new Rule();
        instance.addConsequent(lingVarName, null);
        expResult = true;
        result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);

        // Case sensitive
        lingVarName = "Foo";
        instance = new Rule();
        instance.addAntecedent(lingVarName.toUpperCase(), null);
        expResult = false;
        result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);

        lingVarName = "Foo";
        instance = new Rule();
        instance.addConsequent(lingVarName.toUpperCase(), null);
        expResult = false;
        result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAggregationOperator method, of class Rule.
     */
    @Test
    public final void testGetAggregationOperator() {
        System.out.println("getAggregationOperator");
        Rule instance = new Rule();
        AbstractOperator expResult = new ValidAggregationTestOperator();
        instance.setAggregationOperator(expResult);
        AbstractOperator result = instance.getAggregationOperator();
        assertSame(expResult, result);

        // Initialised by default operator
        instance = new Rule();
        result = instance.getAggregationOperator();
        assertSame(result, Rule.getDefaultAggregationOperator());
    }

    /**
     * Test of getAntecedentAt method, of class Rule.
     */
    @Test
    public final void testGetAntecedentAt() {
        System.out.println("getAntecedentAt");
        int i = 0;
        Rule instance = new Rule();
        Antecedent expResult = new Antecedent(null, null);
        instance.addAntecedent(expResult);
        Antecedent result = instance.getAntecedentAt(i);
        assertSame(expResult, result);
    }

    /**
     * Test of getAntecedentAt method, of class Rule.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void testGetAntecedentAt1() {
        System.out.println("getAntecedentAt");
        int i = 0;
        Rule instance = new Rule();
        instance.getAntecedentAt(i);
    }

    /**
     * Test of getAntecedents method, of class Rule.
     */
    @Test
    public final void testGetAntecedents() {
        System.out.println("getAntecedents");
        Rule instance = new Rule();
        Iterator<Antecedent> result = instance.getAntecedents();
        assertNotNull(result);
    }

    /**
     * Test of getCertainty method, of class Rule.
     */
    @Test
    public final void testGetCertainty() {
        System.out.println("getCertainty");
        Rule instance = new Rule();
        float expResult = 0.5F;
        instance.setCertainty(expResult);
        float result = instance.getCertainty();
        assertEquals(expResult, result, 0.0f);

        // Initialised by default certainty
        instance = new Rule();
        result = instance.getCertainty();
        assertEquals(result, Rule.getDefaultCertainty(), 0.0f);
    }

    /**
     * Test of getCertaintyOperator method, of class Rule.
     */
    @Test
    public final void testGetCertaintyOperator() {
        System.out.println("getCertaintyOperator");
        Rule instance = new Rule();
        AbstractOperator expResult = new ValidCertaintyTestOperator();
        instance.setCertaintyOperator(expResult);
        AbstractOperator result = instance.getCertaintyOperator();
        assertSame(expResult, result);

        // Initialised by default operator
        instance = new Rule();
        result = instance.getCertaintyOperator();
        assertSame(result, Rule.getDefaultCertaintyOperator());
    }

    /**
     * Test of getConsequentAt method, of class Rule.
     */
    @Test
    public final void testGetConsequentAt() {
        System.out.println("getConsequentAt");
        int i = 0;
        Rule instance = new Rule();
        Consequent expResult = new Consequent(null, null);
        instance.addConsequent(expResult);
        Consequent result = instance.getConsequentAt(i);
        assertSame(expResult, result);
    }

    /**
     * Test of getConsequentAt method, of class Rule.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void testGetConsequentAt1() {
        System.out.println("getConsequentAt");
        int i = 0;
        Rule instance = new Rule();
        instance.getConsequentAt(i);
    }

    /**
     * Test of getConsequents method, of class Rule.
     */
    @Test
    public final void testGetConsequents() {
        System.out.println("getConsequents");
        Rule instance = new Rule();
        Iterator<Consequent> result = instance.getConsequents();
        assertNotNull(result);
    }

    /**
     * Test of getDefaultAggregationOperator method, of class Rule.
     */
    @Test
    public final void testGetDefaultAggregationOperator() {
        System.out.println("getDefaultAggregationOperator");
        AbstractOperator result = Rule.getDefaultAggregationOperator();
        assertNotNull(result);
    }

    /**
     * Test of getDefaultCertaintyOperator method, of class Rule.
     */
    @Test
    public final void testGetDefaultCertaintyOperator() {
        System.out.println("getDefaultCertaintyOperator");
        AbstractOperator result = Rule.getDefaultCertaintyOperator();
        assertNotNull(result);
        // Operator has to fulfill the t-norm
        assertTrue(result.isValidTNorm());
    }

    /**
     * Test of getDefaultInferenceOperator method, of class Rule.
     */
    @Test
    public final void testGetDefaultInferenceOperator() {
        System.out.println("getDefaultInferenceOperator");
        AbstractOperator result = Rule.getDefaultInferenceOperator();
        assertNotNull(result);
        // Operator has to fulfill the t-norm
        assertTrue(result.isValidTNorm());
    }

    /**
     * Test of getInferenceOperator method, of class Rule.
     */
    @Test
    public final void testGetInferenceOperator() {
        System.out.println("getInferenceOperator");
        Rule instance = new Rule();
        AbstractOperator expResult = new ValidInferenceTestOperator();
        instance.setInferenceOperator(expResult);
        AbstractOperator result = instance.getInferenceOperator();
        assertSame(expResult, result);

        // Initialised by default operator
        instance = new Rule();
        result = instance.getInferenceOperator();
        assertSame(result, Rule.getDefaultInferenceOperator());
    }

    /**
     * Test of getSizeOfAntecedents method, of class Rule.
     */
    @Test
    public final void testGetSizeOfAntecedents() {
        System.out.println("getSizeOfAntecedents");
        Antecedent antecedent = null;
        Rule instance = new Rule();
        int expResult = 0;
        int result = instance.getSizeOfAntecedents();
        assertEquals(expResult, result);

        // Adding increases size.
        antecedent = new Antecedent();
        instance = new Rule();
        instance.addAntecedent(antecedent);
        expResult = 1;
        result = instance.getSizeOfAntecedents();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSizeOfConsequents method, of class Rule.
     */
    @Test
    public final void testGetSizeOfConsequents() {
        System.out.println("getSizeOfConsequents");
        Consequent consequent = null;
        Rule instance = new Rule();
        int expResult = 0;
        int result = instance.getSizeOfConsequents();
        assertEquals(expResult, result);

        // Adding increases size.
        consequent = new Consequent();
        instance = new Rule();
        instance.addConsequent(consequent);
        expResult = 1;
        result = instance.getSizeOfConsequents();
        assertEquals(expResult, result);
    }

    /**
     * Test of if_ method, of class Rule.
     */
    @Test
    public final void testIf__String_String() {
        System.out.println("if_");
        String lvName = null;
        String lingTermName = null;
        Rule instance = new Rule();
        instance.if_(lvName, lingTermName);
        assertEquals(instance.getSizeOfAntecedents(), 1);

        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        instance.if_(lvName, lingTermName);
        assertEquals(instance.getSizeOfAntecedents(), 1);
        assertEquals(instance.getAntecedentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getAntecedentAt(0).getLinguisticTermName(), lingTermName);

        // Adding identical definitions twice. The result is the same.
        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        instance.if_(lvName, lingTermName);
        instance.if_(lvName, lingTermName);
        assertEquals(instance.getSizeOfAntecedents(), 1);
        assertEquals(instance.getAntecedentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getAntecedentAt(0).getLinguisticTermName(), lingTermName);
    }

    /**
     * Test of if_ method, of class Rule.
     */
    @Test
    public final void testIf__3args() {
        System.out.println("if_");
        String lvName = null;
        String lingTermName = null;
        AbstractOperator compOp = null;
        Rule instance = new Rule();
        instance.if_(lvName, lingTermName, compOp);
        assertEquals(instance.getSizeOfAntecedents(), 1);

        lvName = "Foo";
        lingTermName = "term1";
        compOp = new ValidCompatibilityTestOperator();
        instance = new Rule();
        instance.if_(lvName, lingTermName, compOp);
        assertEquals(instance.getSizeOfAntecedents(), 1);
        assertEquals(instance.getAntecedentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getAntecedentAt(0).getLinguisticTermName(), lingTermName);
        assertEquals(instance.getAntecedentAt(0).getCompatibilityOperator(), compOp);

        // Adding identical definitions twice. The result is the same.
        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        instance.if_(lvName, lingTermName, compOp);
        instance.if_(lvName, lingTermName, compOp);
        assertEquals(instance.getSizeOfAntecedents(), 1);
        assertEquals(instance.getAntecedentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getAntecedentAt(0).getLinguisticTermName(), lingTermName);
        assertEquals(instance.getAntecedentAt(0).getCompatibilityOperator(), compOp);

        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        instance.if_(lvName, lingTermName, compOp);
        instance.if_(lvName, lingTermName, null);
        assertEquals(instance.getSizeOfAntecedents(), 1);
        assertEquals(instance.getAntecedentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getAntecedentAt(0).getLinguisticTermName(), lingTermName);
        assertEquals(instance.getAntecedentAt(0).getCompatibilityOperator(), compOp);
    }

    /**
     * Test of removeAntecedent method, of class Rule.
     */
    @Test
    public final void testRemoveAntecedent_Antecedent() {
        System.out.println("removeAntecedent");
        Antecedent ante = null;
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.removeAntecedent(ante);
        assertEquals(expResult, result);

        ante = new Antecedent();
        instance = new Rule();
        expResult = false;
        result = instance.removeAntecedent(ante);
        assertEquals(expResult, result);

        ante = new Antecedent();
        instance = new Rule();
        expResult = true;
        instance.addAntecedent(ante);
        result = instance.removeAntecedent(ante);
        assertEquals(expResult, result);

        ante = new Antecedent();
        instance = new Rule();
        expResult = false;
        instance.addAntecedent(ante);
        instance.removeAntecedent(ante);
        result = instance.removeAntecedent(ante);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeAntecedent method, of class Rule.
     */
    @Test
    public final void testRemoveAntecedent_String_String() {
        System.out.println("removeAntecedent");
        String lvName = null;
        String lingTermName = null;
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.removeAntecedent(lvName, lingTermName);
        assertEquals(expResult, result);

        lvName = "";
        lingTermName = "";
        instance = new Rule();
        expResult = false;
        result = instance.removeAntecedent(lvName, lingTermName);
        assertEquals(expResult, result);

        lvName = "foo";
        lingTermName = "term1";
        instance = new Rule();
        expResult = true;
        instance.addAntecedent(lvName, lingTermName);
        result = instance.removeAntecedent(lvName, lingTermName);
        assertEquals(expResult, result);

        lvName = "foo";
        lingTermName = "term1";
        instance = new Rule();
        expResult = false;
        instance.addAntecedent(lvName, lingTermName);
        instance.removeAntecedent(lvName, lingTermName);
        result = instance.removeAntecedent(lvName, lingTermName);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeAntecedent method, of class Rule.
     */
    @Test
    public final void testRemoveAntecedent_3args() {
        System.out.println("removeAntecedent");
        String lvName = null;
        String lingTermName = null;
        AbstractOperator compOp = null;
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.removeAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);

        lvName = "";
        lingTermName = "";
        compOp = new ValidCompatibilityTestOperator();
        instance = new Rule();
        expResult = false;
        result = instance.removeAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);

        lvName = "foo";
        lingTermName = "term1";
        compOp = new ValidCompatibilityTestOperator();
        instance = new Rule();
        expResult = true;
        instance.addAntecedent(lvName, lingTermName);
        result = instance.removeAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);

        lvName = "foo";
        lingTermName = "term1";
        compOp = new ValidCompatibilityTestOperator();
        instance = new Rule();
        expResult = false;
        instance.addAntecedent(lvName, lingTermName, compOp);
        instance.removeAntecedent(lvName, lingTermName, compOp);
        result = instance.removeAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeConsequent method, of class Rule.
     */
    @Test
    public final void testRemoveConsequent() {
        System.out.println("removeConsequent");
        String lvName = null;
        String lingTermName = null;
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.removeConsequent(lvName, lingTermName);
        assertEquals(expResult, result);

        lvName = "";
        lingTermName = "";
        instance = new Rule();
        expResult = false;
        result = instance.removeConsequent(lvName, lingTermName);
        assertEquals(expResult, result);

        lvName = "foo";
        lingTermName = "term1";
        instance = new Rule();
        expResult = true;
        instance.addConsequent(lvName, lingTermName);
        result = instance.removeConsequent(lvName, lingTermName);
        assertEquals(expResult, result);

        lvName = "foo";
        lingTermName = "term1";
        instance = new Rule();
        expResult = false;
        instance.addConsequent(lvName, lingTermName);
        instance.removeConsequent(lvName, lingTermName);
        result = instance.removeConsequent(lvName, lingTermName);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeConsequent method, of class Rule.
     */
    @Test
    public final void testRemoveConsequent_Consequent() {
        System.out.println("removeConsequent");
        Consequent cons = null;
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.removeConsequent(cons);
        assertEquals(expResult, result);

        cons = new Consequent();
        instance = new Rule();
        expResult = false;
        result = instance.removeConsequent(cons);
        assertEquals(expResult, result);

        cons = new Consequent();
        instance = new Rule();
        expResult = true;
        instance.addConsequent(cons);
        result = instance.removeConsequent(cons);
        assertEquals(expResult, result);

        cons = new Consequent();
        instance = new Rule();
        expResult = false;
        instance.addConsequent(cons);
        instance.removeConsequent(cons);
        result = instance.removeConsequent(cons);
        assertEquals(expResult, result);
    }

    /**
     * Test of setAggregationOperator method, of class Rule.
     */
    @Test
    public final void testSetAggregationOperator() {
        System.out.println("setAggregationOperator");
        AbstractOperator aggOp = new ValidAggregationTestOperator();
        Rule instance = new Rule();
        instance.setAggregationOperator(aggOp);
        assertSame(instance.getAggregationOperator(), aggOp);
    }

    /**
     * Test of setCertainty method, of class Rule.
     */
    @Test
    public final void testSetCertainty() {
        System.out.println("setCertainty");
        float cert = 0.5F;
        Rule instance = new Rule();
        instance.setCertainty(cert);
        assertEquals(instance.getCertainty(), cert, 0.0f);
    }

    /**
     * Test of setCertainty method, of class Rule.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetCertainty1() {
        System.out.println("setCertainty");
        float cert = 0.0F - Float.POSITIVE_INFINITY;
        Rule instance = new Rule();
        instance.setCertainty(cert);
    }

    /**
     * Test of setCertainty method, of class Rule.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetCertainty2() {
        System.out.println("setCertainty");
        float cert = 1.0F + Float.POSITIVE_INFINITY;
        Rule instance = new Rule();
        instance.setCertainty(cert);
    }

    /**
     * Test of setCertaintyOperator method, of class Rule.
     */
    @Test
    public final void testSetCertaintyOperator() {
        System.out.println("setCertaintyOperator");
        AbstractOperator certOp = new ValidCertaintyTestOperator();
        Rule instance = new Rule();
        instance.setCertaintyOperator(certOp);
        assertSame(instance.getCertaintyOperator(), certOp);
    }

    /**
     * Test of setCertaintyOperator method, of class Rule.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetCertaintyOperator1() {
        System.out.println("setCertaintyOperator");
        AbstractOperator certOp = new InvalidCertaintyTestOperator();
        Rule instance = new Rule();
        instance.setCertaintyOperator(certOp);
    }

    /**
     * Test of setCertaintyOperator method, of class Rule.
     */
    @Test(expected = NullPointerException.class)
    public final void testSetCertaintyOperator2() {
        System.out.println("setCertaintyOperator");
        AbstractOperator certOp = null;
        Rule instance = new Rule();
        instance.setCertaintyOperator(certOp);
    }

    /**
     * Test of setDefaultAggregationOperator method, of class Rule.
     */
    @Test
    public final void testSetDefaultAggregationOperator() {
        System.out.println("setDefaultAggregationOperator");
        AbstractOperator aggOp = new ValidAggregationTestOperator();
        Rule.setDefaultAggregationOperator(aggOp);
        assertSame(Rule.getDefaultAggregationOperator(), aggOp);
    }

    /**
     * Test of setDefaultAggregationOperator method, of class Rule.
     */
    @Test(expected = NullPointerException.class)
    public final void testSetDefaultAggregationOperator1() {
        System.out.println("setDefaultAggregationOperator");
        AbstractOperator aggOp = null;
        Rule.setDefaultAggregationOperator(aggOp);
    }

    /**
     * Test of getDefaultCertainty method, of class Rule.
     */
    @Test
    public final void testGetDefaultCertainty() {
        System.out.println("getDefaultCertainty");
        float expResult = 1.0F;
        float result = Rule.getDefaultCertainty();
        assertEquals(expResult, result, 0.0f);
    }

    /**
     * Test of setDefaultCertainty method, of class Rule.
     */
    @Test
    public final void testSetDefaultCertainty() {
        System.out.println("setDefaultCertainty");
        float cert = 0.5F;
        Rule.setDefaultCertainty(cert);
        assertEquals(Rule.getDefaultCertainty(), cert, 0.0f);
        // Reset
        Rule.setDefaultCertainty(1.0f);
    }

    /**
     * Test of setDefaultCertainty method, of class Rule.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetDefaultCertainty1() {
        System.out.println("setDefaultCertainty");
        float cert = 0.0F - Float.POSITIVE_INFINITY;
        Rule.setDefaultCertainty(cert);
    }

    /**
     * Test of setDefaultCertainty method, of class Rule.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetDefaultCertainty2() {
        System.out.println("setDefaultCertainty");
        float cert = 1.0F + Float.POSITIVE_INFINITY;
        Rule.setDefaultCertainty(cert);
    }

    /**
     * Test of setDefaultCertaintyOperator method, of class Rule.
     */
    @Test
    public final void testSetDefaultCertaintyOperator() {
        System.out.println("setDefaultCertaintyOperator");
        AbstractOperator certOp = new ValidCertaintyTestOperator();
        Rule.setDefaultCertaintyOperator(certOp);
        assertSame(Rule.getDefaultCertaintyOperator(), certOp);
    }

    /**
     * Test of setDefaultCertaintyOperator method, of class Rule.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetDefaultCertaintyOperator1() {
        System.out.println("setDefaultCertaintyOperator");
        AbstractOperator certOp = new InvalidCertaintyTestOperator();
        Rule.setDefaultCertaintyOperator(certOp);
    }

    /**
     * Test of setDefaultCertaintyOperator method, of class Rule.
     */
    @Test(expected = NullPointerException.class)
    public final void testSetDefaultCertaintyOperator2() {
        System.out.println("setDefaultCertaintyOperator");
        AbstractOperator certOp = null;
        Rule.setDefaultCertaintyOperator(certOp);
    }

    /**
     * Test of setDefaultInferenceOperator method, of class Rule.
     */
    @Test
    public final void testSetDefaultInferenceOperator() {
        System.out.println("setDefaultInferenceOperator");
        AbstractOperator infOp = new ValidInferenceTestOperator();
        Rule.setDefaultInferenceOperator(infOp);
        assertSame(Rule.getDefaultInferenceOperator(), infOp);
    }

    /**
     * Test of setDefaultInferenceOperator method, of class Rule.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetDefaultInferenceOperator1() {
        System.out.println("setDefaultInferenceOperator");
        AbstractOperator infOp = new InvalidInferenceTestOperator();
        Rule.setDefaultInferenceOperator(infOp);
    }

    /**
     * Test of setDefaultInferenceOperator method, of class Rule.
     */
    @Test(expected = NullPointerException.class)
    public final void testSetDefaultInferenceOperator2() {
        System.out.println("setDefaultInferenceOperator");
        AbstractOperator infOp = null;
        Rule.setDefaultInferenceOperator(infOp);
    }

    /**
     * Test of setInferenceOperator method, of class Rule.
     */
    @Test
    public final void testSetInferenceOperator() {
        System.out.println("setInferenceOperator");
        AbstractOperator infOp = new ValidInferenceTestOperator();
        Rule instance = new Rule();
        instance.setInferenceOperator(infOp);
        assertSame(instance.getInferenceOperator(), infOp);
    }

    /**
     * Test of setInferenceOperator method, of class Rule.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetInferenceOperator1() {
        System.out.println("setInferenceOperator");
        AbstractOperator infOp = new InvalidInferenceTestOperator();
        Rule instance = new Rule();
        instance.setInferenceOperator(infOp);
    }

    /**
     * Test of setInferenceOperator method, of class Rule.
     */
    @Test(expected = NullPointerException.class)
    public final void testSetInferenceOperator2() {
        System.out.println("setInferenceOperator");
        AbstractOperator infOp = null;
        Rule instance = new Rule();
        instance.setInferenceOperator(infOp);
    }

    /**
     * Test of then_ method, of class Rule.
     */
    @Test
    public final void testThen_() {
        System.out.println("then_");
        String lvName = null;
        String lingTermName = null;
        Rule instance = new Rule();
        instance.then_(lvName, lingTermName);
        assertEquals(instance.getSizeOfConsequents(), 1);

        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        instance.then_(lvName, lingTermName);
        assertEquals(instance.getSizeOfConsequents(), 1);
        assertEquals(instance.getConsequentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getConsequentAt(0).getLinguisticTermName(), lingTermName);

        // Adding identical definitions twice. The result is the same.
        lvName = "Foo";
        lingTermName = "term1";
        instance = new Rule();
        instance.then_(lvName, lingTermName);
        instance.then_(lvName, lingTermName);
        assertEquals(instance.getSizeOfConsequents(), 1);
        assertEquals(instance.getConsequentAt(0).getLinguisticVariableName(), lvName);
        assertEquals(instance.getConsequentAt(0).getLinguisticTermName(), lingTermName);
    }

    /**
     * Test of clone method, of class Rule.
     */
    @Test
    public final void testClone() {
        System.out.println("clone");
        Rule instance = new Rule();
        Object expResult = new Rule();
        Object result = instance.clone();
        assertEquals(expResult, result);

        AbstractOperator aggOp = new ValidAggregationTestOperator();
        instance = new Rule();
        instance.setAggregationOperator(aggOp);
        expResult = new Rule();
        ((Rule) expResult).setAggregationOperator(aggOp);
        result = instance.clone();
        assertEquals(expResult, result);

        AbstractOperator certOp = new ValidCertaintyTestOperator();
        instance = new Rule();
        instance.setCertaintyOperator(certOp);
        expResult = new Rule();
        ((Rule) expResult).setCertaintyOperator(certOp);
        result = instance.clone();
        assertEquals(expResult, result);

        AbstractOperator infOp = new ValidInferenceTestOperator();
        instance = new Rule();
        instance.setInferenceOperator(infOp);
        expResult = new Rule();
        ((Rule) expResult).setInferenceOperator(infOp);
        result = instance.clone();
        assertEquals(expResult, result);

        float certainty = 0.5f;
        instance = new Rule();
        instance.setCertainty(certainty);
        expResult = new Rule();
        ((Rule) expResult).setCertainty(certainty);
        result = instance.clone();
        assertEquals(expResult, result);

        instance = new Rule();
        instance.addAntecedent("Foo", "term1");
        expResult = new Rule();
        ((Rule) expResult).addAntecedent("Foo", "term1");
        result = instance.clone();
        assertEquals(expResult, result);

        AbstractOperator compOp = new ValidCompatibilityTestOperator();
        instance = new Rule();
        instance.addAntecedent("Foo", "term1", compOp);
        expResult = new Rule();
        ((Rule) expResult).addAntecedent("Foo", "term1", compOp);
        result = instance.clone();
        assertEquals(expResult, result);

        instance = new Rule();
        instance.addConsequent("Foo", "term1");
        expResult = new Rule();
        ((Rule) expResult).addConsequent("Foo", "term1");
        result = instance.clone();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Rule.
     */
    @Test
    public final void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Rule();
        instance = new Rule();
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Rule();
        ((Rule) obj).setCertainty(0.5f);
        Rule.setDefaultCertainty(1.0f);
        instance = new Rule();
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Rule();
        ((Rule) obj).setCertainty(0.5f);
        instance = new Rule();
        instance.setCertainty(0.5f);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Rule();
        ((Rule) obj).addAntecedent("Foo", "term1");
        instance = new Rule();
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Rule();
        ((Rule) obj).addAntecedent("Foo", "term1");
        instance = new Rule();
        instance.addAntecedent("Foo", "term1");
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Rule();
        ((Rule) obj).addAntecedent("Foo", "term1", new ValidCompatibilityTestOperator());
        instance = new Rule();
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Rule();
        ((Rule) obj).addAntecedent("Foo", "term1", new ValidCompatibilityTestOperator());
        instance = new Rule();
        instance.addAntecedent("Foo", "term1", new ValidCompatibilityTestOperator());
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Rule();
        ((Rule) obj).addConsequent("Foo", "term1");
        instance = new Rule();
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new Rule();
        ((Rule) obj).addConsequent("Foo", "term1");
        instance = new Rule();
        instance.addConsequent("Foo", "term1");
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        AbstractOperator aggOp = new ValidAggregationTestOperator();
        obj = new Rule();
        ((Rule) obj).setAggregationOperator(aggOp);
        instance = new Rule();
        instance.setAggregationOperator(aggOp);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        AbstractOperator certOp = new ValidCertaintyTestOperator();
        obj = new Rule();
        ((Rule) obj).setCertaintyOperator(certOp);
        instance = new Rule();
        instance.setCertaintyOperator(certOp);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        AbstractOperator infOp = new ValidInferenceTestOperator();
        obj = new Rule();
        ((Rule) obj).setInferenceOperator(infOp);
        instance = new Rule();
        instance.setInferenceOperator(infOp);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Rule.
     */
    @Test
    public final void testHashCode() {
        System.out.println("hashCode");
        Rule instance = new Rule();
        int expResult = new Rule().hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

        Rule.setDefaultCertainty(1.0f);
        instance = new Rule();
        Rule obj = new Rule();
        obj.setCertainty(0.6f);
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        instance = new Rule();
        instance.setCertainty(0.6f);
        obj = new Rule();
        obj.setCertainty(0.6f);
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult == result);

        instance = new Rule();
        obj = new Rule();
        obj.addAntecedent("Foo", "term1");
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        instance = new Rule();
        instance.addAntecedent("Foo", "term1");
        obj = new Rule();
        obj.addAntecedent("Foo", "term1");
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult == result);

        instance = new Rule();
        obj = new Rule();
        obj.addAntecedent("Foo", "term1", new ValidCompatibilityTestOperator());
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        instance = new Rule();
        instance.addAntecedent("Foo", "term1", new ValidCompatibilityTestOperator());
        obj = new Rule();
        obj.addAntecedent("Foo", "term1", new ValidCompatibilityTestOperator());
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult == result);

        instance = new Rule();
        obj = new Rule();
        obj.addConsequent("Foo", "term1");
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        instance = new Rule();
        instance.addConsequent("Foo", "term1");
        obj = new Rule();
        obj.addConsequent("Foo", "term1");
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult == result);

        AbstractOperator certOp = new ValidCertaintyTestOperator();
        instance = new Rule();
        obj = new Rule();
        obj.setCertaintyOperator(certOp);
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        instance = new Rule();
        instance.setCertaintyOperator(certOp);
        obj = new Rule();
        obj.setCertaintyOperator(certOp);
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult == result);

        AbstractOperator aggOp = new ValidAggregationTestOperator();
        instance = new Rule();
        obj = new Rule();
        obj.setAggregationOperator(aggOp);
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        instance = new Rule();
        instance.setAggregationOperator(aggOp);
        obj = new Rule();
        obj.setAggregationOperator(aggOp);
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult == result);

        AbstractOperator infOp = new ValidInferenceTestOperator();
        instance = new Rule();
        obj = new Rule();
        obj.setInferenceOperator(infOp);
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        instance = new Rule();
        instance.setInferenceOperator(infOp);
        obj = new Rule();
        obj.setInferenceOperator(infOp);
        expResult = obj.hashCode();
        result = instance.hashCode();
        assertTrue(expResult == result);
    }

    /**
     * Test of toString method, of class Rule.
     */
    @Test
    public final void testToString() {
        System.out.println("toString");
        Rule instance = new Rule();
        String result = instance.toString();
        assertNotNull(result);
    }

    /**
     * Test of toString method, of class Rule.
     */
    @Test
    public final void testToString_boolean() {
        System.out.println("toString");
        boolean oneLine = false;
        Rule instance = new Rule();
        String result = instance.toString(oneLine);
        assertNotNull(result);

        oneLine = true;
        instance = new Rule();
        result = instance.toString(oneLine);
        assertNotNull(result);
    }
}