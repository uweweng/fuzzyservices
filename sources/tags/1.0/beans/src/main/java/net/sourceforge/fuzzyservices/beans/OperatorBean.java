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

import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;
import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.core.operator.AbstractParameteredOperator;
import net.sourceforge.fuzzyservices.core.operator.OperatorManager;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

/**
 * This class represents a fuzzy operator according to JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.AbstractOperator
 *
 * @since 1.0
 * @author Uwe Weng
 */
public final class OperatorBean implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    //
    // Bound property names
    //

    /** Bound property name for <code>type</code>. */
    public static final String TYPE_PROPERTY = "type";

    /** Bound property name for <code>parameter</code>. */
    public static final String PARAMETER_PROPERTY = "parameter";

    /**
     * The operator type is characterized by his name.
     *  @see net.sourceforge.fuzzyservices.core.operator.OperatorManager#getOperator
     */
    private String type = null;

    /**
     * Depending on selected <code>type</code> the operator has got a parameter.
     */
    private float parameter = Float.NaN;

    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);

    /** Support for any VetoableChangeListeners which have been registered. */
    private transient VetoableChangeSupport vetoableChangeSupport
            = new VetoableChangeSupport(this);

    /**
     * Default <code>OperatorBean</code> constructor. The operator type is
     * <strong>NOT</strong> defined!
     */
    public OperatorBean() {
        // Do nothing
    }

    /**
     * Returns the type of this operator.
     * @return the <code>type</code> property
     * @see #setType
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the parameter of this operator.
     * @return the <code>parameter</code> property
     * @see #setParameter
     */
    public float getParameter() {
        return parameter;
    }

    /**
     * Sets the operator type. If the new type requires a parameter, the
     * default value is set.
     * @param type The new value for the property.
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     * @see #getType
     */
    public void setType(String type) throws PropertyVetoException {
        String oldValue = this.type;
        float oldParameter = this.parameter;
        AbstractOperator op = OperatorManager.getOperator(type);

        if ((op != null) && (op instanceof AbstractParameteredOperator)) {
            AbstractParameteredOperator parameterOp
                    = (AbstractParameteredOperator) op;
            this.parameter = parameterOp.getDefaultParameter();
        }

        vetoableChangeSupport.fireVetoableChange(TYPE_PROPERTY, oldValue, type);
        this.type = type;
        propertyChangeSupport.firePropertyChange(TYPE_PROPERTY, oldValue, type);
        this.parameter = oldParameter;

        if ((op != null) && (op instanceof AbstractParameteredOperator)) {
            AbstractParameteredOperator parameterOp
                    = (AbstractParameteredOperator) op;
            this.parameter = parameterOp.getDefaultParameter();
        }
    }

    /**
     * Sets the parameter of this operator.
     * @param param The new value for the property.
     * @exception IllegalArgumentException if new parameter is invalid
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     * @see #getParameter
     */
    public void setParameter(float param)
    throws IllegalArgumentException, PropertyVetoException {
        float oldValue = this.parameter;
        AbstractOperator op = OperatorManager.getOperator(this.type);

        if ((op != null) && (op instanceof AbstractParameteredOperator)) {
            if (AbstractParameteredOperator.isValidParameter(
                    (AbstractParameteredOperator) op, param) == false) {
                throw new IllegalArgumentException(
                        FuzzyResourceManager.getString(this,
                        "EXCEPTION_OPERATOR_INVALID_PARAMETER",
                        new Object[] { Float.toString(param) }));
            }
        }

        vetoableChangeSupport.fireVetoableChange(PARAMETER_PROPERTY,
                new Float(oldValue), new Float(param));
        this.parameter = param;
        propertyChangeSupport.firePropertyChange(PARAMETER_PROPERTY,
                new Float(oldValue), new Float(param));
    }

    /**
     * Combines two fuzzy sets appropiated the operator type.
     * @return The result of this fuzzy operation
     * @param fs1 The first fuzzy set as operand
     * @param fs2 The second fuzzy set as operand
     */
    public FuzzySetBean combine(FuzzySetBean fs1, FuzzySetBean fs2) {
        AbstractOperator op = OperatorManager.getOperator(type);

        if (op != null) {
            if (op instanceof AbstractParameteredOperator) {
                ((AbstractParameteredOperator) op).setParameter(parameter);
            }

            return FuzzyBeanUtils.convert(op.combine(FuzzyBeanUtils.convert(fs1),
                    FuzzyBeanUtils.convert(fs2)));
        }

        return null;
    }

    /**
     * Indicates whether an operator fullfils the s-norm.
     * @return <code>true</code> if this operator fullfils the s-norm,
     * <code>false</code> otherwise.
     */
    public boolean isValidSNorm() {
        AbstractOperator op = OperatorManager.getOperator(type);

        if (op != null) {
            if (op instanceof AbstractParameteredOperator) {
                ((AbstractParameteredOperator) op).setParameter(parameter);
            }

            return op.isValidSNorm();
        }

        return false;
    }

    /**
     * Indicates whether an operator fullfils the t-norm.
     * @return <code>true</code> if this operator fullfils the t-norm,
     * <code>false</code> otherwise.
     */
    public boolean isValidTNorm() {
        AbstractOperator op = OperatorManager.getOperator(type);

        if (op != null) {
            if (op instanceof AbstractParameteredOperator) {
                ((AbstractParameteredOperator) op).setParameter(parameter);
            }

            return op.isValidTNorm();
        }

        return false;
    }

    /**
     * Adds a <code>VetoableChangeListener</code> to the listener list. The
     * listener is registered for all properties.
     * @param listener  the <code>VetoableChangeListener</code> to be added
     */
    public synchronized void addVetoableChangeListener(
            VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    /**
     * Removes a <code>VetoableChangeListener</code> from the listener list.
     * This removes a <code>VetoableChangeListener</code> that was registered
     * for all properties.
     * @param listener  the <code>VetoableChangeListener</code> to be removed
     */
    public synchronized void removeVetoableChangeListener(
            VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    /**
     * Adds a <code>PropertyChangeListener</code> to the listener list. The
     * listener is registered for all properties. <p>
     * A <code>PropertyChangeEvent</code> will get fired in response to setting
     * a bound property. <p>
     * @param listener  the <code>PropertyChangeListener</code> to be added
     */
    public synchronized void addPropertyChangeListener(
            final PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a <code>PropertyChangeListener</code> from the listener list.
     * This removes a <code>PropertyChangeListener</code> that was registered
     * for all properties.
     * @param listener  the <code>PropertyChangeListener</code> to be removed
     */
    public synchronized void removePropertyChangeListener(
            final PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}

