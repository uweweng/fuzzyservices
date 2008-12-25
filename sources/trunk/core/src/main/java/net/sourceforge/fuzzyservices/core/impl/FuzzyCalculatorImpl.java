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
package net.sourceforge.fuzzyservices.core.impl;

import net.sourceforge.fuzzyservices.core.FuzzyCalculator;
import net.sourceforge.fuzzyservices.core.FuzzyInterval;
import net.sourceforge.fuzzyservices.core.FuzzyLRInterval;
import net.sourceforge.fuzzyservices.core.FuzzyLRNumber;
import net.sourceforge.fuzzyservices.core.FuzzyNumber;
import net.sourceforge.fuzzyservices.core.FuzzySet;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;

/**
 * Implementation of a fuzzy calculator.
 *
 * @see net.sourceforge.fuzzyservices.core.FuzzyCalculator
 *
 * @author Uwe Weng
 */
public class FuzzyCalculatorImpl implements FuzzyCalculator {

    /** The singleton instance of this fuzzy calculator. */
    private static transient FuzzyCalculator instance = null;

    /**
     * Gets an instance of this fuzzy calculator.
     * @return an instance of this class
     */
    public static FuzzyCalculator getInstance() {
        if (instance == null) {
            instance = new FuzzyCalculatorImpl();
        }

        return instance;
    }

    @Override
    public FuzzyInterval add(final FuzzyInterval operand1, final FuzzyInterval operand2) {
        FuzzySet fs = new FuzzySet();

        // Determine the degree of memberships in ascending order.
        float x;
        float dom = 0.0f;
        int pos1 = 0;
        int pos2 = 0;
        float add;
        float[] xValuesOfOperand1 = operand1.getXValues();
        float[] xValuesOfOperand2 = operand2.getXValues();

        // Durchlaufen der aufsteigenden Flanke.
        while (dom != 1.0f) {
            if (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                pos1++;
            } else {
                while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
                    pos1++;
                }
            }

            if (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                pos2++;
            } else {
                while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
                    pos2++;
                }
            }

            x = xValuesOfOperand1[pos1 - 1];
            add = ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                    operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                    xValuesOfOperand1[pos1 - 1])));

            if ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                x = xValuesOfOperand1[pos1 - 1] + add;
            }

            x = x + xValuesOfOperand2[pos1 - 1];
            add = (dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                    operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                    xValuesOfOperand2[pos2 - 1]));

            if ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                x = x + add;
            }

            fs.set(x, dom);
            // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
            dom = ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                    ? operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
        }

        // Das "Plateau" einfuegen.
        while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
            pos1++;
        }

        while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
            pos2++;
        }

        // linke obere Ecke
        fs.set(xValuesOfOperand1[pos1] + xValuesOfOperand2[pos2], 1.0f);

        while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == 1.0f) {
            pos1++;
        }

        while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == 1.0f) {
            pos2++;
        }

        // rechte obere Ecke
        fs.set(xValuesOfOperand1[pos1--] + xValuesOfOperand2[pos2--], 1.0f);

        // Durchlaufen der abfallenden Flanke.
        while (dom != 0.0f) {
            if (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                pos1++;
            } else {
                while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
                    pos1++;
                }
            }

            if (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                pos2++;
            } else {
                while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
                    pos2++;
                }
            }

            x = xValuesOfOperand1[pos1 - 1];
            add = ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                    operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                    xValuesOfOperand1[pos1 - 1])));

            if ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                x = xValuesOfOperand1[pos1 - 1] + add;
            }

            x = x + xValuesOfOperand2[pos2 - 1];
            add = (dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                    operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                    xValuesOfOperand2[pos2 - 1]));

            if ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                x = x + add;
            }

            fs.set(x, dom);
            // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
            dom = ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                    ? operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
        }

        // Den letzten Punkt bei Zugehoerigkeitsgrad 0.0 einfuegen.
        // Im Prinzip muss es der letzte Eintrag in den Vektoren der Operanden sein.
        while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
            pos1++;
        }

        while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
            pos2++;
        }

        fs.set(xValuesOfOperand1[pos1] + xValuesOfOperand2[pos2], 0.0f);
        // Zum Schluss unnoetige Punkte eliminieren.
        fs.reduce();

        return new FuzzyInterval(fs);
    }

    @Override
    public FuzzyLRInterval add(final FuzzyLRInterval operand1, final FuzzyLRInterval operand2) {
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
        op1.reduce();
        op2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((op1.size() != 4) || (op2.size() != 4)) {
            throw new RuntimeException();
        }

        FuzzySet fs = new FuzzySet(); // das spaetere Ergebnis
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
        float[] xValuesOfOperand1 = op1.getXValues();
        alpha = Math.abs(xValuesOfOperand1[0] - xValuesOfOperand1[1]);
        m1 = xValuesOfOperand1[1];
        m2 = xValuesOfOperand1[2];
        beta = Math.abs(xValuesOfOperand1[2] - xValuesOfOperand1[3]);

        // Werte fuer den zweiten Operanden ermitteln
        float[] xValuesOfOperand2 = op2.getXValues();
        gamma = Math.abs(xValuesOfOperand2[0] - xValuesOfOperand2[1]);
        n1 = xValuesOfOperand2[1];
        n2 = xValuesOfOperand2[2];
        delta = Math.abs(xValuesOfOperand2[2] - xValuesOfOperand2[3]);
        // Aus den ermittelten Werten das Ergebnis bestimmen.
        sum1 = m1 + n1;
        sum2 = m2 + n2;
        fs.set(sum1 - (alpha + gamma), 0.0f);
        fs.set(sum1, 1.0f);
        fs.set(sum2, 1.0f);
        fs.set(sum2 + (beta + delta), 0.0f);

        return new FuzzyLRInterval(fs);
    }

    @Override
    public FuzzyLRNumber add(final FuzzyLRNumber operand1, final FuzzyLRNumber operand2) {
        /**
         * Wenn alles einwandfrei funktioniert, dann wird die
         * Zugehoerigkeitsfunktion durch genau drei Punkte spezifiziert. Die
         * Differenz zwischen den x-Werten des ersten und zweiten Punktes
         * ergibt alpha, die Differenz zwischen den x-Werten des zweiten und
         * dritten Punktes ergibt beta.
         */

        // Auf einer Kopie arbeiten.
        FuzzyLRNumber op1 = (FuzzyLRNumber) operand1.clone();
        FuzzyLRNumber op2 = (FuzzyLRNumber) operand2.clone();
        op1.reduce();
        op2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((op1.size() != 3) || (op2.size() != 3)) {
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
        float[] xValuesOfOperand1 = op1.getXValues();
        alpha = Math.abs(xValuesOfOperand1[0] - xValuesOfOperand1[1]);
        beta = Math.abs(xValuesOfOperand1[1] - xValuesOfOperand1[2]);
        x1 = xValuesOfOperand1[1];

        // Werte fuer den zweiten Operanden ermitteln.
        float[] xValuesOfOperand2 = op2.getXValues();
        gamma = Math.abs(xValuesOfOperand2[0] - xValuesOfOperand2[1]);
        delta = Math.abs(xValuesOfOperand2[1] - xValuesOfOperand2[2]);
        x2 = xValuesOfOperand2[1];

        // Aus den ermittelten Werten das Ergebnis bestimmen.
        xSum = x1 + x2;

        return new FuzzyLRNumber(xSum, (alpha + gamma), (beta + delta));
    }

    @Override
    public FuzzyNumber add(final FuzzyNumber operand1, final FuzzyNumber operand2) {
        FuzzySet fs = new FuzzySet();

        // Es werden die spezifizierten Zugehoerigkeitsgrade in aufsteigender
        // Reihenfolge untersucht.
        float x;
        float dom = 0.0f;
        int pos1 = 0;
        int pos2 = 0;
        float add;
        float[] xValuesOfOperand1 = operand1.getXValues();
        float[] xValuesOfOperand2 = operand2.getXValues();

        // Durchlaufen der aufsteigenden Flanke
        while (dom != 1.0f) {
            if (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                pos1++;
            } else {
                while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
                    pos1++;
                }
            }

            if (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                pos2++;
            } else {
                while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
                    pos2++;
                }
            }

            x = xValuesOfOperand1[pos1 - 1];
            add = ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                    operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                    xValuesOfOperand1[pos1 - 1])));

            if ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                x = xValuesOfOperand1[pos1 - 1] + add;
            }

            x = x + xValuesOfOperand2[pos2 - 1];
            add = (dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                    operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                    xValuesOfOperand2[pos2 - 1]));

            if ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                x = x + add;
            }

            fs.set(x, dom);
            // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
            dom = ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                    ? operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
        }

        // Die "Spitze" einfuegen.
        while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
            pos1++;
        }

        while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
            pos2++;
        }

        fs.set(xValuesOfOperand1[pos1] + xValuesOfOperand2[pos2], 1.0f);

        // Durchlaufen der abfallenden Flanke.
        while (dom != 0.0f) {
            if (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                pos1++;
            } else {
                while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
                    pos1++;
                }
            }

            if (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                pos2++;
            } else {
                while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
                    pos2++;
                }
            }

            x = xValuesOfOperand1[pos1 - 1];
            add = ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                    operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                    xValuesOfOperand1[pos1 - 1])));

            if ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                x = xValuesOfOperand1[pos1 - 1] + add;
            }

            x = x + xValuesOfOperand2[pos2 - 1];
            add = (dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                    operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                    xValuesOfOperand2[pos2 - 1]));

            if ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                x = x + add;
            }

            fs.set(x, dom);
            // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
            dom = ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                    ? operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
        }

        // Den letzten Punkt bei Zugehoerigkeitsgrad 0.0 einfuegen.
        // Im Prinzip muss es der letzte Eintrag in den Vektoren der Operanden sein
        while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
            pos1++;
        }

        while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
            pos2++;
        }

        fs.set(xValuesOfOperand1[pos1] + xValuesOfOperand2[pos2], 0.0f);
        // Zum Schluss unnoetige Punkte eliminieren.
        fs.reduce();

        return new FuzzyNumber(fs);
    }

    @Override
    public FuzzyInterval divide(final FuzzyInterval operand1, final FuzzyInterval operand2) {
        if (operand2.getDegreeOfMembership(0.0f) == 0.0f) {
            if (operand1.getDegreeOfMembership(0.0f) > 0.0f) {
                return (FuzzyInterval) operand1.clone(); // entspricht 0 / operand2
            }

            // Die Division kann auf die Multiplikation zurueckgefuehrt werden, indem operand2 invertiert und
            // anschliessend zu operand1 multipliziert wird.
            FuzzyInterval operand2a = (FuzzyInterval) operand2.clone();
            operand2a.invert();

            return multiply(operand1, operand2a);
        } else {
            throw new ArithmeticException(FuzzyResourceManager.getString(this, "EXCEPTION_DIVIDE_BY_ZERO"));
        }
    }

    @Override
    public FuzzyLRInterval divide(final FuzzyLRInterval operand1, final FuzzyLRInterval operand2) {
        // Auf einer Kopie arbeiten.
        FuzzyLRInterval op1 = (FuzzyLRInterval) operand1.clone();
        FuzzyLRInterval op2 = (FuzzyLRInterval) operand2.clone();
        op1.reduce();
        op2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((op1.size() != 4) || (op2.size() != 4)) {
            throw new RuntimeException();
        }

        if (operand2.getDegreeOfMembership(0.0f) == 0.0f) {
            if (operand1.getDegreeOfMembership(0.0f) > 0.0f) {
                return (FuzzyLRInterval) operand1.clone(); // entspricht 0 / operand2
            }

            // Die Division kann auf die Multiplikation zurueckgefuehrt werden,
            // indem operand2 invertiert und anschliessend zu operand1 multipliziert wird.
            FuzzyLRInterval operand2a = (FuzzyLRInterval) operand2.clone();
            operand2a.invert();

            return multiply(operand1, operand2a);
        } else {
            throw new ArithmeticException(FuzzyResourceManager.getString(this, "EXCEPTION_DIVIDE_BY_ZERO"));
        }
    }

    @Override
    public FuzzyLRNumber divide(final FuzzyLRNumber operand1, final FuzzyLRNumber operand2) {
        // Auf einer Kopie arbeiten.
        FuzzyLRNumber op1 = (FuzzyLRNumber) operand1.clone();
        FuzzyLRNumber op2 = (FuzzyLRNumber) operand2.clone();
        op1.reduce();
        op2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((op1.size() != 3) || (op2.size() != 3)) {
            throw new RuntimeException();
        }

        if (operand2.getDegreeOfMembership(0.0f) == 0.0f) {
            if (operand1.getDegreeOfMembership(0.0f) > 0.0f) {
                return (FuzzyLRNumber) operand1.clone(); // entspricht 0 / operand2
            }

            // Die Division kann auf die Multiplikation zurueckgefuehrt werden,
            // indem operand2 invertiert und anschliessend zu operand1 multipliziert wird.
            FuzzyLRNumber operand2a = (FuzzyLRNumber) operand2.clone();
            operand2a.invert();

            return multiply(operand1, operand2a);
        } else {
            throw new ArithmeticException(FuzzyResourceManager.getString(this, "EXCEPTION_DIVIDE_BY_ZERO"));
        }
    }

    @Override
    public FuzzyNumber divide(final FuzzyNumber operand1, final FuzzyNumber operand2) {
        if (operand2.getDegreeOfMembership(0.0f) == 0.0f) {
            if (operand1.getDegreeOfMembership(0.0f) > 0.0f) {
                return (FuzzyNumber) operand1.clone(); // entspricht 0 / operand2
            }

            // Die Division kann auf die Multiplikation zurueckgefuehrt werden,
            // indem operand2 invertiert und
            // anschliessend zu operand1 multipliziert wird.
            FuzzyNumber operand2a = (FuzzyNumber) operand2.clone();
            operand2a.invert();

            return multiply(operand1, operand2a);
        } else {
            throw new ArithmeticException(FuzzyResourceManager.getString(this, "EXCEPTION_DIVIDE_BY_ZERO"));
        }
    }

    @Override
    public FuzzyInterval multiply(final FuzzyInterval operand1, final FuzzyInterval operand2) {
        if (((operand1.getDegreeOfMembership(0.0f) == 0.0f) &&
                (operand2.getDegreeOfMembership(0.0f) == 0.0f)) ||
                ((operand1.getDegreeOfMembership(0.0f) != 0.0f) &&
                (operand2.getDegreeOfMembership(0.0f) != 0.0f))) {
            boolean op1Positive = operand1.isPositive();
            boolean op2Positive = operand2.isPositive();

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                // Die Operanden haben unterschiedliches Vorzeichen.
                // Deshalb muss ein Operand temporaer negiert werden.
                if (operand1.size() <= operand2.size()) {
                    operand1.negate();
                } else {
                    operand2.negate();
                }
            }

            FuzzySet fs = new FuzzySet();

            // Es werden die spezifizierten Zugehoerigkeitsgrade in aufsteigender Reihenfolge untersucht.
            float x;
            float dom = 0.0f;
            int pos1 = 0;
            int pos2 = 0;
            float add;
            float[] xValuesOfOperand1 = operand1.getXValues();
            float[] xValuesOfOperand2 = operand2.getXValues();

            // Durchlaufen der aufsteigenden Flanke.
            while (dom != 1.0f) {
                if (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                    pos1++;
                } else {
                    while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
                        pos1++;
                    }
                }

                if (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                    pos2++;
                } else {
                    while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
                        pos2++;
                    }
                }

                x = xValuesOfOperand1[pos1 - 1];
                add = ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                        operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                        xValuesOfOperand1[pos1 - 1])));

                if ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                    x = xValuesOfOperand1[pos1 - 1] + add;
                }

                add = (dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                        operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                        xValuesOfOperand2[pos2 - 1]));

                if ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                    add = add + xValuesOfOperand2[pos2 - 1];
                } else {
                    add = xValuesOfOperand2[pos2 - 1];
                }

                x = x * add;
                //                x = (xValuesOfOperand1[pos1 - 1] + ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) /
                // ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                // operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] - xValuesOfOperand1[pos1 - 1]))));
                // x = x * (xValuesOfOperand2[pos2 - 1] + ((dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) /
                // ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                // operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] - xValuesOfOperand2[pos2 - 1]))));
                fs.set(x, dom);
                // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
                dom = ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                        ? operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
            }

            // Das "Plateau" einfuegen.
            while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
                pos1++;
            }

            while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
                pos2++;
            }

            // linke obere Ecke
            fs.set(xValuesOfOperand1[pos1] * xValuesOfOperand2[pos2], 1.0f);

            while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == 1.0f) {
                pos1++;
            }

            while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == 1.0f) {
                pos2++;
            }

            // rechte obere Ecke
            fs.set(xValuesOfOperand1[pos1--] * xValuesOfOperand2[pos2--], 1.0f);

            // Durchlaufen der abfallenden Flanke.
            while (dom != 0.0f) {
                if (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                    pos1++;
                } else {
                    while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
                        pos1++;
                    }
                }

                if (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                    pos2++;
                } else {
                    while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
                        pos2++;
                    }
                }

                x = xValuesOfOperand1[pos1 - 1];
                add = ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                        operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                        xValuesOfOperand1[pos1 - 1])));

                if ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                    x = xValuesOfOperand1[pos1 - 1] + add;
                }

                add = (dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                        operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                        xValuesOfOperand2[pos2 - 1]));

                if ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
                    add = add + xValuesOfOperand2[pos2 - 1];
                } else {
                    add = xValuesOfOperand2[pos2 - 1];
                }

                x = x * add;
                // x = (xValuesOfOperand1[pos1 - 1] + ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) /
                // ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                // operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] - xValuesOfOperand1[pos1 - 1]))));
                //                x = x * (xValuesOfOperand2[pos2 - 1] + ((dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) /
                // ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                // operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] - xValuesOfOperand2[pos2 - 1]))));
                fs.set(x, dom);
                // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
                dom = ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                        ? operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
            }

            // Den letzten Punkt bei Zugehoerigkeitsgrad 0.0 einfuegen.
            // Im Prinzip muss es der letzte Eintrag in den Vektoren der Operanden sein.
            while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
                pos1++;
            }

            while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
                pos2++;
            }

            fs.set(xValuesOfOperand1[pos1] * xValuesOfOperand2[pos2], 0.0f);
            // Zum Schluss unnoetige Punkte eliminieren.
            fs.reduce();

            FuzzyInterval result = new FuzzyInterval(fs);

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                // Die Operanden hatten unterschiedliches Vorzeichen.
                // Deshalb wurde ein Operand temporaer negiert.
                // Dies wird hier wieder rueckgaengig gemacht.
                if (operand1.size() <= operand2.size()) {
                    operand1.negate();
                } else {
                    operand2.negate();
                }

                // Ausserdem muss das Ergebnis negiert werden.
                result.negate();
            }

            return result;
        } else {
            return (FuzzyInterval) ((operand1.getDegreeOfMembership(0.0f) != 0.0f) ? operand1.clone() : operand2.clone());
        }
    }

    @Override
    public FuzzyLRInterval multiply(final FuzzyLRInterval operand1, final FuzzyLRInterval operand2) {
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
        op1.reduce();
        op2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((op1.size() != 4) || (op2.size() != 4)) {
            throw new RuntimeException();
        }

        if (((op1.getDegreeOfMembership(0.0f) == 0.0f) && (op2.getDegreeOfMembership(0.0f) == 0.0f)) ||
                ((op1.getDegreeOfMembership(0.0f) != 0.0f) && (op2.getDegreeOfMembership(0.0f) != 0.0f))) {
            boolean op1Positive = op1.isPositive();
            boolean op2Positive = op2.isPositive();

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                // Die Operanden haben unterschiedliches Vorzeichen.
                // Deshalb muss ein Operand temporaer negiert werden.

                /*
                if (op1.size() <= op2.size())
                op1.negate();
                else
                 */
                op2.negate();
            }

            FuzzySet fs = new FuzzySet(); // das spaetere Ergebnis
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
            float[] xValuesOfOperand1 = op1.getXValues();
            alpha = Math.abs(xValuesOfOperand1[0] - xValuesOfOperand1[1]);
            m1 = xValuesOfOperand1[1];
            m2 = xValuesOfOperand1[2];
            beta = Math.abs(xValuesOfOperand1[2] - xValuesOfOperand1[3]);

            // Werte fuer den zweiten Operanden ermitteln.
            float[] xValuesOfOperand2 = op2.getXValues();
            gamma = Math.abs(xValuesOfOperand2[0] - xValuesOfOperand2[1]);
            n1 = xValuesOfOperand2[1];
            n2 = xValuesOfOperand2[2];
            delta = Math.abs(xValuesOfOperand2[2] - xValuesOfOperand2[3]);

            // Aus den ermittelten Werten das Ergebnis bestimmen (nach Dubois und Prade).
            mul1 = m1 * n1;
            mul2 = m2 * n2;
            fs.set(mul1 - (((m1 * gamma) + (n1 * alpha)) - (alpha * gamma)), 0.0f);
            fs.set(mul1, 1.0f);
            fs.set(mul2, 1.0f);
            fs.set(mul2 + ((m2 * delta) + (n2 * beta) + (beta * delta)), 0.0f);

            FuzzyLRInterval result = new FuzzyLRInterval(fs);

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                // Die Operanden hatten unterschiedliches Vorzeichen.
                // Deshalb wurde ein Operand temporaer negiert.
                // Dies wird hier wieder rueckgaengig gemacht.

                /*
                if (op1.size() <= op2.size())
                op1.negate();
                else
                op2.negate();
                 */

                // Ausserdem muss das Ergebnis negiert werden.
                result.negate();
            }

            return result;
        }

        return (FuzzyLRInterval) ((operand1.getDegreeOfMembership(0.0f) != 0.0f) ? operand1.clone() : operand2.clone());
    }

    @Override
    public FuzzyLRNumber multiply(final FuzzyLRNumber operand1, final FuzzyLRNumber operand2) {
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
        op1.reduce();
        op2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((op1.size() != 3) || (op2.size() != 3)) {
            throw new RuntimeException();
        }

        if (((op1.getDegreeOfMembership(0.0f) == 0.0f) && (op2.getDegreeOfMembership(0.0f) == 0.0f)) ||
                ((op1.getDegreeOfMembership(0.0f) != 0.0f) && (op2.getDegreeOfMembership(0.0f) != 0.0f))) {
            boolean op1Positive = op1.isPositive();
            boolean op2Positive = op2.isPositive();

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                // Die Operanden haben unterschiedliches Vorzeichen.
                // Deshalb muss ein Operand temporaer negiert werden.

                /*
                if (op1.size() <= op2.size())
                op1.negate();
                else
                 */
                op2.negate();
            }

            float x1;
            float x2;
            float xMul;
            float alpha;
            float beta;
            float gamma;
            float delta;

            // Werte fuer den ersten Operanden ermitteln.
            float[] xValuesOfOperand1 = op1.getXValues();
            alpha = Math.abs(xValuesOfOperand1[0] - xValuesOfOperand1[1]);
            beta = Math.abs(xValuesOfOperand1[1] - xValuesOfOperand1[2]);
            x1 = xValuesOfOperand1[1];

            // Werte fuer den zweiten Operanden ermitteln.
            float[] xValuesOfOperand2 = op2.getXValues();
            gamma = Math.abs(xValuesOfOperand2[0] - xValuesOfOperand2[1]);
            delta = Math.abs(xValuesOfOperand2[1] - xValuesOfOperand2[2]);
            x2 = xValuesOfOperand2[1];

            // Aus den ermittelten Werten das Ergebnis bestimmen (nach Dubois und Prade).
            xMul = x1 * x2;

            FuzzyLRNumber result =
                    new FuzzyLRNumber(xMul, (((x1 * gamma) + (x2 * alpha)) - (alpha * gamma)),
                    ((x1 * delta) + (x2 * beta) + (beta * delta)));

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                // Die Operanden hatten unterschiedliches Vorzeichen.
                // Deshalb wurde ein Operand temporaer negiert.
                // Dies wird hier wieder rueckgaengig gemacht.

                /*
                if (op1.size() <= op2.size())
                op1.negate();
                else
                op2.negate();
                 */

                // Ausserdem muss das Ergebnis negiert werden.
                result.negate();
            }

            return result;
        }

        return (FuzzyLRNumber) ((operand1.getDegreeOfMembership(0.0f) != 0.0f) ? operand1.clone() : operand2.clone());
    }

    @Override
    public FuzzyNumber multiply(final FuzzyNumber operand1, final FuzzyNumber operand2) {
        if (((operand1.getDegreeOfMembership(0.0f) == 0.0f) &&
                (operand2.getDegreeOfMembership(0.0f) == 0.0f)) ||
                ((operand1.getDegreeOfMembership(0.0f) != 0.0f) &&
                (operand2.getDegreeOfMembership(0.0f) != 0.0f))) {
            boolean op1Positive = operand1.isPositive();
            boolean op2Positive = operand2.isPositive();

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                // Die Operanden haben unterschiedliches Vorzeichen.
                // Deshalb muss ein Operand temporaer negiert werden.
                if (operand1.size() <= operand2.size()) {
                    operand1.negate();
                } else {
                    operand2.negate();
                }
            }

            FuzzySet fs = new FuzzySet();

            // Es werden die spezifizierten Zugehoerigkeitsgrade in aufsteigender Reihenfolge untersucht.
            float x;
            float dom = 0.0f;
            int pos1 = 0;
            int pos2 = 0;
            float add;
            float[] xValuesOfOperand1 = operand1.getXValues();
            float[] xValuesOfOperand2 = operand2.getXValues();

            // Durchlaufen der aufsteigenden Flanke.
            while (dom != 1.0f) {
                if (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                    pos1++;
                } else {
                    while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
                        pos1++;
                    }
                }

                if (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                    pos2++;
                } else {
                    while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
                        pos2++;
                    }
                }

                x = xValuesOfOperand1[pos1 - 1];
                add = ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                        operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                        xValuesOfOperand1[pos1 - 1])));

                if ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                    x = xValuesOfOperand1[pos1 - 1] + add;
                }

                add = (dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                        operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                        xValuesOfOperand2[pos2 - 1]));

                if ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
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
                fs.set(x, dom);
                // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
                dom = ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                        ? operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
            }

            // Die "Spitze" einfuegen.
            while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) < dom) {
                pos1++;
            }

            while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) < dom) {
                pos2++;
            }

            fs.set(xValuesOfOperand1[pos1] * xValuesOfOperand2[pos2], 1.0f);

            // Durchlaufen der abfallenden Flanke.
            while (dom != 0.0f) {
                if (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) == dom) {
                    pos1++;
                } else {
                    while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
                        pos1++;
                    }
                }

                if (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) == dom) {
                    pos2++;
                } else {
                    while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
                        pos2++;
                    }
                }

                x = xValuesOfOperand1[pos1 - 1];
                add = ((dom - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) -
                        operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) / (xValuesOfOperand1[pos1] -
                        xValuesOfOperand1[pos1 - 1])));

                if ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) - operand1.getDegreeOfMembership(xValuesOfOperand1[pos1 - 1])) != 0.0f) {
                    x = xValuesOfOperand1[pos1 - 1] + add;
                }

                add = (dom - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) -
                        operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) / (xValuesOfOperand2[pos2] -
                        xValuesOfOperand2[pos2 - 1]));

                if ((operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) - operand2.getDegreeOfMembership(xValuesOfOperand2[pos2 - 1])) != 0.0f) {
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
                fs.set(x, dom);
                // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
                dom = ((operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]))
                        ? operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) : operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]));
            }

            // Den letzten Punkt bei Zugehoerigkeitsgrad 0.0 einfuegen.
            // Im Prinzip muss es der letzte Eintrag in den Vektoren der Operanden sein.
            while (operand1.getDegreeOfMembership(xValuesOfOperand1[pos1]) > dom) {
                pos1++;
            }

            while (operand2.getDegreeOfMembership(xValuesOfOperand2[pos2]) > dom) {
                pos2++;
            }

            fs.set(xValuesOfOperand1[pos1] * xValuesOfOperand2[pos2], 0.0f);
            // Zum Schluss unnoetige Punkte eliminieren.
            fs.reduce();

            FuzzyNumber result = new FuzzyNumber(fs);

            if (((op1Positive) && (!op2Positive)) || ((!op1Positive) && (op2Positive))) {
                // Die Operanden hatten unterschiedliches Vorzeichen.
                // Deshalb wurde ein Operand temporaer negiert.
                // Dies wird hier wieder rueckgaengig gemacht.
                if (operand1.size() <= operand2.size()) {
                    operand1.negate();
                } else {
                    operand2.negate();
                }

                // Ausserdem muss das Ergebnis negiert werden.
                result.negate();
            }

            return result;
        }

        return (FuzzyNumber) ((operand1.getDegreeOfMembership(0.0f) != 0.0f) ? operand1.clone() : operand2.clone());
    }

    @Override
    public FuzzyInterval subtract(final FuzzyInterval operand1, final FuzzyInterval operand2) {
        // Die Subtraktion kann auf die Addition zurueckgefuehrt werden, indem operand2 negiert und anschliessend zu operand1 addiert wird.
        FuzzyInterval operand2a = (FuzzyInterval) operand2.clone();
        operand2a.negate();

        return add(operand1, operand2a);
    }

    @Override
    public FuzzyLRInterval subtract(final FuzzyLRInterval operand1, final FuzzyLRInterval operand2) {
        // Die Subtraktion kann auf die Addition zurueckgefuehrt werden,
        // indem operand2 negiert und anschliessend zu operand1 addiert wird
        // Auf einer Kopie arbeiten.
        FuzzyLRInterval op1 = (FuzzyLRInterval) operand1.clone();
        FuzzyLRInterval op2 = (FuzzyLRInterval) operand2.clone();
        op1.reduce();
        op2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((op1.size() != 4) || (op2.size() != 4)) {
            throw new RuntimeException();
        }

        FuzzyLRInterval operand2a = (FuzzyLRInterval) operand2.clone();
        operand2a.negate();

        return add(operand1, operand2a);
    }

    @Override
    public FuzzyLRNumber subtract(final FuzzyLRNumber operand1, final FuzzyLRNumber operand2) {
        // Die Subtraktion kann auf die Addition zurueckgefuehrt werden,
        // indem operand2 negiert und anschliessend zu operand1 addiert wird.
        // Auf einer Kopie arbeiten.
        FuzzyLRNumber op1 = (FuzzyLRNumber) operand1.clone();
        FuzzyLRNumber op2 = (FuzzyLRNumber) operand2.clone();
        op1.reduce();
        op2.reduce();

        // Naechste Bedingung sollte nie erfuellt sein.
        if ((op1.size() != 3) || (op2.size() != 3)) {
            throw new RuntimeException();
        }

        FuzzyLRNumber operand2a = (FuzzyLRNumber) operand2.clone();
        operand2a.negate();

        return add(operand1, operand2a);
    }

    @Override
    public FuzzyNumber subtract(final FuzzyNumber operand1, final FuzzyNumber operand2)
            throws NullPointerException {
        // Die Subtraktion kann auf die Addition zurueckgefuehrt werden, indem operand2 negiert und anschliessend zu operand1 addiert wird
        FuzzyNumber operand2a = (FuzzyNumber) operand2.clone();
        operand2a.negate();

        return add(operand1, operand2a);
    }
}
