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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.fuzzyservices.utils.FuzzyManager;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The membership function f(x) is defined by a setDegreeOfMembership of points.
 * Every point marks a relationship between a value x and its
 * degree of membership. All points clamp a traverse.
 * If the degree of membership of a value is not defined by a point exactly
 * it will be calculated by interpolation.
 * This class offers methods for manipulating the membership function, in general.
 * For calculation the pointed membership function is iterated in a certain
 * increment defined in fuzzy manager of this system.
 *
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
public class MembershipFunction implements Cloneable, Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Unique technical identifier. Only for persistence is used.
     */
    private int id;
    //
    // Bound property names
    //
    /** Bound property name for <code>newDegreeOfMembership</code>. */
    public static final String DEGREE_OF_MEMBERSHIP_PROPERTY = "degreeOfMembership";
    /**
     * Set of points specifying the mathematical membership function.
     */
    private List<MembershipFunctionPoint> points = new ArrayList<MembershipFunctionPoint>(FuzzyManager.getMaxNumStep());
    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    /** Support for any VetoableChangeListeners which have been registered. */
    private transient VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    /**
     * Creates a membership function with f(x) = 0.
     */
    public MembershipFunction() {
    }

    /**
     * Creates a membership function with already existing points.
     * @param newPoints an array of X/Degree of Membership Pairs.
     */
    public MembershipFunction(final MembershipFunctionPoint[] newPoints) {
        if (newPoints != null) {
            for (int i = 0; i < newPoints.length; i++) {
                MembershipFunctionPoint membershipFunctionPoint = newPoints[i];
                try {
                    setDegreeOfMembership(membershipFunctionPoint.getX(), membershipFunctionPoint.getDegreeOfMembership());
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(MembershipFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Creates a membership function looks like an isosceles triangle.
     * @param x the x value with degree of membership of 1.0
     * @param spread the spread to x <code>x</code> with degree of membership 0.0
     */
    public MembershipFunction(final float x, final float spread) {
        if (spread > 0.0f) {
            points.add(new MembershipFunctionPoint((x - spread), 0.0f));
            points.add(new MembershipFunctionPoint(x, 1.0f));
            points.add(new MembershipFunctionPoint((x + spread), 0.0f));
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_NUMBER"));
        }
    }

    /**
     * Creates a membership function looks like a trapezium.
     * The degree of membership between <code>plateau1</code> and <code>plateau2</code> is 1.0.
     * @param plateau1 coming from left at this point the membership is 1.0 the first time.
     * @param plateau2 coming from right at this point the membership is 1.0 the first time.
     * @param alpha the spread of the left trailing edge
     * @param beta the spread of the right trailing edge
     */
    public MembershipFunction(final float plateau1, final float plateau2, final float alpha, final float beta) {
        if (((plateau1 - alpha) < plateau1) && (plateau1 < plateau2) && (plateau2 < (plateau2 + beta))) {
            points.add(new MembershipFunctionPoint((plateau1 - alpha), 0.0f));
            points.add(new MembershipFunctionPoint(plateau1, 1.0f));
            points.add(new MembershipFunctionPoint(plateau2, 1.0f));
            points.add(new MembershipFunctionPoint((plateau2 + beta), 0.0f));
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_FUZZY_INTERVAL"));
        }
    }

    /**
     * Returns the technical identifier (e.g. within a database).
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Defines at <code>x</code> a new degree of membership.
     * @param x the x coordinate
     * @param newDegreeOfMembership the new degree of membership at <code>x</code>
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     */
    public final synchronized void setDegreeOfMembership(final float x, final float newDegreeOfMembership) throws PropertyVetoException {
        
        if (x == x) { // x is not Float.NaN

            if ((newDegreeOfMembership >= 0.0f) && (newDegreeOfMembership <= 1.0f)) {
                
                float oldDegreeOfMembership = getDegreeOfMembership(x);
                MembershipFunctionPoint oldPoint = new MembershipFunctionPoint(x, oldDegreeOfMembership);
                MembershipFunctionPoint newPoint = new MembershipFunctionPoint(x, newDegreeOfMembership);
                vetoableChangeSupport.fireVetoableChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);
                
                if (points.isEmpty()) {
                    
                    points.add(new MembershipFunctionPoint(x, newDegreeOfMembership));
                    propertyChangeSupport.firePropertyChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);
                    return;
                }
                
                if (x > (points.get(points.size() - 1)).getX()) {
                    // This case appears very often when combining membership functions
                    points.add(new MembershipFunctionPoint(x, newDegreeOfMembership));
                    propertyChangeSupport.firePropertyChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);
                    return;
                }
                
                if (x < (points.get(0)).getX()) {
                    // This case appears very often when combining membership functions
                    points.add(0, new MembershipFunctionPoint(x, newDegreeOfMembership));
                    propertyChangeSupport.firePropertyChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);
                    return;
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
                        fse.setDegreeOfMembership(newDegreeOfMembership);
                        return;
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
                    fse.setDegreeOfMembership(newDegreeOfMembership);
                    propertyChangeSupport.firePropertyChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);
                    return;
                }

                // x value is not defined yet.
                points.add(maxPos, new MembershipFunctionPoint(x, newDegreeOfMembership));
                propertyChangeSupport.firePropertyChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);
            } else {
                throw new IllegalArgumentException(FuzzyResourceManager.getString(
                        this,
                        "EXCEPTION_INVALID_DEGREE_OF_MEMBERSHIP",
                        new Object[]{
                            Float.toString(newDegreeOfMembership)
                        }));
            }
            
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_NOT_A_NUMBER"));
        }
    }

    /**
     * Sets the degree of membership at <code>x</code> to 0.0.
     * @param x the x coordinate
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     */
    public final synchronized void remove(final float x) throws PropertyVetoException {
        if (x == x) { // x is not Float.NaN

            float oldDegreeOfMembership = getDegreeOfMembership(x);
            float newDegreeOfMembership = 0.0f;
            MembershipFunctionPoint oldPoint = new MembershipFunctionPoint(x, oldDegreeOfMembership);
            MembershipFunctionPoint newPoint = new MembershipFunctionPoint(x, newDegreeOfMembership);
            
            vetoableChangeSupport.fireVetoableChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);

            // The degree of membership is not interesting, because MembershipFunctionPoint.equals()
            // compares only the x value.
            int pos = points.indexOf(new MembershipFunctionPoint(x, 0.0f));
            
            if (pos >= 0) {
                points.remove(pos);
            }
            propertyChangeSupport.firePropertyChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);
            
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_NOT_A_NUMBER"));
        }
    }

    /**
     * Reset the membership function to <tt>f(x) = 0.0</tt>.
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     */
    public final synchronized void clear() throws PropertyVetoException {
        MembershipFunctionPoint entry;
        ListIterator<MembershipFunctionPoint> elements = points.listIterator();
        
        while (elements.hasNext()) {
            entry = elements.next();
            float x = entry.getX();
            float oldDegreeOfMembership = entry.getDegreeOfMembership();
            float newDegreeOfMembership = 0.0f;
            MembershipFunctionPoint oldPoint = new MembershipFunctionPoint(x, oldDegreeOfMembership);
            MembershipFunctionPoint newPoint = new MembershipFunctionPoint(x, newDegreeOfMembership);
            vetoableChangeSupport.fireVetoableChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);
            entry.setDegreeOfMembership(newDegreeOfMembership);
            propertyChangeSupport.firePropertyChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);
        }
        
        points.clear();
    }

    /**
     * Returns the spread on the left falling edge. It is the so-called alpha value.
     * @return the spread alpha
     */
    public final synchronized float getAlpha() {
        MembershipFunction mf = (MembershipFunction) this.clone();
        mf.reduce();
        
        float x0;
        float x1;
        x0 = mf.points.get(0).getX();
        x1 = mf.points.get(1).getX();
        
        return FuzzyManager.round(x1 - x0);
    }

    /**
     * Returns the spread on the right falling edge. It is the so-called beta value.
     * @return the spread beta
     */
    public final synchronized float getBeta() {
        
        MembershipFunction mf = (MembershipFunction) this.clone();
        mf.reduce();
        
        float x0;
        float x1;
        int size = mf.size();
        x0 = mf.points.get(size - 2).getX();
        x1 = mf.points.get(size - 1).getX();
        
        return FuzzyManager.round(x1 - x0);
    }

    /**
     * Returns the height of the membership function. It is the maximum defined
     * degree of membership.
     *
     * @return the maximum degree of membership as height
     */
    public final synchronized float getHeight() {
        float height = 0.0f;
        float dom;
        ListIterator<MembershipFunctionPoint> elements = points.listIterator();
        
        while (elements.hasNext()) {
            dom = elements.next().getDegreeOfMembership();
            
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
     * Checks whether the membership function is normalized. A membership
     * function is normalized if its height is 1.0.
     * @return <code>true</code> if membership function is normalized
     * <code>false</code>, otherwise
     * @see #getHeight
     */
    public final synchronized boolean isNormalized() {
        return (getHeight() == 1.0f);
    }

    /**
     * Normalizes the membership function.
     * Afterwards the degree of membership is alwalys in <tt>[0,1]</tt>.
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     * @see #isNormalized
     */
    public final synchronized void normalize() throws PropertyVetoException {
        float height = getHeight();
        
        if (height != 0.0f) {
            MembershipFunctionPoint entry;
            ListIterator<MembershipFunctionPoint> elements = points.listIterator();
            
            while (elements.hasNext()) {
                entry = elements.next();
                float x = entry.getX();
                float oldDegreeOfMembership = entry.getDegreeOfMembership();
                float newDegreeOfMembership = oldDegreeOfMembership / height;
                MembershipFunctionPoint oldPoint = new MembershipFunctionPoint(x, oldDegreeOfMembership);
                MembershipFunctionPoint newPoint = new MembershipFunctionPoint(x, newDegreeOfMembership);
                
                vetoableChangeSupport.fireVetoableChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);
                entry.setDegreeOfMembership(newDegreeOfMembership);
                propertyChangeSupport.firePropertyChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);
            }
        }
    }

    /**
     * Creates the complementary membership function (<tt>f(x) = 1-f(x)</tt>).
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     */
    public final synchronized void reciproce() throws PropertyVetoException {
        MembershipFunctionPoint entry;
        ListIterator<MembershipFunctionPoint> elements = points.listIterator();
        
        while (elements.hasNext()) {
            entry = elements.next();
            float x = entry.getX();
            float oldDegreeOfMembership = entry.getDegreeOfMembership();
            float newDegreeOfMembership = 1.0f - oldDegreeOfMembership;
            MembershipFunctionPoint oldPoint = new MembershipFunctionPoint(x, oldDegreeOfMembership);
            MembershipFunctionPoint newPoint = new MembershipFunctionPoint(x, newDegreeOfMembership);
            vetoableChangeSupport.fireVetoableChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);
            entry.setDegreeOfMembership(newDegreeOfMembership);
            propertyChangeSupport.firePropertyChange(DEGREE_OF_MEMBERSHIP_PROPERTY, oldPoint, newPoint);
        }
    }

    /**
     * Dilates the membership function.
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     */
    public final synchronized void dilate() throws PropertyVetoException {
        if (!points.isEmpty()) {
            // Iterating x axis in granularity steps.
            float granularity = getGranularity(this);
            int numSteps = getNumSteps(this);
            float x = getMinDefinedX();
            MembershipFunction tmpThis = new MembershipFunction();
            
            for (int i = 0; i < numSteps; i++) {
                
                tmpThis.setDegreeOfMembership(x,
                        (float) Math.pow(getDegreeOfMembership(x),
                        (float) (1.0 / 2.0)));
                x += granularity;
            }

            // Iterating all existing points.
            MembershipFunctionPoint entry;
            ListIterator<MembershipFunctionPoint> elements = points.listIterator();
            
            while (elements.hasNext()) {
                entry = elements.next();
                tmpThis.setDegreeOfMembership(entry.getX(),
                        (float) Math.pow(entry.getDegreeOfMembership(),
                        (float) (1.0 / 2.0)));
            }
            
            elements = tmpThis.points.listIterator();
            
            while (elements.hasNext()) {
                entry = elements.next();
                setDegreeOfMembership(entry.getX(), entry.getDegreeOfMembership());
            }

            // Finally, we try to minimize the number of points.
            reduce();
        }
    }

    /**
     * Concentrates the membership function.
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     */
    public final synchronized void concentrate() throws PropertyVetoException {
        if (!points.isEmpty()) {
            // Iterating x axis in granularity steps.
            float granularity = getGranularity(this);
            int numSteps = getNumSteps(this);
            float x = getMinDefinedX();
            MembershipFunction tmpThis = new MembershipFunction();
            
            for (int i = 0; i < numSteps; i++) {
                
                tmpThis.setDegreeOfMembership(x, (float) Math.pow(getDegreeOfMembership(x), 2.0));
                x += granularity;
            }

            // Iterating all existing points.
            MembershipFunctionPoint entry;
            ListIterator<MembershipFunctionPoint> elements = points.listIterator();
            
            while (elements.hasNext()) {
                entry = elements.next();
                tmpThis.setDegreeOfMembership(entry.getX(),
                        (float) Math.pow(entry.getDegreeOfMembership(), 2.0));
            }
            
            elements = tmpThis.points.listIterator();
            
            while (elements.hasNext()) {
                entry = elements.next();
                setDegreeOfMembership(entry.getX(), entry.getDegreeOfMembership());
            }

            // Finally, we try to minimize the number of points.
            reduce();
        }
    }

    /**
     * Checks whether the membership function is convex. A membership function
     * is convex if there is only one change of sign from + to -.
     * @return <code>true</code> if membership function is convex
     * <code>false</code>, otherwise
     */
    public final synchronized boolean isConvex() {
        if (points.isEmpty()) {
            return false;
        }
        
        if (points.size() > 2) {
            int vzw = 0; // number of change of sign

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
                
                if (((lastSlope > 0.0f) && (slope < 0.0f)) || ((lastSlope < 0.0f) && (slope > 0.0f))) {
                    vzw++;
                }
                
                entryLeft = entryRight;
                
                if (slope != 0.0f) {
                    lastSlope = slope;
                }
            }
            
            return ((vzw == 1) ? true : false);
        }
        
        return true;
    }

    /**
     * Returns an iterator of all x values which define the membership
     * function.
     *
     * @return an iterator of Float objects which are the x values of the
     *         membership function
     */
    public final synchronized Iterator<Float> iterator() {
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
    public final synchronized float[] getXValues() {
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
    public final int size() {
        return points.size();
    }

    /**
     * Checks if the membership function is defined by at least one point.
     *
     * @return <code>true</code> if a point exists <code>false</code>, otherwise
     */
    public final boolean isDefined() {
        return (!points.isEmpty());
    }

    /**
     * Returns the degree of membership to a x value.
     *
     * @param x the x value
     * @return the degree of membership function to <code>x</code>
     */
    public final synchronized float getDegreeOfMembership(final float x) {
        if (points.isEmpty()) {
            return 0.0f;
        }
        
        if ((x < (points.get(0)).getX()) || (x > (points.get(points.size() - 1)).getX())) {
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
     * @param xLeft the left x value
     * @param xRight the right x value
     * @return the calculated slope
     */
    private float getSlope(final float xLeft, final float xRight) {
        // y = a + b * x -> b = (y - a) / x, mit x = x2 - x1
        return ((getDegreeOfMembership(xRight) - getDegreeOfMembership(xLeft)) / (xRight - xLeft));
    }

    /**
     * Calculates the number of steps when combining this membership function
     * with <code>membershipFunction</code>.
     * @param membershipFunction the membership function for combining
     * @return the number of steps
     */
    public synchronized int getNumSteps(final MembershipFunction membershipFunction) {
        float thisspread = this.getMaxDefinedX() - this.getMinDefinedX();
        float otherspread = membershipFunction.getMaxDefinedX() - membershipFunction.getMinDefinedX();
        float commonspread = ((thisspread > otherspread) ? thisspread
                : otherspread);
        
        return ((int) (commonspread / this.getGranularity(membershipFunction)));
    }

    /**
     * Calculates the increment when combining this membership function with
     * <code>membershipFunction</code>.
     * @param membershipFunction the membershipFunction for combining
     * @return the so-called granularity
     */
    public synchronized float getGranularity(final MembershipFunction membershipFunction) {
        float thisspread = this.getMaxDefinedX() - this.getMinDefinedX();
        float otherspread = membershipFunction.getMaxDefinedX() - membershipFunction.getMinDefinedX();
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

    /**
     * Reduces the number of points without changing the appearance of the membership function.
     */
    public synchronized void reduce() {
        // Eliminating nulls on the left side
        while (points.size() > 1) {
            if (((points.get(0)).getDegreeOfMembership() == 0.0f)
                    && ((points.get(1)).getDegreeOfMembership() == 0.0f)) {
                points.remove(0);
            } else {
                break;
            }
        }

        // Eliminating nulls on the right side
        int lastpos;
        
        while ((lastpos = points.size() - 1) > 0) {
            if (((points.get(lastpos - 1)).getDegreeOfMembership() == 0.0f)
                    && ((points.get(lastpos)).getDegreeOfMembership() == 0.0f)) {
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
        
        if ((points.size() == 1) && ((points.get(0)).getDegreeOfMembership() == 0.0f)) {
            points.remove(0);
        }
    }

    /**
     * Gets the minimal value which is defined on the x axis by the set of points.
     *
     * @return the minimal defined x value. Float.NaN if set is empty.
     */
    public final synchronized float getMinDefinedX() {
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
    public final synchronized float getMaxDefinedX() {
        if (!points.isEmpty()) {
            return (points.get(points.size() - 1)).getX();
        }
        
        return Float.NaN;
    }

    /**
     * Negates the membership function.
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     *
     * @see FuzzyNumber
     * @see FuzzyLRNumber
     * @see FuzzyInterval
     * @see FuzzyLRInterval
     */
    public final synchronized void negate() throws PropertyVetoException {
        MembershipFunction f = (MembershipFunction) clone();
        f.clear();
        
        MembershipFunctionPoint entry;
        ListIterator elements = points.listIterator();
        
        while (elements.hasNext()) {
            entry = (MembershipFunctionPoint) elements.next();
            f.setDegreeOfMembership((entry.getX() * (-1.0f)), entry.getDegreeOfMembership());
        }

        // We can reference the object.
        this.points = f.points;
    }

    /**
     * Creates the inverse of the membership function. Due to mathematical restrictions it is impossible
     * to calculate the inverse at <tt>x = 0</tt>, because there is a definition lack.
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     *
     * @see FuzzyNumber
     * @see FuzzyLRNumber
     * @see FuzzyInterval
     * @see FuzzyLRInterval
     */
    public final synchronized void invert() throws PropertyVetoException {
        if (((points.get(0)).getX() * (points.get(points.size() - 1)).getX()) > 0.0f) {
            MembershipFunction f = (MembershipFunction) clone();
            f.clear();
            
            MembershipFunctionPoint entry;
            ListIterator elements = points.listIterator();
            
            while (elements.hasNext()) {
                entry = (MembershipFunctionPoint) elements.next();
                f.setDegreeOfMembership((1.0f / entry.getX()), entry.getDegreeOfMembership());
            }

            // We can reference the object.
            this.points = f.points;
        } else {
            throw new ArithmeticException(FuzzyResourceManager.getString(this, "EXCEPTION_DIVIDE_BY_ZERO"));
        }
    }

    /**
    @Override
    public final boolean equals(final Object obj) {
    if ((obj != null) && (obj instanceof MembershipFunction)) {
    // return points.equals(((FuzzySet)obj).points); // reicht nicht
    // aus, da MembershipFunctionPoint.equals() nur auf Gleichheit der
    // x-Werte prueft
    MembershipFunction tmpThis = (MembershipFunction) this.clone();
    MembershipFunction tmpObj = (MembershipFunction) ((MembershipFunction) obj).clone();
    // At first, we reduce the membership functions to a minimum of points.
    tmpThis.reduce();
    tmpObj.reduce();
    
    // Then, we compare the points pairwise.
    int sizeThis = tmpThis.points.size();
    int sizeObj = tmpObj.points.size();
    
    if (sizeThis != sizeObj) {
    return false;
    }
    
    MembershipFunctionPoint entryThis;
    MembershipFunctionPoint entryObj;
    
    for (int i = 0; i < sizeThis; i++) {
    entryThis = tmpThis.points.get(i);
    entryObj = tmpObj.points.get(i);
    
    if ((entryThis.getX() != entryObj.getX()) || (entryThis.getDegreeOfMembership() != entryObj.getDegreeOfMembership())) {
    return false;
    }
    }
    
    return true;
    }
    
    return false;
    }
    
    @Override
    public final int hashCode() {
    int hash = 7;
    hash = 71 * hash + (this.points != null ? this.points.hashCode() : 0);
    return hash;
    }
    
    @Override
    public final Object clone() {
    try {
    MembershipFunction newObj = (MembershipFunction) super.clone();
    // Duplicate entries physically.
    newObj.points = new ArrayList<MembershipFunctionPoint>(points.size());
    
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
    public final String toString() {
    return points.toString();
    }
     */
    /**
     * Adds a <code>VetoableChangeListener</code> to the listener list. The
     * listener is registered for all properties.
     * @param listener  the <code>VetoableChangeListener</code> to be added
     */
    public final synchronized void addVetoableChangeListener(
            final VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    /**
     * Removes a <code>VetoableChangeListener</code> from the listener list.
     * This removes a <code>VetoableChangeListener</code> that was registered
     * for all properties.
     * @param listener  the <code>VetoableChangeListener</code> to be removed
     */
    public final synchronized void removeVetoableChangeListener(
            final VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    /**
     * Adds a <code>PropertyChangeListener</code> to the listener list. The
     * listener is registered for all properties. <p>
     * A <code>PropertyChangeEvent</code> will get fired in response to setting
     * a bound property. <p>
     * @param listener  the <code>PropertyChangeListener</code> to be added
     */
    public final synchronized void addPropertyChangeListener(
            final PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a <code>PropertyChangeListener</code> from the listener list.
     * This removes a <code>PropertyChangeListener</code> that was registered
     * for all properties.
     * @param listener  the <code>PropertyChangeListener</code> to be removed
     */
    public final synchronized void removePropertyChangeListener(
            final PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
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
        MembershipFunction membershipFunction = (MembershipFunction) obj;
        return new EqualsBuilder().append(this.id, membershipFunction.id).append(this.points, membershipFunction.points).isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 21).append(this.id).append(this.points).toHashCode();
    }
    
    @Override
    public String toString() {
        return FuzzyResourceManager.getString(this,
                "MEMBERSHIP_FUNCTION",
                new Object[]{
                    id,
                    points.toString(),
                    Integer.toString(points.size())
                });
    }
}
