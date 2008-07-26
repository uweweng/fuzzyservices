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

import org.junit.runner.RunWith;

import org.junit.runners.Suite;

/**
 *
 * @author Uwe Weng
 */
@RunWith( Suite.class )
@Suite.SuiteClasses( 
        {net.sourceforge.fuzzyservices.core.operator.DrasticProductTest.class,
    net.sourceforge.fuzzyservices.core.operator.EinsteinSumTest.class,
    net.sourceforge.fuzzyservices.core.operator.BoundedDifferenceTest.class,
    net.sourceforge.fuzzyservices.core.operator.BoundedSumTest.class,
    net.sourceforge.fuzzyservices.core.operator.YagerUnionTest.class,
    net.sourceforge.fuzzyservices.core.operator.WernersOrTest.class,
    net.sourceforge.fuzzyservices.core.operator.OperatorManagerTest.class,
    net.sourceforge.fuzzyservices.core.operator.HamacherUnionTest.class,
    net.sourceforge.fuzzyservices.core.operator.HamacherProductTest.class,
    net.sourceforge.fuzzyservices.core.operator.DrasticSumTest.class,
    net.sourceforge.fuzzyservices.core.operator.MaxTest.class,
    net.sourceforge.fuzzyservices.core.operator.MinTest.class,
    net.sourceforge.fuzzyservices.core.operator.EinsteinProductTest.class,
    net.sourceforge.fuzzyservices.core.operator.AlgebraicProductTest.class,
    net.sourceforge.fuzzyservices.core.operator.HamacherIntersectionTest.class,
    net.sourceforge.fuzzyservices.core.operator.YagerIntersectionTest.class,
    net.sourceforge.fuzzyservices.core.operator.WernersAndTest.class,
    net.sourceforge.fuzzyservices.core.operator.MinMaxCompensationTest.class,
    net.sourceforge.fuzzyservices.core.operator.HamacherSumTest.class,
    net.sourceforge.fuzzyservices.core.operator.AlgebraicSumTest.class
}
     )
public class OperatorSuite
{
}
