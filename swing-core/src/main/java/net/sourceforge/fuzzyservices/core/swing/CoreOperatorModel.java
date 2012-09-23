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

import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.core.operator.AbstractParameteredOperator;
import net.sourceforge.fuzzyservices.swing.AbstractOperatorModel;

import java.util.Collection;
import java.util.Iterator;
import net.sourceforge.fuzzyservices.core.impl.OperatorManagerImpl;


/**
 * CoreOperatorModel
 *
 * @author Uwe Weng
 */
public class CoreOperatorModel extends AbstractOperatorModel {
    private AbstractOperator selectedOperator = null;
    private String[] operatorNames = null;

    public CoreOperatorModel() {
    }

    public CoreOperatorModel(AbstractOperator selectedOperator) {
        this.selectedOperator = selectedOperator;
    }

    @Override
    public String[] getOperatorNames() {
        if (operatorNames == null) {
            Collection<AbstractOperator> op = OperatorManagerImpl.getInstance().getOperators();

            if (op != null) {
                operatorNames = new String[op.size()];

                int i = 0;

                for (Iterator<AbstractOperator> it = op.iterator();
                        it.hasNext();) {
                    AbstractOperator abstractOperator = it.next();
                    operatorNames[i] = abstractOperator.getName();
                    i++;
                }
            }
        }

        return operatorNames;
    }

    @Override
    public String getSelectedOperatorName() {
        return (selectedOperator != null) ? selectedOperator.getName() : null;
    }

    @Override
    public final void setSelectedOperatorName(String name) {
        selectedOperator = OperatorManagerImpl.getInstance().getOperator(name);
        fireTypeChanged(this);
    }

    @Override
    public final float getParameter() {
        if ((selectedOperator != null) &&
                (selectedOperator.requiresParameter() == true)) {
            return ((AbstractParameteredOperator) selectedOperator).getParameter();
        }

        return Float.NaN;
    }

    @Override
    public final void setParameter(float parameter) {
        if ((selectedOperator != null) &&
                (selectedOperator.requiresParameter() == true)) {
            if (((AbstractParameteredOperator) selectedOperator).isValidParameter(
                        parameter) == true) {
                ((AbstractParameteredOperator) selectedOperator).setParameter(parameter);
            }

            fireParameterChanged(this);
        }
    }
}
