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
package net.sourceforge.fuzzyservices.beans;

import net.sourceforge.fuzzyservices.core.FuzzyController;
import net.sourceforge.fuzzyservices.core.LinguisticVariable;

/**
 * This class represents a fuzzy controller according to JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.FuzzyController
 *
 * @since 1.0
 * @author Uwe Weng
 */
public final class FuzzyControllerBean {

    /** The singleton instance of this fuzzy controller bean. */
    private static transient FuzzyControllerBean instance = null;

    /**
     * Default <code>FuzzyControllerBean</code> constructor. It is also
     * possible to use the "singleton" instance.
     * @see #getInstance
     */
    public FuzzyControllerBean() {
    // Do nothing
    }

    /**
     * Gets an instance of this fuzzy controller.
     * @return an instance of this class
     */
    public static final FuzzyControllerBean getInstance() {
        if (instance == null) {
            instance = new FuzzyControllerBean();
        }

        return instance;
    }

    /**
     * Applies <code>factBase</code> to <code>ruleBase</code> using
     * approximate reasoning. The result is stored in a new fact base.
     * @return A fact base with the result of this operation
     * @param ruleBase The rule base
     * @param factBase The fact base
     * @param linguisticVariables The linguistic variable beans
     */
    public FactBaseBean performApproximateReasoning(RuleBaseBean ruleBase,
            FactBaseBean factBase, LinguisticVariableBean[] linguisticVariables) {
        if ((linguisticVariables == null) || (linguisticVariables.length == 0)) {
            return null;
        }
        LinguisticVariable[] lv = new LinguisticVariable[linguisticVariables.length];
        for (int i = 0; i < linguisticVariables.length; i++) {
            lv[i] = FuzzyBeanUtils.convert(linguisticVariables[i]);
        }

        return FuzzyBeanUtils.convert(
                FuzzyController.performApproximateReasoning(
                FuzzyBeanUtils.convert(ruleBase, linguisticVariables),
                FuzzyBeanUtils.convert(factBase, linguisticVariables), lv));
    }
}


