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

import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import java.io.Serializable;
import net.sourceforge.fuzzyservices.core.operator.Min;

/**
 * This class represents an if-clause (antecedent) of a rule according to
 * JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.Antecedent
 *
 * @version 1.0
 * @author Uwe Weng
 */
public final class Antecedent implements VetoableChangeListener, Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    //
    // Bound property names
    //
    /** Bound property name for <code>compatibilityOperator</code>. */
    public static final String COMPATIBILITY_OPERATOR_PROPERTY = "compatibilityOperator";
    /** Bound property name for <code>linguisticVariableName</code>. */
    public static final String LINGUISTIC_VARIABLE_NAME_PROPERTY = "linguisticVariableName";
    /** Bound property name for <code>linguisticTermName</code>. */
    public static final String LINGUISTIC_TERM_NAME_PROPERTY = "linguisticTermName";
    /** The name of a linguistic variable for this antecedent. */
    private String linguisticVariableName = null;
    /** The name of a linguistic term which is part of the linguistic variable. */
    private String linguisticTermName = null;
    /**
     * The operator for computing the compatibility between a fact and this
     * antecedent.
     */
    private Operator compatibilityOperator = OperatorUtils.convert(new Min());
    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Default <code>Antecedent</code> constructor.  This constructor sets a
     * default compatibility operator specified in
     * {@link net.sourceforge.fuzzyservices.core.Antecedent}, and registers itself as a vetoable
     * change listener.
     * @see net.sourceforge.fuzzyservices.core.Antecedent#getDefaultCompatibilityOperator
     */
    public Antecedent() {
        // Register vetoable change listener
        compatibilityOperator.addVetoableChangeListener(this);
    }

    /**
     * Constructs an new antecedent with specified property values.
     * @param newLinguisticVariableName The new value for the property <code>linguisticVariableName</code>.
     * @param newLinguisticTermName The new value for the property <code>linguisticTermName</code>.
     * @param newCompatibilityOperator The new value for the property <code>compatibilityOperator</code>.
     */
    public Antecedent(final String newLinguisticVariableName, final String newLinguisticTermName,
            final Operator newCompatibilityOperator) {
        setLinguisticVariableName(newLinguisticVariableName);
        setLinguisticTermName(newLinguisticTermName);
        setCompatibilityOperator(newCompatibilityOperator);
    }

    /**
     * Returns the compatibility operator of this antecedent.
     * @return the <code>compatibilityOperator</code> property
     * @see #setCompatibilityOperator
     */
    public Operator getCompatibilityOperator() {
        return compatibilityOperator;
    }

    /**
     * Returns the name of a linguistic term belongs to the antecedent.
     * @return the <code>linguisticTermName</code> property
     * @see #setLinguisticTermName
     */
    public String getLinguisticTermName() {
        return linguisticTermName;
    }

    /**
     * Returns the name of the linguistic variable belongs to this antecedent.
     * @return the <code>linguisticVariableName</code> property
     * @see #setLinguisticVariableName
     */
    public String getLinguisticVariableName() {
        return linguisticVariableName;
    }

    /**
     * Sets the compatibility operator of this rule.
     * @param compatibilityOp The new value for the property.
     * @see #getCompatibilityOperator
     */
    public void setCompatibilityOperator(final Operator compatibilityOp) {
        if (!compatibilityOp.isValidTNorm()) {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_T_NORM_OPERATOR"));
        }

        // Unregister vetoable change listener
        this.compatibilityOperator.removeVetoableChangeListener(this);

        Operator oldValue = this.compatibilityOperator;
        this.compatibilityOperator = compatibilityOp;
        // Register vetoable change listener
        this.compatibilityOperator.addVetoableChangeListener(this);
        propertyChangeSupport.firePropertyChange(COMPATIBILITY_OPERATOR_PROPERTY,
                oldValue, compatibilityOp);
    }

    /**
     * Sets the name of linguistic term belongs to the antecedent.
     * @param lingTermName The new value for the property.
     * @see #getLinguisticTermName
     */
    public void setLinguisticTermName(final String lingTermName) {
        String oldLingTermName = this.linguisticTermName;
        this.linguisticTermName = lingTermName;
        propertyChangeSupport.firePropertyChange(LINGUISTIC_TERM_NAME_PROPERTY,
                oldLingTermName, lingTermName);
    }

    /**
     * Sets the name of a linguistic variable belongs to the antecedent.
     * Additionally, the <code>linguisticTermName</code> property is
     * set to <code>null</code>.
     * @param lingVariableName The new value for the property.
     * @see #getLinguisticVariableName
     */
    public void setLinguisticVariableName(final String lingVariableName) {
        String oldLingVariableName = this.linguisticVariableName;
        this.linguisticVariableName = lingVariableName;
        propertyChangeSupport.firePropertyChange(LINGUISTIC_VARIABLE_NAME_PROPERTY,
                oldLingVariableName, lingVariableName);
        // Delete linguistic term
        setLinguisticTermName(null);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void vetoableChange(final PropertyChangeEvent evt)
            throws PropertyVetoException {
        if (evt.getSource() == this.compatibilityOperator) {
            // Check norm
            Operator newOp = new Operator();
            newOp.setType(this.compatibilityOperator.getType());
            newOp.setParameter(this.compatibilityOperator.getParameter());

            String prop = evt.getPropertyName();

            if (prop.equals(Operator.PARAMETER_PROPERTY)) {
                newOp.setParameter(((Float) evt.getNewValue()).floatValue());
            }

            if (prop.equals(Operator.TYPE_PROPERTY)) {
                newOp.setType((String) evt.getNewValue());
            }

            if (!newOp.isValidTNorm()) {
                throw new PropertyVetoException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_INVALID_T_NORM_OPERATOR"), evt);
            }
        }
    }
}
