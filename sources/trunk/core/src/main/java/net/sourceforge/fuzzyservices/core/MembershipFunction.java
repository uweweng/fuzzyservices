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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

/**
 * The membership function f(x) is defined by a set of points. Every point marks a relationship
 * between a value x and its degree of membership. All points clamp a traverse.
 * If the degree of membership of a value is not defined by a point exactly
 * it will be calculated by interpolation.
 * This class offers methods for manipulating the membership function, in general.
 * For calculation the pointed membership function is iterated in a certain increment
 * defined in fuzzy manager of this system.
 *
 * @see FuzzyManager
 * @see MembershipFunctionPoint
 * @see FuzzySet
 * @see FuzzyNumber
 * @see FuzzyLRNumber
 * @see FuzzyInterval
 * @see FuzzyLRInterval
 *
 * @since 1.0
 * @author Uwe Weng
 */
public abstract class MembershipFunction implements Cloneable, Serializable {

    /**
     * Set of points specifying the mathematical membership function.
     */
    protected List<MembershipFunctionPoint> points = new ArrayList<MembershipFunctionPoint>(FuzzyManager.maxNumStep);

    /**
     * Defines at <code>x</code> a new degree of membership.
     * @param x the x coordinate
     * @param dom the new degree of membership at <code>x</code>
     * @return the previous degree of membership if specified, <code>Float.NaN</code> otherwise
     * @exception IllegalArgumentException if <code>x</code> is <code>Float.NaN</code> or not 0.0 <= dom <= 1.0.
     */
    synchronized float setWithoutChecking(final float x, final float dom) throws IllegalArgumentException {
        if (points.isEmpty()) {
            points.add(new MembershipFunctionPoint(x, dom));

            return Float.NaN;
        }

        if (x > (points.get(points.size() - 1)).getX()) {
            // This case appears very often when combining membership functions
            points.add(new MembershipFunctionPoint(x, dom));

            return Float.NaN;
        }

        if (x < (points.get(0)).getX()) {
            // This case appears very often when combining membership functions
            points.add(0, new MembershipFunctionPoint(x, dom));

            return Float.NaN;
        }

        // Binary search
        int minPos = 0;
        int maxPos = points.size() - 1;
        int i;
        MembershipFunctionPoint fse;

        while (maxPos != minPos) {
            i = (maxPos + minPos) / 2;
            fse = points.get(i);

            if (x == fse.getX()) {
                float retvalue = fse.getDegreeOfMembership();
                fse.setDegreeOfMembership(dom);

                return retvalue;
            } else if (x < fse.getX()) {
                maxPos = i;
            } else {
                minPos = i + 1;
            }
        }

        /*
         * // funktioniert bei size = 2 nicht !!! while ((maxPos -
         * minPos) > 1) { i = (maxPos + minPos) / 2; if (x <=
         * ((MembershipFunctionPoint)points.get(i)).getX()) maxPos =
         * i; else minPos = i; }
         */
        fse = points.get(maxPos);

        if (x == fse.getX()) {
            float retvalue = fse.getDegreeOfMembership();
            fse.setDegreeOfMembership(dom);

            return retvalue;
        }

        // x value is not defined yet.
        points.add(maxPos, new MembershipFunctionPoint(x, dom));

        return Float.NaN;
    }

    /**
     * Sets the degree of membership at <code>x</code> to 0.0.
     * @param x the x coodinate
     * @return the previous degree of membership if specified, <code>Float.NaN</code> otherwise
     */
    synchronized float removeWithoutChecking(final float x) {
        // The degree of membership is not interesting, because MembershipFunctionPoint.equals()
        // compares only the x value.
        int pos = points.indexOf(new MembershipFunctionPoint(x, 0.0f));

        if (pos >= 0) {
            float retDoM = (points.get(pos)).getDegreeOfMembership();
            points.remove(pos);

            return retDoM;
        } else {
            return Float.NaN;
        }
    }

    /**
     * Defines at <code>x</code> a new degree of membership.
     * @param x the x coordinate
     * @param dom the new degree of membership at <code>x</code>
     * @return the previous degree of membership if specified, <code>Float.NaN</code> otherwise
     * @exception IllegalArgumentException if <code>x</code> is <code>Float.NaN</code> or not 0.0 <= x <= 1.0.
     */
    public abstract float set(final float x, final float dom) throws IllegalArgumentException;

    /** Reset the membership function to <tt>f(x) = 0.0</tt>. */
    protected synchronized void clear() {
        points.clear();
    }

    /**
     * Sets the degree of membership at <code>x</code> to 0.0.
     *
     * @param x
     *            the x coodinate
     * @return the previous degree of membership if specified,
     *         <code>Float.NaN</code> otherwise
     */
    public abstract float remove(final float x);

    /**
     * Returns the height of the membership function. It is the maximum defined
     * degree of membership.
     *
     * @return the maximum degree of membership as height
     */
    protected synchronized float getHeight() {
        float height = 0.0f;
        float dom;
        ListIterator elements = points.listIterator();

        while (elements.hasNext()) {
            dom = ((MembershipFunctionPoint) elements.next()).getDegreeOfMembership();

            if (dom > height) {
                height = dom;

                if (height == 1.0f) {
                    return 1.0f;
                }
            }
        }

        return height;
    }

    /**
     * Checks whether the membership function is normalized. A membership function is normalized
     * if its height is 1.0.
     * @return <code>true</code> if membership function is normalized <code>false</code>, otherwise
     * @see #getHeight
     */
    protected synchronized boolean isNormalized() {
        return (getHeight() == 1.0f);
    }

    /**
     * Normalizes the membership function.
     * Afterwards the degree of membership is alwalys in <tt>[0,1]</tt>.
     * @see #isNormalized
     */
    protected synchronized void normalize() {
        float height = getHeight();

        if (height != 0.0f) {
            MembershipFunctionPoint entry;
            ListIterator elements = points.listIterator();

            while (elements.hasNext()) {
                entry = (MembershipFunctionPoint) elements.next();
                entry.setDegreeOfMembership(entry.getDegreeOfMembership() / height);
            }
        }
    }

    /**
     * Checks whether the membership function is convex. A membership function is convex
     * if there is only one change of sign from + to -.
     * @return <code>true</code> if membership function is convex <code>false</code>, otherwise
     */
    protected synchronized boolean isConvex() {
        if ((points.size() > 2) &&
                ((points.get(0)).getDegreeOfMembership() == 0.0f) &&
                ((points.get(points.size() - 1)).getDegreeOfMembership() == 0.0f)) {
            int vzw = 0; // Anzahl der stattgefundenen Vorzeichenwechsel
            MembershipFunctionPoint entryLeft;
            MembershipFunctionPoint entryRight;
            ListIterator elements = points.listIterator();
            entryLeft = (MembershipFunctionPoint) elements.next();
            entryRight = (MembershipFunctionPoint) elements.next();

            float slope;
            float lastSlope = getSlope(entryLeft.getX(), entryRight.getX());
            entryLeft = entryRight;

            while (elements.hasNext() && (vzw < 2)) {
                entryRight = (MembershipFunctionPoint) elements.next();
                slope = getSlope(entryLeft.getX(), entryRight.getX());

                if (((lastSlope > 0.0f) && (slope < 0.0f)) ||
                        ((lastSlope < 0.0f) && (slope > 0.0f))) {
                    vzw++;
                }

                entryLeft = entryRight;

                if (slope != 0.0f) {
                    lastSlope = slope;
                }
            }

            return ((vzw == 1) ? true : false);
        }

        return false;
    }

    /**
     * Checks whether the membership function of this fuzzy set fulfills the requirements of a fuzzy number.
     * The membership function has to be convex and normalized. In addition, the maximum
     * is reached at one point.
     * @return <code>true</code> if the fuzzy set would be a fuzzy number <code>false</code>, otherwise
     * @see #isNormalized
     * @see #isConvex
     */
    protected synchronized boolean isValidFuzzyNumber() {
        if (isNormalized() && isConvex()) {
            // Additionally, we are checking whether the maximum is defined in one place
            ListIterator elements = points.listIterator();
            boolean max = false; // Maximum not found

            while (elements.hasNext()) {
                if (((MembershipFunctionPoint) elements.next()).getDegreeOfMembership() == 1.0f) {
                    if (!max) {
                        max = true;
                    } else {
                        return false;
                    }
                }
            }

            return true;
        }

        return false;
    }

    /**
     * Checks whether the membership function of this fuzzy set fulfills the requirements of a fuzzy LR number.
     * In addition to the requirements for a fuzzy number the membership function must be represented
     * by two reference function L and R.
     * @return <code>true</code> if the fuzzy set would be a fuzzy LR number <code>false</code>, otherwise
     * @see #isValidFuzzyNumber
     */
    protected synchronized boolean isValidFuzzyLRNumber() {
        // Creating a copy
        MembershipFunction f = (MembershipFunction) this.clone();
        f.reduce();

        return (((f.size() == 3) && f.isValidFuzzyNumber()) ? true : false);
    }

    /**
     * Checks whether the membership function of this fuzzy set fulfills the requirements of a fuzzy interval.
     * The membership function has to be convex and normalized. In addition, the maximum
     * has to lie between a closed interval (<tt>[a,b]</tt> with <tt>a != b</tt>).
     * @return <code>true</code> if the fuzzy set would be a fuzzy interval <code>false</code>, otherwise
     * @see #isNormalized
     * @see #isConvex
     */
    protected synchronized boolean isValidFuzzyInterval() {
        if (isNormalized() && isConvex()) {
            // Additionally, we are checking whether the maximum is in an interval
            ListIterator elements = points.listIterator();
            boolean max = false; // maximum not found

            while (elements.hasNext()) {
                if (((MembershipFunctionPoint) elements.next()).getDegreeOfMembership() == 1.0f) {
                    if (!max) {
                        max = true;
                    } else {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Checks whether the membership function of this fuzzy set fulfills the requirements of a fuzzy LR interval.
     * In addition to the requirements for a fuzzy interval the membership function must be represented
     * by two reference function L and R.
     * @return <code>true</code> if the fuzzy set would be a fuzzy LR interval <code>false</code>, otherwise
     * @see #isValidFuzzyInterval
     */
    protected synchronized boolean isValidFuzzyLRInterval() {
        // Creating a copy
        MembershipFunction f = (MembershipFunction) this.clone();
        f.reduce();

        return (((f.size() == 4) && f.isValidFuzzyInterval()) ? true : false);
    }

    /**
     * Returns an iterator of all x values which define the membership
     * function.
     *
     * @return an iterator of Float objects which are the x values of the
     *         membership function
     */
    public synchronized Iterator<Float> iterator() {
        List<Float> retVector = new ArrayList<Float>(points.size()); // with x-values

        ListIterator<MembershipFunctionPoint> elements = points.listIterator();

        while (elements.hasNext()) {
            retVector.add(new Float(elements.next().getX()));
        }

        return retVector.iterator();
    }

    /**
     * Returns an array of all x values which define the membership
     * function.
     *
     * @return an array of Float objects which are the x values of the
     *         membership function
     */
    public synchronized float[] getXValues() {
        float[] xValues = null;
        int size = points.size();
        if (size > 0) {
            xValues = new float[points.size()];
            for (int i = 0; i < size; i++) {
                MembershipFunctionPoint membershipFunctionPoint = points.get(i);
                xValues[i] = membershipFunctionPoint.getX();
            }
        }
        return xValues;
    }

    /**
     * Returns the number of points specifying the membership function.
     *
     * @return the number of specified x values
     */
    public int size() {
        return points.size();
    }

    /**
     * Checks if the membership function is defined by at least one point.
     *
     * @return <code>true</code> if a point exists <code>false</code>, otherwise
     */
    public boolean isDefined() {
        return (!points.isEmpty());
    }

    /**
     * Returns the degree of membership to a x value.
     *
     * @param x
     *            the x value
     * @return the degree of membership function to <code>x</code>
     */
    public synchronized float getDegreeOfMembership(final float x) {
        if (points.isEmpty()) {
            return 0.0f;
        }

        if ((x < (points.get(0)).getX()) ||
                (x > (points.get(points.size() - 1)).getX())) {
            return 0.0f;
        }

        // By binary search we are finding the right position within the vector
        int minPos = 0;
        int maxPos = points.size() - 1;
        int i;

        while ((maxPos - minPos) > 1) {
            i = (maxPos + minPos) / 2;

            if (x <= (points.get(i)).getX()) {
                maxPos = i;
            } else {
                minPos = i;
            }
        }

        MembershipFunctionPoint fse = points.get(maxPos);

        if (x == fse.getX()) {
            return fse.getDegreeOfMembership();
        }

        // The x value doe not exist in the vector
        // Therefore, we calculate the degree of membership by interpolation
        // It is right: y = a + b * x2, mit x2 = x - x1;
        fse = points.get(maxPos - 1);

        float a = fse.getDegreeOfMembership();
        float x1 = fse.getX();
        float b = getSlope((maxPos - 1), maxPos);

        return (a + (b * (x - x1)));
    }

    /**
     * Returns the slope between two x value (in neighborhood).
     * @param posLeft the left x value
     * @param posRight the right x value
     * @return the calculated slope
     */
    private float getSlope(final int posLeft, final int posRight) {
        // y = a + b * x -> b = (y - a) / x, mit x = x2 - x1
        MembershipFunctionPoint fse;
        fse = points.get(posLeft);

        float a = fse.getDegreeOfMembership();
        float x1 = fse.getX();
        fse = points.get(posRight);

        float y = fse.getDegreeOfMembership();
        float x2 = fse.getX();

        return FuzzyManager.round((y - a) / (x2 - x1));
    }

    /**
     * Returns the slope between two x value (in neighborhood).
     *
     * @param xLeft
     *            the left x value
     * @param xRight
     *            the right x value
     * @return the calculated slope
     */
    public float getSlope(final float xLeft, final float xRight) {
        // y = a + b * x -> b = (y - a) / x, mit x = x2 - x1
        return ((getDegreeOfMembership(xRight) - getDegreeOfMembership(xLeft)) / (xRight -
                xLeft));
    }

    /**
     * Reduces the number of points without changing the appearance of the membership function.
     */
    public synchronized void reduce() {
        // Eliminating nulls on the left side
        while (points.size() > 1) {
            if (((points.get(0)).getDegreeOfMembership() == 0.0f) &&
                    ((points.get(1)).getDegreeOfMembership() == 0.0f)) {
                points.remove(0);
            } else {
                break;
            }
        }

        // Eliminating nulls on the right side
        int lastpos;

        while ((lastpos = points.size() - 1) > 0) {
            if (((points.get(lastpos - 1)).getDegreeOfMembership() == 0.0f) &&
                    ((points.get(lastpos)).getDegreeOfMembership() == 0.0f)) {
                points.remove(lastpos);
            } else {
                break;
            }
        }

        if (points.size() >= 3) {
            int i = 1;

            while (i < (points.size() - 1)) {
                // Comparing slopes
                if (getSlope(i - 1, i) == getSlope(i, i + 1)) {
                    points.remove(i);
                } else {
                    i++;
                }
            }
        }

        if ((points.size() == 1) &&
                ((points.get(0)).getDegreeOfMembership() == 0.0f)) {
            points.remove(0);
        }
    }

    /**
     * Gets the minimal value which is defined on the x axis by the set of points.
     *
     * @return the minimal defined x value. Float.NaN if set is empty.
     */
    public synchronized float getMinDefinedX() {
        if (!points.isEmpty()) {
            return (points.get(0)).getX();
        }

        return Float.NaN;
    }

    /**
     * Gets the maximal value which is defined on the x axis by the set of points.
     *
     * @return the maximal defined x value. Float.NaN if set is empty.
     */
    public synchronized float getMaxDefinedX() {
        if (!points.isEmpty()) {
            return (points.get(points.size() - 1)).getX();
        }

        return Float.NaN;
    }

    /**
     * Negates the membership function.
     *
     * @see FuzzyNumber
     * @see FuzzyLRNumber
     * @see FuzzyInterval
     * @see FuzzyLRInterval
     */
    protected synchronized void negate() {
        MembershipFunction f = (MembershipFunction) clone();
        f.clear();

        MembershipFunctionPoint entry;
        ListIterator elements = points.listIterator();

        while (elements.hasNext()) {
            entry = (MembershipFunctionPoint) elements.next();
            f.setWithoutChecking((entry.getX() * (-1.0f)),
                    entry.getDegreeOfMembership());
        }

        // We can reference the object.
        this.points = f.points;
    }

    /**
     * Creates the inverse of the membership function. Due to mathematical restrictions it is impossible
     * to calculate the inverse at <tt>x = 0</tt>, because there is a definition lack.
     *
     * @exception ArithmeticException
     *                if the membership function is defined at <tt>x = 0</tt>
     * @see FuzzyNumber
     * @see FuzzyLRNumber
     * @see FuzzyInterval
     * @see FuzzyLRInterval
     */
    protected synchronized void invert() throws ArithmeticException {
        if (((points.get(0)).getX() * (points.get(points.size() - 1)).getX()) > 0.0f) {
            MembershipFunction f = (MembershipFunction) clone();
            f.clear();

            MembershipFunctionPoint entry;
            ListIterator elements = points.listIterator();

            while (elements.hasNext()) {
                entry = (MembershipFunctionPoint) elements.next();
                f.setWithoutChecking((1.0f / entry.getX()),
                        entry.getDegreeOfMembership());
            }

            // We can reference the object.
            this.points = f.points;
        } else {
            throw new ArithmeticException(FuzzyResourceManager.getString(this, "EXCEPTION_DIVIDE_BY_ZERO"));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof MembershipFunction)) {
            // return points.equals(((FuzzySet)obj).points); // reicht nicht
            // aus, da MembershipFunctionPoint.equals() nur auf Gleichheit der
            // x-Werte prueft
            MembershipFunction tmp_this = (MembershipFunction) this.clone();
            MembershipFunction tmp_obj = (MembershipFunction) ((MembershipFunction) obj).clone();
            // At first, we reduce the membership functions to a minimum of points.
            tmp_this.reduce();
            tmp_obj.reduce();

            // Then, we compare the points pairwise.
            int size_this = tmp_this.points.size();
            int size_obj = tmp_obj.points.size();

            if (size_this != size_obj) {
                return false;
            }

            MembershipFunctionPoint entry_this;
            MembershipFunctionPoint entry_obj;

            for (int i = 0; i < size_this; i++) {
                entry_this = tmp_this.points.get(i);
                entry_obj = tmp_obj.points.get(i);

                if ((entry_this.getX() != entry_obj.getX()) ||
                        (entry_this.getDegreeOfMembership() != entry_obj.getDegreeOfMembership())) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.points != null ? this.points.hashCode() : 0);
        return hash;
    }

    @Override
    public Object clone() {
        try {
            MembershipFunction newObj = (MembershipFunction) super.clone();
            // Eintraege physisch duplizieren.
            newObj.points = new Vector<MembershipFunctionPoint>(points.size());

            ListIterator elements = points.listIterator();

            while (elements.hasNext()) {
                newObj.points.add((MembershipFunctionPoint) ((MembershipFunctionPoint) elements.next()).clone());
            }

            return newObj;
        } catch (java.lang.CloneNotSupportedException e) {
            // kann nicht auftreten
            throw new InternalError(e.toString());
        }
    }

    @Override
    public String toString() {
        return points.toString();
    }
}
