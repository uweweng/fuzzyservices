/*******************************************************************************
 *
 *  Copyright (C) 2007  Uwe Weng
 *
 *  This file is part of JFuzzy, a library for processing fuzzy information.
 *
 *  JFuzzy is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  JFuzzy is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with JFuzzy; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  The license is also available at http://www.gnu.org/licenses/gpl.txt
 *
 ******************************************************************************/
package net.sourceforge.fuzzyservices.core;

import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;
import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.core.operator.AbstractParameteredOperator;
import net.sourceforge.fuzzyservices.core.operator.Min;
import java.util.Enumeration;
import java.util.Vector;

/**
 * A fuzzy rule is an if-then statement consisting of antecedents and consequents.
 * Additionally, some fuzzy operators are used for aggregation while making
 * decisions. The faith in a rule is expressed by a certainty factor. It is
 * a value in [0,1].
 *
 * @see AbstractOperator
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class Rule implements java.lang.Cloneable, java.io.Serializable {

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /** By default, the certainty is 1.0. */
    private static float defaultCertainty = 1.0f;

    /**
     * By default, a MIN-AbstractOperator is used for aggregation.
     */
    private static AbstractOperator defaultAggregationOperator = new Min(); // beliebig

    /**
     * By default, a MIN-AbstractOperator is used for reconciliation of
     * result and certainty factor. The operator has to fulfill the t-norm.
     */
    private static AbstractOperator defaultCertaintyOperator = new Min(); // t-Norm

    /**
     * By default, a MIN-AbstractOperator is used for inference.
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
    private Vector antecedents = new Vector(); // of Antecedent

    /**
     * The consequents of the rule are stored in a vector.
     */
    private Vector consequents = new Vector(); // of Consequent

    /**
     * Adds a premise to this rule.
     *
     * @param ante
     *            the new antecedent
     * @exception NullPointerException
     *                if <code>ante</code> is <code>null</code>
     */
    protected synchronized void addAntecedent(final Antecedent ante) throws NullPointerException{
        antecedents.addElement(ante); // No check !!!
    }

    /**
     * Adds a premise to this rule.
     *
     * @param lv
     *            the linguistic variable of the antecedent
     * @param lingTermName
     *            the name of the linguistic term
     * @exception NullPointerException
     *                if one of the parameter is <code>null</code>
     * @see #if_(LinguisticVariable, String)
     */
    public synchronized void addAntecedent(final LinguisticVariable lv,
            final String lingTermName) throws NullPointerException{
        Antecedent ante = new Antecedent(lv, lingTermName);
        if (!antecedents.contains(ante))
            antecedents.addElement(ante);
    }

    /**
     * Adds a premise to this rule.
     *
     * @param lv
     *            the linguistic variable of the antecedent
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
    public synchronized void addAntecedent(final LinguisticVariable lv,
            final String lingTermName, final AbstractOperator compOp) throws NullPointerException, IllegalArgumentException{
        Antecedent ante = new Antecedent(lv, lingTermName, compOp);
        if (!antecedents.contains(ante))
            antecedents.addElement(ante);
    }

    /**
     * Adds a conclusion to this rule.
     *
     * @param cons
     *            the new consequent
     * @exception NullPointerException
     *                if <code>cons</code> is <code>null</code>
     */
    protected synchronized void addConsequent(final Consequent cons) throws NullPointerException{
        consequents.addElement(cons); // Keine Ueberpruefung !!!
    }

    /**
     * Adds a conclusion to this rule.
     *
     * @param lv
     *            the linguistic variable of the consequent
     * @param lingTermName
     *            the name of the linguistic term
     * @exception NullPointerException
     *                if at least one parameter is <code>null</code>
     * @see #then_
     */
    public synchronized void addConsequent(final LinguisticVariable lv,
            final String lingTermName) throws NullPointerException{
        Consequent cons = new Consequent(lv, lingTermName);
        if (!consequents.contains(cons))
            consequents.addElement(cons);
    }

    /**
     * Removes all antecedents and consequents. In addition, certainty and
     * fuzzy operators are reset.
     */
    public synchronized void clear() {
        consequents.removeAllElements();
        antecedents.removeAllElements();
        certainty = defaultCertainty;
        certaintyOp = defaultCertaintyOperator;
        aggregationOp = defaultAggregationOperator;
        inferenceOp = defaultInferenceOperator;
    }

    /**
     * Checks whether an antecedent to a linguistic variable already exists.
     *
     * @param lv
     *            the linguistic variable of the antecedent
     * @param lingTermName
     *            the name of the linguistic term
     * @return <code>true</code> if such antecedent is defined, <code>false</code> otherwise
     */
    public synchronized boolean containsAntecedent(final LinguisticVariable lv,
            final String lingTermName) {
        if ((lv != null) && (lingTermName != null))
            return antecedents.contains(new Antecedent(lv, lingTermName));
        return false;
    }

    /**
     * Checks whether an antecedent to a linguistic variable already exists.
     *
     * @param lv
     *            the linguistic variable of the consequent
     * @param lingTermName
     *            the name of the linguistic term
     * @param compOp
     *            the fuzzy operator for examining the compatibility
     * @return <code>true</code> if such antecedent is defined, <code>false</code> otherwise
     * @exception IllegalArgumentException
     *                if the fuzzy operator does not fulfill the t-norm
     */
    public synchronized boolean containsAntecedent(final LinguisticVariable lv,
            final String lingTermName, final AbstractOperator compOp) throws IllegalArgumentException{
        if ((lv != null) && (lingTermName != null) && (compOp != null))
            return antecedents
                    .contains(new Antecedent(lv, lingTermName, compOp));
        return false;
    }

    /**
     * Checks whether an consequent to a linguistic variable already exists.
     *
     * @param lv
     *            the linguistic variable of the consequent
     * @param lingTermName
     *            the name of the linguistic term
     * @return <code>true</code> if such consequent is defined, <code>false</code> otherwise
     */
    public synchronized boolean containsConsequent(final LinguisticVariable lv,
            final String lingTermName) {
        if ((lv != null) && (lingTermName != null))
            return consequents.contains(new Consequent(lv, lingTermName));
        return false;
    }

    /**
     * Checks whether a linguistic variable is used in one of the
     * antecedents or consequents.
     *
     * @param lv
     *            the linguistic variable
     * @return <code>true</code> if this linguistic variable is referenced, <code>false</code> otherwise
     */
    public synchronized boolean containsLinguisticVariable(
            final LinguisticVariable lv) {
        if (lv != null) {
            java.util.Enumeration elementsAnte = antecedents.elements();
            while (elementsAnte.hasMoreElements()) {
                if (lv == ((Antecedent) elementsAnte.nextElement())
                .getLinguisticVariable())
                    return true;
            }
            java.util.Enumeration elementsCon = consequents.elements();
            while (elementsCon.hasMoreElements()) {
                if (lv == ((Consequent) elementsCon.nextElement())
                .getLinguisticVariable())
                    return true;
            }
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
    public synchronized boolean containsLinguisticVariable(
            final String lingVarName) {
        if (lingVarName != null) {
            java.util.Enumeration elementsAnte = antecedents.elements();
            while (elementsAnte.hasMoreElements()) {
                if (lingVarName
                        .equals(((Antecedent) elementsAnte.nextElement())
                        .getLinguisticVariableName()))
                    return true;
            }
            java.util.Enumeration elementsCon = consequents.elements();
            while (elementsCon.hasMoreElements()) {
                if (lingVarName.equals(((Consequent) elementsCon.nextElement())
                .getLinguisticVariableName()))
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
    protected synchronized Antecedent getAntecedentAt(final int i) throws ArrayIndexOutOfBoundsException{
        return (Antecedent) antecedents.elementAt(i);
    }

    /**
     * Returns an enumeration of antecedents.
     *
     * @return an enumeration of antecedents
     */
    public synchronized Enumeration getAntecedents() {
        return antecedents.elements();
    }

    /**
     * Returns the certainty factor.
     *
     * @return the certainty
     */
    public float getCertainty() {
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
    protected synchronized Consequent getConsequentAt(final int i) throws ArrayIndexOutOfBoundsException{
        return (Consequent) consequents.elementAt(i);
    }

    /**
     * Returns an enumeration of consequents.
     *
     * @return an enumeration of consequents
     */
    public synchronized Enumeration getConsequents() {
        return consequents.elements();
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
    public synchronized int getSizeOfAntecedents() {
        return antecedents.size();
    }

    /**
     * Returns the number of consequents.
     *
     * @return the number of conclusions
     */
    public synchronized int getSizeOfConsequents() {
        return consequents.size();
    }

    /**
     * Adds a premise to the rule.
     * @see #addAntecedent(Antecedent)
     * @param lv the linguistic variable
     * @param lingTermName name of a linguistic term which is member of <code>lv</code>
     */
    public synchronized void if_(final LinguisticVariable lv,
            final String lingTermName) {
        addAntecedent(lv, lingTermName);
    }

    /**
     * Adds a premise to the rule.
     * @see #addAntecedent(Antecedent)
     * @param lv the linguistic variable
     * @param lingTermName name of a linguistic term which is member of <code>lv</code>
     * @param compOp the compatibility operator
     */
    public synchronized void if_(final LinguisticVariable lv,
            final String lingTermName, final AbstractOperator compOp) {
        addAntecedent(lv, lingTermName, compOp);
    }

    /**
     * Removes a premise.
     *
     * @param ante
     *            the antecedent for removing
     * @return <code>true</code> if antecedent did really exist, <code>false</code> otherwise
     */
    protected synchronized boolean removeAntecedent(final Antecedent ante) {
        if (ante != null)
            return antecedents.removeElement(ante);
        return false;
    }

    /**
     * Removes a premise.
     *
     * @param lv
     *            the linguistic variable of the consequent
     * @param lingTermName
     *            the name of the linguistic term
     * @return <code>true</code> if antecedent did really exist, <code>false</code> otherwise
     * @exception NullPointerException
     *                if at least one parameter is <code>null</code>
     */
    public synchronized boolean removeAntecedent(final LinguisticVariable lv,
            final String lingTermName) throws NullPointerException{
        return antecedents.removeElement(new Antecedent(lv, lingTermName));
    }

    /**
     * Removes a premise.
     *
     * @return <code>true</code> if antecedent did really exist, <code>false</code> otherwise
     * @param lv the linguistic variable of the antecedent
     * @param lingTermName the name of the linguistic term
     * @param compOp the fuzzy operator for examining the compatibility
     * @exception NullPointerException
     *                if at least one parameter is <code>null</code>
     * @exception IllegalArgumentException if the fuzzy operator does not fulfill the t-norm
     */
    public synchronized boolean removeAntecedent(final LinguisticVariable lv,
            final String lingTermName, final AbstractOperator compOp) throws NullPointerException, IllegalArgumentException{
        return antecedents.removeElement(new Antecedent(lv, lingTermName,
                compOp));
    }

    /**
     * Removes a conclusion.
     *
     * @param cons
     *            the consequent for removing
     * @return <code>true</code> if consequent did really exist, <code>false</code> otherwise
     */
    protected synchronized boolean removeConsequent(final Consequent cons) {
        if (cons != null)
            return consequents.removeElement(cons);
        return false;
    }

    /**
     * Removes a conclusion.
     *
     * @param lv
     *            the linguistic variable of the consequent
     * @param lingTermName
     *            the name of the linguistic term
     * @return <code>true</code> if consequent did really exist, <code>false</code> otherwise
     * @exception NullPointerException
     *                if at least one parameter is <code>null</code>
     */
    public synchronized boolean removeConsequent(final LinguisticVariable lv,
            final String lingTermName) throws NullPointerException{
        return consequents.removeElement(new Consequent(lv, lingTermName));
    }

    /**
     * Sets the fuzzy operator for aggregation.
     *
     * @param aggOp
     *            the new fuzzy operator for aggregation
     * @return the previous fuzzy operator
     * @exception NullPointerException
     *                if <code>aggOp</code> is <code>null</code>
     */
    public synchronized AbstractOperator setAggregationOperator(final AbstractOperator aggOp) throws NullPointerException{
        if (aggOp != null) {
            AbstractOperator oldAggOp = aggregationOp;
            aggregationOp = aggOp;
            return oldAggOp;
        } else
            throw new NullPointerException();
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
    protected synchronized void setAntecedentAt(final Antecedent ante,
            final int i) throws ArrayIndexOutOfBoundsException{
        antecedents.setElementAt(ante, i);
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
    protected synchronized void setConsequentAt(final Consequent cons,
            final int i) throws ArrayIndexOutOfBoundsException{
        consequents.setElementAt(cons, i);
    }

    /**
     * Sets the certainty factor.
     *
     * @param cert
     *            the new certainty
     * @return the previous certainty
     * @exception IllegalArgumentException
     *                if <code>cert</code> is not in <tt>[0,1]</tt>
     */
    public synchronized float setCertainty(final float cert) throws IllegalArgumentException{
        if ((cert >= 0.0f) && (cert <= 1.0f)) {
            float oldCert = certainty;
            certainty = cert;
            return oldCert;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_CERTAINTY",
                    new Object[] { Float.toString(cert) }));
    }

    /**
     * Sets the certainty operator. It has to fulfill the t-norm.
     *
     * @param certOp the new certainty operator
     * @return the previous operator
     * @exception NullPointerException if <code>certOp</code> is <code>null</code>
     * @exception IllegalArgumentException if the operator does not fulfill the t-norm
     */
    public synchronized AbstractOperator setCertaintyOperator(final AbstractOperator certOp) throws NullPointerException, IllegalArgumentException{
        if (certOp.isValidTNorm()) {
            AbstractOperator oldCertOp = certaintyOp;
            certaintyOp = certOp;
            return oldCertOp;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager
                    .getString(this, "EXCEPTION_INVALID_T_NORM_OPERATOR"));
    }

    /**
     * Sets the default fuzzy operator for aggregation.
     *
     * @param aggOp the new default aggregation operator
     * @return the previous operator
     * @exception NullPointerException if <code>aggOp</code> is <code>null</code>
     */
    public static synchronized AbstractOperator setDefaultAggregationOperator(
            final AbstractOperator aggOp) throws NullPointerException{
        if (aggOp != null) {
            AbstractOperator oldAggOp = defaultAggregationOperator;
            defaultAggregationOperator = aggOp;
            return oldAggOp;
        } else
            throw new NullPointerException();
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
     * @return the previous certainty
     * @exception IllegalArgumentException
     *                if <code>cert</code> is not in <tt>[0,1]</tt>
     */
    public static synchronized float setDefaultCertainty(final float cert) throws IllegalArgumentException{
        if ((cert >= 0.0f) && (cert <= 1.0f)) {
            float oldCert = defaultCertainty;
            defaultCertainty = cert;
            return oldCert;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    Rule.class, "EXCEPTION_INVALID_CERTAINTY",
                    new Object[] { Float.toString(cert) }));
    }

    /**
     * Sets the default certainty operator. It has to fulfill the t-norm.
     *
     * @param certOp the new default certainty operator
     * @return the previous operator
     * @exception NullPointerException if <code>certOp</code> is <code>null</code>
     * @exception IllegalArgumentException if the operator does not fulfill the t-norm
     */
    public static synchronized AbstractOperator setDefaultCertaintyOperator(
            final AbstractOperator certOp) throws NullPointerException, IllegalArgumentException{
        if (certOp.isValidTNorm()) {
            AbstractOperator oldCertOp = defaultCertaintyOperator;
            defaultCertaintyOperator = certOp;
            return oldCertOp;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager
                    .getString(Rule.class, "EXCEPTION_INVALID_T_NORM_OPERATOR"));
    }

    /**
     * Sets the default inference operator. It has to fulfill the t-norm.
     *
     * @param infOp the new default inference operator
     * @return the previous operator
     * @exception NullPointerException if <code>infOp</code> is <code>null</code>
     * @exception IllegalArgumentException if the operator does not fulfill the t-norm
     */
    public static synchronized AbstractOperator setDefaultInferenceOperator(
            final AbstractOperator infOp) throws NullPointerException, IllegalArgumentException{
        if (infOp.isValidTNorm()) {
            AbstractOperator oldInfOp = defaultInferenceOperator;
            defaultInferenceOperator = infOp;
            return oldInfOp;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager
                    .getString(Rule.class, "EXCEPTION_INVALID_T_NORM_OPERATOR"));
    }

    /**
     * Sets the inference operator. It has to fulfill the t-norm.
     *
     * @param infOp the new inference operator
     * @return the previous operator
     * @exception NullPointerException if <code>infOp</code> is <code>null</code>
     * @exception IllegalArgumentException if the operator does not fulfill the t-norm
     */
    public synchronized AbstractOperator setInferenceOperator(final AbstractOperator infOp) throws NullPointerException, IllegalArgumentException{
        if (infOp.isValidTNorm()) {
            AbstractOperator oldInfOp = inferenceOp;
            inferenceOp = infOp;
            return oldInfOp;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager
                    .getString(this, "EXCEPTION_INVALID_T_NORM_OPERATOR"));
    }

    /**
     * Adds a conclusion to this rule.
     *
     * @param lv
     *            the linguistic variable of the consequent
     * @param lingTermName
     *            the name of the linguistic term
     * @exception NullPointerException
     *                if at least one parameter is <code>null</code>
     * @see #addConsequent(Consequent)
     */
    public synchronized void then_(final LinguisticVariable lv,
            final String lingTermName) throws NullPointerException{
        addConsequent(lv, lingTermName);
    }

    /**
     * Creates and returns a copy of this rule.
     *
     * @return a copy of this rule
     */
    public Object clone() {
        try {
            Rule newObj = (Rule) super.clone();
            // Duplicate items physically.
            newObj.antecedents = new java.util.Vector(antecedents.size());
            Enumeration elements = antecedents.elements();
            while (elements.hasMoreElements())
                newObj.antecedents.addElement(((Antecedent) elements
                        .nextElement()).clone());
            newObj.consequents = new java.util.Vector(consequents.size());
            elements = consequents.elements();
            while (elements.hasMoreElements())
                newObj.consequents.addElement(((Consequent) elements
                        .nextElement()).clone());
            return newObj;
        } catch (java.lang.CloneNotSupportedException e) {
            // kann nicht auftreten
            throw new InternalError(e.toString());
        }
    }

    /**
     * Indicates whether some other object is "equal to" this rule
     *
     * @param obj
     *            the reference object with which to compare
     * @return <code>true</code> if this rule is the same as the
     *         <code>obj</code> argument, <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof Rule)) {
            Rule r = (Rule) obj;
            if ((certainty != r.certainty)
            || (!aggregationOp.equals(r.aggregationOp))
            || (!certaintyOp.equals(r.certaintyOp))
            || (!inferenceOp.equals(r.inferenceOp)))
                return false;
            // Comparing sizes of antecedents and consequents
            if ((antecedents.size() != r.antecedents.size())
            || (consequents.size() != r.consequents.size()))
                return false;
            // Comparing antecedents pairwise
            Enumeration elementsAnteThis = this.antecedents.elements();
            Enumeration elementsAnteObj = r.antecedents.elements();
            while (elementsAnteThis.hasMoreElements()) {
                if (!elementsAnteThis.nextElement().equals(
                        elementsAnteObj.nextElement()))
                    return false;
            }
            // Comparing consequents pairwise
            Enumeration elementsConThis = this.consequents.elements();
            Enumeration elementsConObj = r.consequents.elements();
            while (elementsConThis.hasMoreElements()) {
                if (!elementsConThis.nextElement().equals(
                        elementsConObj.nextElement()))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns a textual representation of the rule
     *
     * @return a string representation of the rule
     */
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
        Enumeration elements = antecedents.elements();
        if (elements.hasMoreElements()) {
            ante = (Antecedent) elements.nextElement();
            text = text + ante.toString();
            if (!oneLine) {
                AbstractOperator op = ante.getCompatibilityOperator();
                if (op.requiresParameter())
                    text = text + " ("
                            + ((AbstractParameteredOperator) op).toString(true) + ")";
                else
                    text = text + " (" + op.toString() + ")";
            }
        }
        while (elements.hasMoreElements()) {
            ante = (Antecedent) elements.nextElement();
            if (!oneLine)
                text = text + "\n    ";
            if (aggregationOp.isValidTNorm())
                text = text + " " + FuzzyResourceManager.getString(this, "RULE_AND")
                + " " + ante.toString();
            else
                text = text + " " + FuzzyResourceManager.getString(this, "RULE_OR")
                + " " + ante.toString();
            if (!oneLine) {
                AbstractOperator op = ante.getCompatibilityOperator();
                if (op.requiresParameter())
                    text = text + " ("
                            + ((AbstractParameteredOperator) op).toString(true) + ")";
                else
                    text = text + " (" + op.toString() + ")";
            }
        }
        text = text + ", ";
        if (!oneLine)
            text = text + "\n";
        text = text + FuzzyResourceManager.getString(this, "RULE_THEN") + " ";
        // Iterating the consequents.
        elements = consequents.elements();
        if (elements.hasMoreElements())
            text = text + ((Consequent) elements.nextElement()).toString();
        while (elements.hasMoreElements()) {
            if (!oneLine)
                text = text + "\n    ";
            text = text + " " + FuzzyResourceManager.getString(this, "RULE_AND")
            + " " + ((Consequent) elements.nextElement()).toString();
        }
        if (!oneLine)
            text = text + "\n    ";
        text = text
                + FuzzyResourceManager.getString(this, "RULE_CERTAINTY",
                new Object[] { Float.toString(certainty) });
        return text;
    }
}
