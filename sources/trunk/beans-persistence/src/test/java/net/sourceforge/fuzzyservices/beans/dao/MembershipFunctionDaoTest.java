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

import net.sourceforge.fuzzyservices.beans.MembershipFunctionPoint;
import java.beans.PropertyVetoException;
import net.sourceforge.fuzzyservices.beans.MembershipFunction;
import org.junit.Test;
import static org.junit.Assert.*;

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
        long expResult = instance.size() + 1;
        MembershipFunction MembershipFunction = new MembershipFunction();
        instance.create(MembershipFunction);
        long result = instance.size();
        assertEquals(expResult, result);
        expResult = expResult - 1;
        instance.remove(MembershipFunction);
        result = instance.size();
        assertEquals(expResult, result);
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
