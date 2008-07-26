/*******************************************************************************
 *
 *  Copyright (C) 2007  Uwe Weng
 *
 *  This file is part of JFuzzy, a library for processing fuzzy information.
 *
 *  JFuzzy is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  JFuzzy is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with JFuzzy; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  The license is also available at http://www.gnu.org/licenses/gpl.txt
 *
 ******************************************************************************/
package net.sourceforge.fuzzyservices.swing;

import java.beans.PropertyChangeListener;
import javax.swing.event.ChangeListener;


/**
 *
 * @author Uwe Weng
 */
public interface MembershipFunctionModel {
    /**
     * Adds an <CODE>ChangeListener</CODE> to the model.
     * @param l the listener to add
     */
    public void addChangeListener(final ChangeListener l);

    /**
     * Removes an <CODE>ChangeListener</CODE> from the model.
     * @param l the listener to remove
     */
    public void removeChangeListener(final ChangeListener l);

    /**
     * Adds a PropertyChangeListener to the listener list.
     * @param l The listener to add.
     */
    public void addPropertyChangeListener(final PropertyChangeListener l);

    /**
     * Removes a PropertyChangeListener from the listener list.
     * @param l The listener to remove.
     */
    public void removePropertyChangeListener(final PropertyChangeListener l);

    /**
     * Getter for property enabled.
     * @return Value of property enabled.
     */
    public boolean isEnabled();

    /**
     * Setter for property enabled.
     * @param enabled New value of property enabled.
     */
    public void setEnabled(final boolean enabled);

    /**
     * Returns a crisp value determined by a defuzzifcator, possibly.
     * @return crisp float value as the result of a defuzzification
     */
    public float getDefuzzifiedValue();

    public float[] getXValues();

    public float getDegreeOfMembership(final float x);

    public void addPoint(final float x, final float y);

    public void removePointAt(final float x);
}
