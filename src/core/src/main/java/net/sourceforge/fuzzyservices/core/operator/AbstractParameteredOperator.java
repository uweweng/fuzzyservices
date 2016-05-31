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
package net.sourceforge.fuzzyservices.core.operator;

import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

import java.io.Serializable;

/**
 * The <strong>abstract</strong> class <code>AbstractParameteredOperator</code>
 * is the base class for the definiton of parameterized fuzzy operators.
 * The class is abstract because the calculation rules of operators are different.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public abstract class AbstractParameteredOperator
        extends AbstractOperator
        implements Serializable {

    /**
     * The parameter of the operator
     */
    protected float parameter;

    /**
     * Default constructor. The first parameter is the default parameter.
     * @see #getDefaultParameter
     */
    public AbstractParameteredOperator() {
        super();
        parameter = getDefaultParameter();
    }

    /**
     * Returns the default parameter.
     * @return The default parameter for an operator which needs a parameter.
     */
    public abstract float getDefaultParameter();

    /**
     * Returns the parameter of the operator.
     * @return the parameter
     * @see #setParameter
     */
    public final float getParameter() {
        return parameter;
    }

    /**
     * Indicates whether the argument is a valid parameter for an operator which
     * is an instance of a subclass.
     * @param param the value to be checked
     * @return <code>true</code> if argument is a valid parameter,
     * <code>false>/code> otherwise.
     */
    public abstract boolean isValidParameter(final float param);

    /**
     * Checks whether <code>param</code> is a valid parameter for the operator
     * <code>op</code>.
     * @param op The operator
     * @param param The parameter
     * @return <code>true</code>, if the value is a valid parameter for the
     * operator, <code>false</code> otherwise
     * @exception NullPointerException if <code>op</code> is <code>null</code>
     */
    public static boolean isValidParameter(final AbstractParameteredOperator op, final float param)
            throws NullPointerException {
        return op.isValidParameter(param);
    }

    @Override
    public final boolean requiresParameter() {
        return true;
    }

    /**
     * Sets the parameter of the operator.
     * @param param The new value
     * @exception IllegalArgumentException if new parameter is invalid
     * @see #getParameter
     */
    public final synchronized void setParameter(final float param)
            throws IllegalArgumentException {
        if (isValidParameter(param) == true) {
            this.parameter = param;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this,
                    "EXCEPTION_OPERATOR_INVALID_PARAMETER",
                    new Object[]{Float.toString(param)}));
        }
    }

    @Override
    public String toString() {
        return toString(false);
    }

    /**
     * Returns a textual representation of the operator
     * @param withParameter Decides whether the parameter is part of the
     * representation
     * @return a string representation of the operator
     */
    public abstract String toString(final boolean withParameter);
}
