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
package net.sourceforge.fuzzyservices.ejb;

import net.sourceforge.fuzzyservices.beans.FuzzyInterval;
import net.sourceforge.fuzzyservices.beans.FuzzyLRInterval;
import net.sourceforge.fuzzyservices.beans.FuzzyLRNumber;
import net.sourceforge.fuzzyservices.beans.FuzzyNumber;
import net.sourceforge.fuzzyservices.core.FuzzyInterval;
import net.sourceforge.fuzzyservices.core.FuzzyLRInterval;
import net.sourceforge.fuzzyservices.core.FuzzyLRNumber;
import net.sourceforge.fuzzyservices.core.FuzzyNumber;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;


/**
 * EJB implementation of a fuzzy calculator for arithmetic operations on fuzzy numbers and
 * intervals. It supports both core objects and JavaBeans.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class FuzzyCalculatorBean implements SessionBean {
    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Session context for this bean instance.
     */
    private SessionContext context;

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">;
    /**
     * @param aContext session context for this bean instance
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    @Override
    public final void setSessionContext(SessionContext aContext) {
        context = aContext;
    }

    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    @Override
    public final void ejbActivate() {
    }

    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    @Override
    public final void ejbPassivate() {
    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    @Override
    public final void ejbRemove() {
    }

    // </editor-fold>;
    /**
     * Adds fuzzy interval <code>operand1</code> to fuzzy interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyInterval add(FuzzyInterval operand1, FuzzyInterval operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .add(operand1,
            operand2);
    }

    /**
     * Adds fuzzy LR interval <code>operand1</code> to fuzzy LR interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRInterval add(FuzzyLRInterval operand1,
        FuzzyLRInterval operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .add(operand1,
            operand2);
    }

    /**
     * Adds fuzzy LR number <code>operand1</code> to fuzzy LR number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRNumber add(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .add(operand1,
            operand2);
    }

    /**
     * Adds fuzzy number <code>operand1</code> to fuzzy number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyNumber add(FuzzyNumber operand1, FuzzyNumber operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .add(operand1,
            operand2);
    }

    /**
     * Devides fuzzy interval <code>operand1</code> through fuzzy interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyInterval divide(FuzzyInterval operand1, FuzzyInterval operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .divide(operand1,
            operand2);
    }

    /**
     * Devides fuzzy LR interval <code>operand1</code> through fuzzy LR interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRInterval divide(FuzzyLRInterval operand1,
        FuzzyLRInterval operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .divide(operand1,
            operand2);
    }

    /**
     * Devides fuzzy LR number <code>operand1</code> through fuzzy LR number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRNumber divide(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .divide(operand1,
            operand2);
    }

    /**
     * Devides fuzzy number <code>operand1</code> through fuzzy number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyNumber divide(FuzzyNumber operand1, FuzzyNumber operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .divide(operand1,
            operand2);
    }

    /**
     * Multiplies fuzzy interval <code>operand1</code> with fuzzy interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyInterval multiply(FuzzyInterval operand1, FuzzyInterval operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .multiply(operand1,
            operand2);
    }

    /**
     * Multiplies fuzzy LR interval <code>operand1</code> with fuzzy LR interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRInterval multiply(FuzzyLRInterval operand1,
        FuzzyLRInterval operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .multiply(operand1,
            operand2);
    }

    /**
     * Multiplies fuzzy LR number <code>operand1</code> with fuzzy LR number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRNumber multiply(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .multiply(operand1,
            operand2);
    }

    /**
     * Multiplies fuzzy number <code>operand1</code> with fuzzy number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyNumber multiply(FuzzyNumber operand1, FuzzyNumber operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .multiply(operand1,
            operand2);
    }

    /**
     * Subtracts fuzzy interval <code>operand2</code> from fuzzy interval <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyInterval subtract(FuzzyInterval operand1, FuzzyInterval operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .subtract(operand1,
            operand2);
    }

    /**
     * Subtracts fuzzy LR interval <code>operand2</code> from fuzzy LR interval <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRInterval subtract(FuzzyLRInterval operand1,
        FuzzyLRInterval operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .subtract(operand1,
            operand2);
    }

    /**
     * Subtracts fuzzy LR number <code>operand2</code> from fuzzy LR number <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRNumber subtract(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .subtract(operand1,
            operand2);
    }

    /**
     * Subtracts fuzzy number <code>operand2</code> from fuzzy number <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyNumber subtract(FuzzyNumber operand1, FuzzyNumber operand2) {
        return net.sourceforge.fuzzyservices.core.FuzzyCalculator.getInstance()
                                                                 .subtract(operand1,
            operand2);
    }

    /**
     * Adds fuzzy interval <code>operand1</code> to fuzzy interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyInterval add(FuzzyInterval operand1,
        FuzzyInterval operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .add(operand1,
            operand2);
    }

    /**
     * Adds fuzzy LR interval <code>operand1</code> to fuzzy LR interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRInterval add(FuzzyLRInterval operand1,
        FuzzyLRInterval operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .add(operand1,
            operand2);
    }

    /**
     * Adds fuzzy LR number <code>operand1</code> to fuzzy LR number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRNumber add(FuzzyLRNumber operand1,
        FuzzyLRNumber operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .add(operand1,
            operand2);
    }

    /**
     * Adds fuzzy number <code>operand1</code> to fuzzy number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyNumber add(FuzzyNumber operand1,
        FuzzyNumber operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .add(operand1,
            operand2);
    }

    /**
     * Devides fuzzy interval <code>operand1</code> through fuzzy interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyInterval divide(FuzzyInterval operand1,
        FuzzyInterval operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .divide(operand1,
            operand2);
    }

    /**
     * Devides fuzzy LR interval <code>operand1</code> through fuzzy LR interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRInterval divide(FuzzyLRInterval operand1,
        FuzzyLRInterval operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .divide(operand1,
            operand2);
    }

    /**
     * Devides fuzzy LR number <code>operand1</code> through fuzzy LR number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRNumber divide(FuzzyLRNumber operand1,
        FuzzyLRNumber operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .divide(operand1,
            operand2);
    }

    /**
     * Devides fuzzy number <code>operand1</code> through fuzzy number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyNumber divide(FuzzyNumber operand1,
        FuzzyNumber operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .divide(operand1,
            operand2);
    }

    /**
     * Multiplies fuzzy interval <code>operand1</code> with fuzzy interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyInterval multiply(FuzzyInterval operand1,
        FuzzyInterval operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .multiply(operand1,
            operand2);
    }

    /**
     * Multiplies fuzzy LR interval <code>operand1</code> with fuzzy LR interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRInterval multiply(FuzzyLRInterval operand1,
        FuzzyLRInterval operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .multiply(operand1,
            operand2);
    }

    /**
     * Multiplies fuzzy LR number <code>operand1</code> with fuzzy LR number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRNumber multiply(FuzzyLRNumber operand1,
        FuzzyLRNumber operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .multiply(operand1,
            operand2);
    }

    /**
     * Multiplies fuzzy number <code>operand1</code> with fuzzy number <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyNumber multiply(FuzzyNumber operand1,
        FuzzyNumber operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .multiply(operand1,
            operand2);
    }

    /**
     * Subtracts fuzzy interval <code>operand2</code> from fuzzy interval <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyInterval subtract(FuzzyInterval operand1,
        FuzzyInterval operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .subtract(operand1,
            operand2);
    }

    /**
     * Subtracts fuzzy LR interval <code>operand2</code> from fuzzy LR interval <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRInterval subtract(FuzzyLRInterval operand1,
        FuzzyLRInterval operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .subtract(operand1,
            operand2);
    }

    /**
     * Subtracts fuzzy LR number <code>operand2</code> from fuzzy LR number <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyLRNumber subtract(FuzzyLRNumber operand1,
        FuzzyLRNumber operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .subtract(operand1,
            operand2);
    }

    /**
     * Subtracts fuzzy number <code>operand2</code> from fuzzy number <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyNumber subtract(FuzzyNumber operand1,
        FuzzyNumber operand2) {
        return net.sourceforge.fuzzyservices.beans.FuzzyCalculator.getInstance()
                                                                      .subtract(operand1,
            operand2);
    }
}
