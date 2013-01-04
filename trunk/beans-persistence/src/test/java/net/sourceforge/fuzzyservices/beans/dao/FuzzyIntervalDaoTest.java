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
import net.sourceforge.fuzzyservices.beans.FuzzyInterval;
import net.sourceforge.fuzzyservices.beans.MembershipFunction;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of class FuzzyIntervalDao.
 *
 * @author Uwe Weng
 */
public class FuzzyIntervalDaoTest {

    /**
     * Test of create method, of class FuzzyIntervalDao.
     */
    @Test
    public void testCreate() throws PropertyVetoException {
        System.out.println("create");
        FuzzyIntervalDao instance = new FuzzyIntervalDao();
        FuzzyInterval expResult = new FuzzyInterval();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        FuzzyInterval result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());

        instance = new FuzzyIntervalDao();
        MembershipFunction membershipFunction = new MembershipFunction(-3.0f, -2.0f, 1.0f, 1.0f);
        expResult = new FuzzyInterval();
        expResult.setMembershipFunction(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }

    /**
     * Test of removeById method, of class FuzzyIntervalDao.
     */
    @Test
    public void testRemoveById() {
        System.out.println("removeById");
        FuzzyIntervalDao instance = new FuzzyIntervalDao();
        FuzzyInterval expResult = new FuzzyInterval();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        FuzzyInterval result = instance.findById(id);
        assertNull(result);

        instance = new FuzzyIntervalDao();
        MembershipFunction membershipFunction = new MembershipFunction(-3.0f, -2.0f, 1.0f, 1.0f);
        expResult = new FuzzyInterval();
        expResult.setMembershipFunction(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of remove method, of class FuzzyIntervalDao.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        FuzzyIntervalDao instance = new FuzzyIntervalDao();
        FuzzyInterval expResult = new FuzzyInterval();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        FuzzyInterval result = instance.findById(id);
        assertNull(result);

        instance = new FuzzyIntervalDao();
        MembershipFunction membershipFunction = new MembershipFunction(-3.0f, -2.0f, 1.0f, 1.0f);
        expResult = new FuzzyInterval();
        expResult.setMembershipFunction(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of update method, of class FuzzyIntervalDao.
     */
    @Test
    public void testUpdate() throws PropertyVetoException {
        System.out.println("update");
        FuzzyIntervalDao instance = new FuzzyIntervalDao();
        FuzzyInterval expResult = new FuzzyInterval();
        expResult.setMembershipFunction(new MembershipFunction(5.0f, 7.0f, 1.0f, 1.0f));
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        expResult = instance.update(expResult);
        FuzzyInterval result = instance.findById(id);
        assertEquals(result, expResult);
        // Update values
        // (1) new membership function
        expResult.setMembershipFunction(new MembershipFunction(-3.0f, -2.0f, 1.0f, 1.0f));
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(result, expResult);
        // Cleaning
        instance.removeById(expResult.getId());
    }

    /**
     * Test of size method, of class FuzzyIntervalDao.
     */
    @Test
    public void testSize() throws PropertyVetoException {
        System.out.println("size");
        FuzzyIntervalDao instance = new FuzzyIntervalDao();
        int expResult = instance.size() + 1;
        FuzzyInterval FuzzyInterval = new FuzzyInterval();
        instance.create(FuzzyInterval);
        int result = instance.size();
        assertEquals(expResult, result);
        expResult = expResult - 1;
        instance.remove(FuzzyInterval);
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class FuzzyIntervalDao.
     */
    @Test
    public void testFindAll() throws PropertyVetoException {
        System.out.println("findAll");
        FuzzyIntervalDao instance = new FuzzyIntervalDao();
        int expResult = instance.size();
        List<FuzzyInterval> result = instance.findAll();
        assertEquals(expResult, result.size());

        instance = new FuzzyIntervalDao();
        FuzzyInterval fuzzyInterval = new FuzzyInterval();
        instance.create(fuzzyInterval);
        result = instance.findAll();
        assertTrue(result.contains(fuzzyInterval));
        // Cleaning
        instance.removeById(fuzzyInterval.getId());
    }

    /**
     * Test of iterate method, of class FuzzyIntervalDao.
     */
    @Test
    public void testIterate() throws PropertyVetoException {
        System.out.println("iterate");
        FuzzyIntervalDao instance = new FuzzyIntervalDao();

        int size = instance.size();
        Iterable<FuzzyInterval> result = instance.iterate(size, 0);
        assertFalse(result.iterator().hasNext());
        
        result = instance.iterate(size, 1);
        assertFalse(result.iterator().hasNext());

        instance = new FuzzyIntervalDao();
        FuzzyInterval fuzzyInterval = new FuzzyInterval();
        instance.create(fuzzyInterval);
        size = instance.size();
        result = instance.iterate(size - 1, 1);
        assertEquals(fuzzyInterval, result.iterator().next());
        // Cleaning
        instance.removeById(fuzzyInterval.getId());

        // Checking order
        instance = new FuzzyIntervalDao();
        FuzzyInterval fuzzyInterval1 = new FuzzyInterval();
        FuzzyInterval fuzzyInterval2 = new FuzzyInterval();
        instance.create(fuzzyInterval1);
        instance.create(fuzzyInterval2);
        size = instance.size();
        result = instance.iterate(size - 2, 1);
        assertEquals(fuzzyInterval1, result.iterator().next());
        result = instance.iterate(size - 1, 1);
        assertEquals(fuzzyInterval2, result.iterator().next());
        result = instance.iterate(size - 2, 2);
        Iterator<FuzzyInterval> it = result.iterator();
        assertEquals(fuzzyInterval1, it.next());
        assertEquals(fuzzyInterval2, it.next());
        // Cleaning
        instance.removeById(fuzzyInterval1.getId());
        instance.removeById(fuzzyInterval2.getId());
    }
    
    /**
     * Test of findById method, of class FuzzyIntervalDao.
     */
    @Test
    public void testFindById() throws PropertyVetoException {
        System.out.println("findById");
        FuzzyIntervalDao instance = new FuzzyIntervalDao();
        FuzzyInterval expResult = null;
        FuzzyInterval result = instance.findById(-1);
        assertEquals(expResult, result);

        instance = new FuzzyIntervalDao();
        expResult = new FuzzyInterval();
        instance.create(expResult);
        int id = expResult.getId();
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }
}