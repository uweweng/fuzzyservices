/*******************************************************************************
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
package net.sourceforge.fuzzyservices.utils;

import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class FuzzyResourceManagerTest.
 *
 * @author Uwe Weng
 */
public class FuzzyResourceManagerTest {

    /**
     * Test of getString method, of class FuzzyResourceManager.
     */
    @Test
    public final void testGetStringObjectString() {
        System.out.println("getString");
        Object caller = this;
        String name = "TEST";
        String expResult = "Test.de";
        FuzzyResourceManager.setLocale(Locale.GERMAN);
        String result = FuzzyResourceManager.getString(caller, name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getString method, of class FuzzyResourceManager.
     */
    @Test
    public final void testGetStringClassString() {
        System.out.println("getString");
        Class caller = FuzzyResourceManager.class;
        // Get string in german language
        String name = "TEST";
        String expResult = "Test.de";
        FuzzyResourceManager.setLocale(Locale.GERMAN);
        String result = FuzzyResourceManager.getString(caller, name);
        assertEquals(expResult, result);

        // Get string in default language. It is german, too.
        FuzzyResourceManager.setLocale(null);
        result = FuzzyResourceManager.getString(caller, name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getString method, of class FuzzyResourceManager.
     */
    @Test
    public final void testGetString3args1() {
        System.out.println("getString");
        Object caller = this;
        String name = "TESTPARAMS";
        Object[] params = new Object[]{"Test"};
        String expResult = "Test.de";
        String result = FuzzyResourceManager.getString(caller, name, params);
        assertEquals(expResult, result);
    }

    /**
     * Test of getString method, of class FuzzyResourceManager.
     */
    @Test
    public final void testGetString3args2() {
        System.out.println("getString");
        Class caller = FuzzyResourceManager.class;
        String name = "TESTPARAMS";
        Object[] params = new Object[]{"Test"};
        String expResult = "Test.de";
        String result = FuzzyResourceManager.getString(caller, name, params);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLocale method, of class FuzzyResourceManager.
     */
    @Test
    public final void testGetLocale() {
        System.out.println("getLocale");
        Locale expResult = Locale.GERMAN;
        FuzzyResourceManager.setLocale(Locale.GERMAN);
        Locale result = FuzzyResourceManager.getLocale();
        assertEquals(expResult, result);

        // No locale means default locale.
        FuzzyResourceManager.setLocale(null);
        expResult = Locale.getDefault();
        result = FuzzyResourceManager.getLocale();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLocale method, of class FuzzyResourceManager.
     */
    @Test
    public final void testSetLocale() {
        System.out.println("setLocale");
        Locale newLocale = Locale.GERMAN;
        FuzzyResourceManager.setLocale(newLocale);
        assertEquals(FuzzyResourceManager.getLocale(), newLocale);
    }
}