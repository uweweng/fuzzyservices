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

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * DefaultMembershipFunctionTableModel
 *
 * @author Uwe Weng
 */
public class DefaultMembershipFunctionTableModel
    extends AbstractMembershipFunctionTableModel implements ChangeListener {
    private MembershipFunctionModel model = new DefaultMembershipFunctionModel();

    public DefaultMembershipFunctionTableModel(MembershipFunctionModel model) {
        this.model = model;

        if (model != null) {
            model.addChangeListener(this);
        }
    }

    @Override
    public int getRowCount() {
        return (model != null) ? model.getXValues().length : 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (model == null) {
            return null;
        }

        switch (columnIndex) {
        case INDEX_X_COLUMN:
            return model.getXValues()[rowIndex];

        case INDEX_DEGREE_OF_MEMBERSHIP_COLUMN:
            return model.getDegreeOfMembership(model.getXValues()[rowIndex]);

        default:
            return null;
        }
    }

    @Override
    public final void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (model == null) {
            return;
        }

        float x = model.getXValues()[rowIndex];
        float dom = model.getDegreeOfMembership(x);

        switch (columnIndex) {
        case INDEX_X_COLUMN:
            model.removePointAt(x);
            model.addPoint(Float.parseFloat(aValue.toString()), dom);

            break;

        case INDEX_DEGREE_OF_MEMBERSHIP_COLUMN:
            model.removePointAt(x);
            model.addPoint(x, Float.parseFloat(aValue.toString()));

            break;
        }
    }

    @Override
    public final void stateChanged(ChangeEvent e) {
        fireTableDataChanged();
    }
}
