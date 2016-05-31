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

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.fuzzyservices.beans.swing.MembershipFunctionBeanModel;
import net.sourceforge.fuzzyservices.swing.MembershipFunctionModel;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.beans.PropertyEditorSupport;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

/**
 * Editor class for membership function properties.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class MembershipFunctionEditor
        extends PropertyEditorSupport implements ChangeListener {

    /**
     * It is paintable.
     * @return <code>true</code>
     */
    @Override
    public final boolean isPaintable() {
        return true;
    }

    @Override
    public final void paintValue(final Graphics gfx, final Rectangle box) {
        String text = "";
        MembershipFunction membershipFunction = (MembershipFunction) getValue();
        if (membershipFunction != null) {
            text = FuzzyResourceManager.getString(this, "MEMBERSHIP_FUNCTION_EDITOR_VALUE",
                    new Object[]{membershipFunction.getXValues().length});
        }
        gfx.drawString(text, box.x, box.y + gfx.getFontMetrics().getHeight());
    }

    /**
     * This property value has no text.
     * @return <code>null</code>
     */
    @Override
    public String getAsText() {
        return null;
    }

    @Override
    public String getJavaInitializationString() {
        MembershipFunction membershipFunction = (MembershipFunction) getValue();
        return JavaInitializationStringFormatter.getJavaInitializationStringForMembershipFunction(membershipFunction);
    }

    /**
     * @return a <code>MembershipFunctionEditorPanel</code> component
     */
    @Override
    public Component getCustomEditor() {
        MembershipFunctionModel model = new MembershipFunctionBeanModel((MembershipFunction) getValue());
        model.addChangeListener(this);
        model.setEnabled(true);
        MembershipFunctionEditorPanel p = new MembershipFunctionEditorPanel(model);
        return p;
    }

    /**
     * It supports a custom editor.
     * @return <code>true</code>
     */
    @Override
    public final boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public final void stateChanged(ChangeEvent e) {
        MembershipFunction membershipFunction = null;
        MembershipFunctionModel model = (MembershipFunctionModel) e.getSource();
        float[] xValues = model.getXValues();

        if ((xValues != null) && (xValues.length > 0)) {
            membershipFunction = new MembershipFunction();

            for (int i = 0; i < xValues.length; i++) {
                try {
                    membershipFunction.setDegreeOfMembership(xValues[i], model.getDegreeOfMembership(xValues[i]));
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(MembershipFunctionEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        setValue(membershipFunction);
    }
}
