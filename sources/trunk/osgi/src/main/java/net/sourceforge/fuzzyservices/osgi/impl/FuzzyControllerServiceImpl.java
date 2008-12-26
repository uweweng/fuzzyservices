package net.sourceforge.fuzzyservices.osgi.impl;

import net.sourceforge.fuzzyservices.osgi.FuzzyControllerService;

/**
 * FuzzyControllerServiceImpl
 *
 * @author Uwe Weng
 */
public class FuzzyControllerServiceImpl implements FuzzyControllerService {

    @Override
    public String foo() {
        return "Hello";
    }

}
