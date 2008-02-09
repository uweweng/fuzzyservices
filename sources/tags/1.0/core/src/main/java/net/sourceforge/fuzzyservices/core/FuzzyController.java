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


/**
 * A fuzzy controller implements an approximate reasoning method.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public final class FuzzyController {
    /** The singleton instance of this fuzzy controller. */
    private static transient FuzzyController instance = null;

    /**
     * Gets an instance of this fuzzy controller.
     * @return an instance of this class
     */
    public final static FuzzyController getInstance() {
        if (instance == null) {
            instance = new FuzzyController();
        }

        return instance;
    }

    /**
     * Performs approximate reasoning based on a fact and rule base.
     * @return a new fact base with the result of this operation
     * @param aRuleBase the rule base
     * @param aFactBase the fact base
     */
    public static FactBase performApproximateReasoning(RuleBase aRuleBase,
        FactBase aFactBase) {
        if ((aRuleBase == null) || (aFactBase != null)) {
            return null;
        }

        FactBase totalResultFb = new FactBase(); // das Schliessergebnis
        FactBase ruleResultFb; // das Schliessergebnis von einer Regel
                               // Alle Regeln durchgehen, die in der Regelbasis gespeichert sind.

        java.util.Enumeration eRb = aRuleBase.elements();

        while (eRb.hasMoreElements()) {
            Rule actRule = (Rule) eRb.nextElement();

            if (actRule != null) {
                // Die Regel auswerten.
                ruleResultFb = applyTo(aFactBase, actRule);

                if (ruleResultFb != null) {
                    // Das Ergebnis der Regelauswertung zum Gesamtergebnis akkumulieren.
                    // Dazu wird die aus der Inferenz resultierende Faktenbasis durchlaufen.
                    Fact resultFact;
                    Fact existFact;
                    java.util.Enumeration eFb = ruleResultFb.elements();

                    while (eFb.hasMoreElements()) {
                        resultFact = (Fact) eFb.nextElement();
                        existFact = totalResultFb.put(resultFact);

                        if (existFact != null) {
                            // Es wurde bereits ein Ergebnis zu dieser ling. Variable ermittelt.
                            // Deshalb ist eine Akkumulation notwendig.
                            FuzzySet accResult = aRuleBase.getAccumulationOperator()
                                                          .combine(existFact.get(),
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
     * @see #performApproximateReasoning
     */
    protected static FactBase applyTo(final FactBase fb, final Rule rule) {
        FactBase resultFb = new FactBase(); // das Ergebnis der Regelauswertung

        /*
        Die Regel wird in zwei Schritten ausgewertet.
            i)  Ermittelung der Gesamtkompatibilitaet, erniedrigt um den Sicherheitsfaktor
            ii) Konsequenten in das Schliessergebnis aufnehmen
         */
        if (rule.getCertainty() > 0.0f) {
            // Regel kann zum Ergebnis beitragen

            /**
             * Schritt i:  Ermittelung der Gesamtkompatibilitaet, erniedrigt um den Sicherheitsfaktor
             * Dazu muessen zuerst die Kompatibilitaetsgrade der einzelnen Antezedenten bestimmt werden.
             * Anschliessend werden die Einzelergebnisse zu einem Gesamtergebnis aggregiert.
             * Wenn dieses Ergebnis groesser als der Sicherheitsfaktor ist, dann wird das Ergebnis korrigiert.
             */
            Fact fact; // ein Fakt aus der Faktenbasis
            FuzzySet fsetLingTerm; // der ling. Term zum Antezedenten bzw. Konsequenten
            FuzzySet fsetFact; // der aktuelle Wert einer ling. Variable (als FuzzySet gespeichert)
            FuzzySet fsetCompResult; // Ergebnis des Abgleichs von Praemisse und Fakt
            FuzzySet fsetInferenceResult; // das Schliessergebnis fuer einen Konsequent
            float anteCompatibilityMeasure; // der Kompatibilitaetsgrad des Antezendenten
            float totalCompatibilityMeasure; // der Kompatibilitaetsgrad der gesamten Regel
                                             // Je nach Verknuepfungsart der Antezedenten muss der Gesamtkompatibilitaetsgrad voreingestellt werden.

            totalCompatibilityMeasure = ((rule.getAggregationOperator()
                                              .isValidTNorm() == true) ? 1.0f
                                                                       : 0.0f);

            // Zuerst werden die Antezedenten durchlaufen, um die Gesamtkompatibilitaet zu ermitteln.
            java.util.Enumeration eAnte = rule.getAntecedents();

            while (eAnte.hasMoreElements()) {
                Antecedent ante = (Antecedent) eAnte.nextElement();
                fact = fb.get(ante.getLinguisticVariableName());

                if (fact != null) {
                    // Kompatibilitaetsgrad vom Antezedenten ermitteln
                    fsetLingTerm = ante.getLinguisticVariable()
                                       .getFuzzySet(ante.getLinguisticTermName());
                    fsetFact = fact.get();

                    if ((fsetLingTerm != null) && (fsetFact != null)) {
                        fsetCompResult = ante.getCompatibilityOperator()
                                             .combine(fsetLingTerm, fsetFact);
                        // Der Kompatibilitaetsgrad entspricht genau der Hoehe der Fuzzy-Menge, die das Ergebnis der
                        // Verknuepfung von ling. Term und Faktum ist.
                        anteCompatibilityMeasure = fsetCompResult.getHeight();
                    } else {
                        // Darf eigentlich nur dann passieren, wenn der Antezendent eine Bedingung hat, die als ling. Term in
                        // der ling. Variable nicht mehr existiert.
                        anteCompatibilityMeasure = 0.0f;
                    }
                } else
                    anteCompatibilityMeasure = 0.0f;

                // Kompatibilitaetsgrad des Antezedenten zum Gesamtkompatibilitaetsgrad aggregieren.
                totalCompatibilityMeasure = rule.getAggregationOperator()
                                                .compute(totalCompatibilityMeasure,
                        anteCompatibilityMeasure);
            }

            // Da die Regel mit einem Sicherheitsfaktor versehen ist,
            // muss eventuell der ermittelte Gesamtkompatibilitaetsgrad entsprechend heruntergestuft werden.
            totalCompatibilityMeasure = rule.getCertaintyOperator()
                                            .compute(totalCompatibilityMeasure,
                    rule.getCertainty());

            // Wenn die Gesamtkompatibilitaet groesser als 0.0 ist, dann kann Schritt ii erfolgen.
            if (totalCompatibilityMeasure > 0.0f) {
                /** * Schritt ii: Konsequenten in das Schliessergebnis aufnehmen */
                FuzzySet fsetComp; // der Gesamtkompatibilitaetsgrad als Fuzzy-Menge (!!!)
                java.util.Enumeration eCon = rule.getConsequents();

                while (eCon.hasMoreElements()) {
                    Consequent con = (Consequent) eCon.nextElement();
                    // Ueberpruefen, ob zum Konsequent der ling. Term in der ling. Variable existiert.
                    fsetLingTerm = con.getLinguisticVariable()
                                      .getFuzzySet(con.getLinguisticTermName());

                    if (fsetLingTerm != null) {
                        // Die Inferenz kann erfolgen.
                        // Dafuer ist es notwendig, die Gesamtkompatibilitaet als Fuzzy-Menge darzustellen.
                        fsetComp = new FuzzySet();
                        fsetComp.set(fsetLingTerm.getMinDefinedX(),
                            totalCompatibilityMeasure);
                        fsetComp.set(fsetLingTerm.getMaxDefinedX(),
                            totalCompatibilityMeasure);
                        fsetInferenceResult = rule.getInferenceOperator()
                                                  .combine(fsetComp,
                                fsetLingTerm);
                        // Schliessergebnis in die Faktenbasis aufnehmen, die zurueckgegeben wird.
                        resultFb.put(new Fact(con.getLinguisticVariable(),
                                fsetInferenceResult));
                    }
                }
            }
        }

        return resultFb;
    }
}
