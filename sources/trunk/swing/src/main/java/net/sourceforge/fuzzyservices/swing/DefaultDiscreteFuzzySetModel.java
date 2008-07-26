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
package net.sourceforge.fuzzyservices.swing;

import java.util.ArrayList;
import java.util.List;


/**
 * DefaultDiscreteFuzzySetModel
 *
 * @author Uwe Weng
 */
public class DefaultDiscreteFuzzySetModel extends AbstractDiscreteFuzzySetModel {
    private List<Entry> objects = new ArrayList<Entry>();

    public DefaultDiscreteFuzzySetModel() {
    }

    @Override
    public int size() {
        return objects.size();
    }

    @Override
    public final float getDegreeOfMembershipOf(final Object object) {
        return objects.get(objects.indexOf(object)).degreeOfMembership;
    }

    @Override
    public Object getObjectAt(final int rowIndex) {
        return objects.get(rowIndex).object;
    }

    @Override
    public final void setDegreeOfMembershipAt(final float degreeOfMembership, final int rowIndex) {
        objects.get(rowIndex).degreeOfMembership = degreeOfMembership;
        fireDegreeOfMembershipChanged(this, objects.get(rowIndex).object);
    }

    @Override
    public final void setObjectAt(final Object aValue, final int rowIndex) {
        Object oldObject = objects.get(rowIndex).object;
        objects.get(rowIndex).object = aValue;
        fireObjectRemoved(this, oldObject);
        fireObjectAdded(this, aValue);
    }

    class Entry {
        public Object object;
        public float degreeOfMembership;

        public Entry(final Object object, final float degreeOfMembership) {
            this.object = object;
            this.degreeOfMembership = degreeOfMembership;
        }
    }
}
