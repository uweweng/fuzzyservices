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
import net.sourceforge.fuzzyservices.beans.FuzzyNumber;
import net.sourceforge.fuzzyservices.beans.MembershipFunction;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of class FuzzyNumberDao.
 *
 * @author Uwe Weng
 */
public class FuzzyNumberDaoTest {

    /**
     * Test of create method, of class FuzzyNumberDao.
     */
    @Test
    public void testCreate() throws PropertyVetoException {
        System.out.println("create");
        FuzzyNumberDao instance = new FuzzyNumberDao();
        FuzzyNumber expResult = new FuzzyNumber();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        FuzzyNumber result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());

        instance = new FuzzyNumberDao();
        MembershipFunction membershipFunction = new MembershipFunction(-1.0f, 1.0f);
        expResult = new FuzzyNumber();
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
     * Test of removeById method, of class FuzzyNumberDao.
     */
    @Test
    public void testRemoveById() {
        System.out.println("removeById");
        FuzzyNumberDao instance = new FuzzyNumberDao();
        FuzzyNumber expResult = new FuzzyNumber();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        FuzzyNumber result = instance.findById(id);
        assertNull(result);

        instance = new FuzzyNumberDao();
        MembershipFunction membershipFunction = new MembershipFunction(-1.0f, 1.0f);
        expResult = new FuzzyNumber();
        expResult.setMembershipFunction(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of remove method, of class FuzzyNumberDao.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        FuzzyNumberDao instance = new FuzzyNumberDao();
        FuzzyNumber expResult = new FuzzyNumber();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        FuzzyNumber result = instance.findById(id);
        assertNull(result);

        instance = new FuzzyNumberDao();
        MembershipFunction membershipFunction = new MembershipFunction(-1.0f, 1.0f);
        expResult = new FuzzyNumber();
        expResult.setMembershipFunction(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of update method, of class FuzzyNumberDao.
     */
    @Test
    public void testUpdate() throws PropertyVetoException {
        System.out.println("update");
        FuzzyNumberDao instance = new FuzzyNumberDao();
        FuzzyNumber expResult = new FuzzyNumber();
        expResult.setMembershipFunction(new MembershipFunction(1.0f, 1.0f));
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        expResult = instance.update(expResult);
        FuzzyNumber result = instance.findById(id);
        assertEquals(result, expResult);
        // Update values
        // (1) new membership function
        expResult.setMembershipFunction(new MembershipFunction(-1.0f, 1.0f));
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(result, expResult);
        // Cleaning
        instance.removeById(expResult.getId());
    }

    /**
     * Test of size method, of class FuzzyNumberDao.
     */
    @Test
    public void testSize() throws PropertyVetoException {
        System.out.println("size");
        FuzzyNumberDao instance = new FuzzyNumberDao();
        int expResult = instance.size() + 1;
        FuzzyNumber FuzzyNumber = new FuzzyNumber();
        instance.create(FuzzyNumber);
        int result = instance.size();
        assertEquals(expResult, result);
        expResult = expResult - 1;
        instance.remove(FuzzyNumber);
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class FuzzyNumberDao.
     */
    @Test
    public void testFindAll() throws PropertyVetoException {
        System.out.println("findAll");
        FuzzyNumberDao instance = new FuzzyNumberDao();
        int expResult = instance.size();
        List<FuzzyNumber> result = instance.findAll();
        assertEquals(expResult, result.size());

        instance = new FuzzyNumberDao();
        FuzzyNumber fuzzyNumber = new FuzzyNumber();
        instance.create(fuzzyNumber);
        result = instance.findAll();
        assertTrue(result.contains(fuzzyNumber));
        // Cleaning
        instance.removeById(fuzzyNumber.getId());
    }

    /**
     * Test of iterate method, of class FuzzyNumberDao.
     */
    @Test
    public void testIterate() throws PropertyVetoException {
        System.out.println("iterate");
        FuzzyNumberDao instance = new FuzzyNumberDao();

        int size = instance.size();
        Iterable<FuzzyNumber> result = instance.iterate(size, 0);
        assertFalse(result.iterator().hasNext());
        
        result = instance.iterate(size, 1);
        assertFalse(result.iterator().hasNext());

        instance = new FuzzyNumberDao();
        FuzzyNumber fuzzyNumber = new FuzzyNumber();
        instance.create(fuzzyNumber);
        size = instance.size();
        result = instance.iterate(size - 1, 1);
        assertEquals(fuzzyNumber, result.iterator().next());
        // Cleaning
        instance.removeById(fuzzyNumber.getId());

        // Checking order
        instance = new FuzzyNumberDao();
        FuzzyNumber fuzzyNumber1 = new FuzzyNumber();
        FuzzyNumber fuzzyNumber2 = new FuzzyNumber();
        instance.create(fuzzyNumber1);
        instance.create(fuzzyNumber2);
        size = instance.size();
        result = instance.iterate(size - 2, 1);
        assertEquals(fuzzyNumber1, result.iterator().next());
        result = instance.iterate(size - 1, 1);
        assertEquals(fuzzyNumber2, result.iterator().next());
        result = instance.iterate(size - 2, 2);
        Iterator<FuzzyNumber> it = result.iterator();
        assertEquals(fuzzyNumber1, it.next());
        assertEquals(fuzzyNumber2, it.next());
        // Cleaning
        instance.removeById(fuzzyNumber1.getId());
        instance.removeById(fuzzyNumber2.getId());
    }
    
    /**
     * Test of findById method, of class FuzzyNumberDao.
     */
    @Test
    public void testFindById() throws PropertyVetoException {
        System.out.println("findById");
        FuzzyNumberDao instance = new FuzzyNumberDao();
        FuzzyNumber expResult = null;
        FuzzyNumber result = instance.findById(-1);
        assertEquals(expResult, result);

        instance = new FuzzyNumberDao();
        expResult = new FuzzyNumber();
        instance.create(expResult);
        int id = expResult.getId();
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }
}