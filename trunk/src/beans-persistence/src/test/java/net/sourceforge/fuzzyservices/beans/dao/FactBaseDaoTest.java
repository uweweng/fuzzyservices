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
import net.sourceforge.fuzzyservices.beans.Fact;
import net.sourceforge.fuzzyservices.beans.FactBase;
import net.sourceforge.fuzzyservices.beans.FuzzySet;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of class FactBaseDao.
 *
 * @author Uwe Weng
 */
public class FactBaseDaoTest {

    /**
     * Test of create method, of class FactBaseDao.
     */
    @Test
    public void testCreate() throws PropertyVetoException {
        System.out.println("create");
        FactBaseDao instance = new FactBaseDao();
        FactBase expResult = new FactBase();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        FactBase result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(id);

        instance = new FactBaseDao();
        expResult = new FactBase("foo1");
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertNotNull(result.getName());
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(id);

        instance = new FactBaseDao();
        expResult = new FactBase("foo2");
        Fact[] facts = new Fact[0];
        expResult.setFacts(facts);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertNotNull(result.getFacts());
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(id);

        instance = new FactBaseDao();
        expResult = new FactBase("foo3");
        facts = new Fact[1];
        facts[0] = new Fact();
        expResult.setFacts(facts);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(id);
    }

    /**
     * Test of removeById method, of class FactBaseDao.
     */
    @Test
    public void testRemoveById() {
        System.out.println("removeById");
        FactBaseDao instance = new FactBaseDao();
        FactBase expResult = new FactBase();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        FactBase result = instance.findById(id);
        assertNull(result);

        instance = new FactBaseDao();
        expResult = new FactBase("foo1");
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);

        instance = new FactBaseDao();
        expResult = new FactBase("foo2");
        Fact[] facts = new Fact[0];
        expResult.setFacts(facts);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);

        instance = new FactBaseDao();
        expResult = new FactBase("foo3");
        facts = new Fact[1];
        facts[0] = new Fact();
        expResult.setFacts(facts);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of remove method, of class FactBaseDao.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        FactBaseDao instance = new FactBaseDao();
        FactBase expResult = new FactBase();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        FactBase result = instance.findById(id);
        assertNull(result);

        instance = new FactBaseDao();
        expResult = new FactBase("foo1");
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);

        instance = new FactBaseDao();
        expResult = new FactBase("foo2");
        Fact[] facts = new Fact[0];
        expResult.setFacts(facts);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);

        instance = new FactBaseDao();
        expResult = new FactBase("foo3");
        facts = new Fact[1];
        facts[0] = new Fact();
        expResult.setFacts(facts);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of update method, of class FactBaseDao.
     */
    @Test
    public void testUpdate() throws PropertyVetoException {
        System.out.println("update");
        FactBaseDao instance = new FactBaseDao();
        FactBase expResult = new FactBase();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        expResult = instance.update(expResult);
        FactBase result = instance.findById(id);
        assertEquals(result, expResult);
        // Update values
        // (1) name
        expResult.setName("Foo");
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(result, expResult);
        // (2) facts
        expResult = new FactBase();
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        Fact[] facts = new Fact[2];
        facts[0] = new Fact("A", new FuzzySet());
        facts[1] = new Fact("B", new FuzzySet());
        expResult.setFacts(facts);
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(result, expResult);

        // Complex object
        instance = new FactBaseDao();
        expResult = new FactBase("Foo");
        facts = new Fact[2];
        facts[0] = new Fact("B", new FuzzySet());
        facts[1] = new Fact("A", new FuzzySet());
        expResult.setFacts(facts);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        // Update values
        expResult.setName(null);
        expResult.setFacts(null);
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(result, expResult);
    }

    /**
     * Test of size method, of class FactBaseDao.
     */
    @Test
    public void testSize() throws PropertyVetoException {
        System.out.println("size");
        FactBaseDao instance = new FactBaseDao();
        int expResult = instance.size() + 1;
        FactBase factBase = new FactBase();
        instance.create(factBase);
        int result = instance.size();
        assertEquals(expResult, result);
        expResult = expResult - 1;
        instance.remove(factBase);
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class FactBaseDao.
     */
    @Test
    public void testFindAll() throws PropertyVetoException {
        System.out.println("findAll");
        FactBaseDao instance = new FactBaseDao();
        int expResult = instance.size();
        List<FactBase> result = instance.findAll();
        assertEquals(expResult, result.size());

        instance = new FactBaseDao();
        FactBase factBase = new FactBase();
        instance.create(factBase);
        result = instance.findAll();
        assertTrue(result.contains(factBase));
        // Cleaning
        instance.removeById(factBase.getId());
    }

    /**
     * Test of iterate method, of class FactBaseDao.
     */
    @Test
    public void testIterate() throws PropertyVetoException {
        System.out.println("iterate");
        FactBaseDao instance = new FactBaseDao();

        int size = instance.size();
        Iterable<FactBase> result = instance.iterate(size, 0);
        assertFalse(result.iterator().hasNext());
        
        result = instance.iterate(size, 1);
        assertFalse(result.iterator().hasNext());

        instance = new FactBaseDao();
        FactBase factBase = new FactBase();
        instance.create(factBase);
        size = instance.size();
        result = instance.iterate(size - 1, 1);
        assertEquals(factBase, result.iterator().next());
        // Cleaning
        instance.removeById(factBase.getId());

        // Checking order
        instance = new FactBaseDao();
        FactBase factBase1 = new FactBase();
        FactBase factBase2 = new FactBase();
        instance.create(factBase1);
        instance.create(factBase2);
        size = instance.size();
        result = instance.iterate(size - 2, 1);
        assertEquals(factBase1, result.iterator().next());
        result = instance.iterate(size - 1, 1);
        assertEquals(factBase2, result.iterator().next());
        result = instance.iterate(size - 2, 2);
        Iterator<FactBase> it = result.iterator();
        assertEquals(factBase1, it.next());
        assertEquals(factBase2, it.next());
        // Cleaning
        instance.removeById(factBase1.getId());
        instance.removeById(factBase2.getId());
    }

    /**
     * Test of findById method, of class FactBaseDao.
     */
    @Test
    public void testFindById() throws PropertyVetoException {
        System.out.println("findById");
        FactBaseDao instance = new FactBaseDao();
        FactBase expResult = null;
        FactBase result = instance.findById(-1);
        assertEquals(expResult, result);

        instance = new FactBaseDao();
        expResult = new FactBase();
        instance.create(expResult);
        int id = expResult.getId();
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }

    /**
     * Test of findByName method, of class FactBaseDao.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        FactBaseDao instance = new FactBaseDao();
        FactBase expResult = null;
        try {
            FactBase result = instance.findByName(null);
            fail();
        } catch (Exception e) {
        }

        instance = new FactBaseDao();
        String name = "testFindByName";
        expResult = new FactBase();
        expResult.setName(name);
        instance.create(expResult);
        FactBase result = instance.findByName(name);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }
}