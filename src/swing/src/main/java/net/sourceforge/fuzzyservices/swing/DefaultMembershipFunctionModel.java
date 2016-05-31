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
package net.sourceforge.fuzzyservices.swing;

import java.util.HashMap;
import java.util.Set;


/**
 *
 * @author Uwe Weng
 */
public class DefaultMembershipFunctionModel
    extends AbstractMembershipFunctionModel {
    /**
     * Holds value of property enabled.
     */
    private HashMap points = new HashMap();

    /** Creates a new instance of DefaultMembershipFunctionModel */
    public DefaultMembershipFunctionModel() {
        super();
        setEnabled(true);
    }

    public final float getDefuzzifiedValue() {
        return Float.NaN;
    }

    public float[] getXValues() {
        Set s = points.keySet();
        Object[] o = s.toArray();
        int size = s.size();
        float[] f = new float[size];

        for (int i = 0; i < size; i++) {
            f[i] = ((Float) o[i]).floatValue();
        }

        return f;
    }

    public final float getDegreeOfMembership(float x) {
        Float f = (Float) points.get(new Float(x));

        if (f != null) {
            return f.floatValue();
        }

        return Float.NaN;
    }

    public final void addPoint(float x, float y) throws IllegalArgumentException {
        points.put(new Float(x), new Float(y));
        fireStateChanged(this);
    }

    public final void removePointAt(float x) {
        points.remove(new Float(x));
        fireStateChanged(this);
    }
}
