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
import net.sourceforge.fuzzyservices.beans.FuzzyNumber;
import org.junit.Test;
import static org.junit.Assert.*;

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
        long expResult = instance.size() + 1;
        FuzzyNumber FuzzyNumber = new FuzzyNumber();
        instance.create(FuzzyNumber);
        long result = instance.size();
        assertEquals(expResult, result);
        expResult = expResult - 1;
        instance.remove(FuzzyNumber);
        result = instance.size();
        assertEquals(expResult, result);
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