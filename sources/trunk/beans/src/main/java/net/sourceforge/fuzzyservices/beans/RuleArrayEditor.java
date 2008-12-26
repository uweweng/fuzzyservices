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
import java.beans.PropertyEditorSupport;
import net.sourceforge.fuzzyservices.beans.swing.RuleBaseBeanModel;
import net.sourceforge.fuzzyservices.swing.RuleBaseModel;
import net.sourceforge.fuzzyservices.swing.RuleBaseModelEvent;
import net.sourceforge.fuzzyservices.swing.RuleBaseModelListener;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

/**
 * Editor class for an array of rules.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class RuleArrayEditor extends PropertyEditorSupport
        implements RuleBaseModelListener {

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
        Rule[] rules = (Rule[]) getValue();
        if (rules != null) {
            text = FuzzyResourceManager.getString(this, "RULE_ARRAY_EDITOR_VALUE",
                    new Object[]{rules.length});
        }
        gfx.drawString(text, box.x, box.y + gfx.getFontMetrics().getHeight());
    }

    @Override
    public String getJavaInitializationString() {
        Rule[] rules = (Rule[]) getValue();
        StringBuffer str = new StringBuffer();
        if (rules != null) {
            str.append("new Rule[] {");
            for (int i = 0; i < rules.length; i++) {
                Rule rule = rules[i];
                if (i > 0) {
                    str.append(", ");
                }
                str.append(JavaInitializationStringFormatter.getJavaInitializationStringForRule(rule));
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
     * @return a <code>RuleArrayEditorPanel</code> component
     */
    @Override
    public Component getCustomEditor() {
        RuleBase rb = new RuleBase();
        rb.setRules((Rule[]) getValue());
        RuleBaseModel model = new RuleBaseBeanModel(rb);
        model.addRuleBaseModelListener(this);
        RuleArrayEditorPanel p = new RuleArrayEditorPanel(model);
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
    public void ruleBaseChanged(final RuleBaseModelEvent e) {
        firePropertyChange();
    }
}
