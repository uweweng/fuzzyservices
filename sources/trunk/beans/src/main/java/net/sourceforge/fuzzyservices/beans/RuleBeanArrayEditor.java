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

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Uwe Weng
 */
public class RuleBeanArrayEditor extends PropertyEditorSupport implements PropertyChangeListener {
    
    /** Creates a new instance of RuleBeanArrayEditor */
    public RuleBeanArrayEditor() {
    }
    
    /**
     * Gets the property value as text.
     * @return the property value as text.
     */
    public String getAsText() {
        return "RuleBeanArrayEditor";
    }
    
    /**
     * Paint a representation of the value into a given area of screen real estate.
     * @return a FactBeanArrayEditorPanel component
     */
    public Component getCustomEditor() {
        RuleBeanArrayEditorPanel p = new RuleBeanArrayEditorPanel();
        return p;
    }
    
    /**
     * Determines whether this property editor supports a custom editor.
     * It supports a custom editor.
     */
    public boolean supportsCustomEditor() {
        return true;
    }
    
    
    public void propertyChange(PropertyChangeEvent evt) {
    }
    
}
