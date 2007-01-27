/*******************************************************************************
 *
 *  Copyright (C) 2007  Uwe Weng
 *
 *  This file is part of JFuzzy, a library for processing fuzzy information.
 *
 *  JFuzzy is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  JFuzzy is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with JFuzzy; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  The license is also available at http://www.gnu.org/licenses/gpl.txt
 *
 ******************************************************************************/
package net.sourceforge.fuzzyservices.beans;

import net.sourceforge.fuzzyservices.core.FuzzyController;


/**
 * This class represents a fuzzy controller according to JavaBeans conventions.
 *
 * @see com.jfuzzy.core.FuzzyController
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
     */
    public FactBaseBean performApproximateReasoning(RuleBaseBean ruleBase,
            FactBaseBean factBase) {
        return FuzzyBeanUtils.convert(
                FuzzyController.performApproximateReasoning(
                FuzzyBeanUtils.convert(ruleBase),
                FuzzyBeanUtils.convert(factBase)));
    }
}


