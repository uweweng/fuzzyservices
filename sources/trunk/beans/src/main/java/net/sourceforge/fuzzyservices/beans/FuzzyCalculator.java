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
package net.sourceforge.fuzzyservices.beans;

import java.io.Serializable;

/**
 * This class represents a fuzzy calculator according to JavaBeans conventions.
 *
 * @see net.sourceforge.fuzzyservices.core.FuzzyCalculator
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class FuzzyCalculator implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Adds fuzzy interval <code>operand1</code> to fuzzy interval
     * <code>operand2</code>.
     *
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyInterval add(FuzzyInterval operand1, FuzzyInterval operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Adds fuzzy LR interval <code>operand1</code> to fuzzy LR interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRInterval add(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Adds fuzzy LR number <code>operand1</code> to fuzzy LR number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRNumber add(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Adds fuzzy number <code>operand1</code> to fuzzy number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyNumber add(FuzzyNumber operand1, FuzzyNumber operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Devides fuzzy interval <code>operand1</code> through fuzzy interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     * @exception ArithmeticException if the membership function of
     * <code>operand2</code> is not defined at <tt>x = 0</tt>. It would be a
     * division by zero.
     */
    public FuzzyInterval divide(FuzzyInterval operand1, FuzzyInterval operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Devides fuzzy LR interval <code>operand1</code> through fuzzy LR
     * interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     * @exception ArithmeticException if the membership function of
     * <code>operand2</code> is not defined at <tt>x = 0</tt>. It would be a
     * division by zero.
     */
    public FuzzyLRInterval divide(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Devides fuzzy LR number <code>operand1</code> through fuzzy LR number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     * @exception ArithmeticException if the membership function of
     * <code>operand2</code> is not defined at <tt>x = 0</tt>. It would be a
     * division by zero.
     */
    public FuzzyLRNumber divide(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Devides fuzzy number <code>operand1</code> through fuzzy number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     * @exception ArithmeticException if the membership function of
     * <code>operand2</code> is not defined at <tt>x = 0</tt>. It would be a
     * division by zero.
     */
    public FuzzyNumber divide(FuzzyNumber operand1, FuzzyNumber operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Multiplies fuzzy interval <code>operand1</code> with fuzzy interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyInterval multiply(FuzzyInterval operand1, FuzzyInterval operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Multiplies fuzzy LR interval <code>operand1</code> with fuzzy LR
     * interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRInterval multiply(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Multiplies fuzzy LR number <code>operand1</code> with fuzzy LR number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRNumber multiply(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Multiplies fuzzy number <code>operand1</code> with fuzzy number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyNumber multiply(FuzzyNumber operand1, FuzzyNumber operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Subtracts fuzzy interval <code>operand2</code> from fuzzy interval
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyInterval subtract(FuzzyInterval operand1, FuzzyInterval operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Subtracts fuzzy LR interval <code>operand2</code> from fuzzy LR interval
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRInterval subtract(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Subtracts fuzzy LR number <code>operand2</code> from fuzzy LR number
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRNumber subtract(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Subtracts fuzzy number <code>operand2</code> from fuzzy number
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyNumber subtract(FuzzyNumber operand1, FuzzyNumber operand2) throws NullPointerException {
        // TODO Implementing
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
