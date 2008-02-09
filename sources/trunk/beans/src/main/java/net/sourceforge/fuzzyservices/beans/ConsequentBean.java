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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;


/**
 * This class represents an then-clause (consequent) of a rule according to
 * JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.Consequent
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class ConsequentBean implements Serializable {
    
    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;
    
    //
    // Bound property names
    //
    
    /** Bound property name for <code>linguisticVariableName</code>. */
    public static final String LINGUISTIC_VARIABLE_NAME_PROPERTY
            = "linguisticVariableName";
    
    /** Bound property name for <code>linguisticTermName</code>. */
    public static final String LINGUISTIC_TERM_NAME_PROPERTY = "linguisticTermName";
    
    /** The name of a linguistic variable belongs to the consequent. */
    private String linguisticVariableName = null;
    
    /** The name of a linguistic term which is part of the linguistic variable. */
    private String linguisticTermName = null;
    
    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);
    
    /** Default <code>ConsequentBean</code> constructor. */
    public ConsequentBean() {
        // Do nothing
    }
    
    /**
     * Returns the name of a linguistic term belongs to the consequent.
     * @return the <code>linguisticTermName</code> property
     * @see #setLinguisticTermName
     */
    public String getLinguisticTermName() {
        return linguisticTermName;
    }
    
    /**
     * Returns the name of a linguistic variable belongs to the consequent.
     * @return the <code>linguisticVariableName</code> property
     * @see #setLinguisticVariableName
     */
    public String getLinguisticVariableName() {
        return linguisticVariableName;
    }
    
    /**
     * Sets the name of a linguistic term belongs to the consequent.
     * @param lingTermName The new value for the property.
     * @exception NullPointerException if <code>linguisticVariable</code> is
     * not defined yet
     * @see #getLinguisticTermName
     */
    public void setLinguisticTermName(String lingTermName) {
        String oldLinguisticTermName = this.linguisticTermName;
        this.linguisticTermName = lingTermName;
        propertyChangeSupport.firePropertyChange(LINGUISTIC_TERM_NAME_PROPERTY, oldLinguisticTermName, lingTermName);
    }
    
    /**
     * Sets the name of a linguistic variable belongs to this consequent.
     * Additionally, the <code>linguisticTermName</code> property is
     * set to <code>null</code>.
     * @param lingVariableName The new value for the property.
     * @see #getLinguisticVariableName
     */
    public void setLinguisticVariableName(String lingVariableName) {
        String oldLinguisticVariableName = this.linguisticVariableName;
        this.linguisticVariableName = lingVariableName;
        propertyChangeSupport.firePropertyChange(LINGUISTIC_VARIABLE_NAME_PROPERTY, oldLinguisticVariableName, lingVariableName);
        // Delete linguistic Term
        setLinguisticTermName(null);
    }
    
    /**
     * Adds a <code>PropertyChangeListener</code> to the listener list. The listener is registered for all properties. <p>
     * A <code>PropertyChangeEvent</code> will get fired in response to setting a bound property. <p>
     * @param listener  the <code>PropertyChangeListener</code> to be added
     */
    public synchronized void addPropertyChangeListener(final PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    
    /**
     * Removes a <code>PropertyChangeListener</code> from the listener list.
     * This removes a <code>PropertyChangeListener</code> that was registered for all properties.
     * @param listener  the <code>PropertyChangeListener</code> to be removed
     */
    public synchronized void removePropertyChangeListener(final PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}


