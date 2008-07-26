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
import net.sourceforge.fuzzyservices.core.defuzzification.CenterOfArea;
import net.sourceforge.fuzzyservices.core.operator.Min;

import java.io.Serializable;
import java.util.Iterator;

/**
 * The class <code>Fact</code> defines a relationship between a value and a term of a linguistic variable.
 * To which term the value is associated depends on the degree of compatibility between value and all linguistic terms.
 * The value can be interpreted as current state of a linguistic variable.
 *
 * @see LinguisticVariable
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class Fact
    implements Cloneable,
               Serializable
{
    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /** The current value is stored as a fuzzy set. Every crisp value is transformed to a fuzzy set. */
    private FuzzySet value;

    /** The linguistic variable as base for the fact. */
    private LinguisticVariable lingVar;

    /**
     * Default constructor. Linguistic Variable is unknown.
     */
    public Fact(  )
    {
    }

    /**
     * Constructs a new fact with the linguistic variable <code>lv</code> as base.
     * The first value is of type <code>byte</code>.
     * @param lv the linguistic variable as base.
     * @param newValue the first value which describes the state of the linguistic variable.
     * @exception IllegalArgumentException if <code>lv</code> is <code>null</code>
     */
    public Fact( final LinguisticVariable lv, final byte newValue )
         throws IllegalArgumentException
    {
        if ( lv != null )
        {
            this.lingVar = lv;
            this.value = new FuzzySet( newValue );
        } else
        {
            throw new IllegalArgumentException( FuzzyResourceManager.getString( this,
                                                                                "EXCEPTION_UNKNOWN_LINGUISTIC_VARIABLE" ) );
        }
    }

    /**
     * Constructs a new fact with the linguistic variable <code>lv</code> as base.
     * The first value is of type <code>double</code>.
     * @param lv the linguistic variable as base.
     * @param newValue the first value which describes the state of the linguistic variable.
     * @exception IllegalArgumentException if <code>lv</code> is <code>null</code>
     */
    public Fact( final LinguisticVariable lv, final double newValue )
         throws IllegalArgumentException
    {
        if ( lv != null )
        {
            this.lingVar = lv;
            this.value = new FuzzySet( (float) newValue );
        } else
        {
            throw new IllegalArgumentException( FuzzyResourceManager.getString( this,
                                                                                "EXCEPTION_UNKNOWN_LINGUISTIC_VARIABLE" ) );
        }
    }

    /**
     * Constructs a new fact with the linguistic variable <code>lv</code> as base.
     * The first value is of type <code>float</code>.
     * @param lv the linguistic variable as base.
     * @param newValue the first value which describes the state of the linguistic variable.
     * @exception IllegalArgumentException if <code>lv</code> is <code>null</code>
     */
    public Fact( final LinguisticVariable lv, final float newValue )
         throws IllegalArgumentException
    {
        if ( lv != null )
        {
            this.lingVar = lv;
            this.value = new FuzzySet( newValue );
        } else
        {
            throw new IllegalArgumentException( FuzzyResourceManager.getString( this,
                                                                                "EXCEPTION_UNKNOWN_LINGUISTIC_VARIABLE" ) );
        }
    }

    /**
     * Constructs a new fact with the linguistic variable <code>lv</code> as base.
     * The first value is of type <code>int</code>.
     * @param lv the linguistic variable as base.
     * @param newValue the first value which describes the state of the linguistic variable.
     * @exception IllegalArgumentException if <code>lv</code> is <code>null</code>
     */
    public Fact( final LinguisticVariable lv, final int newValue )
         throws IllegalArgumentException
    {
        if ( lv != null )
        {
            this.lingVar = lv;
            this.value = new FuzzySet( newValue );
        } else
        {
            throw new IllegalArgumentException( FuzzyResourceManager.getString( this,
                                                                                "EXCEPTION_UNKNOWN_LINGUISTIC_VARIABLE" ) );
        }
    }

    /**
     * Constructs a new fact with the linguistic variable <code>lv</code> as base.
     * The first value is of type <code>long</code>.
     * @param lv the linguistic variable as base.
     * @param newValue the first value which describes the state of the linguistic variable.
     * @exception IllegalArgumentException if <code>lv</code> is <code>null</code>
     */
    public Fact( final LinguisticVariable lv, final long newValue )
         throws IllegalArgumentException
    {
        if ( lv != null )
        {
            this.lingVar = lv;
            this.value = new FuzzySet( newValue );
        } else
        {
            throw new IllegalArgumentException( FuzzyResourceManager.getString( this,
                                                                                "EXCEPTION_UNKNOWN_LINGUISTIC_VARIABLE" ) );
        }
    }

    /**
     * Constructs a new fact with the linguistic variable <code>lv</code> as base.
     * The first value is of type <code>short</code>.
     * @param lv the linguistic variable as base.
     * @param newValue the first value which describes the state of the linguistic variable.
     * @exception IllegalArgumentException if <code>lv</code> is <code>null</code>
     */
    public Fact( final LinguisticVariable lv, final short newValue )
         throws IllegalArgumentException
    {
        if ( lv != null )
        {
            this.lingVar = lv;
            this.value = new FuzzySet( newValue );
        } else
        {
            throw new IllegalArgumentException( FuzzyResourceManager.getString( this,
                                                                                "EXCEPTION_UNKNOWN_LINGUISTIC_VARIABLE" ) );
        }
    }

    /**
     * Constructs a new fact with the linguistic variable <code>lv</code> as base. The first value is a membership function.
     * @param lv the linguistic variable as base.
     * @param newValue the first value which describes the state of the linguistic variable.
     * @exception IllegalArgumentException if <code>lv</code> is <code>null</code>
     * @see FuzzySet
     * @see FuzzyNumber
     * @see FuzzyLRNumber
     * @see FuzzyInterval
     * @see FuzzyLRInterval
     */
    public Fact( final LinguisticVariable lv, final MembershipFunction newValue )
         throws IllegalArgumentException
    {
        if ( lv != null )
        {
            this.lingVar = lv;
            this.value = new FuzzySet(  );

            if ( newValue != null )
            {
                for ( Iterator<Float> it = newValue.iterator(  ); it.hasNext(  ); )
                {
                    float x = it.next(  );
                    this.value.set( x,
                                    newValue.getDegreeOfMembership( x ) );
                }
            }
        } else
        {
            throw new IllegalArgumentException( FuzzyResourceManager.getString( this,
                                                                                "EXCEPTION_UNKNOWN_LINGUISTIC_VARIABLE" ) );
        }
    }

    /**
     * Constructs a new fact with the linguistic variable <code>lv</code> as base.
     * The first value is the linguistic term with the name <code>value</code> which belongs to the linguistic variable.
     * @param lv the linguistic variable as base.
     * @param newValue the name of a linguistic term describing the state of the linguistic variable.
     * @exception IllegalArgumentException if <code>lv</code> is <code>null</code> or linguistic variable has not got any term with this name
     * @see LinguisticVariable
     */
    public Fact( final LinguisticVariable lv, final String newValue )
         throws IllegalArgumentException
    {
        FuzzySet termFSet = lv.getFuzzySet( newValue );

        if ( termFSet != null )
        {
            this.lingVar = lv;
            this.value = termFSet;
        } else
        {
            throw new IllegalArgumentException( FuzzyResourceManager.getString(
                                                                                this,
                                                                                "EXCEPTION_UNKNOWN_LINGUISTIC_TERM",
                                                                                new Object[] { lv.getName(  ), newValue } ) );
        }
    }

    /**
     * Returns the degree of memberships of the current value to the linguistic terms which are part of the linguistic variable.
     * It is the minimum height of value and membership function of the linguistic term.
     * @return a discrete fuzzy set with the names of linguistic terms and appropiated degree of memberships
     */
    public synchronized DiscreteFuzzySet evaluate(  )
    {
        DiscreteFuzzySet<String> dfs = new DiscreteFuzzySet<String>(  );
        Min min = new Min(  ); // the operator for computing the degree of membership

        for ( Iterator<String> it = lingVar.getNames(  ); it.hasNext(  ); )
        {
            String name = it.next(  );
            dfs.add( name,
                     ( lingVar.getFuzzySet( name ).combine( value, min ) ).getHeight(  ) );
        }

        return dfs;
    }

    /**
     * Returns the current value as a new fuzzy set.
     * @return the value as a new fuzzy set
     */
    public FuzzySet get(  )
    {
        return ( (FuzzySet) value.clone(  ) );
    }

    /**
     * Returns the linguistic variable of this fact.
     * @return the linguistic variable
     */
    public LinguisticVariable getLinguisticVariable(  )
    {
        return lingVar;
    }

    /**
     * Returns the name of linguistic variable which belongs to this fact.
     * @return the name
     */
    public String getLinguisticVariableName(  )
    {
        return lingVar.getName(  );
    }

    /**
     * Defines a new value for this fact. It is a crisp value of type <code>byte</code>.
     * @param newValue The new value for the property.
     * @see #get
     */
    public final synchronized void set( final byte newValue )
    {
        this.value = new FuzzySet( newValue );
    }

    /**
     * Defines a new value for this fact. It is a crisp value of type <code>float</code>.
     * @param newValue The new value for the property.
     * @see #get
     */
    public final synchronized void set( final double newValue )
    {
        this.value = new FuzzySet( (float) newValue );
    }

    /**
     * Defines a new value for this fact. It is a crisp value of type <code>float</code>.
     * @param newValue The new value for the property.
     * @see #get
     */
    public final synchronized void set( final float newValue )
    {
        this.value = new FuzzySet( newValue );
    }

    /**
     * Defines a new value for this fact. It is a crisp value of type <code>int</code>.
     * @param newValue The new value for the property.
     * @see #get
     */
    public final synchronized void set( final int newValue )
    {
        this.value = new FuzzySet( newValue );
    }

    /**
     * Defines a new value for this fact. It is a crisp value of type <code>short</code>.
     * @param newValue The new value for the property.
     * @see #get
     */
    public final synchronized void set( final short newValue )
    {
        this.value = new FuzzySet( newValue );
    }

    /**
     * Defines a new value for this fact. It is a membership function.
     * @param newValue The new value for the property.
     * @see #get
     * @see FuzzySet
     * @see FuzzyNumber
     * @see FuzzyLRNumber
     * @see FuzzyInterval
     * @see FuzzyLRInterval
     */
    public final synchronized void set( final MembershipFunction newValue )
    {
        if ( newValue != null )
        {
            // At first, clear the old value
            this.value.clear(  );

            for ( Iterator<Float> it = newValue.iterator(  ); it.hasNext(  ); )
            {
                float x = it.next(  );
                this.value.set( x,
                                newValue.getDegreeOfMembership( x ) );
            }
        }
    }

    /**
     * Defines a new value for this fact. It is the name of a linguistic term which belongs to the linguistic variable.
     * @param newValue The name of the linguistic term.
     * @exception IllegalArgumentException if linguistic variable has not got any term with this name.
     * @see #get
     * @see LinguisticVariable
     */
    public final synchronized void set( final String newValue )
                          throws IllegalArgumentException
    {
        FuzzySet termFSet = lingVar.getFuzzySet( newValue );

        if ( termFSet != null )
        {
            this.value = termFSet;
        } else
        {
            throw new IllegalArgumentException( FuzzyResourceManager.getString(
                                                                                this,
                                                                                "EXCEPTION_UNKNOWN_LINGUISTIC_TERM",
                                                                                new Object[]
                                                                                {
                                                                                    lingVar.getName(  ), newValue
                                                                                } ) );
        }
    }

    @Override
    public final boolean equals( Object obj )
    {
        if ( ( obj != null ) &&
                 ( obj instanceof Fact ) &&
                 ( value.equals( ( (Fact) obj ).value ) ) &&
                 ( lingVar.equals( ( (Fact) obj ).lingVar ) ) )
        {
            return true;
        } else
        {
            return false;
        }
    }

    @Override
    public int hashCode(  )
    {
        int hash = 3;
        hash = ( 89 * hash ) + ( ( this.value != null ) ? this.value.hashCode(  ) : 0 );
        hash = ( 89 * hash ) + ( ( this.lingVar != null ) ? this.lingVar.hashCode(  ) : 0 );

        return hash;
    }

    @Override
    public Object clone(  )
    {
        try
        {
            Fact newObj = (Fact) super.clone(  );
            // In opposite to the linguistic variable the value of the fact is cloned
            newObj.value = (FuzzySet) value.clone(  );

            return newObj;
        } catch ( java.lang.CloneNotSupportedException e )
        {
            // It is impossible
            throw new InternalError( e.toString(  ) );
        }
    }

    @Override
    public String toString(  )
    {
        return toString( false );
    }

    /**
     * Returns a textual representation of the fact
     * @param withLingVar <code>true</code> if the definition of the linguistic variable are also returned,
     * <code>false</code> otherwise.
     * @return a string representation of the fact
     * @see LinguisticVariable
     */
    public String toString( final boolean withLingVar )
    {
        String text = "";

        if ( withLingVar )
        {
            // The long version
            text = lingVar.toString(  ) + "\n";
        }

        String lingVarName = lingVar.getName(  );

        if ( value == null )
        {
            return text +
                   ( lingVarName + " = " + FuzzyResourceManager.getString( this, "FACT_UNKNOWN_LINGUISTIC_TERM" ) );
        }

        // Compare the fuzzy set with all terms in order to print the name of the term.
        for ( Iterator<String> it = lingVar.getNames(  ); it.hasNext(  ); )
        {
            String name = it.next(  );

            if ( value.equals( lingVar.getFuzzySet( name ) ) )
            {
                return text + ( lingVarName + " = " + name );
            }
        }

        // Otherwise the value is defuzzified.
        if ( value.size(  ) == 1 )
        {
            return text +
                   (
                       lingVarName + " = " +
                       FuzzyResourceManager.getString( this,
                                                       "FACT_CRISP_VALUE",
                                                       new Object[]
                                                       {
                                                           Float.toString( value.getMinDefinedX(  ) ),
                                                           Float.toString( value.getHeight(  ) )
                                                       } )
                    );
        } else if ( value.size(  ) == 0 )
        {
            return text +
                   ( lingVarName + " = " + FuzzyResourceManager.getString( this, "FACT_UNKNOWN_LINGUISTIC_TERM" ) );
        } else
        {
            return text +
                   (
                       lingVarName + " = " +
                       FuzzyResourceManager.getString( this,
                                                       "FACT_FUZZY_VALUE",
                                                       new Object[]
                                                       {
                                                           Float.toString( ( new CenterOfArea(  ) ).defuzzify( value ) )
                                                       } )
                    );
        }
    }
}
