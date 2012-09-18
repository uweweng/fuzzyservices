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
import net.sourceforge.fuzzyservices.beans.FuzzyLRNumber;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class FuzzyLRNumberDao.
 *
 * @author Uwe Weng
 */
public class FuzzyLRNumberDaoTest {

    /**
     * Test of create method, of class FuzzyLRNumberDao.
     */
    @Test
    public void testCreate() throws PropertyVetoException {
        System.out.println("create");
        FuzzyLRNumberDao instance = new FuzzyLRNumberDao();
        FuzzyLRNumber expResult = new FuzzyLRNumber();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        FuzzyLRNumber result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());

        instance = new FuzzyLRNumberDao();
        MembershipFunction membershipFunction = new MembershipFunction(-2.0f, 1.0f);
        expResult = new FuzzyLRNumber();
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
     * Test of removeById method, of class FuzzyLRNumberDao.
     */
    @Test
    public void testRemoveById() {
        System.out.println("removeById");
        FuzzyLRNumberDao instance = new FuzzyLRNumberDao();
        FuzzyLRNumber expResult = new FuzzyLRNumber();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        FuzzyLRNumber result = instance.findById(id);
        assertNull(result);

        instance = new FuzzyLRNumberDao();
        MembershipFunction membershipFunction = new MembershipFunction(-2.0f, 1.0f);
        expResult = new FuzzyLRNumber();
        expResult.setMembershipFunction(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.removeById(id);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of remove method, of class FuzzyLRNumberDao.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        FuzzyLRNumberDao instance = new FuzzyLRNumberDao();
        FuzzyLRNumber expResult = new FuzzyLRNumber();
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        FuzzyLRNumber result = instance.findById(id);
        assertNull(result);

        instance = new FuzzyLRNumberDao();
        MembershipFunction membershipFunction = new MembershipFunction(-2.0f, 1.0f);
        expResult = new FuzzyLRNumber();
        expResult.setMembershipFunction(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        instance.remove(expResult);
        result = instance.findById(id);
        assertNull(result);
    }

    /**
     * Test of update method, of class FuzzyLRNumberDao.
     */
    @Test
    public void testUpdate() throws PropertyVetoException {
        System.out.println("update");
        FuzzyLRNumberDao instance = new FuzzyLRNumberDao();
        FuzzyLRNumber expResult = new FuzzyLRNumber();
        expResult.setMembershipFunction(new MembershipFunction(1.0f, 1.0f));
        instance.create(expResult);
        int id = expResult.getId();
        assertNotNull(id);
        expResult = instance.update(expResult);
        FuzzyLRNumber result = instance.findById(id);
        assertEquals(result, expResult);
        // Update values
        // (1) new membership function
        expResult.setMembershipFunction(new MembershipFunction(-2.0f, 1.0f));
        expResult = instance.update(expResult);
        result = instance.findById(id);
        assertEquals(result, expResult);
        // Cleaning
        instance.removeById(expResult.getId());
    }

    /**
     * Test of size method, of class FuzzyLRNumberDao.
     */
    @Test
    public void testSize() throws PropertyVetoException {
        System.out.println("size");
        FuzzyLRNumberDao instance = new FuzzyLRNumberDao();
        long expResult = instance.size() + 1;
        FuzzyLRNumber FuzzyLRNumber = new FuzzyLRNumber();
        instance.create(FuzzyLRNumber);
        long result = instance.size();
        assertEquals(expResult, result);
        expResult = expResult - 1;
        instance.remove(FuzzyLRNumber);
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findById method, of class FuzzyLRNumberDao.
     */
    @Test
    public void testFindById() throws PropertyVetoException {
        System.out.println("findById");
        FuzzyLRNumberDao instance = new FuzzyLRNumberDao();
        FuzzyLRNumber expResult = null;
        FuzzyLRNumber result = instance.findById(-1);
        assertEquals(expResult, result);

        instance = new FuzzyLRNumberDao();
        expResult = new FuzzyLRNumber();
        instance.create(expResult);
        int id = expResult.getId();
        result = instance.findById(id);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }
}