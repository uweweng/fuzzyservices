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
package net.sourceforge.fuzzyservices.beans;

import net.sourceforge.fuzzyservices.core.FuzzyManager;
import net.sourceforge.fuzzyservices.core.MembershipFunctionPoint;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

/**
 * This class represents a point on a membership function according to
 * JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.MembershipFunctionPoint
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class MembershipFunctionPointBean implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    //
    // Bound property names
    //

    /** Bound property name for <code>x</code>. */
    public static final String X_PROPERTY = "x";

    /** Bound property name for <code>degreeOfMembership</code>. */
    public static final String DEGREE_OF_MEMBERSHIP_PROPERTY
            = "degreeOfMembership";

    /** The x value. */
    private float x = FuzzyManager.round(0);

    /** The degree of membership. */
    private float degreeOfMembership = FuzzyManager.round(0);

    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);

    /** Support for any VetoableChangeListeners which have been registered. */
    private transient VetoableChangeSupport vetoableChangeSupport
            = new VetoableChangeSupport(this);

    /**
     * Default <code>MembershipFunctionPointBean</code> constructor. Both x
     * value and degree of membership are 0.0.
     */
    public MembershipFunctionPointBean() {
        // Do nothing
    }

    /**
     * Constructs a <code>MembershipFunctionPointBean</code> which is
     * initialized with <code>x</code> as x value and
     * <code>newDegreeOfMembership</code> as degree of membership of this x
     * value.
     * @param newX the x value
     * @param newDegreeOfMembership the degree of membership to the x value
     * @exception IllegalArgumentException if <code>x</code> is
     * <code>Float.NaN</code>, or not 0.0 <= aDegreeOfMembership <= 1.0,
     */
    public MembershipFunctionPointBean(float newX, float newDegreeOfMembership)
    throws IllegalArgumentException {
        // Check values against core implementation
        MembershipFunctionPoint p = new MembershipFunctionPoint(newX,
                newDegreeOfMembership);
        this.x = newX;
        this.degreeOfMembership = newDegreeOfMembership;
    }

    /**
     * Returns the x value of this point.
     * @return the <code>x</code> property
     * @see #setX
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the degree of membership of this point.
     * @return the <code>degreeOfMembership</code> property
     * @see #setDegreeOfMembership
     */
    public float getDegreeOfMembership() {
        return degreeOfMembership;
    }

    /**
     * Sets the degree of membership of this point.
     * @param aDegreeOfMembership The new value for the property.
     * @exception IllegalArgumentException if not 0.0 <= aDegreeOfMembership
     * <= 1.0
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     * @see #getDegreeOfMembership
     */
    public void setDegreeOfMembership(float aDegreeOfMembership)
    throws IllegalArgumentException, PropertyVetoException {

        // Check value against core implementation
        MembershipFunctionPoint p = new MembershipFunctionPoint(x,
                aDegreeOfMembership);

        float oldValue = this.degreeOfMembership;
        float newDegreeOfMembership = FuzzyManager.round(aDegreeOfMembership);

        vetoableChangeSupport.fireVetoableChange(DEGREE_OF_MEMBERSHIP_PROPERTY,
                new Float(oldValue), new Float(newDegreeOfMembership));
        this.degreeOfMembership = newDegreeOfMembership;
        propertyChangeSupport.firePropertyChange(DEGREE_OF_MEMBERSHIP_PROPERTY,
                new Float(oldValue), new Float(newDegreeOfMembership));
    }

    /**
     * Sets the x value of this point.
     * @param xValue The new value for the property.
     * @exception IllegalArgumentException if <code>xValue</code> is
     * <code>Float.NaN</code>
     * @exception PropertyVetoException when the attempt to set the property is
     * vetoed by a listener
     * @see #getX
     */
    public void setX(float xValue) throws IllegalArgumentException,
            PropertyVetoException {

        // Check value against core implementation
        MembershipFunctionPoint p = new MembershipFunctionPoint(xValue,
                degreeOfMembership);

        float oldValue = this.x;
        float newX = FuzzyManager.round(xValue);
        vetoableChangeSupport.fireVetoableChange(X_PROPERTY,
                new Float(oldValue), new Float(newX));
        this.x = newX;
        propertyChangeSupport.firePropertyChange(X_PROPERTY,
                new Float(oldValue), new Float(newX));
    }

    /**
     * Adds a <code>VetoableChangeListener</code> to the listener list. The
     * listener is registered for all properties.
     * @param listener  the <code>VetoableChangeListener</code> to be added
     */
    public synchronized void addVetoableChangeListener(
            VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    /**
     * Removes a <code>VetoableChangeListener</code> from the listener list.
     * This removes a <code>VetoableChangeListener</code> that was registered
     * for all properties.
     * @param listener  the <code>VetoableChangeListener</code> to be removed
     */
    public synchronized void removeVetoableChangeListener(
            VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    /**
     * Adds a <code>PropertyChangeListener</code> to the listener list. The
     * listener is registered for all properties. <p>
     * A <code>PropertyChangeEvent</code> will get fired in response to setting
     * a bound property. <p>
     * @param listener  the <code>PropertyChangeListener</code> to be added
     */
    public synchronized void addPropertyChangeListener(
            final PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a <code>PropertyChangeListener</code> from the listener list.
     * This removes a <code>PropertyChangeListener</code> that was registered
     * for all properties.
     * @param listener  the <code>PropertyChangeListener</code> to be removed
     */
    public synchronized void removePropertyChangeListener(
            final PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}


