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
package net.sourceforge.fuzzyservices.core;

/**
 * A fuzzy calculator offers the four base aithmethic operations like a normal
 * calculator. It supports addition, substratcion, multiplication, and devision
 * on fuzzy numbers and intervals (even of LR type).
 *
 * @version 1.0
 * @author Uwe Weng
 */
public interface FuzzyCalculator {

    /**
     * Adds fuzzy interval <code>operand1</code> to fuzzy interval
     * <code>operand2</code>.
     *
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyInterval add(final FuzzyInterval operand1, final FuzzyInterval operand2);

    /**
     * Adds fuzzy LR interval <code>operand1</code> to fuzzy LR interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRInterval add(final FuzzyLRInterval operand1, final FuzzyLRInterval operand2);

    /**
     * Adds fuzzy LR number <code>operand1</code> to fuzzy LR number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRNumber add(final FuzzyLRNumber operand1, final FuzzyLRNumber operand2);

    /**
     * Adds fuzzy number <code>operand1</code> to fuzzy number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyNumber add(final FuzzyNumber operand1, final FuzzyNumber operand2);

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
    public FuzzyInterval divide(final FuzzyInterval operand1, final FuzzyInterval operand2);

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
    public FuzzyLRInterval divide(final FuzzyLRInterval operand1, final FuzzyLRInterval operand2);

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
    public FuzzyLRNumber divide(final FuzzyLRNumber operand1, final FuzzyLRNumber operand2);

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
    public FuzzyNumber divide(final FuzzyNumber operand1, final FuzzyNumber operand2);

    /**
     * Multiplies fuzzy interval <code>operand1</code> with fuzzy interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyInterval multiply(final FuzzyInterval operand1, final FuzzyInterval operand2);

    /**
     * Multiplies fuzzy LR interval <code>operand1</code> with fuzzy LR
     * interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRInterval multiply(final FuzzyLRInterval operand1, final FuzzyLRInterval operand2);

    /**
     * Multiplies fuzzy LR number <code>operand1</code> with fuzzy LR number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRNumber multiply(final FuzzyLRNumber operand1, final FuzzyLRNumber operand2);

    /**
     * Multiplies fuzzy number <code>operand1</code> with fuzzy number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyNumber multiply(final FuzzyNumber operand1, final FuzzyNumber operand2);

    /**
     * Subtracts fuzzy interval <code>operand2</code> from fuzzy interval
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyInterval subtract(final FuzzyInterval operand1, final FuzzyInterval operand2);

    /**
     * Subtracts fuzzy LR interval <code>operand2</code> from fuzzy LR interval
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRInterval subtract(final FuzzyLRInterval operand1, final FuzzyLRInterval operand2);

    /**
     * Subtracts fuzzy LR number <code>operand2</code> from fuzzy LR number
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRNumber subtract(final FuzzyLRNumber operand1, final FuzzyLRNumber operand2);

    /**
     * Subtracts fuzzy number <code>operand2</code> from fuzzy number
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyNumber subtract(final FuzzyNumber operand1, final FuzzyNumber operand2)
            throws NullPointerException;
}
