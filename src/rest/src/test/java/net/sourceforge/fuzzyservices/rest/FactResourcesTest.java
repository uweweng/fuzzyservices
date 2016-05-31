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
import net.sourceforge.fuzzyservices.beans.Fact;
import net.sourceforge.fuzzyservices.beans.FuzzySet;
import net.sourceforge.fuzzyservices.beans.MembershipFunction;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of class FactResources.
 *
 * @author Uwe Weng
 */
public class FactResourcesTest extends JerseyTest {

    public FactResourcesTest() throws Exception {
        super("net.sourceforge.fuzzyservices.rest");
    }

    /**
     * Returns the test object.
     *
     * @return a new resource for testing
     */
    private WebResource getTestResource() {
        return resource().path("facts");
    }

    /**
     * Test of getById method, of class FactResources.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        // Simple test with undefined id
        WebResource instance = getTestResource();
        ClientResponse response1 = instance.path("-1").get(ClientResponse.class);
        assertEquals(404, response1.getStatus());

        // Test without value
        instance = getTestResource();
        FactResource result = null;
        String linguisticVariableName = null;
        MembershipFunction membershipFunction = null;
        FuzzySet value = null;
        FactResource expResult = new FactResource(new Fact(linguisticVariableName, value));
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(FactResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(FactResource.class);
            assertEquals(result.getBean(), expResult.getBean());
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Test with value
        instance = getTestResource();
        result = null;
        linguisticVariableName = "foo";
        membershipFunction = new MembershipFunction(1.0f, 2.0f, 3.0f);
        value = new FuzzySet(membershipFunction);
        expResult = new FactResource(new Fact(linguisticVariableName, value));
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(FactResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(FactResource.class);
            assertEquals(result.getBean(), expResult.getBean());
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();
    }

    /**
     * Test of create method, of class FactResources.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        // Simple test without a value
        WebResource instance = getTestResource();
        FactResource result = null;
        String linguisticVariableName = null;
        MembershipFunction membershipFunction = null;
        FuzzySet value = null;
        FactResource expResult = new FactResource(new Fact(linguisticVariableName, value));
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(FactResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(FactResource.class);
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
        membershipFunction = new MembershipFunction();
        value = new FuzzySet(membershipFunction);
        expResult = new FactResource(new Fact(linguisticVariableName, value));
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(FactResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(FactResource.class);
        } catch (UniformInterfaceException e) {
            fail();
        }
        assertEquals(expResult, result);
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Complex test with a defined membership function
        instance = getTestResource();
        result = null;
        linguisticVariableName = "foo2";
        membershipFunction = new MembershipFunction(1.0f, 1.0f);
        value = new FuzzySet(membershipFunction);
        expResult = new FactResource(new Fact(linguisticVariableName, value));
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(FactResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(FactResource.class);
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
        linguisticVariableName = null;
        membershipFunction = null;
        value = null;
        expResult = new FactResource(new Fact(linguisticVariableName, value));
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
     * Test of put method, of class FactResources.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        WebResource instance = getTestResource();
        String linguisticVariableName = null;
        MembershipFunction membershipFunction = null;
        FuzzySet value = null;
        FactResource expResult = new FactResource(new Fact(linguisticVariableName, value));
        // Creating the resource
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(FactResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        FactResource result = null;

        // Update without changes
        instance = getTestResource();
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(FactResource.class);

        instance = getTestResource();
        try {
            result = instance.uri(location).get(FactResource.class);
            assertEquals(result, expResult);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Update values
        // (1) new membership function
        linguisticVariableName = "foo";
        membershipFunction = new MembershipFunction();
        value = new FuzzySet(membershipFunction);
        expResult.getBean().setValue(value);
        // Updating the resource
        instance = getTestResource();
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(FactResource.class);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(FactResource.class);
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
        membershipFunction = new MembershipFunction(1.0f, 1.0f);
        value = new FuzzySet(membershipFunction);
        expResult = new FactResource(new Fact(linguisticVariableName, value));
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(FactResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        // Update values
        // New complex value
        instance = getTestResource();
        membershipFunction = new MembershipFunction(1.0f, 2.0f, 1.0f, 1.0f);
        value = new FuzzySet(membershipFunction);
        expResult.getBean().setValue(value);
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(FactResource.class);

        instance = getTestResource();
        try {
            result = instance.uri(location).get(FactResource.class);
            assertEquals(result, expResult);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Clear value
        expResult.getBean().setValue(null);
        // Updating the resource
        instance = getTestResource();
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(FactResource.class);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(FactResource.class);
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
     * Test of delete method, of class FactResources.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        WebResource instance = getTestResource();
        String linguisticVariableName = null;
        MembershipFunction membershipFunction = null;
        FuzzySet value = null;
        FactResource expResult = new FactResource(new Fact(linguisticVariableName, value));
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(FactResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        instance.uri(location).delete();
        try {
            instance = getTestResource();
            instance.uri(location).get(FactResource.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(404, e.getResponse().getStatus());
        }

        // With value
        instance = getTestResource();
        linguisticVariableName = "foo";
        membershipFunction = new MembershipFunction();
        value = new FuzzySet(membershipFunction);
        expResult = new FactResource(new Fact(linguisticVariableName, value));
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(FactResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        instance.uri(location).delete();
        try {
            instance = getTestResource();
            instance.uri(location).get(FactResource.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(404, e.getResponse().getStatus());
        }

        // With complex value
        instance = getTestResource();
        linguisticVariableName = "foo2";
        membershipFunction = new MembershipFunction(1.0f, 1.0f);
        value = new FuzzySet(membershipFunction);
        expResult = new FactResource(new Fact(linguisticVariableName, value));
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(FactResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        instance.uri(location).delete();
        try {
            instance = getTestResource();
            instance.uri(location).get(FactResource.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(404, e.getResponse().getStatus());
        }
    }
}
