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

import java.util.Collection;

/**
 * Swing Model for a fact base.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public interface FactBaseModel {

    /**
     * Returns the name of the fact base.
     * @return the name
     */
    String getName();

    /**
     * Sets the name of the fact base.
     * @param name the new name
     */
    void setName(final String name);

    /**
     * Returns all facts as <code>FactModel</code>.
     * @return all facts of the fact base as model
     */
    Collection<FactModel> getFacts();

    /**
     * Adds a <code>FactBaseModelListener</code> to the listener list.<p>
     * A <code>FactBaseModelEvent</code> will get fired in response to setting
     * a bound property. <p>
     * @param listener  the <code>FactBaseModelListener</code> to be added
     */
    void addFactBaseModelListener(final FactBaseModelListener listener);

    /**
     * Removes a <code>FactBaseModelListener</code> from the listener list.
     * @param listener  the <code>FactBaseModelListener</code> to be removed
     */
    void removeFactBaseModelListener(final FactBaseModelListener listener);
}
