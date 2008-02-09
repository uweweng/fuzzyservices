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
package net.sourceforge.fuzzyservices.beans.swing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import net.sourceforge.fuzzyservices.beans.RuleBean;
import net.sourceforge.fuzzyservices.swing.AbstractRuleModel;
import net.sourceforge.fuzzyservices.swing.OperatorModel;

/**
 * RuleBeanModel
 *
 * @author Uwe Weng
 */
public class RuleBeanModel extends AbstractRuleModel implements PropertyChangeListener {

    private RuleBean rule;

    public RuleBeanModel() {
        rule = new RuleBean();
        rule.addPropertyChangeListener(this);
    }

    public RuleBeanModel(RuleBean rule) {
        this.rule = rule;
        if (this.rule != null) {
            this.rule.addPropertyChangeListener(this);
        }
    }

    @Override
    public OperatorModel getCertaintyOperator() {
        return (rule != null) ? new OperatorBeanModel(rule.getCertaintyOperator()) : null;
    }

    @Override
    public OperatorModel getInferenceOperator() {
        return (rule != null) ? new OperatorBeanModel(rule.getInferenceOperator()) : null;
    }

    @Override
    public OperatorModel getAggregationOperator() {
        return (rule != null) ? new OperatorBeanModel(rule.getAggregationOperator()) : null;
    }

    @Override
    public float getCertainty() {
        return (rule != null) ? rule.getCertainty() : 0.0f;
    }

    @Override
    public void setCertainty(float certainty) {
        if (rule != null) {
            rule.setCertainty(certainty);
        }
    }

    @Override
    public int getSizeOfAntecedents() {
        return (rule != null) ? rule.getAntecedents().length : 0;
    }

    @Override
    public OperatorModel getCompatibilityOperatorOfAntecedentAt(int index) {
        return (rule != null) ? new OperatorBeanModel(rule.getAntecedents(index).getCompatibilityOperator()) : null;
    }

    @Override
    public String getLingVarNameOfAntecedentAt(int index) {
        return (rule != null) ? rule.getAntecedents(index).getLinguisticVariableName() : null;
    }

    @Override
    public void setLingVarNameOfAntecedentAt(String name, int index) {
        if (rule != null) {
            rule.getAntecedents(index).setLinguisticVariableName(name);
        }
    }

    @Override
    public String getLingTermNameOfAntecedentAt(int index) {
        return (rule != null) ? rule.getAntecedents(index).getLinguisticTermName() : null;
    }

    @Override
    public void setLingTermNameOfAntecedentAt(String name, int index) {
        if (rule != null) {
            rule.getAntecedents(index).setLinguisticTermName(name);
        }
    }

    @Override
    public int getSizeOfConsequents() {
        return (rule != null) ? rule.getConsequents().length : 0;
    }

    @Override
    public String getLingVarNameOfConsequentAt(int index) {
        return (rule != null) ? rule.getConsequents(index).getLinguisticVariableName() : null;
    }

    @Override
    public void setLingVarNameOfConsequentAt(String name, int index) {
        if (rule != null) {
            rule.getConsequents(index).setLinguisticVariableName(name);
        }
    }

    @Override
    public String getLingTermNameOfConsequentAt(int index) {
        return (rule != null) ? rule.getConsequents(index).getLinguisticTermName() : null;
    }

    @Override
    public void setLingTermNameOfConsequentAt(String name, int index) {
        if (rule != null) {
            rule.getConsequents(index).setLinguisticTermName(name);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if (RuleBean.CERTAINTY_PROPERTY.equals(propertyName) == true) {
            fireCertaintyChanged(this);
            return;
        }
        if (RuleBean.AGGREGATION_OPERTAOR_PROPERTY.equals(propertyName) == true) {
            fireAggregationOperatorChanged(this);
            return;
        }
        if (RuleBean.INFERENCE_OPERATOR_PROPERTY.equals(propertyName) == true) {
            fireInferenceOperatorChanged(this);
            return;
        }
        if (RuleBean.CERTAINTY_OPERATOR_PROPERTY.equals(propertyName) == true) {
            fireCertaintyOperatorChanged(this);
            return;
        }
        if (RuleBean.ANTECEDENTS_PROPERTY.equals(propertyName) == true) {
            fireAntecedentsChanged(this);
            return;
        }
        if (RuleBean.CONSEQUENTS_PROPERTY.equals(propertyName) == true) {
            fireConsequentsChanged(this);
            return;
        }
    }
}
