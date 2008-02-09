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
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditorSupport;
import java.beans.PropertyEditor;

/**
 *
 * @author Uwe Weng
 */
public class SimpleAttributeEditor extends PropertyEditorSupport implements PropertyChangeListener {
    
   
    /**
     * Creates a new instance of SimpleAttributeEditor

    public SimpleAttributeEditor() {
        super();
    }
     */
    public boolean isPaintable() {
        return true;  // true;
    }

    public void paintValue(Graphics gfx, Rectangle box) {
        String text = ((SimpleAttribute)getValue()).getText();
        if (text != null) {
            gfx.drawString(text, box.x+1, box.y+10);
        }
    }

    public String getAsText() {
        return null; //((SimpleAttribute)getValue()).getText();
    }
    public Component getCustomEditor() {
        SimpleAttributePanel p = new SimpleAttributePanel((SimpleAttribute)getValue());
        p.addPropertyChangeListener("SIMPLETEXT", this);
        return p;
    }

    public boolean supportsCustomEditor() {
        return true;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        firePropertyChange();
    }
}
