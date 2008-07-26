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

import net.sourceforge.fuzzyservices.beans.Rule;
import net.sourceforge.fuzzyservices.swing.AbstractRuleModel;
import net.sourceforge.fuzzyservices.swing.OperatorModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * RuleBeanModel
 *
 * @author Uwe Weng
 */
public class RuleBeanModel extends AbstractRuleModel
    implements PropertyChangeListener {
    private Rule rule;

    public RuleBeanModel() {
        rule = new Rule();
        rule.addPropertyChangeListener(this);
    }

    public RuleBeanModel(Rule rule) {
        this.rule = rule;

        if (this.rule != null) {
            this.rule.addPropertyChangeListener(this);
        }
    }

    @Override
    public OperatorModel getCertaintyOperator() {
        return (rule != null)
        ? new OperatorBeanModel(rule.getCertaintyOperator()) : null;
    }

    @Override
    public OperatorModel getInferenceOperator() {
        return (rule != null)
        ? new OperatorBeanModel(rule.getInferenceOperator()) : null;
    }

    @Override
    public OperatorModel getAggregationOperator() {
        return (rule != null)
        ? new OperatorBeanModel(rule.getAggregationOperator()) : null;
    }

    @Override
    public final float getCertainty() {
        return (rule != null) ? rule.getCertainty() : 0.0f;
    }

    @Override
    public final void setCertainty(float certainty) {
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
        return (rule != null)
        ? new OperatorBeanModel(rule.getAntecedents(index)
                                    .getCompatibilityOperator()) : null;
    }

    @Override
    public String getLingVarNameOfAntecedentAt(int index) {
        return (rule != null)
        ? rule.getAntecedents(index).getLinguisticVariableName() : null;
    }

    @Override
    public final void setLingVarNameOfAntecedentAt(String name, int index) {
        if (rule != null) {
            rule.getAntecedents(index).setLinguisticVariableName(name);
        }
    }

    @Override
    public String getLingTermNameOfAntecedentAt(int index) {
        return (rule != null)
        ? rule.getAntecedents(index).getLinguisticTermName() : null;
    }

    @Override
    public final void setLingTermNameOfAntecedentAt(String name, int index) {
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
        return (rule != null)
        ? rule.getConsequents(index).getLinguisticVariableName() : null;
    }

    @Override
    public final void setLingVarNameOfConsequentAt(String name, int index) {
        if (rule != null) {
            rule.getConsequents(index).setLinguisticVariableName(name);
        }
    }

    @Override
    public String getLingTermNameOfConsequentAt(int index) {
        return (rule != null)
        ? rule.getConsequents(index).getLinguisticTermName() : null;
    }

    @Override
    public final void setLingTermNameOfConsequentAt(String name, int index) {
        if (rule != null) {
            rule.getConsequents(index).setLinguisticTermName(name);
        }
    }

    @Override
    public final void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        if (Rule.CERTAINTY_PROPERTY.equals(propertyName) == true) {
            fireCertaintyChanged(this);

            return;
        }

        if (Rule.AGGREGATION_OPERTAOR_PROPERTY.equals(propertyName) == true) {
            fireAggregationOperatorChanged(this);

            return;
        }

        if (Rule.INFERENCE_OPERATOR_PROPERTY.equals(propertyName) == true) {
            fireInferenceOperatorChanged(this);

            return;
        }

        if (Rule.CERTAINTY_OPERATOR_PROPERTY.equals(propertyName) == true) {
            fireCertaintyOperatorChanged(this);

            return;
        }

        if (Rule.ANTECEDENTS_PROPERTY.equals(propertyName) == true) {
            fireAntecedentsChanged(this);

            return;
        }

        if (Rule.CONSEQUENTS_PROPERTY.equals(propertyName) == true) {
            fireConsequentsChanged(this);

            return;
        }
    }
}
