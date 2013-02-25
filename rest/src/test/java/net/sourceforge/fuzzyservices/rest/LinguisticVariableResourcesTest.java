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
import java.beans.PropertyVetoException;
import java.net.URI;
import net.sourceforge.fuzzyservices.beans.FuzzySet;
import net.sourceforge.fuzzyservices.beans.LinguisticTerm;
import net.sourceforge.fuzzyservices.beans.LinguisticVariable;
import net.sourceforge.fuzzyservices.beans.MembershipFunction;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of class LinguisticVariableResources.
 *
 * @author Uwe Weng
 */
public class LinguisticVariableResourcesTest extends JerseyTest {

    public LinguisticVariableResourcesTest() throws Exception {
        super("net.sourceforge.fuzzyservices.rest");
    }

    /**
     * Returns the test object.
     *
     * @return a new resource for testing
     */
    private WebResource getTestResource() {
        return resource().path("linguistic_variables");
    }

    /**
     * Test of getById method, of class LinguisticVariableResources.
     */
    @Test
    public void testGetById() throws PropertyVetoException {
        System.out.println("getById");
        // Simple test with undefined id
        WebResource instance = getTestResource();
        ClientResponse response1 = instance.path("-1").get(ClientResponse.class);
        assertEquals(404, response1.getStatus());

        // Test without linguistic term
        instance = getTestResource();
        LinguisticVariableResource result = null;
        String linguisticVariableName = null;
        String linguisticTermName = null;
        MembershipFunction membershipFunction = null;
        FuzzySet fuzzySet = null;
        LinguisticTerm linguisticTerm = null;
        LinguisticVariable linguisticVariable = new LinguisticVariable();
        LinguisticVariableResource expResult = new LinguisticVariableResource(linguisticVariable);
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(LinguisticVariableResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(LinguisticVariableResource.class);
            assertEquals(result.getBean(), expResult.getBean());
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Test with linguistic term
        instance = getTestResource();
        result = null;
        linguisticVariableName = "foo";
        linguisticTermName = "a";
        membershipFunction = new MembershipFunction(1.0f, 2.0f, 3.0f);
        fuzzySet = new FuzzySet(membershipFunction);
        linguisticTerm = new LinguisticTerm(linguisticTermName, fuzzySet);
        linguisticVariable = new LinguisticVariable();
        linguisticVariable.setName(linguisticVariableName);
        linguisticVariable.setLinguisticTerms(new LinguisticTerm[]{linguisticTerm});
        expResult = new LinguisticVariableResource(linguisticVariable);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(LinguisticVariableResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(LinguisticVariableResource.class);
            assertEquals(result.getBean(), expResult.getBean());
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();
    }

    /**
     * Test of create method, of class LinguisticVariableResources.
     */
    @Test
    public void testCreate() throws PropertyVetoException {
        System.out.println("create");
        // Simple test without a value
        WebResource instance = getTestResource();
        LinguisticVariableResource result = null;
        String linguisticVariableName = null;
        String linguisticTermName = null;
        MembershipFunction membershipFunction = null;
        FuzzySet fuzzySet = null;
        LinguisticTerm linguisticTerm = null;
        LinguisticVariable linguisticVariable = new LinguisticVariable();
        LinguisticVariableResource expResult = new LinguisticVariableResource(linguisticVariable);
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(LinguisticVariableResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(LinguisticVariableResource.class);
        } catch (UniformInterfaceException e) {
            fail();
        }
        assertEquals(expResult, result);
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Complex test with a value
        instance = getTestResource();
        result = null;
        linguisticVariableName = "foo";
        linguisticTermName = "a";
        membershipFunction = null;
        fuzzySet = new FuzzySet(membershipFunction);
        linguisticTerm = new LinguisticTerm(linguisticTermName, fuzzySet);
        linguisticVariable = new LinguisticVariable();
        linguisticVariable.setName(linguisticVariableName);
        linguisticVariable.setLinguisticTerms(new LinguisticTerm[]{linguisticTerm});
        expResult = new LinguisticVariableResource(linguisticVariable);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(LinguisticVariableResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(LinguisticVariableResource.class);
        } catch (UniformInterfaceException e) {
            fail();
        }
        assertEquals(expResult, result);
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Complex test with a defined linguistic term
        instance = getTestResource();
        result = null;
        linguisticVariableName = "foo2";
        linguisticTermName = "x";
        membershipFunction = new MembershipFunction(1.0f, 2.0f, 3.0f);
        fuzzySet = new FuzzySet(membershipFunction);
        linguisticTerm = new LinguisticTerm(linguisticTermName, fuzzySet);
        linguisticVariable = new LinguisticVariable();
        linguisticVariable.setName(linguisticVariableName);
        linguisticVariable.setLinguisticTerms(new LinguisticTerm[]{linguisticTerm});
        expResult = new LinguisticVariableResource(linguisticVariable);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(LinguisticVariableResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(LinguisticVariableResource.class);
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
        linguisticVariableName = "foo3";
        linguisticTermName = null;
        membershipFunction = null;
        fuzzySet = null;
        linguisticTerm = null;
        linguisticVariable = new LinguisticVariable();
        linguisticVariable.setName(linguisticVariableName);
        expResult = new LinguisticVariableResource(linguisticVariable);
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
     * Test of put method, of class LinguisticVariableResources.
     */
    @Test
    public void testPut() throws PropertyVetoException {
        System.out.println("put");
        WebResource instance = getTestResource();
        String linguisticVariableName = null;
        String linguisticTermName = null;
        MembershipFunction membershipFunction = null;
        FuzzySet fuzzySet = null;
        LinguisticTerm linguisticTerm = null;
        LinguisticVariable linguisticVariable = new LinguisticVariable();
        LinguisticVariableResource expResult = new LinguisticVariableResource(linguisticVariable);
        // Creating the resource
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(LinguisticVariableResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        LinguisticVariableResource result = null;

        // Update without changes
        instance = getTestResource();
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(LinguisticVariableResource.class);

        instance = getTestResource();
        try {
            result = instance.uri(location).get(LinguisticVariableResource.class);
            assertEquals(result, expResult);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Update values
        // (1) new name
        linguisticVariableName = "foo";
        expResult.getBean().setName(linguisticVariableName);
        // Updating the resource
        instance = getTestResource();
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(LinguisticVariableResource.class);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(LinguisticVariableResource.class);
            // Comparing values
            assertEquals(expResult, result);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Complex object
        linguisticVariableName = "foo2";
        linguisticTermName = "a";
        membershipFunction = null;
        fuzzySet = new FuzzySet(membershipFunction);
        linguisticTerm = new LinguisticTerm(linguisticTermName, fuzzySet);
        linguisticVariable = new LinguisticVariable();
        linguisticVariable.setName(linguisticVariableName);
        linguisticVariable.setLinguisticTerms(new LinguisticTerm[]{linguisticTerm});
        expResult = new LinguisticVariableResource(linguisticVariable);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(LinguisticVariableResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        // Update linguistic terms
        // New complex linguistic term
        instance = getTestResource();
        membershipFunction = new MembershipFunction(1.0f, 2.0f, 1.0f, 1.0f);
        fuzzySet = new FuzzySet(membershipFunction);
        linguisticTerm.setFuzzySet(fuzzySet);
        expResult.getBean().setLinguisticTerms(0, linguisticTerm);
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(LinguisticVariableResource.class);

        instance = getTestResource();
        try {
            result = instance.uri(location).get(LinguisticVariableResource.class);
            assertEquals(result, expResult);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Clear linguistic term
        expResult.getBean().setLinguisticTerms(null);
        // Updating the resource
        instance = getTestResource();
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(LinguisticVariableResource.class);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(LinguisticVariableResource.class);
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
     * Test of delete method, of class LinguisticVariableResources.
     */
    @Test
    public void testDelete() throws PropertyVetoException {
        System.out.println("delete");
        WebResource instance = getTestResource();
        String linguisticVariableName = null;
        String linguisticTermName = null;
        MembershipFunction membershipFunction = null;
        FuzzySet fuzzySet = null;
        LinguisticTerm linguisticTerm = null;
        LinguisticVariable linguisticVariable = new LinguisticVariable();
        LinguisticVariableResource expResult = new LinguisticVariableResource(linguisticVariable);
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(LinguisticVariableResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        instance.uri(location).delete();
        try {
            instance = getTestResource();
            instance.uri(location).get(LinguisticVariableResource.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(404, e.getResponse().getStatus());
        }

        // With linguistic term
        instance = getTestResource();
        linguisticVariableName = "foo";
        linguisticTermName = "a";
        membershipFunction = null;
        fuzzySet = new FuzzySet(membershipFunction);
        linguisticTerm = new LinguisticTerm(linguisticTermName, fuzzySet);
        linguisticVariable = new LinguisticVariable();
        linguisticVariable.setName(linguisticVariableName);
        linguisticVariable.setLinguisticTerms(new LinguisticTerm[]{linguisticTerm});
        expResult = new LinguisticVariableResource(linguisticVariable);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(LinguisticVariableResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        instance.uri(location).delete();
        try {
            instance = getTestResource();
            instance.uri(location).get(LinguisticVariableResource.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(404, e.getResponse().getStatus());
        }

        // With complex linguistic term
        instance = getTestResource();
        linguisticVariableName = "foo2";
        linguisticTermName = "x";
        membershipFunction = new MembershipFunction(1.0f, 2.0f, 3.0f);
        fuzzySet = new FuzzySet(membershipFunction);
        linguisticTerm = new LinguisticTerm(linguisticTermName, fuzzySet);
        linguisticVariable = new LinguisticVariable();
        linguisticVariable.setName(linguisticVariableName);
        linguisticVariable.setLinguisticTerms(new LinguisticTerm[]{linguisticTerm});
        expResult = new LinguisticVariableResource(linguisticVariable);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(LinguisticVariableResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        instance.uri(location).delete();
        try {
            instance = getTestResource();
            instance.uri(location).get(LinguisticVariableResource.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(404, e.getResponse().getStatus());
        }
    }
}
