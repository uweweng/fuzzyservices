/*******************************************************************************
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
package net.sourceforge.fuzzyservices.swing;

import javax.swing.table.AbstractTableModel;
import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;

/**
 * AbstractAntecedentTableModel
 *
 * @author Uwe Weng
 */
public abstract class AbstractAntecedentTableModel extends AbstractTableModel {

    final public static int INDEX_LINGUISTIC_VARIABLE_NAME_COLUMN = 0;
    final public static int INDEX_LINGUISTIC_TERM_NAME_COLUMN = 1;
    final public static int INDEX_COMPATIBILITY_OPERATOR_COLUMN = 2;
    final public static int INDEX_COMPATIBILITY_OPERATOR_PARAMETER_COLUMN = 3;
    
    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case INDEX_LINGUISTIC_VARIABLE_NAME_COLUMN:
                return FuzzyResourceManager.getString(this, "ANTECEDENT_TABLE_MODEL_COLUMN_LINGUISTIC_VARIABLE_NAME");
            case INDEX_LINGUISTIC_TERM_NAME_COLUMN:
                return FuzzyResourceManager.getString(this, "ANTECEDENT_TABLE_MODEL_COLUMN_LINGUISTIC_TERM_NAME");
            case INDEX_COMPATIBILITY_OPERATOR_COLUMN:
                return FuzzyResourceManager.getString(this, "ANTECEDENT_TABLE_MODEL_COLUMN_COMPATIBILITY_OPERATOR_NAME");
            case INDEX_COMPATIBILITY_OPERATOR_PARAMETER_COLUMN:
                return FuzzyResourceManager.getString(this, "ANTECEDENT_TABLE_MODEL_COLUMN_COMPATIBILITY_OPERATOR_PARAMETER");
            default:
                return null;
        }
    }
    
    
}
