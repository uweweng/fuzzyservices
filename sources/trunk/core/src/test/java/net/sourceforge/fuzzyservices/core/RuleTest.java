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

import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of class RuleTest.
 *
 * @author Uwe Weng
 */
public class RuleTest {

    /**
     * Test of addAntecedent method, of class Rule.
     */
    @Test
    public final void testAddAntecedent_Antecedent() {
        System.out.println("addAntecedent");
        Antecedent ante = null;
        Rule instance = new Rule();
        instance.addAntecedent(ante);
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAntecedent method, of class Rule.
     */
    @Test
    public final void testAddAntecedent_String_String() {
        System.out.println("addAntecedent");
        String lvName = "";
        String lingTermName = "";
        Rule instance = new Rule();
        instance.addAntecedent(lvName, lingTermName);
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAntecedent method, of class Rule.
     */
    @Test
    public final void testAddAntecedent_3args() {
        System.out.println("addAntecedent");
        String lvName = "";
        String lingTermName = "";
        AbstractOperator compOp = null;
        Rule instance = new Rule();
        instance.addAntecedent(lvName, lingTermName, compOp);
        fail("The test case is a prototype.");
    }

    /**
     * Test of addConsequent method, of class Rule.
     */
    @Test
    public final void testAddConsequent_Consequent() {
        System.out.println("addConsequent");
        Consequent cons = null;
        Rule instance = new Rule();
        instance.addConsequent(cons);
        fail("The test case is a prototype.");
    }

    /**
     * Test of addConsequent method, of class Rule.
     */
    @Test
    public final void testAddConsequent_String_String() {
        System.out.println("addConsequent");
        String lvName = "";
        String lingTermName = "";
        Rule instance = new Rule();
        instance.addConsequent(lvName, lingTermName);
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class Rule.
     */
    @Test
    public final void testClear() {
        System.out.println("clear");
        Rule instance = new Rule();
        instance.clear();
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsAntecedent method, of class Rule.
     */
    @Test
    public final void testContainsAntecedent_String_String() {
        System.out.println("containsAntecedent");
        String lvName = "";
        String lingTermName = "";
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.containsAntecedent(lvName, lingTermName);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsAntecedent method, of class Rule.
     */
    @Test
    public final void testContainsAntecedent_3args() {
        System.out.println("containsAntecedent");
        String lvName = "";
        String lingTermName = "";
        AbstractOperator compOp = null;
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.containsAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsConsequent method, of class Rule.
     */
    @Test
    public final void testContainsConsequent() {
        System.out.println("containsConsequent");
        String lvName = "";
        String lingTermName = "";
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.containsConsequent(lvName, lingTermName);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsLinguisticVariable method, of class Rule.
     */
    @Test
    public final void testContainsLinguisticVariable() {
        System.out.println("containsLinguisticVariable");
        String lingVarName = "";
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.containsLinguisticVariable(lingVarName);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAggregationOperator method, of class Rule.
     */
    @Test
    public final void testGetAggregationOperator() {
        System.out.println("getAggregationOperator");
        Rule instance = new Rule();
        AbstractOperator expResult = null;
        AbstractOperator result = instance.getAggregationOperator();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAntecedentAt method, of class Rule.
     */
    @Test
    public final void testGetAntecedentAt() {
        System.out.println("getAntecedentAt");
        int i = 0;
        Rule instance = new Rule();
        Antecedent expResult = null;
        Antecedent result = instance.getAntecedentAt(i);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAntecedents method, of class Rule.
     */
    @Test
    public final void testGetAntecedents() {
        System.out.println("getAntecedents");
        Rule instance = new Rule();
        Iterator<Antecedent> expResult = null;
        Iterator<Antecedent> result = instance.getAntecedents();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCertainty method, of class Rule.
     */
    @Test
    public final void testGetCertainty() {
        System.out.println("getCertainty");
        Rule instance = new Rule();
        float expResult = 0.0F;
        float result = instance.getCertainty();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCertaintyOperator method, of class Rule.
     */
    @Test
    public final void testGetCertaintyOperator() {
        System.out.println("getCertaintyOperator");
        Rule instance = new Rule();
        AbstractOperator expResult = null;
        AbstractOperator result = instance.getCertaintyOperator();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConsequentAt method, of class Rule.
     */
    @Test
    public final void testGetConsequentAt() {
        System.out.println("getConsequentAt");
        int i = 0;
        Rule instance = new Rule();
        Consequent expResult = null;
        Consequent result = instance.getConsequentAt(i);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConsequents method, of class Rule.
     */
    @Test
    public final void testGetConsequents() {
        System.out.println("getConsequents");
        Rule instance = new Rule();
        Iterator<Consequent> expResult = null;
        Iterator<Consequent> result = instance.getConsequents();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultAggregationOperator method, of class Rule.
     */
    @Test
    public final void testGetDefaultAggregationOperator() {
        System.out.println("getDefaultAggregationOperator");
        AbstractOperator expResult = null;
        AbstractOperator result = Rule.getDefaultAggregationOperator();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultCertaintyOperator method, of class Rule.
     */
    @Test
    public final void testGetDefaultCertaintyOperator() {
        System.out.println("getDefaultCertaintyOperator");
        AbstractOperator expResult = null;
        AbstractOperator result = Rule.getDefaultCertaintyOperator();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultInferenceOperator method, of class Rule.
     */
    @Test
    public final void testGetDefaultInferenceOperator() {
        System.out.println("getDefaultInferenceOperator");
        AbstractOperator expResult = null;
        AbstractOperator result = Rule.getDefaultInferenceOperator();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInferenceOperator method, of class Rule.
     */
    @Test
    public final void testGetInferenceOperator() {
        System.out.println("getInferenceOperator");
        Rule instance = new Rule();
        AbstractOperator expResult = null;
        AbstractOperator result = instance.getInferenceOperator();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSizeOfAntecedents method, of class Rule.
     */
    @Test
    public final void testGetSizeOfAntecedents() {
        System.out.println("getSizeOfAntecedents");
        Rule instance = new Rule();
        int expResult = 0;
        int result = instance.getSizeOfAntecedents();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSizeOfConsequents method, of class Rule.
     */
    @Test
    public final void testGetSizeOfConsequents() {
        System.out.println("getSizeOfConsequents");
        Rule instance = new Rule();
        int expResult = 0;
        int result = instance.getSizeOfConsequents();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of if_ method, of class Rule.
     */
    @Test
    public final void testIf__String_String() {
        System.out.println("if_");
        String lvName = "";
        String lingTermName = "";
        Rule instance = new Rule();
        instance.if_(lvName, lingTermName);
        fail("The test case is a prototype.");
    }

    /**
     * Test of if_ method, of class Rule.
     */
    @Test
    public final void testIf__3args() {
        System.out.println("if_");
        String lvName = "";
        String lingTermName = "";
        AbstractOperator compOp = null;
        Rule instance = new Rule();
        instance.if_(lvName, lingTermName, compOp);
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAntecedent method, of class Rule.
     */
    @Test
    public final void testRemoveAntecedent_String_String() {
        System.out.println("removeAntecedent");
        String lvName = "";
        String lingTermName = "";
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.removeAntecedent(lvName, lingTermName);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAntecedent method, of class Rule.
     */
    @Test
    public final void testRemoveAntecedent_3args() {
        System.out.println("removeAntecedent");
        String lvName = "";
        String lingTermName = "";
        AbstractOperator compOp = null;
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.removeAntecedent(lvName, lingTermName, compOp);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeConsequent method, of class Rule.
     */
    @Test
    public final void testRemoveConsequent() {
        System.out.println("removeConsequent");
        String lvName = "";
        String lingTermName = "";
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.removeConsequent(lvName, lingTermName);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAggregationOperator method, of class Rule.
     */
    @Test
    public final void testSetAggregationOperator() {
        System.out.println("setAggregationOperator");
        AbstractOperator aggOp = null;
        Rule instance = new Rule();
        AbstractOperator expResult = null;
        AbstractOperator result = instance.setAggregationOperator(aggOp);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCertainty method, of class Rule.
     */
    @Test
    public final void testSetCertainty() {
        System.out.println("setCertainty");
        float cert = 0.0F;
        Rule instance = new Rule();
        float expResult = 0.0F;
        float result = instance.setCertainty(cert);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCertaintyOperator method, of class Rule.
     */
    @Test
    public final void testSetCertaintyOperator() {
        System.out.println("setCertaintyOperator");
        AbstractOperator certOp = null;
        Rule instance = new Rule();
        AbstractOperator expResult = null;
        AbstractOperator result = instance.setCertaintyOperator(certOp);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDefaultAggregationOperator method, of class Rule.
     */
    @Test
    public final void testSetDefaultAggregationOperator() {
        System.out.println("setDefaultAggregationOperator");
        AbstractOperator aggOp = null;
        AbstractOperator expResult = null;
        AbstractOperator result = Rule.setDefaultAggregationOperator(aggOp);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultCertainty method, of class Rule.
     */
    @Test
    public final void testGetDefaultCertainty() {
        System.out.println("getDefaultCertainty");
        float expResult = 0.0F;
        float result = Rule.getDefaultCertainty();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDefaultCertainty method, of class Rule.
     */
    @Test
    public final void testSetDefaultCertainty() {
        System.out.println("setDefaultCertainty");
        float cert = 0.0F;
        float expResult = 0.0F;
        float result = Rule.setDefaultCertainty(cert);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDefaultCertaintyOperator method, of class Rule.
     */
    @Test
    public final void testSetDefaultCertaintyOperator() {
        System.out.println("setDefaultCertaintyOperator");
        AbstractOperator certOp = null;
        AbstractOperator expResult = null;
        AbstractOperator result = Rule.setDefaultCertaintyOperator(certOp);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDefaultInferenceOperator method, of class Rule.
     */
    @Test
    public final void testSetDefaultInferenceOperator() {
        System.out.println("setDefaultInferenceOperator");
        AbstractOperator infOp = null;
        AbstractOperator expResult = null;
        AbstractOperator result = Rule.setDefaultInferenceOperator(infOp);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInferenceOperator method, of class Rule.
     */
    @Test
    public final void testSetInferenceOperator() {
        System.out.println("setInferenceOperator");
        AbstractOperator infOp = null;
        Rule instance = new Rule();
        AbstractOperator expResult = null;
        AbstractOperator result = instance.setInferenceOperator(infOp);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of then_ method, of class Rule.
     */
    @Test
    public final void testThen_() {
        System.out.println("then_");
        String lvName = "";
        String lingTermName = "";
        Rule instance = new Rule();
        instance.then_(lvName, lingTermName);
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Rule.
     */
    @Test
    public final void testClone() {
        System.out.println("clone");
        Rule instance = new Rule();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Rule.
     */
    @Test
    public final void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Rule.
     */
    @Test
    public final void testHashCode() {
        System.out.println("hashCode");
        Rule instance = new Rule();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Rule.
     */
    @Test
    public final void testToString_0args() {
        System.out.println("toString");
        Rule instance = new Rule();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Rule.
     */
    @Test
    public final void testToString_boolean() {
        System.out.println("toString");
        boolean oneLine = false;
        Rule instance = new Rule();
        String expResult = "";
        String result = instance.toString(oneLine);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}