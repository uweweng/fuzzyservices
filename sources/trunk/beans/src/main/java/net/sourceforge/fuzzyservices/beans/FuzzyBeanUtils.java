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
package net.sourceforge.fuzzyservices.beans;

import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.core.operator.AbstractParameteredOperator;
import net.sourceforge.fuzzyservices.core.Antecedent;
import net.sourceforge.fuzzyservices.core.Consequent;
import net.sourceforge.fuzzyservices.core.Fact;
import net.sourceforge.fuzzyservices.core.FactBase;
import net.sourceforge.fuzzyservices.core.FuzzyInterval;
import net.sourceforge.fuzzyservices.core.FuzzyLRInterval;
import net.sourceforge.fuzzyservices.core.FuzzyLRNumber;
import net.sourceforge.fuzzyservices.core.FuzzyNumber;
import net.sourceforge.fuzzyservices.core.FuzzySet;
import net.sourceforge.fuzzyservices.core.LinguisticVariable;
import net.sourceforge.fuzzyservices.core.MembershipFunction;
import net.sourceforge.fuzzyservices.core.Rule;
import net.sourceforge.fuzzyservices.core.RuleBase;
import net.sourceforge.fuzzyservices.core.operator.OperatorManager;
import java.beans.PropertyVetoException;
import java.util.Iterator;

/**
 * Utility class for converting between core and beans objects.
 *
 * @since 1.0
 * @author Uwe Weng
 */
final class FuzzyBeanUtils {

    /**
     * Converts the fact bean to a basic fact.
     * @return A basic fact object
     * @param fact The fact bean to be converted
     * @param lv The linguistic variable beans
     */
    final static Fact convert(FactBean fact, LinguisticVariableBean[] lv) {
        if (fact != null) {
            Fact newFact = new Fact(convert(convert(
                    fact.getLinguisticVariableName(), lv)), convert(fact.getValue()));

            return newFact;
        }

        return null;
    }

    /**
     * Converts the fact base bean to a basic fact base.
     * @return A basic fact base object
     * @param factBase The fact base bean to be converted
     * @param lv The linguistic variable beans
     */
    final static FactBase convert(FactBaseBean factBase, LinguisticVariableBean[] lv) {
        if (factBase != null) {
            FactBase newFactBase = new FactBase();
            newFactBase.setName(factBase.getName());

            FactBean[] facts = factBase.getFacts();

            if (facts != null) {
                for (int i = 0; i < facts.length; i++) {
                    if (facts[i] != null) {
                        newFactBase.add(convert(facts[i], lv));
                    }
                }
            }

            return newFactBase;
        }

        return null;
    }

    /**
     * Converts the fuzzy interval bean to a basic fuzzy interval.
     * @return A basic fuzzy interval object
     * @param fuzzyInterval The fuzzy interval bean to be converted
     */
    final static FuzzyInterval convert(FuzzyIntervalBean fuzzyInterval) {
        if (fuzzyInterval != null) {
            FuzzySet newFuzzySet = new FuzzySet();
            MembershipFunctionPointBean[] points = fuzzyInterval.getMembershipFunction();

            if (points != null) {
                for (int i = 0; i < points.length; i++) {
                    if (points[i] != null) {
                        newFuzzySet.set(points[i].getX(),
                                points[i].getDegreeOfMembership());
                    }
                }
            }

            return new FuzzyInterval(newFuzzySet);
        }

        return null;
    }

    /**
     * Converts the fuzzy LR interval bean to a basic fuzzy LR interval.
     * @return A basic fuzzy LR interval object
     * @param fuzzyLRInterval The fuzzy LR interval bean to be converted
     */
    final static FuzzyLRInterval convert(
            FuzzyLRIntervalBean fuzzyLRInterval) {
        if (fuzzyLRInterval != null) {
            FuzzySet newFuzzySet = new FuzzySet();
            MembershipFunctionPointBean[] points = fuzzyLRInterval.getMembershipFunction();

            if (points != null) {
                for (int i = 0; i < points.length; i++) {
                    if (points[i] != null) {
                        newFuzzySet.set(points[i].getX(),
                                points[i].getDegreeOfMembership());
                    }
                }
            }

            return new FuzzyLRInterval(newFuzzySet);
        }

        return null;
    }

    /**
     * Converts the fuzzy LR number bean to a basic fuzzy LR number.
     * @return A basic fuzzy LR number object
     * @param fuzzyLRNumber The fuzzy LR number bean to be converted
     */
    final static FuzzyLRNumber convert(FuzzyLRNumberBean fuzzyLRNumber) {
        if (fuzzyLRNumber != null) {
            FuzzySet newFuzzySet = new FuzzySet();
            MembershipFunctionPointBean[] points = fuzzyLRNumber.getMembershipFunction();

            if (points != null) {
                for (int i = 0; i < points.length; i++) {
                    if (points[i] != null) {
                        newFuzzySet.set(points[i].getX(),
                                points[i].getDegreeOfMembership());
                    }
                }
            }

            return new FuzzyLRNumber(newFuzzySet);
        }

        return null;
    }

    /**
     * Converts the fuzzy number bean to a basic fuzzy number.
     * @return A basic fuzzy number object
     * @param fuzzyNumber The fuzzy number bean to be converted
     */
    final static FuzzyNumber convert(FuzzyNumberBean fuzzyNumber) {
        if (fuzzyNumber != null) {
            FuzzySet newFuzzySet = new FuzzySet();
            MembershipFunctionPointBean[] points = fuzzyNumber.getMembershipFunction();

            if (points != null) {
                for (int i = 0; i < points.length; i++) {
                    if (points[i] != null) {
                        newFuzzySet.set(points[i].getX(),
                                points[i].getDegreeOfMembership());
                    }
                }
            }

            return new FuzzyNumber(newFuzzySet);
        }

        return null;
    }

    /**
     * Converts the fuzzy set bean to a basic fuzzy set.
     * @return A basic fuzzy set object
     * @param fuzzySet The fuzzy set bean to be converted
     */
    final static FuzzySet convert(FuzzySetBean fuzzySet) {
        if (fuzzySet != null) {
            FuzzySet newFuzzySet = new FuzzySet();
            MembershipFunctionPointBean[] points = fuzzySet.getMembershipFunction();

            if (points != null) {
                for (int i = 0; i < points.length; i++) {
                    if (points[i] != null) {
                        newFuzzySet.set(points[i].getX(),
                                points[i].getDegreeOfMembership());
                    }
                }
            }

            return newFuzzySet;
        }

        return null;
    }

    /**
     * Determines the linguistic variable bean by name.
     * @return A linguistic variable bean object
     * @param name The name of a linguistic variable to be determined
     * @param lv The linguistic variable beans
     */
    final static LinguisticVariableBean convert(String name, LinguisticVariableBean[] lv) {
        for (int i = 0; i < lv.length; i++) {
            if (lv[i] != null) {
                if (name.equals(lv[i].getName()) == true) {
                    return lv[i];
                }
            }
        }
        return null;
    }

    /**
     * Converts the linguistic variable bean to a basic linguistic variable.
     * @return A basic linguistic variable object
     * @param linguisticVariable The linguistic variable bean to be converted
     */
    final static LinguisticVariable convert(
            LinguisticVariableBean linguisticVariable) {
        if (linguisticVariable != null) {
            LinguisticVariable newLinguisticVariable = new LinguisticVariable(linguisticVariable.getName());
            LinguisticTermBean[] terms = linguisticVariable.getLinguisticTerms();

            if (terms != null) {
                for (int i = 0; i < terms.length; i++) {
                    if (terms[i] != null) {
                        newLinguisticVariable.set(terms[i].getName(),
                                convert(terms[i].getFuzzySet()));
                    }
                }
            }

            return newLinguisticVariable;
        }

        return null;
    }

    /**
     * Converts the operator bean to a basic operator.
     * @return A basic operator object
     * @param operator The operator bean to be converted
     */
    final static AbstractOperator convert(OperatorBean operator) {
        if (operator != null) {
            AbstractOperator op = OperatorManager.getOperator(operator.getType());

            if (op != null) {
                if (op.requiresParameter() == true) {
                    ((AbstractParameteredOperator) op).setParameter(
                            operator.getParameter());
                }

                return op;
            }
        }

        return null;
    }

    /**
     * Converts the rule bean to a basic rule.
     * @return A basic rule object
     * @param rule The rule bean to be converted
     * @param lv The linguistic variable beans
     */
    final static Rule convert(RuleBean rule, LinguisticVariableBean[] lv) {
        if (rule != null) {
            Rule newRule = new Rule();
            newRule.setAggregationOperator(convert(
                    rule.getAggregationOperator()));
            newRule.setCertaintyOperator(convert(rule.getCertaintyOperator()));
            newRule.setInferenceOperator(convert(rule.getInferenceOperator()));
            newRule.setCertainty(rule.getCertainty());

            AntecedentBean[] antecedents = rule.getAntecedents();

            if (antecedents != null) {
                for (int i = 0; i < antecedents.length; i++) {
                    newRule.addAntecedent(convert(
                            convert(antecedents[i].getLinguisticVariableName(), lv)),
                            antecedents[i].getLinguisticTermName(),
                            convert(antecedents[i].getCompatibilityOperator()));
                }
            }

            ConsequentBean[] consequents = rule.getConsequents();

            if (consequents != null) {
                for (int i = 0; i < consequents.length; i++) {
                    newRule.addConsequent(convert(
                            convert(consequents[i].getLinguisticVariableName(), lv)),
                            consequents[i].getLinguisticTermName());
                }
            }
        }

        return null;
    }

    /**
     * Converts the rule base bean to a basic rule base.
     * @return A basic rule base object
     * @param ruleBase The rule base bean to be converted
     * @param lv The linguistic variable beans
     */
    final static RuleBase convert(RuleBaseBean ruleBase, LinguisticVariableBean[] lv) {
        if (ruleBase != null) {
            RuleBase newRuleBase = new RuleBase();
            newRuleBase.setName(ruleBase.getName());
            newRuleBase.setAccumulationOperator(convert(
                    ruleBase.getAccumulationOperator()));

            RuleBean[] rules = ruleBase.getRules();

            if (rules != null) {
                for (int i = 0; i < rules.length; i++) {
                    if (rules[i] != null) {
                        newRuleBase.add(convert(rules[i], lv));
                    }
                }
            }

            return newRuleBase;
        }

        return null;
    }

    /**
     * Converts the fact bean to a basic fact.
     * @return A basic fact object
     * @param fact The fact bean to be converted
     */
    final static FactBean convert(Fact fact) {
        if (fact != null) {
            FactBean newFact = new FactBean();

            try {
                newFact.setLinguisticVariableName(
                        fact.getLinguisticVariable().getName());
                newFact.setValue(convert(fact.get()));
            } catch (PropertyVetoException e) {
            // do nothing
            }

            return newFact;
        }

        return null;
    }

    /**
     * Converts the fact base to a fact base bean.
     * @return A fact base bean
     * @param factBase The fact base to be converted
     */
    final static FactBaseBean convert(FactBase factBase) {
        if (factBase != null) {
            FactBaseBean newFactBase = new FactBaseBean();
            newFactBase.setName(factBase.getName());

            int size = factBase.size();

            if (size > 0) {
                FactBean[] newFacts = new FactBean[size];
                int i = 0;

                for (Iterator<Fact> it = factBase.iterator(); it.hasNext();) {
                    newFacts[i] = convert(it.next());
                    i++;
                }

                newFactBase.setFacts(newFacts);
            }

            return newFactBase;
        }

        return null;
    }

    /**
     * Converts the fuzzy interval to a fuzzy interval bean.
     * @return A fuzzy interval bean
     * @param fuzzyInterval The fuzzy interval to be converted
     */
    final static FuzzyIntervalBean convert(FuzzyInterval fuzzyInterval) {
        if (fuzzyInterval != null) {
            FuzzyIntervalBean newFuzzyInterval = new FuzzyIntervalBean();
            newFuzzyInterval.setMembershipFunction(toMembershipFunction(
                    fuzzyInterval));

            return newFuzzyInterval;
        }

        return null;
    }

    /**
     * Converts the fuzzy LR interval to a fuzzy LR interval bean.
     * @return A fuzzy LR interval bean
     * @param fuzzyLRInterval The fuzzy LR interval to be converted
     */
    final static FuzzyLRIntervalBean convert(FuzzyLRInterval fuzzyLRInterval) {
        if (fuzzyLRInterval != null) {
            FuzzyLRIntervalBean newFuzzyLRInterval = new FuzzyLRIntervalBean();
            newFuzzyLRInterval.setMembershipFunction(toMembershipFunction(
                    fuzzyLRInterval));

            return newFuzzyLRInterval;
        }

        return null;
    }

    /**
     * Converts the fuzzy LR number to a fuzzy LR number bean.
     * @return A fuzzy LR number bean
     * @param fuzzyLRNumber The fuzzy LR number to be converted
     */
    final static FuzzyLRNumberBean convert(FuzzyLRNumber fuzzyLRNumber) {
        if (fuzzyLRNumber != null) {
            FuzzyLRNumberBean newFuzzyLRNumber = new FuzzyLRNumberBean();
            newFuzzyLRNumber.setMembershipFunction(toMembershipFunction(
                    fuzzyLRNumber));

            return newFuzzyLRNumber;
        }

        return null;
    }

    /**
     * Converts the fuzzy number to a fuzzy number bean.
     * @return A fuzzy number bean
     * @param fuzzyNumber The fuzzy number to be converted
     */
    final static FuzzyNumberBean convert(FuzzyNumber fuzzyNumber) {
        if (fuzzyNumber != null) {
            FuzzyNumberBean newFuzzyNumber = new FuzzyNumberBean();
            newFuzzyNumber.setMembershipFunction(toMembershipFunction(
                    fuzzyNumber));

            return newFuzzyNumber;
        }

        return null;
    }

    /**
     * Converts the fuzzy set to a fuzzy set bean.
     * @return A fuzzy set bean
     * @param fuzzySet The fuzzy set to be converted
     */
    final static FuzzySetBean convert(FuzzySet fuzzySet) {
        if (fuzzySet != null) {
            FuzzySetBean newFuzzySet = new FuzzySetBean();
            newFuzzySet.setMembershipFunction(toMembershipFunction(fuzzySet));

            return newFuzzySet;
        }

        return null;
    }

    /**
     * Converts the linguistic variable to a linguistic variable bean.
     * @return A linguistic variable bean
     * @param linguisticVariable The linguistic variable to be converted
     */
    final static LinguisticVariableBean convert(
            LinguisticVariable linguisticVariable) {
        if (linguisticVariable != null) {
            LinguisticVariableBean newLinguisticVariable = new LinguisticVariableBean();

            try {
                newLinguisticVariable.setName(linguisticVariable.getName());

                int size = linguisticVariable.size();

                if (size > 0) {
                    LinguisticTermBean[] terms = new LinguisticTermBean[size];
                    int i = 0;

                    for (Iterator<String> it = linguisticVariable.getNames(); it.hasNext();) {

                        String name = it.next();
                        LinguisticTermBean newLinguisticTerm = new LinguisticTermBean();
                        newLinguisticTerm.setName(name);
                        newLinguisticTerm.setFuzzySet(convert(
                                linguisticVariable.getFuzzySet(name)));
                        terms[i] = newLinguisticTerm;
                        i++;
                    }

                    newLinguisticVariable.setLinguisticTerms(terms);
                }
            } catch (PropertyVetoException e) {
            // do nothing
            }

            return newLinguisticVariable;
        }

        return null;
    }

    /**
     * Converts the operator to an operator bean.
     * @return An operator bean
     * @param operator The operator to be converted
     */
    final static OperatorBean convert(AbstractOperator operator) {
        if (operator != null) {
            OperatorBean op = new OperatorBean();

            try {
                op.setType(operator.toString());

                if (operator.requiresParameter() == true) {
                    AbstractParameteredOperator parameterOperator = (AbstractParameteredOperator) operator;
                    op.setParameter(parameterOperator.getParameter());
                }
            } catch (PropertyVetoException e) {
            // do nothing
            }

            return op;
        }

        return null;
    }

    /**
     * Converts the rule to a rule bean.
     * @return A rule bean
     * @param rule The rule to be converted
     */
    final static RuleBean convert(Rule rule) {
        if (rule != null) {
            RuleBean newRule = new RuleBean();
            newRule.setAggregationOperator(convert(
                    rule.getAggregationOperator()));
            newRule.setCertaintyOperator(convert(rule.getCertaintyOperator()));
            newRule.setInferenceOperator(convert(rule.getInferenceOperator()));
            newRule.setCertainty(rule.getCertainty());

            int sizeOfAntecedents = rule.getSizeOfAntecedents();

            if (sizeOfAntecedents > 0) {
                AntecedentBean[] newAntecedents = new AntecedentBean[sizeOfAntecedents];
                int i = 0;

                for (Iterator<Antecedent> it = rule.getAntecedents(); it.hasNext();) {
                    Antecedent antecedent = it.next();
                    AntecedentBean newAntecedent = new AntecedentBean();
                    newAntecedent.setCompatibilityOperator(convert(
                            antecedent.getCompatibilityOperator()));

                    newAntecedent.setLinguisticVariableName(antecedent.getLinguisticVariable().getName());
                    newAntecedent.setLinguisticTermName(antecedent.getLinguisticTermName());
                    newAntecedents[i] = newAntecedent;
                    i++;
                }

                newRule.setAntecedents(newAntecedents);
            }

            int sizeOfConsequents = rule.getSizeOfConsequents();

            if (sizeOfConsequents > 0) {
                ConsequentBean[] newConsequents = new ConsequentBean[sizeOfConsequents];
                int i = 0;

                for (Iterator<Consequent> it = rule.getConsequents(); it.hasNext();) {
                    Consequent consequent = it.next();

                    ConsequentBean newConsequent = new ConsequentBean();
                    newConsequent.setLinguisticVariableName(consequent.getLinguisticVariableName());
                    newConsequent.setLinguisticTermName(consequent.getLinguisticTermName());
                    newConsequents[i] = newConsequent;
                    i++;
                }

                newRule.setConsequents(newConsequents);
            }
        }

        return null;
    }

    /**
     * Converts the rule base to a rule base bean.
     * @return A rule base bean
     * @param ruleBase The rule base to be converted
     */
    final static RuleBaseBean convert(RuleBase ruleBase) {
        if (ruleBase != null) {
            RuleBaseBean newRuleBase = new RuleBaseBean();
            newRuleBase.setName(ruleBase.getName());
            newRuleBase.setAccumulationOperator(convert(
                    ruleBase.getAccumulationOperator()));

            int size = ruleBase.size();

            if (size > 0) {
                RuleBean[] newRules = new RuleBean[size];
                int i = 0;

                for (Iterator<Rule> it = ruleBase.iterator(); it.hasNext();) {
                    newRules[i] = convert(it.next());
                    i++;
                }

                newRuleBase.setRules(newRules);
            }

            return newRuleBase;
        }

        return null;
    }

    /**
     * Creates a fuzzy interval based on the <code>points</code>.
     * @return A fuzzy interval
     * @param points The points representing the membership function of the new
     * fuzzy interval
     */
    final static FuzzyInterval createFuzzyInterval(
            MembershipFunctionPointBean[] points) {
        return new FuzzyInterval(createFuzzySet(points));
    }

    /**
     * Creates a fuzzy LR interval based on the <code>points</code>.
     * @return A fuzzy LR interval
     * @param points The points representing the membership function of the new
     * fuzzy LR interval
     */
    final static FuzzyLRInterval createFuzzyLRInterval(
            MembershipFunctionPointBean[] points) {
        return new FuzzyLRInterval(createFuzzySet(points));
    }

    /**
     * Creates a fuzzy LR number based on the <code>points</code>.
     * @return A fuzzy LR number
     * @param points The points representing the membership function of the new
     * fuzzy LR number
     */
    final static FuzzyLRNumber createFuzzyLRNumber(
            MembershipFunctionPointBean[] points) {
        return new FuzzyLRNumber(createFuzzySet(points));
    }

    /**
     * Creates a fuzzy number based on the <code>points</code>.
     * @return A fuzzy number
     * @param points The points representing the membership function of the new
     * fuzzy number
     */
    final static FuzzyNumber createFuzzyNumber(
            MembershipFunctionPointBean[] points) {
        return new FuzzyNumber(createFuzzySet(points));
    }

    /**
     * Creates a fuzzy set based on the <code>points</code>.
     * @return A fuzzy set
     * @param points The points representing the membership function of the new
     * fuzzy set
     */
    final static FuzzySet createFuzzySet(MembershipFunctionPointBean[] points) {
        FuzzySet fuzzySet = new FuzzySet();

        if (points != null) {
            for (int i = 0; i < points.length; i++) {
                fuzzySet.set(points[i].getX(), points[i].getDegreeOfMembership());
            }
        }

        return fuzzySet;
    }

    /**
     * Converts the points of a membership function to beans.
     * @return An array of point beans
     * @param membershipFunction The membership function which points are
     * converted to beans
     */
    final static MembershipFunctionPointBean[] toMembershipFunction(
            MembershipFunction membershipFunction) {
        if (membershipFunction != null) {
            MembershipFunctionPointBean[] points = new MembershipFunctionPointBean[membershipFunction.size()];
            int i = 0;

            for (Iterator<Float> it = membershipFunction.iterator(); it.hasNext();) {
                float x = it.next();
                points[i] = new MembershipFunctionPointBean(x,
                        membershipFunction.getDegreeOfMembership(x));
                i++;
            }

            return points;
        }

        return null;
    }
}


