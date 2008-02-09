/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.fuzzyservices.swing;

import java.util.Collection;

/**
 *
 * @author Administrator
 */
public interface RuleBaseModel {

    String getName();
    
    void setName(String name);
    
    Collection<RuleModel> getRules();
    
    OperatorModel getAccumulationOperator();

    void addRuleBaseModelListener(RuleBaseModelListener l);
    
    void removeRuleBaseModelListener(RuleBaseModelListener l);
    
}
