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

import net.sourceforge.fuzzyservices.core.FuzzySet;
import java.io.Serializable;
import java.util.Iterator;

/**
 * The <strong>abstract</strong> class
 * <code>AbstractMultiplyingParameteredOperator</code> is the base for
 * parameterized fuzzy operators which process a complex relationship between
 * degree of memberships. When combining such membership functions only the
 * iteration of the x axis is possible in order to calculate the result
 * depending on a parameter. The degree of memberships to this values are
 * multiplied.
 * The class is abstract because the calculation rules of operators are different.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public abstract class AbstractMultiplyingParameteredOperator
        extends AbstractComplexParameteredOperator implements Serializable {

    @Override
    public FuzzySet combine(final FuzzySet fs1, final FuzzySet fs2) {
        if ((fs1 != null) && (fs2 != null)) {
            // Special cases
            if ((!fs1.isDefined()) || (!fs2.isDefined())) {
                return new FuzzySet();
            }

            // Singleton requires a special treatment.
            if (fs1.size() == 1) {
                FuzzySet fs = new FuzzySet();
                Iterator<Float> it = fs1.iterator();
                float x = it.next();
                fs.set(x,
                        compute(fs1.getDegreeOfMembership(x),
                        fs2.getDegreeOfMembership(x)));

                fs.reduce();

                return fs;
            }

            if (fs2.size() == 1) {
                FuzzySet fs = new FuzzySet();
                Iterator<Float> it = fs2.iterator();
                float x = it.next();
                fs.set(x,
                        compute(fs1.getDegreeOfMembership(x),
                        fs2.getDegreeOfMembership(x)));

                fs.reduce();

                return fs;
            }

            // Idee: Es wird nur der Bereich durchlaufen,
            // in dem die Zugehoerigkeitsfunktionen beider Fuzzy-Mengen
            // definiert sind.
            // Gemeinsamen Definitionsbereich ermitteln.
            float minx1 = fs1.getMinDefinedX();
            float minx2 = fs2.getMinDefinedX();
            float maxx1 = fs1.getMaxDefinedX();
            float maxx2 = fs2.getMaxDefinedX();
            float minx = ((minx1 > minx2) ? minx1 : minx2);
            float maxx = ((maxx1 < maxx2) ? maxx1 : maxx2);

            // Sonderfall: Zugehoerigkeitsfunktionen ueberlappen sich nicht.
            if (minx > maxx) {
                return new FuzzySet();
            }

            // Standardfall:
            // Alle relevanten, dh in dem gemeinsamen Bereich liegenden, Punkte
            // in temporaere Fuzzy-Mengen kopieren.
            FuzzySet tmp_fs1 = new FuzzySet();
            FuzzySet tmp_fs2 = new FuzzySet();
            // Untere Grenze fuer fs1 in die neue, temporaere Fuzzy-Menge
            // einfuegen.
            tmp_fs1.set(minx, fs1.getDegreeOfMembership(minx));

            // Alle Punkte aus dem gemeinsamen Intervall uebernehmen.
            float x;
            Iterator<Float> it = fs1.iterator();
            x = it.next();

            while (x < maxx) {
                if (x > minx) {
                    tmp_fs1.set(x, fs1.getDegreeOfMembership(x));
                }
                x = it.next();
            }

            // Obere Grenze fuer fs1 in die neue, temporaere Fuzzy-Menge
            // einfuegen.
            tmp_fs1.set(maxx, fs1.getDegreeOfMembership(maxx));
            // Das gleiche Spiel auch mit fs2 durchfuehren.
            // Untere Grenze fuer fs1 in die neue, temporaere Fuzzy-Menge
            // einfuegen.
            tmp_fs2.set(minx, fs2.getDegreeOfMembership(minx));
            // Alle Punkte aus dem gemeinsamen Intervall uebernehmen.
            it = fs2.iterator();
            x = it.next();

            while (x < maxx) {
                if (x > minx) {
                    tmp_fs2.set(x, fs2.getDegreeOfMembership(x));
                }
                x = it.next();
            }

            // Obere Grenze fuer fs2 in die neue, temporaere Fuzzy-Menge
            // einfuegen.
            tmp_fs2.set(maxx, fs2.getDegreeOfMembership(maxx));

            return super.combine(tmp_fs1, tmp_fs2);
        }

        return null;
    }
}
