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
package net.sourceforge.fuzzyservices.core.defuzzification;

import net.sourceforge.fuzzyservices.core.AbstractDefuzzificator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * You can ask the manager for all implemented defuzzificators.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class DefuzzificatorManager {
    
    /**
     * Contains all knwon defuzzificators of the fuzzy system with its name as
     * key.
     */
    private static Hashtable defuzzyOperators = new Hashtable();
    
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
     * @return an array with defuzzificators
     * @see com.jfuzzy.core.AbstractDefuzzificator
     */
    public static AbstractDefuzzificator[] getDefuzzificators() {
        // Copying all defuzzificators into an array.
        int size = defuzzyOperators.size();
        if (size > 0) {
            Vector defuzzyVector = new Vector(size);
            Enumeration elements = defuzzyOperators.elements();
            while (elements.hasMoreElements()) {
                defuzzyVector.addElement(elements.nextElement());
            }
            AbstractDefuzzificator[] ret = new AbstractDefuzzificator[size];
            defuzzyVector.copyInto(ret);
            return ret;
        }
        return null;
    }
    
    /**
     * Returns the defuzzificator with the passed name.
     *
     * @param name the name as identifierAbstractDefuzzificatornstance of type
     * <code>Defuzzificator</code> or <AbstractDefuzzificatore> if not found
     * @see com.jfuzzy.core.AbstractDefuzzificator
     */
    public static AbstractDefuzzificator getDefuzzificator(String name) {
        return (AbstractDefuzzificator) defuzzyOperators.get(name);
    }
    
    /**
     * Registers a new defuzzificator on fuzzy system.
     *
     * @param defuzzy the new defuzzificaAbstractDefuzzificatorering
     * @return an instance of type <code>Defuzzificator</code> if such object
     * with tAbstractDefuzzificatorlready exists or <code>null</code>
     * @see com.jfuzzy.core.AbstractDefuzzificator
     */
    public static AbstractDefuzzificator registerDefuzzificator(AbstractDefuzzificator defuzzy) {
        return (AbstractDefuzzificator) defuzzyOperators.put(defuzzy.toString(),
                defuzzy);
    }
}
