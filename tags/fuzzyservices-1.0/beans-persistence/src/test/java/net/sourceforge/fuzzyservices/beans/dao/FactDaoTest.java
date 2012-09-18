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

import net.sourceforge.fuzzyservices.beans.MembershipFunction;
import java.beans.PropertyVetoException;
import net.sourceforge.fuzzyservices.beans.Fact;
import net.sourceforge.fuzzyservices.beans.FuzzySet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class FactDao.
 *
 * @author Uwe Weng
 */
public class FactDaoTest {

    /**
     * Test of create method, of class FactDao.
     */
    @Test
    public void testCreate() throws PropertyVetoException {
        System.out.println("create");
        FactDao instance = new FactDao();
        Fact expResult = new Fact();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        Fact result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(id);

        instance = new FactDao();
        expResult = new Fact("foo1", null);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertNotNull(result.getLinguisticVariableName());
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(id);

        instance = new FactDao();
        expResult = new Fact("foo2", new FuzzySet());
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertNotNull(result.getValue());
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(id);

        instance = new FactDao();
        MembershipFunction membershipFunction = new MembershipFunction(1.0f, 1.0f);
        FuzzySet value = new FuzzySet(membershipFunction);
        expResult = new Fact("foo3", value);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(id);
        /*
        instance = new FactDao();
        System.out.println(value.getId());
        expResult = new Fact("foo4", value);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertEquals(expResult, result);
         */
    }

    /**
     * Test of removeById method, of class FactDao.
     */
    @Test
    public void testRemoveById() {
        System.out.println("removeById");
        FactDao instance = new FactDao();
        Fact expResult = new Fact();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        Fact result = instance.findById(id);
        assertNull(result);

        instance = new FactDao();
        expResult = new Fact("foo1", null);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);

        instance = new FactDao();
        expResult = new Fact("foo2", new FuzzySet());
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);

        instance = new FactDao();
        MembershipFunction membershipFunction = new MembershipFunction(1.0f, 1.0f);
        expResult = new Fact("foo3", new FuzzySet(membershipFunction));
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of remove method, of class FactDao.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        FactDao instance = new FactDao();
        Fact expResult = new Fact();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        Fact result = instance.findById(id);
        assertNull(result);

        instance = new FactDao();
        expResult = new Fact("foo1", null);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);

        instance = new FactDao();
        expResult = new Fact("foo2", new FuzzySet());
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);

        instance = new FactDao();
        MembershipFunction membershipFunction = new MembershipFunction(1.0f, 1.0f);
        expResult = new Fact("foo3", new FuzzySet(membershipFunction));
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of update method, of class FactDao.
     */
    @Test
    public void testUpdate() throws PropertyVetoException {
        System.out.println("update");
        FactDao instance = new FactDao();
        Fact expResult = new Fact();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        expResult = instance.update(expResult);
        Fact result = instance.findById(id);
        assertEquals(result, expResult);
        // Update values
        // (1) linguistic variable name
        expResult.setLinguisticVariableName("Foo");
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(result, expResult);
        // Cleaning
        instance.removeById(expResult.getId());
        
        // (2) value
        expResult = new Fact();
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        expResult.setValue(new FuzzySet());
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(result, expResult);
        // Cleaning
        instance.removeById(expResult.getId());

        // Complex object
        instance = new FactDao();
        MembershipFunction membershipFunction = new MembershipFunction(1.0f, 1.0f);
        expResult = new Fact("Foo", new FuzzySet(membershipFunction));
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        // Update values
        expResult.setLinguisticVariableName(null);
        expResult.setValue(null);
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(result, expResult);
        // Cleaning
        instance.removeById(expResult.getId());
}

    /**
     * Test of size method, of class FactDao.
     */
    @Test
    public void testSize() throws PropertyVetoException {
        System.out.println("size");
        FactDao instance = new FactDao();
        long expResult = instance.size() + 1;
        Fact fact = new Fact();
        instance.create(fact);
        long result = instance.size();
        assertEquals(expResult, result);
        expResult = expResult - 1;
        instance.remove(fact);
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findById method, of class FactDao.
     */
    @Test
    public void testFindById() throws PropertyVetoException {
        System.out.println("findById");
        FactDao instance = new FactDao();
        Fact expResult = null;
        Fact result = instance.findById(-1);
        assertEquals(expResult, result);

        instance = new FactDao();
        expResult = new Fact();
        instance.create(expResult);
        int id = expResult.getId();
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }
}