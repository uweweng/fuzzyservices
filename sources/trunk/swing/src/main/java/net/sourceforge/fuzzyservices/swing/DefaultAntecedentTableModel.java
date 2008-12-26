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
 * DefaultAntecedentTableModel
 *
 * @author Uwe Weng
 */
public class DefaultAntecedentTableModel extends AbstractAntecedentTableModel {
    private RuleModel model = new DefaultRuleModel();

    public DefaultAntecedentTableModel(RuleModel ruleModel) {
        this.model = ruleModel;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (model == null) {
            return null;
        }

        switch (columnIndex) {
        case INDEX_LINGUISTIC_VARIABLE_NAME_COLUMN:
            return model.getLingVarNameOfAntecedentAt(rowIndex);

        case INDEX_LINGUISTIC_TERM_NAME_COLUMN:
            return model.getLingTermNameOfAntecedentAt(rowIndex);

        case INDEX_COMPATIBILITY_OPERATOR_COLUMN:
            return model.getCompatibilityOperatorOfAntecedentAt(rowIndex)
                        .getSelectedOperatorName();

        case INDEX_COMPATIBILITY_OPERATOR_PARAMETER_COLUMN:
            return model.getCompatibilityOperatorOfAntecedentAt(rowIndex)
                        .getParameter();

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
        case INDEX_LINGUISTIC_VARIABLE_NAME_COLUMN:
            model.setLingVarNameOfAntecedentAt(aValue.toString(), rowIndex);

            break;

        case INDEX_LINGUISTIC_TERM_NAME_COLUMN:
            model.setLingTermNameOfAntecedentAt(aValue.toString(), rowIndex);

            break;

        case INDEX_COMPATIBILITY_OPERATOR_COLUMN:
            model.getCompatibilityOperatorOfAntecedentAt(rowIndex)
                 .setSelectedOperatorName(aValue.toString());

            break;

        case INDEX_COMPATIBILITY_OPERATOR_PARAMETER_COLUMN:
            model.getCompatibilityOperatorOfAntecedentAt(rowIndex)
                 .setParameter(Float.parseFloat(aValue.toString()));

            break;

        default:
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public int getRowCount() {
        return (model != null) ? model.getSizeOfAntecedents() : 0;
    }
}
