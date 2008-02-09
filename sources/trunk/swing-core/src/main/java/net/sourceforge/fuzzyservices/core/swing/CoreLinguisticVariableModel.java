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

import java.util.ArrayList;
import java.util.Iterator;
import net.sourceforge.fuzzyservices.core.LinguisticVariable;
import net.sourceforge.fuzzyservices.swing.AbstractLinguisticVariableModel;
import net.sourceforge.fuzzyservices.swing.MembershipFunctionModel;

/**
 * CoreLinguisticVariableModel
 *
 * @author Uwe Weng
 */
public class CoreLinguisticVariableModel extends AbstractLinguisticVariableModel {

    private LinguisticVariable linguisticVariable = new LinguisticVariable();
    private ArrayList<String> linguisticTermNames = new ArrayList<String>();

    public CoreLinguisticVariableModel() {
    }

    public CoreLinguisticVariableModel(LinguisticVariable linguisticVariable) {
        this.linguisticVariable = linguisticVariable;
        if (this.linguisticVariable != null) {
            for (Iterator<String> it = linguisticVariable.getNames(); it.hasNext();) {
                String name = it.next();
                linguisticTermNames.add(name);
            }
        }
    }

    @Override
    public String getName() {
        return (linguisticVariable != null) ? linguisticVariable.getName() : null;
    }

    @Override
    public void setName(String name) {
        if (linguisticVariable != null) {
            linguisticVariable.setName(name);
            fireNameChanged(this);
        }
    }

    @Override
    public int size() {
        return (linguisticTermNames != null) ? linguisticTermNames.size() : null;
    }

    @Override
    public MembershipFunctionModel getMembershipFunctionOfLinguisticTermAt(int index) {
        return ((linguisticTermNames != null) && (linguisticVariable != null)) ? new CoreMembershipModel(linguisticVariable.getFuzzySet(linguisticTermNames.get(index))) : null;
    }

    @Override
    public String getNameOfLinguisticTermAt(int index) {
        return (linguisticTermNames != null) ? linguisticTermNames.get(index) : null;
    }
}
