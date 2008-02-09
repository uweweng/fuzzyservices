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
import java.beans.PropertyEditorSupport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.sourceforge.fuzzyservices.beans.swing.MembershipFunctionBeanModel;
import net.sourceforge.fuzzyservices.swing.MembershipFunctionModel;

/**
 *
 * @author Uwe Weng
 */
public class MembershipFunctionPointBeanArrayEditor extends PropertyEditorSupport implements ChangeListener {
    
    /**
     * It is paintable.
     * @return <CODE>true</CODE>
     */
    public boolean isPaintable() {
        return true;
    }

    public void paintValue(Graphics gfx, Rectangle box) {
        String text = "MembershipFunctionBeanArrayEditor";
        if (text != null) {
            gfx.drawString("Test456", box.x+1, box.y+10);
        }
    }

    /**
     * This property value has no text.
     * @return <CODE>null</CODE>
     */
    public String getAsText() {
        return null;
    }
    
    public String getJavaInitializationString() {
        // TODO Aus dem Objekt Source Code zusammensetzen, damit der Zustand im Code des Nutzers persistiert wird
	return "null";
    }

    /**
     * @return a MembershipFunctionBeanArrayEditorPanel component
     */
    public Component getCustomEditor() {
        MembershipFunctionModel beanModel = new MembershipFunctionBeanModel((MembershipFunctionPointBean[])getValue());
        beanModel.addChangeListener(this);
        beanModel.setEnabled(true);
        MembershipFunctionPointBeanArrayEditorPanel p = new MembershipFunctionPointBeanArrayEditorPanel();
        p.setMembershipFunctionModel(beanModel);
        return p;
    }
    
    /**
     * It supports a custom editor.
     * @return <CODE>true</CODE>
     */
    public boolean supportsCustomEditor() {
        return true;
    }
    
    public void stateChanged(ChangeEvent e) {
        MembershipFunctionPointBean[] points = null;
        MembershipFunctionModel model = (MembershipFunctionModel)e.getSource();
        float[] xValues = model.getXValues();
        if ((xValues != null) && (xValues.length > 0)) {
            points = new MembershipFunctionPointBean[xValues.length];
            for (int i = 0; i < points.length; i++) {
                points[i] = new MembershipFunctionPointBean(xValues[i], model.getDegreeOfMembership(xValues[i]));
            }
        }
        setValue(points);
    }
}
