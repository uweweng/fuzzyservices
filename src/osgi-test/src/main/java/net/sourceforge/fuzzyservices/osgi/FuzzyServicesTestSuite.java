/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.fuzzyservices.osgi;

import junit.framework.Test;
import org.apache.felix.ipojo.junit4osgi.OSGiTestSuite;
import org.osgi.framework.BundleContext;

/**
 *
 * @author Weng
 */
public class FuzzyServicesTestSuite {

    /**
     * This method returns the suite of tests to run.
     */
    public static Test suite(BundleContext bc) {
        OSGiTestSuite suite = new OSGiTestSuite("Fuzzy Services OSGi test suite", bc);
        suite.addTestSuite(FuzzyControllerTestCase.class);
        suite.addTestSuite(FuzzyCalculatorTestCase.class);
        suite.addTestSuite(DefuzzificatorTestCase.class);
        return suite;
    }
}
