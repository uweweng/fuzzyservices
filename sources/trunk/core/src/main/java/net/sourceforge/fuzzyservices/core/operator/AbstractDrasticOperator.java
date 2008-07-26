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
package net.sourceforge.fuzzyservices.core.operator;

import net.sourceforge.fuzzyservices.core.AbstractOperator;
import net.sourceforge.fuzzyservices.utils.FuzzyManager;
import net.sourceforge.fuzzyservices.core.FuzzySet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The <strong>abstract</strong> class <code>AbstractDrasticOperator</code> is
 * the base class for all fuzzy operators which compute a degree of membership
 * based on conditions.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public abstract class AbstractDrasticOperator
    extends AbstractOperator
    implements Serializable
{
    /**
     * Returns the value if no condition is fullfiled
     *
     * @return a value depending on the calculation rule
     */
    public abstract float getDefaultValue(  );

    /**
     * Returns the value which has to be fulfilled
     * @return a value depending on the calculation rule
     */
    public abstract float getConditionValue(  );

    @Override
    public FuzzySet combine( final FuzzySet fs1, final FuzzySet fs2 )
    {
        if ( ( fs1 != null ) && ( fs2 != null ) )
        {
            FuzzySet fs = new FuzzySet(  );

            // Prinzip: Uebernehme den Teil der Zugehoerigkeitsfunktion,
            // der innerhalb des Intervalls liegt, in dem mindestens eine der
            // Bedingungen zutrifft.
            // Das Intervall wird durch +- STEPWIDTH durch den SONST-Fall
            // eingegrenzt,
            // wenn der berechnete Zugehoerigkeitsgrad bei +-STEPWIDTH es
            // zulaesst.
            float defaultValue = this.getDefaultValue(  );
            float conditionValue = this.getConditionValue(  );

            /*
             * Bei diesen Operatoren ist es sinnvoll, erst alle Punkte (in fs1
             * und fs2) in ein Feld zu schreiben und zu sortieren. Der Vorteil
             * besteht darin, dass das Einfuegen in die resultierende
             * Fuzzy-Menge beschleunigt werden kann.
             */

            /**
             * Es wird ausreichend Speicherplatz reserviert. Erst wird die
             * Fuzzy-Menge mit der groesseren Anzahl von Eintraegen kopiert.
             */
            List<Float> x_values = new ArrayList<Float>( ( fs1.size(  ) + fs2.size(  ) ) - 2 );

            int i = ( ( fs1.size(  ) >= fs2.size(  ) ) ? 1 : 2 );
            Iterator<Float> it = ( ( i == 1 ) ? fs1.iterator(  ) : fs2.iterator(  ) );

            // x-Werte in einen Vektor kopieren
            while ( it.hasNext(  ) )
            {
                x_values.add( it.next(  ) );
            }

            // Die Fuzzy-Menge mit der kleineren Anzahl wird nun einsortiert.
            // Dazu wird die binaere Suche zur Hilfe (vgl. FuzzySet.set())
            // genommen.
            it = ( ( i == 1 ) ? fs2.iterator(  ) : fs1.iterator(  ) );

            float x;

            while ( it.hasNext(  ) )
            {
                x = it.next(  );
insertBlock:
                {
                    if ( x > x_values.get( x_values.size(  ) - 1 ) )
                    {
                        x_values.add( x );

                        break insertBlock;
                    }

                    if ( x < x_values.get( 0 ) )
                    {
                        x_values.add( 0, x );

                        break insertBlock;
                    }

                    // Binaere Suche
                    int minPos = 0;
                    int maxPos = x_values.size(  ) - 1;

                    while ( maxPos != minPos )
                    {
                        i = ( maxPos + minPos ) / 2;

                        if ( x == x_values.get( i ) )
                        {
                            break insertBlock;
                        } else if ( x < x_values.get( i ) )
                        {
                            maxPos = i;
                        } else
                        {
                            minPos = i + 1;
                        }
                    }

                    if ( x != x_values.get( maxPos ) )
                    {
                        // x-Wert ist noch nicht als Eintrag vorhanden
                        x_values.add( maxPos, x );
                    }
                } // insertBlock
            }

            float dom;
            float dom1;
            float dom2;
            float tmp_dom1;
            float tmp_dom2;

            for ( it = x_values.iterator(  ); it.hasNext(  ); )
            {
                x = it.next(  );
                dom1 = fs1.getDegreeOfMembership( x );
                dom2 = fs2.getDegreeOfMembership( x );
                dom = compute( dom1, dom2 );

                if ( ( dom != defaultValue ) ||
                         ( ( dom == defaultValue ) && ( ( dom1 == conditionValue ) || ( dom2 == conditionValue ) ) ) )
                {
                    // Es ist definitiv NICHT der SONST-Fall.
                    // Als erstes pruefen, ob beim naechst kleineren x-Wert der
                    // SONST-Fall eintreten wuerde.
                    tmp_dom1 = fs1.getDegreeOfMembership( x - FuzzyManager.getStepwidth(  ) );
                    tmp_dom2 = fs2.getDegreeOfMembership( x - FuzzyManager.getStepwidth(  ) );

                    if ( ( tmp_dom1 != conditionValue ) && ( tmp_dom2 != conditionValue ) )
                    {
                        // Es ist definitiv der SONST-Fall.
                        fs.set( x - FuzzyManager.getStepwidth(  ), defaultValue );
                    }

                    fs.set( x, dom );
                    // Zum Schluss pruefen, ob beim naechst groesseren x-Wert
                    // der SONST-Fall eintreten wuerde.
                    tmp_dom1 = fs1.getDegreeOfMembership( x + FuzzyManager.getStepwidth(  ) );
                    tmp_dom2 = fs2.getDegreeOfMembership( x + FuzzyManager.getStepwidth(  ) );

                    if ( ( tmp_dom1 != conditionValue ) && ( tmp_dom2 != conditionValue ) )
                    {
                        // Es ist definitiv der SONST-Fall.
                        fs.set( x + FuzzyManager.getStepwidth(  ), defaultValue );
                    }
                }
            }

            fs.reduce(  );

            return fs;
        }

        return null;
    }
}
