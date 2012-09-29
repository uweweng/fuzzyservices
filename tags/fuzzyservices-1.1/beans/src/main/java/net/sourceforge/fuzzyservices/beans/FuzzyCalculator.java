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
public class FuzzyCalculator implements FuzzyCalculatorI, Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public FuzzyInterval add(FuzzyInterval operand1, FuzzyInterval operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyLRInterval add(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyLRNumber add(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyNumber add(FuzzyNumber operand1, FuzzyNumber operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyInterval divide(FuzzyInterval operand1, FuzzyInterval operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyLRInterval divide(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyLRNumber divide(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyNumber divide(FuzzyNumber operand1, FuzzyNumber operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyInterval multiply(FuzzyInterval operand1, FuzzyInterval operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyLRInterval multiply(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyLRNumber multiply(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyNumber multiply(FuzzyNumber operand1, FuzzyNumber operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyInterval subtract(FuzzyInterval operand1, FuzzyInterval operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyLRInterval subtract(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyLRNumber subtract(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        // TODO Implementing
        return null;
    }

    @Override
    public FuzzyNumber subtract(FuzzyNumber operand1, FuzzyNumber operand2) throws NullPointerException {
        // TODO Implementing
        return null;
    }
}
