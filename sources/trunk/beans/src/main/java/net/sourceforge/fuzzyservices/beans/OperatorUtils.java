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
package net.sourceforge.fuzzyservices.beans;

import java.beans.PropertyVetoException;
import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.core.operator.AbstractParameteredOperator;
import net.sourceforge.fuzzyservices.core.impl.OperatorManagerImpl;

/** 
 * Utility class for converting between core and beans operator. 
 * 
 * @since 1.0 
 * @author Uwe Weng 
 */
public final class OperatorUtils {

    /** 
     * Converts the operator to an operator bean. 
     * @return An operator bean 
     * @param operator The operator to be converted 
     */
    public static Operator convert(AbstractOperator operator) {
        if (operator != null) {
            Operator op = new Operator();
            try {
                op.setType(operator.toString());
                if (operator.requiresParameter() == true) {
                    AbstractParameteredOperator parameterOperator = (AbstractParameteredOperator) operator;
                    op.setParameter(parameterOperator.getParameter());
                }
            } catch (PropertyVetoException e) {
                // do nothing 
            }
            return op;
        }
        return null;
    }

    /** 
     * Converts the operator bean to a basic operator. 
     * @return A basic operator object 
     * @param operator The operator bean to be converted 
     */
    public static AbstractOperator convert(Operator operator) {
        if (operator != null) {
            AbstractOperator op = OperatorManagerImpl.getInstance().getOperator(operator.getType());
            if (op != null) {
                if (op.requiresParameter() == true) {
                    ((AbstractParameteredOperator) op).setParameter(operator.getParameter());
                }
                return op;
            }
        }
        return null;
    }
}
