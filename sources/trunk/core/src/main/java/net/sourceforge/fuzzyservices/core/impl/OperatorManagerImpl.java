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
package net.sourceforge.fuzzyservices.core.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.core.OperatorManager;
import net.sourceforge.fuzzyservices.core.operator.AlgebraicProduct;
import net.sourceforge.fuzzyservices.core.operator.AlgebraicSum;
import net.sourceforge.fuzzyservices.core.operator.BoundedDifference;
import net.sourceforge.fuzzyservices.core.operator.BoundedSum;
import net.sourceforge.fuzzyservices.core.operator.DrasticProduct;
import net.sourceforge.fuzzyservices.core.operator.DrasticSum;
import net.sourceforge.fuzzyservices.core.operator.EinsteinProduct;
import net.sourceforge.fuzzyservices.core.operator.EinsteinSum;
import net.sourceforge.fuzzyservices.core.operator.HamacherIntersection;
import net.sourceforge.fuzzyservices.core.operator.HamacherProduct;
import net.sourceforge.fuzzyservices.core.operator.HamacherSum;
import net.sourceforge.fuzzyservices.core.operator.HamacherUnion;
import net.sourceforge.fuzzyservices.core.operator.Max;
import net.sourceforge.fuzzyservices.core.operator.Min;
import net.sourceforge.fuzzyservices.core.operator.MinMaxCompensation;
import net.sourceforge.fuzzyservices.core.operator.WernersAnd;
import net.sourceforge.fuzzyservices.core.operator.WernersOr;
import net.sourceforge.fuzzyservices.core.operator.YagerIntersection;
import net.sourceforge.fuzzyservices.core.operator.YagerUnion;

/**
 * Implementation of an operator manager.
 *
 * @see net.sourceforge.fuzzyservices.core.OperatorManager
 *
 * @author Uwe Weng
 */
public class OperatorManagerImpl implements OperatorManager {

    /**
     * Contains all known fuzzy operators of the fuzzy system with its name as
     * key.
     */
    private static Map<String, AbstractOperator> combineOperators = new HashMap<String, AbstractOperator>();


    static {
        initCombineOperators();
    }
    /** The singleton instance of this operator manager. */
    private static transient OperatorManager instance = null;

    /**
     * Gets an instance of this operator manager.
     * @return an instance of this class
     */
    public final static OperatorManager getInstance() {
        if (instance == null) {
            instance = new OperatorManagerImpl();
        }

        return instance;
    }

    /**
     * Initializes the list of fuzzy operators while loading the manager.
     */
    private static void initCombineOperators() {
        Min minOp = new Min();
        combineOperators.put(minOp.getName(), minOp);

        Max maxOp = new Max();
        combineOperators.put(maxOp.getName(), maxOp);

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

        EinsteinProduct einsteinProdOp = new EinsteinProduct();
        combineOperators.put(einsteinProdOp.getName(), einsteinProdOp);

        EinsteinSum einsteinSumOp = new EinsteinSum();
        combineOperators.put(einsteinSumOp.getName(), einsteinSumOp);

        HamacherIntersection hamacherInterOp = new HamacherIntersection(0.0f);
        combineOperators.put(hamacherInterOp.getName(), hamacherInterOp);

        HamacherProduct hamacherProdOp = new HamacherProduct();
        combineOperators.put(hamacherProdOp.getName(), hamacherProdOp);

        HamacherSum hamacherSumOp = new HamacherSum();
        combineOperators.put(hamacherSumOp.getName(), hamacherSumOp);

        HamacherUnion hamacherUnionOp = new HamacherUnion(-1.0f);
        combineOperators.put(hamacherUnionOp.getName(), hamacherUnionOp);

        MinMaxCompensation minMaxCompOp = new MinMaxCompensation(0.0f);
        combineOperators.put(minMaxCompOp.getName(), minMaxCompOp);

        WernersAnd wernersAndOp = new WernersAnd(0.0f);
        combineOperators.put(wernersAndOp.getName(), wernersAndOp);

        WernersOr wernersOrOp = new WernersOr(0.0f);
        combineOperators.put(wernersOrOp.getName(), wernersOrOp);

        YagerIntersection yagerInterOp = new YagerIntersection(1.0f);
        combineOperators.put(yagerInterOp.getName(), yagerInterOp);

        YagerUnion yagerUnionOp = new YagerUnion(1.0f);
        combineOperators.put(yagerUnionOp.getName(), yagerUnionOp);
    }

    @Override
    public Collection<AbstractOperator> getOperators() {
        int size = combineOperators.size();

        if (size > 0) {
            return combineOperators.values();
        }

        return null;
    }

    @Override
    public AbstractOperator getOperator(final String name) {
        if (name != null) {
            return combineOperators.get(name);
        }
        return null;
    }
}
