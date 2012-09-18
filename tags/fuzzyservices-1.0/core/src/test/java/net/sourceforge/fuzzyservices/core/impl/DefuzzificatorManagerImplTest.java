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
package net.sourceforge.fuzzyservices.core.impl;

import java.util.Collection;
import net.sourceforge.fuzzyservices.core.AbstractDefuzzificator;
import net.sourceforge.fuzzyservices.core.MembershipFunction;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class DefuzzificatorManagerImpl.
 *
 * @author Uwe Weng
 */
public class DefuzzificatorManagerImplTest {

    /**
     * Defuzzificator for tests.
     *
     * @version        1.0
     */
    public class TestDefuzzificator extends AbstractDefuzzificator {

        /**
         * Default serial version UID.
         */
        private static final long serialVersionUID = 1L;

        @Override
        public float defuzzify(final MembershipFunction membershipFunction) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public String getName() {
            return "TestDefuzzificator";
        }
    }

    /**
     * Test of getDefuzzificators method, of class DefuzzificatorManager.
     */
    @Test
    public final void testGetDefuzzificators() {
        System.out.println("getDefuzzificators");
        // Any defuzzificators already exist
        Collection<AbstractDefuzzificator> result = DefuzzificatorManagerImpl.getInstance().getDefuzzificators();
        assertNotNull(result);
        int size = result.size();
        AbstractDefuzzificator defuzzificator = new TestDefuzzificator();
        DefuzzificatorManagerImpl.registerDefuzzificator(defuzzificator);
        result = DefuzzificatorManagerImpl.getInstance().getDefuzzificators();
        assertNotNull(result);
        int newSize = result.size();
        assertEquals(size + 1, newSize);
    }

    /**
     * Test of getDefuzzificator method, of class DefuzzificatorManager.
     */
    @Test
    public final void testGetDefuzzificator() {
        System.out.println("getDefuzzificator");
        AbstractDefuzzificator expResult = new TestDefuzzificator();
        String name = expResult.getName();
        DefuzzificatorManagerImpl.registerDefuzzificator(expResult);
        AbstractDefuzzificator result = DefuzzificatorManagerImpl.getInstance().getDefuzzificator(name);
        assertNotNull(result);
        assertEquals(result, expResult);

        // What happens when parameter is null?
        expResult = null;
        name = null;
        result = DefuzzificatorManagerImpl.getInstance().getDefuzzificator(name);
        assertEquals(expResult, result);

        // What happens when defuzzificator is unknown?
        expResult = null;
        name = "";
        result = DefuzzificatorManagerImpl.getInstance().getDefuzzificator(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerDefuzzificator method, of class DefuzzificatorManager.
     */
    @Test
    public final void testRegisterDefuzzificator() {
        System.out.println("registerDefuzzificator");
        AbstractDefuzzificator expResult = new TestDefuzzificator();
        DefuzzificatorManagerImpl.registerDefuzzificator(expResult);
        AbstractDefuzzificator result = DefuzzificatorManagerImpl.getInstance().getDefuzzificator(expResult.getName());
        assertEquals(expResult, result);

        // What happens when parameter is null?
        DefuzzificatorManagerImpl.registerDefuzzificator(null);
    }
}