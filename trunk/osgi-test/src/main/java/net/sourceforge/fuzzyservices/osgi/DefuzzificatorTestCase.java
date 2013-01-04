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
package net.sourceforge.fuzzyservices.osgi;

import net.sourceforge.fuzzyservices.core.DefuzzificatorManager;
import org.apache.felix.ipojo.junit4osgi.OSGiTestCase;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Test of OSGi service Defuzzificator using 
 * <a href="http://felix.apache.org/site/apache-felix-ipojo-junit4osgi.html">JUnit4OSGi</a> framework.
 * The current version use JUnit 3.8 without annotation support.
 * 
 * @author Uwe Weng
 */
public class DefuzzificatorTestCase extends OSGiTestCase {

    /**
     * Test the availability of the service Defuzzificator.
     */
    public void testGetService() {
        System.out.println("getService");
        // You can access here the bundle context through the 'getContext()' method 
        BundleContext c = getContext();
        assertNotNull("Assert Existing bundle context", c);
        ServiceReference reference = getServiceReference(DefuzzificatorManager.class.getName());
        assertNotNull("Assert Service Reference", reference);
        DefuzzificatorManager service = (DefuzzificatorManager) getContext().getService(reference);
        assertNotNull("Assert Service Availability", service);
    }
}
