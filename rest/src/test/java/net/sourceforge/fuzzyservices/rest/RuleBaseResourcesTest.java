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
package net.sourceforge.fuzzyservices.rest;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;
import java.net.URI;
import net.sourceforge.fuzzyservices.beans.Antecedent;
import net.sourceforge.fuzzyservices.beans.Consequent;
import net.sourceforge.fuzzyservices.beans.OperatorUtils;
import net.sourceforge.fuzzyservices.beans.Rule;
import net.sourceforge.fuzzyservices.beans.RuleBase;
import net.sourceforge.fuzzyservices.core.operator.BoundedDifference;
import net.sourceforge.fuzzyservices.core.operator.DrasticProduct;
import net.sourceforge.fuzzyservices.core.operator.DrasticSum;
import net.sourceforge.fuzzyservices.core.operator.EinsteinProduct;
import net.sourceforge.fuzzyservices.core.operator.HamacherIntersection;
import net.sourceforge.fuzzyservices.core.operator.HamacherProduct;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of class RuleBaseResources.
 *
 * @author Uwe Weng
 */
public class RuleBaseResourcesTest extends JerseyTest {

    public RuleBaseResourcesTest() throws Exception {
        super("net.sourceforge.fuzzyservices.rest");
    }

    /**
     * Returns the test object.
     *
     * @return a new resource for testing
     */
    private WebResource getTestResource() {
        return resource().path("rule_bases");
    }

    /**
     * Test of getById method, of class RuleBaseResources.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        // Simple test with undefined id
        WebResource instance = getTestResource();
        ClientResponse response1 = instance.path("-1").get(ClientResponse.class);
        assertEquals(404, response1.getStatus());

        // Test without rules
        instance = getTestResource();
        RuleBaseResource result = null;
        String ruleBaseName = null;
        Rule[] rules = null;
        RuleBase ruleBase = new RuleBase(ruleBaseName);
        ruleBase.setRules(rules);
        RuleBaseResource expResult = new RuleBaseResource(ruleBase);
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(RuleBaseResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(RuleBaseResource.class);
            assertEquals(result.getBean(), expResult.getBean());
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Test with rules
        instance = getTestResource();
        result = null;
        ruleBaseName = "foo";
        rules = new Rule[0];
        ruleBase = new RuleBase(ruleBaseName);
        ruleBase.setRules(rules);
        expResult = new RuleBaseResource(ruleBase);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(RuleBaseResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(RuleBaseResource.class);
            assertEquals(result.getBean(), expResult.getBean());
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Test with complex rules
        instance = getTestResource();
        result = null;
        ruleBaseName = "foo2";
        rules = new Rule[2];
        rules[0] = getTestRuleWithDefaults();
        rules[1] = getNewTestRule();
        ruleBase = new RuleBase(ruleBaseName);
        ruleBase.setRules(rules);
        ruleBase.setAccumulationOperator(OperatorUtils.convert(new DrasticSum()));
        expResult = new RuleBaseResource(ruleBase);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(RuleBaseResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(RuleBaseResource.class);
            assertEquals(result.getBean(), expResult.getBean());
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();
    }

    /**
     * Test of create method, of class RuleBaseResources.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        // Simple test without a rule
        WebResource instance = getTestResource();
        RuleBaseResource result = null;
        String ruleBaseName = null;
        Rule[] rules = null;
        RuleBase ruleBase = new RuleBase(ruleBaseName);
        ruleBase.setRules(rules);
        RuleBaseResource expResult = new RuleBaseResource(ruleBase);
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(RuleBaseResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(RuleBaseResource.class);
        } catch (UniformInterfaceException e) {
            fail();
        }
        assertEquals(expResult, result);
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Complex test with rules
        instance = getTestResource();
        result = null;
        ruleBaseName = "foo";
        rules = new Rule[0];
        ruleBase = new RuleBase(ruleBaseName);
        ruleBase.setRules(rules);
        expResult = new RuleBaseResource(ruleBase);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(RuleBaseResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(RuleBaseResource.class);
        } catch (UniformInterfaceException e) {
            fail();
        }
        assertEquals(expResult, result);
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Complex test with a defined rule
        instance = getTestResource();
        result = null;
        ruleBaseName = "foo2";
        rules = new Rule[2];
        rules[0] = getTestRuleWithDefaults();
        rules[1] = getNewTestRule();
        ruleBase = new RuleBase(ruleBaseName);
        ruleBase.setRules(rules);
        ruleBase.setAccumulationOperator(OperatorUtils.convert(new DrasticSum()));
        expResult = new RuleBaseResource(ruleBase);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(RuleBaseResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(RuleBaseResource.class);
        } catch (UniformInterfaceException e) {
            fail();
        }
        assertEquals(expResult, result);
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Creating the same resource twice makes two different resources
        instance = getTestResource();
        result = null;
        ruleBaseName = null;
        rules = null;
        ruleBase = new RuleBase(ruleBaseName);
        ruleBase.setRules(rules);
        expResult = new RuleBaseResource(ruleBase);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location1 = response.getLocation();
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location2 = response.getLocation();
        assertNotSame(location2, location1);
        // Cleaning
        instance = getTestResource();
        instance.uri(location1).delete();
        instance = getTestResource();
        instance.uri(location2).delete();
    }

    /**
     * Test of put method, of class RuleBaseResources.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        WebResource instance = getTestResource();
        String ruleBaseName = null;
        Rule[] rules = null;
        RuleBase ruleBase = new RuleBase(ruleBaseName);
        ruleBase.setRules(rules);
        RuleBaseResource expResult = new RuleBaseResource(ruleBase);
        // Creating the resource
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(RuleBaseResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        RuleBaseResource result = null;

        // Update without changes
        instance = getTestResource();
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(RuleBaseResource.class);

        instance = getTestResource();
        try {
            result = instance.uri(location).get(RuleBaseResource.class);
            assertEquals(result, expResult);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Update values
        // (1) new rule
        ruleBaseName = null;
        rules = new Rule[0];
        expResult.getBean().setRules(rules);
        // Updating the resource
        instance = getTestResource();
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(RuleBaseResource.class);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(RuleBaseResource.class);
            // Comparing values
            assertEquals(expResult, result);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Complex object
        ruleBaseName = "foo";
        rules = new Rule[0];
        ruleBase = new RuleBase(ruleBaseName);
        ruleBase.setRules(rules);
        expResult = new RuleBaseResource(ruleBase);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(RuleBaseResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        // Update rules
        // New complex rule
        instance = getTestResource();
        rules = new Rule[2];
        rules[0] = getTestRuleWithDefaults();
        rules[1] = getNewTestRule();
        expResult.getBean().setRules(rules);
        // And new operator
        expResult.getBean().setAccumulationOperator(OperatorUtils.convert(new DrasticSum()));
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(RuleBaseResource.class);

        instance = getTestResource();
        try {
            result = instance.uri(location).get(RuleBaseResource.class);
            assertEquals(result, expResult);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Clear rules
        expResult.getBean().setRules(null);
        // Updating the resource
        instance = getTestResource();
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(RuleBaseResource.class);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(RuleBaseResource.class);
            // Comparing values
            assertEquals(expResult, result);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();
    }

    /**
     * Test of delete method, of class RuleBaseResources.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        WebResource instance = getTestResource();
        String ruleBaseName = null;
        Rule[] rules = null;
        RuleBase ruleBase = new RuleBase(ruleBaseName);
        ruleBase.setRules(rules);
        RuleBaseResource expResult = new RuleBaseResource(ruleBase);
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(RuleBaseResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        instance.uri(location).delete();
        try {
            instance = getTestResource();
            instance.uri(location).get(RuleBaseResource.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(404, e.getResponse().getStatus());
        }

        // With rules
        instance = getTestResource();
        ruleBaseName = "foo";
        rules = new Rule[0];
        ruleBase = new RuleBase(ruleBaseName);
        ruleBase.setRules(rules);
        expResult = new RuleBaseResource(ruleBase);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(RuleBaseResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        instance.uri(location).delete();
        try {
            instance = getTestResource();
            instance.uri(location).get(RuleBaseResource.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(404, e.getResponse().getStatus());
        }

        // With complex rules
        instance = getTestResource();
        ruleBaseName = "foo2";
        rules = new Rule[2];
        rules[0] = getTestRuleWithDefaults();
        rules[1] = getNewTestRule();
        ruleBase = new RuleBase(ruleBaseName);
        ruleBase.setRules(rules);
        ruleBase.setAccumulationOperator(OperatorUtils.convert(new DrasticSum()));
        expResult = new RuleBaseResource(ruleBase);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(RuleBaseResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        instance.uri(location).delete();
        try {
            instance = getTestResource();
            instance.uri(location).get(RuleBaseResource.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(404, e.getResponse().getStatus());
        }
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
