/*
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
package net.sourceforge.fuzzyservices.core;

/**
 * A fuzzy controller implements an approximate reasoning method.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public interface FuzzyController {

    /**
     * Performs approximate reasoning based on a fact and rule base.
     * @return a new fact base with the result of this operation
     * @param aRuleBase the rule base
     * @param aFactBase the fact base
     * @param linguisticVariables the linguistic variables
     */
    public FactBase performApproximateReasoning(RuleBase aRuleBase, FactBase aFactBase,
            LinguisticVariable[] linguisticVariables);
}
