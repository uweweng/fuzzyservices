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
import net.sourceforge.fuzzyservices.beans.swing.FactBaseBeanModel;
import net.sourceforge.fuzzyservices.swing.FactBaseModel;
import net.sourceforge.fuzzyservices.swing.FactBaseModelEvent;
import net.sourceforge.fuzzyservices.swing.FactBaseModelListener;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

/**
 * Editor class for an array of facts.
 * 
 * @version 1.0
 * @author Uwe Weng
 */
public class FactArrayEditor extends PropertyEditorSupport
        implements FactBaseModelListener {

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
        Fact[] facts = (Fact[]) getValue();
        if (facts != null) {
            text = FuzzyResourceManager.getString(this, "FACT_ARRAY_EDITOR_VALUE",
                    new Object[]{facts.length});
        }
        gfx.drawString(text, box.x, box.y + gfx.getFontMetrics().getHeight());
    }

    @Override
    public String getJavaInitializationString() {
        Fact[] facts = (Fact[]) getValue();
        StringBuffer str = new StringBuffer();
        if (facts != null) {
            str.append("new Fact[] {");
            for (int i = 0; i < facts.length; i++) {
                Fact fact = facts[i];
                if (i > 0) {
                    str.append(", ");
                }
                str.append(JavaInitializationStringFormatter.getJavaInitializationStringForFact(fact));
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
     * @return a <code>FactArrayEditorPanel</code> component
     */
    @Override
    public Component getCustomEditor() {
        FactBase fb = new FactBase();
        fb.setFacts((Fact[]) getValue());
        FactBaseModel model = new FactBaseBeanModel(fb);
        model.addFactBaseModelListener(this);
        FactArrayEditorPanel p = new FactArrayEditorPanel(model);
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
    public void factBaseChanged(final FactBaseModelEvent e) {
        firePropertyChange();
    }
}
