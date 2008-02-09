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
 * AbstractMembershipFunctionTableModel
 *
 * @author Uwe Weng
 */
public abstract class AbstractMembershipFunctionTableModel extends AbstractTableModel {

    final public static int INDEX_X_COLUMN = 0;
    final public static int INDEX_DEGREE_OF_MEMBERSHIP_COLUMN = 1;

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case INDEX_X_COLUMN:
                return FuzzyResourceManager.getString(this, "MEMBERSHIP_FUNCTION_TABLE_MODEL_COLUMN_X");
            case INDEX_DEGREE_OF_MEMBERSHIP_COLUMN:
                return FuzzyResourceManager.getString(this, "MEMBERSHIP_FUNCTION_TABLE_MODEL_COLUMN_DEGREE_OF_MEMBERSHIP");
            default:
                return null;
        }
    }
}
