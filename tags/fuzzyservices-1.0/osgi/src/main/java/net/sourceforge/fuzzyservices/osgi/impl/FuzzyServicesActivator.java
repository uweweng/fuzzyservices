/*
 *
 *  Copyright (C) 2008  Uwe Weng
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
package net.sourceforge.fuzzyservices.osgi.impl;

import java.util.Hashtable;
import net.sourceforge.fuzzyservices.core.FuzzyCalculator;
import net.sourceforge.fuzzyservices.core.FuzzyController;
import net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl;
import net.sourceforge.fuzzyservices.core.impl.FuzzyControllerImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

/**
 * FuzzyServicesActivator
 *
 * @author Uwe Weng
 */
public class FuzzyServicesActivator implements BundleActivator {

    public static BundleContext context = null;

    @Override
    public void start(BundleContext bc) throws Exception {
        System.out.println(bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " starting...");
        context = bc;
        FuzzyController fuzzyControllerService = FuzzyControllerImpl.getInstance();
        context.registerService(
                FuzzyController.class.getName(), fuzzyControllerService, new Hashtable());
        System.out.println("Service registered: FuzzyController");
        FuzzyCalculator fuzzyCalculatorService = FuzzyCalculatorImpl.getInstance();
        context.registerService(
                FuzzyCalculator.class.getName(), fuzzyCalculatorService, new Hashtable());
        System.out.println("Service registered: FuzzyCalculator");
    }

    @Override
    public void stop(BundleContext bc) throws Exception {
        System.out.println(bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " stopping...");
        context = null;
    }
}
