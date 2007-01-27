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
import java.util.ListIterator;

/**
 * A fuzzy interval is a convex, normalized fuzzy set whose membership function
 * is at least segmentally continuous and has a mean interval whose elements
 * possess the membership function value f(x) = 1.
 * This class offers the functionality of a fuzzy interval. Comparable with
 * traditional numbers and intervals arithmetic operations on fuzzy intervals
 * are possible. In this case, the principle of advanced operations is used.
 * Advanced operations means a separated examination of both edges.
 *
 * @see FuzzySet
 * @see FuzzyManager
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class FuzzyInterval extends MembershipFunction implements Cloneable, Serializable {
    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor is private
     * because an undefined membership function can not be a valid fuzzy interval.
     */
    public FuzzyInterval() {
        // Not allowed
    }
    
    /**
     * Creates a fuzzy interval with a membership function like a trapezium.
     * The degree of membership between <code>plateau1</code> and <code>plateau2</code> is 1.0.
     * @param plateau1 coming from left at this point the membership is 1.0 the first time.
     * @param plateau2 coming from right at this point the membership is 1.0 the first time.
     * @param alpha the spread of the left trailing edge
     * @param beta the spread of the right trailing edge
     * @exception IllegalArgumentException if is not fulfill <code>plateau1 - alpha</code> < <code>plateau1</code> <=
     * <code>plateau2</code> < <code>plateau2 + beta</code>
     */
    public FuzzyInterval(final float plateau1, final float plateau2,
            final float alpha, final float beta) throws IllegalArgumentException{
        if (((plateau1 - alpha) < plateau1) && (plateau1 < plateau2) &&
                (plateau2 < (plateau2 + beta))) {
            points.add(new MembershipFunctionPoint((plateau1 - alpha),
                    0.0f));
            points.add(new MembershipFunctionPoint(plateau1, 1.0f));
            points.add(new MembershipFunctionPoint(plateau2, 1.0f));
            points.add(new MembershipFunctionPoint((plateau2 + beta),
                    0.0f));
        } else
            throw new IllegalArgumentException();
    }
    
    /**
     * Creates a fuzzy interval by converting a fuzzy interval of type LR.
     * A fuzzy LR interval is always a valid fuzzy interval.
     * @param fi the fuzzy LR interval
     * @exception NullPointerException if <code>fi</code> is <code>null</code>
     */
    public FuzzyInterval(final FuzzyLRInterval fi) throws NullPointerException{
        ListIterator elements = fi.points.listIterator();
        
        while (elements.hasNext())
            points.add(((MembershipFunctionPoint) elements.next()).clone());
    }
    
    /**
     * Creates a fuzzy interval by converting a fuzzy set.
     * The fuzzy set has to fulfill the requirements of a fuzzy interval.
     * @param fs the fuzzy set
     * @exception IllegalArgumentException if <code>fs</code> does not fulfill the properties of a fuzzy interval
     * @exception NullPointerException if <code>fs</code> is <code>null</code>
     */
    public FuzzyInterval(final FuzzySet fs) throws NullPointerException, IllegalArgumentException{
        if (fs.isValidFuzzyInterval()) {
            ListIterator elements = fs.points.listIterator();
            
            while (elements.hasNext())
                points.add(((MembershipFunctionPoint) elements.next()).clone());
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_INTERVAL"));
    }
    
    /**
     * Creates the inverse of the membership function. Due to mathematical restrictions it is impossible
     * to calculate the inverse at <tt>x = 0</tt>, because there is a definition lack.
     *
     * @exception ArithmeticException
     *                if the membership function is defined at <tt>x = 0</tt>
     */
    public synchronized void invert() throws ArithmeticException{
        super.invert();
    }
    
    /**
     * Checks whether the fuzzy interval is negative. A fuzzy interval is negative
     * if degree of membership is only greater than zero when x is lower than zero.
     * @return <code>true</code> if fuzzy interval is negative, <code>false</code> otherwise
     */
    public synchronized boolean isNegative() {
        MembershipFunctionPoint entry;
        ListIterator elements = points.listIterator();
        
        while (elements.hasNext()) {
            entry = (MembershipFunctionPoint) elements.next();
            
            if (entry.getX() >= 0.0f) {
                if (entry.getDegreeOfMembership() > 0.0f)
                    return false;
            }
        }
        
        return true;
    }
    
    /**
     * Checks whether the fuzzy interval is positive. A fuzzy interval is positive
     * if degree of membership is only greater than zero when x is also greater than zero.
     * @return <code>true</code> if fuzzy interval is positive, <code>false</code> otherwise
     */
    public synchronized boolean isPositive() {
        MembershipFunctionPoint entry;
        ListIterator elements = points.listIterator();
        
        while (elements.hasNext()) {
            entry = (MembershipFunctionPoint) elements.next();
            
            if (entry.getX() <= 0.0f) {
                if (entry.getDegreeOfMembership() > 0.0f)
                    return false;
            } else
                
                return true;
        }
        
        return true;
    }
    
    /**
     * Checks whether the membership function of this fuzzy interval fulfills the requirements of a fuzzy LR interval.
     * In addition to the requirements for a fuzzy interval the membership function must be represented
     * by two reference function L and R.
     * @return <code>true</code> if the fuzzy interval would be a fuzzy LR interval <code>false</code>, otherwise
     */
    public synchronized boolean isValidFuzzyLRInterval() {
        FuzzyInterval fi = (FuzzyInterval) this.clone();
        fi.reduce();
        
        return ((fi.size() == 4) ? true : false);
    }
    
    /**
     * Negates the fuzzy interval.
     */
    public synchronized void negate() {
        super.negate();
    }
    
    /**
     * Sets the degree of membership at <code>x</code> to 0.0.
     * @param x the x coodinate
     * @return the previous degree of membership if specified, <code>Float.NaN</code> otherwise
     * @exception IllegalArgumentException if the membership function would not fulfill the conditions of a fuzzy
     * interval after deletion
     */
    public synchronized float remove(final float x) throws IllegalArgumentException{
        // Fuzzy-Intervall in eine Fuzzy-Menge konvertieren, Punkt loeschen und auf Fuzzy-Intervall pruefen
        FuzzySet fs = new FuzzySet(this);
        float retfloat = fs.remove(x);
        
        if (fs.isValidFuzzyInterval()) {
            this.points = fs.points;
            
            return retfloat;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_INTERVAL"));
    }
    
    /**
     * Defines at <code>x</code> a new degree of membership.
     * @param x the x coodinate
     * @param dom the new degree of membership at <code>x</code>
     * @return the previous degree of membership if specified, <code>Float.NaN</code> otherwise
     * @exception IllegalArgumentException if <code>x</code> is <code>Float.NaN</code>, or not 0.0 <= dom <= 1.0,
     * or the membership function does not fulfill the conditions of a fuzzy interval.
     */
    public synchronized float set(final float x, final float dom) throws IllegalArgumentException{
        // Fuzzy-Intervall in eine Fuzzy-Menge konvertieren, Punkt einfuegen und auf Fuzzy-Intervall pruefen.
        FuzzySet fs = new FuzzySet(this);
        float retfloat = fs.set(x, dom);
        
        if (fs.isValidFuzzyInterval()) {
            this.points = fs.points;
            
            return retfloat;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_INTERVAL"));
    }
    
    /**
     * Returns a textual representation of the fuzzy interval
     * @param withPoints Decides whether the membership function points are part of the representation
     * @return a string representation of the fuzzy interval
     */
    public String toString(final boolean withPoints) {
        if (withPoints)
            return super.toString();
        else
            
            return FuzzyResourceManager.getString(this, "FUZZY_INTERVAL_WITHOUT_POINTS",
                    new Object[] { Integer.toString(points.size()) });
    }
}
