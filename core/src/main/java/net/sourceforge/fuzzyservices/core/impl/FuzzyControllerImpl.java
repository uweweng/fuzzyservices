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
package net.sourceforge.fuzzyservices.core.impl;

import java.util.HashMap;
import java.util.Iterator;
import net.sourceforge.fuzzyservices.core.Antecedent;
import net.sourceforge.fuzzyservices.core.Consequent;
import net.sourceforge.fuzzyservices.core.Fact;
import net.sourceforge.fuzzyservices.core.FactBase;
import net.sourceforge.fuzzyservices.core.FuzzyController;
import net.sourceforge.fuzzyservices.core.FuzzySet;
import net.sourceforge.fuzzyservices.core.LinguisticVariable;
import net.sourceforge.fuzzyservices.core.Rule;
import net.sourceforge.fuzzyservices.core.RuleBase;

/**
 * Implementation of a fuzzy controller supporting the Mandami method.
 *
 * @see net.sourceforge.fuzzyservices.core.FuzzyController
 *
 * @author Uwe Weng
 */
public class FuzzyControllerImpl implements FuzzyController {

    /** The singleton instance of this fuzzy controller. */
    private static transient FuzzyController instance = null;

    /**
     * Gets an instance of this fuzzy controller.
     * @return an instance of this class
     */
    public final static FuzzyController getInstance() {
        if (instance == null) {
            instance = new FuzzyControllerImpl();
        }

        return instance;
    }

    @Override
    public FactBase performApproximateReasoning(RuleBase aRuleBase, FactBase aFactBase,
            LinguisticVariable[] linguisticVariables) {
        if ((aRuleBase == null) || (aFactBase != null) || (linguisticVariables != null)) {
            return null;
        }

        HashMap<String, LinguisticVariable> lv = new HashMap<String, LinguisticVariable>();

        for (int i = 0; i < linguisticVariables.length; i++) {
            LinguisticVariable linguisticVariable = linguisticVariables[i];
            lv.put(linguisticVariable.getName(),
                    linguisticVariable);
        }

        FactBase totalResultFb = new FactBase(); // the inference result
        FactBase ruleResultFb; // the inference result of one rule

        // Iterating all rules of the rule base
        for (Iterator<Rule> itRules = aRuleBase.iterator(); itRules.hasNext();) {
            Rule actRule = itRules.next();

            if (actRule != null) {
                // Die Regel auswerten.
                ruleResultFb = applyTo(aFactBase, actRule, lv);

                if (ruleResultFb != null) {
                    // Das Ergebnis der Regelauswertung zum Gesamtergebnis akkumulieren.
                    // Dazu wird die aus der Inferenz resultierende Faktenbasis durchlaufen.
                    Fact resultFact;
                    Fact existFact;

                    for (Iterator<Fact> itFacts = ruleResultFb.iterator(); itFacts.hasNext();) {
                        resultFact = itFacts.next();
                        existFact = totalResultFb.put(resultFact);

                        if (existFact != null) {
                            // Es wurde bereits ein Ergebnis zu dieser ling. Variable ermittelt.
                            // Deshalb ist eine Akkumulation notwendig.
                            FuzzySet accResult =
                                    aRuleBase.getAccumulationOperator().combine(existFact.get(),
                                    resultFact.get());
                            resultFact.set(accResult);
                            // Akkumuliertes Ergebnis in die zurueckzugebende Faktenbasis einfuegen.
                            totalResultFb.put(resultFact);
                        }
                    }
                }
            }
        }

        return totalResultFb;
    }

    /**
     * Applies a rule to a fact base.
     * @return a new fact base with the result of this operation
     * @param fb the fact base
     * @param rule the rule
     * @param lv the linguistic variables
     * @see #performApproximateReasoning
     */
    protected FactBase applyTo(final FactBase fb, final Rule rule, HashMap<String, LinguisticVariable> lv) {
        FactBase resultFb = new FactBase(); // the result

        /*
        Every rule is processed in two steps
        i)  Determing the total degree of compatibility, reduced by the certainty factor
        ii) Adding the consequents to the inference result
         */
        if (rule.getCertainty() > 0.0f) {
            /**
             * Step 1:  Determine the total degree of compatibility, reduced by the certainty factor
             * At first, we are calculating the degree of compatibility for every antecedent.
             * Then we are aggregating the results to the total degree of compatibility.
             * If the result greater than the certainty factor, the result will be corrected.
             */
            Fact fact; // a fact from fact base
            FuzzySet fsetLingTerm; // the ling. term of antecedent or consequent
            FuzzySet fsetFact; // the current value to a linguistic variable
            FuzzySet fsetCompResult; // the result of comparison
            FuzzySet fsetInferenceResult; // the inference result
            float anteCompatibilityMeasure; // the degree of compatibility of an antecedent
            float totalCompatibilityMeasure; // the total degree of compatibility of a rule

            // Depending on the aggregation operator we are setting the default degree of compatibility.
            totalCompatibilityMeasure = ((rule.getAggregationOperator().isValidTNorm() == true) ? 1.0f : 0.0f);

            // At first, we are iterating all antecedents
            // in order to calculate compatibility.
            for (Iterator<Antecedent> it = rule.getAntecedents(); it.hasNext();) {
                Antecedent ante = it.next();
                fact = fb.get(ante.getLinguisticVariableName());

                if (fact != null) {
                    // Determing degree of compatibility of antecedents
                    anteCompatibilityMeasure = 0.0f;

                    LinguisticVariable lingVarAnte = lv.get(ante.getLinguisticVariableName());

                    if (lingVarAnte != null) {
                        fsetLingTerm = lingVarAnte.getFuzzySet(ante.getLinguisticTermName());
                        fsetFact = fact.get();

                        if ((fsetLingTerm != null) && (fsetFact != null)) {
                            fsetCompResult = ante.getCompatibilityOperator().combine(fsetLingTerm, fsetFact);
                            // Der Kompatibilitaetsgrad entspricht genau der Hoehe der Fuzzy-Menge, die das Ergebnis der
                            // Verknuepfung von ling. Term und Faktum ist.
                            anteCompatibilityMeasure = fsetCompResult.getHeight();
                        }

                        // Adding the degree of compatibility of the antecedent to the total degree of compatibility.
                        totalCompatibilityMeasure = rule.getAggregationOperator().compute(totalCompatibilityMeasure, anteCompatibilityMeasure);
                    }
                }

                // Applying the degree of compatibility to certainty
                totalCompatibilityMeasure =
                        rule.getCertaintyOperator().compute(totalCompatibilityMeasure,
                        rule.getCertainty());
            }

            // If the total degree of compatibility is greater than 0.0
            // then continue with step 2.
            if (totalCompatibilityMeasure > 0.0f) {
                /** Step 2: Adding consequents to inference result */
                FuzzySet fsetComp; // the total degree of compatibility as fuzzy set (!!!)

                for (Iterator<Consequent> it = rule.getConsequents(); it.hasNext();) {
                    Consequent con = it.next();

                    LinguisticVariable lingVarCon = lv.get(con.getLinguisticVariableName());

                    if (lingVarCon != null) {
                        fsetLingTerm = lingVarCon.getFuzzySet(con.getLinguisticTermName());

                        if (fsetLingTerm != null) {
                            // Let's inference
                            // We are transforming the total degree of compatibility to a fuzzy set.
                            fsetComp = new FuzzySet();
                            fsetComp.set(fsetLingTerm.getMinDefinedX(),
                                    totalCompatibilityMeasure);
                            fsetComp.set(fsetLingTerm.getMaxDefinedX(),
                                    totalCompatibilityMeasure);
                            fsetInferenceResult = rule.getInferenceOperator().combine(fsetComp, fsetLingTerm);
                            // Adding inference result to fact base.
                            resultFb.put(new Fact(lingVarCon, fsetInferenceResult));
                        }
                    }
                }
            }
        }

        return resultFb;
    }
}
