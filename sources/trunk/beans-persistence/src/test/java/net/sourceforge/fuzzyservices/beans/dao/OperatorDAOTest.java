/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.fuzzyservices.beans.dao;

import java.beans.PropertyVetoException;
import net.sourceforge.fuzzyservices.beans.Operator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public final class OperatorDAOTest {

    public OperatorDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of persistOperator method, of class OperatorDAO.
     * @throws PropertyVetoException 
     */
    @Test
    public void testPersistOperator() throws PropertyVetoException {
        System.out.println("persistOperator");
        Operator operator = new Operator();
        operator.setType("MAX");
        operator.setParameter(1.0f);
        OperatorDAO instance = new OperatorDAO();
        instance.persistOperator(operator);
        System.out.println(operator.getId());
    }

    /**
     * Test of getOperator method, of class OperatorDAO.
     */
    @Test
    public void testGetOperator() {
/*        
        System.out.println("getOperator");
        Integer id = null;
        OperatorDAO instance = new OperatorDAO();
        Operator expResult = null;
        Operator result = instance.getOperator(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
 */
    }

}