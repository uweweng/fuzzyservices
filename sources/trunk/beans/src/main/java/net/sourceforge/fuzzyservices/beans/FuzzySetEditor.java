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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.sourceforge.fuzzyservices.beans.swing.MembershipFunctionBeanModel;
import net.sourceforge.fuzzyservices.swing.MembershipFunctionModel;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

/**
 * Editor class for fuzzy set properties.
 * 
 * @version 1.0
 * @author Uwe Weng
 */
public class FuzzySetEditor extends PropertyEditorSupport
        implements ChangeListener {

    /**
     * Defuzzificator for expressing the value in property sheet. The value is defuzzified by the Center-of-area method
     * @see Defuzzificator
     */
    private Defuzzificator defuzzificator = new Defuzzificator(Defuzzificator.TYPE_CENTER_OF_AREA);

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
        FuzzySet fs = (FuzzySet) getValue();
        if (fs != null) {
            text = FuzzyResourceManager.getString(this, "FUZZY_SET_EDITOR_VALUE",
                    new Object[]{defuzzificator.defuzzify(fs)});
        }
        gfx.drawString(text, box.x, box.y + gfx.getFontMetrics().getHeight());
    }

    @Override
    public String getJavaInitializationString() {
        FuzzySet fuzzySet = (FuzzySet) getValue();
        return JavaInitializationStringFormatter.getJavaInitializationStringForFuzzySet(fuzzySet);
    }

    /**
     * This property value has no text.
     * @return <code>null</code>
     */
    @Override
    public String getAsText() {
        return null;
    }

    /**
     * @return a <code>FuzzySetEditorPanel</code> component
     */
    @Override
    public Component getCustomEditor() {
        MembershipFunctionModel model = 
                new MembershipFunctionBeanModel(((FuzzySet) getValue()).getMembershipFunction());
        model.addChangeListener(this);
        model.setEnabled(true);
        FuzzySetEditorPanel p = new FuzzySetEditorPanel(model);
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
    public void stateChanged(final ChangeEvent e) {
        firePropertyChange();
    }
}
