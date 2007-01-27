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

import net.sourceforge.fuzzyservices.core.FuzzyLRNumber;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * This class represents a fuzzy number of type LR according to JavaBeans
 * conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.FuzzyLRNumber
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class FuzzyLRNumberBean implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    //
    // Bound property names
    //

    /** Bound property name for <code>membershipFunction</code>. */
    public static final String MEMBERSHIP_FUNCTION_PROPERTY
            = "membershipFunction";

    /** The underlying membership function is described by an array of points.*/
    private MembershipFunctionPointBean[] membershipFunction = null;

    /** Reusing the core implementation as internal representation. */
    private FuzzyLRNumber fuzzyLRNumber = null;

    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);

    /**
     * Default <code>FuzzyNumberBean</code> constructor. This constructor
     * creates a valid default fuzzy number of type LR with the coordinates
     * (0.0, 0.0), (1.0, 1.0) and (2.0, 0.0). Hence, this number has got an
     * alpha = beta = 1.0.
     */
    public FuzzyLRNumberBean() {
        // Create default fuzzy LR number
        fuzzyLRNumber = new FuzzyLRNumber(1.0f, 1.0f);
        membershipFunction = FuzzyBeanUtils.toMembershipFunction(fuzzyLRNumber);
    }

    /**
     * Returns the membership function consisting of many points.
     * @return the <code>membershipFunction</code> property
     * @see #setMembershipFunction(MembershipFunctionPointBean[])
     */
    public MembershipFunctionPointBean[] getMembershipFunction() {
        return membershipFunction;
    }

    /**
     * Gets the coordinates located at the specified <code>index</code> of this
     * array with points representing the membership function.
     * @param     index the position of the point to be returned.
     * @return    the point located at the specified index.
     * @exception ArrayIndexOutOfBoundsException if <code>index</code> is an
     * invalid position
     * @see #setMembershipFunction(MembershipFunctionPointBean[])
     */
    public MembershipFunctionPointBean getMembershipFunction(int index)
    throws ArrayIndexOutOfBoundsException {
        return getMembershipFunction()[index];
    }

    /**
     * Returns the alpha value.
     * @return the alpha value
     */
    public float getAlpha() {
        return fuzzyLRNumber.getAlpha();
    }

    /**
     * Returns the beta value.
     * @return the beta value
     */
    public float getBeta() {
        return fuzzyLRNumber.getBeta();
    }

    /** Inverts this fuzzy LR number. */
    public void invert() {
        fuzzyLRNumber.invert();

        FuzzyLRNumberBean fn = FuzzyBeanUtils.convert(fuzzyLRNumber);
        setMembershipFunction(fn.getMembershipFunction());
    }

    /**
     * Perform the isNegative method.
     * @return boolean
     */
    public boolean isNegative() {
        return fuzzyLRNumber.isNegative();
    }

    /**
     * Perform the isPositive method.
     * @return boolean
     */
    public boolean isPositive() {
        return fuzzyLRNumber.isPositive();
    }

    /** Negates this fuzzy LR number. */
    public void negate() {
        fuzzyLRNumber.negate();

        FuzzyLRNumberBean fn = FuzzyBeanUtils.convert(fuzzyLRNumber);
        setMembershipFunction(fn.getMembershipFunction());
    }

    /**
     * Defines a new membership function for this fuzzy LR number.
     * @param newMembershipFunction An array with the new points.
     * @exception IllegalArgumentException if new membership function does not
     * fulfill the requirements of a fuzzy LR number
     * @see #getMembershipFunction()
     */
    public void setMembershipFunction(
            MembershipFunctionPointBean[] newMembershipFunction)
            throws IllegalArgumentException {
        MembershipFunctionPointBean[] oldValue = this.membershipFunction;
        this.membershipFunction = newMembershipFunction;
        // Update internal representation
        fuzzyLRNumber = FuzzyBeanUtils.createFuzzyLRNumber(
                this.membershipFunction);
        propertyChangeSupport.firePropertyChange(MEMBERSHIP_FUNCTION_PROPERTY,
                oldValue, newMembershipFunction);
    }

    /**
     * Replaces a point within the membership function of this fuzzy LR number.
     * @param index The index value into the property array.
     * @param newMembershipFunction A new point within the membership function
     * @exception ArrayIndexOutOfBoundsException if <code>index</code> is an
     * invalid position
     * @exception IllegalArgumentException if new membership function does not
     * fulfill the requirements of a fuzzy LR number
     * @see #getMembershipFunction()
     */
    public void setMembershipFunction(int index,
            MembershipFunctionPointBean newMembershipFunction)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        MembershipFunctionPointBean oldValue = this.membershipFunction[index];
        this.membershipFunction[index] = newMembershipFunction;

        if ((oldValue != null) && !oldValue.equals(newMembershipFunction)) {
            // Update internal representation
            fuzzyLRNumber = FuzzyBeanUtils.createFuzzyLRNumber(
                    this.membershipFunction);
            propertyChangeSupport.firePropertyChange(MEMBERSHIP_FUNCTION_PROPERTY,
                    null, this.membershipFunction);
        };
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


