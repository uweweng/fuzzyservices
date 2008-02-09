/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.fuzzyservices.swing;

/**
 *
 * @author Administrator
 */
public interface RuleModel {

    OperatorModel getCertaintyOperator();
    OperatorModel getInferenceOperator();
    OperatorModel getAggregationOperator();
    float getCertainty();
    void setCertainty(float certainty);
    
    int getSizeOfAntecedents();
    OperatorModel getCompatibilityOperatorOfAntecedentAt(int index);
    String getLingVarNameOfAntecedentAt(int index);
    void setLingVarNameOfAntecedentAt(String name, int index);
    String getLingTermNameOfAntecedentAt(int index);
    void setLingTermNameOfAntecedentAt(String name, int index);
    
    int getSizeOfConsequents();
    String getLingVarNameOfConsequentAt(int index);
    void setLingVarNameOfConsequentAt(String name, int index);
    String getLingTermNameOfConsequentAt(int index);
    void setLingTermNameOfConsequentAt(String name, int index);
    
    void addRuleModelListener(RuleModelListener l);
    
    void removeRuleModelListener(RuleModelListener l);
    
}
