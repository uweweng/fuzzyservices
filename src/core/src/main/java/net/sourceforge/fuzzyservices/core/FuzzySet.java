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
 * In general, a fuzzy set is the basement for fuzzy logic. It is also called "Fuzzy set theory".
 * In this case, the membership function f(x) is defined by a set of points. Every point marks a relationship
 * between a value x and its degree of membership. All points clamp a traverse.
 * If the degree of membership of a value is not defined by a point exactly
 * it will be calculated by interpolation.
 * This class offers methods for manipulating the membership function of a fuzzy set.
 * For calculation the pointed membership function is iterated in a certain increment
 * defined in fuzzy manager of this system.
 *
 * @see net.sourceforge.fuzzyservices.utils.FuzzyManager
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class FuzzySet extends MembershipFunction implements Cloneable, Serializable {

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor with membership function <tt>f(x)=0.0</tt>,
     * at beginning of the lifecycle of this object.
     */
    public FuzzySet() {
    // Default constructor
    }

    /**
     * Create a fuzzy set with a peak at <code>x</code>.
     * @param x the membership function has got at x a value of 1.0
     */
    public FuzzySet(final float x) {
        points.add(new MembershipFunctionPoint(x, 1.0f));
    }

    /**
     * Creates a fuzzy set which membership function looks like an isosceles triangle.
     * @param x the x value with degree of membership of 1.0
     * @param spread the spread to x <code>x</code> with degree of membership 0.0
     * @exception IllegalArgumentException if <code>spread</code> is not positive.
     */
    public FuzzySet(final float x, final float spread) throws IllegalArgumentException {
        if (spread > 0.0f) {
            points.add(new MembershipFunctionPoint((x - spread), 0.0f));
            points.add(new MembershipFunctionPoint(x, 1.0f));
            points.add(new MembershipFunctionPoint((x + spread), 0.0f));
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_SET"));
        }
    }

    /**
     * Constructs a fuzzy set with a membership function like a triangle.
     * @param x the x value with degree of membership of 1.0
     * @param alpha the distance to <code>x</code> on the left side and degree of membership 0.0
     * @param beta the distance to <code>x</code> on the right side and degree of membership 0.0
     * @exception IllegalArgumentException if <code>alpha</code> or <code>beta</code> is not positive
     */
    public FuzzySet(final float x, final float alpha, final float beta) throws IllegalArgumentException {
        if ((alpha > 0.0f) && (beta > 0.0f)) {
            points.add(new MembershipFunctionPoint((x - alpha), 0.0f));
            points.add(new MembershipFunctionPoint(x, 1.0f));
            points.add(new MembershipFunctionPoint((x + beta), 0.0f));
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_SET"));
        }
    }

    /**
     * Creates a fuzzy set with a membership function like a trapezium. The degree of membership
     * between <code>plateau1</code> and <code>plateau2</code> is 1.0.
     * @param plateau1 coming from left at this point the membership is 1.0 the first time.
     * @param plateau2 coming from right at this point the membership is 1.0 the first time.
     * @param alpha the spread of the left trailing edge
     * @param beta the spread of the right trailing edge
     * @exception IllegalArgumentException if is not fulfill <code>plateau1 - alpha</code> < <code>plateau1</code> <=
     * <code>plateau2</code> < <code>plateau2 + beta</code>
     */
    public FuzzySet(final float plateau1, final float plateau2,
            final float alpha, final float beta) throws IllegalArgumentException {
        if (((plateau1 - alpha) < plateau1) && (plateau1 <= plateau2) &&
                (plateau2 < (plateau2 + beta))) {
            points.add(new MembershipFunctionPoint((plateau1 - alpha),
                    0.0f));
            points.add(new MembershipFunctionPoint(plateau1, 1.0f));

            if (plateau2 != plateau1) {
                // sonst Triangle
                points.add(new MembershipFunctionPoint(plateau2, 1.0f));
            }

            points.add(new MembershipFunctionPoint((plateau2 + beta),
                    0.0f));
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this, "EXCEPTION_INVALID_FUZZY_SET"));
        }
    }

    /**
     * Creates a fuzzy set by converting a fuzzy interval.
     * @param fi the fuzzy interval; if <code>null</code> the membership function is also f(x) = 0.0
     */
    public FuzzySet(final FuzzyInterval fi) {
        if (fi != null) {
            ListIterator<MembershipFunctionPoint> elements = fi.points.listIterator();

            while (elements.hasNext()) {
                points.add((MembershipFunctionPoint) elements.next().clone());
            }
        }
    }

    /**
     * Creates a fuzzy set by converting a fuzzy interval of type LR.
     * @param fi the fuzzy LR interval; if <code>null</code> the membership function is also f(x) = 0.0
     */
    public FuzzySet(final FuzzyLRInterval fi) {
        if (fi != null) {
            ListIterator<MembershipFunctionPoint> elements = fi.points.listIterator();

            while (elements.hasNext()) {
                points.add((MembershipFunctionPoint) elements.next().clone());
            }
        }
    }

    /**
     * Creates a fuzzy set by converting a fuzzy number of type LR.
     * @param fn the fuzzy LR number; if <code>null</code> the membership function is also f(x) = 0.0
     */
    public FuzzySet(final FuzzyLRNumber fn) {
        if (fn != null) {
            ListIterator<MembershipFunctionPoint> elements = fn.points.listIterator();

            while (elements.hasNext()) {
                points.add((MembershipFunctionPoint) elements.next().clone());
            }
        }
    }

    /**
     * Creates a fuzzy set by converting a fuzzy number.
     * @param fn the fuzzy number; if <code>null</code> the membership function is also f(x) = 0.0
     */
    public FuzzySet(final FuzzyNumber fn) {
        if (fn != null) {
            ListIterator<MembershipFunctionPoint> elements = fn.points.listIterator();

            while (elements.hasNext()) {
                points.add((MembershipFunctionPoint) elements.next().clone());
            }
        }
    }

    @Override
    public final synchronized void clear() {
        super.clear();
    }

    /**
     * Combines two fuzzy sets to a new fuzzy set using an operator.
     * @param fs1 The first operand
     * @param fs2 The second operand
     * @param op The operator for combining the operands
     * @return the result of this operation. It is a new fuzzy set.
     * @exception NullPointerException if <code>op</code> is <code>null</code>
     */
    public static FuzzySet combine(final FuzzySet fs1, final FuzzySet fs2,
            final AbstractOperator op) throws NullPointerException {
        return op.combine(fs1, fs2);
    }

    /**
     * Combines the fuzzy set with a second fuzzy set, <code>fs</code>, using an operator.
     * @param fs The second operand
     * @param op The operator for combining the operands
     * @return the result of this operation. It is a new fuzzy set.
     * @exception NullPointerException if <code>op</code> is <code>null</code>
     */
    public synchronized FuzzySet combine(final FuzzySet fs, final AbstractOperator op) throws NullPointerException {
        return op.combine(this, fs);
    }

    /** Concentrates the fuzzy set. */
    public final synchronized void concentrate() {
        if (!points.isEmpty()) {
            // Iterating x axis in granularity steps.
            float granularity = getGranularity(this);
            int numSteps = getNumSteps(this);
            float x = getMinDefinedX();
            FuzzySet tmp_this = new FuzzySet();

            for (int i = 0; i < numSteps; i++) {
                tmp_this.set(x, (float) Math.pow(getDegreeOfMembership(x), 2.0));
                x += granularity;
            }

            // Iterating all existing points.
            MembershipFunctionPoint entry;
            ListIterator<MembershipFunctionPoint> elements = points.listIterator();

            while (elements.hasNext()) {
                entry = elements.next();
                tmp_this.set(entry.getX(),
                        (float) Math.pow(entry.getDegreeOfMembership(), 2.0));
            }

            elements = tmp_this.points.listIterator();

            while (elements.hasNext()) {
                entry = elements.next();
                set(entry.getX(), entry.getDegreeOfMembership());
            }

            // Finally, we try to minimize the number of points.
            reduce();
        }
    }

    /** Dilates the fuzzy set. */
    public final synchronized void dilate() {
        if (!points.isEmpty()) {
            // Iterating x axis in granularity steps.
            float granularity = getGranularity(this);
            int numSteps = getNumSteps(this);
            float x = getMinDefinedX();
            FuzzySet tmp_this = new FuzzySet();

            for (int i = 0; i < numSteps; i++) {
                tmp_this.set(x,
                        (float) Math.pow(getDegreeOfMembership(x),
                        (float) (1.0 / 2.0)));
                x += granularity;
            }

            // Iterating all existing points.
            MembershipFunctionPoint entry;
            ListIterator<MembershipFunctionPoint> elements = points.listIterator();

            while (elements.hasNext()) {
                entry = elements.next();
                tmp_this.set(entry.getX(),
                        (float) Math.pow(entry.getDegreeOfMembership(),
                        (float) (1.0 / 2.0)));
            }

            elements = tmp_this.points.listIterator();

            while (elements.hasNext()) {
                entry = elements.next();
                set(entry.getX(), entry.getDegreeOfMembership());
            }

            // Finally, we try to minimize the number of points.
            reduce();
        }
    }

    /**
     * Calculates the increment when combining this fuzzy set with <code>fs</code>.
     * @param fs the fuzzy set for combining
     * @return the so-called granularity
     * @see #combine
     */
    public final synchronized float getGranularity(final FuzzySet fs) {
        float thisspread = this.getMaxDefinedX() - this.getMinDefinedX();
        float otherspread = fs.getMaxDefinedX() - fs.getMinDefinedX();
        float commonspread = ((thisspread > otherspread) ? thisspread
                : otherspread);

        // Checking, whether the default granularity is ok.
        // On calculation the number of steps take priority over default granularity.
        if ((commonspread / FuzzyManager.getMaxNumStep()) > FuzzyManager.getStepwidth()) {
            return FuzzyManager.round(commonspread / FuzzyManager.getMaxNumStep());
        } else {
            return FuzzyManager.getStepwidth();
        }
    }

    @Override
    public final synchronized float getHeight() {
        return super.getHeight();
    }

    /**
     * Calculates the number of steps when combining this fuzzy set with <code>fs</code>.
     * @param fs the fuzzy set for combining
     * @return the number of steps
     * @see #combine
     */
    public final synchronized int getNumSteps(final FuzzySet fs) {
        float thisspread = this.getMaxDefinedX() - this.getMinDefinedX();
        float otherspread = fs.getMaxDefinedX() - fs.getMinDefinedX();
        float commonspread = ((thisspread > otherspread) ? thisspread
                : otherspread);

        return ((int) (commonspread / this.getGranularity(fs)));
    }

    @Override
    public synchronized boolean isConvex() {
        return super.isConvex();
    }

    @Override
    public synchronized boolean isNormalized() {
        return super.isNormalized();
    }

    @Override
    public synchronized boolean isValidFuzzyInterval() {
        return super.isValidFuzzyInterval();
    }

    @Override
    public synchronized boolean isValidFuzzyLRInterval() {
        return super.isValidFuzzyLRInterval();
    }

    @Override
    public synchronized boolean isValidFuzzyLRNumber() {
        return super.isValidFuzzyLRNumber();
    }

    @Override
    public synchronized boolean isValidFuzzyNumber() {
        return super.isValidFuzzyNumber();
    }

    @Override
    public final synchronized void normalize() {
        super.normalize();
    }

    /** Creates the complementary membership function (<tt>f(x) = 1-f(x)</tt>). */
    public final synchronized void reciproce() {
        MembershipFunctionPoint entry;
        ListIterator<MembershipFunctionPoint> elements = points.listIterator();

        while (elements.hasNext()) {
            entry = elements.next();
            entry.setDegreeOfMembership(1.0f - entry.getDegreeOfMembership());
        }
    }

    @Override
    public final synchronized float remove(final float x) {
        return removeWithoutChecking(x);
    }

    @Override
    public final synchronized float set(final float x, final float dom) throws IllegalArgumentException {
        return super.setWithoutChecking(x, dom);
    }

    /**
     * Returns a textual representation of the fuzzy set
     * @param withPoints Decides whether the membership function points are part of the representation
     * @return a string representation of the fuzzy set
     */
    public String toString(final boolean withPoints) {
        if (withPoints) {
            return super.toString();
        } else {
            return FuzzyResourceManager.getString(this, "FUZZY_SET_WITHOUT_POINTS",
                    new Object[]{Integer.toString(points.size())});
        }
    }
}
