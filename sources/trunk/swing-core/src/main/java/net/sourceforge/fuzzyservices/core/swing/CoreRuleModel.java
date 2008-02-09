/*******************************************************************************
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
package net.sourceforge.fuzzyservices.core.swing;

import net.sourceforge.fuzzyservices.core.Rule;
import net.sourceforge.fuzzyservices.swing.AbstractRuleModel;
import net.sourceforge.fuzzyservices.swing.OperatorModel;

/**
 * CoreRuleModel
 *
 * @author Uwe Weng
 */
public class CoreRuleModel extends AbstractRuleModel {

    private Rule rule = new Rule();
    
    public CoreRuleModel(Rule rule) {
        this.rule = rule;
    }
    
    @Override
    public OperatorModel getCertaintyOperator() {
        return (rule != null) ? new CoreOperatorModel(rule.getCertaintyOperator()) : null;
    }

    @Override
    public OperatorModel getInferenceOperator() {
        return (rule != null) ? new CoreOperatorModel(rule.getInferenceOperator()) : null;
    }

    @Override
    public OperatorModel getAggregationOperator() {
        return (rule != null) ? new CoreOperatorModel(rule.getAggregationOperator()) : null;
    }

    @Override
    public float getCertainty() {
        return (rule != null) ? rule.getCertainty() : 0.0f;
    }

    @Override
    public void setCertainty(float certainty) {
        if (rule != null) {
            rule.setCertainty(certainty);
            fireCertaintyChanged(this);
        }
    }

    @Override
    public int getSizeOfAntecedents() {
        return (rule != null) ? rule.getSizeOfAntecedents() : 0;
    }

    @Override
    public OperatorModel getCompatibilityOperatorOfAntecedentAt(int index) {
        return (rule != null) ? new CoreOperatorModel(rule.getAntecedentAt(index).getCompatibilityOperator()) : null;
    }

    @Override
    public String getLingVarNameOfAntecedentAt(int index) {
        return (rule != null) ? rule.getAntecedentAt(index).getLinguisticVariableName() : null;
    }

    @Override
    public void setLingVarNameOfAntecedentAt(String name, int index) {
        if (rule != null) {
            rule.getAntecedentAt(index).setLinguisticVariableName(name);
        }
    }

    @Override
    public String getLingTermNameOfAntecedentAt(int index) {
        return (rule != null) ? rule.getAntecedentAt(index).getLinguisticTermName() : null;
    }

    @Override
    public void setLingTermNameOfAntecedentAt(String name, int index) {
        if (rule != null) {
            rule.getAntecedentAt(index).setLinguisticTermName(name);
        }
    }

    @Override
    public int getSizeOfConsequents() {
        return (rule != null) ? rule.getSizeOfConsequents() : 0;
    }

    @Override
    public String getLingVarNameOfConsequentAt(int index) {
        return (rule != null) ? rule.getConsequentAt(index).getLinguisticVariableName() : null;
    }

    @Override
    public void setLingVarNameOfConsequentAt(String name, int index) {
        if (rule != null) {
            rule.getConsequentAt(index).setLinguisticVariableName(name);
        }
    }

    @Override
    public String getLingTermNameOfConsequentAt(int index) {
        return (rule != null) ? rule.getConsequentAt(index).getLinguisticTermName() : null;
    }

    @Override
    public void setLingTermNameOfConsequentAt(String name, int index) {
        if (rule != null) {
            rule.getConsequentAt(index).setLinguisticTermName(name);
        }
    }

}
