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
package net.sourceforge.fuzzyservices.beans.swing;

import java.beans.PropertyVetoException;
import net.sourceforge.fuzzyservices.beans.MembershipFunctionPointBean;
import net.sourceforge.fuzzyservices.swing.AbstractMembershipFunctionModel;

/**
 *
 *
 * @author Uwe Weng
 */
public class MembershipFunctionBeanModel extends AbstractMembershipFunctionModel {
    
    /** The underlying membership function is described by an array of points.*/
    private MembershipFunctionPointBean[] membershipFunction = null;
    
    /** Creates a new instance of MembershipFunctionBeanModel
     * @param membershipFunction 
     */
    public MembershipFunctionBeanModel(MembershipFunctionPointBean[] membershipFunction) {
        this.membershipFunction = membershipFunction;
    }
    
    @Override
    public float getDefuzzifiedValue() {
        return Float.NaN;
    }
    
    @Override
    public float[] getXValues() {
        float[] xValues = null;
        if (membershipFunction != null) {
            xValues = new float[membershipFunction.length];
            for (int i = 0; i < xValues.length; i++) {
                if (membershipFunction[i] != null) {
                    xValues[i] = membershipFunction[i].getX();
                }
            }
        }
        return xValues;
    }
    
    @Override
    public float getDegreeOfMembership(float x) {
        if (membershipFunction != null) {
            for (int i = 0; i < membershipFunction.length; i++) {
                if ((membershipFunction[i] != null) && (membershipFunction[i].getX() == x)) {
                    return membershipFunction[i].getDegreeOfMembership();
                }
            }
        }
        return Float.NaN;
    }
    
    @Override
    public void addPoint(float x, float y) throws IllegalArgumentException {
        if (membershipFunction != null) {
            for (int i = 0; i < membershipFunction.length; i++) {
                if ((membershipFunction[i] != null) && (membershipFunction[i].getX() == x)) {
                    if (membershipFunction[i].getDegreeOfMembership() != y) {
                        try {
                            membershipFunction[i].setDegreeOfMembership(y);
                        } catch (PropertyVetoException ex) {
                            throw new IllegalArgumentException(ex.getLocalizedMessage());
                        }
                        fireStateChanged(this);
                        return;
                    }
                }
            }
        }
        MembershipFunctionPointBean[] newMembershipFunction = new MembershipFunctionPointBean[(membershipFunction != null) ? membershipFunction.length + 1 : 1];
        for (int i = 0; i < newMembershipFunction.length - 1; i++) {
            newMembershipFunction[i] = membershipFunction[i];
        }
        newMembershipFunction[newMembershipFunction.length - 1] = new MembershipFunctionPointBean(x, y);
        membershipFunction = newMembershipFunction;
        fireStateChanged(this);
    }
    
    @Override
    public void removePointAt(float x) {
        if (membershipFunction != null) {
            for (int i = 0; i < membershipFunction.length; i++) {
                if ((membershipFunction[i] != null) && (membershipFunction[i].getX() == x)) {
                    membershipFunction[i] = null;
                    fireStateChanged(this);
                }
            }
        }
    }
    
}
