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

import net.sourceforge.fuzzyservices.core.defuzzification.CenterOfArea;
import net.sourceforge.fuzzyservices.core.defuzzification.LeftOfMax;
import net.sourceforge.fuzzyservices.core.defuzzification.MeanOfMax;
import net.sourceforge.fuzzyservices.core.defuzzification.RightOfMax;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;


/**
 * This class represents a defuzzificator according to JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.AbstractDefuzzificator
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class DefuzzificatorBean implements Serializable {

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
     *
     * @see #setType
     * @see net.sourceforge.fuzzyservices.core.defuzzification.CenterOfArea
     */
    public static final byte TYPE_CENTER_OF_AREA = 0;

    /**
     * Constant that defines the left-of-max method as <code>type</code>.
     *
     *
     * @see #setType
     * @see net.sourceforge.fuzzyservices.core.defuzzification.LeftOfMax
     */
    public static final byte TYPE_LEFT_OF_MAX = 1;

    /**
     * Constant that defines the mean-of-max method as <code>type</code>.
     *
     *
     * @see #setType
     * @see net.sourceforge.fuzzyservices.core.defuzzification.MeanOfMax
     */
    public static final byte TYPE_MEAN_OF_MAX = 2;

    /**
     * Constant that defines the right-of-max method as <code>type</code>.
     *
     *
     * @see #setType
     * @see net.sourceforge.fuzzyservices.core.defuzzification.RightOfMax
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
    private transient PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);

    /** Default <code>DefuzzificatorBean</code> constructor. */
    public DefuzzificatorBean() {
        // Do nothing
    }

    /**
     * Returns the type of this defuzzificator.
     * @return the <code>type</code> property
     * @see #setType
     */
    public byte getType() {
        return type;
    }

    /**
     * Sets the defuzzification type.
     * @param newType The new value for the property.
     * @see #getType
     */
    public void setType(byte newType) {
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
    public float defuzzify(FuzzySetBean fuzzySet) {
        switch (type) {
            case TYPE_CENTER_OF_AREA:
                return new CenterOfArea().defuzzify(FuzzyBeanUtils.convert(
                        fuzzySet));

            case TYPE_LEFT_OF_MAX:
                return new LeftOfMax().defuzzify(FuzzyBeanUtils.convert(
                        fuzzySet));

            case TYPE_MEAN_OF_MAX:
                return new MeanOfMax().defuzzify(FuzzyBeanUtils.convert(
                        fuzzySet));

            case TYPE_RIGHT_OF_MAX:
                return new RightOfMax().defuzzify(FuzzyBeanUtils.convert(
                        fuzzySet));
        }

        return Float.NaN;
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


