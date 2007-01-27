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
package net.sourceforge.fuzzyservices.core.operator;

import net.sourceforge.fuzzyservices.core.AbstractOperator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

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
    private static Hashtable combineOperators = new Hashtable();
    
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
        combineOperators.put(MinOp.toString(), MinOp);
        Max MaxOp = new Max();
        combineOperators.put(MaxOp.toString(), MaxOp);
        AlgebraicSum algSumOp = new AlgebraicSum();
        combineOperators.put(algSumOp.toString(), algSumOp);
        AlgebraicProduct algProdOp = new AlgebraicProduct();
        combineOperators.put(algProdOp.toString(), algProdOp);
        BoundedDifference boundedDiffOp = new BoundedDifference();
        combineOperators.put(boundedDiffOp.toString(), boundedDiffOp);
        BoundedSum boundedSumOp = new BoundedSum();
        combineOperators.put(boundedSumOp.toString(), boundedSumOp);
        DrasticProduct drasticProdOp = new DrasticProduct();
        combineOperators.put(drasticProdOp.toString(), drasticProdOp);
        DrasticSum drasticSumOp = new DrasticSum();
        combineOperators.put(drasticSumOp.toString(), drasticSumOp);
        EinsteinProduct EinsteinProdOp = new EinsteinProduct();
        combineOperators.put(EinsteinProdOp.toString(), EinsteinProdOp);
        EinsteinSum EinsteinSumOp = new EinsteinSum();
        combineOperators.put(EinsteinSumOp.toString(), EinsteinSumOp);
        HamacherIntersection HamacherInterOp = new HamacherIntersection(0.0f);
        combineOperators.put(HamacherInterOp.toString(false), HamacherInterOp);
        HamacherProduct HamacherProdOp = new HamacherProduct();
        combineOperators.put(HamacherProdOp.toString(), HamacherProdOp);
        HamacherSum HamacherSumOp = new HamacherSum();
        combineOperators.put(HamacherSumOp.toString(), HamacherSumOp);
        HamacherUnion HamacherUnionOp = new HamacherUnion(-1.0f);
        combineOperators.put(HamacherUnionOp.toString(false), HamacherUnionOp);
        MinMaxCompensation MinMaxCompOp = new MinMaxCompensation(0.0f);
        combineOperators.put(MinMaxCompOp.toString(false), MinMaxCompOp);
        WernersAnd WernersAndOp = new WernersAnd(0.0f);
        combineOperators.put(WernersAndOp.toString(false), WernersAndOp);
        WernersOr WernersOrOp = new WernersOr(0.0f);
        combineOperators.put(WernersOrOp.toString(false), WernersOrOp);
        YagerIntersection YagerInterOp = new YagerIntersection(1.0f);
        combineOperators.put(YagerInterOp.toString(false), YagerInterOp);
        YagerUnion YagerUnionOp = new YagerUnion(1.0f);
        combineOperators.put(YagerUnionOp.toString(false), YagerUnionOp);
    }
    
    /**
     * Returns all registered fuzzy operators as an array.
     * @return an array of fuzzy operators
     * @see com.jfuzzy.core.AbstractOperator
     */
    public static AbstractOperator[] getOperators() {
        // Alle gefundenen Objekte in ein Standard-Array uebertragen
        int size = combineOperators.size();
        if (size > 0) {
            Vector combineVector = new Vector(size);
            Enumeration elements = combineOperators.elements();
            while (elements.hasMoreElements())
                combineVector.addElement(elements.nextElement());
            AbstractOperator[] retarray = new AbstractOperator[size];
            combineVector.copyInto(retarray);
            return retarray;
        }
        return null;
    }
    
    /**
     * Returns a fuzzy operator with passed name.
     *
     * @param name the name as identifier
     * @return an instance of type <code>AbstractOperator</code> or
     * <code>null</code>
     * @see com.jfuzzy.core.AbstractOperator
     */
    public static AbstractOperator getOperator(String name) {
        return (AbstractOperator) combineOperators.get(name);
    }
    
    /**
     * Register a fuzzy operator on the fuzzy system.
     *
     * @param op the new fuzzy operator
     * @return an instance of type <code>AbstractOperator</code>
     * if such object with the same name already exists or <code>null</code>
     * @see com.jfuzzy.core.AbstractOperator
     */
    public static AbstractOperator registerOperator(AbstractOperator op) {
        if (op.requiresParameter())
            return (AbstractOperator) combineOperators.put(((AbstractParameteredOperator) op)
            .toString(false), op);
        else
            return (AbstractOperator) combineOperators.put(op.toString(), op);
    }
}
