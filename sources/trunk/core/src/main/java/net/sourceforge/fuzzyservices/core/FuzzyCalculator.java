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

import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;
import java.util.ListIterator;

/**
 * A fuzzy calculator offers the four base aithmethic operations like a normal
 * calculator. It supports addition, substratcion, multiplication, and devision
 * on fuzzy numbers and intervals (even of LR type).
 * The fuzzy calculator is implemented as a singleton.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public final class FuzzyCalculator
{
    /** The singleton instance of this fuzzy calculator. */
    private static transient FuzzyCalculator instance = null;

    /**
     * Gets an instance of this fuzzy calculator.
     * @return an instance of this class
     */
    public static FuzzyCalculator getInstance(  )
    {
        if ( instance == null )
        {
            instance = new FuzzyCalculator(  );
        }

        return instance;
    }

    /**
     * Adds fuzzy interval <code>operand1</code> to fuzzy interval
     * <code>operand2</code>.
     *
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     */
    public FuzzyInterval add( final FuzzyInterval operand1, final FuzzyInterval operand2 )
    {
        FuzzySet fs = new FuzzySet(  );

        // Determine the degree of memberships in ascending order.
        float x;
        float dom = 0.0f;
        MembershipFunctionPoint entryLeft1;
        MembershipFunctionPoint entryRight1;
        MembershipFunctionPoint entryLeft2;
        MembershipFunctionPoint entryRight2;
        int pos1 = 0;
        int pos2 = 0;
        float add;

        // Durchlaufen der aufsteigenden Flanke.
        while ( dom != 1.0f )
        {
            if ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) == dom )
            {
                pos1++;
            } else
            {
                while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) < dom )
                {
                    pos1++;
                }
            }

            if ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) == dom )
            {
                pos2++;
            } else
            {
                while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) < dom )
                {
                    pos2++;
                }
            }

            entryLeft1 = operand1.points.get( pos1 - 1 );
            entryLeft2 = operand2.points.get( pos2 - 1 );
            entryRight1 = operand1.points.get( pos1 );
            entryRight2 = operand2.points.get( pos2 );
            x = entryLeft1.getX(  );
            add = ( 
                      ( dom - entryLeft1.getDegreeOfMembership(  ) ) / ( 
                                                                           ( 
                                                                               entryRight1.getDegreeOfMembership(  ) -
                                                                               entryLeft1.getDegreeOfMembership(  )
                                                                            ) / ( 
                                                                                    entryRight1.getX(  ) -
                                                                                    entryLeft1.getX(  )
                                                                                 )
                                                                        )
                   );

            if ( ( entryRight1.getDegreeOfMembership(  ) - entryLeft1.getDegreeOfMembership(  ) ) != 0.0f )
            {
                x = entryLeft1.getX(  ) + add;
            }

            x = x + entryLeft2.getX(  );
            add = ( dom - entryLeft2.getDegreeOfMembership(  ) ) / ( 
                                                                       ( 
                                                                           entryRight2.getDegreeOfMembership(  ) -
                                                                           entryLeft2.getDegreeOfMembership(  )
                                                                        ) / ( 
                                                                                entryRight2.getX(  ) -
                                                                                entryLeft2.getX(  )
                                                                             )
                                                                    );

            if ( ( entryRight2.getDegreeOfMembership(  ) - entryLeft2.getDegreeOfMembership(  ) ) != 0.0f )
            {
                x = x + add;
            }

            fs.set( x, dom );
            // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
            dom = ( 
                      ( entryRight1.getDegreeOfMembership(  ) < entryRight2.getDegreeOfMembership(  ) )
                      ? entryRight1.getDegreeOfMembership(  ) : entryRight2.getDegreeOfMembership(  )
                   );
        }

        // Das "Plateau" einfuegen.
        while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) < dom )
        {
            pos1++;
        }

        while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) < dom )
        {
            pos2++;
        }

        // linke obere Ecke
        fs.set( ( operand1.points.get( pos1 ) ).getX(  ) + ( operand2.points.get( pos2 ) ).getX(  ), 1.0f );

        while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) == 1.0f )
        {
            pos1++;
        }

        while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) == 1.0f )
        {
            pos2++;
        }

        // rechte obere Ecke
        fs.set( ( operand1.points.get( pos1-- ) ).getX(  ) + ( operand2.points.get( pos2-- ) ).getX(  ), 1.0f );

        // Durchlaufen der abfallenden Flanke.
        while ( dom != 0.0f )
        {
            if ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) == dom )
            {
                pos1++;
            } else
            {
                while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) > dom )
                {
                    pos1++;
                }
            }

            if ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) == dom )
            {
                pos2++;
            } else
            {
                while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) > dom )
                {
                    pos2++;
                }
            }

            entryLeft1 = operand1.points.get( pos1 - 1 );
            entryLeft2 = operand2.points.get( pos2 - 1 );
            entryRight1 = operand1.points.get( pos1 );
            entryRight2 = operand2.points.get( pos2 );
            //                x = (entryLeft1.getX() + ((dom - entryLeft1.getDegreeOfMembership()) / ((entryRight1.getDegreeOfMembership() -
            // entryLeft1.getDegreeOfMembership()) / (entryRight1.getX() - entryLeft1.getX()))));
            //                x = x + (entryLeft2.getX() + ((dom - entryLeft2.getDegreeOfMembership()) / ((entryRight2.getDegreeOfMembership() -
            // entryLeft2.getDegreeOfMembership()) / (entryRight2.getX() - entryLeft2.getX()))));
            x = entryLeft1.getX(  );
            add = ( 
                      ( dom - entryLeft1.getDegreeOfMembership(  ) ) / ( 
                                                                           ( 
                                                                               entryRight1.getDegreeOfMembership(  ) -
                                                                               entryLeft1.getDegreeOfMembership(  )
                                                                            ) / ( 
                                                                                    entryRight1.getX(  ) -
                                                                                    entryLeft1.getX(  )
                                                                                 )
                                                                        )
                   );

            if ( ( entryRight1.getDegreeOfMembership(  ) - entryLeft1.getDegreeOfMembership(  ) ) != 0.0f )
            {
                x = entryLeft1.getX(  ) + add;
            }

            x = x + entryLeft2.getX(  );
            add = ( dom - entryLeft2.getDegreeOfMembership(  ) ) / ( 
                                                                       ( 
                                                                           entryRight2.getDegreeOfMembership(  ) -
                                                                           entryLeft2.getDegreeOfMembership(  )
                                                                        ) / ( 
                                                                                entryRight2.getX(  ) -
                                                                                entryLeft2.getX(  )
                                                                             )
                                                                    );

            if ( ( entryRight2.getDegreeOfMembership(  ) - entryLeft2.getDegreeOfMembership(  ) ) != 0.0f )
            {
                x = x + add;
            }

            fs.set( x, dom );
            // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
            dom = ( 
                      ( entryRight1.getDegreeOfMembership(  ) > entryRight2.getDegreeOfMembership(  ) )
                      ? entryRight1.getDegreeOfMembership(  ) : entryRight2.getDegreeOfMembership(  )
                   );
        }

        // Den letzten Punkt bei Zugehoerigkeitsgrad 0.0 einfuegen.
        // Im Prinzip muss es der letzte Eintrag in den Vektoren der Operanden sein.
        while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) > dom )
        {
            pos1++;
        }

        while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) > dom )
        {
            pos2++;
        }

        fs.set( ( operand1.points.get( pos1 ) ).getX(  ) + ( operand2.points.get( pos2 ) ).getX(  ), 0.0f );
        // Zum Schluss unnoetige Punkte eliminieren.
        fs.reduce(  );

        return new FuzzyInterval( fs );
    }

    /**
     * Adds fuzzy LR interval <code>operand1</code> to fuzzy LR interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRInterval add( final FuzzyLRInterval operand1, final FuzzyLRInterval operand2 )
    {
        /**
         * Wenn alles einwandfrei funktioniert, dann wird die
         * Zugehoerigkeitsfunktion durch genau vier Punkte spezifiziert.
         * Die Differenz zwischen den x-Werten des ersten und zweiten Punktes
         * ergibt alpha, die Differenz zwischen den x-Werten des dritten und
         * vierten Punktes ergibt beta.
         */

        // Auf einer Kopie arbeiten.
        FuzzyLRInterval op1 = (FuzzyLRInterval) operand1.clone(  );
        FuzzyLRInterval op2 = (FuzzyLRInterval) operand2.clone(  );
        op1.reduce(  );
        op2.reduce(  );

        // Naechste Bedingung sollte nie erfuellt sein.
        if ( ( op1.size(  ) != 4 ) || ( op2.size(  ) != 4 ) )
        {
            throw new RuntimeException(  );
        }

        FuzzySet fs = new FuzzySet(  ); // das spaetere Ergebnis
        MembershipFunctionPoint entry;
        MembershipFunctionPoint entryNext;
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
        ListIterator elements = op1.points.listIterator(  );
        entry = (MembershipFunctionPoint) elements.next(  );
        entryNext = (MembershipFunctionPoint) elements.next(  );
        alpha = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
        m1 = entryNext.getX(  );
        entryNext = (MembershipFunctionPoint) elements.next(  );
        m2 = entryNext.getX(  );
        entry = entryNext;
        entryNext = (MembershipFunctionPoint) elements.next(  );
        beta = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
        // Werte fuer den zweiten Operanden ermitteln
        elements = op2.points.listIterator(  );
        entry = (MembershipFunctionPoint) elements.next(  );
        entryNext = (MembershipFunctionPoint) elements.next(  );
        gamma = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
        n1 = entryNext.getX(  );
        entryNext = (MembershipFunctionPoint) elements.next(  );
        n2 = entryNext.getX(  );
        entry = entryNext;
        entryNext = (MembershipFunctionPoint) elements.next(  );
        delta = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
        // Aus den ermittelten Werten das Ergebnis bestimmen.
        sum1 = m1 + n1;
        sum2 = m2 + n2;
        fs.set( sum1 - ( alpha + gamma ), 0.0f );
        fs.set( sum1, 1.0f );
        fs.set( sum2, 1.0f );
        fs.set( sum2 + ( beta + delta ), 0.0f );

        return new FuzzyLRInterval( fs );
    }

    /**
     * Adds fuzzy LR number <code>operand1</code> to fuzzy LR number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRNumber add( final FuzzyLRNumber operand1, final FuzzyLRNumber operand2 )
    {
        /**
         * Wenn alles einwandfrei funktioniert, dann wird die
         * Zugehoerigkeitsfunktion durch genau drei Punkte spezifiziert. Die
         * Differenz zwischen den x-Werten des ersten und zweiten Punktes
         * ergibt alpha, die Differenz zwischen den x-Werten des zweiten und
         * dritten Punktes ergibt beta.
         */

        // Auf einer Kopie arbeiten.
        FuzzyLRNumber op1 = (FuzzyLRNumber) operand1.clone(  );
        FuzzyLRNumber op2 = (FuzzyLRNumber) operand2.clone(  );
        op1.reduce(  );
        op2.reduce(  );

        // Naechste Bedingung sollte nie erfuellt sein.
        if ( ( op1.size(  ) != 3 ) || ( op2.size(  ) != 3 ) )
        {
            throw new RuntimeException(  );
        }

        MembershipFunctionPoint entry;
        MembershipFunctionPoint entryNext;
        float x1;
        float x2;
        float xSum;
        float alpha;
        float beta;
        float gamma;
        float delta;

        // Werte fuer den ersten Operanden ermitteln.
        ListIterator elements = op1.points.listIterator(  );
        entry = (MembershipFunctionPoint) elements.next(  );
        entryNext = (MembershipFunctionPoint) elements.next(  );
        alpha = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
        entry = entryNext;
        entryNext = (MembershipFunctionPoint) elements.next(  );
        beta = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
        x1 = entry.getX(  );
        // Werte fuer den zweiten Operanden ermitteln.
        elements = op2.points.listIterator(  );
        entry = (MembershipFunctionPoint) elements.next(  );
        entryNext = (MembershipFunctionPoint) elements.next(  );
        gamma = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
        entry = entryNext;
        entryNext = (MembershipFunctionPoint) elements.next(  );
        delta = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
        x2 = entry.getX(  );
        // Aus den ermittelten Werten das Ergebnis bestimmen.
        xSum = x1 + x2;

        return new FuzzyLRNumber( xSum, ( alpha + gamma ), ( beta + delta ) );
    }

    /**
     * Adds fuzzy number <code>operand1</code> to fuzzy number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyNumber add( final FuzzyNumber operand1, final FuzzyNumber operand2 )
    {
        FuzzySet fs = new FuzzySet(  );

        // Es werden die spezifizierten Zugehoerigkeitsgrade in aufsteigender
        // Reihenfolge untersucht.
        float x;
        float dom = 0.0f;
        MembershipFunctionPoint entryLeft1;
        MembershipFunctionPoint entryRight1;
        MembershipFunctionPoint entryLeft2;
        MembershipFunctionPoint entryRight2;
        int pos1 = 0;
        int pos2 = 0;
        float add;

        // Durchlaufen der aufsteigenden Flanke
        while ( dom != 1.0f )
        {
            if ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) == dom )
            {
                pos1++;
            } else
            {
                while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) < dom )
                {
                    pos1++;
                }
            }

            if ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) == dom )
            {
                pos2++;
            } else
            {
                while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) < dom )
                {
                    pos2++;
                }
            }

            entryLeft1 = operand1.points.get( pos1 - 1 );
            entryLeft2 = operand2.points.get( pos2 - 1 );
            entryRight1 = operand1.points.get( pos1 );
            entryRight2 = operand2.points.get( pos2 );
            x = entryLeft1.getX(  );
            add = ( 
                      ( dom - entryLeft1.getDegreeOfMembership(  ) ) / ( 
                                                                           ( 
                                                                               entryRight1.getDegreeOfMembership(  ) -
                                                                               entryLeft1.getDegreeOfMembership(  )
                                                                            ) / ( 
                                                                                    entryRight1.getX(  ) -
                                                                                    entryLeft1.getX(  )
                                                                                 )
                                                                        )
                   );

            if ( ( entryRight1.getDegreeOfMembership(  ) - entryLeft1.getDegreeOfMembership(  ) ) != 0.0f )
            {
                x = entryLeft1.getX(  ) + add;
            }

            x = x + entryLeft2.getX(  );
            add = ( dom - entryLeft2.getDegreeOfMembership(  ) ) / ( 
                                                                       ( 
                                                                           entryRight2.getDegreeOfMembership(  ) -
                                                                           entryLeft2.getDegreeOfMembership(  )
                                                                        ) / ( 
                                                                                entryRight2.getX(  ) -
                                                                                entryLeft2.getX(  )
                                                                             )
                                                                    );

            if ( ( entryRight2.getDegreeOfMembership(  ) - entryLeft2.getDegreeOfMembership(  ) ) != 0.0f )
            {
                x = x + add;
            }

            fs.set( x, dom );
            // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
            dom = ( 
                      ( entryRight1.getDegreeOfMembership(  ) < entryRight2.getDegreeOfMembership(  ) )
                      ? entryRight1.getDegreeOfMembership(  ) : entryRight2.getDegreeOfMembership(  )
                   );
        }

        // Die "Spitze" einfuegen.
        while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) < dom )
        {
            pos1++;
        }

        while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) < dom )
        {
            pos2++;
        }

        fs.set( ( operand1.points.get( pos1 ) ).getX(  ) + ( operand2.points.get( pos2 ) ).getX(  ), 1.0f );

        // Durchlaufen der abfallenden Flanke.
        while ( dom != 0.0f )
        {
            if ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) == dom )
            {
                pos1++;
            } else
            {
                while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) > dom )
                {
                    pos1++;
                }
            }

            if ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) == dom )
            {
                pos2++;
            } else
            {
                while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) > dom )
                {
                    pos2++;
                }
            }

            entryLeft1 = operand1.points.get( pos1 - 1 );
            entryLeft2 = operand2.points.get( pos2 - 1 );
            entryRight1 = operand1.points.get( pos1 );
            entryRight2 = operand2.points.get( pos2 );
            //                x = (entryLeft1.getX() + ((dom - entryLeft1.getDegreeOfMembership()) / ((entryRight1.getDegreeOfMembership() -
            // entryLeft1.getDegreeOfMembership()) / (entryRight1.getX() - entryLeft1.getX()))));
            //                x = x + (entryLeft2.getX() + ((dom - entryLeft2.getDegreeOfMembership()) / ((entryRight2.getDegreeOfMembership() -
            // entryLeft2.getDegreeOfMembership()) / (entryRight2.getX() - entryLeft2.getX()))));
            x = entryLeft1.getX(  );
            add = ( 
                      ( dom - entryLeft1.getDegreeOfMembership(  ) ) / ( 
                                                                           ( 
                                                                               entryRight1.getDegreeOfMembership(  ) -
                                                                               entryLeft1.getDegreeOfMembership(  )
                                                                            ) / ( 
                                                                                    entryRight1.getX(  ) -
                                                                                    entryLeft1.getX(  )
                                                                                 )
                                                                        )
                   );

            if ( ( entryRight1.getDegreeOfMembership(  ) - entryLeft1.getDegreeOfMembership(  ) ) != 0.0f )
            {
                x = entryLeft1.getX(  ) + add;
            }

            x = x + entryLeft2.getX(  );
            add = ( dom - entryLeft2.getDegreeOfMembership(  ) ) / ( 
                                                                       ( 
                                                                           entryRight2.getDegreeOfMembership(  ) -
                                                                           entryLeft2.getDegreeOfMembership(  )
                                                                        ) / ( 
                                                                                entryRight2.getX(  ) -
                                                                                entryLeft2.getX(  )
                                                                             )
                                                                    );

            if ( ( entryRight2.getDegreeOfMembership(  ) - entryLeft2.getDegreeOfMembership(  ) ) != 0.0f )
            {
                x = x + add;
            }

            fs.set( x, dom );
            // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
            dom = ( 
                      ( entryRight1.getDegreeOfMembership(  ) > entryRight2.getDegreeOfMembership(  ) )
                      ? entryRight1.getDegreeOfMembership(  ) : entryRight2.getDegreeOfMembership(  )
                   );
        }

        // Den letzten Punkt bei Zugehoerigkeitsgrad 0.0 einfuegen.
        // Im Prinzip muss es der letzte Eintrag in den Vektoren der Operanden sein
        while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) > dom )
        {
            pos1++;
        }

        while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) > dom )
        {
            pos2++;
        }

        fs.set( ( operand1.points.get( pos1 ) ).getX(  ) + ( operand2.points.get( pos2 ) ).getX(  ), 0.0f );
        // Zum Schluss unnoetige Punkte eliminieren.
        fs.reduce(  );

        return new FuzzyNumber( fs );
    }

    /**
     * Devides fuzzy interval <code>operand1</code> through fuzzy interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     * @exception ArithmeticException if the membership function of
     * <code>operand2</code> is not defined at <tt>x = 0</tt>. It would be a
     * division by zero.
     */
    public FuzzyInterval divide( final FuzzyInterval operand1, final FuzzyInterval operand2 )
    {
        if ( operand2.getDegreeOfMembership( 0.0f ) == 0.0f )
        {
            if ( operand1.getDegreeOfMembership( 0.0f ) > 0.0f )
            {
                return (FuzzyInterval) operand1.clone(  ); // entspricht 0 / operand2
            }

            // Die Division kann auf die Multiplikation zurueckgefuehrt werden, indem operand2 invertiert und
            // anschliessend zu operand1 multipliziert wird.
            FuzzyInterval operand2a = (FuzzyInterval) operand2.clone(  );
            operand2a.invert(  );

            return multiply( operand1, operand2a );
        } else
        {
            throw new ArithmeticException( FuzzyResourceManager.getString( this, "EXCEPTION_DIVIDE_BY_ZERO" ) );
        }
    }

    /**
     * Devides fuzzy LR interval <code>operand1</code> through fuzzy LR
     * interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     * @exception ArithmeticException if the membership function of
     * <code>operand2</code> is not defined at <tt>x = 0</tt>. It would be a
     * division by zero.
     */
    public FuzzyLRInterval divide( final FuzzyLRInterval operand1, final FuzzyLRInterval operand2 )
    {
        // Auf einer Kopie arbeiten.
        FuzzyLRInterval op1 = (FuzzyLRInterval) operand1.clone(  );
        FuzzyLRInterval op2 = (FuzzyLRInterval) operand2.clone(  );
        op1.reduce(  );
        op2.reduce(  );

        // Naechste Bedingung sollte nie erfuellt sein.
        if ( ( op1.size(  ) != 4 ) || ( op2.size(  ) != 4 ) )
        {
            throw new RuntimeException(  );
        }

        if ( operand2.getDegreeOfMembership( 0.0f ) == 0.0f )
        {
            if ( operand1.getDegreeOfMembership( 0.0f ) > 0.0f )
            {
                return (FuzzyLRInterval) operand1.clone(  ); // entspricht 0 / operand2
            }

            // Die Division kann auf die Multiplikation zurueckgefuehrt werden,
            // indem operand2 invertiert und anschliessend zu operand1 multipliziert wird.
            FuzzyLRInterval operand2a = (FuzzyLRInterval) operand2.clone(  );
            operand2a.invert(  );

            return multiply( operand1, operand2a );
        } else
        {
            throw new ArithmeticException( FuzzyResourceManager.getString( this, "EXCEPTION_DIVIDE_BY_ZERO" ) );
        }
    }

    /**
     * Devides fuzzy LR number <code>operand1</code> through fuzzy LR number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     * @exception ArithmeticException if the membership function of
     * <code>operand2</code> is not defined at <tt>x = 0</tt>. It would be a
     * division by zero.
     */
    public FuzzyLRNumber divide( final FuzzyLRNumber operand1, final FuzzyLRNumber operand2 )
    {
        // Auf einer Kopie arbeiten.
        FuzzyLRNumber op1 = (FuzzyLRNumber) operand1.clone(  );
        FuzzyLRNumber op2 = (FuzzyLRNumber) operand2.clone(  );
        op1.reduce(  );
        op2.reduce(  );

        // Naechste Bedingung sollte nie erfuellt sein.
        if ( ( op1.size(  ) != 3 ) || ( op2.size(  ) != 3 ) )
        {
            throw new RuntimeException(  );
        }

        if ( operand2.getDegreeOfMembership( 0.0f ) == 0.0f )
        {
            if ( operand1.getDegreeOfMembership( 0.0f ) > 0.0f )
            {
                return (FuzzyLRNumber) operand1.clone(  ); // entspricht 0 / operand2
            }

            // Die Division kann auf die Multiplikation zurueckgefuehrt werden,
            // indem operand2 invertiert und anschliessend zu operand1 multipliziert wird.
            FuzzyLRNumber operand2a = (FuzzyLRNumber) operand2.clone(  );
            operand2a.invert(  );

            return multiply( operand1, operand2a );
        } else
        {
            throw new ArithmeticException( FuzzyResourceManager.getString( this, "EXCEPTION_DIVIDE_BY_ZERO" ) );
        }
    }

    /**
     * Devides fuzzy number <code>operand1</code> through fuzzy number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     * @exception ArithmeticException if the membership function of
     * <code>operand2</code> is not defined at <tt>x = 0</tt>. It would be a
     * division by zero.
     */
    public FuzzyNumber divide( final FuzzyNumber operand1, final FuzzyNumber operand2 )
    {
        if ( operand2.getDegreeOfMembership( 0.0f ) == 0.0f )
        {
            if ( operand1.getDegreeOfMembership( 0.0f ) > 0.0f )
            {
                return (FuzzyNumber) operand1.clone(  ); // entspricht 0 / operand2
            }

            // Die Division kann auf die Multiplikation zurueckgefuehrt werden,
            // indem operand2 invertiert und
            // anschliessend zu operand1 multipliziert wird.
            FuzzyNumber operand2a = (FuzzyNumber) operand2.clone(  );
            operand2a.invert(  );

            return multiply( operand1, operand2a );
        } else
        {
            throw new ArithmeticException( FuzzyResourceManager.getString( this, "EXCEPTION_DIVIDE_BY_ZERO" ) );
        }
    }

    /**
     * Multiplies fuzzy interval <code>operand1</code> with fuzzy interval
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyInterval multiply( final FuzzyInterval operand1, final FuzzyInterval operand2 )
    {
        if ( ( 
                     ( operand1.getDegreeOfMembership( 0.0f ) == 0.0f ) &&
                     ( operand2.getDegreeOfMembership( 0.0f ) == 0.0f )
                  ) ||
                 ( 
                         ( operand1.getDegreeOfMembership( 0.0f ) != 0.0f ) &&
                         ( operand2.getDegreeOfMembership( 0.0f ) != 0.0f )
                      ) )
        {
            boolean op1Positive = operand1.isPositive(  );
            boolean op2Positive = operand2.isPositive(  );

            if ( ( ( op1Positive ) && ( ! op2Positive ) ) || ( ( ! op1Positive ) && ( op2Positive ) ) )
            {
                // Die Operanden haben unterschiedliches Vorzeichen.
                // Deshalb muss ein Operand temporaer negiert werden.
                if ( operand1.size(  ) <= operand2.size(  ) )
                {
                    operand1.negate(  );
                } else
                {
                    operand2.negate(  );
                }
            }

            FuzzySet fs = new FuzzySet(  );

            // Es werden die spezifizierten Zugehoerigkeitsgrade in aufsteigender Reihenfolge untersucht.
            float x;
            float dom = 0.0f;
            MembershipFunctionPoint entryLeft1;
            MembershipFunctionPoint entryRight1;
            MembershipFunctionPoint entryLeft2;
            MembershipFunctionPoint entryRight2;
            int pos1 = 0;
            int pos2 = 0;
            float add;

            // Durchlaufen der aufsteigenden Flanke.
            while ( dom != 1.0f )
            {
                if ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) == dom )
                {
                    pos1++;
                } else
                {
                    while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) < dom )
                    {
                        pos1++;
                    }
                }

                if ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) == dom )
                {
                    pos2++;
                } else
                {
                    while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) < dom )
                    {
                        pos2++;
                    }
                }

                entryLeft1 = operand1.points.get( pos1 - 1 );
                entryLeft2 = operand2.points.get( pos2 - 1 );
                entryRight1 = operand1.points.get( pos1 );
                entryRight2 = operand2.points.get( pos2 );
                x = entryLeft1.getX(  );
                add = ( 
                          ( dom - entryLeft1.getDegreeOfMembership(  ) ) / ( 
                                                                               ( 
                                                                                   entryRight1.getDegreeOfMembership(  ) -
                                                                                   entryLeft1.getDegreeOfMembership(  )
                                                                                ) / ( 
                                                                                        entryRight1.getX(  ) -
                                                                                        entryLeft1.getX(  )
                                                                                     )
                                                                            )
                       );

                if ( ( entryRight1.getDegreeOfMembership(  ) - entryLeft1.getDegreeOfMembership(  ) ) != 0.0f )
                {
                    x = entryLeft1.getX(  ) + add;
                }

                add = ( dom - entryLeft2.getDegreeOfMembership(  ) ) / ( 
                                                                           ( 
                                                                               entryRight2.getDegreeOfMembership(  ) -
                                                                               entryLeft2.getDegreeOfMembership(  )
                                                                            ) / ( 
                                                                                    entryRight2.getX(  ) -
                                                                                    entryLeft2.getX(  )
                                                                                 )
                                                                        );

                if ( ( entryRight2.getDegreeOfMembership(  ) - entryLeft2.getDegreeOfMembership(  ) ) != 0.0f )
                {
                    add = add + entryLeft2.getX(  );
                } else
                {
                    add = entryLeft2.getX(  );
                }

                x = x * add;
                //                x = (entryLeft1.getX() + ((dom - entryLeft1.getDegreeOfMembership()) /
                // ((entryRight1.getDegreeOfMembership() -
                // entryLeft1.getDegreeOfMembership()) / (entryRight1.getX() - entryLeft1.getX()))));
                // x = x * (entryLeft2.getX() + ((dom - entryLeft2.getDegreeOfMembership()) /
                // ((entryRight2.getDegreeOfMembership() -
                // entryLeft2.getDegreeOfMembership()) / (entryRight2.getX() - entryLeft2.getX()))));
                fs.set( x, dom );
                // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
                dom = ( 
                          ( entryRight1.getDegreeOfMembership(  ) < entryRight2.getDegreeOfMembership(  ) )
                          ? entryRight1.getDegreeOfMembership(  ) : entryRight2.getDegreeOfMembership(  )
                       );
            }

            // Das "Plateau" einfuegen.
            while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) < dom )
            {
                pos1++;
            }

            while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) < dom )
            {
                pos2++;
            }

            // linke obere Ecke
            fs.set( ( operand1.points.get( pos1 ) ).getX(  ) * ( operand2.points.get( pos2 ) ).getX(  ), 1.0f );

            while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) == 1.0f )
            {
                pos1++;
            }

            while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) == 1.0f )
            {
                pos2++;
            }

            // rechte obere Ecke
            fs.set( ( operand1.points.get( pos1-- ) ).getX(  ) * ( operand2.points.get( pos2-- ) ).getX(  ), 1.0f );

            // Durchlaufen der abfallenden Flanke.
            while ( dom != 0.0f )
            {
                if ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) == dom )
                {
                    pos1++;
                } else
                {
                    while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) > dom )
                    {
                        pos1++;
                    }
                }

                if ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) == dom )
                {
                    pos2++;
                } else
                {
                    while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) > dom )
                    {
                        pos2++;
                    }
                }

                entryLeft1 = operand1.points.get( pos1 - 1 );
                entryLeft2 = operand2.points.get( pos2 - 1 );
                entryRight1 = operand1.points.get( pos1 );
                entryRight2 = operand2.points.get( pos2 );
                x = entryLeft1.getX(  );
                add = ( 
                          ( dom - entryLeft1.getDegreeOfMembership(  ) ) / ( 
                                                                               ( 
                                                                                   entryRight1.getDegreeOfMembership(  ) -
                                                                                   entryLeft1.getDegreeOfMembership(  )
                                                                                ) / ( 
                                                                                        entryRight1.getX(  ) -
                                                                                        entryLeft1.getX(  )
                                                                                     )
                                                                            )
                       );

                if ( ( entryRight1.getDegreeOfMembership(  ) - entryLeft1.getDegreeOfMembership(  ) ) != 0.0f )
                {
                    x = entryLeft1.getX(  ) + add;
                }

                add = ( dom - entryLeft2.getDegreeOfMembership(  ) ) / ( 
                                                                           ( 
                                                                               entryRight2.getDegreeOfMembership(  ) -
                                                                               entryLeft2.getDegreeOfMembership(  )
                                                                            ) / ( 
                                                                                    entryRight2.getX(  ) -
                                                                                    entryLeft2.getX(  )
                                                                                 )
                                                                        );

                if ( ( entryRight2.getDegreeOfMembership(  ) - entryLeft2.getDegreeOfMembership(  ) ) != 0.0f )
                {
                    add = add + entryLeft2.getX(  );
                } else
                {
                    add = entryLeft2.getX(  );
                }

                x = x * add;
                // x = (entryLeft1.getX() + ((dom - entryLeft1.getDegreeOfMembership()) /
                // ((entryRight1.getDegreeOfMembership() -
                // entryLeft1.getDegreeOfMembership()) / (entryRight1.getX() - entryLeft1.getX()))));
                //                x = x * (entryLeft2.getX() + ((dom - entryLeft2.getDegreeOfMembership()) /
                // ((entryRight2.getDegreeOfMembership() -
                // entryLeft2.getDegreeOfMembership()) / (entryRight2.getX() - entryLeft2.getX()))));
                fs.set( x, dom );
                // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
                dom = ( 
                          ( entryRight1.getDegreeOfMembership(  ) > entryRight2.getDegreeOfMembership(  ) )
                          ? entryRight1.getDegreeOfMembership(  ) : entryRight2.getDegreeOfMembership(  )
                       );
            }

            // Den letzten Punkt bei Zugehoerigkeitsgrad 0.0 einfuegen.
            // Im Prinzip muss es der letzte Eintrag in den Vektoren der Operanden sein.
            while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) > dom )
            {
                pos1++;
            }

            while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) > dom )
            {
                pos2++;
            }

            fs.set( ( operand1.points.get( pos1 ) ).getX(  ) * ( operand2.points.get( pos2 ) ).getX(  ), 0.0f );
            // Zum Schluss unnoetige Punkte eliminieren.
            fs.reduce(  );

            FuzzyInterval result = new FuzzyInterval( fs );

            if ( ( ( op1Positive ) && ( ! op2Positive ) ) || ( ( ! op1Positive ) && ( op2Positive ) ) )
            {
                // Die Operanden hatten unterschiedliches Vorzeichen.
                // Deshalb wurde ein Operand temporaer negiert.
                // Dies wird hier wieder rueckgaengig gemacht.
                if ( operand1.size(  ) <= operand2.size(  ) )
                {
                    operand1.negate(  );
                } else
                {
                    operand2.negate(  );
                }

                // Ausserdem muss das Ergebnis negiert werden.
                result.negate(  );
            }

            return result;
        } else
        {
            return (FuzzyInterval) ( 
                       ( operand1.getDegreeOfMembership( 0.0f ) != 0.0f ) ? operand1.clone(  ) : operand2.clone(  )
                    );
        }
    }

    /**
     * Multiplies fuzzy LR interval <code>operand1</code> with fuzzy LR
     * interval <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRInterval multiply( final FuzzyLRInterval operand1, final FuzzyLRInterval operand2 )
    {
        /**
         * Wenn alles einwandfrei funktioniert, dann wird die
         * Zugehoerigkeitsfunktion durch genau vier Punkte spezifiziert.
         * Die Differenz zwischen den x-Werten des ersten und zweiten Punktes
         * ergibt alpha, die Differenz zwischen den x-Werten des dritten und
         * vierten Punktes ergibt beta.
         */

        // Auf einer Kopie arbeiten.
        FuzzyLRInterval op1 = (FuzzyLRInterval) operand1.clone(  );
        FuzzyLRInterval op2 = (FuzzyLRInterval) operand2.clone(  );
        op1.reduce(  );
        op2.reduce(  );

        // Naechste Bedingung sollte nie erfuellt sein.
        if ( ( op1.size(  ) != 4 ) || ( op2.size(  ) != 4 ) )
        {
            throw new RuntimeException(  );
        }

        if ( ( ( op1.getDegreeOfMembership( 0.0f ) == 0.0f ) && ( op2.getDegreeOfMembership( 0.0f ) == 0.0f ) ) ||
                 ( ( op1.getDegreeOfMembership( 0.0f ) != 0.0f ) && ( op2.getDegreeOfMembership( 0.0f ) != 0.0f ) ) )
        {
            boolean op1Positive = op1.isPositive(  );
            boolean op2Positive = op2.isPositive(  );

            if ( ( ( op1Positive ) && ( ! op2Positive ) ) || ( ( ! op1Positive ) && ( op2Positive ) ) )
            {
                // Die Operanden haben unterschiedliches Vorzeichen.
                // Deshalb muss ein Operand temporaer negiert werden.

                /*
                if (op1.size() <= op2.size())
                op1.negate();
                else
                 */
                op2.negate(  );
            }

            FuzzySet fs = new FuzzySet(  ); // das spaetere Ergebnis
            MembershipFunctionPoint entry;
            MembershipFunctionPoint entryNext;
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
            ListIterator elements = op1.points.listIterator(  );
            entry = (MembershipFunctionPoint) elements.next(  );
            entryNext = (MembershipFunctionPoint) elements.next(  );
            alpha = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
            m1 = entryNext.getX(  );
            entryNext = (MembershipFunctionPoint) elements.next(  );
            m2 = entryNext.getX(  );
            entry = entryNext;
            entryNext = (MembershipFunctionPoint) elements.next(  );
            beta = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
            // Werte fuer den zweiten Operanden ermitteln.
            elements = op2.points.listIterator(  );
            entry = (MembershipFunctionPoint) elements.next(  );
            entryNext = (MembershipFunctionPoint) elements.next(  );
            gamma = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
            n1 = entryNext.getX(  );
            entryNext = (MembershipFunctionPoint) elements.next(  );
            n2 = entryNext.getX(  );
            entry = entryNext;
            entryNext = (MembershipFunctionPoint) elements.next(  );
            delta = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
            // Aus den ermittelten Werten das Ergebnis bestimmen (nach Dubois und Prade).
            mul1 = m1 * n1;
            mul2 = m2 * n2;
            fs.set( mul1 - ( ( ( m1 * gamma ) + ( n1 * alpha ) ) - ( alpha * gamma ) ), 0.0f );
            fs.set( mul1, 1.0f );
            fs.set( mul2, 1.0f );
            fs.set( mul2 + ( ( m2 * delta ) + ( n2 * beta ) + ( beta * delta ) ), 0.0f );

            FuzzyLRInterval result = new FuzzyLRInterval( fs );

            if ( ( ( op1Positive ) && ( ! op2Positive ) ) || ( ( ! op1Positive ) && ( op2Positive ) ) )
            {
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
                result.negate(  );
            }

            return result;
        }

        return (FuzzyLRInterval) ( 
                   ( operand1.getDegreeOfMembership( 0.0f ) != 0.0f ) ? operand1.clone(  ) : operand2.clone(  )
                );
    }

    /**
     * Multiplies fuzzy LR number <code>operand1</code> with fuzzy LR number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRNumber multiply( final FuzzyLRNumber operand1, final FuzzyLRNumber operand2 )
    {
        /**
         * Wenn alles einwandfrei funktioniert, dann wird die
         * Zugehoerigkeitsfunktion durch genau drei Punkte spezifiziert.
         * Die Differenz zwischen den x-Werten des ersten und zweiten Punktes
         * ergibt alpha, die Differenz zwischen den x-Werten des zweiten und
         * dritten Punktes ergibt beta.
         */

        // Auf einer Kopie arbeiten.
        FuzzyLRNumber op1 = (FuzzyLRNumber) operand1.clone(  );
        FuzzyLRNumber op2 = (FuzzyLRNumber) operand2.clone(  );
        op1.reduce(  );
        op2.reduce(  );

        // Naechste Bedingung sollte nie erfuellt sein.
        if ( ( op1.size(  ) != 3 ) || ( op2.size(  ) != 3 ) )
        {
            throw new RuntimeException(  );
        }

        if ( ( ( op1.getDegreeOfMembership( 0.0f ) == 0.0f ) && ( op2.getDegreeOfMembership( 0.0f ) == 0.0f ) ) ||
                 ( ( op1.getDegreeOfMembership( 0.0f ) != 0.0f ) && ( op2.getDegreeOfMembership( 0.0f ) != 0.0f ) ) )
        {
            boolean op1Positive = op1.isPositive(  );
            boolean op2Positive = op2.isPositive(  );

            if ( ( ( op1Positive ) && ( ! op2Positive ) ) || ( ( ! op1Positive ) && ( op2Positive ) ) )
            {
                // Die Operanden haben unterschiedliches Vorzeichen.
                // Deshalb muss ein Operand temporaer negiert werden.

                /*
                if (op1.size() <= op2.size())
                op1.negate();
                else
                 */
                op2.negate(  );
            }

            MembershipFunctionPoint entry;
            MembershipFunctionPoint entryNext;
            float x1;
            float x2;
            float xMul;
            float alpha;
            float beta;
            float gamma;
            float delta;

            // Werte fuer den ersten Operanden ermitteln.
            ListIterator elements = op1.points.listIterator(  );
            entry = (MembershipFunctionPoint) elements.next(  );
            entryNext = (MembershipFunctionPoint) elements.next(  );
            alpha = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
            entry = entryNext;
            entryNext = (MembershipFunctionPoint) elements.next(  );
            beta = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
            x1 = entry.getX(  );
            // Werte fuer den zweiten Operanden ermitteln.
            elements = op2.points.listIterator(  );
            entry = (MembershipFunctionPoint) elements.next(  );
            entryNext = (MembershipFunctionPoint) elements.next(  );
            gamma = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
            entry = entryNext;
            entryNext = (MembershipFunctionPoint) elements.next(  );
            delta = Math.abs( ( entry.getX(  ) - entryNext.getX(  ) ) );
            x2 = entry.getX(  );
            // Aus den ermittelten Werten das Ergebnis bestimmen (nach Dubois und Prade).
            xMul = x1 * x2;

            FuzzyLRNumber result =
                new FuzzyLRNumber( xMul, ( ( ( x1 * gamma ) + ( x2 * alpha ) ) - ( alpha * gamma ) ),
                                   ( ( x1 * delta ) + ( x2 * beta ) + ( beta * delta ) ) );

            if ( ( ( op1Positive ) && ( ! op2Positive ) ) || ( ( ! op1Positive ) && ( op2Positive ) ) )
            {
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
                result.negate(  );
            }

            return result;
        }

        return (FuzzyLRNumber) ( 
                   ( operand1.getDegreeOfMembership( 0.0f ) != 0.0f ) ? operand1.clone(  ) : operand2.clone(  )
                );
    }

    /**
     * Multiplies fuzzy number <code>operand1</code> with fuzzy number
     * <code>operand2</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyNumber multiply( final FuzzyNumber operand1, final FuzzyNumber operand2 )
    {
        if ( ( 
                     ( operand1.getDegreeOfMembership( 0.0f ) == 0.0f ) &&
                     ( operand2.getDegreeOfMembership( 0.0f ) == 0.0f )
                  ) ||
                 ( 
                         ( operand1.getDegreeOfMembership( 0.0f ) != 0.0f ) &&
                         ( operand2.getDegreeOfMembership( 0.0f ) != 0.0f )
                      ) )
        {
            boolean op1Positive = operand1.isPositive(  );
            boolean op2Positive = operand2.isPositive(  );

            if ( ( ( op1Positive ) && ( ! op2Positive ) ) || ( ( ! op1Positive ) && ( op2Positive ) ) )
            {
                // Die Operanden haben unterschiedliches Vorzeichen.
                // Deshalb muss ein Operand temporaer negiert werden.
                if ( operand1.size(  ) <= operand2.size(  ) )
                {
                    operand1.negate(  );
                } else
                {
                    operand2.negate(  );
                }
            }

            FuzzySet fs = new FuzzySet(  );

            // Es werden die spezifizierten Zugehoerigkeitsgrade in aufsteigender Reihenfolge untersucht.
            float x;
            float dom = 0.0f;
            MembershipFunctionPoint entryLeft1;
            MembershipFunctionPoint entryRight1;
            MembershipFunctionPoint entryLeft2;
            MembershipFunctionPoint entryRight2;
            int pos1 = 0;
            int pos2 = 0;
            float add;

            // Durchlaufen der aufsteigenden Flanke.
            while ( dom != 1.0f )
            {
                if ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) == dom )
                {
                    pos1++;
                } else
                {
                    while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) < dom )
                    {
                        pos1++;
                    }
                }

                if ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) == dom )
                {
                    pos2++;
                } else
                {
                    while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) < dom )
                    {
                        pos2++;
                    }
                }

                entryLeft1 = operand1.points.get( pos1 - 1 );
                entryLeft2 = operand2.points.get( pos2 - 1 );
                entryRight1 = operand1.points.get( pos1 );
                entryRight2 = operand2.points.get( pos2 );
                x = entryLeft1.getX(  );
                add = ( 
                          ( dom - entryLeft1.getDegreeOfMembership(  ) ) / ( 
                                                                               ( 
                                                                                   entryRight1.getDegreeOfMembership(  ) -
                                                                                   entryLeft1.getDegreeOfMembership(  )
                                                                                ) / ( 
                                                                                        entryRight1.getX(  ) -
                                                                                        entryLeft1.getX(  )
                                                                                     )
                                                                            )
                       );

                if ( ( entryRight1.getDegreeOfMembership(  ) - entryLeft1.getDegreeOfMembership(  ) ) != 0.0f )
                {
                    x = entryLeft1.getX(  ) + add;
                }

                add = ( dom - entryLeft2.getDegreeOfMembership(  ) ) / ( 
                                                                           ( 
                                                                               entryRight2.getDegreeOfMembership(  ) -
                                                                               entryLeft2.getDegreeOfMembership(  )
                                                                            ) / ( 
                                                                                    entryRight2.getX(  ) -
                                                                                    entryLeft2.getX(  )
                                                                                 )
                                                                        );

                if ( ( entryRight2.getDegreeOfMembership(  ) - entryLeft2.getDegreeOfMembership(  ) ) != 0.0f )
                {
                    add = add + entryLeft2.getX(  );
                } else
                {
                    add = entryLeft2.getX(  );
                }

                x = x * add;
                //                x = (entryLeft1.getX() + ((dom - entryLeft1.getDegreeOfMembership()) /
                // ((entryRight1.getDegreeOfMembership() -
                // entryLeft1.getDegreeOfMembership()) / (entryRight1.getX() - entryLeft1.getX()))));
                //                x = x * (entryLeft2.getX() + ((dom - entryLeft2.getDegreeOfMembership()) /
                // ((entryRight2.getDegreeOfMembership() -
                // entryLeft2.getDegreeOfMembership()) / (entryRight2.getX() - entryLeft2.getX()))));
                fs.set( x, dom );
                // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
                dom = ( 
                          ( entryRight1.getDegreeOfMembership(  ) < entryRight2.getDegreeOfMembership(  ) )
                          ? entryRight1.getDegreeOfMembership(  ) : entryRight2.getDegreeOfMembership(  )
                       );
            }

            // Die "Spitze" einfuegen.
            while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) < dom )
            {
                pos1++;
            }

            while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) < dom )
            {
                pos2++;
            }

            fs.set( ( operand1.points.get( pos1 ) ).getX(  ) * ( operand2.points.get( pos2 ) ).getX(  ), 1.0f );

            // Durchlaufen der abfallenden Flanke.
            while ( dom != 0.0f )
            {
                if ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) == dom )
                {
                    pos1++;
                } else
                {
                    while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) > dom )
                    {
                        pos1++;
                    }
                }

                if ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) == dom )
                {
                    pos2++;
                } else
                {
                    while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) > dom )
                    {
                        pos2++;
                    }
                }

                entryLeft1 = operand1.points.get( pos1 - 1 );
                entryLeft2 = operand2.points.get( pos2 - 1 );
                entryRight1 = operand1.points.get( pos1 );
                entryRight2 = operand2.points.get( pos2 );
                x = entryLeft1.getX(  );
                add = ( 
                          ( dom - entryLeft1.getDegreeOfMembership(  ) ) / ( 
                                                                               ( 
                                                                                   entryRight1.getDegreeOfMembership(  ) -
                                                                                   entryLeft1.getDegreeOfMembership(  )
                                                                                ) / ( 
                                                                                        entryRight1.getX(  ) -
                                                                                        entryLeft1.getX(  )
                                                                                     )
                                                                            )
                       );

                if ( ( entryRight1.getDegreeOfMembership(  ) - entryLeft1.getDegreeOfMembership(  ) ) != 0.0f )
                {
                    x = entryLeft1.getX(  ) + add;
                }

                add = ( dom - entryLeft2.getDegreeOfMembership(  ) ) / ( 
                                                                           ( 
                                                                               entryRight2.getDegreeOfMembership(  ) -
                                                                               entryLeft2.getDegreeOfMembership(  )
                                                                            ) / ( 
                                                                                    entryRight2.getX(  ) -
                                                                                    entryLeft2.getX(  )
                                                                                 )
                                                                        );

                if ( ( entryRight2.getDegreeOfMembership(  ) - entryLeft2.getDegreeOfMembership(  ) ) != 0.0f )
                {
                    add = add + entryLeft2.getX(  );
                } else
                {
                    add = entryLeft2.getX(  );
                }

                x = x * add;
                //                x = (entryLeft1.getX() + ((dom - entryLeft1.getDegreeOfMembership()) /
                // ((entryRight1.getDegreeOfMembership() -
                // entryLeft1.getDegreeOfMembership()) / (entryRight1.getX() - entryLeft1.getX()))));
                //                x = x * (entryLeft2.getX() + ((dom - entryLeft2.getDegreeOfMembership()) /
                // ((entryRight2.getDegreeOfMembership() -
                // entryLeft2.getDegreeOfMembership()) / (entryRight2.getX() - entryLeft2.getX()))));
                fs.set( x, dom );
                // Naechst hoeheren Zugehoerigkeitsgrad auf der aufsteigenden Flanke ermitteln.
                dom = ( 
                          ( entryRight1.getDegreeOfMembership(  ) > entryRight2.getDegreeOfMembership(  ) )
                          ? entryRight1.getDegreeOfMembership(  ) : entryRight2.getDegreeOfMembership(  )
                       );
            }

            // Den letzten Punkt bei Zugehoerigkeitsgrad 0.0 einfuegen.
            // Im Prinzip muss es der letzte Eintrag in den Vektoren der Operanden sein.
            while ( ( operand1.points.get( pos1 ) ).getDegreeOfMembership(  ) > dom )
            {
                pos1++;
            }

            while ( ( operand2.points.get( pos2 ) ).getDegreeOfMembership(  ) > dom )
            {
                pos2++;
            }

            fs.set( ( operand1.points.get( pos1 ) ).getX(  ) * ( operand2.points.get( pos2 ) ).getX(  ), 0.0f );
            // Zum Schluss unnoetige Punkte eliminieren.
            fs.reduce(  );

            FuzzyNumber result = new FuzzyNumber( fs );

            if ( ( ( op1Positive ) && ( ! op2Positive ) ) || ( ( ! op1Positive ) && ( op2Positive ) ) )
            {
                // Die Operanden hatten unterschiedliches Vorzeichen.
                // Deshalb wurde ein Operand temporaer negiert.
                // Dies wird hier wieder rueckgaengig gemacht.
                if ( operand1.size(  ) <= operand2.size(  ) )
                {
                    operand1.negate(  );
                } else
                {
                    operand2.negate(  );
                }

                // Ausserdem muss das Ergebnis negiert werden.
                result.negate(  );
            }

            return result;
        }

        return (FuzzyNumber) ( 
                   ( operand1.getDegreeOfMembership( 0.0f ) != 0.0f ) ? operand1.clone(  ) : operand2.clone(  )
                );
    }

    /**
     * Subtracts fuzzy interval <code>operand2</code> from fuzzy interval
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyInterval subtract( final FuzzyInterval operand1, final FuzzyInterval operand2 )
    {
        // Die Subtraktion kann auf die Addition zurueckgefuehrt werden, indem operand2 negiert und anschliessend zu operand1 addiert wird.
        FuzzyInterval operand2a = (FuzzyInterval) operand2.clone(  );
        operand2a.negate(  );

        return add( operand1, operand2a );
    }

    /**
     * Subtracts fuzzy LR interval <code>operand2</code> from fuzzy LR interval
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRInterval subtract( final FuzzyLRInterval operand1, final FuzzyLRInterval operand2 )
    {
        // Die Subtraktion kann auf die Addition zurueckgefuehrt werden,
        // indem operand2 negiert und anschliessend zu operand1 addiert wird
        // Auf einer Kopie arbeiten.
        FuzzyLRInterval op1 = (FuzzyLRInterval) operand1.clone(  );
        FuzzyLRInterval op2 = (FuzzyLRInterval) operand2.clone(  );
        op1.reduce(  );
        op2.reduce(  );

        // Naechste Bedingung sollte nie erfuellt sein.
        if ( ( op1.size(  ) != 4 ) || ( op2.size(  ) != 4 ) )
        {
            throw new RuntimeException(  );
        }

        FuzzyLRInterval operand2a = (FuzzyLRInterval) operand2.clone(  );
        operand2a.negate(  );

        return add( operand1, operand2a );
    }

    /**
     * Subtracts fuzzy LR number <code>operand2</code> from fuzzy LR number
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyLRNumber subtract( final FuzzyLRNumber operand1, final FuzzyLRNumber operand2 )
    {
        // Die Subtraktion kann auf die Addition zurueckgefuehrt werden,
        // indem operand2 negiert und anschliessend zu operand1 addiert wird.
        // Auf einer Kopie arbeiten.
        FuzzyLRNumber op1 = (FuzzyLRNumber) operand1.clone(  );
        FuzzyLRNumber op2 = (FuzzyLRNumber) operand2.clone(  );
        op1.reduce(  );
        op2.reduce(  );

        // Naechste Bedingung sollte nie erfuellt sein.
        if ( ( op1.size(  ) != 3 ) || ( op2.size(  ) != 3 ) )
        {
            throw new RuntimeException(  );
        }

        FuzzyLRNumber operand2a = (FuzzyLRNumber) operand2.clone(  );
        operand2a.negate(  );

        return add( operand1, operand2a );
    }

    /**
     * Subtracts fuzzy number <code>operand2</code> from fuzzy number
     * <code>operand1</code>.
     * @return The result of this algebraic operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @exception NullPointerException if at least one operand is
     * <code>null</code>
     */
    public FuzzyNumber subtract( final FuzzyNumber operand1, final FuzzyNumber operand2 )
                         throws NullPointerException
    {
        // Die Subtraktion kann auf die Addition zurueckgefuehrt werden, indem operand2 negiert und anschliessend zu operand1 addiert wird
        FuzzyNumber operand2a = (FuzzyNumber) operand2.clone(  );
        operand2a.negate(  );

        return add( operand1, operand2a );
    }
}
