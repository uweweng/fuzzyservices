/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
final class OperatorUtils {

    /** 
     * Converts the operator to an operator bean. 
     * @return An operator bean 
     * @param operator The operator to be converted 
     */
    final static Operator convert(AbstractOperator operator) {
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
    final static AbstractOperator convert(Operator operator) {
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
