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
package net.sourceforge.fuzzyservices.core;

/**
 * The <code>FuzzyManager</code> is the place for all options which control the fuzzy system.
 * For instance, it defines the step width for scanning a fuzzy set at complex operations, or
 * the maximal number of steps, or accuracy for computations.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class FuzzyManager {

    /**
     * Defines the increment for scanning fuzzy sets. It has to be a value greater than zero.
     * The default value is 0.01.
     */
    protected static float stepwidth = (float) (1.0 / 100.0); // 0.01f;
    /**
     * Defines the maximal number of steps fot iterating. If necessary the increment will be adjusted.
     * It must be a value greater than zero. The value 200 is preset.
     */
    protected static int maxNumStep = 200;
    /**
     * Defines the number of internal decimal places. This value is computed by the <code>stepwidth</code>.
     * For better performance this value is redundant.
     */
    protected static int scale = computeScale(stepwidth);							// berechnet

    /** The fuzzy manager is a static class. */
    private FuzzyManager() {
    // Not allowed
    }

    /**
     * Returns the increment for traversing fuzzy sets.
     *
     * @return the increment
     * @see #stepwidth
     */
    public static float getStepwidth() {
        return stepwidth;
    }

    /**
     * Sets the increment if value is greater than 0.0.
     *
     * @param width the new value
     * @see #stepwidth
     */
    public static void setStepwidth(float width) {
        if (width > 0.0f) {
            stepwidth = width;
            scale = computeScale(stepwidth);
        }
    }

    /**
     * Returns the maximum numbers of steps when combining two fuzzy sets.
     *
     * @return the maximum number of steps
     * @see #maxNumStep
     */
    public static int getMaxNumStep() {
        return maxNumStep;
    }

    /**
     * Sets the maximum numbers of steps when combining two fuzzy sets
     * if new value is greater than 0.0.
     *
     * @param max the new value
     * @see #maxNumStep
     */
    public static void setMaxNumStep(int max) {
        if (max > 0) {
            maxNumStep = max;
        }
    }

    /**
     * Reduces the decimal places of value <code>x</code> to <code>scale</code>.
     *
     * @param x
     *           the value to reduce
     * @return the new value reduced to <code>scale</code> decimal places
     * @see #scale
     */
    public static float round(float x) {
        double dimension = Math.pow(10.0, scale);
        return ((float) (Math.round(x * dimension) / dimension));
    }

    /**
     * Returns the delta between <code>x</code> and <code>round(x)</code>.
     *
     * @param x
     *           the value to determine the delta
     * @return the calculated delta
     * @see #round
     */
    public static float getDelta(float x) {
        return x - round(x);
    }

    /**
     * Compute the number of decimal places of <code>stepwidth</code>
     *
     * @param stepwidth
     *            die increment
     * @return the number of decimal places
     * @see #stepwidth
     */
    private static int computeScale(float stepwidth) {
        int i = 0;
        float scaledstepwidth = stepwidth * (float) Math.pow(10.0f, i);
        while (Math.round(scaledstepwidth) != scaledstepwidth) {
            i++;
            scaledstepwidth = stepwidth * (float) Math.pow(10.0f, i);
        }
        return i;
    }
}
