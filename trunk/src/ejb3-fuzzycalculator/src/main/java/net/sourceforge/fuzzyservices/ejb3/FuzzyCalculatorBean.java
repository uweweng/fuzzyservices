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
package net.sourceforge.fuzzyservices.ejb3;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import net.sourceforge.fuzzyservices.beans.FuzzyCalculator;
import net.sourceforge.fuzzyservices.beans.FuzzyCalculatorI;
import net.sourceforge.fuzzyservices.beans.FuzzyInterval;
import net.sourceforge.fuzzyservices.beans.FuzzyLRInterval;
import net.sourceforge.fuzzyservices.beans.FuzzyLRNumber;
import net.sourceforge.fuzzyservices.beans.FuzzyNumber;

/**
 * EJB implementation of a fuzzy calculator for arithmetic operations on fuzzy 
 * numbers and intervals. It supports the bean implementation.
 *
 * @version 1.0
 * @author Uwe Weng
 */
@Stateless(mappedName = "FuzzyCalculator")
@Remote(FuzzyCalculatorI.class)
public class FuzzyCalculatorBean implements FuzzyCalculatorI {

    @Override
    public FuzzyInterval add(FuzzyInterval operand1, FuzzyInterval operand2) {
        return new FuzzyCalculator().add(operand1, operand2);
    }

    @Override
    public FuzzyLRInterval add(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        return new FuzzyCalculator().add(operand1, operand2);
    }

    @Override
    public FuzzyLRNumber add(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        return new FuzzyCalculator().add(operand1, operand2);
    }

    @Override
    public FuzzyNumber add(FuzzyNumber operand1, FuzzyNumber operand2) {
        return new FuzzyCalculator().add(operand1, operand2);
    }

    @Override
    public FuzzyInterval divide(FuzzyInterval operand1, FuzzyInterval operand2) {
        return new FuzzyCalculator().divide(operand1, operand2);
    }

    @Override
    public FuzzyLRInterval divide(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        return new FuzzyCalculator().divide(operand1, operand2);
    }

    @Override
    public FuzzyLRNumber divide(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        return new FuzzyCalculator().divide(operand1, operand2);
    }

    @Override
    public FuzzyNumber divide(FuzzyNumber operand1, FuzzyNumber operand2) {
        return new FuzzyCalculator().divide(operand1, operand2);
    }

    @Override
    public FuzzyInterval multiply(FuzzyInterval operand1, FuzzyInterval operand2) {
        return new FuzzyCalculator().multiply(operand1, operand2);
    }

    @Override
    public FuzzyLRInterval multiply(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        return new FuzzyCalculator().multiply(operand1, operand2);
    }

    @Override
    public FuzzyLRNumber multiply(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        return new FuzzyCalculator().multiply(operand1, operand2);
    }

    @Override
    public FuzzyNumber multiply(FuzzyNumber operand1, FuzzyNumber operand2) {
        return new FuzzyCalculator().multiply(operand1, operand2);
    }

    @Override
    public FuzzyInterval subtract(FuzzyInterval operand1, FuzzyInterval operand2) {
        return new FuzzyCalculator().subtract(operand1, operand2);
    }

    @Override
    public FuzzyLRInterval subtract(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        return new FuzzyCalculator().subtract(operand1, operand2);
    }

    @Override
    public FuzzyLRNumber subtract(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        return new FuzzyCalculator().subtract(operand1, operand2);
    }

    @Override
    public FuzzyNumber subtract(FuzzyNumber operand1, FuzzyNumber operand2) {
        return new FuzzyCalculator().subtract(operand1, operand2);
    }
}
