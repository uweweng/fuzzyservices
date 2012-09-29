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

import java.io.Serializable;
import java.util.Iterator;
import net.sourceforge.fuzzyservices.utils.FuzzyManager;

/**
 * This class represents a defuzzificator according to JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.AbstractDefuzzificator
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class Defuzzificator implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;
    //
    // Defuzzificator types
    //
    /**
     * Constant that defines the center-of-area method as <code>type</code>.
     *
     * @see #setType
     */
    public static final byte TYPE_CENTER_OF_AREA = 0;
    /**
     * Constant that defines the left-of-max method as <code>type</code>.
     *
     * @see #setType
     */
    public static final byte TYPE_LEFT_OF_MAX = 1;
    /**
     * Constant that defines the mean-of-max method as <code>type</code>.
     *
     * @see #setType
     */
    public static final byte TYPE_MEAN_OF_MAX = 2;
    /**
     * Constant that defines the right-of-max method as <code>type</code>.
     *
     * @see #setType
     */
    public static final byte TYPE_RIGHT_OF_MAX = 3;
    //
    // Bound property names
    //
    /** Bound property name for <code>type</code>. */
    public static final String TYPE_PROPERTY = "type";
    /** The defuzzificator type. Default is <code>TYPE_CENTER_OF_AREA</code>. */
    private byte type = TYPE_CENTER_OF_AREA;
    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /** Default <code>Defuzzificator</code> constructor. */
    public Defuzzificator() {
        // Do nothing
    }

    /**
     * Constructs a new defuzzifcator.
     * @param newType The new value for the property <code>type</code>.
     */
    public Defuzzificator(final byte newType) {
        this.type = newType;
    }

    /**
     * Returns the type of this defuzzificator.
     * @return the <code>type</code> property
     * @see #setType
     */
    public final byte getType() {
        return type;
    }

    /**
     * Sets the defuzzification type.
     * @param newType The new value for the property.
     * @see #getType
     */
    public final void setType(final byte newType) {
        byte oldType = this.type;
        this.type = newType;
        propertyChangeSupport.firePropertyChange(TYPE_PROPERTY,
                new Byte(oldType), new Byte(newType));
    }

    /**
     * Defuzzifies the <code>fuzzySet</code> object using the specified type.
     * @return The crisp value as result of the defuzzification
     * @param fuzzySet The fuzzy set to be defuzzified
     * @see #setType
     */
    public final float defuzzify(final FuzzySet fuzzySet) {
        switch (type) {
            case TYPE_CENTER_OF_AREA:
                return defuzzifyByCenterOfAreaMethod(fuzzySet);

            case TYPE_LEFT_OF_MAX:
                return defuzzifyByLeftOfMaxMethod(fuzzySet);

            case TYPE_MEAN_OF_MAX:
                return defuzzifyByMeanOfMaxMethod(fuzzySet);

            case TYPE_RIGHT_OF_MAX:
                return defuzzifyByRightOfMaxMethod(fuzzySet);
            default:
                return Float.NaN;
        }

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

    /**
     * Defuzzifies fuzzy set by center-of-area method.
     * @param fuzzySet fuzzy set to be defuzzified
     * @return crisp value
     */
    private float defuzzifyByCenterOfAreaMethod(final FuzzySet fuzzySet) {
        if (fuzzySet != null) {

            MembershipFunction membershipFunction = fuzzySet.getMembershipFunction();
            if (membershipFunction != null) {
                for (Iterator<Float> it = membershipFunction.iterator(); it.hasNext();) {
                    float x1 = it.next().floatValue();
                    float y1 = membershipFunction.getDegreeOfMembership(x1);

                    if (it.hasNext()) {
                        float x2;
                        float y2;
                        float diff;
                        float slope;
                        float quaddiff;
                        float tridiff;
                        float zaehler = 0.0f;
                        float nenner = 0.0f;

                        while (it.hasNext()) {
                            x2 = it.next().floatValue();
                            y2 = membershipFunction.getDegreeOfMembership(x2);

                            slope = (y2 - y1) / (x2 - x1);
                            diff = y1 - (slope * x1);
                            // Hilfsrechnungen
                            quaddiff = (new Float((Math.pow(x2, 2.0) - Math.pow(x1, 2.0)))).floatValue();
                            tridiff = (new Float((Math.pow(x2, 3.0) - Math.pow(x1, 3.0)))).floatValue();
                            // Calculate moment
                            // zaehler += 0.33f * slope * tridiff + 0.5f * diff
                            // * quaddiff;
                            zaehler += (((float) (1.0 / 3.0) * slope * tridiff)
                                    + ((float) (1.0 / 2.0) * diff * quaddiff));
                            // Calculate trapez.
                            // nenner += 0.5f * slope * quaddiff + diff * (x2 - x1);
                            nenner += (((float) (1.0 / 2.0) * slope * quaddiff) + (diff * (x2 - x1)));
                            x1 = x2;
                            y1 = y2;
                        }

                        return FuzzyManager.round(zaehler / nenner);
                    }

                    return x1;
                }
            }
        }
        return Float.NaN;
    }

    /**
     * Defuzzifies fuzzy set by left-of-max method.
     * @param fuzzySet fuzzy set to be defuzzified
     * @return crisp value
     */
    private float defuzzifyByLeftOfMaxMethod(final FuzzySet fuzzySet) {
        float value = Float.NaN;
        if (fuzzySet != null) {

            MembershipFunction membershipFunction = fuzzySet.getMembershipFunction();
            if (membershipFunction != null) {
                float x;
                float dom;
                float maxDoM = 0.0f;

                for (Iterator<Float> it = membershipFunction.iterator(); it.hasNext();) {
                    x = it.next();
                    dom = membershipFunction.getDegreeOfMembership(x);

                    if (dom > maxDoM) {
                        maxDoM = dom;
                        value = x;

                        if (maxDoM == 1.0f) {
                            return x;
                        }
                    }
                }
            }
        }
        return value;
    }

    /**
     * Defuzzifies fuzzy set by mean-of-max method.
     * @param fuzzySet fuzzy set to be defuzzified
     * @return crisp value
     */
    private float defuzzifyByMeanOfMaxMethod(final FuzzySet fuzzySet) {
        float value = Float.NaN;
        if (fuzzySet != null) {

            MembershipFunction membershipFunction = fuzzySet.getMembershipFunction();
            if (membershipFunction != null) {
                value = ((defuzzifyByLeftOfMaxMethod(fuzzySet)
                        + defuzzifyByRightOfMaxMethod(fuzzySet)) / 2.0f);
            }
        }
        return value;
    }

    /**
     * Defuzzifies fuzzy set by right-of-max method.
     * @param fuzzySet fuzzy set to be defuzzified
     * @return crisp value
     */
    private float defuzzifyByRightOfMaxMethod(final FuzzySet fuzzySet) {
        float value = Float.NaN;
        if (fuzzySet != null) {

            MembershipFunction membershipFunction = fuzzySet.getMembershipFunction();
            if (membershipFunction != null) {
                float x;
                float dom;
                float maxDoM = 0.0f;

                for (Iterator<Float> it = membershipFunction.iterator(); it.hasNext();) {
                    x = it.next().floatValue();
                    dom = membershipFunction.getDegreeOfMembership(x);

                    if (dom >= maxDoM) {
                        maxDoM = dom;
                        value = x;
                    }
                }
            }
        }
        return value;
    }
}
