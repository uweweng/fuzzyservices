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
import net.sourceforge.fuzzyservices.beans.FuzzyLRInterval;
import net.sourceforge.fuzzyservices.beans.MembershipFunction;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of class FuzzyLRIntervalDao.
 *
 * @author Uwe Weng
 */
public class FuzzyLRIntervalDaoTest {

    /**
     * Test of create method, of class FuzzyLRIntervalDao.
     */
    @Test
    public void testCreate() throws PropertyVetoException {
        System.out.println("create");
        FuzzyLRIntervalDao instance = new FuzzyLRIntervalDao();
        FuzzyLRInterval expResult = new FuzzyLRInterval();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        FuzzyLRInterval result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());

        instance = new FuzzyLRIntervalDao();
        MembershipFunction membershipFunction = new MembershipFunction(-3.0f, -2.0f, 1.0f, 1.0f);
        expResult = new FuzzyLRInterval();
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
     * Test of removeById method, of class FuzzyLRIntervalDao.
     */
    @Test
    public void testRemoveById() {
        System.out.println("removeById");
        FuzzyLRIntervalDao instance = new FuzzyLRIntervalDao();
        FuzzyLRInterval expResult = new FuzzyLRInterval();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        FuzzyLRInterval result = instance.findById(id);
        assertNull(result);

        instance = new FuzzyLRIntervalDao();
        MembershipFunction membershipFunction = new MembershipFunction(-3.0f, -2.0f, 1.0f, 1.0f);
        expResult = new FuzzyLRInterval();
        expResult.setMembershipFunction(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of remove method, of class FuzzyLRIntervalDao.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        FuzzyLRIntervalDao instance = new FuzzyLRIntervalDao();
        FuzzyLRInterval expResult = new FuzzyLRInterval();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        FuzzyLRInterval result = instance.findById(id);
        assertNull(result);

        instance = new FuzzyLRIntervalDao();
        MembershipFunction membershipFunction = new MembershipFunction(-3.0f, -2.0f, 1.0f, 1.0f);
        expResult = new FuzzyLRInterval();
        expResult.setMembershipFunction(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of update method, of class FuzzyLRIntervalDao.
     */
    @Test
    public void testUpdate() throws PropertyVetoException {
        System.out.println("update");
        FuzzyLRIntervalDao instance = new FuzzyLRIntervalDao();
        FuzzyLRInterval expResult = new FuzzyLRInterval();
        expResult.setMembershipFunction(new MembershipFunction(5.0f, 7.0f, 1.0f, 1.0f));
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        expResult = instance.update(expResult);
        FuzzyLRInterval result = instance.findById(id);
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
     * Test of size method, of class FuzzyLRIntervalDao.
     */
    @Test
    public void testSize() throws PropertyVetoException {
        System.out.println("size");
        FuzzyLRIntervalDao instance = new FuzzyLRIntervalDao();
        int expResult = instance.size() + 1;
        FuzzyLRInterval FuzzyLRInterval = new FuzzyLRInterval();
        instance.create(FuzzyLRInterval);
        int result = instance.size();
        assertEquals(expResult, result);
        expResult = expResult - 1;
        instance.remove(FuzzyLRInterval);
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class FuzzyLRIntervalDao.
     */
    @Test
    public void testFindAll() throws PropertyVetoException {
        System.out.println("findAll");
        FuzzyLRIntervalDao instance = new FuzzyLRIntervalDao();
        int expResult = instance.size();
        List<FuzzyLRInterval> result = instance.findAll();
        assertEquals(expResult, result.size());

        instance = new FuzzyLRIntervalDao();
        FuzzyLRInterval fuzzyLRInterval = new FuzzyLRInterval();
        instance.create(fuzzyLRInterval);
        result = instance.findAll();
        assertTrue(result.contains(fuzzyLRInterval));
        // Cleaning
        instance.removeById(fuzzyLRInterval.getId());
    }

    /**
     * Test of iterate method, of class FuzzyLRIntervalDao.
     */
    @Test
    public void testIterate() throws PropertyVetoException {
        System.out.println("iterate");
        FuzzyLRIntervalDao instance = new FuzzyLRIntervalDao();

        int size = instance.size();
        Iterable<FuzzyLRInterval> result = instance.iterate(size, 0);
        assertFalse(result.iterator().hasNext());
        
        result = instance.iterate(size, 1);
        assertFalse(result.iterator().hasNext());

        instance = new FuzzyLRIntervalDao();
        FuzzyLRInterval fuzzyLRInterval = new FuzzyLRInterval();
        instance.create(fuzzyLRInterval);
        size = instance.size();
        result = instance.iterate(size - 1, 1);
        assertEquals(fuzzyLRInterval, result.iterator().next());
        // Cleaning
        instance.removeById(fuzzyLRInterval.getId());

        // Checking order
        instance = new FuzzyLRIntervalDao();
        FuzzyLRInterval fuzzyLRInterval1 = new FuzzyLRInterval();
        FuzzyLRInterval fuzzyLRInterval2 = new FuzzyLRInterval();
        instance.create(fuzzyLRInterval1);
        instance.create(fuzzyLRInterval2);
        size = instance.size();
        result = instance.iterate(size - 2, 1);
        assertEquals(fuzzyLRInterval1, result.iterator().next());
        result = instance.iterate(size - 1, 1);
        assertEquals(fuzzyLRInterval2, result.iterator().next());
        result = instance.iterate(size - 2, 2);
        Iterator<FuzzyLRInterval> it = result.iterator();
        assertEquals(fuzzyLRInterval1, it.next());
        assertEquals(fuzzyLRInterval2, it.next());
        // Cleaning
        instance.removeById(fuzzyLRInterval1.getId());
        instance.removeById(fuzzyLRInterval2.getId());
    }
    
    /**
     * Test of findById method, of class FuzzyLRIntervalDao.
     */
    @Test
    public void testFindById() throws PropertyVetoException {
        System.out.println("findById");
        FuzzyLRIntervalDao instance = new FuzzyLRIntervalDao();
        FuzzyLRInterval expResult = null;
        FuzzyLRInterval result = instance.findById(-1);
        assertEquals(expResult, result);

        instance = new FuzzyLRIntervalDao();
        expResult = new FuzzyLRInterval();
        instance.create(expResult);
        int id = expResult.getId();
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }
}