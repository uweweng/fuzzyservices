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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This class represents a fuzzy interval according to JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.FuzzyInterval
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class FuzzyInterval implements Serializable, PropertyChangeListener, VetoableChangeListener {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Unique technical identifier. Only for persistence is used.
     */
    private int id;
    //
    // Bound property names
    //
    /** Bound property name for <code>membershipFunction</code>. */
    public static final String MEMBERSHIP_FUNCTION_PROPERTY = "membershipFunction";
    /** Bound property name for <code>negative</code>. */
    public static final String NEGATIVE_PROPERTY = "negative";
    /** Bound property name for <code>positive</code>. */
    public static final String POSITIVE_PROPERTY = "positive";
    /** Bound property name for <code>validFuzzyLRInterval</code>. */
    public static final String VALID_FUZZY_LR_INTERVAL_PROPERTY = "validFuzzyLRInterval";
    /** The membership function property.*/
    private MembershipFunction membershipFunction = null;
    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Default <code>FuzzyInterval</code> constructor. This constructor
     * creates a valid default fuzzy interval with the coordinates 
     * (0.0, 0.0), (1.0, 1.0), (2.0, 1.0) and (3.0, 0.0).
     */
    public FuzzyInterval() {
        // Create default fuzzy interval
        membershipFunction = new MembershipFunction(1.0f, 2.0f, 1.0f, 1.0f);
    }

    /**
     * Returns the technical identifier (e.g. within a database).
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the membership function.
     * @return the <code>membershipFunction</code> property
     * @see #setMembershipFunction(MembershipFunction)
     */
    public final MembershipFunction getMembershipFunction() {
        return membershipFunction;
    }

    /**
     * Sets the membership function.
     * @param newMembershipFunction The new value for the property.
     * @see #getMembershipFunction()
     */
    public final void setMembershipFunction(final MembershipFunction newMembershipFunction) {
        FuzzySet fs = new FuzzySet(newMembershipFunction);
        if (!fs.isValidFuzzyInterval()) {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_INTERVAL"));
        }
        MembershipFunction oldValue = this.membershipFunction;
        boolean oldPositive = isPositive();
        boolean oldNegative = isNegative();
        boolean oldIsValidFuzzyLRInterval = isValidFuzzyLRInterval();
        if (membershipFunction != null) {
            this.membershipFunction.removePropertyChangeListener(this);
            this.membershipFunction.removeVetoableChangeListener(this);
        }
        this.membershipFunction = newMembershipFunction;
        if (membershipFunction != null) {
            this.membershipFunction.addPropertyChangeListener(this);
            this.membershipFunction.addVetoableChangeListener(this);
        }
        propertyChangeSupport.firePropertyChange(MEMBERSHIP_FUNCTION_PROPERTY,
                oldValue, newMembershipFunction);
        propertyChangeSupport.firePropertyChange(POSITIVE_PROPERTY,
                oldPositive, isPositive());
        propertyChangeSupport.firePropertyChange(NEGATIVE_PROPERTY,
                oldNegative, isNegative());
        propertyChangeSupport.firePropertyChange(VALID_FUZZY_LR_INTERVAL_PROPERTY,
                oldIsValidFuzzyLRInterval, isValidFuzzyLRInterval());
    }

    /**
     * Inverts this fuzzy interval.
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     */
    public final void invert() throws PropertyVetoException {
        if (membershipFunction != null) {
            membershipFunction.invert();
        }
    }

    /**
     * Checks whether the fuzzy interval is negative. A fuzzy interval is negative
     * if degree of membership is only greater than zero when x is lower than zero.
     * @return <code>true</code> if fuzzy interval is negative, <code>false</code> otherwise
     */
    public final synchronized boolean isNegative() {
        if (membershipFunction != null) {

            for (Iterator<Float> it = membershipFunction.iterator(); it.hasNext();) {
                float x = it.next();
                if (x >= 0.0f) {
                    if (membershipFunction.getDegreeOfMembership(x) > 0.0f) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Checks whether the fuzzy interval is positive. A fuzzy interval is positive
     * if degree of membership is only greater than zero when x is also greater than zero.
     * @return <code>true</code> if fuzzy interval is positive, <code>false</code> otherwise
     */
    public final synchronized boolean isPositive() {
        if (membershipFunction != null) {

            for (Iterator<Float> it = membershipFunction.iterator(); it.hasNext();) {
                float x = it.next();
                if (x <= 0.0f) {
                    if (membershipFunction.getDegreeOfMembership(x) > 0.0f) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Checks whether the fuzzy interval fulfills the requirements of a fuzzy
     * LR interval.
     * In addition to the requirements for a fuzzy interval the membership
     * function must be represented by two reference function L and R.
     * @return <code>true</code> if the fuzzy interval would be a fuzzy LR
     * interval <code>false</code>, otherwise
     * @see #isValidFuzzyInterval
     */
    public final boolean isValidFuzzyLRInterval() {
        // Creating a copy
        MembershipFunction f = (MembershipFunction) membershipFunction.clone();
        f.reduce();
        if (f.size() == 4) {
            return true;
        }
        return false;
    }

    /**
     * Negates the fuzzy interval.
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     */
    public final void negate() throws PropertyVetoException {
        if (membershipFunction != null) {
            membershipFunction.negate();
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

    @Override
    public final void propertyChange(final PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();

        if (evt.getSource() == membershipFunction) {

            if (prop.equals(MembershipFunction.DEGREE_OF_MEMBERSHIP_PROPERTY)) {
                try {

                    MembershipFunctionPoint p = (MembershipFunctionPoint) evt.getOldValue();
                    FuzzyInterval oldFuzzyInterval = (FuzzyInterval) this.clone();
                    oldFuzzyInterval.getMembershipFunction().setDegreeOfMembership(p.getX(), p.getDegreeOfMembership());

                    propertyChangeSupport.firePropertyChange(POSITIVE_PROPERTY,
                            oldFuzzyInterval.isPositive(), isPositive());

                    propertyChangeSupport.firePropertyChange(NEGATIVE_PROPERTY,
                            oldFuzzyInterval.isNegative(), isNegative());

                    propertyChangeSupport.firePropertyChange(VALID_FUZZY_LR_INTERVAL_PROPERTY,
                            oldFuzzyInterval.isValidFuzzyLRInterval(), isValidFuzzyLRInterval());

                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FuzzyNumber.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public final void vetoableChange(final PropertyChangeEvent evt)
            throws PropertyVetoException {
        String prop = evt.getPropertyName();

        if (evt.getSource() == membershipFunction) {

            if (prop.equals(MembershipFunction.DEGREE_OF_MEMBERSHIP_PROPERTY)) {
                // Check constraints on cloned membership function
                MembershipFunctionPoint p = (MembershipFunctionPoint) evt.getNewValue();
                MembershipFunction m = (MembershipFunction) membershipFunction.clone();

                m.setDegreeOfMembership(p.getX(), p.getDegreeOfMembership());
                FuzzySet fs = new FuzzySet(m);
                if (!fs.isValidFuzzyInterval()) {
                    throw new PropertyVetoException(FuzzyResourceManager.getString(
                            this, "EXCEPTION_INVALID_FUZZY_INTERVAL"), evt);
                }
            }
        }
    }

    @Override
    public Object clone() {
        return SerializationUtils.clone(this);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        FuzzyInterval fi = (FuzzyInterval) obj;
        return new EqualsBuilder().append(this.id, fi.id).append(this.membershipFunction, fi.membershipFunction).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 21).append(this.id).append(this.membershipFunction).toHashCode();
    }

    @Override
    public String toString() {
        return toString(true);
    }

    /**
     * Returns a textual representation of the fuzzy interval
     *
     * @param withMembershipFunction
     *            <code>true</code> if all points of membership function are also returned,
     *            <code>false</code> otherwise.
     * @return a string representation of the fuzzy interval
     * @see MembershipFunction#toString
     */
    public String toString(final boolean withMembershipFunction) {
        if (withMembershipFunction) {
            return FuzzyResourceManager.getString(this,
                    "FUZZY_INTERVAL_WITH_MEMBERSHIP_FUNCTION",
                    new Object[]{
                        id,
                        membershipFunction.toString()
                    });
        } else {
            Defuzzificator def = new Defuzzificator(Defuzzificator.TYPE_MEAN_OF_MAX);
            return FuzzyResourceManager.getString(this,
                    "FUZZY_INTERVAL_WITHOUT_MEMBERSHIP_FUNCTION",
                    new Object[]{
                        id,
                        Float.toString(def.defuzzify(new FuzzySet(membershipFunction))),
                        membershipFunction.toString()
                    });
        }
    }
}
