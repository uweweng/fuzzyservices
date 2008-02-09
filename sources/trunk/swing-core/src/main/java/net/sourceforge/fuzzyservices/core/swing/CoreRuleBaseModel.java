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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import net.sourceforge.fuzzyservices.core.Rule;
import net.sourceforge.fuzzyservices.core.RuleBase;
import net.sourceforge.fuzzyservices.swing.AbstractRuleBaseModel;
import net.sourceforge.fuzzyservices.swing.OperatorModel;
import net.sourceforge.fuzzyservices.swing.RuleModel;

/**
 * CoreRuleBaseModel
 *
 * @author Uwe Weng
 */
public class CoreRuleBaseModel extends AbstractRuleBaseModel {

    private RuleBase ruleBase = new RuleBase();

    public CoreRuleBaseModel() {
    }

    public CoreRuleBaseModel(RuleBase ruleBase) {
        this.ruleBase = ruleBase;
    }

    @Override
    public String getName() {
        return (ruleBase != null) ? ruleBase.getName() : null;
    }

    @Override
    public void setName(String name) {
        if (ruleBase != null) {
            ruleBase.setName(name);
            fireNameChanged(this);
        }
    }

    @Override
    public Collection<RuleModel> getRules() {
        if (ruleBase != null) {
            Collection<RuleModel> col = new ArrayList<RuleModel>();
            for (Iterator<Rule> it = ruleBase.iterator(); it.hasNext();) {
                Rule rule = it.next();
                col.add(new CoreRuleModel(rule));
            }
            return col;
        }
        return null;
    }

    @Override
    public OperatorModel getAccumulationOperator() {
        return (ruleBase != null) ? new CoreOperatorModel(ruleBase.getAccumulationOperator()) : null;
    }
}
