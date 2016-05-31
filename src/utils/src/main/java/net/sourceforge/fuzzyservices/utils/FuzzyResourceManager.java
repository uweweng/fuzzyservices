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
package net.sourceforge.fuzzyservices.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The fuzzy resource manager is responsible for internationalization depending
 * on a preset locale.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public final class FuzzyResourceManager {

    /**
     * Depending on this locale the fuzzy manager delivers the requested
     * Resource in the right language.
     */
    private static transient Locale locale = Locale.getDefault();

    /**
     * Creates a new instance of FuzzyResourceManager.
     */
    private FuzzyResourceManager() {
        // Not allowed
    }

    /**
     * Returns a string resource.
     *
     * @param caller determine location of resource via caller object
     * @param name as identifier within the resoure pool
     * @return localized string
     */
    public static String getString(final Object caller, final String name) {
        return getString(caller.getClass().getPackage().getName(), name, null);
    }

    /**
     * Returns a string resource.
     *
     * @param caller determine location of resource via caller class
     * @param name as identifier within the resoure pool
     * @return localized string
     */
    public static String getString(final Class caller, final String name) {
        return getString(caller.getPackage().getName(), name, null);
    }

    /**
     * Returns a parametrized string resource.
     *
     * @param caller determine location of resource via caller object
     * @param name as identifier within resource pool
     * @param params the parameters for this resource
     * @return localized string
     */
    public static String getString(final Object caller, final String name, final Object[] params) {
        return getString(caller.getClass().getPackage().getName(), name, params);
    }

    /**
     * Returns a parametrized string resource.
     * @param caller determine location of resource via caller class
     * @param name as identifier within resource pool
     * @param params the parameters for this resource
     * @return localized string
     */
    public static String getString(final Class caller, final String name, final Object[] params) {
        return getString(caller.getPackage().getName(), name, params);
    }

    /**
     * Returns a parametrized string resource.
     * @param packagename determine location of resource along package path
     * @param name as identifier within resource pool
     * @param params the parameters for this resource
     * @return localized string
     */
    private static String getString(final String packagename, final String name, final Object[] params) {
        // Load resource bundle
        ResourceBundle res = null;
        if (packagename != null) {
            res = ResourceBundle.getBundle(packagename + "." + "resources", getLocale());
        } else {
            res = ResourceBundle.getBundle("resources", getLocale());
        }

        // Get resource
        String value = res.getString(name);

        if ((params != null) && (params.length > 0)) {
            // Format resource string with arguments if required
            MessageFormat formatter = new MessageFormat(value);
            formatter.setLocale(getLocale());
            value = formatter.format(params);
        }

        return value;
    }

    /**
     * Returns the current locale.
     *
     * @return the locale
     */
    public static Locale getLocale() {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return locale;
    }

    /**
     * Defines a new locale for the resource manager.
     *
     * @param newLocale the new locale
     */
    public static void setLocale(final Locale newLocale) {
        locale = newLocale;
    }
}
