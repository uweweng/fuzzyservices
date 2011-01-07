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

/**
 * Utility class for creating Java Initialization Strings.
 *
 * @see RuleArrayEditor
 * @see FactArrayEditor
 * @see MembershipFunctionEditor
 * @see LinguisticTermArrayEditor
 * @see OperatorEditor
 *
 * @version 1.0
 * @author Uwe Weng
 */
public final class JavaInitializationStringFormatter {

    /**
     * An untility class should not have a public (default) constructor.
     */
    private JavaInitializationStringFormatter() {
        // Do nothing
    }

    /**
     * Returns Java code for creating <code>membershipFunction</code>.
     * @param membershipFunction the membership function as basis
     * @return Java code for creating the membership function
     */
    public static String getJavaInitializationStringForMembershipFunction(final MembershipFunction membershipFunction) {
        StringBuffer str = new StringBuffer();
        if (membershipFunction != null) {
            str.append("new MembershipFunction(");
            float[] xValues = membershipFunction.getXValues();
            if (xValues != null) {
                str.append("new MembershipFunctionPoint[] {");
                for (int i = 0; i < xValues.length; i++) {
                    float xValue = xValues[i];
                    if (i > 0) {
                        str.append(", ");
                    }
                    str.append("new MembershipFunctionPoint(");
                    str.append(xValue);
                    str.append(", ");
                    str.append(membershipFunction.getDegreeOfMembership(xValue));
                    str.append(")");
                }
                str.append("}");
            } else {
                str.append("null");
            }
            str.append(")");
        } else {
            str.append("null");
        }
        return str.toString();
    }

    /**
     * Returns Java code for creating <code>operator</code>.
     * @param operator the opertor as basis
     * @return Java code for creating the operator
     */
    public static String getJavaInitializationStringForOperator(final Operator operator) {
        StringBuffer str = new StringBuffer();
        if (operator != null) {
            str.append("new Operator(");
            str.append(operator.getType());
            str.append(", ");
            str.append(operator.getParameter());
            str.append(")");
        } else {
            str.append("null");
        }
        return str.toString();
    }

    /**
     * Returns Java code for creating <code>fuzzySet</code>.
     * @param fuzzySet the fuzzy set as basis
     * @return Java code for creating the fuzzy set
     */
    public static String getJavaInitializationStringForFuzzySet(final FuzzySet fuzzySet) {
        StringBuffer str = new StringBuffer();
        if (fuzzySet != null) {
            str.append("new FuzzySet(");
            str.append(getJavaInitializationStringForMembershipFunction(fuzzySet.getMembershipFunction()));
            str.append(")");
        } else {
            str.append("null");
        }
        return str.toString();
    }

    /**
     * Returns Java code for creating <code>rule</code>.
     * @param rule the rule as basis
     * @return Java code for creating the rule
     */
    public static String getJavaInitializationStringForRule(final Rule rule) {
        StringBuffer str = new StringBuffer();
        if (rule != null) {
            str.append("new Rule(");
            Antecedent[] antecedents = rule.getAntecedents();
            if (antecedents != null) {
                str.append("new Antecedent[] {");
                for (int i = 0; i < antecedents.length; i++) {
                    Antecedent antecedent = antecedents[i];
                    if (i > 0) {
                        str.append(", ");
                    }
                    str.append("new Antecedent(");
                    str.append(antecedent.getLinguisticVariableName());
                    str.append(", ");
                    str.append(antecedent.getLinguisticTermName());
                    str.append(", ");
                    str.append(getJavaInitializationStringForOperator(antecedent.getCompatibilityOperator()));
                    str.append(")");
                }
                str.append("}");
            } else {
                str.append("null");
            }
            str.append(", ");
            Consequent[] consequents = rule.getConsequents();
            if (consequents != null) {
                str.append("new Consequent[] {");
                for (int i = 0; i < consequents.length; i++) {
                    Consequent consequent = consequents[i];
                    if (i > 0) {
                        str.append(", ");
                    }
                    str.append("new Consequent(");
                    str.append(consequent.getLinguisticVariableName());
                    str.append(", ");
                    str.append(consequent.getLinguisticTermName());
                    str.append(")");
                }
                str.append("}");
            } else {
                str.append("null");
            }
            str.append(", ");
            str.append(getJavaInitializationStringForOperator(rule.getAggregationOperator()));
            str.append(", ");
            str.append(getJavaInitializationStringForOperator(rule.getInferenceOperator()));
            str.append(", ");
            str.append(getJavaInitializationStringForOperator(rule.getCertaintyOperator()));
            str.append(", ");
            str.append(rule.getCertainty());
            str.append(")");
        } else {
            str.append("null");
        }
        return str.toString();
    }

    /**
     * Returns Java code for creating <code>fact</code>.
     * @param fact the fact as basis
     * @return Java code for creating the fact
     */
    public static String getJavaInitializationStringForFact(final Fact fact) {
        StringBuffer str = new StringBuffer();
        if (fact != null) {
            str.append("new Fact(");
            str.append(fact.getLinguisticVariableName());
            str.append(", ");
            str.append(getJavaInitializationStringForFuzzySet(fact.getValue()));
            str.append(")");
        } else {
            str.append("null");
        }
        return str.toString();
    }

    /**
     * Returns Java code for creating <code>linguisticTerm</code>.
     * @param linguisticTerm the linguistic term as basis
     * @return Java code for creating the linguistic term
     */
    public static String getJavaInitializationStringForLinguisticTerm(final LinguisticTerm linguisticTerm) {
        StringBuffer str = new StringBuffer();
        if (linguisticTerm != null) {
            str.append("new LinguisticTerm(");
            str.append(linguisticTerm.getName());
            str.append(", ");
            str.append(getJavaInitializationStringForFuzzySet(linguisticTerm.getFuzzySet()));
            str.append(")");
        } else {
            str.append("null");
        }
        return str.toString();
    }
}
