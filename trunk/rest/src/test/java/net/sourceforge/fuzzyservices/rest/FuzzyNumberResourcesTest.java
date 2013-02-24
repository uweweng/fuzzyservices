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
import net.sourceforge.fuzzyservices.beans.FuzzyNumber;
import net.sourceforge.fuzzyservices.beans.MembershipFunction;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of class FuzzyNumberResources.
 *
 * @author Uwe Weng
 */
public class FuzzyNumberResourcesTest extends JerseyTest {

    public FuzzyNumberResourcesTest() throws Exception {
        super("net.sourceforge.fuzzyservices.rest");
    }

    /**
     * Returns the test object.
     *
     * @return a new resource for testing
     */
    private WebResource getTestResource() {
        return resource().path("fuzzynumbers");
    }

    /**
     * Test of getById method, of class FuzzyNumberResources.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        // Simple test with undefined id
        WebResource instance = getTestResource();
        ClientResponse response1 = instance.path("-1").get(ClientResponse.class);
        assertEquals(404, response1.getStatus());

        // Test with default membership function
        instance = getTestResource();
        FuzzyNumberResource result = null;
        MembershipFunction membershipFunction = null;
        FuzzyNumber fuzzyNumber = new FuzzyNumber();
        FuzzyNumberResource expResult = new FuzzyNumberResource(fuzzyNumber);
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(FuzzyNumberResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(FuzzyNumberResource.class);
            assertEquals(result.getBean(), expResult.getBean());
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Test with membership function
        instance = getTestResource();
        result = null;
        membershipFunction = new MembershipFunction(1.0f, 2.0f, 3.0f);
        fuzzyNumber.setMembershipFunction(membershipFunction);
        expResult = new FuzzyNumberResource(fuzzyNumber);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(FuzzyNumberResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(FuzzyNumberResource.class);
            assertEquals(result.getBean(), expResult.getBean());
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();
    }

    /**
     * Test of create method, of class FuzzyNumberResources.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        // Simple test with default membership function
        WebResource instance = getTestResource();
        FuzzyNumberResource result = null;
        MembershipFunction membershipFunction = null;
        FuzzyNumber fuzzyNumber = new FuzzyNumber();
        FuzzyNumberResource expResult = new FuzzyNumberResource(fuzzyNumber);
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(FuzzyNumberResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(FuzzyNumberResource.class);
        } catch (UniformInterfaceException e) {
            fail();
        }
        assertNotNull(result.getBean().getMembershipFunction());
        assertEquals(expResult, result);
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Complex test with a defined membership function
        instance = getTestResource();
        result = null;
        membershipFunction = new MembershipFunction(1.0f, 1.0f);
        fuzzyNumber = new FuzzyNumber();
        fuzzyNumber.setMembershipFunction(membershipFunction);
        expResult = new FuzzyNumberResource(fuzzyNumber);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(FuzzyNumberResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(FuzzyNumberResource.class);
        } catch (UniformInterfaceException e) {
            fail();
        }
        assertNotNull(result.getBean().getMembershipFunction());
        assertEquals(expResult, result);
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Creating the same resource twice makes two different resources
        instance = getTestResource();
        result = null;
        membershipFunction = null;
        fuzzyNumber = new FuzzyNumber();
        expResult = new FuzzyNumberResource(fuzzyNumber);
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
     * Test of put method, of class FuzzyNumberResources.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        WebResource instance = getTestResource();
        MembershipFunction membershipFunction = null;
        FuzzyNumber fuzzyNumber = new FuzzyNumber();
        FuzzyNumberResource expResult = new FuzzyNumberResource(fuzzyNumber);
        // Creating the resource
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(FuzzyNumberResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        FuzzyNumberResource result = null;

        // Update without changes
        instance = getTestResource();
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(FuzzyNumberResource.class);

        instance = getTestResource();
        try {
            result = instance.uri(location).get(FuzzyNumberResource.class);
            assertEquals(result, expResult);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Update values
        // (1) new membership function
        expResult.getBean().setMembershipFunction(new MembershipFunction(1.0f, 1.0f));
        // Updating the resource
        instance = getTestResource();
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(FuzzyNumberResource.class);
        instance = getTestResource();
        try {
            result = instance.uri(location).get(FuzzyNumberResource.class);
            // Comparing values
            assertEquals(expResult, result);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();

        // Complex object
        membershipFunction = new MembershipFunction(1.0f, 1.0f);
        fuzzyNumber = new FuzzyNumber();
        fuzzyNumber.setMembershipFunction(membershipFunction);
        expResult = new FuzzyNumberResource(fuzzyNumber);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(FuzzyNumberResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        // Update values
        // New complex membership function
        instance = getTestResource();
        expResult.getBean().setMembershipFunction(new MembershipFunction(1.0f, 2.0f, 1.0f));
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        expResult = response.getEntity(FuzzyNumberResource.class);

        instance = getTestResource();
        try {
            result = instance.uri(location).get(FuzzyNumberResource.class);
            assertEquals(result, expResult);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(location).delete();
    }

    /**
     * Test of delete method, of class FuzzyNumberResources.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        WebResource instance = getTestResource();
        MembershipFunction membershipFunction = null;
        FuzzyNumber fuzzyNumber = new FuzzyNumber();
        FuzzyNumberResource expResult = new FuzzyNumberResource(fuzzyNumber);
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        expResult = response.getEntity(FuzzyNumberResource.class);
        int id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        instance.uri(location).delete();
        try {
            instance = getTestResource();
            instance.uri(location).get(FuzzyNumberResource.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(404, e.getResponse().getStatus());
        }

        // With complex membership function
        instance = getTestResource();
        membershipFunction = new MembershipFunction(1.0f, 1.0f);
        fuzzyNumber = new FuzzyNumber();
        fuzzyNumber.setMembershipFunction(membershipFunction);
        expResult = new FuzzyNumberResource(fuzzyNumber);
        response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        location = response.getLocation();
        expResult = response.getEntity(FuzzyNumberResource.class);
        id = expResult.getBean().getId();
        assertNotNull(id);
        instance = getTestResource();
        instance.uri(location).delete();
        try {
            instance = getTestResource();
            instance.uri(location).get(FuzzyNumberResource.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(404, e.getResponse().getStatus());
        }
    }
}
