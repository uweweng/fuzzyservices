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
import net.sourceforge.fuzzyservices.beans.swing.OperatorBeanModel;
import net.sourceforge.fuzzyservices.swing.OperatorModel;
import net.sourceforge.fuzzyservices.swing.OperatorModelEvent;
import net.sourceforge.fuzzyservices.swing.OperatorModelListener;

/**
 * Editor class for (fuzzy) operator properties.
 * 
 * @version 1.0
 * @author Uwe Weng
 */
public class OperatorEditor extends PropertyEditorSupport implements OperatorModelListener {

    /**
     * This property value has no text.
     * @return <code>null</code>
     */
    @Override
    public String getAsText() {
        return null;
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
    public String getJavaInitializationString() {
        Operator operator = (Operator) getValue();
        return JavaInitializationStringFormatter.getJavaInitializationStringForOperator(operator);
    }

    /**
     * @return a <code>OperatorEditorPanel</code> component
     */
    @Override
    public java.awt.Component getCustomEditor() {
        OperatorModel model = new OperatorBeanModel((Operator) getValue());
        model.addOperatorModelListener(this);
        OperatorEditorPanel p = new OperatorEditorPanel(model);
        return p;
    }

    @Override
    public void operatorChanged(final OperatorModelEvent e) {
        firePropertyChange();
    }
}
