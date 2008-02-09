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
package net.sourceforge.fuzzyservices.core;

import junit.framework.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Test for class <code>FuzzyResourceManager</code>
 *
 * @author Uwe Weng
 */
public class FuzzyResourceManagerTest extends TestCase {

    public FuzzyResourceManagerTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of getString method, of class net.sourceforge.fuzzyservices.core.FuzzyResourceManager.
     */
    public void testGetString() {
        System.out.println("getString");

        String name = "EXCEPTION_DIVIDE_BY_ZERO";

        String expResult = "Division durch 0";
        FuzzyResourceManager.setLocale(Locale.GERMAN);
        String result = FuzzyResourceManager.getString(FuzzySet.class, name);
        assertEquals(expResult, result);

    }

    /**
     * Test of getLocale method, of class net.sourceforge.fuzzyservices.core.FuzzyResourceManager.
     */
    public void testGetLocale() {
        System.out.println("getLocale");

        Locale expResult = Locale.getDefault();
        FuzzyResourceManager.setLocale(expResult);
        Locale result = FuzzyResourceManager.getLocale();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLocale method, of class net.sourceforge.fuzzyservices.core.FuzzyResourceManager.
     */
    public void testSetLocale() {
        System.out.println("setLocale");

        Locale newLocale = Locale.GERMAN;

        FuzzyResourceManager.setLocale(newLocale);
        assertEquals(FuzzyResourceManager.getLocale(), newLocale);
    }

}
