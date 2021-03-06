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

import java.util.EventObject;


/**
 * RuleModelEvent
 *
 * @author Uwe Weng
 */
public class RuleModelEvent extends EventObject {
    public final static int ANTECEDENTS_CHANGED = 3;
    public final static int CONSEQUENTS_CHANGED = 6;
    public final static int CERTAINTY_CHANGED = 7;
    public final static int CERTAINTY_OPERATOR_CHANGED = 8;
    public final static int AGGREGATION_OPERATOR_CHANGED = 9;
    public final static int INFERENCE_OPERATOR_CHANGED = 10;
    protected int type;

    public RuleModelEvent(Object source) {
        super(source);
    }

    public RuleModelEvent(Object source, int type) {
        super(source);
        this.type = type;
    }
}
