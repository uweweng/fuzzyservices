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


/**
 *
 * @author Uwe Weng
 */
public class SimpleBean {
    /**
     * Holds value of property attr.
     */
    private String[] attr;

    /**
     * Utility field used by bound properties.
     */
    private java.beans.PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    /**
     * Utility field used by constrained properties.
     */
    private java.beans.VetoableChangeSupport vetoableChangeSupport = new java.beans.VetoableChangeSupport(this);

    /**
     * Holds value of property simpleAttribute.
     */
    private SimpleAttribute simpleAttribute = new SimpleAttribute();

    /**
     * Holds value of property text.
     */
    private String text;

    /**
     * Holds value of property simpleAttrs.
     */
    private SimpleAttribute[] simpleAttrs;

    /** Creates a new instance of SimpleBean */
    public SimpleBean() {
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     * @param l The listener to add.
     */
    public final void addPropertyChangeListener(java.beans.PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    /**
     * Removes a PropertyChangeListener from the listener list.
     * @param l The listener to remove.
     */
    public final void removePropertyChangeListener(
        java.beans.PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    /**
     * Adds a VetoableChangeListener to the listener list.
     * @param l The listener to add.
     */
    public final void addVetoableChangeListener(java.beans.VetoableChangeListener l) {
        vetoableChangeSupport.addVetoableChangeListener(l);
    }

    /**
     * Removes a VetoableChangeListener from the listener list.
     * @param l The listener to remove.
     */
    public final void removeVetoableChangeListener(
        java.beans.VetoableChangeListener l) {
        vetoableChangeSupport.removeVetoableChangeListener(l);
    }

    /**
     * Indexed getter for property attr.
     * @param index Index of the property.
     * @return Value of the property at <CODE>index</CODE>.
     */
    public java.lang.String getAttr(int index) {
        return this.attr[index];
    }

    /**
     * Getter for property attr.
     * @return Value of property attr.
     */
    public java.lang.String[] getAttr() {
        return this.attr;
    }

    /**
     * Indexed setter for property attr.
     * @param index Index of the property.
     * @param attr New value of the property at <CODE>index</CODE>.
     *
     * @throws PropertyVetoException if some vetoable listeners reject the new value
     */
    public final void setAttr(int index, java.lang.String attr)
        throws java.beans.PropertyVetoException {
        String oldAttr = this.attr[index];
        this.attr[index] = attr;

        try {
            vetoableChangeSupport.fireVetoableChange("attr", null, null);
        } catch (java.beans.PropertyVetoException vetoException) {
            this.attr[index] = oldAttr;
            throw vetoException;
        }

        propertyChangeSupport.firePropertyChange("attr", null, null);
    }

    /**
     * Setter for property attr.
     * @param attr New value of property attr.
     *
     * @throws PropertyVetoException if some vetoable listeners reject the new value
     */
    public final void setAttr(java.lang.String[] attr)
        throws java.beans.PropertyVetoException {
        String[] oldAttr = this.attr;
        vetoableChangeSupport.fireVetoableChange("attr", oldAttr, attr);
        this.attr = attr;
        propertyChangeSupport.firePropertyChange("attr", oldAttr, attr);
    }

    /**
     * Getter for property simpleAttribute.
     * @return Value of property simpleAttribute.
     */
    public net.sourceforge.fuzzyservices.beans.SimpleAttribute getSimpleAttribute() {
        return this.simpleAttribute;
    }

    /**
     * Setter for property simpleAttribute.
     * @param simpleAttribute New value of property simpleAttribute.
     */
    public final void setSimpleAttribute(
        net.sourceforge.fuzzyservices.beans.SimpleAttribute simpleAttribute) {
        this.simpleAttribute = simpleAttribute;
    }

    /**
     * Getter for property text.
     * @return Value of property text.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Setter for property text.
     * @param text New value of property text.
     */
    public final void setText(String text) {
        String oldText = this.text;
        this.text = text;
        propertyChangeSupport.firePropertyChange("text", oldText, text);
    }

    /**
     * Indexed getter for property simpleAttrs.
     * @param index Index of the property.
     * @return Value of the property at <CODE>index</CODE>.
     */
    public SimpleAttribute getSimpleAttrs(int index) {
        return this.simpleAttrs[index];
    }

    /**
     * Getter for property simpleAttrs.
     * @return Value of property simpleAttrs.
     */
    public SimpleAttribute[] getSimpleAttrs() {
        return this.simpleAttrs;
    }

    /**
     * Indexed setter for property simpleAttrs.
     * @param index Index of the property.
     * @param simpleAttrs New value of the property at <CODE>index</CODE>.
     *
     * @throws PropertyVetoException if some vetoable listeners reject the new value
     */
    public final void setSimpleAttrs(int index, SimpleAttribute simpleAttrs)
        throws java.beans.PropertyVetoException {
        SimpleAttribute oldSimpleAttrs = this.simpleAttrs[index];
        this.simpleAttrs[index] = simpleAttrs;

        try {
            vetoableChangeSupport.fireVetoableChange("simpleAttrs", null, null);
        } catch (java.beans.PropertyVetoException vetoException) {
            this.simpleAttrs[index] = oldSimpleAttrs;
            throw vetoException;
        }

        propertyChangeSupport.firePropertyChange("simpleAttrs", null, null);
    }

    /**
     * Setter for property simpleAttrs.
     * @param simpleAttrs New value of property simpleAttrs.
     *
     * @throws PropertyVetoException if some vetoable listeners reject the new value
     */
    public final void setSimpleAttrs(SimpleAttribute[] simpleAttrs)
        throws java.beans.PropertyVetoException {
        SimpleAttribute[] oldSimpleAttrs = this.simpleAttrs;
        vetoableChangeSupport.fireVetoableChange("simpleAttrs", oldSimpleAttrs,
            simpleAttrs);
        this.simpleAttrs = simpleAttrs;
        propertyChangeSupport.firePropertyChange("simpleAttrs", oldSimpleAttrs,
            simpleAttrs);
    }
}
