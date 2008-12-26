package net.sourceforge.fuzzyservices.osgi.impl;

import java.util.Hashtable;
import net.sourceforge.fuzzyservices.osgi.FuzzyCalculatorService;
import net.sourceforge.fuzzyservices.osgi.FuzzyControllerService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;

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
        FuzzyControllerService fuzzyControllerService = new FuzzyControllerServiceImpl();
        context.registerService(
                FuzzyControllerService.class.getName(), fuzzyControllerService, new Hashtable());
        System.out.println("Service registered: FuzzyControllerService");
        FuzzyCalculatorService fuzzyCalculatorService = new FuzzyCalculatorServiceImpl();
        context.registerService(
                FuzzyCalculatorService.class.getName(), fuzzyCalculatorService, new Hashtable());
        System.out.println("Service registered: FuzzyCalculatorService");
    }

    @Override
    public void stop(BundleContext bc) throws Exception {
        System.out.println(bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME) + " stopping...");
        context = null;
    }
}
