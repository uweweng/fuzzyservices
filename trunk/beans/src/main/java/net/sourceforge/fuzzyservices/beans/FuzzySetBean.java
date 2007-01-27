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

import net.sourceforge.fuzzyservices.core.FuzzySet;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * This class represents a fuzzy set according to JavaBeans conventions.
 *
 * @see com.jfuzzy.core.FuzzySet
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class FuzzySetBean implements Serializable {

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
    private FuzzySet fuzzySet = new FuzzySet();

    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);

    /** Default <code>FuzzySetBean</code> constructor. */
    public FuzzySetBean() {
        // Do nothing
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

    /** Concentrates this fuzzy set. */
    public void concentrate() {
        fuzzySet.concentrate();

        FuzzySetBean fs = FuzzyBeanUtils.convert(fuzzySet);
        setMembershipFunction(fs.getMembershipFunction());
    }

    /** Dilates this fuzzy set. */
    public void dilate() {
        fuzzySet.dilate();

        FuzzySetBean fs = FuzzyBeanUtils.convert(fuzzySet);
        setMembershipFunction(fs.getMembershipFunction());
    }

    /**
     * Returns the height of this fuzzy set.
     * @return the current height
     */
    public float getHeight() {
        return fuzzySet.getHeight();
    }

    /**
     * Perform the isConvex method.
     * @return boolean
     */
    public boolean isConvex() {
        return fuzzySet.isConvex();
    }

    /**
     * Perform the isNormalized method.
     * @return boolean
     */
    public boolean isNormalized() {
        return fuzzySet.isNormalized();
    }

    /**
     * Perform the isValidFuzzyInterval method.
     * @return boolean
     */
    public boolean isValidFuzzyInterval() {
        return fuzzySet.isValidFuzzyInterval();
    }

    /**
     * Perform the isValidFuzzyLRInterval method.
     * @return boolean
     */
    public boolean isValidFuzzyLRInterval() {
        return fuzzySet.isValidFuzzyLRInterval();
    }

    /**
     * Perform the isValidFuzzyLRNumber method.
     * @return boolean
     */
    public boolean isValidFuzzyLRNumber() {
        return fuzzySet.isValidFuzzyLRNumber();
    }

    /**
     * Perform the isValidFuzzyNumber method.
     * @return boolean
     */
    public boolean isValidFuzzyNumber() {
        return fuzzySet.isValidFuzzyNumber();
    }

    /** Normalizes this fuzzy set. */
    public void normalize() {
        fuzzySet.normalize();

        FuzzySetBean fs = FuzzyBeanUtils.convert(fuzzySet);
        setMembershipFunction(fs.getMembershipFunction());
    }

    /** Computes the reciproce fuzzy set. */
    public void reciproce() {
        fuzzySet.reciproce();

        FuzzySetBean fs = FuzzyBeanUtils.convert(fuzzySet);
        setMembershipFunction(fs.getMembershipFunction());
    }

    /**
     * Defines a new membership function for this fuzzy set.
     * @param newMembershipFunction An array with the new points.
     * @see #getMembershipFunction()
     */
    public void setMembershipFunction(
            MembershipFunctionPointBean[] newMembershipFunction) {
        MembershipFunctionPointBean[] oldValue = this.membershipFunction;
        this.membershipFunction = newMembershipFunction;
        // Update internal representation
        fuzzySet = FuzzyBeanUtils.createFuzzySet(this.membershipFunction);
        propertyChangeSupport.firePropertyChange(MEMBERSHIP_FUNCTION_PROPERTY,
                oldValue, newMembershipFunction);
    }

    /**
     * Replaces a point within the membership function of this fuzzy set.
     * @param index The index value into the property array.
     * @param newMembershipFunction A new point within the membership function
     * @exception ArrayIndexOutOfBoundsException if <code>index</code> is an
     * invalid position
     * @see #getMembershipFunction()
     */
    public void setMembershipFunction(int index,
            MembershipFunctionPointBean newMembershipFunction)
            throws ArrayIndexOutOfBoundsException {
        MembershipFunctionPointBean oldValue = this.membershipFunction[index];
        this.membershipFunction[index] = newMembershipFunction;

        if ((oldValue != null) && !oldValue.equals(newMembershipFunction)) {
            // Update internal representation
            fuzzySet = FuzzyBeanUtils.createFuzzySet(this.membershipFunction);
            propertyChangeSupport.firePropertyChange(
                    MEMBERSHIP_FUNCTION_PROPERTY, null,
                    this.membershipFunction);
        }

        ;
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


