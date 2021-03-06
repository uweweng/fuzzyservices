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
package net.sourceforge.fuzzyservices.swing;


/**
 *
 * @author Uwe Weng
 */
public interface DiscreteFuzzySetModel {
    float getDegreeOfMembershipOf(final Object object);

    Object getObjectAt(final int rowIndex);

    void setDegreeOfMembershipAt(final float degreeOfMembership, final int rowIndex);

    void setObjectAt(final Object aValue, final int rowIndex);

    /**
     * Returns the number of objects in fuzzy set.
     *
     * @return        the size of discrete fuzzy set.
     */
    int size();

    void addDiscreteFuzzySetModelListener(final DiscreteFuzzySetModelListener l);

    void removeDiscreteFuzzySetModelListener(final DiscreteFuzzySetModelListener l);
}
