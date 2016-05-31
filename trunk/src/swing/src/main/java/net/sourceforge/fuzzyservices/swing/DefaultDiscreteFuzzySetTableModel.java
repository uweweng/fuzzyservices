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
 * DefaultDiscreteFuzzySetTableModel
 *
 * @author Uwe Weng
 */
public class DefaultDiscreteFuzzySetTableModel
    extends AbstractDiscreteFuzzySetTableModel {
    private DiscreteFuzzySetModel model = new DefaultDiscreteFuzzySetModel();

    public DefaultDiscreteFuzzySetTableModel(DiscreteFuzzySetModel model) {
        this.model = model;
    }

    @Override
    public int getRowCount() {
        return (model != null) ? model.size() : 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (model == null) {
            return null;
        }

        switch (columnIndex) {
        case INDEX_OBJECT_COLUMN:
            return model.getObjectAt(rowIndex);

        case INDEX_DEGREE_OF_MEMBERSHIP_COLUMN:
            return model.getDegreeOfMembershipOf(model.getObjectAt(rowIndex));

        default:
            return null;
        }
    }

    @Override
    public final void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (model == null) {
            return;
        }

        switch (columnIndex) {
        case INDEX_OBJECT_COLUMN:
            model.setObjectAt(aValue, rowIndex);

            break;

        case INDEX_DEGREE_OF_MEMBERSHIP_COLUMN:
            model.setDegreeOfMembershipAt(Float.parseFloat(aValue.toString()),
                rowIndex);

            break;
        }
    }
}
