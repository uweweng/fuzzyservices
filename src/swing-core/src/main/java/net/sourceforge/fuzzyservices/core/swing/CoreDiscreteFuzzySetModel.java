/*
 *
 *  Copyright (C) 2008  Uwe Weng
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
package net.sourceforge.fuzzyservices.core.swing;

import net.sourceforge.fuzzyservices.core.DiscreteFuzzySet;
import net.sourceforge.fuzzyservices.swing.AbstractDiscreteFuzzySetModel;


/**
 * CoreDiscreteFuzzySetModel
 *
 * @author Uwe Weng
 */
public class CoreDiscreteFuzzySetModel extends AbstractDiscreteFuzzySetModel {
    private DiscreteFuzzySet discreteFuzzySet = new DiscreteFuzzySet();

    public CoreDiscreteFuzzySetModel() {
    }

    public CoreDiscreteFuzzySetModel(DiscreteFuzzySet discreteFuzzySet) {
        this.discreteFuzzySet = discreteFuzzySet;
    }

    @Override
    public final float getDegreeOfMembershipOf(Object object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getObjectAt(int rowIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public final void setDegreeOfMembershipAt(float degreeOfMembership, int rowIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public final void setObjectAt(Object aValue, int rowIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
