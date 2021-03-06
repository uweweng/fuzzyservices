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

import java.io.Serializable;

/**
 * The class <code>Consequent</code> represents the then-clause of a rule.
 * It contains the conclusion of approximate reasoning described as a term name of a linguistic variable.
 *
 * @see LinguisticVariable
 * @see Rule
 *
 * @version 1.0
 * @author Uwe Weng
 */
public final class Consequent extends RulePart implements Cloneable, Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a conclusion.
     */
    public Consequent() {
        super();
    }

    /**
     * Constructs a conclusion.
     * @param lvName linguistic variable name for describing a conclusion of an if-then-clause
     * @param lingTermName the name of a linguistic term which is part of the linguistic variable representing the conclusion.
     */
    public Consequent(final String lvName, final String lingTermName) {
        super(lvName, lingTermName);
    }
}
