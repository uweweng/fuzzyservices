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
package net.sourceforge.fuzzyservices.core;

/**
 * There are different methods to defuzzify a membership function in order to get a crisp value.<p>
 * The <strong>abstract</strong> class <code>AbstractDefuzzificator</code> is the base class for implementing different
 * defuzzification methods.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public abstract class AbstractDefuzzificator implements java.io.Serializable {
    /**
     * Defuzzifies the membership function object.
     * @return The crisp value as result of the defuzzification
     * @param f The membership function to be defuzzified
     */
    public abstract float defuzzify(final MembershipFunction f);

    /**
     * Returns a textual representation of the defuzzificator
     * @return a string representation of the defuzzificator
     */
    public abstract String toString();

    /**
     * Indicates whether some other object is "equal to" this defuzzificator
     * @param obj the reference object with which to compare
     * @return <code>true</code> if this defuzzificator is the same as the <code>obj</code> argument,
     * <code>false</code> otherwise.
     */
    public abstract boolean equals(Object obj);
}
