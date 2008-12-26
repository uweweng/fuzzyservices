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

import java.io.Serializable;
import java.util.ListIterator;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

/**
 * A fuzzy number is a convex, normalized fuzzy set whose membership function
 * is at least segmentally continuous and has the functional value f(x) = 1
 * at precisely one element.
 * This class offers the functionality of a fuzzy number. Comparable with
 * traditional numbers arithmetic operations on fuzzy numbers are possible.
 * In this case, the principle of advanced operations is used.
 * Advanced operations means a separated examination of both edges.
 *
 * @see FuzzySet
 * @see FuzzyManager
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class FuzzyNumber extends MembershipFunction implements Cloneable, Serializable {

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor is private
     * because an undefined membership function can not be a valid fuzzy number.
     */
    private FuzzyNumber() {
        super();
    }

    /**
     * Creates a fuzzy number which membership function looks like an isosceles triangle.
     * @param x the x value with degree of membership of 1.0
     * @param spread the spread to x <code>x</code> with degree of membership 0.0
     */
    public FuzzyNumber(final float x, final float spread) {
        if (spread > 0.0f) {
            points.add(new MembershipFunctionPoint((x - spread), 0.0f));
            points.add(new MembershipFunctionPoint(x, 1.0f));
            points.add(new MembershipFunctionPoint((x + spread), 0.0f));
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_NUMBER"));
        }
    }

    /**
     * Constructs a fuzzy number with a membership function like a triangle.
     * The parameters <code>alpha</code> and <code>beta</code> have to be positive.
     * @param x the x value with degree of membership of 1.0
     * @param alpha the distance to <code>x</code> on the left side and degree of membership 0.0
     * @param beta the distance to <code>x</code> on the right side and degree of membership 0.0
     */
    public FuzzyNumber(final float x, final float alpha, final float beta) {
        if ((alpha > 0.0f) && (beta > 0.0f)) {
            points.add(new MembershipFunctionPoint((x - alpha), 0.0f));
            points.add(new MembershipFunctionPoint(x, 1.0f));
            points.add(new MembershipFunctionPoint((x + beta), 0.0f));
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_NUMBER"));
        }
    }

    /**
     * Creates a fuzzy number by converting a fuzzy number of type LR.
     * A fuzzy LR number is always a valid fuzzy number.
     * @param fn the fuzzy LR number
     * @exception NullPointerException if <code>fn</code> is <code>null</code>
     */
    public FuzzyNumber(final FuzzyLRNumber fn) throws NullPointerException {
        ListIterator<MembershipFunctionPoint> elements = fn.points.listIterator();

        while (elements.hasNext()) {
            points.add((MembershipFunctionPoint) elements.next().clone());
        }
    }

    /**
     * Creates a fuzzy number by converting a fuzzy set.
     * @param fs the fuzzy set
     * @exception IllegalArgumentException if the fuzzy set does not fulfill the properties of a fuzzy number
     * @exception NullPointerException if <code>fs</code> is <code>null</code>
     */
    public FuzzyNumber(final FuzzySet fs) throws NullPointerException, IllegalArgumentException {
        if (fs.isValidFuzzyNumber()) {
            ListIterator<MembershipFunctionPoint> elements = fs.points.listIterator();

            while (elements.hasNext()) {
                points.add((MembershipFunctionPoint) elements.next().clone());
            }
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_NUMBER"));
        }
    }

    @Override
    public final synchronized void invert() throws ArithmeticException {
        super.invert();
    }

    /**
     * Checks whether the fuzzy number is negative. A fuzzy number is negative
     * if degree of membership is only greater than zero when x is lower than zero.
     * @return <code>true</code> if fuzzy number is negative, <code>false</code> otherwise
     */
    public final synchronized boolean isNegative() {
        MembershipFunctionPoint entry;
        ListIterator<MembershipFunctionPoint> elements = points.listIterator();

        while (elements.hasNext()) {
            entry = elements.next();

            if (entry.getX() >= 0.0f) {
                if (entry.getDegreeOfMembership() > 0.0f) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks whether the fuzzy number is positive. A fuzzy number is positive
     * if degree of membership is only greater than zero when x is also greater than zero.
     * @return <code>true</code> if fuzzy number is positive, <code>false</code> otherwise
     */
    public final synchronized boolean isPositive() {
        MembershipFunctionPoint entry;
        ListIterator<MembershipFunctionPoint> elements = points.listIterator();

        while (elements.hasNext()) {
            entry = elements.next();

            if (entry.getX() <= 0.0f) {
                if (entry.getDegreeOfMembership() > 0.0f) {
                    return false;
                }
            } else {
                return true;
            }
        }

        return true;
    }

    @Override
    public final synchronized boolean isValidFuzzyLRNumber() {
        FuzzyNumber fn = (FuzzyNumber) this.clone();
        fn.reduce();

        return ((fn.size() == 3) ? true : false);
    }

    @Override
    public final synchronized void negate() {
        super.negate();
    }

    @Override
    public final synchronized float remove(final float x) throws IllegalArgumentException {
        // Converting fuzzy number to fuzzy set, deleting point, and checking for fuzzy number
        FuzzySet fs = new FuzzySet(this);
        float retfloat = fs.remove(x);

        if (fs.isValidFuzzyNumber()) {
            this.points = fs.points;

            return retfloat;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_NUMBER"));
        }
    }

    @Override
    public final synchronized float set(final float x, final float dom) throws IllegalArgumentException {
        // Converting fuzzy number to fuzzy set, deleting point, and checking for fuzzy number
        FuzzySet fs = new FuzzySet(this);
        float retfloat = fs.set(x, dom);

        if (fs.isValidFuzzyNumber()) {
            this.points = fs.points;

            return retfloat;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_NUMBER"));
        }
    }

    /**
     * Returns a textual representation of the fuzzy number
     * @param withPoints Decides whether the membership function points are part of the representation
     * @return a string representation of the fuzzy number
     */
    public final String toString(final boolean withPoints) {
        if (withPoints) {
            return super.toString();
        } else {
            return FuzzyResourceManager.getString(this, "FUZZY_NUMBER_WITHOUT_POINTS", new Object[]{Integer.toString(points.size())});
        }
    }
}
