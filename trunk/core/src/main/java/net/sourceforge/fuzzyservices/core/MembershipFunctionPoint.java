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
package net.sourceforge.fuzzyservices.core;

import java.io.Serializable;

/**
 * The class <code>MembershipFunctionPoint</code> is only for internal use
 * An instance assigns a degree of membership to a x value.
 * Due to optimal performance there are no constraints on this value pairs.
 *
 * @see MembershipFunction
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class MembershipFunctionPoint implements Cloneable, Serializable {

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /** The x value. */
    private float x;

    /** The degree of membership. */
    private float degreeOfMembership;

    /**
     * Constructs a membership function point which is initialized with <code>x=0.0</code>
     * and <code>degreeOfMembership=0.0</code> as degree of membership of this x value.
     * This constructor is primarly for persistence purpose.
     */
    public MembershipFunctionPoint() {
        x=0.0f;
        degreeOfMembership=0.0f;
    }

    /**
     * Constructs a membership function point which is initialized with <code>x</code> as x value
     * and <code>aDegreeOfMembership</code> as degree of membership of this x value.
     * @param xValue the x value
     * @param aDegreeOfMembership the degree of membership to the x value
     * @exception IllegalArgumentException if <code>x</code> is <code>Float.NaN</code>, or not 0.0 <= aDegreeOfMembership <= 1.0,
     */
    public MembershipFunctionPoint(final float xValue, final float aDegreeOfMembership) throws IllegalArgumentException {
        if (x == x) { // x is not Float.NaN
            // Reduce number of delimiters
            this.x = FuzzyManager.round(xValue);
            setDegreeOfMembership(aDegreeOfMembership);

        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_NOT_A_NUMBER"));
    }

    /**
     * Returns the degree of membership of this point.
     * @return the <code>degreeOfMembership</code> property
     * @see #setDegreeOfMembership
     */
    public float getDegreeOfMembership() {
        return degreeOfMembership;
    }

    /**
     * Returns the x value of this point.
     * @return the <code>x</code> property
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the degree of membership of this point.
     * @param aDegreeOfMembership The new value for the property.
     * @exception IllegalArgumentException if not 0.0 <= aDegreeOfMembership <= 1.0
     * @return The old degree of membership
     * @see #getDegreeOfMembership
     */
    public float setDegreeOfMembership(float aDegreeOfMembership) throws IllegalArgumentException {
        if ((aDegreeOfMembership >= 0.0f) && (aDegreeOfMembership <= 1.0f)) {

            float retDoM = this.degreeOfMembership;
            this.degreeOfMembership = FuzzyManager.round(aDegreeOfMembership);
            return retDoM;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_DEGREE_OF_MEMBERSHIP",
                    new Object[] { Float.toString(aDegreeOfMembership) }));
    }

    /**
     * Creates and returns a copy of this membership function point.
     * @return a copy of this point
     */
    public Object clone() {
        try {
            MembershipFunctionPoint newObj = (MembershipFunctionPoint) super.clone();

            return newObj;
        } catch (java.lang.CloneNotSupportedException e) {
            // kann nicht auftreten
            throw new InternalError(e.toString());
        }
    }

    /**
     * Indicates whether some other object is "equal to" this membership function point
     * @param obj the reference object with which to compare
     * @return <code>true</code> if this membership function point is the same as the <code>obj</code> argument,
     * <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof MembershipFunctionPoint)) {
            return (x == ((MembershipFunctionPoint) obj).x);
        }

        return false;
    }

    /**
     * Returns a textual representation of the membership function point
     * @return a string representation of the point
     */
    public String toString() {
        return new String("(" + x + ", " + degreeOfMembership + ")");
    }
}
