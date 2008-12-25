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
package net.sourceforge.fuzzyservices.core;

import java.io.Serializable;
import java.util.ListIterator;
import net.sourceforge.fuzzyservices.utils.FuzzyManager;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

/**
 * A fuzzy number of type LR is a convex, normalized fuzzy set whose membership
 * function is at least segmentally continuous and has the functional value
 * f(x) = 1 at precisely one element. In comparison to fuzzy numbers
 * it is assumed a linear trend of two reference functions L(X) and R(x)
 * on the left and right side of this element.
 * This class offers the functionality of a fuzzy LR number. Comparable with
 * traditional numbers arithmetic operations on fuzzy LR numbers are possible.
 * In this case, the principle of advanced operations is used.
 * Advanced operations means a separated examination of both edges.
 *
 * @see FuzzySet
 * @see FuzzyNumber
 * @see FuzzyManager
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class FuzzyLRNumber extends MembershipFunction implements Cloneable, Serializable {
    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor is private
     * because an undefined membership function can not be a valid fuzzy number of type LR.
     */
    private FuzzyLRNumber() {
        super();
    }

    /**
     * Creates a fuzzy number of type LR with identical reference functions L(x) and R(x).
     * @param x the x value with degree of membership of 1.0
     * @param spread the spread to x <code>x</code> with degree of membership 0.0
     * @exception IllegalArgumentException if <code>spread</code> is not positive.
     */
    public FuzzyLRNumber(final float x, final float spread) throws IllegalArgumentException{
        if (spread > 0.0f) {
            points.add(new MembershipFunctionPoint((x - spread), 0.0f));
            points.add(new MembershipFunctionPoint(x, 1.0f));
            points.add(new MembershipFunctionPoint((x + spread), 0.0f));
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_LR_NUMBER"));
        }
    }

    /**
     * Constructs a fuzzy LR number with a membership function like a triangle.
     * @param x the x value with degree of membership of 1.0
     * @param alpha the distance to <code>x</code> on the left side and degree of membership 0.0
     * @param beta the distance to <code>x</code> on the right side and degree of membership 0.0
     * @exception IllegalArgumentException if <code>alpha</code> or <code>beta</code> is not positive
     */
    public FuzzyLRNumber(final float x, final float alpha, final float beta) throws IllegalArgumentException{
        if ((alpha > 0.0f) && (beta > 0.0f)) {
            points.add(new MembershipFunctionPoint((x - alpha), 0.0f));
            points.add(new MembershipFunctionPoint(x, 1.0f));
            points.add(new MembershipFunctionPoint((x + beta), 0.0f));
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_LR_NUMBER"));
        }
    }

    /**
     * Creates a fuzzy number of type LR by converting a fuzzy number.
     * The fuzzy number has to fulfill the requirements of a fuzzy LR number.
     * @param fn the fuzzy number
     * @exception IllegalArgumentException if <code>fn</code> does not fulfill the properties of a fuzzy LR number
     * @exception NullPointerException if <code>fn</code> is <code>null</code>
     */
    public FuzzyLRNumber(final FuzzyNumber fn) throws NullPointerException, IllegalArgumentException{
        if (fn.isValidFuzzyLRNumber()) {
            ListIterator<MembershipFunctionPoint> elements = fn.points.listIterator();

            while (elements.hasNext()) {
                points.add((MembershipFunctionPoint) elements.next().clone());
            }

            this.reduce();
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_LR_NUMBER"));
        }
    }

    /**
     * Creates a fuzzy number of type LR by converting a fuzzy set.
     * The fuzzy set has to fulfill the requirements of a fuzzy LR number.
     * @param fs the fuzzy set
     * @exception IllegalArgumentException if <code>fs</code> does not fulfill the properties of a fuzzy LR number
     * @exception NullPointerException if <code>fs</code> is <code>null</code>
     */
    public FuzzyLRNumber(final FuzzySet fs) throws NullPointerException, IllegalArgumentException{
        if (fs.isValidFuzzyLRNumber()) {
            ListIterator<MembershipFunctionPoint> elements = fs.points.listIterator();

            while (elements.hasNext()) {
                points.add((MembershipFunctionPoint) elements.next().clone());
            }
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_LR_NUMBER"));
        }
    }

    /**
     * Returns the spread on the left falling edge. It is the so-called alpha value.
     * @return the spread alpha
     */
    public final synchronized float getAlpha() {
        FuzzyLRNumber fn = (FuzzyLRNumber) this.clone();
        fn.reduce();

        float x0;
        float x1;
        x0 = (fn.points.get(0)).getX();
        x1 = (fn.points.get(1)).getX();

        return FuzzyManager.round(x1 - x0);
    }

    /**
     * Returns the spread on the right falling edge. It is the so-called beta value.
     * @return the spread beta
     */
    public final synchronized float getBeta() {
        FuzzyLRNumber fn = (FuzzyLRNumber) this.clone();
        fn.reduce();

        float x0;
        float x1;
        int size = fn.points.size();
        x0 = (fn.points.get(size - 2)).getX();
        x1 = (fn.points.get(size - 1)).getX();

        return FuzzyManager.round(x1 - x0);
    }

    @Override
    public final synchronized void invert() throws ArithmeticException{
        super.invert();
    }

    /**
     * Checks whether the fuzzy number of type LR is negative. A fuzzy LR number is negative
     * if degree of membership is only greater than zero when x is lower than zero.
     * @return <code>true</code> if fuzzy LR number is negative, <code>false</code> otherwise
     */
    public synchronized boolean isNegative() {
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
     * Checks whether the fuzzy number of type LR is positive. A fuzzy LR number is positive
     * if degree of membership is only greater than zero when x is also greater than zero.
     * @return <code>true</code> if fuzzy LR number is positive, <code>false</code> otherwise
     */
    public synchronized boolean isPositive() {
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
    public final synchronized void negate() {
        super.negate();
    }

    @Override
    public final synchronized float remove(final float x) throws IllegalArgumentException{
        // Fuzzy-LR-Zahl in eine Fuzzy-Zahl konvertieren, Punkt loeschen und auf Fuzzy-Intervall pruefen.
        FuzzySet fs = new FuzzySet(this);
        float retfloat = fs.remove(x);

        if (fs.isValidFuzzyLRNumber()) {
            this.points = fs.points;

            return retfloat;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_LR_NUMBER"));
        }
    }

    @Override
    public final synchronized float set(final float x, final float dom) throws IllegalArgumentException{
        // Fuzzy-LR-Zahl in eine Fuzzy-Zahl konvertieren, Punkt einfuegen und auf Fuzzy-LR-Zahl pruefen.
        FuzzyNumber fn = new FuzzyNumber(this);
        float retfloat = fn.set(x, dom);

        if (fn.isValidFuzzyLRNumber()) {
            this.points = fn.points;

            //                    this.reduce();
            return retfloat;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_LR_NUMBER"));
        }
    }

    /**
     * Returns a textual representation of the fuzzy LR number
     * @param withPoints Decides whether the membership function points are part of the representation
     * @return a string representation of the fuzzy LR number
     */
    public String toString(final boolean withPoints) {
        if (withPoints) {
            return super.toString();
        }
        else {
            return FuzzyResourceManager.getString(this, "FUZZY_LR_NUMBER_WITHOUT_POINTS", new Object[]{Integer.toString(points.size())});
        }
    }
}

