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
package net.sourceforge.fuzzyservices.beans.dao;

import net.sourceforge.fuzzyservices.core.operator.EinsteinProduct;
import net.sourceforge.fuzzyservices.beans.Antecedent;
import net.sourceforge.fuzzyservices.core.operator.HamacherProduct;
import net.sourceforge.fuzzyservices.core.operator.HamacherIntersection;
import net.sourceforge.fuzzyservices.core.operator.DrasticProduct;
import net.sourceforge.fuzzyservices.beans.OperatorUtils;
import java.beans.PropertyVetoException;
import net.sourceforge.fuzzyservices.beans.Consequent;
import net.sourceforge.fuzzyservices.beans.Rule;
import net.sourceforge.fuzzyservices.beans.RuleBase;
import net.sourceforge.fuzzyservices.core.operator.BoundedDifference;
import net.sourceforge.fuzzyservices.core.operator.DrasticSum;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class RuleBaseDao.
 *
 * @author Uwe Weng
 */
public class RuleBaseDaoTest {

    /**
     * Test of create method, of class RuleBaseDao.
     */
    @Test
    public void testCreate() throws PropertyVetoException {
        System.out.println("create");
        RuleBaseDao instance = new RuleBaseDao();
        RuleBase expResult = new RuleBase();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        RuleBase result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());

        instance = new RuleBaseDao();
        expResult = new RuleBase("foo1");
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertNotNull(result.getName());
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());

        instance = new RuleBaseDao();
        expResult = new RuleBase("foo2");
        Rule[] rules = new Rule[0];
        expResult.setRules(rules);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertNotNull(result.getRules());
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());

        instance = new RuleBaseDao();
        expResult = new RuleBase("foo3");
        rules = new Rule[]{getTestRuleWithDefaults(), getNewTestRule()};
        expResult.setRules(rules);
        expResult.setAccumulationOperator(OperatorUtils.convert(new DrasticSum()));
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }

    /**
     * Test of removeById method, of class RuleBaseDao.
     */
    @Test
    public void testRemoveById() {
        System.out.println("removeById");
        RuleBaseDao instance = new RuleBaseDao();
        RuleBase expResult = new RuleBase();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        RuleBase result = instance.findById(id);
        assertNull(result);

        instance = new RuleBaseDao();
        expResult = new RuleBase("foo1");
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);

        instance = new RuleBaseDao();
        expResult = new RuleBase("foo2");
        Rule[] rules = new Rule[0];
        expResult.setRules(rules);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);

        instance = new RuleBaseDao();
        expResult = new RuleBase("foo3");
        rules = new Rule[]{getTestRuleWithDefaults(), getNewTestRule()};
        expResult.setRules(rules);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of remove method, of class RuleBaseDao.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        RuleBaseDao instance = new RuleBaseDao();
        RuleBase expResult = new RuleBase();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        RuleBase result = instance.findById(id);
        assertNull(result);

        instance = new RuleBaseDao();
        expResult = new RuleBase("foo1");
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);

        instance = new RuleBaseDao();
        expResult = new RuleBase("foo2");
        Rule[] rules = new Rule[0];
        expResult.setRules(rules);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);

        instance = new RuleBaseDao();
        expResult = new RuleBase("foo3");
        rules = new Rule[]{getTestRuleWithDefaults(), getNewTestRule()};
        expResult.setRules(rules);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of update method, of class RuleBaseDao.
     */
    @Test
    public void testUpdate() throws PropertyVetoException {
        System.out.println("update");
        RuleBaseDao instance = new RuleBaseDao();
        RuleBase expResult = new RuleBase();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        expResult = instance.update(expResult);
        RuleBase result = instance.findById(id);
        assertEquals(expResult, result);
        // Update values
        // (1) name
        expResult.setName("Foo");
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(expResult, result);
        // (2) rules
        expResult = new RuleBase("Foo");
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        Rule[] rules = new Rule[]{getTestRuleWithDefaults(), getNewTestRule()};
        expResult.setRules(rules);
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());

        // Complex object
        instance = new RuleBaseDao();
        expResult = new RuleBase("Foo");
        rules = new Rule[]{getTestRuleWithDefaults()};
        expResult.setRules(rules);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        // Update values
        expResult.setName(null);
        rules = new Rule[]{getNewTestRule()};
        expResult.setRules(rules);
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }

    /**
     * Test of size method, of class RuleBaseDao.
     */
    @Test
    public void testSize() throws PropertyVetoException {
        System.out.println("size");
        RuleBaseDao instance = new RuleBaseDao();
        long expResult = instance.size() + 1;
        RuleBase RuleBase = new RuleBase();
        instance.create(RuleBase);
        long result = instance.size();
        assertEquals(expResult, result);
        expResult = expResult - 1;
        instance.remove(RuleBase);
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findById method, of class RuleBaseDao.
     */
    @Test
    public void testFindById() throws PropertyVetoException {
        System.out.println("findById");
        RuleBaseDao instance = new RuleBaseDao();
        RuleBase expResult = null;
        RuleBase result = instance.findById(-1);
        assertEquals(expResult, result);

        instance = new RuleBaseDao();
        expResult = new RuleBase();
        instance.create(expResult);
        int id = expResult.getId();
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }

    /**
     * Test of findByName method, of class RuleBaseDao.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        RuleBaseDao instance = new RuleBaseDao();
        RuleBase expResult = null;
        try {
            RuleBase result = instance.findByName(null);
            fail();
        } catch (Exception e) {
        }

        instance = new RuleBaseDao();
        String name = "testFindByName";
        expResult = new RuleBase();
        expResult.setName(name);
        instance.create(expResult);
        RuleBase result = instance.findByName(name);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }

    private Rule getTestRuleWithDefaults() {
        Rule testRule = new Rule();
        Antecedent ante1 = new Antecedent();
        Consequent cons1 = new Consequent();
        testRule.setAntecedents(new Antecedent[]{ante1});
        testRule.setConsequents(new Consequent[]{cons1});
        return testRule;
    }

    private Rule getNewTestRule() {
        Rule testRule = new Rule();
        testRule.setCertainty(0.8f);
        testRule.setAggregationOperator(OperatorUtils.convert(new DrasticProduct()));
        testRule.setInferenceOperator(OperatorUtils.convert(new HamacherIntersection()));
        testRule.setCertaintyOperator(OperatorUtils.convert(new HamacherProduct()));
        Antecedent ante1 = new Antecedent("Foo", "A", OperatorUtils.convert(new BoundedDifference()));
        Antecedent ante2 = new Antecedent("Foo", "B", OperatorUtils.convert(new EinsteinProduct()));
        Consequent cons1 = new Consequent("Foo", "B");
        Consequent cons2 = new Consequent("Buu", "X");
        testRule.setAntecedents(new Antecedent[]{ante1, ante2});
        testRule.setConsequents(new Consequent[]{cons1, cons2});
        return testRule;
    }
}