/*
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

import net.sourceforge.fuzzyservices.beans.RuleBase;
import net.sourceforge.fuzzyservices.beans.Rule;
import net.sourceforge.fuzzyservices.swing.AbstractRuleBaseModel;
import net.sourceforge.fuzzyservices.swing.OperatorModel;
import net.sourceforge.fuzzyservices.swing.RuleModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.Collection;


/**
 * RuleBaseBeanModel
 *
 * @author Uwe Weng
 */
public class RuleBaseBeanModel extends AbstractRuleBaseModel
    implements PropertyChangeListener {
    private RuleBase ruleBase;

    public RuleBaseBeanModel() {
        ruleBase = new RuleBase();
        ruleBase.addPropertyChangeListener(this);
    }

    public RuleBaseBeanModel(RuleBase ruleBase) {
        this.ruleBase = ruleBase;

        if (this.ruleBase != null) {
            this.ruleBase.addPropertyChangeListener(this);
        }
    }

    @Override
    public String getName() {
        return (ruleBase != null) ? ruleBase.getName() : null;
    }

    @Override
    public final void setName(String name) {
        if (ruleBase != null) {
            ruleBase.setName(name);
        }
    }

    @Override
    public Collection<RuleModel> getRules() {
        if (ruleBase != null) {
            Rule[] rules = ruleBase.getRules();

            if ((rules != null) && (rules.length > 0)) {
                Collection<RuleModel> col = new ArrayList<RuleModel>(rules.length);

                for (int i = 0; i < rules.length; i++) {
                    col.add(new RuleBeanModel(rules[i]));
                }

                return col;
            }
        }

        return null;
    }

    @Override
    public OperatorModel getAccumulationOperator() {
        return (ruleBase != null)
        ? new OperatorBeanModel(ruleBase.getAccumulationOperator()) : null;
    }

    @Override
    public final void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        if (RuleBase.NAME_PROPERTY.equals(propertyName) == true) {
            fireNameChanged(this);

            return;
        }

        if (RuleBase.RULES_PROPERTY.equals(propertyName) == true) {
            fireRulesChanged(this);

            return;
        }

        if (RuleBase.ACCUMULATION_OPERATOR_PROPERTY.equals(propertyName) == true) {
            fireAccumulationOperatorChanged(this);

            return;
        }
    }
}
