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

import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;
import net.sourceforge.fuzzyservices.core.RuleBase;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;

/**
 * This class represents a rule base according to JavaBeans conventions.
 *
 * @see com.jfuzzy.core.RuleBase
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class RuleBaseBean implements VetoableChangeListener, Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    //
    // Bound property names
    //

    /** Bound property name for <code>name</code>. */
    public static final String NAME_PROPERTY = "name";

    /** Bound property name for <code>rules</code>. */
    public static final String RULES_PROPERTY = "rules";

    /** Bound property name for <code>accumulationOperator</code>. */
    public static final String ACCUMULATION_OPERATOR_PROPERTY
            = "accumulationOperator";

    /** Name property. */
    private String name = null;

    /** Rules property. A rule base contains an array of rules. */
    private RuleBean[] rules = null;

    /** The operator for accumulating the matching results. */
    private OperatorBean accumulationOperator
            = FuzzyBeanUtils.convert(RuleBase.getDefaultAccumulationOperator());

    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);

    /**
     * Default <code>RuleBaseBean</code> constructor.  This constructor sets a
     * default accumulation operator specified in
     * {@link com.jfuzzy.core.RuleBase}, and registers itself as a vetoable
     * change listener.
     * @see com.jfuzzy.core.RuleBase#getDefaultAccumulationOperator
     */
    public RuleBaseBean() {
        accumulationOperator = FuzzyBeanUtils.convert(
                RuleBase.getDefaultAccumulationOperator());
        // Register vetoable change listener
        accumulationOperator.addVetoableChangeListener(this);
    }

    /**
     * Returns the name of this fact base.
     * @return the <code>name</code> property
     * @see #setName
     */
    public String getName() {
        return name;
    }

    /**
     * Returns all rules of this rule base.
     * @return the <code>rules</code> property
     * @see #setRules
     */
    public RuleBean[] getRules() {
        return rules;
    }

    /**
     * Gets the rule located at the specified <code>index</code> of this rule
     * base.
     * @param     index the position of the rule to be returned.
     * @return    the rule located at the specified index.
     * @exception ArrayIndexOutOfBoundsException if <code>index</code> is an
     * invalid position
     * @see #setRules
     */
    public RuleBean getRules(int index) throws ArrayIndexOutOfBoundsException {
        return getRules()[index];
    }

    /**
     * Returns the accumulation operator of this rule base.
     * @return the <code>accumulationOperator</code> property
     * @see #setAccumulationOperator
     */
    public OperatorBean getAccumulationOperator() {
        return accumulationOperator;
    }

    /**
     * Sets the accumulation operator of this rule base.
     * @param newAccumulationOperator The new value for the property.
     * @exception NullPointerException if <code>accumulationOperator</code> is
     * <code>null</code>
     * @exception IllegalArgumentException if new operator does not fulfill the
     * requirements of s-norm
     * @see #getAccumulationOperator
     */
    public void setAccumulationOperator(OperatorBean newAccumulationOperator)
    throws NullPointerException, IllegalArgumentException {
        if (newAccumulationOperator.isValidSNorm() == false) {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_S_NORM_OPERATOR"));
        }

        // Unregister vetoable change listener
        this.accumulationOperator.removeVetoableChangeListener(this);

        OperatorBean oldValue = this.accumulationOperator;
        this.accumulationOperator = newAccumulationOperator;
        // Register vetoable change listener
        this.accumulationOperator.addVetoableChangeListener(this);
        propertyChangeSupport.firePropertyChange(ACCUMULATION_OPERATOR_PROPERTY,
                oldValue, newAccumulationOperator);
    }

    /**
     * Sets the name of this rule base.
     * @param newName The new value for the property.
     * @see #getName
     */
    public void setName(String newName) {
        String oldValue = this.name;
        this.name = newName;
        propertyChangeSupport.firePropertyChange(NAME_PROPERTY, oldValue,
                newName);
    }

    /**
     * Fills the rule base with rules.
     * @param rules An array with rules.
     * @see #getRules()
     */
    public void setRules(RuleBean[] rules) {
        net.sourceforge.fuzzyservices.beans.RuleBean[] oldValue = this.rules;
        this.rules = rules;
        propertyChangeSupport.firePropertyChange(RULES_PROPERTY, oldValue,
                rules);
    }

    /**
     * Replaces the rule at the position <code>index</code> of the rule base.
     * @param index The index value into the property array.
     * @param rules The new rule for the rule base.
     * @exception ArrayIndexOutOfBoundsException if <code>index</code> is an
     * invalid position
     * @see #getRules(int)
     */
    public void setRules(int index, RuleBean rules)
    throws ArrayIndexOutOfBoundsException {
        RuleBean oldValue = this.rules[index];
        this.rules[index] = rules;

        if ((oldValue != null) && !oldValue.equals(rules)) {
            propertyChangeSupport.firePropertyChange(RULES_PROPERTY, null,
                    this.rules);
        }

        ;
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
        if (evt.getSource() == this.accumulationOperator) {
            // Check norm
            OperatorBean newOp = new OperatorBean();
            newOp.setType(this.accumulationOperator.getType());
            newOp.setParameter(this.accumulationOperator.getParameter());

            String prop = evt.getPropertyName();
            if (prop.equals(OperatorBean.PARAMETER_PROPERTY) == true) {
                newOp.setParameter(((Float) evt.getNewValue()).floatValue());
            }

            if (prop.equals(OperatorBean.TYPE_PROPERTY) == true) {
                newOp.setType((String) evt.getNewValue());
            }

            if (newOp.isValidSNorm() == false) {
                throw new PropertyVetoException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_INVALID_S_NORM_OPERATOR"), evt);
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


