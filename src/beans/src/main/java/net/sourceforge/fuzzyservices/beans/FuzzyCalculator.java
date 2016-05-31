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

import java.beans.PropertyVetoException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

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
        MembershipFunction membershipFunctionOfResult = new MembershipFunction();

        // Determine the degree of memberships in ascending order.
        float x;
        float dom = 0.0f;
        int pos1 = 0;
        int pos2 = 0;
        float add;
        MembershipFunction membershipFunctionOfOperand1 = operand1.getMembershipFunction();
        MembershipFunction membershipFunctionOfOperand2 = operand2.getMembershipFunction();
        float[] xValuesOfOperand1 = membershipFunctionOfOperand1.getXValues();
        float[] xValuesOfOperand2 = membershipFunctionOfOperand2.getXValues();

        // Durchlaufen der aufsteigenden Flanke.
        while (dom != 1.0f) {
            if (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                pos1++;
            } else {
                while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
                    pos1++;
                }
            }

            if (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                pos2++;
            } else {
                while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
                    pos2++;
                }
            }

            x = xValuesOfOperand1[pos1 - 1];
            add = ((dom - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                    membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                    xValuesOfOperand1[pos1 - 1])));

            if ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                x = xValuesOfOperand1[pos1 - 1] + add;
            }

            x = x + xValuesOfOperand2[pos1 - 1];
            add = (dom - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                    membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                    xValuesOfOperand2[pos2 - 1]));

            if ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                x = x + add;
            }
            try {
                membershipFunctionOfResult.setDegreeOfMembership(x, dom);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
            dom = ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                    ? membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
        }

        // Das "Plateau" einfuegen.
        while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
            pos1++;
        }

        while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
            pos2++;
        }
        try {
            // linke obere Ecke
            membershipFunctionOfResult.setDegreeOfMembership(xValuesOfOperand1[pos1] + xValuesOfOperand2[pos2], 1.0f);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == 1.0f) {
            pos1++;
        }

        while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == 1.0f) {
            pos2++;
        }
        try {
            // rechte obere Ecke
            membershipFunctionOfResult.setDegreeOfMembership(xValuesOfOperand1[pos1--] + xValuesOfOperand2[pos2--], 1.0f);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Durchlaufen der abfallenden Flanke.
        while (dom != 0.0f) {
            if (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                pos1++;
            } else {
                while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
                    pos1++;
                }
            }

            if (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                pos2++;
            } else {
                while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
                    pos2++;
                }
            }

            x = xValuesOfOperand1[pos1 - 1];
            add = ((dom - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                    membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                    xValuesOfOperand1[pos1 - 1])));

            if ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                x = xValuesOfOperand1[pos1 - 1] + add;
            }

            x = x + xValuesOfOperand2[pos2 - 1];
            add = (dom - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                    membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                    xValuesOfOperand2[pos2 - 1]));

            if ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                x = x + add;
            }
            try {
                membershipFunctionOfResult.setDegreeOfMembership(x, dom);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
            dom = ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                    ? membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
        }

        // Den letzten Punkt bei Zugehoerigkeitsgrad 0.0 einfuegen.
        // Im Prinzip muss es der letzte Eintrag in den Vektoren der Operanden sein.
        while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
            pos1++;
        }

        while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
            pos2++;
        }
        try {
            membershipFunctionOfResult.setDegreeOfMembership(xValuesOfOperand1[pos1] + xValuesOfOperand2[pos2], 0.0f);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Zum Schluss unnoetige Punkte eliminieren.
        membershipFunctionOfResult.reduce();

        FuzzyInterval result = new FuzzyInterval();
        result.setMembershipFunction(membershipFunctionOfResult);
        return result;
    }

    @Override
    public FuzzyLRInterval add(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        /**
         * Wenn alles einwandfrei funktioniert, dann wird die
         * Zugehoerigkeitsfunktion durch genau vier Punkte spezifiziert.
         * Die Differenz zwischen den x-Werten des ersten und zweiten Punktes
         * ergibt alpha, die Differenz zwischen den x-Werten des dritten und
         * vierten Punktes ergibt beta.
         */

        // Auf einer Kopie arbeiten.
        MembershipFunction membershipFunctionOfOperand1 = (MembershipFunction) operand1.getMembershipFunction().clone();
        MembershipFunction membershipFunctionOfOperand2 = (MembershipFunction) operand2.getMembershipFunction().clone();
        
        membershipFunctionOfOperand1.reduce();
        membershipFunctionOfOperand2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((membershipFunctionOfOperand1.size() != 4) || (membershipFunctionOfOperand2.size() != 4)) {
            throw new RuntimeException();
        }

        MembershipFunction membershipFunctionOfResult = new MembershipFunction(); // das spaetere Ergebnis
        float m1;
        float m2;
        float n1;
        float n2;
        float sum1;
        float sum2;
        float alpha;
        float beta;
        float gamma;
        float delta;

        // Werte fuer den ersten Operanden ermitteln.
        float[] xValuesOfOperand1 = membershipFunctionOfOperand1.getXValues();
        alpha = Math.abs(xValuesOfOperand1[0] - xValuesOfOperand1[1]);
        m1 = xValuesOfOperand1[1];
        m2 = xValuesOfOperand1[2];
        beta = Math.abs(xValuesOfOperand1[2] - xValuesOfOperand1[3]);

        // Werte fuer den zweiten Operanden ermitteln
        float[] xValuesOfOperand2 = membershipFunctionOfOperand2.getXValues();
        gamma = Math.abs(xValuesOfOperand2[0] - xValuesOfOperand2[1]);
        n1 = xValuesOfOperand2[1];
        n2 = xValuesOfOperand2[2];
        delta = Math.abs(xValuesOfOperand2[2] - xValuesOfOperand2[3]);
        // Aus den ermittelten Werten das Ergebnis bestimmen.
        sum1 = m1 + n1;
        sum2 = m2 + n2;
        try {
            membershipFunctionOfResult.setDegreeOfMembership(sum1 - (alpha + gamma), 0.0f);
            membershipFunctionOfResult.setDegreeOfMembership(sum1, 1.0f);
            membershipFunctionOfResult.setDegreeOfMembership(sum2, 1.0f);
            membershipFunctionOfResult.setDegreeOfMembership(sum2 + (beta + delta), 0.0f);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }

        FuzzyLRInterval result = new FuzzyLRInterval();
        result.setMembershipFunction(membershipFunctionOfResult);
        return result;
    }

    @Override
    public FuzzyLRNumber add(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        /**
         * Wenn alles einwandfrei funktioniert, dann wird die
         * Zugehoerigkeitsfunktion durch genau drei Punkte spezifiziert. Die
         * Differenz zwischen den x-Werten des ersten und zweiten Punktes
         * ergibt alpha, die Differenz zwischen den x-Werten des zweiten und
         * dritten Punktes ergibt beta.
         */

        // Auf einer Kopie arbeiten.
        MembershipFunction membershipFunctionOfOperand1 = (MembershipFunction) operand1.getMembershipFunction().clone();
        MembershipFunction membershipFunctionOfOperand2 = (MembershipFunction) operand2.getMembershipFunction().clone();
        
        membershipFunctionOfOperand1.reduce();
        membershipFunctionOfOperand2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((membershipFunctionOfOperand1.size() != 3) || (membershipFunctionOfOperand2.size() != 3)) {
            throw new RuntimeException();
        }

        float x1;
        float x2;
        float xSum;
        float alpha;
        float beta;
        float gamma;
        float delta;

        // Werte fuer den ersten Operanden ermitteln.
        float[] xValuesOfOperand1 = membershipFunctionOfOperand1.getXValues();
        alpha = Math.abs(xValuesOfOperand1[0] - xValuesOfOperand1[1]);
        beta = Math.abs(xValuesOfOperand1[1] - xValuesOfOperand1[2]);
        x1 = xValuesOfOperand1[1];

        // Werte fuer den zweiten Operanden ermitteln.
        float[] xValuesOfOperand2 = membershipFunctionOfOperand2.getXValues();
        gamma = Math.abs(xValuesOfOperand2[0] - xValuesOfOperand2[1]);
        delta = Math.abs(xValuesOfOperand2[1] - xValuesOfOperand2[2]);
        x2 = xValuesOfOperand2[1];

        // Aus den ermittelten Werten das Ergebnis bestimmen.
        xSum = x1 + x2;

        MembershipFunction membershipFunctionOfResult = new MembershipFunction(xSum, (alpha + gamma), (beta + delta));
        FuzzyLRNumber result = new FuzzyLRNumber();
        result.setMembershipFunction(membershipFunctionOfResult);
        return result;
    }

    @Override
    public FuzzyNumber add(FuzzyNumber operand1, FuzzyNumber operand2) {
        MembershipFunction membershipFunctionOfResult = new MembershipFunction();

        // Es werden die spezifizierten Zugehoerigkeitsgrade in aufsteigender
        // Reihenfolge untersucht.
        float x;
        float dom = 0.0f;
        int pos1 = 0;
        int pos2 = 0;
        float add;
        MembershipFunction membershipFunctionOfOperand1 = operand1.getMembershipFunction();
        MembershipFunction membershipFunctionOfOperand2 = operand2.getMembershipFunction();
        float[] xValuesOfOperand1 = membershipFunctionOfOperand1.getXValues();
        float[] xValuesOfOperand2 = membershipFunctionOfOperand2.getXValues();

        // Durchlaufen der aufsteigenden Flanke
        while (dom != 1.0f) {
            if (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                pos1++;
            } else {
                while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
                    pos1++;
                }
            }

            if (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                pos2++;
            } else {
                while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
                    pos2++;
                }
            }

            x = xValuesOfOperand1[pos1 - 1];
            add = ((dom - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                    membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                    xValuesOfOperand1[pos1 - 1])));

            if ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                x = xValuesOfOperand1[pos1 - 1] + add;
            }

            x = x + xValuesOfOperand2[pos2 - 1];
            add = (dom - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                    membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                    xValuesOfOperand2[pos2 - 1]));

            if ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                x = x + add;
            }
            try {
                membershipFunctionOfResult.setDegreeOfMembership(x, dom);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
            dom = ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                    ? membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
        }

        // Die "Spitze" einfuegen.
        while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
            pos1++;
        }

        while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
            pos2++;
        }
        try {
            membershipFunctionOfResult.setDegreeOfMembership(xValuesOfOperand1[pos1] + xValuesOfOperand2[pos2], 1.0f);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Durchlaufen der abfallenden Flanke.
        while (dom != 0.0f) {
            if (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                pos1++;
            } else {
                while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
                    pos1++;
                }
            }

            if (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                pos2++;
            } else {
                while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
                    pos2++;
                }
            }

            x = xValuesOfOperand1[pos1 - 1];
            add = ((dom - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                    membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                    xValuesOfOperand1[pos1 - 1])));

            if ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                x = xValuesOfOperand1[pos1 - 1] + add;
            }

            x = x + xValuesOfOperand2[pos2 - 1];
            add = (dom - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                    membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                    xValuesOfOperand2[pos2 - 1]));

            if ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                x = x + add;
            }
            try {
                membershipFunctionOfResult.setDegreeOfMembership(x, dom);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
            dom = ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                    ? membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
        }

        // Den letzten Punkt bei Zugehoerigkeitsgrad 0.0 einfuegen.
        // Im Prinzip muss es der letzte Eintrag in den Vektoren der Operanden sein
        while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
            pos1++;
        }

        while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
            pos2++;
        }
        try {
            membershipFunctionOfResult.setDegreeOfMembership(xValuesOfOperand1[pos1] + xValuesOfOperand2[pos2], 0.0f);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Zum Schluss unnoetige Punkte eliminieren.
        membershipFunctionOfResult.reduce();

        FuzzyNumber result = new FuzzyNumber();
        result.setMembershipFunction(membershipFunctionOfResult);
        return result;
    }

    @Override
    public FuzzyInterval divide(FuzzyInterval operand1, FuzzyInterval operand2) {
        MembershipFunction membershipFunctionOfOperand1 = operand1.getMembershipFunction();
        MembershipFunction membershipFunctionOfOperand2 = operand2.getMembershipFunction();
        if (membershipFunctionOfOperand2.getDegreeOfMembership(0.0f) == 0.0f) {
            if (membershipFunctionOfOperand1.getDegreeOfMembership(0.0f) > 0.0f) {
                return (FuzzyInterval) operand1.clone(); // entspricht 0 / operand2
            }

            // Die Division kann auf die Multiplikation zurueckgefuehrt werden, indem operand2 invertiert und
            // anschliessend zu operand1 multipliziert wird.
            FuzzyInterval operand2a = (FuzzyInterval) operand2.clone();
            try {
                operand2a.invert();
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }

            return multiply(operand1, operand2a);
        } else {
            throw new ArithmeticException(FuzzyResourceManager.getString(this, "EXCEPTION_DIVIDE_BY_ZERO"));
        }
    }

    @Override
    public FuzzyLRInterval divide(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        // Auf einer Kopie arbeiten.
        FuzzyLRInterval op1 = (FuzzyLRInterval) operand1.clone();
        FuzzyLRInterval op2 = (FuzzyLRInterval) operand2.clone();
        MembershipFunction membershipFunctionOfOperand1 = op1.getMembershipFunction();
        MembershipFunction membershipFunctionOfOperand2 = op2.getMembershipFunction();
        op1.getMembershipFunction().reduce();
        op2.getMembershipFunction().reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((membershipFunctionOfOperand1.size() != 4) || (membershipFunctionOfOperand2.size() != 4)) {
            throw new RuntimeException();
        }

        if (membershipFunctionOfOperand2.getDegreeOfMembership(0.0f) == 0.0f) {
            if (membershipFunctionOfOperand1.getDegreeOfMembership(0.0f) > 0.0f) {
                return (FuzzyLRInterval) operand1.clone(); // entspricht 0 / operand2
            }

            // Die Division kann auf die Multiplikation zurueckgefuehrt werden,
            // indem operand2 invertiert und anschliessend zu operand1 multipliziert wird.
            FuzzyLRInterval operand2a = (FuzzyLRInterval) operand2.clone();
            try {
                operand2a.invert();
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }

            return multiply(operand1, operand2a);
        } else {
            throw new ArithmeticException(FuzzyResourceManager.getString(this, "EXCEPTION_DIVIDE_BY_ZERO"));
        }
    }

    @Override
    public FuzzyLRNumber divide(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        // Auf einer Kopie arbeiten.
        FuzzyLRNumber op1 = (FuzzyLRNumber) operand1.clone();
        FuzzyLRNumber op2 = (FuzzyLRNumber) operand2.clone();
        MembershipFunction membershipFunctionOfOperand1 = op1.getMembershipFunction();
        MembershipFunction membershipFunctionOfOperand2 = op2.getMembershipFunction();
        membershipFunctionOfOperand1.reduce();
        membershipFunctionOfOperand2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((membershipFunctionOfOperand1.size() != 3) || (membershipFunctionOfOperand2.size() != 3)) {
            throw new RuntimeException();
        }

        if (membershipFunctionOfOperand2.getDegreeOfMembership(0.0f) == 0.0f) {
            if (membershipFunctionOfOperand1.getDegreeOfMembership(0.0f) > 0.0f) {
                return (FuzzyLRNumber) operand1.clone(); // entspricht 0 / operand2
            }

            // Die Division kann auf die Multiplikation zurueckgefuehrt werden,
            // indem operand2 invertiert und anschliessend zu operand1 multipliziert wird.
            FuzzyLRNumber operand2a = (FuzzyLRNumber) operand2.clone();
            try {
                operand2a.invert();
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }

            return multiply(operand1, operand2a);
        } else {
            throw new ArithmeticException(FuzzyResourceManager.getString(this, "EXCEPTION_DIVIDE_BY_ZERO"));
        }
    }

    @Override
    public FuzzyNumber divide(FuzzyNumber operand1, FuzzyNumber operand2) {
        MembershipFunction membershipFunctionOfOperand1 = operand1.getMembershipFunction();
        MembershipFunction membershipFunctionOfOperand2 = operand2.getMembershipFunction();
        if (membershipFunctionOfOperand2.getDegreeOfMembership(0.0f) == 0.0f) {
            if (membershipFunctionOfOperand1.getDegreeOfMembership(0.0f) > 0.0f) {
                return (FuzzyNumber) operand1.clone(); // entspricht 0 / operand2
            }

            // Die Division kann auf die Multiplikation zurueckgefuehrt werden,
            // indem operand2 invertiert und
            // anschliessend zu operand1 multipliziert wird.
            FuzzyNumber operand2a = (FuzzyNumber) operand2.clone();
            try {
                operand2a.invert();
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }

            return multiply(operand1, operand2a);
        } else {
            throw new ArithmeticException(FuzzyResourceManager.getString(this, "EXCEPTION_DIVIDE_BY_ZERO"));
        }
    }

    @Override
    public FuzzyInterval multiply(FuzzyInterval operand1, FuzzyInterval operand2) {
        MembershipFunction membershipFunctionOfOperand1 = operand1.getMembershipFunction();
        MembershipFunction membershipFunctionOfOperand2 = operand2.getMembershipFunction();
        if (((membershipFunctionOfOperand1.getDegreeOfMembership(0.0f) == 0.0f) &&
                (membershipFunctionOfOperand2.getDegreeOfMembership(0.0f) == 0.0f)) ||
                ((membershipFunctionOfOperand1.getDegreeOfMembership(0.0f) != 0.0f) &&
                (membershipFunctionOfOperand2.getDegreeOfMembership(0.0f) != 0.0f))) {
            boolean op1Positive = operand1.isPositive();
            boolean op2Positive = operand2.isPositive();

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                // Die Operanden haben unterschiedliches Vorzeichen.
                // Deshalb muss ein Operand temporaer negiert werden.
                if (membershipFunctionOfOperand1.size() <= membershipFunctionOfOperand2.size()) {
                    try {
                        operand1.negate();
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        operand2.negate();
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            MembershipFunction membershipFunctionOfResult = new MembershipFunction();

            // Es werden die spezifizierten Zugehoerigkeitsgrade in aufsteigender Reihenfolge untersucht.
            float x;
            float dom = 0.0f;
            int pos1 = 0;
            int pos2 = 0;
            float add;
            float[] xValuesOfOperand1 = membershipFunctionOfOperand1.getXValues();
            float[] xValuesOfOperand2 = membershipFunctionOfOperand2.getXValues();

            // Durchlaufen der aufsteigenden Flanke.
            while (dom != 1.0f) {
                if (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                    pos1++;
                } else {
                    while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
                        pos1++;
                    }
                }

                if (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                    pos2++;
                } else {
                    while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
                        pos2++;
                    }
                }

                x = xValuesOfOperand1[pos1 - 1];
                add = ((dom - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                        membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                        xValuesOfOperand1[pos1 - 1])));

                if ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                    x = xValuesOfOperand1[pos1 - 1] + add;
                }

                add = (dom - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                        membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                        xValuesOfOperand2[pos2 - 1]));

                if ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                    add = add + xValuesOfOperand2[pos2 - 1];
                } else {
                    add = xValuesOfOperand2[pos2 - 1];
                }

                x = x * add;
                        try {
                            //                x = (xValuesOfOperand1[pos1 - 1] + ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) /
                            // ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                            // operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] - xValuesOfOperand1[pos1 - 1]))));
                            // x = x * (xValuesOfOperand2[pos2 - 1] + ((dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) /
                            // ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                            // operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] - xValuesOfOperand2[pos2 - 1]))));
                            membershipFunctionOfResult.setDegreeOfMembership(x, dom);
                        } catch (PropertyVetoException ex) {
                            Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                        }
                // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
                dom = ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                        ? membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
            }

            // Das "Plateau" einfuegen.
            while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
                pos1++;
            }

            while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
                pos2++;
            }
            try {
                // linke obere Ecke
                membershipFunctionOfResult.setDegreeOfMembership(xValuesOfOperand1[pos1] * xValuesOfOperand2[pos2], 1.0f);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }

            while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == 1.0f) {
                pos1++;
            }

            while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == 1.0f) {
                pos2++;
            }
            try {
                // rechte obere Ecke
                membershipFunctionOfResult.setDegreeOfMembership(xValuesOfOperand1[pos1--] * xValuesOfOperand2[pos2--], 1.0f);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Durchlaufen der abfallenden Flanke.
            while (dom != 0.0f) {
                if (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                    pos1++;
                } else {
                    while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
                        pos1++;
                    }
                }

                if (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                    pos2++;
                } else {
                    while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
                        pos2++;
                    }
                }

                x = xValuesOfOperand1[pos1 - 1];
                add = ((dom - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                        membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                        xValuesOfOperand1[pos1 - 1])));

                if ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                    x = xValuesOfOperand1[pos1 - 1] + add;
                }

                add = (dom - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                        membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                        xValuesOfOperand2[pos2 - 1]));

                if ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                    add = add + xValuesOfOperand2[pos2 - 1];
                } else {
                    add = xValuesOfOperand2[pos2 - 1];
                }

                x = x * add;
                        try {
                            // x = (xValuesOfOperand1[pos1 - 1] + ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) /
                            // ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                            // operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] - xValuesOfOperand1[pos1 - 1]))));
                            //                x = x * (xValuesOfOperand2[pos2 - 1] + ((dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) /
                            // ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                            // operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] - xValuesOfOperand2[pos2 - 1]))));
                            membershipFunctionOfResult.setDegreeOfMembership(x, dom);
                        } catch (PropertyVetoException ex) {
                            Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                        }
                // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
                dom = ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                        ? membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
            }

            // Den letzten Punkt bei Zugehoerigkeitsgrad 0.0 einfuegen.
            // Im Prinzip muss es der letzte Eintrag in den Vektoren der Operanden sein.
            while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
                pos1++;
            }

            while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
                pos2++;
            }
            try {
                membershipFunctionOfResult.setDegreeOfMembership(xValuesOfOperand1[pos1] * xValuesOfOperand2[pos2], 0.0f);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Zum Schluss unnoetige Punkte eliminieren.
            membershipFunctionOfResult.reduce();

            FuzzyInterval result = new FuzzyInterval();
            result.setMembershipFunction(membershipFunctionOfResult);

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                // Die Operanden hatten unterschiedliches Vorzeichen.
                // Deshalb wurde ein Operand temporaer negiert.
                // Dies wird hier wieder rueckgaengig gemacht.
                if (membershipFunctionOfOperand1.size() <= membershipFunctionOfOperand2.size()) {
                    try {
                        operand1.negate();
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        operand2.negate();
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        try {
                            // Ausserdem muss das Ergebnis negiert werden.
                            result.negate();
                        } catch (PropertyVetoException ex) {
                            Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                        }
            }

            return result;
        } else {
            return (FuzzyInterval) ((membershipFunctionOfOperand1.getDegreeOfMembership(0.0f) != 0.0f) ? operand1.clone() : operand2.clone());
        }
    }

    @Override
    public FuzzyLRInterval multiply(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        /**
         * Wenn alles einwandfrei funktioniert, dann wird die
         * Zugehoerigkeitsfunktion durch genau vier Punkte spezifiziert.
         * Die Differenz zwischen den x-Werten des ersten und zweiten Punktes
         * ergibt alpha, die Differenz zwischen den x-Werten des dritten und
         * vierten Punktes ergibt beta.
         */

        // Auf einer Kopie arbeiten.
        FuzzyLRInterval op1 = (FuzzyLRInterval) operand1.clone();
        FuzzyLRInterval op2 = (FuzzyLRInterval) operand2.clone();
        MembershipFunction membershipFunctionOfOperand1 = op1.getMembershipFunction();
        MembershipFunction membershipFunctionOfOperand2 = op2.getMembershipFunction();
        membershipFunctionOfOperand1.reduce();
        membershipFunctionOfOperand2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((membershipFunctionOfOperand1.size() != 4) || (membershipFunctionOfOperand2.size() != 4)) {
            throw new RuntimeException();
        }

        if (((membershipFunctionOfOperand1.getDegreeOfMembership(0.0f) == 0.0f) && (membershipFunctionOfOperand2.getDegreeOfMembership(0.0f) == 0.0f)) ||
                ((membershipFunctionOfOperand1.getDegreeOfMembership(0.0f) != 0.0f) && (membershipFunctionOfOperand2.getDegreeOfMembership(0.0f) != 0.0f))) {
            boolean op1Positive = op1.isPositive();
            boolean op2Positive = op2.isPositive();

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                try {
                    // Deshalb muss ein Operand temporaer negiert werden.

                    /*
                    if (op1.size() <= op2.size())
                    op1.negate();
                    else
                     */
                    op2.negate();
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            MembershipFunction membershipFunctionOfResult = new MembershipFunction(); // das spaetere Ergebnis
            float m1;
            float m2;
            float n1;
            float n2;
            float mul1;
            float mul2;
            float alpha;
            float beta;
            float gamma;
            float delta;

            // Werte fuer den ersten Operanden ermitteln.
            float[] xValuesOfOperand1 = membershipFunctionOfOperand1.getXValues();
            alpha = Math.abs(xValuesOfOperand1[0] - xValuesOfOperand1[1]);
            m1 = xValuesOfOperand1[1];
            m2 = xValuesOfOperand1[2];
            beta = Math.abs(xValuesOfOperand1[2] - xValuesOfOperand1[3]);

            // Werte fuer den zweiten Operanden ermitteln.
            float[] xValuesOfOperand2 = membershipFunctionOfOperand2.getXValues();
            gamma = Math.abs(xValuesOfOperand2[0] - xValuesOfOperand2[1]);
            n1 = xValuesOfOperand2[1];
            n2 = xValuesOfOperand2[2];
            delta = Math.abs(xValuesOfOperand2[2] - xValuesOfOperand2[3]);

            // Aus den ermittelten Werten das Ergebnis bestimmen (nach Dubois und Prade).
            mul1 = m1 * n1;
            mul2 = m2 * n2;
            try {
                membershipFunctionOfResult.setDegreeOfMembership(mul1 - (((m1 * gamma) + (n1 * alpha)) - (alpha * gamma)), 0.0f);
                membershipFunctionOfResult.setDegreeOfMembership(mul1, 1.0f);
                membershipFunctionOfResult.setDegreeOfMembership(mul2, 1.0f);
                membershipFunctionOfResult.setDegreeOfMembership(mul2 + ((m2 * delta) + (n2 * beta) + (beta * delta)), 0.0f);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }

            FuzzyLRInterval result = new FuzzyLRInterval();
            result.setMembershipFunction(membershipFunctionOfResult);

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                try {
                    // Die Operanden hatten unterschiedliches Vorzeichen.
                    // Dies wird hier wieder rueckgaengig gemacht.

                    /*
                    if (op1.size() <= op2.size())
                    op1.negate();
                    else
                    op2.negate();
                     */

                    // Ausserdem muss das Ergebnis negiert werden.
                    result.negate();
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return result;
        }

        return (FuzzyLRInterval) ((operand1.getMembershipFunction().getDegreeOfMembership(0.0f) != 0.0f) ? operand1.clone() : operand2.clone());
    }

    @Override
    public FuzzyLRNumber multiply(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        /**
         * Wenn alles einwandfrei funktioniert, dann wird die
         * Zugehoerigkeitsfunktion durch genau drei Punkte spezifiziert.
         * Die Differenz zwischen den x-Werten des ersten und zweiten Punktes
         * ergibt alpha, die Differenz zwischen den x-Werten des zweiten und
         * dritten Punktes ergibt beta.
         */

        // Auf einer Kopie arbeiten.
        FuzzyLRNumber op1 = (FuzzyLRNumber) operand1.clone();
        FuzzyLRNumber op2 = (FuzzyLRNumber) operand2.clone();
        MembershipFunction membershipFunctionOfOperand1 = op1.getMembershipFunction();
        MembershipFunction membershipFunctionOfOperand2 = op2.getMembershipFunction();
        membershipFunctionOfOperand1.reduce();
        membershipFunctionOfOperand2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((membershipFunctionOfOperand1.size() != 3) || (membershipFunctionOfOperand2.size() != 3)) {
            throw new RuntimeException();
        }

        if (((membershipFunctionOfOperand1.getDegreeOfMembership(0.0f) == 0.0f) && (membershipFunctionOfOperand2.getDegreeOfMembership(0.0f) == 0.0f)) ||
                ((membershipFunctionOfOperand1.getDegreeOfMembership(0.0f) != 0.0f) && (membershipFunctionOfOperand2.getDegreeOfMembership(0.0f) != 0.0f))) {
            boolean op1Positive = op1.isPositive();
            boolean op2Positive = op2.isPositive();

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                try {
                    // Deshalb muss ein Operand temporaer negiert werden.
                    op2.negate();
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            float x1;
            float x2;
            float xMul;
            float alpha;
            float beta;
            float gamma;
            float delta;

            // Werte fuer den ersten Operanden ermitteln.
            float[] xValuesOfOperand1 = membershipFunctionOfOperand1.getXValues();
            alpha = Math.abs(xValuesOfOperand1[0] - xValuesOfOperand1[1]);
            beta = Math.abs(xValuesOfOperand1[1] - xValuesOfOperand1[2]);
            x1 = xValuesOfOperand1[1];

            // Werte fuer den zweiten Operanden ermitteln.
            float[] xValuesOfOperand2 = membershipFunctionOfOperand2.getXValues();
            gamma = Math.abs(xValuesOfOperand2[0] - xValuesOfOperand2[1]);
            delta = Math.abs(xValuesOfOperand2[1] - xValuesOfOperand2[2]);
            x2 = xValuesOfOperand2[1];

            // Aus den ermittelten Werten das Ergebnis bestimmen (nach Dubois und Prade).
            xMul = x1 * x2;

            MembershipFunction membershipFunctionOfResult = new MembershipFunction(xMul, (((x1 * gamma) + (x2 * alpha)) - (alpha * gamma)),
                    ((x1 * delta) + (x2 * beta) + (beta * delta)));
            
            FuzzyLRNumber result = new FuzzyLRNumber();
            result.setMembershipFunction(membershipFunctionOfResult);

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                try {
                    // Die Operanden hatten unterschiedliches Vorzeichen.
                    // Dies wird hier wieder rueckgaengig gemacht.
                    // Dies wird hier wieder rueckgaengig gemacht.

                    // Ausserdem muss das Ergebnis negiert werden.
                    result.negate();
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return result;
        }

        return (FuzzyLRNumber) ((operand1.getMembershipFunction().getDegreeOfMembership(0.0f) != 0.0f) ? operand1.clone() : operand2.clone());
    }

    @Override
    public FuzzyNumber multiply(FuzzyNumber operand1, FuzzyNumber operand2) {
        MembershipFunction membershipFunctionOfOperand1 = operand1.getMembershipFunction();
        MembershipFunction membershipFunctionOfOperand2 = operand2.getMembershipFunction();
        if (((membershipFunctionOfOperand1.getDegreeOfMembership(0.0f) == 0.0f) &&
                (membershipFunctionOfOperand2.getDegreeOfMembership(0.0f) == 0.0f)) ||
                ((membershipFunctionOfOperand1.getDegreeOfMembership(0.0f) != 0.0f) &&
                (membershipFunctionOfOperand2.getDegreeOfMembership(0.0f) != 0.0f))) {
            boolean op1Positive = operand1.isPositive();
            boolean op2Positive = operand2.isPositive();

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                // Die Operanden haben unterschiedliches Vorzeichen.
                // Deshalb muss ein Operand temporaer negiert werden.
                if (membershipFunctionOfOperand1.size() <= membershipFunctionOfOperand2.size()) {
                    try {
                        operand1.negate();
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        operand2.negate();
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            MembershipFunction membershipFunctionOfResult = new MembershipFunction();

            // Es werden die spezifizierten Zugehoerigkeitsgrade in aufsteigender Reihenfolge untersucht.
            float x;
            float dom = 0.0f;
            int pos1 = 0;
            int pos2 = 0;
            float add;
            float[] xValuesOfOperand1 = membershipFunctionOfOperand1.getXValues();
            float[] xValuesOfOperand2 = membershipFunctionOfOperand2.getXValues();

            // Durchlaufen der aufsteigenden Flanke.
            while (dom != 1.0f) {
                if (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                    pos1++;
                } else {
                    while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
                        pos1++;
                    }
                }

                if (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                    pos2++;
                } else {
                    while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
                        pos2++;
                    }
                }

                x = xValuesOfOperand1[pos1 - 1];
                add = ((dom - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                        membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                        xValuesOfOperand1[pos1 - 1])));

                if ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                    x = xValuesOfOperand1[pos1 - 1] + add;
                }

                add = (dom - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                        membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                        xValuesOfOperand2[pos2 - 1]));

                if ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                    add = add + xValuesOfOperand2[pos2 - 1];
                } else {
                    add = xValuesOfOperand2[pos2 - 1];
                }

                x = x * add;
                //                x = (xValuesOfOperand1[pos1 - 1] + ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) /
                // ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                // operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] - xValuesOfOperand1[pos1 - 1]))));
                //                x = x * (xValuesOfOperand2[pos2 - 1] + ((dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) /
                // ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                // operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] - xValuesOfOperand2[pos2 - 1]))));
                try {
                    membershipFunctionOfResult.setDegreeOfMembership(x, dom);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
                dom = ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                        ? membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
            }

            // Die "Spitze" einfuegen.
            while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
                pos1++;
            }

            while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
                pos2++;
            }
            
            try {
                membershipFunctionOfResult.setDegreeOfMembership(xValuesOfOperand1[pos1] * xValuesOfOperand2[pos2], 1.0f);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Durchlaufen der abfallenden Flanke.
            while (dom != 0.0f) {
                if (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                    pos1++;
                } else {
                    while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
                        pos1++;
                    }
                }

                if (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                    pos2++;
                } else {
                    while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
                        pos2++;
                    }
                }

                x = xValuesOfOperand1[pos1 - 1];
                add = ((dom - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                        membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                        xValuesOfOperand1[pos1 - 1])));

                if ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                    x = xValuesOfOperand1[pos1 - 1] + add;
                }

                add = (dom - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                        membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                        xValuesOfOperand2[pos2 - 1]));

                if ((membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                    add = add + xValuesOfOperand2[pos2 - 1];
                } else {
                    add = xValuesOfOperand2[pos2 - 1];
                }

                x = x * add;
                //                x = (xValuesOfOperand1[pos1 - 1] + ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) /
                // ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                // operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] - xValuesOfOperand1[pos1 - 1]))));
                //                x = x * (xValuesOfOperand2[pos2 - 1] + ((dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) /
                // ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                // operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] - xValuesOfOperand2[pos2 - 1]))));
                try {
                    membershipFunctionOfResult.setDegreeOfMembership(x, dom);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
                dom = ((membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                        ? membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
            }

            // Den letzten Punkt bei Zugehoerigkeitsgrad 0.0 einfuegen.
            // Im Prinzip muss es der letzte Eintrag in den Vektoren der Operanden sein.
            while (membershipFunctionOfOperand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
                pos1++;
            }

            while (membershipFunctionOfOperand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
                pos2++;
            }
            try {
                membershipFunctionOfResult.setDegreeOfMembership(xValuesOfOperand1[pos1] * xValuesOfOperand2[pos2], 0.0f);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Zum Schluss unnoetige Punkte eliminieren.
            membershipFunctionOfResult.reduce();

            FuzzyNumber result = new FuzzyNumber();
            result.setMembershipFunction(membershipFunctionOfResult);

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                // Die Operanden hatten unterschiedliches Vorzeichen.
                // Deshalb wurde ein Operand temporaer negiert.
                // Dies wird hier wieder rueckgaengig gemacht.
                if (membershipFunctionOfOperand1.size() <= membershipFunctionOfOperand2.size()) {
                    try {
                        operand1.negate();
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        operand2.negate();
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    // Ausserdem muss das Ergebnis negiert werden.
                    result.negate();
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return result;
        }

        return (FuzzyNumber) ((operand1.getMembershipFunction().getDegreeOfMembership(0.0f) != 0.0f) ? operand1.clone() : operand2.clone());
    }

    @Override
    public FuzzyInterval subtract(FuzzyInterval operand1, FuzzyInterval operand2) {
        // Die Subtraktion kann auf die Addition zurueckgefuehrt werden, indem operand2 negiert und anschliessend zu operand1 addiert wird.
        FuzzyInterval operand2a = (FuzzyInterval) operand2.clone();
        try {
            operand2a.negate();
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return add(operand1, operand2a);
    }

    @Override
    public FuzzyLRInterval subtract(FuzzyLRInterval operand1, FuzzyLRInterval operand2) {
        // Die Subtraktion kann auf die Addition zurueckgefuehrt werden,
        // indem operand2 negiert und anschliessend zu operand1 addiert wird
        // Auf einer Kopie arbeiten.
        MembershipFunction membershipFunctionOfOperand1 = operand1.getMembershipFunction();
        MembershipFunction membershipFunctionOfOperand2 = operand2.getMembershipFunction();
        membershipFunctionOfOperand1.reduce();
        membershipFunctionOfOperand2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((membershipFunctionOfOperand1.size() != 4) || (membershipFunctionOfOperand2.size() != 4)) {
            throw new RuntimeException();
        }

        FuzzyLRInterval operand2a = (FuzzyLRInterval) operand2.clone();
        try {
            operand2a.negate();
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return add(operand1, operand2a);
    }

    @Override
    public FuzzyLRNumber subtract(FuzzyLRNumber operand1, FuzzyLRNumber operand2) {
        // Die Subtraktion kann auf die Addition zurueckgefuehrt werden,
        // indem operand2 negiert und anschliessend zu operand1 addiert wird.
        // Auf einer Kopie arbeiten.
        MembershipFunction membershipFunctionOfOperand1 = operand1.getMembershipFunction();
        MembershipFunction membershipFunctionOfOperand2 = operand2.getMembershipFunction();
        membershipFunctionOfOperand1.reduce();
        membershipFunctionOfOperand2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((membershipFunctionOfOperand1.size() != 3) || (membershipFunctionOfOperand2.size() != 3)) {
            throw new RuntimeException();
        }

        FuzzyLRNumber operand2a = (FuzzyLRNumber) operand2.clone();
        try {
            operand2a.negate();
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return add(operand1, operand2a);
    }

    @Override
    public FuzzyNumber subtract(FuzzyNumber operand1, FuzzyNumber operand2) throws NullPointerException {
        // Die Subtraktion kann auf die Addition zurueckgefuehrt werden, indem operand2 negiert und anschliessend zu operand1 addiert wird
        FuzzyNumber operand2a = (FuzzyNumber) operand2.clone();
        try {
            operand2a.negate();
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FuzzyCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return add(operand1, operand2a);
    }
}
