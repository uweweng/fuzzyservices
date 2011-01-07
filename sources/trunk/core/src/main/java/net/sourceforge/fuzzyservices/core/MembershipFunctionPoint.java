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
package net.sourceforge.fuzzyservices.core;

import net.sourceforge.fuzzyservices.utils.FuzzyManager;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;
import java.io.Serializable;

/**
 * The class <code>MembershipFunctionPoint</code> is only for internal use
 * An instance assigns a degree of membership to a x value.
 * Due to optimal performance there are no constraints on this value pairs.
 *
 * @see MembershipFunction
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class MembershipFunctionPoint implements Cloneable, Serializable {

    /**
     * Default serial version UID.
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
        x = Float.NaN;
        degreeOfMembership = 0.0f;
    }

    /**
     * Constructs a membership function point which is initialized with <code>x</code> as x value
     * and <code>aDegreeOfMembership</code> as degree of membership of this x value.
     * @param xValue the x value
     * @param aDegreeOfMembership the degree of membership to the x value
     */
    public MembershipFunctionPoint(final float xValue, final float aDegreeOfMembership) {
        if (x == x) { // x is not Float.NaN
            // Reduce number of delimiters

            this.x = FuzzyManager.round(xValue);
            setDegreeOfMembership(aDegreeOfMembership);
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_NOT_A_NUMBER"));
        }
    }

    /**
     * Returns the degree of membership of this point.
     * @return the <code>degreeOfMembership</code> property
     * @see #setDegreeOfMembership
     */
    public final float getDegreeOfMembership() {
        return degreeOfMembership;
    }

    /**
     * Returns the x value of this point.
     * @return the <code>x</code> property
     */
    public final float getX() {
        return x;
    }

    /**
     * Sets the degree of membership of this point.
     * @param aDegreeOfMembership The new value for the property between
     * 0.0 <= aDegreeOfMembership <= 1.0
     * @return The old degree of membership
     * @see #getDegreeOfMembership
     */
    public final float setDegreeOfMembership(final float aDegreeOfMembership) {
        if ((aDegreeOfMembership >= 0.0f) && (aDegreeOfMembership <= 1.0f)) {
            float retDoM = this.degreeOfMembership;
            this.degreeOfMembership = FuzzyManager.round(aDegreeOfMembership);

            return retDoM;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this,
                    "EXCEPTION_INVALID_DEGREE_OF_MEMBERSHIP",
                    new Object[]{
                        Float.toString(aDegreeOfMembership)
                    }));
        }
    }

    @Override
    public Object clone() {
        try {
            MembershipFunctionPoint newObj = (MembershipFunctionPoint) super.clone();
            newObj.x = this.x;
            newObj.degreeOfMembership = this.degreeOfMembership;
            return newObj;
        } catch (java.lang.CloneNotSupportedException e) {
            // never happens
            throw new InternalError(e.toString());
        }
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj != null) && (obj instanceof MembershipFunctionPoint)) {
            return (x == ((MembershipFunctionPoint) obj).x);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = (37 * hash) + Float.floatToIntBits(this.x);
        return hash;
    }

    @Override
    public String toString() {
        return FuzzyResourceManager.getString(this,
                "MEMBERSHIP_FUNCTION_POINT",
                new Object[]{
                    Float.toString(x), Float.toString(degreeOfMembership)
                });
    }
}
