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

import net.sourceforge.fuzzyservices.utils.FuzzyManager;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;
import java.io.Serializable;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This class represents a point on a membership function according to
 * JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.MembershipFunctionPoint
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
    private float x = FuzzyManager.round(0);
    /** The degree of membership. */
    private float degreeOfMembership = FuzzyManager.round(0);

    /**
     * Default <code>MembershipFunctionPoint</code> constructor. Both x
     * value and degree of membership are 0.0.
     */
    public MembershipFunctionPoint() {
        // Do nothing
    }

    /**
     * Constructs a <code>MembershipFunctionPoint</code> which is
     * initialized with <code>x</code> as x value and
     * <code>newDegreeOfMembership</code> as degree of membership of this x
     * value.
     * @param newX the x value
     * @param newDegreeOfMembership the degree of membership to the x value
     */
    public MembershipFunctionPoint(final float newX, final float newDegreeOfMembership) {
        setX(newX);
        setDegreeOfMembership(newDegreeOfMembership);
    }

    /**
     * Returns the x value of this point.
     * @return the <code>x</code> property
     * @see #setX
     */
    public final float getX() {
        return x;
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
     * Sets the degree of membership of this point.
     * @param aDegreeOfMembership The new value for the property.
     * @see #getDegreeOfMembership
     */
    public final void setDegreeOfMembership(final float aDegreeOfMembership) {
        if ((aDegreeOfMembership >= 0.0f) && (aDegreeOfMembership <= 1.0f)) {
            this.degreeOfMembership = FuzzyManager.round(aDegreeOfMembership);
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this,
                    "EXCEPTION_INVALID_DEGREE_OF_MEMBERSHIP",
                    new Object[]{
                        Float.toString(aDegreeOfMembership)
                    }));
        }
    }

    /**
     * Sets the x value of this point.
     * @param xValue The new value for the property.
     * @see #getX
     */
    public final void setX(final float xValue) {
        if (x == x) { // x is not Float.NaN
            // Reduce number of delimiters

            this.x = FuzzyManager.round(xValue);
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_NOT_A_NUMBER"));
        }
    }
    /*
    @Override
    public Object clone() {
    try {
    MembershipFunctionPoint newObj = (MembershipFunctionPoint) super.clone();
    
    return newObj;
    } catch (java.lang.CloneNotSupportedException e) {
    // kann nicht auftreten
    throw new InternalError(e.toString());
    }
    }
    
    @Override
    public final boolean equals(Object obj) {
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
    return "(" + x + ", " + degreeOfMembership + ")";
    }
     */

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
        MembershipFunctionPoint point = (MembershipFunctionPoint) obj;
        return new EqualsBuilder().append(this.x, point.x).append(this.degreeOfMembership, point.degreeOfMembership).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 21).append(this.x).append(this.degreeOfMembership).toHashCode();
    }

    @Override
    public String toString() {
        return FuzzyResourceManager.getString(this,
                "MEMBERSHIP_FUNCTION_POINT",
                new Object[]{
                    Float.toString(x),
                    Float.toString(degreeOfMembership)
                });
    }
}
