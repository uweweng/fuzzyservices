/*
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

import net.sourceforge.fuzzyservices.beans.FactBase;
import net.sourceforge.fuzzyservices.beans.Fact;
import net.sourceforge.fuzzyservices.swing.AbstractFactBaseModel;
import net.sourceforge.fuzzyservices.swing.FactModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.Collection;


/**
 * FactBaseBeanModel
 *
 * @author Uwe Weng
 */
public class FactBaseBeanModel extends AbstractFactBaseModel
    implements PropertyChangeListener {
    private FactBase factBase;

    public FactBaseBeanModel() {
        factBase = new FactBase();
        factBase.addPropertyChangeListener(this);
    }

    public FactBaseBeanModel(FactBase factBase) {
        this.factBase = factBase;

        if (this.factBase != null) {
            this.factBase.addPropertyChangeListener(this);
        }
    }

    @Override
    public String getName() {
        return (factBase != null) ? factBase.getName() : null;
    }

    @Override
    public final void setName(String name) {
        if (factBase != null) {
            factBase.setName(name);
        }
    }

    @Override
    public Collection<FactModel> getFacts() {
        if (factBase != null) {
            Fact[] facts = factBase.getFacts();

            if ((facts != null) && (facts.length > 0)) {
                Collection<FactModel> col = new ArrayList<FactModel>(facts.length);

                for (int i = 0; i < facts.length; i++) {
                    col.add(new FactBeanModel(facts[i]));
                }

                return col;
            }
        }

        return null;
    }

    @Override
    public final void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        if (FactBase.NAME_PROPERTY.equals(propertyName) == true) {
            fireNameChanged(this);

            return;
        }

        if (FactBase.FACTS_PROPERTY.equals(propertyName) == true) {
            fireFactsChanged(this);

            return;
        }
    }
}
