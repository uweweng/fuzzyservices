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
import net.sourceforge.fuzzyservices.beans.FuzzyLRInterval;
import org.junit.Test;
import static org.junit.Assert.*;

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

        instance = new FuzzyLRIntervalDao();
        MembershipFunction membershipFunction = new MembershipFunction(-3.0f, -2.0f, 1.0f, 1.0f);
        expResult = new FuzzyLRInterval();
        expResult.setMembershipFunction(membershipFunction);
        instance.create(expResult);
        id = expResult.getId();
        assertNotNull(id);
        result = instance.findById(id);
        assertEquals(expResult, result);
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
    }

    /**
     * Test of size method, of class FuzzyLRIntervalDao.
     */
    @Test
    public void testSize() throws PropertyVetoException {
        System.out.println("size");
        FuzzyLRIntervalDao instance = new FuzzyLRIntervalDao();
        long expResult = instance.size() + 1;
        FuzzyLRInterval FuzzyLRInterval = new FuzzyLRInterval();
        instance.create(FuzzyLRInterval);
        long result = instance.size();
        assertEquals(expResult, result);
        expResult = expResult - 1;
        instance.remove(FuzzyLRInterval);
        result = instance.size();
        assertEquals(expResult, result);
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
    }
}