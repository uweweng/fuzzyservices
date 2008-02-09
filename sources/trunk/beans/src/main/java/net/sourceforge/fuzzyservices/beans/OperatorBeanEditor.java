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

import java.beans.PropertyEditorSupport;

/**
 *
 * @author Uwe Weng
 */
public class OperatorBeanEditor extends PropertyEditorSupport {
    
    /** Creates a new instance of OperatorBeanEditor */
    public OperatorBeanEditor() {
    }
    
    /**
     * Gets the property value as text.
     * This property value has no text.
     * @return null
     */
    public String getAsText() {
        return null;
    }
    
    /**
     * This propertyEditor can provide a custom editor.
     *
     * @return  True
     */
    public boolean supportsCustomEditor() {
        return true;
    }
    
    /**
     * A PropertyEditor may chose to make available a full custom Component
     * that edits its property value.  It is the responsibility of the
     * PropertyEditor to hook itself up to its editor Component itself and
     * to report property value changes by firing a PropertyChange event.
     * <P>
     * The higher-level code that calls getCustomEditor may either embed
     * the Component in some larger property sheet, or it may put it in
     * its own individual dialog, or ...
     *
     * @return A java.awt.Component that will allow a human to directly
     *      edit the current property value.
     */
    public java.awt.Component getCustomEditor() {
        return new OperatorBeanEditorPanel((OperatorBean)getValue());
    }
    
}
