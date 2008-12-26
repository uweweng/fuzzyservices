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
package net.sourceforge.fuzzyservices.core.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.fuzzyservices.core.AbstractDefuzzificator;
import net.sourceforge.fuzzyservices.core.DefuzzificatorManager;
import net.sourceforge.fuzzyservices.core.defuzzification.CenterOfArea;
import net.sourceforge.fuzzyservices.core.defuzzification.LeftOfMax;
import net.sourceforge.fuzzyservices.core.defuzzification.MeanOfMax;
import net.sourceforge.fuzzyservices.core.defuzzification.RightOfMax;

/**
 * Implementation of a defuzzificator manager.
 *
 * @see net.sourceforge.fuzzyservices.core.DefuzzificatorManager
 *
 * @author Uwe Weng
 */
public class DefuzzificatorManagerImpl implements DefuzzificatorManager {

    /**
     * Contains all known defuzzificators of the fuzzy system with its name as
     * key.
     */
    private static Map<String, AbstractDefuzzificator> defuzzyOperators =
            new HashMap<String, AbstractDefuzzificator>();


    static {
        initDefuzzificators();
    }
    /** The singleton instance of this defuzzificator manager. */
    private static transient DefuzzificatorManager instance = null;

    /**
     * Gets an instance of this defuzzificator manager.
     * @return an instance of this class
     */
    public final static DefuzzificatorManager getInstance() {
        if (instance == null) {
            instance = new DefuzzificatorManagerImpl();
        }

        return instance;
    }

    /**
     * Initializes the list of defuzzicators while loading the defuzzificator manager.
     */
    private static void initDefuzzificators() {
        CenterOfArea center = new CenterOfArea();
        defuzzyOperators.put(center.getName(), center);

        LeftOfMax left = new LeftOfMax();
        defuzzyOperators.put(left.getName(), left);

        MeanOfMax mean = new MeanOfMax();
        defuzzyOperators.put(mean.getName(), mean);

        RightOfMax right = new RightOfMax();
        defuzzyOperators.put(right.getName(), right);
    }

    @Override
    public Collection<AbstractDefuzzificator> getDefuzzificators() {
        int size = defuzzyOperators.size();

        if (size > 0) {
            return defuzzyOperators.values();
        }

        return null;
    }

    @Override
    public AbstractDefuzzificator getDefuzzificator(final String name) {
        return defuzzyOperators.get(name);
    }

    /**
     * Registers a new defuzzificator on fuzzy system.
     *
     * @param defuzzy the new defuzzificator
     * @see net.sourceforge.fuzzyservices.core.AbstractDefuzzificator
     */
    public static void registerDefuzzificator(final AbstractDefuzzificator defuzzy) {
        if (defuzzy != null) {
            defuzzyOperators.put(defuzzy.getName(), defuzzy);
        }
    }
}
