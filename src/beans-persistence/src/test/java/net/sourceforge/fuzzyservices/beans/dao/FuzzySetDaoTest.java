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
import net.sourceforge.fuzzyservices.beans.FuzzySet;
import net.sourceforge.fuzzyservices.beans.MembershipFunction;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of class FuzzySetDao.
 *
 * @author Uwe Weng
 */
public class FuzzySetDaoTest {

    /**
     * Test of create method, of class FuzzySetDao.
     */
    @Test
    public void testCreate() throws PropertyVetoException {
        System.out.println("create");
        FuzzySetDao instance = new FuzzySetDao();
        FuzzySet expResult = new FuzzySet(null);
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        FuzzySet result = instance.findById(id);
        assertNull(result.getMembershipFunction());
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());

        instance = new FuzzySetDao();
        MembershipFunction membershipFunction = new MembershipFunction();
        expResult = new FuzzySet(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertNotNull(result.getMembershipFunction());
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());

        instance = new FuzzySetDao();
        membershipFunction = new MembershipFunction(1.0f, 1.0f);
        expResult = new FuzzySet(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertNotNull(result.getMembershipFunction());
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }

    /**
     * Test of removeById method, of class FuzzySetDao.
     */
    @Test
    public void testRemoveById() {
        System.out.println("removeById");
        FuzzySetDao instance = new FuzzySetDao();
        FuzzySet expResult = new FuzzySet(null);
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        FuzzySet result = instance.findById(id);
        assertNull(result);

        instance = new FuzzySetDao();
        MembershipFunction membershipFunction = new MembershipFunction();
        expResult = new FuzzySet(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);

        instance = new FuzzySetDao();
        membershipFunction = new MembershipFunction(1.0f, 1.0f);
        expResult = new FuzzySet(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of remove method, of class FuzzySetDao.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        FuzzySetDao instance = new FuzzySetDao();
        FuzzySet expResult = new FuzzySet(null);
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        FuzzySet result = instance.findById(id);
        assertNull(result);

        instance = new FuzzySetDao();
        MembershipFunction membershipFunction = new MembershipFunction();
        expResult = new FuzzySet(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);

        instance = new FuzzySetDao();
        membershipFunction = new MembershipFunction(1.0f, 1.0f);
        expResult = new FuzzySet(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of update method, of class FuzzySetDao.
     */
    @Test
    public void testUpdate() throws PropertyVetoException {
        System.out.println("update");
        FuzzySetDao instance = new FuzzySetDao();
        FuzzySet expResult = new FuzzySet(null);
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        expResult = instance.update(expResult);
        FuzzySet result = instance.findById(id);
        assertEquals(result, expResult);
        // Update values
        // (1) new membership function
        expResult.setMembershipFunction(new MembershipFunction());
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(result, expResult);
        // Cleaning
        instance.removeById(expResult.getId());

        // Complex object
        instance = new FuzzySetDao();
        MembershipFunction membershipFunction = new MembershipFunction(1.0f, 1.0f);
        expResult = new FuzzySet(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        // Update values
        // New complex membership function
        expResult.setMembershipFunction(new MembershipFunction(1.0f, 2.0f, 1.0f, 1.0f));
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(result, expResult);
        // Clear membership function
        expResult.setMembershipFunction(null);
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(result, expResult);
        // Cleaning
        instance.removeById(expResult.getId());
    }

    /**
     * Test of size method, of class FuzzySetDao.
     */
    @Test
    public void testSize() throws PropertyVetoException {
        System.out.println("size");
        FuzzySetDao instance = new FuzzySetDao();
        int expResult = instance.size() + 1;
        FuzzySet FuzzySet = new FuzzySet();
        instance.create(FuzzySet);
        int result = instance.size();
        assertEquals(expResult, result);
        expResult = expResult - 1;
        instance.remove(FuzzySet);
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class FuzzySetDao.
     */
    @Test
    public void testFindAll() throws PropertyVetoException {
        System.out.println("findAll");
        FuzzySetDao instance = new FuzzySetDao();
        int expResult = instance.size();
        List<FuzzySet> result = instance.findAll();
        assertEquals(expResult, result.size());

        instance = new FuzzySetDao();
        FuzzySet fuzzySet = new FuzzySet();
        instance.create(fuzzySet);
        result = instance.findAll();
        assertTrue(result.contains(fuzzySet));
        // Cleaning
        instance.removeById(fuzzySet.getId());
    }

    /**
     * Test of iterate method, of class FuzzySetDao.
     */
    @Test
    public void testIterate() throws PropertyVetoException {
        System.out.println("iterate");
        FuzzySetDao instance = new FuzzySetDao();

        int size = instance.size();
        Iterable<FuzzySet> result = instance.iterate(size, 0);
        assertFalse(result.iterator().hasNext());
        
        result = instance.iterate(size, 1);
        assertFalse(result.iterator().hasNext());

        instance = new FuzzySetDao();
        FuzzySet fuzzySet = new FuzzySet();
        instance.create(fuzzySet);
        size = instance.size();
        result = instance.iterate(size - 1, 1);
        assertEquals(fuzzySet, result.iterator().next());
        // Cleaning
        instance.removeById(fuzzySet.getId());

        // Checking order
        instance = new FuzzySetDao();
        FuzzySet fuzzySet1 = new FuzzySet();
        FuzzySet fuzzySet2 = new FuzzySet();
        instance.create(fuzzySet1);
        instance.create(fuzzySet2);
        size = instance.size();
        result = instance.iterate(size - 2, 1);
        assertEquals(fuzzySet1, result.iterator().next());
        result = instance.iterate(size - 1, 1);
        assertEquals(fuzzySet2, result.iterator().next());
        result = instance.iterate(size - 2, 2);
        Iterator<FuzzySet> it = result.iterator();
        assertEquals(fuzzySet1, it.next());
        assertEquals(fuzzySet2, it.next());
        // Cleaning
        instance.removeById(fuzzySet1.getId());
        instance.removeById(fuzzySet2.getId());
    }
    
    /**
     * Test of findById method, of class FuzzySetDao.
     */
    @Test
    public void testFindById() throws PropertyVetoException {
        System.out.println("findById");
        FuzzySetDao instance = new FuzzySetDao();
        FuzzySet expResult = null;
        FuzzySet result = instance.findById(-1);
        assertEquals(expResult, result);

        instance = new FuzzySetDao();
        expResult = new FuzzySet();
        instance.create(expResult);
        int id = expResult.getId();
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }
}