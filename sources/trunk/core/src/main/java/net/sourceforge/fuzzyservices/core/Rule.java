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

import java.io.Serializable;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;
import net.sourceforge.fuzzyservices.core.operator.AbstractParameteredOperator;
import net.sourceforge.fuzzyservices.core.operator.Min;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A fuzzy rule is an if-then statement consisting of antecedents and consequents.
 * Additionally, some fuzzy operators are used for aggregation while making
 * decisions. The faith in a rule is expressed by a certainty factor. It is
 * a value in [0,1].
 *
 * @see AbstractOperator
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class Rule implements Cloneable, Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;
    /** By default, the certainty is 1.0. */
    private static float defaultCertainty = 1.0f;
    /**
     * By default, a MIN-Operator is used for aggregation.
     */
    private static AbstractOperator defaultAggregationOperator = new Min(); // beliebig
    /**
     * By default, a MIN-Operator is used for reconciliation of
     * result and certainty factor. The operator has to fulfill the t-norm.
     */
    private static AbstractOperator defaultCertaintyOperator = new Min(); // t-Norm
    /**
     * By default, a MIN-Operator is used for inference.
     */
    private static AbstractOperator defaultInferenceOperator = new Min(); // t-Norm
    /**
     * The faith in a rule is expressed by a certainty factor.
     * It is a value in [0,1].
     */
    private float certainty = defaultCertainty;
    /** A fuzzy operator is required for aggregation. */
    private AbstractOperator aggregationOp = defaultAggregationOperator; // (bel.)
    /**
     * The premise of a rule must not higher than the certainty. Therefore,
     * a fuzzy operator of type t-norm is required.
     */
    private AbstractOperator certaintyOp = defaultCertaintyOperator; // (t-Norm)
    /** A fuzzy operator for inference is required. */
    private AbstractOperator inferenceOp = defaultInferenceOperator; // (t-Norm)
    /**
     * The antecedents of the rule are stored in a vector.
     */
    private List<Antecedent> antecedents = new ArrayList<Antecedent>();
    /**
     * The consequents of the rule are stored in a vector.
     */
    private List<Consequent> consequents = new ArrayList<Consequent>();

    /**
     * Adds a premise to this rule.
     *
     * @param ante
     *            the new antecedent
     * @exception NullPointerException
     *                if <code>ante</code> is <code>null</code>
     */
    public final synchronized void addAntecedent(final Antecedent ante)
            throws NullPointerException {
        antecedents.add(ante); // No check !!!
    }

    /**
     * Adds a premise to this rule.
     *
     * @param lvName
     *            the linguistic variable name of the antecedent
     * @param lingTermName
     *            the name of the linguistic term
     * @see #if_(LinguisticVariable, String)
     */
    public final synchronized void addAntecedent(final String lvName, final String lingTermName) {
        Antecedent ante = new Antecedent(lvName, lingTermName);

        if (!antecedents.contains(ante)) {
            antecedents.add(ante);
        }
    }

    /**
     * Adds a premise to this rule.
     *
     * @param lvName
     *            the linguistic variable name of the antecedent
     * @param lingTermName
     *            the name of the linguistic term
     * @param compOp
     *            the fuzzy operator for examining the compatibility
     * @exception NullPointerException
     *                if at least one parameter is <code>null</code>
     * @exception IllegalArgumentException
     *                if the fuzzy operator does not fulfill the t-norm
     * @see #if_(LinguisticVariable, String)
     */
    public final synchronized void addAntecedent(final String lvName, final String lingTermName,
            final AbstractOperator compOp)
            throws NullPointerException, IllegalArgumentException {
        Antecedent ante = new Antecedent(lvName, lingTermName, compOp);

        if (!antecedents.contains(ante)) {
            antecedents.add(ante);
        }
    }

    /**
     * Adds a conclusion to this rule.
     *
     * @param cons
     *            the new consequent
     * @exception NullPointerException
     *                if <code>cons</code> is <code>null</code>
     */
    public final synchronized void addConsequent(final Consequent cons)
            throws NullPointerException {
        consequents.add(cons); // No check !!!
    }

    /**
     * Adds a conclusion to this rule.
     *
     * @param lvName
     *            the linguistic variable name of the consequent
     * @param lingTermName
     *            the name of the linguistic term
     * @see #then_
     */
    public final synchronized void addConsequent(final String lvName, final String lingTermName) {
        Consequent cons = new Consequent(lvName, lingTermName);

        if (!consequents.contains(cons)) {
            consequents.add(cons);
        }
    }

    /**
     * Removes all antecedents and consequents.
     */
    public final synchronized void clear() {
        consequents.clear();
        antecedents.clear();
    }

    /**
     * Checks whether an antecedent to a linguistic variable already exists.
     *
     * @param lvName
     *            the linguistic variable name of the antecedent
     * @param lingTermName
     *            the name of the linguistic term
     * @return <code>true</code> if such antecedent is defined, <code>false</code> otherwise
     */
    public synchronized boolean containsAntecedent(final String lvName, final String lingTermName) {
        if ((lvName != null) && (lingTermName != null)) {
            return antecedents.contains(new Antecedent(lvName, lingTermName));
        }

        return false;
    }

    /**
     * Checks whether an antecedent to a linguistic variable already exists.
     *
     * @param lvName
     *            the linguistic variable name of the consequent
     * @param lingTermName
     *            the name of the linguistic term
     * @param compOp
     *            the fuzzy operator for examining the compatibility
     * @return <code>true</code> if such antecedent is defined, <code>false</code> otherwise
     * @exception IllegalArgumentException
     *                if the fuzzy operator does not fulfill the t-norm
     */
    public synchronized boolean containsAntecedent(final String lvName, final String lingTermName,
            final AbstractOperator compOp)
            throws IllegalArgumentException {
        if ((lvName != null) && (lingTermName != null) && (compOp != null)) {
            return antecedents.contains(new Antecedent(lvName, lingTermName, compOp));
        }

        return false;
    }

    /**
     * Checks whether an consequent to a linguistic variable already exists.
     *
     * @param lvName
     *            the linguistic variable name of the consequent
     * @param lingTermName
     *            the name of the linguistic term
     * @return <code>true</code> if such consequent is defined, <code>false</code> otherwise
     */
    public synchronized boolean containsConsequent(final String lvName, final String lingTermName) {
        if ((lvName != null) && (lingTermName != null)) {
            return consequents.contains(new Consequent(lvName, lingTermName));
        }

        return false;
    }

    /**
     * Checks whether a linguistic variable is used in one of the
     * antecedents or consequents.
     *
     * @param lingVarName
     *            the name of a linguistic variable
     * @return <code>true</code> if this linguistic variable is referenced, <code>false</code> otherwise
     */
    public synchronized boolean containsLinguisticVariable(final String lingVarName) {
        for (Antecedent antecedent : antecedents) {
            String lingVarNameOfAntecedent = antecedent.getLinguisticVariableName();
            if (((lingVarName == null) && (lingVarNameOfAntecedent == null)) || ((lingVarName != null) && (lingVarName.equals(lingVarNameOfAntecedent) == true))) {
                return true;
            }
        }

        for (Consequent consequent : consequents) {
            String lingVarNameOfConsequent = consequent.getLinguisticVariableName();
            if (((lingVarName == null) && (lingVarNameOfConsequent == null)) || ((lingVarName != null) && (lingVarName.equals(lingVarNameOfConsequent) == true))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the fuzzy operator for aggregation.
     *
     * @return the fuzzy operator for aggregation
     */
    public AbstractOperator getAggregationOperator() {
        return aggregationOp;
    }

    /**
     * Returns the antecedent at index <code>i</code>.
     *
     * @param i
     *            index
     * @return the antecedent
     * @exception ArrayIndexOutOfBoundsException
     *                if no antecedent is defined at position <code>i</code>
     */
    public synchronized Antecedent getAntecedentAt(final int i)
            throws ArrayIndexOutOfBoundsException {
        return antecedents.get(i);
    }

    /**
     * Returns an iterator of antecedents.
     *
     * @return an iterator of antecedents
     */
    public synchronized Iterator<Antecedent> getAntecedents() {
        return antecedents.iterator();
    }

    /**
     * Returns the certainty factor.
     *
     * @return the certainty
     */
    public final float getCertainty() {
        return certainty;
    }

    /**
     * Returns the certainty operator.
     *
     * @return the certainty operator
     */
    public AbstractOperator getCertaintyOperator() {
        return certaintyOp;
    }

    /**
     * Returns the consequent at index <code>i</code>.
     *
     * @param i
     *            index
     * @return the consequent
     * @exception ArrayIndexOutOfBoundsException
     *                if no consequent is defined at position <code>i</code>
     */
    public synchronized Consequent getConsequentAt(final int i)
            throws ArrayIndexOutOfBoundsException {
        return consequents.get(i);
    }

    /**
     * Returns an iterator of consequents.
     *
     * @return an iterator of consequents
     */
    public synchronized Iterator<Consequent> getConsequents() {
        return consequents.iterator();
    }

    /**
     * Returns the default fuzzy operator for aggregation.
     *
     * @return the default aggregation operator
     */
    public static AbstractOperator getDefaultAggregationOperator() {
        return defaultAggregationOperator;
    }

    /**
     * Returns the default certainty operator.
     *
     * @return the default certainty operator
     */
    public static AbstractOperator getDefaultCertaintyOperator() {
        return defaultCertaintyOperator;
    }

    /**
     * Returns the default inference operator.
     *
     * @return the default inference operator
     */
    public static AbstractOperator getDefaultInferenceOperator() {
        return defaultInferenceOperator;
    }

    /**
     * Returns the inference operator.
     *
     * @return the inference operator
     */
    public AbstractOperator getInferenceOperator() {
        return inferenceOp;
    }

    /**
     * Returns the number of antecedents.
     *
     * @return the number of premises
     */
    public final synchronized int getSizeOfAntecedents() {
        return antecedents.size();
    }

    /**
     * Returns the number of consequents.
     *
     * @return the number of conclusions
     */
    public final synchronized int getSizeOfConsequents() {
        return consequents.size();
    }

    /**
     * Adds a premise to the rule.
     * @see #addAntecedent(Antecedent)
     * @param lvName the linguistic variable name
     * @param lingTermName name of a linguistic term which is member of <code>lv</code>
     */
    public final synchronized void if_(final String lvName, final String lingTermName) {
        addAntecedent(lvName, lingTermName);
    }

    /**
     * Adds a premise to the rule.
     * @see #addAntecedent(Antecedent)
     * @param lvName the linguistic variable name
     * @param lingTermName name of a linguistic term which is member of <code>lv</code>
     * @param compOp the compatibility operator
     */
    public final synchronized void if_(final String lvName, final String lingTermName, final AbstractOperator compOp) {
        addAntecedent(lvName, lingTermName, compOp);
    }

    /**
     * Removes a premise.
     *
     * @param ante
     *            the antecedent for removing
     * @return <code>true</code> if antecedent did really exist, <code>false</code> otherwise
     */
    public synchronized boolean removeAntecedent(final Antecedent ante) {
        if (ante != null) {
            return antecedents.remove(ante);
        }

        return false;
    }

    /**
     * Removes a premise.
     *
     * @param lvName
     *            the linguistic variable name of the consequent
     * @param lingTermName
     *            the name of the linguistic term
     * @return <code>true</code> if antecedent did really exist, <code>false</code> otherwise
     * @exception NullPointerException
     *                if at least one parameter is <code>null</code>
     */
    public synchronized boolean removeAntecedent(final String lvName, final String lingTermName)
            throws NullPointerException {
        return antecedents.remove(new Antecedent(lvName, lingTermName));
    }

    /**
     * Removes a premise.
     *
     * @return <code>true</code> if antecedent did really exist, <code>false</code> otherwise
     * @param lvName the linguistic variable name of the antecedent
     * @param lingTermName the name of the linguistic term
     * @param compOp the fuzzy operator for examining the compatibility
     * @exception NullPointerException
     *                if at least one parameter is <code>null</code>
     * @exception IllegalArgumentException if the fuzzy operator does not fulfill the t-norm
     */
    public synchronized boolean removeAntecedent(final String lvName, final String lingTermName,
            final AbstractOperator compOp)
            throws NullPointerException, IllegalArgumentException {
        return antecedents.remove(new Antecedent(lvName, lingTermName, compOp));
    }

    /**
     * Removes a conclusion.
     *
     * @param cons
     *            the consequent for removing
     * @return <code>true</code> if consequent did really exist, <code>false</code> otherwise
     */
    public synchronized boolean removeConsequent(final Consequent cons) {
        if (cons != null) {
            return consequents.remove(cons);
        }

        return false;
    }

    /**
     * Removes a conclusion.
     *
     * @param lvName
     *            the linguistic variable name of the consequent
     * @param lingTermName
     *            the name of the linguistic term
     * @return <code>true</code> if consequent did really exist, <code>false</code> otherwise
     * @exception NullPointerException
     *                if at least one parameter is <code>null</code>
     */
    public synchronized boolean removeConsequent(final String lvName, final String lingTermName)
            throws NullPointerException {
        return consequents.remove(new Consequent(lvName, lingTermName));
    }

    /**
     * Sets the fuzzy operator for aggregation.
     *
     * @param aggOp
     *            the new fuzzy operator for aggregation
     * @exception NullPointerException
     *                if <code>aggOp</code> is <code>null</code>
     */
    public synchronized void setAggregationOperator(final AbstractOperator aggOp)
            throws NullPointerException {
        if (aggOp != null) {
            aggregationOp = aggOp;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Redefines the antecedent at index <code>i</code>.
     *
     * @param ante the new premise
     * @param i
     *            index
     * @exception ArrayIndexOutOfBoundsException
     *                if no antecedent is defined at position <code>i</code>
     */
    protected synchronized void setAntecedentAt(final Antecedent ante, final int i)
            throws ArrayIndexOutOfBoundsException {
        antecedents.set(i, ante);
    }

    /**
     * Redefines the conclusion at index <code>i</code>.
     *
     * @param cons the new consequent
     * @param i
     *            index
     * @exception ArrayIndexOutOfBoundsException
     *                if no consequent is defined at position <code>i</code>
     */
    protected synchronized void setConsequentAt(final Consequent cons, final int i)
            throws ArrayIndexOutOfBoundsException {
        consequents.set(i, cons);
    }

    /**
     * Sets the certainty factor.
     *
     * @param cert
     *            the new certainty
     * @exception IllegalArgumentException
     *                if <code>cert</code> is not in <tt>[0,1]</tt>
     */
    public final synchronized void setCertainty(final float cert)
            throws IllegalArgumentException {
        if ((cert >= 0.0f) && (cert <= 1.0f)) {
            certainty = cert;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_CERTAINTY",
                    new Object[]{Float.toString(cert)}));
        }
    }

    /**
     * Sets the certainty operator. It has to fulfill the t-norm.
     *
     * @param certOp the new certainty operator
     * @exception NullPointerException if <code>certOp</code> is <code>null</code>
     * @exception IllegalArgumentException if the operator does not fulfill the t-norm
     */
    public synchronized void setCertaintyOperator(final AbstractOperator certOp)
            throws NullPointerException, IllegalArgumentException {
        if (certOp.isValidTNorm()) {
            certaintyOp = certOp;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this,
                    "EXCEPTION_INVALID_T_NORM_OPERATOR"));
        }
    }

    /**
     * Sets the default fuzzy operator for aggregation.
     *
     * @param aggOp the new default aggregation operator
     * @exception NullPointerException if <code>aggOp</code> is <code>null</code>
     */
    public static synchronized void setDefaultAggregationOperator(final AbstractOperator aggOp)
            throws NullPointerException {
        if (aggOp != null) {
            defaultAggregationOperator = aggOp;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Returns the default certainty factor.
     *
     * @return the default certainty
     */
    public static float getDefaultCertainty() {
        return defaultCertainty;
    }

    /**
     * Sets the default certainty factor.
     *
     * @param cert
     *            the new default certainty
     * @exception IllegalArgumentException
     *                if <code>cert</code> is not in <tt>[0,1]</tt>
     */
    public static synchronized void setDefaultCertainty(final float cert)
            throws IllegalArgumentException {
        if ((cert >= 0.0f) && (cert <= 1.0f)) {
            defaultCertainty = cert;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    Rule.class, "EXCEPTION_INVALID_CERTAINTY",
                    new Object[]{Float.toString(cert)}));
        }
    }

    /**
     * Sets the default certainty operator. It has to fulfill the t-norm.
     *
     * @param certOp the new default certainty operator
     * @exception NullPointerException if <code>certOp</code> is <code>null</code>
     * @exception IllegalArgumentException if the operator does not fulfill the t-norm
     */
    public static synchronized void setDefaultCertaintyOperator(final AbstractOperator certOp)
            throws NullPointerException, IllegalArgumentException {
        if (certOp.isValidTNorm()) {
            defaultCertaintyOperator = certOp;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(Rule.class,
                    "EXCEPTION_INVALID_T_NORM_OPERATOR"));
        }
    }

    /**
     * Sets the default inference operator. It has to fulfill the t-norm.
     *
     * @param infOp the new default inference operator
     * @exception NullPointerException if <code>infOp</code> is <code>null</code>
     * @exception IllegalArgumentException if the operator does not fulfill the t-norm
     */
    public static synchronized void setDefaultInferenceOperator(final AbstractOperator infOp)
            throws NullPointerException, IllegalArgumentException {
        if (infOp.isValidTNorm()) {
            defaultInferenceOperator = infOp;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(Rule.class,
                    "EXCEPTION_INVALID_T_NORM_OPERATOR"));
        }
    }

    /**
     * Sets the inference operator. It has to fulfill the t-norm.
     *
     * @param infOp the new inference operator
     * @exception NullPointerException if <code>infOp</code> is <code>null</code>
     * @exception IllegalArgumentException if the operator does not fulfill the t-norm
     */
    public synchronized void setInferenceOperator(final AbstractOperator infOp)
            throws NullPointerException, IllegalArgumentException {
        if (infOp.isValidTNorm()) {
            inferenceOp = infOp;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(this,
                    "EXCEPTION_INVALID_T_NORM_OPERATOR"));
        }
    }

    /**
     * Adds a conclusion to this rule.
     *
     * @param lvName
     *            the linguistic variable name of the consequent
     * @param lingTermName
     *            the name of the linguistic term
     * @exception NullPointerException
     *                if at least one parameter is <code>null</code>
     * @see #addConsequent(Consequent)
     */
    public final synchronized void then_(final String lvName, final String lingTermName)
            throws NullPointerException {
        addConsequent(lvName, lingTermName);
    }

    @Override
    public Object clone() {
        try {
            Rule newObj = (Rule) super.clone();
            // Duplicate items physically.
            newObj.antecedents = new ArrayList<Antecedent>(antecedents.size());

            for (Antecedent antecedent : antecedents) {
                newObj.antecedents.add((Antecedent) antecedent.clone());
            }

            newObj.consequents = new ArrayList<Consequent>(consequents.size());

            for (Consequent consequent : consequents) {
                newObj.consequents.add((Consequent) consequent.clone());
            }

            return newObj;
        } catch (java.lang.CloneNotSupportedException e) {
            // kann nicht auftreten
            throw new InternalError(e.toString());
        }
    }

    @Override
    public final boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof Rule)) {
            Rule r = (Rule) obj;

            if ((certainty != r.certainty) ||
                    (!aggregationOp.equals(r.aggregationOp)) ||
                    (!certaintyOp.equals(r.certaintyOp)) ||
                    (!inferenceOp.equals(r.inferenceOp))) {
                return false;
            }

            // Comparing sizes of antecedents and consequents
            if ((antecedents.size() != r.antecedents.size()) ||
                    (consequents.size() != r.consequents.size())) {
                return false;
            }

            // Comparing antecedents pairwise
            if (this.antecedents.equals(r.antecedents) == false) {
                return false;
            }

            // Comparing consequents pairwise
            if (this.consequents.equals(r.consequents) == false) {
                return false;
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = (89 * hash) + Float.floatToIntBits(this.certainty);
        hash = (89 * hash) + ((this.aggregationOp != null) ? this.aggregationOp.hashCode() : 0);
        hash = (89 * hash) + ((this.certaintyOp != null) ? this.certaintyOp.hashCode() : 0);
        hash = (89 * hash) + ((this.inferenceOp != null) ? this.inferenceOp.hashCode() : 0);
        hash = (89 * hash) + ((this.antecedents != null) ? this.antecedents.hashCode() : 0);
        hash = (89 * hash) + ((this.consequents != null) ? this.consequents.hashCode() : 0);

        return hash;
    }

    @Override
    public String toString() {
        return toString(false);
    }

    /**
     * Returns a textual representation of the rule
     * @param oneLine <code>true</code> if all statements are printed in one line, <code>false</code> otherwise.
     * @return a string representation of the rule
     */
    public String toString(boolean oneLine) {
        String text = FuzzyResourceManager.getString(this, "RULE_IF") + " ";

        // Iterating the antecedents
        Antecedent ante;
        Iterator<Antecedent> itAnte = antecedents.iterator();

        if (itAnte.hasNext() == true) {
            ante = itAnte.next();
            text = text + ante.toString();

            if (!oneLine) {
                AbstractOperator op = ante.getCompatibilityOperator();

                if (op.requiresParameter()) {
                    text = text + " (" + ((AbstractParameteredOperator) op).toString(true) + ")";
                } else {
                    text = text + " (" + op.toString() + ")";
                }
            }
        }

        while (itAnte.hasNext() == true) {
            ante = itAnte.next();

            if (!oneLine) {
                text = text + "\n    ";
            }

            if (aggregationOp.isValidTNorm()) {
                text = text + " " + FuzzyResourceManager.getString(this, "RULE_AND") + " " + ante.toString();
            } else {
                text = text + " " + FuzzyResourceManager.getString(this, "RULE_OR") + " " + ante.toString();
            }

            if (!oneLine) {
                AbstractOperator op = ante.getCompatibilityOperator();

                if (op.requiresParameter()) {
                    text = text + " (" + ((AbstractParameteredOperator) op).toString(true) + ")";
                } else {
                    text = text + " (" + op.toString() + ")";
                }
            }
        }

        text = text + ", ";

        if (!oneLine) {
            text = text + "\n";
        }

        text = text + FuzzyResourceManager.getString(this, "RULE_THEN") + " ";

        // Iterating the consequents.
        Iterator<Consequent> itCons = consequents.iterator();

        if (itCons.hasNext() == true) {
            text = text + itCons.next().toString();
        }

        while (itCons.hasNext() == true) {
            if (!oneLine) {
                text = text + "\n    ";
            }

            text = text + " " + FuzzyResourceManager.getString(this, "RULE_AND") + " " +
                    itCons.next().toString();
        }

        if (!oneLine) {
            text = text + "\n    ";
        }

        text = text +
                FuzzyResourceManager.getString(this,
                "RULE_CERTAINTY",
                new Object[]{Float.toString(certainty)});

        return text;
    }
}
