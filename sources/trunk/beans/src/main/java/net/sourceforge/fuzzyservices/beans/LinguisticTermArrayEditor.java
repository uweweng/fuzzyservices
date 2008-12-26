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

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditorSupport;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.fuzzyservices.beans.swing.LinguisticVariableBeanModel;
import net.sourceforge.fuzzyservices.swing.LinguisticVariableModel;
import net.sourceforge.fuzzyservices.swing.LinguisticVariableModelEvent;
import net.sourceforge.fuzzyservices.swing.LinguisticVariableModelListener;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

/**
 * Editor class for an array of linguistic terms.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class LinguisticTermArrayEditor extends PropertyEditorSupport
        implements LinguisticVariableModelListener {

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
        LinguisticTerm[] linguisticTerms = (LinguisticTerm[]) getValue();
        if (linguisticTerms != null) {
            text = FuzzyResourceManager.getString(this, "LINGUISTIC_TERM_ARRAY_EDITOR_VALUE",
                    new Object[]{linguisticTerms.length});
        }
        gfx.drawString(text, box.x, box.y + gfx.getFontMetrics().getHeight());
    }

    @Override
    public String getJavaInitializationString() {
        LinguisticTerm[] linguisticTerms = (LinguisticTerm[]) getValue();
        StringBuffer str = new StringBuffer();
        if (linguisticTerms != null) {
            str.append("new LinguisticTerm[] {");
            for (int i = 0; i < linguisticTerms.length; i++) {
                LinguisticTerm linguisticTerm = linguisticTerms[i];
                if (i > 0) {
                    str.append(", ");
                }
                str.append(
                        JavaInitializationStringFormatter.getJavaInitializationStringForLinguisticTerm(linguisticTerm));
            }
            str.append("}");
        } else {
            str.append("null");
        }
        return str.toString();
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
     * @return a <code>LinguisticTermArrayEditorPanel</code> component
     */
    @Override
    public Component getCustomEditor() {
        LinguisticVariable lv = new LinguisticVariable();
        try {
            lv.setLinguisticTerms((LinguisticTerm[]) getValue());
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(LinguisticTermArrayEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(LinguisticTermArrayEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        LinguisticVariableModel model = new LinguisticVariableBeanModel(lv);
        model.addLinguisticVariableModelListener(this);
        LinguisticTermArrayEditorPanel p = new LinguisticTermArrayEditorPanel(model);
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
    public void linguisticVariableChanged(final LinguisticVariableModelEvent event) {
        firePropertyChange();
    }
}
