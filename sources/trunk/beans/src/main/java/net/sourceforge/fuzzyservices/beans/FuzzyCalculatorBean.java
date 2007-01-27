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

import net.sourceforge.fuzzyservices.core.FuzzyCalculator;

/**
 * This class represents a fuzzy calculator according to JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.FuzzyCalculator
 *
 * @since 1.0
 * @author Uwe Weng
 */
public final class FuzzyCalculatorBean {
    /** The singleton instance of this fuzzy calculator bean. */
    private static transient FuzzyCalculatorBean instance = null;

    /**
     * Default <code>FuzzyCalculatorBean</code> constructor. It is also
     * possible to use the "singleton" instance.
     * @see #getInstance
     */
    public FuzzyCalculatorBean() {
        // Do nothing
    }

    /**
     * Gets an instance of this fuzzy calculator.
     * @return an instance of this class
     */
    public static final FuzzyCalculatorBean getInstance() {
        if (instance == null) {
            instance = new FuzzyCalculatorBean();
        }

        return instance;
    }

    /**
     * Adds fuzzy interval <code>operand1</code> to fuzzy interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyIntervalBean add(FuzzyIntervalBean operand1,
            FuzzyIntervalBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().add(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Adds fuzzy LR interval <code>operand1</code> to fuzzy LR interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRIntervalBean add(FuzzyLRIntervalBean operand1,
            FuzzyLRIntervalBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().add(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Adds fuzzy LR number <code>operand1</code> to fuzzy LR number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRNumberBean add(FuzzyLRNumberBean operand1,
            FuzzyLRNumberBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().add(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Adds fuzzy number <code>operand1</code> to fuzzy number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyNumberBean add(FuzzyNumberBean operand1,
            FuzzyNumberBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().add(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Devides fuzzy interval <code>operand1</code> through fuzzy interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyIntervalBean divide(FuzzyIntervalBean operand1,
            FuzzyIntervalBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().divide(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Devides fuzzy LR interval <code>operand1</code> through fuzzy LR interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRIntervalBean divide(FuzzyLRIntervalBean operand1,
            FuzzyLRIntervalBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().divide(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Devides fuzzy LR number <code>operand1</code> through fuzzy LR number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRNumberBean divide(FuzzyLRNumberBean operand1,
            FuzzyLRNumberBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().divide(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Devides fuzzy number <code>operand1</code> through fuzzy number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyNumberBean divide(FuzzyNumberBean operand1,
            FuzzyNumberBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().divide(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Multiplies fuzzy interval <code>operand1</code> with fuzzy interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyIntervalBean multiply(FuzzyIntervalBean operand1,
            FuzzyIntervalBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().multiply(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Multiplies fuzzy LR interval <code>operand1</code> with fuzzy LR interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRIntervalBean multiply(FuzzyLRIntervalBean operand1,
            FuzzyLRIntervalBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().multiply(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Multiplies fuzzy LR number <code>operand1</code> with fuzzy LR number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRNumberBean multiply(FuzzyLRNumberBean operand1,
            FuzzyLRNumberBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().multiply(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Multiplies fuzzy number <code>operand1</code> with fuzzy number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyNumberBean multiply(FuzzyNumberBean operand1,
            FuzzyNumberBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().multiply(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Subtracts fuzzy interval <code>operand2</code> from fuzzy interval
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyIntervalBean subtract(FuzzyIntervalBean operand1,
            FuzzyIntervalBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().subtract(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Subtracts fuzzy LR interval <code>operand2</code> from fuzzy LR interval
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRIntervalBean subtract(FuzzyLRIntervalBean operand1,
            FuzzyLRIntervalBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().subtract(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Subtracts fuzzy LR number <code>operand2</code> from fuzzy LR number
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRNumberBean subtract(FuzzyLRNumberBean operand1,
            FuzzyLRNumberBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().subtract(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }

    /**
     * Subtracts fuzzy number <code>operand2</code> from fuzzy number
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyNumberBean subtract(FuzzyNumberBean operand1,
            FuzzyNumberBean operand2) {
        return FuzzyBeanUtils.convert(FuzzyCalculator.getInstance().subtract(
                FuzzyBeanUtils.convert(operand1),
                FuzzyBeanUtils.convert(operand2)));
    }
}

