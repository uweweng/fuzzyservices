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
package net.sourceforge.fuzzyservices.core.operator;

import java.util.Collection;
import net.sourceforge.fuzzyservices.core.AbstractOperator;
import java.util.HashMap;
import java.util.Map;

/**
 * You can ask the manager for all implemented operators.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class OperatorManager {

    /**
     * Contains all known fuzzy operators of the fuzzy system with its name as
     * key.
     */
    private static Map<String, AbstractOperator> combineOperators = new HashMap<String, AbstractOperator>();

    static {
        initCombineOperators();
    }

    /**
     * The operator manager is a static class.
     */
    public OperatorManager() {
    // Not allowed
    }

    /**
     * Initializes the list of fuzzy operators while loading the manager.
     */
    private static void initCombineOperators() {
        Min MinOp = new Min();
        combineOperators.put(MinOp.getName(), MinOp);
        Max MaxOp = new Max();
        combineOperators.put(MaxOp.getName(), MaxOp);
        AlgebraicSum algSumOp = new AlgebraicSum();
        combineOperators.put(algSumOp.getName(), algSumOp);
        AlgebraicProduct algProdOp = new AlgebraicProduct();
        combineOperators.put(algProdOp.getName(), algProdOp);
        BoundedDifference boundedDiffOp = new BoundedDifference();
        combineOperators.put(boundedDiffOp.getName(), boundedDiffOp);
        BoundedSum boundedSumOp = new BoundedSum();
        combineOperators.put(boundedSumOp.getName(), boundedSumOp);
        DrasticProduct drasticProdOp = new DrasticProduct();
        combineOperators.put(drasticProdOp.getName(), drasticProdOp);
        DrasticSum drasticSumOp = new DrasticSum();
        combineOperators.put(drasticSumOp.getName(), drasticSumOp);
        EinsteinProduct EinsteinProdOp = new EinsteinProduct();
        combineOperators.put(EinsteinProdOp.getName(), EinsteinProdOp);
        EinsteinSum EinsteinSumOp = new EinsteinSum();
        combineOperators.put(EinsteinSumOp.getName(), EinsteinSumOp);
        HamacherIntersection HamacherInterOp = new HamacherIntersection(0.0f);
        combineOperators.put(HamacherInterOp.getName(), HamacherInterOp);
        HamacherProduct HamacherProdOp = new HamacherProduct();
        combineOperators.put(HamacherProdOp.getName(), HamacherProdOp);
        HamacherSum HamacherSumOp = new HamacherSum();
        combineOperators.put(HamacherSumOp.getName(), HamacherSumOp);
        HamacherUnion HamacherUnionOp = new HamacherUnion(-1.0f);
        combineOperators.put(HamacherUnionOp.getName(), HamacherUnionOp);
        MinMaxCompensation MinMaxCompOp = new MinMaxCompensation(0.0f);
        combineOperators.put(MinMaxCompOp.getName(), MinMaxCompOp);
        WernersAnd WernersAndOp = new WernersAnd(0.0f);
        combineOperators.put(WernersAndOp.getName(), WernersAndOp);
        WernersOr WernersOrOp = new WernersOr(0.0f);
        combineOperators.put(WernersOrOp.getName(), WernersOrOp);
        YagerIntersection YagerInterOp = new YagerIntersection(1.0f);
        combineOperators.put(YagerInterOp.getName(), YagerInterOp);
        YagerUnion YagerUnionOp = new YagerUnion(1.0f);
        combineOperators.put(YagerUnionOp.getName(), YagerUnionOp);
    }

    /**
     * Returns all registered fuzzy operators as a collection.
     * @return a collection of fuzzy operators
     * @see net.sourceforge.fuzzyservices.core.AbstractOperator
     */
    public static Collection<AbstractOperator> getOperators() {
        int size = combineOperators.size();
        if (size > 0) {
            return combineOperators.values();
        }
        return null;
    }

    /**
     * Returns a fuzzy operator with passed name.
     *
     * @param name the name as identifier
     * @return an instance of type <code>AbstractOperator</code> or
     * <code>null</code>
     * @see net.sourceforge.fuzzyservices.core.AbstractOperator
     */
    public static AbstractOperator getOperator(String name) {
        if (name != null) {
            return combineOperators.get(name);
        }
        return null;
    }

    /**
     * Register a fuzzy operator on the fuzzy system.
     *
     * @param op the new fuzzy operator
     * @return an instance of type <code>AbstractOperator</code>
     * if such object with the same name already exists or <code>null</code>
     * @see net.sourceforge.fuzzyservices.core.AbstractOperator
     */
    public static AbstractOperator registerOperator(AbstractOperator op) {
        if (op.requiresParameter() == true) {
            return combineOperators.put(((AbstractParameteredOperator) op).toString(false), op);
        } else {
            return combineOperators.put(op.toString(), op);
        }
    }
}
