/*******************************************************************************
 *
 *  Copyright (C) 2007  Uwe Weng
 *
 *  This file is part of Fuzzy Services on SourceForge.net,
 *  a library for processing fuzzy information.
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
import net.sourceforge.fuzzyservices.core.Rule;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;

/**
 * This class represents a rule according to JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.Rule
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class RuleBean implements VetoableChangeListener, Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    //
    // Bound property names
    //

    /** Bound property name for <code>antecedents</code>. */
    public static final String ANTECEDENTS_PROPERTY = "antecedents";

    /** Bound property name for <code>consequents</code>. */
    public static final String CONSEQUENTS_PROPERTY = "consequents";

    /** Bound property name for <code>aggregationOperator</code>. */
    public static final String AGGREGATION_OPERTAOR_PROPERTY
            = "aggregationOperator";

    /** Bound property name for <code>certaintyOperator</code>. */
    public static final String CERTAINTY_OPERATOR_PROPERTY
            = "certaintyOperator";

    /** Bound property name for <code>inferenceOperator</code>. */
    public static final String INFERENCE_OPERATOR_PROPERTY
            = "inferenceOperator";

    /** Bound property name for <code>certainty</code>. */
    public static final String CERTAINTY_PROPERTY = "certainty";

    /** Antecedents property as if-clause of this rule. */
    private AntecedentBean[] antecedents = null;

    /** Consequents property as then-clause of this rule. */
    private ConsequentBean[] consequents = null;

    /** The aggregation operator property. */
    private OperatorBean aggregationOperator = FuzzyBeanUtils.convert(
            Rule.getDefaultAggregationOperator());

    /** The certainty operator property. */
    private OperatorBean certaintyOperator = FuzzyBeanUtils.convert(
            Rule.getDefaultCertaintyOperator());

    /** The inference operator property. */
    private OperatorBean inferenceOperator = FuzzyBeanUtils.convert(
            Rule.getDefaultInferenceOperator());

    /**
     * The certainty property. It is a real number in the area of [0,1]. The
     * default is 0.
     */
    private float certainty = 0.0f;

    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);

    /**
     * Default <code>RuleBean</code> constructor.  This constructor sets the
     * default operators specified in {@link net.sourceforge.fuzzyservices.core.Rule}, and
     * registers itself as vetoable change listeners.
     * @see net.sourceforge.fuzzyservices.core.Rule#getDefaultAggregationOperator
     * @see net.sourceforge.fuzzyservices.core.Rule#getDefaultCertaintyOperator
     * @see net.sourceforge.fuzzyservices.core.Rule#getDefaultInferenceOperator
     */
    public RuleBean() {
        aggregationOperator = FuzzyBeanUtils.convert(
                Rule.getDefaultAggregationOperator());
        certaintyOperator = FuzzyBeanUtils.convert(
                Rule.getDefaultCertaintyOperator());
        // Register vetoable change listener
        certaintyOperator.addVetoableChangeListener(this);
        inferenceOperator = FuzzyBeanUtils.convert(
                Rule.getDefaultInferenceOperator());
        // Register vetoable change listener
        inferenceOperator.addVetoableChangeListener(this);
    }

    /**
     * Returns all antecedents of this rule.
     * @return the <code>antecedents</code> property
     * @see #setAntecedents
     */
    public AntecedentBean[] getAntecedents() {
        return antecedents;
    }

    /**
     * Gets the antecedent located at the specified <code>index</code> of this
     * rule.
     * @param     index the position of the antecedent to be returned.
     * @return    the antecedent located at the specified index.
     * @exception ArrayIndexOutOfBoundsException if <code>index</code> is an
     * invalid position
     * @see #setAntecedents
     */
    public AntecedentBean getAntecedents(int index)
    throws ArrayIndexOutOfBoundsException {
        return getAntecedents()[index];
    }

    /**
     * Returns all consequents of this rule.
     * @return the <code>consequents</code> property
     * @see #setConsequents
     */
    public ConsequentBean[] getConsequents() {
        return consequents;
    }

    /**
     * Gets the consequent located at the specified <code>index</code> of this
     * rule.
     * @param     index the position of the consequent to be returned.
     * @return    the consequent located at the specified index.
     * @exception ArrayIndexOutOfBoundsException if <code>index</code> is an
     * invalid position
     * @see #setConsequents
     */
    public ConsequentBean getConsequents(int index)
    throws ArrayIndexOutOfBoundsException {
        return getConsequents()[index];
    }

    /**
     * Returns the aggregation operator of this rule.
     * @return the <code>aggregationOperator</code> property
     * @see #setAggregationOperator
     */
    public OperatorBean getAggregationOperator() {
        return aggregationOperator;
    }

    /**
     * Returns the certainty operator of this rule.
     * @return the <code>certaintyOperator</code> property
     * @see #setCertaintyOperator
     */
    public OperatorBean getCertaintyOperator() {
        return certaintyOperator;
    }

    /**
     * Returns the inference operator of this rule.
     * @return the <code>inferenceOperator</code> property
     * @see #setInferenceOperator
     */
    public OperatorBean getInferenceOperator() {
        return inferenceOperator;
    }

    /**
     * Returns the certainty of this rule.
     * @return the <code>certainty</code> property
     * @see #setCertainty
     */
    public float getCertainty() {
        return certainty;
    }

    /**
     * Sets the aggregation operator of this rule.
     * @param newAggregationOperator The new value for the property.
     * @exception NullPointerException if <code>aggregationOperator</code> is
     * <code>null</code>
     * @exception IllegalArgumentException if new operator does not fulfill the
     * requirements of t-norm
     * @see #getAggregationOperator
     */
    public void setAggregationOperator(OperatorBean newAggregationOperator)
    throws NullPointerException, IllegalArgumentException {
        if (newAggregationOperator == null) {
            throw new NullPointerException();
        }

        OperatorBean oldValue = this.aggregationOperator;
        this.aggregationOperator = newAggregationOperator;
        propertyChangeSupport.firePropertyChange(AGGREGATION_OPERTAOR_PROPERTY,
                oldValue, newAggregationOperator);
    }

    /**
     * Defines new antecedents for this rule.
     * @param newAntecedents An array with antecedents
     * @see #getAntecedents(int)
     */
    public void setAntecedents(AntecedentBean[] newAntecedents) {
        AntecedentBean[] oldValue = this.antecedents;
        this.antecedents = newAntecedents;
        propertyChangeSupport.firePropertyChange(ANTECEDENTS_PROPERTY,
                oldValue, newAntecedents);
    }

    /**
     * Replaces a antecedent of this rule.
     * @param index The index value into the property array.
     * @param newAntecedents A new antecedent for this rule
     * @exception ArrayIndexOutOfBoundsException if <code>index</code> is an
     * invalid position
     * @see #getAntecedents(int)
     */
    public void setAntecedents(int index, AntecedentBean newAntecedents)
    throws ArrayIndexOutOfBoundsException {
        AntecedentBean oldValue = this.antecedents[index];
        this.antecedents[index] = newAntecedents;

        if ((oldValue != null) && !oldValue.equals(newAntecedents)) {
            propertyChangeSupport.firePropertyChange(ANTECEDENTS_PROPERTY,
                    null, this.antecedents);
        };
    }

    /**
     * Sets the certainty for this rule.
     * @param newCertainty The new value for the property.
     * @exception IllegalArgumentException if new certainty is invalid
     * @see #getCertainty
     */
    public void setCertainty(float newCertainty)
    throws IllegalArgumentException {
        if ((newCertainty < 0.0f) || (newCertainty > 1.0f)) {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_CERTAINTY",
                    new Object[] { Float.toString(newCertainty) }));
        }

        float oldValue = this.certainty;
        this.certainty = newCertainty;
        propertyChangeSupport.firePropertyChange(CERTAINTY_PROPERTY,
                new Float(oldValue), new Float(newCertainty));
    }

    /**
     * Sets the certainty operator of this rule.
     * @param newCertaintyOperator The new value for the property.
     * @exception NullPointerException if <code>certaintyOperator</code> is
     * <code>null</code>
     * @exception IllegalArgumentException if new operator does not fulfill the
     * requirements of t-norm
     * @see #getCertaintyOperator
     */
    public void setCertaintyOperator(OperatorBean newCertaintyOperator)
    throws NullPointerException, IllegalArgumentException {
        if (newCertaintyOperator.isValidTNorm() == false) {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_T_NORM_OPERATOR"));
        }

        // Unregister vetoable change listener
        this.certaintyOperator.removeVetoableChangeListener(this);

        OperatorBean oldValue = this.certaintyOperator;
        this.certaintyOperator = newCertaintyOperator;
        // Register vetoable change listener
        this.certaintyOperator.addVetoableChangeListener(this);
        propertyChangeSupport.firePropertyChange(CERTAINTY_OPERATOR_PROPERTY,
                oldValue, newCertaintyOperator);
    }

    /**
     * Defines new consequents for this rule.
     * @param newConsequents An array with consequents
     * @see #getConsequents()
     */
    public void setConsequents(ConsequentBean[] newConsequents) {
        ConsequentBean[] oldValue = this.consequents;
        this.consequents = newConsequents;
        propertyChangeSupport.firePropertyChange(CONSEQUENTS_PROPERTY,
                oldValue, newConsequents);
    }

    /**
     * Replaces a consequent of this rule.
     * @param index The index value into the property array.
     * @param newConsequents A new consequent for this rule
     * @exception ArrayIndexOutOfBoundsException if <code>index</code> is an
     * invalid position
     * @see #getConsequents()
     */
    public void setConsequents(int index, ConsequentBean newConsequents)
    throws ArrayIndexOutOfBoundsException {
        ConsequentBean oldValue = this.consequents[index];
        this.consequents[index] = newConsequents;

        if ((oldValue != null) && !oldValue.equals(newConsequents)) {
            propertyChangeSupport.firePropertyChange(CONSEQUENTS_PROPERTY,
                    null, this.consequents);
        };
    }

    /**
     * Sets the inference operator of this rule.
     * @param anInferenceOperator The new value for the property.
     * @exception NullPointerException if <code>inferenceOperator</code> is
     * <code>null</code>
     * @exception IllegalArgumentException if new operator does not fulfill the
     * requirements of t-norm
     * @see #getInferenceOperator
     */
    public void setInferenceOperator(OperatorBean anInferenceOperator)
    throws NullPointerException, IllegalArgumentException {
        if (anInferenceOperator.isValidTNorm() == false) {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_T_NORM_OPERATOR"));
        }

        // Unregister vetoable change listener
        this.inferenceOperator.removeVetoableChangeListener(this);

        OperatorBean oldValue = this.inferenceOperator;
        this.inferenceOperator = anInferenceOperator;
        // Register vetoable change listener
        this.inferenceOperator.addVetoableChangeListener(this);
        propertyChangeSupport.firePropertyChange(INFERENCE_OPERATOR_PROPERTY,
                oldValue, anInferenceOperator);
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
        String prop = evt.getPropertyName();
        if (evt.getSource() == certaintyOperator) {
            // Check norm
            OperatorBean newOp = new OperatorBean();
            newOp.setType(certaintyOperator.getType());
            newOp.setParameter(certaintyOperator.getParameter());

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

        if (evt.getSource() == inferenceOperator) {
            // Check norm
            OperatorBean newOp = new OperatorBean();
            newOp.setType(inferenceOperator.getType());
            newOp.setParameter(inferenceOperator.getParameter());

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


