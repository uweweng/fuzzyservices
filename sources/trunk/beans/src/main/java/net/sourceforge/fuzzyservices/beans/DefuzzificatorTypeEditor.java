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
package net.sourceforge.fuzzyservices.beans;

import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

import java.beans.PropertyEditorSupport;

/**
 * Editor support for type property of a defuzzificator bean.
 * 
 * @version 1.0
 * @author Uwe Weng
 */
public class DefuzzificatorTypeEditor extends PropertyEditorSupport {

    /**
     * Array with all supported types.
     */
    private static byte[] supportedTypes = {
        Defuzzificator.TYPE_CENTER_OF_AREA,
        Defuzzificator.TYPE_LEFT_OF_MAX,
        Defuzzificator.TYPE_MEAN_OF_MAX,
        Defuzzificator.TYPE_RIGHT_OF_MAX
    };
    /**
     * Array with all names of supported types (I18N).
     */
    private static String[] typeNames = {
        FuzzyResourceManager.getString(DefuzzificatorTypeEditor.class,
        "DEFUZZIFICATOR_TYPE_CENTER_OF_AREA"),
        FuzzyResourceManager.getString(DefuzzificatorTypeEditor.class,
        "DEFUZZIFICATOR_TYPE_LEFT_OF_MAX"),
        FuzzyResourceManager.getString(DefuzzificatorTypeEditor.class,
        "DEFUZZIFICATOR_TYPE_MEAN_OF_MAX"),
        FuzzyResourceManager.getString(DefuzzificatorTypeEditor.class,
        "DEFUZZIFICATOR_TYPE_RIGHT_OF_MAX")
    };

    @Override
    public final void setAsText(final String text) {
        for (int i = 0; i < supportedTypes.length; i++) {
            if (typeNames[i].equals(text)) {
                setValue(new Byte(supportedTypes[i]));
                return;
            }
        }
        throw new java.lang.IllegalArgumentException(text);
    }

    @Override
    public String getAsText() {
        byte value = ((Byte) getValue()).byteValue();
        for (int i = 0; i < supportedTypes.length; i++) {
            if (supportedTypes[i] == value) {
                return typeNames[i];
            }
        }
        return null;
    }

    @Override
    public String[] getTags() {
        return typeNames;
    }
}
