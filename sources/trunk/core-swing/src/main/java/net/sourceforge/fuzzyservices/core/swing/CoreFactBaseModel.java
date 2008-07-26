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
package net.sourceforge.fuzzyservices.core.swing;

import net.sourceforge.fuzzyservices.core.Fact;
import net.sourceforge.fuzzyservices.core.FactBase;
import net.sourceforge.fuzzyservices.swing.AbstractFactBaseModel;
import net.sourceforge.fuzzyservices.swing.FactModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * CoreFactBaseModel
 *
 * @author Uwe Weng
 */
public class CoreFactBaseModel extends AbstractFactBaseModel {
    private FactBase factBase = new FactBase();

    public CoreFactBaseModel() {
    }

    public CoreFactBaseModel(FactBase factBase) {
        this.factBase = factBase;
    }

    @Override
    public String getName() {
        return (factBase != null) ? factBase.getName() : null;
    }

    @Override
    public final void setName(String name) {
        if (factBase != null) {
            factBase.setName(name);
            fireNameChanged(this);
        }
    }

    @Override
    public Collection<FactModel> getFacts() {
        if (factBase != null) {
            Collection<FactModel> col = new ArrayList<FactModel>();

            for (Iterator<Fact> it = factBase.iterator(); it.hasNext();) {
                Fact fact = it.next();
                col.add(new CoreFactModel(fact));
            }

            return col;
        }

        return null;
    }
}
