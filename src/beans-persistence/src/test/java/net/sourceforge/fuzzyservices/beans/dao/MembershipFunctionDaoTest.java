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

import java.beans.PropertyVetoException;
import java.util.Iterator;
import java.util.List;
import net.sourceforge.fuzzyservices.beans.MembershipFunction;
import net.sourceforge.fuzzyservices.beans.MembershipFunctionPoint;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of class MembershipFunctionDao.
 *
 * @author Uwe Weng
 */
public class MembershipFunctionDaoTest {

    /**
     * Test of create method, of class MembershipFunctionDao.
     */
    @Test
    public void testCreate() throws PropertyVetoException {
        System.out.println("create");
        MembershipFunctionDao instance = new MembershipFunctionDao();
        MembershipFunction expResult = new MembershipFunction(null);
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        MembershipFunction result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());

        instance = new MembershipFunctionDao();
        MembershipFunctionPoint[] points = new MembershipFunctionPoint[0];
        expResult = new MembershipFunction(points);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());

        instance = new MembershipFunctionDao();
        points = new MembershipFunctionPoint[1];
        points[0] = new MembershipFunctionPoint(1.0f, 1.0f);
        expResult = new MembershipFunction(points);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }

    /**
     * Test of removeById method, of class MembershipFunctionDao.
     */
    @Test
    public void testRemoveById() {
        System.out.println("removeById");
        MembershipFunctionDao instance = new MembershipFunctionDao();
        MembershipFunction expResult = new MembershipFunction(null);
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        MembershipFunction result = instance.findById(id);
        assertNull(result);

        instance = new MembershipFunctionDao();
        MembershipFunctionPoint[] points = new MembershipFunctionPoint[0];
        expResult = new MembershipFunction(points);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);

        instance = new MembershipFunctionDao();
        points = new MembershipFunctionPoint[1];
        points[0] = new MembershipFunctionPoint(1.0f, 1.0f);
        expResult = new MembershipFunction(points);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of remove method, of class MembershipFunctionDao.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        MembershipFunctionDao instance = new MembershipFunctionDao();
        MembershipFunction expResult = new MembershipFunction(null);
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        MembershipFunction result = instance.findById(id);
        assertNull(result);

        instance = new MembershipFunctionDao();
        MembershipFunctionPoint[] points = new MembershipFunctionPoint[0];
        expResult = new MembershipFunction(points);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);

        instance = new MembershipFunctionDao();
        points = new MembershipFunctionPoint[1];
        points[0] = new MembershipFunctionPoint(1.0f, 1.0f);
        expResult = new MembershipFunction(points);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of update method, of class MembershipFunctionDao.
     */
    @Test
    public void testUpdate() throws PropertyVetoException {
        System.out.println("update");
        MembershipFunctionDao instance = new MembershipFunctionDao();
        MembershipFunction expResult = new MembershipFunction(null);
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        expResult = instance.update(expResult);
        MembershipFunction result = instance.findById(id);
        assertEquals(expResult, result);
        // Update values
        // (1) new membership function
        MembershipFunctionPoint[] points = new MembershipFunctionPoint[0];
        expResult.setDegreeOfMembership(1.0f, 1.0f);
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());

        // Complex object
        instance = new MembershipFunctionDao();
        points = new MembershipFunctionPoint[3];
        points[0] = new MembershipFunctionPoint(0.0f, 0.0f);
        points[1] = new MembershipFunctionPoint(1.0f, 1.0f);
        points[2] = new MembershipFunctionPoint(2.0f, 0.0f);
        expResult = new MembershipFunction(points);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        // Update values
        // Clear membership function
        expResult.clear();
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }

    /**
     * Test of size method, of class MembershipFunctionDao.
     */
    @Test
    public void testSize() throws PropertyVetoException {
        System.out.println("size");
        MembershipFunctionDao instance = new MembershipFunctionDao();
        int expResult = instance.size() + 1;
        MembershipFunction MembershipFunction = new MembershipFunction();
        instance.create(MembershipFunction);
        int result = instance.size();
        assertEquals(expResult, result);
        expResult = expResult - 1;
        instance.remove(MembershipFunction);
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class MembershipFunctionDao.
     */
    @Test
    public void testFindAll() throws PropertyVetoException {
        System.out.println("findAll");
        MembershipFunctionDao instance = new MembershipFunctionDao();
        int expResult = instance.size();
        List<MembershipFunction> result = instance.findAll();
        assertEquals(expResult, result.size());

        instance = new MembershipFunctionDao();
        MembershipFunction membershipFunction = new MembershipFunction();
        instance.create(membershipFunction);
        result = instance.findAll();
        assertTrue(result.contains(membershipFunction));
        // Cleaning
        instance.removeById(membershipFunction.getId());
    }

    /**
     * Test of iterate method, of class MembershipFunctionDao.
     */
    @Test
    public void testIterate() throws PropertyVetoException {
        System.out.println("iterate");
        MembershipFunctionDao instance = new MembershipFunctionDao();

        int size = instance.size();
        Iterable<MembershipFunction> result = instance.iterate(size, 0);
        assertFalse(result.iterator().hasNext());
        
        result = instance.iterate(size, 1);
        assertFalse(result.iterator().hasNext());

        instance = new MembershipFunctionDao();
        MembershipFunction membershipFunction = new MembershipFunction();
        instance.create(membershipFunction);
        size = instance.size();
        result = instance.iterate(size - 1, 1);
        assertEquals(membershipFunction, result.iterator().next());
        // Cleaning
        instance.removeById(membershipFunction.getId());

        // Checking order
        instance = new MembershipFunctionDao();
        MembershipFunction membershipFunction1 = new MembershipFunction();
        MembershipFunction membershipFunction2 = new MembershipFunction();
        instance.create(membershipFunction1);
        instance.create(membershipFunction2);
        size = instance.size();
        result = instance.iterate(size - 2, 1);
        assertEquals(membershipFunction1, result.iterator().next());
        result = instance.iterate(size - 1, 1);
        assertEquals(membershipFunction2, result.iterator().next());
        result = instance.iterate(size - 2, 2);
        Iterator<MembershipFunction> it = result.iterator();
        assertEquals(membershipFunction1, it.next());
        assertEquals(membershipFunction2, it.next());
        // Cleaning
        instance.removeById(membershipFunction1.getId());
        instance.removeById(membershipFunction2.getId());
    }
    
    /**
     * Test of findById method, of class MembershipFunctionDao.
     */
    @Test
    public void testFindById() throws PropertyVetoException {
        System.out.println("findById");
        MembershipFunctionDao instance = new MembershipFunctionDao();
        MembershipFunction expResult = null;
        MembershipFunction result = instance.findById(-1);
        assertEquals(expResult, result);

        instance = new MembershipFunctionDao();
        expResult = new MembershipFunction();
        instance.create(expResult);
        int id = expResult.getId();
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }
}
