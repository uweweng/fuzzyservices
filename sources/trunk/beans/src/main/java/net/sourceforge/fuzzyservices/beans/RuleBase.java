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

import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import java.io.Serializable;
import net.sourceforge.fuzzyservices.core.operator.Max;

/**
 * This class represents a rule base according to JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.RuleBase
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class RuleBase implements VetoableChangeListener, Serializable {

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
    public static final String ACCUMULATION_OPERATOR_PROPERTY = "accumulationOperator";
    /** Name property. */
    private String name = null;
    /** Rules property. A rule base contains an array of rules. */
    private Rule[] rules = null;
    /** The operator for accumulating the matching results. */
    private Operator accumulationOperator = null;
    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Default <code>RuleBase</code> constructor.  This constructor sets a
     * default accumulation operator specified in
     * {@link net.sourceforge.fuzzyservices.core.RuleBase}, and registers itself as a vetoable
     * change listener.
     * @see net.sourceforge.fuzzyservices.core.RuleBase#getDefaultAccumulationOperator
     */
    public RuleBase() {
        accumulationOperator = OperatorUtils.convert(new Max());
        // Register vetoable change listener
        accumulationOperator.addVetoableChangeListener(this);
    }

    /**
     * Returns the name of this rule base.
     * @return the <code>name</code> property
     * @see #setName
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns all rules of this rule base.
     * @return the <code>rules</code> property
     * @see #setRules
     */
    public final Rule[] getRules() {
        return rules;
    }

    /**
     * Gets the rule located at the specified <code>index</code> of this rule
     * base.
     * @param     index the position of the rule to be returned.
     * @return    the rule located at the specified index.
     * @see #setRules
     */
    public final Rule getRules(final int index) {
        return getRules()[index];
    }

    /**
     * Returns the accumulation operator of this rule base.
     * @return the <code>accumulationOperator</code> property
     * @see #setAccumulationOperator
     */
    public final Operator getAccumulationOperator() {
        return accumulationOperator;
    }

    /**
     * Sets the accumulation operator of this rule base.
     * @param newAccumulationOperator The new value for the property.
     * @see #getAccumulationOperator
     */
    public final void setAccumulationOperator(final Operator newAccumulationOperator) {
        if (!newAccumulationOperator.isValidSNorm()) {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_S_NORM_OPERATOR"));
        }

        // Unregister vetoable change listener
        this.accumulationOperator.removeVetoableChangeListener(this);

        Operator oldValue = this.accumulationOperator;
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
    public final void setName(final String newName) {
        String oldValue = this.name;
        this.name = newName;
        propertyChangeSupport.firePropertyChange(NAME_PROPERTY, oldValue,
                newName);
    }

    /**
     * Fills the rule base with rules.
     * @param newRules an array with rules.
     * @see #getRules()
     */
    public final void setRules(final Rule[] newRules) {
        net.sourceforge.fuzzyservices.beans.Rule[] oldValue = this.rules;
        this.rules = newRules;
        propertyChangeSupport.firePropertyChange(RULES_PROPERTY, oldValue, rules);
    }

    /**
     * Replaces the rule at the position <code>index</code> of the rule base.
     * @param index The index value into the property array.
     * @param newRule the new rule for the rule base.
     * @see #getRules(int)
     */
    public final void setRules(final int index, final Rule newRule) {
        Rule oldValue = this.rules[index];
        this.rules[index] = newRule;

        if ((oldValue != null) && !oldValue.equals(newRule)) {
            propertyChangeSupport.firePropertyChange(RULES_PROPERTY, null,
                    this.rules);
        }
    }

    @Override
    public final void vetoableChange(final PropertyChangeEvent evt)
            throws PropertyVetoException {
        if (evt.getSource() == this.accumulationOperator) {
            // Check norm
            Operator newOp = new Operator();
            newOp.setType(this.accumulationOperator.getType());
            newOp.setParameter(this.accumulationOperator.getParameter());

            String prop = evt.getPropertyName();

            if (prop.equals(Operator.PARAMETER_PROPERTY)) {
                newOp.setParameter(((Float) evt.getNewValue()).floatValue());
            }

            if (prop.equals(Operator.TYPE_PROPERTY)) {
                newOp.setType((String) evt.getNewValue());
            }

            if (!newOp.isValidSNorm()) {
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
    public final synchronized void addPropertyChangeListener(
            final PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a <code>PropertyChangeListener</code> from the listener list.
     * This removes a <code>PropertyChangeListener</code> that was registered
     * for all properties.
     * @param listener  the <code>PropertyChangeListener</code> to be removed
     */
    public final synchronized void removePropertyChangeListener(
            final PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}