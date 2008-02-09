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
package net.sourceforge.fuzzyservices.beans;

import java.beans.PropertyEditorSupport;
import java.util.Collection;
import java.util.Iterator;
import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.core.operator.OperatorManager;

/**
 * Editor support for type property of a operator bean.
 * @author Uwe Weng
 */
public class OperatorTypeEditor extends PropertyEditorSupport {

    private static Collection<AbstractOperator> supportedTypes = OperatorManager.getOperators();
    private static String[] typeNames = null;
    private int selectedType;

    @Override
    public void setAsText(String text) throws java.lang.IllegalArgumentException {
        if ((text == null) || (text.equals(""))) {
            setValue(null);
            return;
        }
        AbstractOperator op = OperatorManager.getOperator(text);
        if (op != null) {
            setValue(op.toString());
            return;
        }
        throw new java.lang.IllegalArgumentException(text);
    }

    @Override
    public String getAsText() {
        String value = (String) getValue();
        if (value != null) {
            AbstractOperator op = OperatorManager.getOperator(value);
            if (op != null) {
                return op.toString();
            }
        }
        return "";
    }

    @Override
    public String[] getTags() {
        if (typeNames == null) {
            typeNames = new String[supportedTypes.size() + 1];
            typeNames[0] = "";
            int i = 0;
            for (Iterator<AbstractOperator> it = supportedTypes.iterator(); it.hasNext();) {
                AbstractOperator abstractOperator = it.next();
                typeNames[i] = abstractOperator.toString();
                i++;
            }

        }
        return typeNames;
    }
}
