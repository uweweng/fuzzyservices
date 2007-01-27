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
    public FuzzyNumber() {
        // Not allowed
    }
    
    /**
     * Creates a fuzzy number which membership function looks like an isosceles triangle.
     * @param x the x value with degree of membership of 1.0
     * @param spread the spread to x <code>x</code> with degree of membership 0.0
     * @exception IllegalArgumentException if <code>spread</code> is not positive.
     */
    public FuzzyNumber(final float x, final float spread) throws IllegalArgumentException{
        if (spread > 0.0f) {
            points.add(new MembershipFunctionPoint((x - spread), 0.0f));
            points.add(new MembershipFunctionPoint(x, 1.0f));
            points.add(new MembershipFunctionPoint((x + spread), 0.0f));
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_NUMBER"));
    }
    
    /**
     * Constructs a fuzzy number with a membership function like a triangle.
     * @param x the x value with degree of membership of 1.0
     * @param alpha the distance to <code>x</code> on the left side and degree of membership 0.0
     * @param beta the distance to <code>x</code> on the right side and degree of membership 0.0
     * @exception IllegalArgumentException if <code>alpha</code> or <code>beta</code> is not positive
     */
    public FuzzyNumber(final float x, final float alpha, final float beta) throws IllegalArgumentException{
        if ((alpha > 0.0f) && (beta > 0.0f)) {
            points.add(new MembershipFunctionPoint((x - alpha), 0.0f));
            points.add(new MembershipFunctionPoint(x, 1.0f));
            points.add(new MembershipFunctionPoint((x + beta), 0.0f));
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_NUMBER"));
    }
    
    /**
     * Creates a fuzzy number by converting a fuzzy number of type LR.
     * A fuzzy LR number is always a valid fuzzy number.
     * @param fn the fuzzy LR number
     * @exception NullPointerException if <code>fn</code> is <code>null</code>
     */
    public FuzzyNumber(final FuzzyLRNumber fn) throws NullPointerException{
        ListIterator elements = fn.points.listIterator();
        
        while (elements.hasNext())
            points.add(((MembershipFunctionPoint) elements.next()).clone());
    }
    
    /**
     * Creates a fuzzy number by converting a fuzzy set.
     * @param fs the fuzzy set
     * @exception IllegalArgumentException if the fuzzy set does not fulfill the properties of a fuzzy number
     * @exception NullPointerException if <code>fs</code> is <code>null</code>
     */
    public FuzzyNumber(final FuzzySet fs) throws NullPointerException, IllegalArgumentException{
        if (fs.isValidFuzzyNumber()) {
            ListIterator elements = fs.points.listIterator();
            
            while (elements.hasNext())
                points.add(((MembershipFunctionPoint) elements.next()).clone());
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_NUMBER"));
    }
    
    /**
     * Creates the inverse of the membership function. Due to mathematical restrictions it is impossible
     * to calculate the inverse at <tt>x = 0</tt>, because there is a definition lack.
     * @exception ArithmeticException
     *                if the membership function is defined at <tt>x = 0</tt>
     */
    public synchronized void invert() throws ArithmeticException {
        super.invert();
    }
    
    /**
     * Checks whether the fuzzy number is negative. A fuzzy number is negative
     * if degree of membership is only greater than zero when x is lower than zero.
     * @return <code>true</code> if fuzzy number is negative, <code>false</code> otherwise
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
     * Checks whether the fuzzy number is positive. A fuzzy number is positive
     * if degree of membership is only greater than zero when x is also greater than zero.
     * @return <code>true</code> if fuzzy number is positive, <code>false</code> otherwise
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
     * Checks whether this fuzzy number fulfills the requirements of a fuzzy LR number.
     * In addition to the requirements for a fuzzy number the membership function must be represented
     * by two reference function L and R.
     * @return <code>true</code> if the fuzzy number would be a fuzzy LR number <code>false</code>, otherwise
     */
    public synchronized boolean isValidFuzzyLRNumber() {
        FuzzyNumber fn = (FuzzyNumber) this.clone();
        fn.reduce();
        
        return ((fn.size() == 3) ? true : false);
    }
    
    /**
     * Negates the fuzzy number.
     */
    public synchronized void negate() {
        super.negate();
    }
    
    /**
     * Sets the degree of membership at <code>x</code> to 0.0.
     * @param x the x coodinate
     * @return the previous degree of membership if specified, <code>Float.NaN</code> otherwise
     * @exception IllegalArgumentException if the membership function would not fulfill the conditions of a
     * fuzzy number after deletion
     */
    public synchronized float remove(final float x) throws IllegalArgumentException{
        // Fuzzy-Zahl in eine Fuzzy-Menge konvertieren, Punkt loeschen und auf Fuzzy-Zahl pruefen
        FuzzySet fs = new FuzzySet(this);
        float retfloat = fs.remove(x);
        
        if (fs.isValidFuzzyNumber()) {
            this.points = fs.points;
            
            return retfloat;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_NUMBER"));
    }
    
    /**
     * Defines at <code>x</code> a new degree of membership.
     * @param x the x coodinate
     * @param dom the new degree of membership at <code>x</code>
     * @return the previous degree of membership if specified, <code>Float.NaN</code> otherwise
     * @exception IllegalArgumentException if <code>x</code> is <code>Float.NaN</code>, or not 0.0 <= dom <= 1.0,
     * or the membership function does not fulfill the conditions of a fuzzy number.
     */
    public synchronized float set(final float x, final float dom) throws IllegalArgumentException{
        // Fuzzy-Zahl in eine Fuzzy-Menge konvertieren, Punkt einfuegen und auf Fuzzy-Zahl pruefen.
        FuzzySet fs = new FuzzySet(this);
        float retfloat = fs.set(x, dom);
        
        if (fs.isValidFuzzyNumber()) {
            this.points = fs.points;
            
            return retfloat;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_NUMBER"));
    }
    
    /**
     * Returns a textual representation of the fuzzy number
     * @param withPoints Decides whether the membership function points are part of the representation
     * @return a string representation of the fuzzy number
     */
    public String toString(final boolean withPoints) {
        if (withPoints)
            return super.toString();
        else
            
            return FuzzyResourceManager.getString(this, "FUZZY_NUMBER_WITHOUT_POINTS",
                    new Object[] { Integer.toString(points.size()) });
    }
}
