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
import net.sourceforge.fuzzyservices.utils.FuzzyManager;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

/**
 * A fuzzy interval of type LR is a convex, normalized fuzzy set whose
 * membership function is at least segmentally continuous and has a mean
 * interval whose iterator possess the membership function value f(x) = 1.
 * In comparison to fuzzy interval it is assumed a linear trend of two reference
 * functions L(X) and R(x) on the left and right side of this interval.
 * This class offers the functionality of a fuzzy LR interval. Comparable with
 * traditional numbers and intervals arithmetic operations on fuzzy LR intervals
 * are possible. In this case, the principle of advanced operations is used.
 * Advanced operations means a separated examination of both edges.
 *
 * @see FuzzySet
 * @see net.sourceforge.fuzzyservices.utils.FuzzyManager
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class FuzzyLRInterval extends MembershipFunction implements Cloneable, Serializable {
    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor is private
     * because an undefined membership function can not be a valid fuzzy interval of type LR.
     */
    private FuzzyLRInterval() {
        super();
    }

    /**
     * Creates a fuzzy interval of type LR with a membership function like a trapezium.
     * The degree of membership between <code>plateau1</code> and <code>plateau2</code> is 1.0.
     * @param plateau1 coming from left at this point the membership is 1.0 the first time.
     * @param plateau2 coming from right at this point the membership is 1.0 the first time.
     * @param alpha the spread of the left trailing edge
     * @param beta the spread of the right trailing edge
     * @exception IllegalArgumentException if is not fulfill <code>plateau1 - alpha</code> < <code>plateau1</code> <=
     * <code>plateau2</code> < <code>plateau2 + beta</code>
     */
    public FuzzyLRInterval(final float plateau1, final float plateau2,
            final float alpha, final float beta) throws IllegalArgumentException{
        if (((plateau1 - alpha) < plateau1) && (plateau1 < plateau2) &&
                (plateau2 < (plateau2 + beta))) {
            points.add(new MembershipFunctionPoint((plateau1 - alpha), 0.0f));
            points.add(new MembershipFunctionPoint(plateau1, 1.0f));
            points.add(new MembershipFunctionPoint(plateau2, 1.0f));
            points.add(new MembershipFunctionPoint((plateau2 + beta), 0.0f));
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_LR_INTERVAL"));
        }
    }

    /**
     * Creates a fuzzy interval of type LR by converting a fuzzy interval.
     * The fuzzy interval has to fulfill the requirements of a fuzzy LR interval.
     * @param fi the fuzzy interval
     * @exception IllegalArgumentException if <code>fi</code> does not fulfill the properties of a fuzzy LR interval
     * @exception NullPointerException if <code>fi</code> is <code>null</code>
     */
    public FuzzyLRInterval(final FuzzyInterval fi) throws NullPointerException, IllegalArgumentException{
        if (fi.isValidFuzzyLRInterval()) {
            ListIterator<MembershipFunctionPoint> elements = fi.points.listIterator();

            while (elements.hasNext()) {
                points.add((MembershipFunctionPoint) elements.next().clone());
            }

            this.reduce();
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_LR_INTERVAL"));
        }
    }

    /**
     * Creates a fuzzy interval of type LR by converting a fuzzy set.
     * The fuzzy set has to fulfill the requirements of a fuzzy LR interval.
     * @param fs the fuzzy set
     * @exception IllegalArgumentException if <code>fs</code> does not fulfill the properties of a fuzzy LR interval
     * @exception NullPointerException if <code>fs</code> is <code>null</code>
     */
    public FuzzyLRInterval(final FuzzySet fs) throws NullPointerException, IllegalArgumentException{
        if (fs.isValidFuzzyLRInterval()) {
            ListIterator<MembershipFunctionPoint> elements = fs.points.listIterator();

            while (elements.hasNext()) {
                points.add((MembershipFunctionPoint) elements.next().clone());
            }
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_LR_INTERVAL"));
        }
    }

    /**
     * Returns the spread on the left falling edge. It is the so-called alpha value.
     * @return the spread alpha
     */
    public final synchronized float getAlpha() {
        FuzzyLRInterval fi = (FuzzyLRInterval) this.clone();
        fi.reduce();

        float x0;
        float x1;
        x0 = (fi.points.get(0)).getX();
        x1 = (fi.points.get(1)).getX();

        return FuzzyManager.round(x1 - x0);
    }

    /**
     * Returns the spread on the right falling edge. It is the so-called beta value.
     * @return the spread beta
     */
    public final synchronized float getBeta() {
        FuzzyLRInterval fi = (FuzzyLRInterval) this.clone();
        fi.reduce();

        float x0;
        float x1;
        int size = fi.points.size();
        x0 = (fi.points.get(size - 2)).getX();
        x1 = (fi.points.get(size - 1)).getX();

        return FuzzyManager.round(x1 - x0);
    }

    @Override
    public final synchronized void invert() throws ArithmeticException{
        super.invert();
    }

    /**
     * Checks whether the fuzzy interval of type LR is negative. A fuzzy LR interval is negative
     * if degree of membership is only greater than zero when x is lower than zero.
     * @return <code>true</code> if fuzzy LR interval is negative, <code>false</code> otherwise
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
     * Checks whether the fuzzy interval of type LR is positive. A fuzzy LR interval is positive
     * if degree of membership is only greater than zero when x is also greater than zero.
     * @return <code>true</code> if fuzzy LR interval is positive, <code>false</code> otherwise
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
        // Fuzzy-Zahl in eine Fuzzy-Menge konvertieren, Punkt loeschen und auf Fuzzy-Intervall pruefen
        FuzzySet fs = new FuzzySet(this);
        float retfloat = fs.remove(x);

        if (fs.isValidFuzzyLRInterval()) {
            this.points = fs.points;

            return retfloat;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_LR_INTERVAL"));
        }
    }

    @Override
    public final synchronized float set(final float x, final float dom) throws IllegalArgumentException{
        // Fuzzy-LR-Intervall in ein Fuzzy-Intervall konvertieren, Punkt einfuegen und auf Fuzzy-LR-Intervall pruefen.
        FuzzyInterval fi = new FuzzyInterval(this);
        float retfloat = fi.set(x, dom);

        if (fi.isValidFuzzyLRInterval()) {
            this.points = fi.points;

            //                    this.reduce();
            return retfloat;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_LR_INTERVAL"));
        }
    }

    /**
     * Returns a textual representation of the fuzzy LR interval
     * @param withPoints Decides whether the membership function points are part of the representation
     * @return a string representation of the fuzzy LR interval
     */
    public String toString(final boolean withPoints) {
        if (withPoints) {
            return super.toString();
        }
        else {
            return FuzzyResourceManager.getString(this, "FUZZY_LR_INTERVAL_WITHOUT_POINTS", new Object[]{Integer.toString(points.size())});
        }
    }
}
