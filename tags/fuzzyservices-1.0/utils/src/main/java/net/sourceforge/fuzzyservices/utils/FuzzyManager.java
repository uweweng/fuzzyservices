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
package net.sourceforge.fuzzyservices.utils;

/**
 * The <code>FuzzyManager</code> is the place for all options which control the
 * fuzzy system.
 * For instance, it defines the step width for scanning a fuzzy set at complex
 * operations, or the maximal number of steps, or accuracy for computations.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public final class FuzzyManager {

    /**
     * Default maximum number of steps is 200.
     */
    public static final int DEFAULT_MAX_NUM_STEP = 200;
    /**
     * Defines the increment for scanning fuzzy sets. It has to be a value greater than zero.
     * The default value is 0.01.
     */
    private static float stepwidth = (float) (1.0 / 100.0); // 0.01f;
    /**
     * Defines the maximal number of steps fot iterating. If necessary the increment will be adjusted.
     * It must be a value greater than zero. The value 200 is preset.
     */
    private static int maxNumStep = DEFAULT_MAX_NUM_STEP;
    /**
     * Defines the number of internal decimal places. This value is computed by the <code>increment</code>.
     * For better performance this value is redundant.
     */
    protected static int scale = computeScale(stepwidth); // calculated
    /**
     * Defines the decimal places.
     */
    private static final float POWER_OF_ONE = 10.0F;

    /** The fuzzy manager is a static class. */
    private FuzzyManager() {
        // Not allowed
    }

    /**
     * Returns the increment for traversing fuzzy sets.
     *
     * @return the increment
     * @see #increment
     */
    public static float getStepwidth() {
        return stepwidth;
    }

    /**
     * Sets the increment. The value has to be greater than 0.0.
     *
     * @param width the new value
     * @see #increment
     */
    public static void setStepwidth(final float width) {
        if (width > 0.0f) {
            stepwidth = width;
            scale = computeScale(stepwidth);
        } else {
            throw new IllegalArgumentException(
                    FuzzyResourceManager.getString(FuzzyManager.class,
                    "EXCEPTION_INVALID_STEP_WIDTH"));
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
    public static void setMaxNumStep(final int max) {
        if (max > 0) {
            maxNumStep = max;
        } else {
            throw new IllegalArgumentException(
                    FuzzyResourceManager.getString(FuzzyManager.class,
                    "EXCEPTION_INVALID_MAX_NUM_STEP"));
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
    public static float round(final float x) {
        double dimension = Math.pow(POWER_OF_ONE, scale);

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
    public static float getDelta(final float x) {
        return x - round(x);
    }

    /**
     * Compute the number of decimal places of <code>increment</code>.
     *
     * @param increment the increment
     * @return the number of decimal places
     * @see #increment
     */
    private static int computeScale(final float increment) {
        int i = 0;
        float scaledstepwidth = increment * (float) Math.pow(POWER_OF_ONE, i);

        while (Math.round(scaledstepwidth) != scaledstepwidth) {
            i++;
            scaledstepwidth = increment * (float) Math.pow(POWER_OF_ONE, i);
        }
        return i;
    }
}
