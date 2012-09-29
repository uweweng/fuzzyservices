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

import net.sourceforge.fuzzyservices.beans.LinguisticVariable;
import net.sourceforge.fuzzyservices.swing.AbstractLinguisticVariableModel;
import net.sourceforge.fuzzyservices.swing.MembershipFunctionModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * LinguisticVariableBeanModel
 *
 * @author Uwe Weng
 */
public class LinguisticVariableBeanModel extends AbstractLinguisticVariableModel
    implements PropertyChangeListener {
    private LinguisticVariable linguisticVariable;

    public LinguisticVariableBeanModel() {
        linguisticVariable = new LinguisticVariable();
        linguisticVariable.addPropertyChangeListener(this);
    }

    public LinguisticVariableBeanModel(
        LinguisticVariable linguisticVariable) {
        this.linguisticVariable = linguisticVariable;

        if (this.linguisticVariable != null) {
            this.linguisticVariable.addPropertyChangeListener(this);
        }
    }

    @Override
    public String getName() {
        return (linguisticVariable != null) ? linguisticVariable.getName() : null;
    }

    @Override
    public final void setName(String name) {
        if (linguisticVariable != null) {
            try {
                linguisticVariable.setName(name);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(LinguisticVariableBeanModel.class.getName())
                      .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public int size() {
        return (linguisticVariable != null)
        ? linguisticVariable.getLinguisticTerms().length : 0;
    }

    @Override
    public MembershipFunctionModel getMembershipFunctionOfLinguisticTermAt(
        int index) {
        return (linguisticVariable != null)
        ? new MembershipFunctionBeanModel(linguisticVariable.getLinguisticTerms(
                index).getFuzzySet().getMembershipFunction()) : null;
    }

    @Override
    public String getNameOfLinguisticTermAt(int index) {
        return (linguisticVariable != null)
        ? linguisticVariable.getLinguisticTerms(index).getName() : null;
    }

    @Override
    public final void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        if (LinguisticVariable.NAME_PROPERTY.equals(propertyName) == true) {
            fireNameChanged(this);

            return;
        }

        if (LinguisticVariable.LINGUISTIC_TERMS_PROPERTY.equals(
                    propertyName) == true) {
            fireLinguisticTermsChanged(this);

            return;
        }
    }
}
