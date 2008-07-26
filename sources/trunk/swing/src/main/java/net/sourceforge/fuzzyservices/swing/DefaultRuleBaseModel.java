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
package net.sourceforge.fuzzyservices.swing;

import java.util.ArrayList;
import java.util.Collection;


/**
 * DefaultRuleBaseModel
 *
 * @author Uwe Weng
 */
public class DefaultRuleBaseModel extends AbstractRuleBaseModel {
    private String name = "";
    private ArrayList<RuleModel> rules = new ArrayList<RuleModel>();
    private OperatorModel accumulationOperator = new DefaultOperatorModel();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public final void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<RuleModel> getRules() {
        return new ArrayList<RuleModel>(rules);
    }

    @Override
    public OperatorModel getAccumulationOperator() {
        return accumulationOperator;
    }
}
