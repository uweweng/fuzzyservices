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
package net.sourceforge.fuzzyservices.beans.swing;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.fuzzyservices.swing.AbstractMembershipFunctionModel;

import net.sourceforge.fuzzyservices.beans.MembershipFunction;

/**
 *
 *
 * @author Uwe Weng
 */
public class MembershipFunctionBeanModel extends AbstractMembershipFunctionModel {

    /** The source of this model.*/
    private MembershipFunction membershipFunction = null;

    /**
     * Creates a new instance of MembershipFunctionBeanModel.
     * @param membershipFunction the source of this model
     */
    public MembershipFunctionBeanModel(final MembershipFunction membershipFunction) {
        this.membershipFunction = membershipFunction;
    }

    @Override
    public float[] getXValues() {
        return (membershipFunction != null) ? membershipFunction.getXValues()
                : null;
    }

    @Override
    public final float getDegreeOfMembership(final float x) {
        return (membershipFunction != null)
                ? membershipFunction.getDegreeOfMembership(x) : Float.NaN;
    }

    @Override
    public final void addPoint(final float x, final float y) {
        if (membershipFunction != null) {
            try {
                membershipFunction.setDegreeOfMembership(x, y);
                fireStateChanged(this);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MembershipFunctionBeanModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public final void removePointAt(final float x) {
        if (membershipFunction != null) {
            try {
                membershipFunction.remove(x);
                fireStateChanged(this);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MembershipFunctionBeanModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public final float getDefuzzifiedValue() {
        return Float.NaN;
    }
}
