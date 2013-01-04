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
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of class MyResources.
 *
 * @author Uwe Weng
 */
public class MyResourcesTest extends JerseyTest {

    public MyResourcesTest() throws Exception {
        super("net.sourceforge.fuzzyservices.rest");
    }
    
    /**
     * Returns the test object.
     * @return a new resource for testing
     */
    private WebResource getTestResource() {
        return resource().path("myresources");
    }
    
    /**
     * Test of getById method, of class MyResources.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        WebResource instance = getTestResource();
        MyResource expResult = null;
        MyResource result = null;
        ClientResponse response1 = instance.path("-1").get(ClientResponse.class);
        assertEquals(404, response1.getStatus());
        
        instance = getTestResource();
        expResult = new MyResource();
        expResult.setBean(new MyResourceBean("Foo"));
        ClientResponse response2 = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response2.getStatus());
        expResult = response2.getEntity(MyResource.class);
        assertNotNull(expResult.getBean().getId());
        
        instance = getTestResource();
        ClientResponse response3 = instance.uri(response2.getLocation()).get(ClientResponse.class);
        result = response3.getEntity(MyResource.class);
        assertEquals(expResult, result);
        // Cleaning
        instance = getTestResource();
        instance.uri(response2.getLocation()).delete();
//        instance.path(String.valueOf(result.getBean().getId())).delete();
//        instance.entity(result).delete();
    }

    /**
     * Test of createMyResource method, of class MyResources.
     */
/**    
    @Test
    public void testCreateMyResource() {
        System.out.println("createMyResource");
        WebResource instance = getTestResource();
        MyResource expResult = new MyResource(new MyResourceBean("Foo"));
        MyResource result = null;
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        
        instance = getTestResource();
        try {
            result = instance.uri(response.getLocation()).get(MyResource.class);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Cleaning
        instance = getTestResource();
        instance.uri(response.getLocation()).delete();
        
        instance = getTestResource();
        expResult = new MyResource(new MyResourceBean("Foo"));
        result = null;
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
*/
    /**
     * Test of updateMyResource method, of class MyResources.
     */
/**    
    @Test
    public void testUpdateMyResource() {
        System.out.println("updateMyResource");
        WebResource instance = getTestResource();
        MyResource expResult = new MyResource(new MyResourceBean(null));
        MyResource result = null;
        // Creating the resource
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        instance = getTestResource();
        try {
            result = instance.get(MyResource.class);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Update values
        expResult = result;
        expResult.getBean().setName("Foo");
        // Updating the resource
        instance = getTestResource();
        response = instance.entity(expResult).put(ClientResponse.class);
        assertEquals(200, response.getStatus());
        instance = getTestResource();
        try {
            result = instance.get(MyResource.class);
        } catch (UniformInterfaceException e) {
            fail();
        }
        // Comparing values
        assertEquals(expResult.getBean().getName(), result.getBean().getName());
        // Cleaning
        instance = getTestResource();
        instance.delete();
    }
*/
    /**
     * Test of removeMyResourceById method, of class MyResources.
     */
/**
    @Test
    public void testRemoveMyResourceById() {
        System.out.println("removeMyResourceById");
        WebResource instance = getTestResource();
        MyResource expResult = new MyResource();
        MyResource result = null;
        ClientResponse response = instance.entity(expResult).post(ClientResponse.class);
        assertEquals(201, response.getStatus());
        URI location = response.getLocation();
        instance = getTestResource();
        instance.delete();
        instance = getTestResource();
        try {
            result = instance.get(MyResource.class);
            fail();
        } catch (UniformInterfaceException e) {
            assertEquals(404, e.getResponse().getStatus());
        }
    }
*/
}