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

/**
 * This class represents a fuzzy interval of type LR according to JavaBeans
 * conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.FuzzyLRInterval
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class FuzzyLRInterval implements Serializable, PropertyChangeListener, VetoableChangeListener {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    //
    // Bound property names
    //
    /** Bound property name for <code>membershipFunction</code>. */
    public static final String MEMBERSHIP_FUNCTION_PROPERTY = "membershipFunction";
    /** Bound property name for <code>negative</code>. */
    public static final String NEGATIVE_PROPERTY = "negative";
    /** Bound property name for <code>positive</code>. */
    public static final String POSITIVE_PROPERTY = "positive";
    /** Bound property name for <code>alpha</code>. */
    public static final String ALPHA_PROPERTY = "alpha";
    /** Bound property name for <code>beta</code>. */
    public static final String BETA_PROPERTY = "beta";
    /** The membership function property.*/
    private MembershipFunction membershipFunction = null;
    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Default <code>FuzzyLRInterval</code> constructor. This constructor
     * creates a valid default fuzzy interval of type LR with the coordinates
     * (0.0, 0.0), (1.0, 1.0), (2.0, 1.0) and (3.0, 0.0). Hence, this interval
     * has got an alpha = beta = 1.0.
     */
    public FuzzyLRInterval() {
        // Create default fuzzy LR interval
        membershipFunction = new MembershipFunction(1.0f, 2.0f, 1.0f, 1.0f);
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
        FuzzySet fs = new FuzzySet(membershipFunction);
        if (!fs.isValidFuzzyLRInterval()) {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_LR_INTERVAL"));
        }

        MembershipFunction oldValue = this.membershipFunction;
        boolean oldPositive = isPositive();
        boolean oldNegative = isNegative();
        float oldAlpha = getAlpha();
        float oldBeta = getBeta();
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
        propertyChangeSupport.firePropertyChange(ALPHA_PROPERTY,
                new Float(oldAlpha), new Float(getAlpha()));
        propertyChangeSupport.firePropertyChange(BETA_PROPERTY,
                new Float(oldBeta), new Float(getBeta()));
    }

    /**
     * Returns the spread on the left falling edge. It is the so-called alpha value.
     * @return the spread alpha
     */
    public final float getAlpha() {
        if (membershipFunction != null) {
            return membershipFunction.getAlpha();
        }
        return Float.NaN;
    }

    /**
     * Returns the spread on the right falling edge. It is the so-called beta value.
     * @return the spread beta
     */
    public final float getBeta() {
        if (membershipFunction != null) {
            return membershipFunction.getBeta();
        }
        return Float.NaN;
    }

    /**
     * Inverts this fuzzy LR interval.
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     */
    public final void invert() throws PropertyVetoException {
        if (membershipFunction != null) {
            membershipFunction.invert();
        }
    }

    /**
     * Checks whether the fuzzy interval of type LR is negative. A fuzzy LR interval is negative
     * if degree of membership is only greater than zero when x is lower than zero.
     * @return <code>true</code> if fuzzy LR interval is negative, <code>false</code> otherwise
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
     * Checks whether the fuzzy interval of type LR is positive. A fuzzy LR interval is positive
     * if degree of membership is only greater than zero when x is also greater than zero.
     * @return <code>true</code> if fuzzy LR interval is positive, <code>false</code> otherwise
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
     * Negates this fuzzy LR interval.
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
                    FuzzyLRInterval oldFuzzyLRInterval = (FuzzyLRInterval) this.clone();
                    oldFuzzyLRInterval.getMembershipFunction().setDegreeOfMembership(p.getX(), p.getDegreeOfMembership());

                    propertyChangeSupport.firePropertyChange(POSITIVE_PROPERTY,
                            oldFuzzyLRInterval.isPositive(), isPositive());

                    propertyChangeSupport.firePropertyChange(NEGATIVE_PROPERTY,
                            oldFuzzyLRInterval.isNegative(), isNegative());

                    propertyChangeSupport.firePropertyChange(ALPHA_PROPERTY,
                            oldFuzzyLRInterval.getAlpha(), getAlpha());

                    propertyChangeSupport.firePropertyChange(BETA_PROPERTY,
                            oldFuzzyLRInterval.getBeta(), getBeta());

                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FuzzyNumber.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CloneNotSupportedException ex) {
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
                if (!fs.isValidFuzzyLRInterval()) {
                    throw new PropertyVetoException(FuzzyResourceManager.getString(
                            this, "EXCEPTION_INVALID_FUZZY_LR_INTERVAL"), evt);
                }
            }
        }
    }
}