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
package net.sourceforge.fuzzyservices.beans.swing;

import net.sourceforge.fuzzyservices.beans.Operator;
import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.swing.AbstractOperatorModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;

import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.fuzzyservices.core.impl.OperatorManagerImpl;

/**
 * OperatorBeanModel
 *
 * @author Uwe Weng
 */
public class OperatorBeanModel extends AbstractOperatorModel
        implements PropertyChangeListener {

    private Operator operator = null;
    private String[] operatorNames = null;

    public OperatorBeanModel() {
        operator = new Operator();
        operator.addPropertyChangeListener(this);
    }

    public OperatorBeanModel(Operator operator) {
        this.operator = operator;

        if (this.operator != null) {
            this.operator.addPropertyChangeListener(this);
        }
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
        return (operator != null) ? operator.getType() : null;
    }

    @Override
    public final void setSelectedOperatorName(String name) {
        if (operator != null) {
            try {
                operator.setType(name);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(OperatorBeanModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public final float getParameter() {
        return (operator != null) ? operator.getParameter() : 0.0f;
    }

    @Override
    public final void setParameter(float parameter) {
        if (operator != null) {
            try {
                operator.setParameter(parameter);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(OperatorBeanModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(OperatorBeanModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public final void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        if (Operator.PARAMETER_PROPERTY.equals(propertyName) == true) {
            fireParameterChanged(this);

            return;
        }

        if (Operator.TYPE_PROPERTY.equals(propertyName) == true) {
            fireTypeChanged(this);

            return;
        }
    }
}
