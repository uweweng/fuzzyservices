/*******************************************************************************
 *
 *  Copyright (C) 2008  Uwe Weng
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
package net.sourceforge.fuzzyservices.beans.swing;

import net.sourceforge.fuzzyservices.beans.Fact;
import net.sourceforge.fuzzyservices.swing.AbstractFactModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * FactBeanModel
 *
 * @author Uwe Weng
 */
public class FactBeanModel extends AbstractFactModel
    implements PropertyChangeListener {
    private Fact fact;

    public FactBeanModel() {
        fact = new Fact();
        fact.addPropertyChangeListener(this);
    }

    public FactBeanModel(Fact fact) {
        this.fact = fact;

        if (this.fact != null) {
            this.fact.addPropertyChangeListener(this);
        }
    }

    @Override
    public final void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        if (Fact.VALUE_PROPERTY.equals(propertyName) == true) {
            fireValueChanged(this);

            return;
        }

        if (Fact.LINGUISTIC_VARIABLE_NAME_PROPERTY.equals(propertyName) == true) {
            fireLinguisticVariableChanged(this);

            return;
        }
    }
}