/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.fuzzyservices.core;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 *
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml", "/applicationContext-test.xml"})
public class FuzzyControllerTest {

    private FuzzyController fuzzyController;

    @Autowired
    public void setFuzzyController(FuzzyController fuzzyController) {
        this.fuzzyController = fuzzyController;
    }

    /**
     * Test
     */
    @Test
    public void testFoo() {
        Assert.assertNotNull(fuzzyController);
    }
}
