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
package net.sourceforge.fuzzyservices.ejb3;

import net.sourceforge.fuzzyservices.beans.FactBaseBean;
import net.sourceforge.fuzzyservices.beans.RuleBaseBean;
import net.sourceforge.fuzzyservices.core.FactBase;
import net.sourceforge.fuzzyservices.core.RuleBase;
import net.sourceforge.fuzzyservices.beans.LinguisticVariableBean;
import net.sourceforge.fuzzyservices.core.LinguisticVariable;
import javax.ejb.Stateless;

/**
 * EJB implementation of a fuzzy controller for approximate reasoning.
 * It supports both core objects and JavaBeans.
 *
 * @since 1.0
 * @author Uwe Weng
 */
@Stateless(mappedName = "FuzzyController")
public class FuzzyControllerBean implements FuzzyControllerRemote {

    @Override
    public FactBaseBean performApproximateReasoning(final RuleBaseBean ruleBase,
            final FactBaseBean factBase, final LinguisticVariableBean[] lv) {
        return net.sourceforge.fuzzyservices.beans.FuzzyControllerBean.getInstance().performApproximateReasoning(ruleBase, factBase, lv);
    }

    @Override
    public FactBase performApproximateReasoning(final RuleBase ruleBase, final FactBase factBase, final LinguisticVariable[] lv) {
        return net.sourceforge.fuzzyservices.core.FuzzyController.getInstance().performApproximateReasoning(ruleBase, factBase, lv);
    }
}
