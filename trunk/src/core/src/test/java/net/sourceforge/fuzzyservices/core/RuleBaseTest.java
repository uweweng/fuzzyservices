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
 * Test of class RuleBase.
 *
 * @author Uwe Weng
 */
public class RuleBaseTest {

    /**
     * Operator for tests.
     *
     * @version        1.0
     */
    public static class ValidAccumulationTestOperator extends AbstractOperator {

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
            return true;
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
    public static class InvalidAccumulationTestOperator extends AbstractOperator {

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
            return false;
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
     * Test of add method, of class RuleBase.
     */
    @Test
    public final void testAdd() {
        System.out.println("add");
        Rule rule = null;
        RuleBase instance = new RuleBase();
        assertEquals(instance.size(), 0);
        instance.add(rule);
        assertEquals(instance.size(), 0);

        rule = new Rule();
        instance = new RuleBase();
        assertEquals(instance.size(), 0);
        instance.add(rule);
        assertEquals(instance.size(), 1);
    }

    /**
     * Test of clear method, of class RuleBase.
     */
    @Test
    public final void testClear() {
        System.out.println("clear");
        RuleBase instance = new RuleBase();
        assertEquals(instance.size(), 0);
        instance.clear();
        assertEquals(instance.size(), 0);

        Rule rule = new Rule();
        instance = new RuleBase();
        instance.add(rule);
        assertEquals(instance.size(), 1);
        instance.clear();
        assertEquals(instance.size(), 0);
    }

    /**
     * Test of contains method, of class RuleBase.
     */
    @Test
    public final void testContains() {
        System.out.println("contains");
        Rule rule = null;
        RuleBase instance = new RuleBase();
        boolean expResult = false;
        boolean result = instance.contains(rule);
        assertEquals(expResult, result);

        rule = new Rule();
        instance = new RuleBase();
        expResult = false;
        result = instance.contains(rule);
        assertEquals(expResult, result);

        rule = new Rule();
        instance = new RuleBase();
        expResult = true;
        instance.add(rule);
        result = instance.contains(rule);
        assertEquals(expResult, result);

        rule = new Rule();
        instance = new RuleBase();
        expResult = true;
        instance.add(rule);
        result = instance.contains(rule);
        assertEquals(expResult, result);
        expResult = false;
        instance.remove(rule);
        result = instance.contains(rule);
        assertEquals(expResult, result);

        // Rule base uses equals method of rule.
        rule = new Rule();
        instance = new RuleBase();
        expResult = true;
        instance.add(rule);
        rule = new Rule();
        result = instance.contains(rule);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsLinguisticVariable method, of class RuleBase.
     */
    @Test
    public final void testContainsLinguisticVariable() {
        System.out.println("containsLinguisticVariable");
        String lingVarName = null;
        Rule rule = null;
        RuleBase instance = new RuleBase();
        boolean expResult = false;
        boolean result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);

        lingVarName = "Foo";
        rule = null;
        instance = new RuleBase();
        expResult = false;
        result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);

        lingVarName = "Foo";
        rule = new Rule();
        rule.addAntecedent(lingVarName, "term1");
        rule.addConsequent("Foo2", "term1");
        instance = new RuleBase();
        instance.add(rule);
        expResult = true;
        result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);

        lingVarName = "Foo";
        rule = new Rule();
        rule.addAntecedent("Foo2", "term1");
        rule.addConsequent(lingVarName, "term1");
        instance = new RuleBase();
        instance.add(rule);
        expResult = true;
        result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);

        // Case sensitive
        lingVarName = "Foo";
        rule = new Rule();
        rule.addAntecedent(lingVarName.toUpperCase(), "term1");
        rule.addConsequent("Foo2", "term1");
        instance = new RuleBase();
        instance.add(rule);
        expResult = false;
        result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);

        lingVarName = "Foo";
        rule = new Rule();
        rule.addAntecedent("Foo2", "term1");
        rule.addConsequent(lingVarName.toUpperCase(), "term1");
        instance = new RuleBase();
        instance.add(rule);
        expResult = false;
        result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class RuleBase.
     */
    @Test
    public final void testIterator() {
        System.out.println("iterator");
        RuleBase instance = new RuleBase();
        Iterator<Rule> result = instance.iterator();
        assertNotNull(result);
    }

    /**
     * Test of getAccumulationOperator method, of class RuleBase.
     */
    @Test
    public final void testGetAccumulationOperator() {
        System.out.println("getAccumulationOperator");
        RuleBase instance = new RuleBase();
        AbstractOperator expResult = new ValidAccumulationTestOperator();
        instance.setAccumulationOperator(expResult);
        AbstractOperator result = instance.getAccumulationOperator();
        assertSame(expResult, result);

        // Initialised by default operator
        instance = new RuleBase();
        result = instance.getAccumulationOperator();
        assertSame(result, RuleBase.getDefaultAccumulationOperator());
    }

    /**
     * Test of getDefaultAccumulationOperator method, of class RuleBase.
     */
    @Test
    public final void testGetDefaultAccumulationOperator() {
        System.out.println("getDefaultAccumulationOperator");
        AbstractOperator result = RuleBase.getDefaultAccumulationOperator();
        assertNotNull(result);
        // Operator has to fulfill the s-norm
        assertTrue(result.isValidSNorm());
    }

    /**
     * Test of getName method, of class RuleBase.
     */
    @Test
    public final void testGetName() {
        System.out.println("getName");
        RuleBase instance = new RuleBase();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);

        instance = new RuleBase("Foo");
        expResult = "Foo";
        result = instance.getName();
        assertEquals(expResult, result);

        instance = new RuleBase(null, null);
        expResult = null;
        result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class RuleBase.
     */
    @Test
    public final void testIsEmpty() {
        System.out.println("isEmpty");
        RuleBase instance = new RuleBase();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);

        instance = new RuleBase();
        assertTrue(instance.isEmpty());
        instance.add(new Rule());
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);

        instance = new RuleBase();
        assertTrue(instance.isEmpty());
        instance.add(new Rule());
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class RuleBase.
     */
    @Test
    public final void testRemove() {
        System.out.println("remove");
        Rule rule = null;
        RuleBase instance = new RuleBase();
        boolean expResult = false;
        boolean result = instance.remove(rule);
        assertEquals(expResult, result);

        rule = new Rule();
        instance = new RuleBase();
        expResult = false;
        result = instance.remove(rule);
        assertEquals(expResult, result);

        rule = new Rule();
        instance = new RuleBase();
        expResult = true;
        instance.add(rule);
        result = instance.remove(rule);
        assertEquals(expResult, result);

        rule = new Rule();
        instance = new RuleBase();
        expResult = false;
        instance.add(rule);
        instance.remove(rule);
        result = instance.remove(rule);
        assertEquals(expResult, result);
    }

    /**
     * Test of setAccumulationOperator method, of class RuleBase.
     */
    @Test
    public final void testSetAccumulationOperator() {
        System.out.println("setAccumulationOperator");
        AbstractOperator accOp = new ValidAccumulationTestOperator();
        RuleBase instance = new RuleBase();
        instance.setAccumulationOperator(accOp);
        assertSame(instance.getAccumulationOperator(), accOp);
    }

    /**
     * Test of setAccumulationOperator method, of class RuleBase.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetAccumulationOperator1() {
        System.out.println("setAccumulationOperator");
        AbstractOperator accOp = new InvalidAccumulationTestOperator();
        RuleBase instance = new RuleBase();
        instance.setAccumulationOperator(accOp);
    }

    /**
     * Test of setAccumulationOperator method, of class RuleBase.
     */
    @Test(expected = NullPointerException.class)
    public final void testSetAccumulationOperator2() {
        System.out.println("setAccumulationOperator");
        AbstractOperator accOp = null;
        RuleBase instance = new RuleBase();
        instance.setAccumulationOperator(accOp);
    }

    /**
     * Test of setDefaultAccumulationOperator method, of class RuleBase.
     */
    @Test
    public final void testSetDefaultAccumulationOperator() {
        System.out.println("setDefaultAccumulationOperator");
        AbstractOperator accOp = new ValidAccumulationTestOperator();
        RuleBase.setDefaultAccumulationOperator(accOp);
        assertSame(RuleBase.getDefaultAccumulationOperator(), accOp);
    }

    /**
     * Test of setDefaultAccumulationOperator method, of class RuleBase.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetDefaultAccumulationOperator1() {
        System.out.println("setDefaultAccumulationOperator");
        AbstractOperator accOp = new InvalidAccumulationTestOperator();
        RuleBase.setDefaultAccumulationOperator(accOp);
    }

    /**
     * Test of setDefaultAccumulationOperator method, of class RuleBase.
     */
    @Test(expected = NullPointerException.class)
    public final void testSetDefaultAccumulationOperator2() {
        System.out.println("setDefaultAccumulationOperator");
        AbstractOperator accOp = null;
        RuleBase.setDefaultAccumulationOperator(accOp);
    }

    /**
     * Test of setName method, of class RuleBase.
     */
    @Test
    public final void testSetName() {
        System.out.println("setName");
        String name = null;
        RuleBase instance = new RuleBase();
        instance.setName(name);
        assertNull(instance.getName());

        name = "Foo";
        instance = new RuleBase();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of size method, of class RuleBase.
     */
    @Test
    public final void testSize() {
        System.out.println("size");
        Rule rule = null;
        RuleBase instance = new RuleBase();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);

        // Adding increases size.
        rule = new Rule();
        instance = new RuleBase();
        instance.add(rule);
        expResult = 1;
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of clone method, of class RuleBase.
     */
    @Test
    public final void testClone() {
        System.out.println("clone");
        RuleBase instance = new RuleBase();
        Object expResult = new RuleBase();
        Object result = instance.clone();
        assertEquals(expResult, result);

        instance = new RuleBase("foo");
        expResult = new RuleBase("foo");
        result = instance.clone();
        assertEquals(expResult, result);

        Rule rule = new Rule();
        instance = new RuleBase("foo", rule);
        expResult = new RuleBase("foo", rule);
        result = instance.clone();
        assertEquals(expResult, result);

        rule = new Rule();
        instance = new RuleBase("foo", null);
        expResult = new RuleBase("foo", rule);
        result = instance.clone();
        assertFalse(result.equals(expResult));

        AbstractOperator accOp = new ValidAccumulationTestOperator();
        instance = new RuleBase();
        instance.setAccumulationOperator(accOp);
        expResult = new RuleBase();
        ((RuleBase) expResult).setAccumulationOperator(accOp);
        result = instance.clone();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class RuleBase.
     */
    @Test
    public final void testEquals() {
        System.out.println("equals");
        Object obj = null;
        RuleBase instance = new RuleBase();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new RuleBase();
        instance = new RuleBase();
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new RuleBase(null, null);
        instance = new RuleBase("Foo");
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new RuleBase("Foo");
        instance = new RuleBase("Foo");
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        Rule rule = new Rule();
        obj = new RuleBase(rule);
        instance = new RuleBase(rule);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        AbstractOperator accOp = new ValidAccumulationTestOperator();
        obj = new RuleBase();
        ((RuleBase) obj).setAccumulationOperator(accOp);
        instance = new RuleBase();
        instance.setAccumulationOperator(accOp);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class RuleBase.
     */
    @Test
    public final void testHashCode() {
        System.out.println("hashCode");
        RuleBase instance = new RuleBase();
        int expResult = new RuleBase().hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new RuleBase("foo");
        expResult = new RuleBase("foo").hashCode();
        result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new RuleBase("foo");
        expResult = new RuleBase("FOO").hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        Rule rule = new Rule();
        instance = new RuleBase("foo", rule);
        expResult = new RuleBase("foo", rule).hashCode();
        result = instance.hashCode();
        assertTrue(expResult == result);

        rule = new Rule();
        instance = new RuleBase("foo", null);
        expResult = new RuleBase("foo", rule).hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        rule = new Rule();
        instance = new RuleBase("foo", rule);
        expResult = new RuleBase("Foo", rule).hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);

        AbstractOperator accOp = new ValidAccumulationTestOperator();
        instance = new RuleBase();
        instance.setAccumulationOperator(accOp);
        expResult = new RuleBase().hashCode();
        result = instance.hashCode();
        assertTrue(expResult != result);
    }

    /**
     * Test of toString method, of class RuleBase.
     */
    @Test
    public final void testToString() {
        System.out.println("toString");
        RuleBase instance = new RuleBase();
        String result = instance.toString();
        assertNotNull(result);
    }

    /**
     * Test of toString method, of class RuleBase.
     */
    @Test
    public final void testToString_boolean() {
        System.out.println("toString");
        boolean withRules = false;
        RuleBase instance = new RuleBase();
        String result = instance.toString(withRules);
        assertNotNull(result);

        withRules = true;
        instance = new RuleBase();
        result = instance.toString(withRules);
        assertNotNull(result);
    }
}