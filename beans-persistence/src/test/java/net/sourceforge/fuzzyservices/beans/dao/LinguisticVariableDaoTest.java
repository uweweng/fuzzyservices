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
import net.sourceforge.fuzzyservices.beans.FuzzySet;
import net.sourceforge.fuzzyservices.beans.LinguisticTerm;
import net.sourceforge.fuzzyservices.beans.LinguisticVariable;
import net.sourceforge.fuzzyservices.beans.MembershipFunction;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of class LinguisticVariableDao.
 *
 * @author Uwe Weng
 */
public class LinguisticVariableDaoTest {

    /**
     * Test of findByName method, of class LinguisticVariableDao.
     */
    @Test
    public void testFindByName() throws PropertyVetoException {
        System.out.println("findByName");
        LinguisticVariableDao instance = new LinguisticVariableDao();
        LinguisticVariable expResult = null;
        try {
            LinguisticVariable result = instance.findByName(null);
            fail();
        } catch (Exception e) {
        }

        instance = new LinguisticVariableDao();
        String name = "testFindByName";
        expResult = new LinguisticVariable();
        expResult.setName(name);
        LinguisticTerm linguisticTerm = new LinguisticTerm("a", new FuzzySet(new MembershipFunction(1.0f, 1.0f)));
        expResult.setLinguisticTerms(new LinguisticTerm[]{linguisticTerm});
        instance.create(expResult);
        LinguisticVariable result = instance.findByName(name);
        assertEquals(expResult, result);
        // Cleaning
        instance.removeById(expResult.getId());
    }
}