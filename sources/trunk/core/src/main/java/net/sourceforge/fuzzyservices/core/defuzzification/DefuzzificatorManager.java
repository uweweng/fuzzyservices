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
package net.sourceforge.fuzzyservices.core.defuzzification;

import java.util.Collection;
import net.sourceforge.fuzzyservices.core.AbstractDefuzzificator;
import java.util.HashMap;
import java.util.Map;

/**
 * You can ask the manager for all implemented defuzzificators.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class DefuzzificatorManager {

    /**
     * Contains all known defuzzificators of the fuzzy system with its name as
     * key.
     */
    private static Map<String, AbstractDefuzzificator> defuzzyOperators = new HashMap<String, AbstractDefuzzificator>();

    static {
        initDefuzzificators();
    }

    /**
     * The defuzzificator manager is a static class.
     */
    private DefuzzificatorManager() {
    // Not allowed
    }

    /**
     * Initializes the list of defuzzicators while loading the fuzzy manager.
     */
    private static void initDefuzzificators() {
        CenterOfArea center = new CenterOfArea();
        defuzzyOperators.put(center.toString(), center);
        LeftOfMax left = new LeftOfMax();
        defuzzyOperators.put(left.toString(), left);
        MeanOfMax mean = new MeanOfMax();
        defuzzyOperators.put(mean.toString(), mean);
        RightOfMax right = new RightOfMax();
        defuzzyOperators.put(right.toString(), right);
    }

    /**
     * Returns all registered defuzzificators as an array.
     *
     * @return a collection with defuzzificators
     * @see net.sourceforge.fuzzyservices.core.AbstractDefuzzificator
     */
    public static Collection<AbstractDefuzzificator> getDefuzzificators() {
        int size = defuzzyOperators.size();
        if (size > 0) {
            return defuzzyOperators.values();
        }
        return null;
    }

    /**
     * Returns the defuzzificator with the passed name.
     *
     * @param name the name as identifierAbstractDefuzzificatornstance of type
     * <code>Defuzzificator</code> or <AbstractDefuzzificatore> if not found
     * @return the selected defuzzificator
     * @see net.sourceforge.fuzzyservices.core.AbstractDefuzzificator
     */
    public static AbstractDefuzzificator getDefuzzificator(String name) {
        return defuzzyOperators.get(name);
    }

    /**
     * Registers a new defuzzificator on fuzzy system.
     *
     * @param defuzzy the new defuzzificaAbstractDefuzzificatorering
     * @return an instance of type <code>Defuzzificator</code> if such object
     * with tAbstractDefuzzificatorlready exists or <code>null</code>
     * @see net.sourceforge.fuzzyservices.core.AbstractDefuzzificator
     */
    public static AbstractDefuzzificator registerDefuzzificator(AbstractDefuzzificator defuzzy) {
        return defuzzyOperators.put(defuzzy.toString(), defuzzy);
    }
}
