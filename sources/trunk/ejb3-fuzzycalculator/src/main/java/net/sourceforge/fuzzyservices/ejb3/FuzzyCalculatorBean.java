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
package net.sourceforge.fuzzyservices.ejb3;

import javax.ejb.Stateless;

/**
 * EJB implementation of a fuzzy calculator for arithmetic operations on fuzzy numbers and
 * intervals. It supports both core objects and JavaBeans.
 *
 * @version 1.0
 * @author Uwe Weng
 */
@Stateless(mappedName = "FuzzyCalculator")
public class FuzzyCalculatorBean implements FuzzyCalculatorRemote {

    /**
     * Adds fuzzy interval <code>operand1</code> to fuzzy interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyInterval add(net.sourceforge.fuzzyservices.core.FuzzyInterval operand1, net.sourceforge.fuzzyservices.core.FuzzyInterval operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().add(operand1,
                operand2);
    }

    /**
     * Adds fuzzy LR interval <code>operand1</code> to fuzzy LR interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyLRInterval add(net.sourceforge.fuzzyservices.core.FuzzyLRInterval operand1, net.sourceforge.fuzzyservices.core.FuzzyLRInterval operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().add(operand1,
                operand2);
    }

    /**
     * Adds fuzzy LR number <code>operand1</code> to fuzzy LR number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyLRNumber add(net.sourceforge.fuzzyservices.core.FuzzyLRNumber operand1, net.sourceforge.fuzzyservices.core.FuzzyLRNumber operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().add(operand1,
                operand2);
    }

    /**
     * Adds fuzzy number <code>operand1</code> to fuzzy number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyNumber add(net.sourceforge.fuzzyservices.core.FuzzyNumber operand1, net.sourceforge.fuzzyservices.core.FuzzyNumber operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().add(operand1,
                operand2);
    }

    /**
     * Devides fuzzy interval <code>operand1</code> through fuzzy interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyInterval divide(net.sourceforge.fuzzyservices.core.FuzzyInterval operand1, net.sourceforge.fuzzyservices.core.FuzzyInterval operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().divide(operand1,
                operand2);
    }

    /**
     * Devides fuzzy LR interval <code>operand1</code> through fuzzy LR interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyLRInterval divide(net.sourceforge.fuzzyservices.core.FuzzyLRInterval operand1, net.sourceforge.fuzzyservices.core.FuzzyLRInterval operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().divide(operand1,
                operand2);
    }

    /**
     * Devides fuzzy LR number <code>operand1</code> through fuzzy LR number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyLRNumber divide(net.sourceforge.fuzzyservices.core.FuzzyLRNumber operand1, net.sourceforge.fuzzyservices.core.FuzzyLRNumber operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().divide(operand1,
                operand2);
    }

    /**
     * Devides fuzzy number <code>operand1</code> through fuzzy number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyNumber divide(net.sourceforge.fuzzyservices.core.FuzzyNumber operand1, net.sourceforge.fuzzyservices.core.FuzzyNumber operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().divide(operand1,
                operand2);
    }

    /**
     * Multiplies fuzzy interval <code>operand1</code> with fuzzy interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyInterval multiply(net.sourceforge.fuzzyservices.core.FuzzyInterval operand1, net.sourceforge.fuzzyservices.core.FuzzyInterval operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().multiply(operand1,
                operand2);
    }

    /**
     * Multiplies fuzzy LR interval <code>operand1</code> with fuzzy LR interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyLRInterval multiply(net.sourceforge.fuzzyservices.core.FuzzyLRInterval operand1, net.sourceforge.fuzzyservices.core.FuzzyLRInterval operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().multiply(operand1,
                operand2);
    }

    /**
     * Multiplies fuzzy LR number <code>operand1</code> with fuzzy LR number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyLRNumber multiply(net.sourceforge.fuzzyservices.core.FuzzyLRNumber operand1, net.sourceforge.fuzzyservices.core.FuzzyLRNumber operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().multiply(operand1,
                operand2);
    }

    /**
     * Multiplies fuzzy number <code>operand1</code> with fuzzy number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyNumber multiply(net.sourceforge.fuzzyservices.core.FuzzyNumber operand1, net.sourceforge.fuzzyservices.core.FuzzyNumber operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().multiply(operand1,
                operand2);
    }

    /**
     * Subtracts fuzzy interval <code>operand2</code> from fuzzy interval <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyInterval subtract(net.sourceforge.fuzzyservices.core.FuzzyInterval operand1, net.sourceforge.fuzzyservices.core.FuzzyInterval operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().subtract(operand1,
                operand2);
    }

    /**
     * Subtracts fuzzy LR interval <code>operand2</code> from fuzzy LR interval <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyLRInterval subtract(net.sourceforge.fuzzyservices.core.FuzzyLRInterval operand1, net.sourceforge.fuzzyservices.core.FuzzyLRInterval operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().subtract(operand1,
                operand2);
    }

    /**
     * Subtracts fuzzy LR number <code>operand2</code> from fuzzy LR number <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyLRNumber subtract(net.sourceforge.fuzzyservices.core.FuzzyLRNumber operand1, net.sourceforge.fuzzyservices.core.FuzzyLRNumber operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().subtract(operand1,
                operand2);
    }

    /**
     * Subtracts fuzzy number <code>operand2</code> from fuzzy number <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.core.FuzzyNumber subtract(net.sourceforge.fuzzyservices.core.FuzzyNumber operand1, net.sourceforge.fuzzyservices.core.FuzzyNumber operand2) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyCalculatorImpl.getInstance().subtract(operand1,
                operand2);
    }

    /**
     * Adds fuzzy interval <code>operand1</code> to fuzzy interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyInterval add(net.sourceforge.fuzzyservices.beans.FuzzyInterval operand1, net.sourceforge.fuzzyservices.beans.FuzzyInterval operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().add(operand1,
                operand2);
    }

    /**
     * Adds fuzzy LR interval <code>operand1</code> to fuzzy LR interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyLRInterval add(net.sourceforge.fuzzyservices.beans.FuzzyLRInterval operand1, net.sourceforge.fuzzyservices.beans.FuzzyLRInterval operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().add(operand1,
                operand2);
    }

    /**
     * Adds fuzzy LR number <code>operand1</code> to fuzzy LR number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyLRNumber add(net.sourceforge.fuzzyservices.beans.FuzzyLRNumber operand1, net.sourceforge.fuzzyservices.beans.FuzzyLRNumber operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().add(operand1,
                operand2);
    }

    /**
     * Adds fuzzy number <code>operand1</code> to fuzzy number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyNumber add(net.sourceforge.fuzzyservices.beans.FuzzyNumber operand1, net.sourceforge.fuzzyservices.beans.FuzzyNumber operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().add(operand1,
                operand2);
    }

    /**
     * Devides fuzzy interval <code>operand1</code> through fuzzy interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyInterval divide(net.sourceforge.fuzzyservices.beans.FuzzyInterval operand1, net.sourceforge.fuzzyservices.beans.FuzzyInterval operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().divide(operand1,
                operand2);
    }

    /**
     * Devides fuzzy LR interval <code>operand1</code> through fuzzy LR interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyLRInterval divide(net.sourceforge.fuzzyservices.beans.FuzzyLRInterval operand1, net.sourceforge.fuzzyservices.beans.FuzzyLRInterval operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().divide(operand1,
                operand2);
    }

    /**
     * Devides fuzzy LR number <code>operand1</code> through fuzzy LR number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyLRNumber divide(net.sourceforge.fuzzyservices.beans.FuzzyLRNumber operand1, net.sourceforge.fuzzyservices.beans.FuzzyLRNumber operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().divide(operand1,
                operand2);
    }

    /**
     * Devides fuzzy number <code>operand1</code> through fuzzy number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyNumber divide(net.sourceforge.fuzzyservices.beans.FuzzyNumber operand1, net.sourceforge.fuzzyservices.beans.FuzzyNumber operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().divide(operand1,
                operand2);
    }

    /**
     * Multiplies fuzzy interval <code>operand1</code> with fuzzy interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyInterval multiply(net.sourceforge.fuzzyservices.beans.FuzzyInterval operand1, net.sourceforge.fuzzyservices.beans.FuzzyInterval operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().multiply(operand1,
                operand2);
    }

    /**
     * Multiplies fuzzy LR interval <code>operand1</code> with fuzzy LR interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyLRInterval multiply(net.sourceforge.fuzzyservices.beans.FuzzyLRInterval operand1, net.sourceforge.fuzzyservices.beans.FuzzyLRInterval operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().multiply(operand1,
                operand2);
    }

    /**
     * Multiplies fuzzy LR number <code>operand1</code> with fuzzy LR number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyLRNumber multiply(net.sourceforge.fuzzyservices.beans.FuzzyLRNumber operand1, net.sourceforge.fuzzyservices.beans.FuzzyLRNumber operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().multiply(operand1,
                operand2);
    }

    /**
     * Multiplies fuzzy number <code>operand1</code> with fuzzy number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyNumber multiply(net.sourceforge.fuzzyservices.beans.FuzzyNumber operand1, net.sourceforge.fuzzyservices.beans.FuzzyNumber operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().multiply(operand1,
                operand2);
    }

    /**
     * Subtracts fuzzy interval <code>operand2</code> from fuzzy interval <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyInterval subtract(net.sourceforge.fuzzyservices.beans.FuzzyInterval operand1, net.sourceforge.fuzzyservices.beans.FuzzyInterval operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().subtract(operand1,
                operand2);
    }

    /**
     * Subtracts fuzzy LR interval <code>operand2</code> from fuzzy LR interval <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyLRInterval subtract(net.sourceforge.fuzzyservices.beans.FuzzyLRInterval operand1, net.sourceforge.fuzzyservices.beans.FuzzyLRInterval operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().subtract(operand1,
                operand2);
    }

    /**
     * Subtracts fuzzy LR number <code>operand2</code> from fuzzy LR number <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyLRNumber subtract(net.sourceforge.fuzzyservices.beans.FuzzyLRNumber operand1, net.sourceforge.fuzzyservices.beans.FuzzyLRNumber operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().subtract(operand1,
                operand2);
    }

    /**
     * Subtracts fuzzy number <code>operand2</code> from fuzzy number <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    @Override
    public net.sourceforge.fuzzyservices.beans.FuzzyNumber subtract(net.sourceforge.fuzzyservices.beans.FuzzyNumber operand1, net.sourceforge.fuzzyservices.beans.FuzzyNumber operand2) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyCalculator().subtract(operand1,
                operand2);
    }
}
