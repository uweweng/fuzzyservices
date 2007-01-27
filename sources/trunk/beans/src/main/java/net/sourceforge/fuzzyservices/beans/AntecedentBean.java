/*******************************************************************************
 *
 *  Copyright (C) 2007  Uwe Weng
 *
 *  This file is part of JFuzzy, a library for processing fuzzy information.
 *
 *  JFuzzy is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  JFuzzy is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with JFuzzy; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  The license is also available at http://www.gnu.org/licenses/gpl.txt
 *
 ******************************************************************************/
package net.sourceforge.fuzzyservices.beans;

import net.sourceforge.fuzzyservices.core.Antecedent;
import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;

/**
 * This class represents an if-clause (antecedent) of a rule according to
 * JavaBeans conventions.
 *
 * @see com.jfuzzy.core.Antecedent
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class AntecedentBean implements VetoableChangeListener, Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    //
    // Bound property names
    //

    /** Bound property name for <code>compatibilityOperator</code>. */
    public static final String COMPATIBILITY_OPERATOR_PROPERTY
            = "compatibilityOperator";

    /** Bound property name for <code>linguisticVariable</code>. */
    public static final String LINGUISTIC_VARIABLE_PROPERTY
            = "linguisticVariable";

    /** Bound property name for <code>linguisticTerm</code>. */
    public static final String LINGUISTIC_TERM_PROPERTY = "linguisticTerm";

    /** The linguistic variable of this antecedent. */
    private LinguisticVariableBean linguisticVariable = null;

    /** The linguistic term which is part of the linguistic variable. */
    private LinguisticTermBean linguisticTerm = null;

    /**
     * The operator for computing the compatibility between a fact and this
     * antecedent.
     */
    private OperatorBean compatibilityOperator
            = FuzzyBeanUtils.convert(
            Antecedent.getDefaultCompatibilityOperator());

    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);

    /**
     * Default <code>AntecedentBean</code> constructor.  This constructor sets a
     * default compatibility operator specified in
     * {@link com.jfuzzy.core.Antecedent}, and registers itself as a vetoable
     * change listener.
     * @see com.jfuzzy.core.Antecedent#getDefaultCompatibilityOperator
     */
    public AntecedentBean() {
        compatibilityOperator = FuzzyBeanUtils.convert(
                Antecedent.getDefaultCompatibilityOperator());
        // Register vetoable change listener
        compatibilityOperator.addVetoableChangeListener(this);
    }

    /**
     * Returns the compatibility operator of this antecedent.
     * @return the <code>compatibilityOperator</code> property
     * @see #setCompatibilityOperator
     */
    public OperatorBean getCompatibilityOperator() {
        return compatibilityOperator;
    }

    /**
     * Returns the linguistic term of this antecedent.
     * @return the <code>linguisticTerm</code> property
     * @see #setLinguisticTerm
     */
    public LinguisticTermBean getLinguisticTerm() {
        return linguisticTerm;
    }

    /**
     * Returns the linguistic variable of this antecedent.
     * @return the <code>linguisticVariable</code> property
     * @see #setLinguisticVariable
     */
    public LinguisticVariableBean getLinguisticVariable() {
        return linguisticVariable;
    }

    /**
     * Sets the compatibility operator of this rule.
     * @param compatibilityOp The new value for the property.
     * @exception NullPointerException if <code>compatibilityOperator</code> is
     * <code>null</code>
     * @exception IllegalArgumentException if new operator does not fulfill the
     * requirements of t-norm
     * @see #getCompatibilityOperator
     */
    public void setCompatibilityOperator(final OperatorBean compatibilityOp)
    throws NullPointerException, IllegalArgumentException {
        if (compatibilityOp.isValidTNorm() == false) {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_T_NORM_OPERATOR"));
        }

        // Unregister vetoable change listener
        this.compatibilityOperator.removeVetoableChangeListener(this);

        OperatorBean oldValue = this.compatibilityOperator;
        this.compatibilityOperator = compatibilityOp;
        // Register vetoable change listener
        this.compatibilityOperator.addVetoableChangeListener(this);
        propertyChangeSupport.firePropertyChange(COMPATIBILITY_OPERATOR_PROPERTY,
                oldValue, compatibilityOp);
    }

    /**
     * Sets the linguistic term of this antecedent.
     * @param lingTerm The new value for the property.
     * @exception IllegalArgumentException if <code>linguisticTerm</code> is
     * not part of current linguistic variable property
     * @see #getLinguisticTerm
     */
    public void setLinguisticTerm(final LinguisticTermBean lingTerm)
    throws IllegalArgumentException {
        if ((lingTerm != null)
        && (this.linguisticVariable.contains(lingTerm) == false)) {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_UNKNOWN_LINGUISTIC_TERM", new Object[] {
                this.linguisticVariable.getName(), lingTerm.getName()
            }));
        }

        LinguisticTermBean oldValue = this.linguisticTerm;
        this.linguisticTerm = lingTerm;
        propertyChangeSupport.firePropertyChange(LINGUISTIC_TERM_PROPERTY,
                oldValue, lingTerm);
    }

    /**
     * Sets the linguistic variable of this consequent. Additionally, the
     * <code>linguisticTerm</code> property is
     * set to <code>null</code>.
     * @param lingVariable The new value for the property.
     * @see #getLinguisticVariable
     */
    public void setLinguisticVariable(final LinguisticVariableBean lingVariable) {
        LinguisticVariableBean oldValue = this.linguisticVariable;
        this.linguisticVariable = lingVariable;
        propertyChangeSupport.firePropertyChange(LINGUISTIC_VARIABLE_PROPERTY,
                oldValue, lingVariable);
        // Delete linguistic term
        setLinguisticTerm(null);
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
     * This method gets called when a constrained property is changed.
     * @param     evt a <code>PropertyChangeEvent</code> object describing the
     * event source and the property that has changed.
     * @exception PropertyVetoException if the recipient wishes the property
     * change to be rolled back.
     */
    public void vetoableChange(PropertyChangeEvent evt)
    throws PropertyVetoException {
        if (evt.getSource() == this.compatibilityOperator) {
            // Check norm
            OperatorBean newOp = new OperatorBean();
            newOp.setType(this.compatibilityOperator.getType());
            newOp.setParameter(this.compatibilityOperator.getParameter());

            String prop = evt.getPropertyName();
            if (prop.equals(OperatorBean.PARAMETER_PROPERTY) == true) {
                newOp.setParameter(((Float) evt.getNewValue()).floatValue());
            }

            if (prop.equals(OperatorBean.TYPE_PROPERTY) == true) {
                newOp.setType((String) evt.getNewValue());
            }

            if (newOp.isValidTNorm() == false) {
                throw new PropertyVetoException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_INVALID_T_NORM_OPERATOR"), evt);
            }
        }

        if ((evt.getSource() == this.linguisticVariable) &&
                (this.linguisticTerm != null)) {
            // Is lingusitic term already defined?
            boolean isDefined = false;
            Object newValue = evt.getNewValue();
            LinguisticTermBean[] newTerms = (LinguisticTermBean[]) newValue;

            if (newTerms != null) {
                for (int i = 0; i < newTerms.length; i++) {
                    if ((newTerms[i] != null) &&
                            (newTerms[i].equals(this.linguisticTerm) == true)) {
                        isDefined = true;

                        break;
                    }
                }
            }

            if (isDefined == false) {
                throw new PropertyVetoException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_UNKNOWN_LINGUISTIC_TERM",
                        new Object[] {
                    ((LinguisticVariableBean) evt.getSource()).getName(),
                    this.linguisticTerm.getName()}), evt);
            }
        }
    }
}


