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

import net.sourceforge.fuzzyservices.utils.FuzzyManager;
import net.sourceforge.fuzzyservices.utils.FuzzyResourceManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Instances of <code>DiscreteFuzzySet</code> represent a fuzzy set with discrete objects
 * of one type. The membership to this set is specified by a number between 0.0 and 1.0.
 * It is the so-called degree of membership.
 *
 * @param <E> the type of elements maintained by this set
 * @version 1.0
 * @author Uwe Weng
 */
public final class DiscreteFuzzySet<E> implements Cloneable, Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Set of objects (as key) with its degree of membership as value.
     */
    private Map<E, Float> objects = new HashMap<E, Float>();

    /**
     * Constructs a discrete fuzzy set of type <code>E</code>.
     */
    public DiscreteFuzzySet() {
    }

    /**
     * Constructs a discrete fuzzy set with <code>obj</code> as first object,
     * which is member of the set. Further objects has to be of the same type.
     *
     * @param obj
     *            the object
     * @param dom
     *            the degree of membership of <code>obj</code> in <tt>[0,1]</tt>
     */
    public DiscreteFuzzySet(final E obj, final float dom) {
        add(obj, dom);
    }

    /**
     * Adds an object, if the degree of membership is greater than 0.
     *
     * @param obj
     *            the object
     * @param dom
     *            the degree of membership of <code>obj</code> in <tt>[0,1]</tt>
     */
    public synchronized void add(final E obj, final float dom) {
        if (obj != null) {
            if ((dom >= 0.0f) && (dom <= 1.0f)) {
                if (dom > 0.0f) {
                    objects.put(obj, new Float(FuzzyManager.round(dom)));
                }
            } else {
                throw new IllegalArgumentException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_INVALID_DEGREE_OF_MEMBERSHIP",
                        new Object[]{Float.toString(dom)}));
            }
        }
    }

    /** Deletes all objects from the fuzzy set. */
    public synchronized void clear() {
        objects.clear();
    }

    @Override
    public Object clone() {
        try {
            DiscreteFuzzySet newObj = (DiscreteFuzzySet) super.clone();

            return newObj;
        } catch (java.lang.CloneNotSupportedException e) {
            // it is impossible
            throw new InternalError(e.toString());
        }
    }

    /**
     * Combines this instance with a second discrete fuzzy sets to a new discrete fuzzy set.
     *
     * @param dfs The second operand
     * @param op the operator for combining (required)
     * @return the result of this operation. It is a new discrete fuzzy set.
     */
    public synchronized DiscreteFuzzySet<E> combine(final DiscreteFuzzySet<E> dfs, final AbstractOperator op) {
        if (dfs != null) {
            DiscreteFuzzySet<E> resultDiscreteFuzzySet = new DiscreteFuzzySet<E>();

            // Iterating both discrete fuzzy sets and combinding the elements
            for (Iterator<E> it = iterator(); it.hasNext();) {
                E object = it.next();
                resultDiscreteFuzzySet.put(object,
                        op.compute(
                        getDegreeOfMembership(object),
                        dfs.getDegreeOfMembership(object)));
            }

            for (Iterator<E> it = dfs.iterator(); it.hasNext();) {
                E object = it.next();
                resultDiscreteFuzzySet.put(object,
                        op.compute(
                        getDegreeOfMembership(object),
                        dfs.getDegreeOfMembership(object)));
            }

            return resultDiscreteFuzzySet;
        }

        return null;
    }

    /** Concentrates the discrete fuzzy set. That means, degree of memberships are squared.*/
    public synchronized void concentrate() {
        for (Iterator<E> it = objects.keySet().iterator(); it.hasNext();) {
            E obj = it.next();
            objects.put(obj, new Float(FuzzyManager.round((float) Math.pow(
                    objects.get(obj).floatValue(), 2.0))));
        }
    }

    /**
     * Checks whether a object already belongs to the discrete fuzzy set.
     *
     * @param obj
     *            the object
     * @return <code>true</code> if <code>obj</code> is part of the set
     */
    public synchronized boolean contains(final E obj) {
        return (getDegreeOfMembership(obj) > 0.0f);
    }

    /** Dilates the fuzzy set. It is extracted a root.*/
    public synchronized void dilate() {
        for (Iterator<E> it = objects.keySet().iterator(); it.hasNext();) {
            E obj = it.next();
            objects.put(obj,
                    new Float(FuzzyManager.round((float) Math.pow(
                    objects.get(obj).floatValue(),
                    (1.0 / 2.0)))));
        }
    }

    /**
     * Returns all objects of the discrete fuzzy set as enumeration.
     *
     * @return an iterator of all objects with a degree of membership > 0.0
     */
    public synchronized Iterator<E> iterator() {
        return objects.keySet().iterator();
    }

    /**
     * Returns all objects which degree of membership is greater than or equal to <code>alpha</code>.
     *
     * @param alpha
     *            the limit for degree of membership in <tt>[0,1]</tt>
     * @return a collection with objects which degree of membership >= <code>alpha</code>
     * @see #getStrictAlphaCut
     */
    public synchronized Collection<E> getAlphaCut(final float alpha) {
        if ((alpha >= 0.0) && (alpha <= 1.0f)) {
            Collection<E> col = new ArrayList<E>(objects.size());

            for (Iterator<E> it = objects.keySet().iterator(); it.hasNext();) {
                E obj = it.next();

                if (objects.get(obj).floatValue() >= alpha) {
                    // Object fulfills the alpha cut
                    col.add(obj);
                }
            }

            return col;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_ALPHA",
                    new Object[]{Float.toString(alpha)}));
        }
    }

    /**
     * Returns all objects which degree of membership is greater than <code>alpha</code>.
     *
     * @param alpha
     *            the limit for degree of membership in <tt>[0,1]</tt>
     * @return a collection with objects which degree of membership > <code>alpha</code>
     * @see #getAlphaCut
     */
    public synchronized Collection<E> getStrictAlphaCut(final float alpha) {
        if ((alpha >= 0.0) && (alpha <= 1.0f)) {
            Collection<E> col = new ArrayList<E>(objects.size());

            for (Iterator<E> it = objects.keySet().iterator(); it.hasNext();) {
                E obj = it.next();

                if (objects.get(obj).floatValue() > alpha) {
                    // Object fulfills the alpha cut
                    col.add(obj);
                }
            }

            return col;
        } else {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_ALPHA",
                    new Object[]{Float.toString(alpha)}));
        }
    }

    /**
     * Returns the degree of membership of an object.
     *
     * @param obj
     *            the object
     * @return the degree ob membership of the object
     */
    public synchronized float getDegreeOfMembership(final E obj) {
        Float degreeOfMembership = 0.0F;
        if (obj != null) {
            degreeOfMembership = objects.get(obj);
            if (degreeOfMembership == null) {
                return 0.0F;
            } else {
                return degreeOfMembership;
            }
        }

        return degreeOfMembership;
    }

    /**
     * Returns the highest degree of membership over all objects in this fuzzy set. It is a value in interval <tt>[0,1]</tt>.
     *
     * @return the highest value
     */
    public synchronized float getMaximumDegreeOfMembership() {
        Float maxDegreeOfMembership = 0.0f;

        for (Iterator<E> it = objects.keySet().iterator(); it.hasNext();) {
            E obj = it.next();
            Float degreeOfMembership = objects.get(obj).floatValue();

            if (degreeOfMembership > maxDegreeOfMembership) {
                maxDegreeOfMembership = degreeOfMembership;
            }
        }

        return maxDegreeOfMembership;
    }

    /**
     * Indicates whether discrete fuzzy set contains objects.
     *
     * @return <code>true</code> if objects are member of the fuzzy set, <code>false</code> otherwise
     */
    public synchronized boolean isEmpty() {
        return objects.isEmpty();
    }

    /**
     * Adds an object, if the degree of membership is greater than 0.
     *
     * @param obj
     *            the object
     * @param dom
     *            the new degree of membership in <tt>[0,1]</tt>
     * @return the old degree of membership if <code>obj</code> is already member of the set, <code>NaN</code> otherwise
     */
    public synchronized float put(final E obj, final float dom) {
        if (obj != null) {
            if ((dom >= 0.0f) && (dom <= 1.0f)) {
                if (dom > 0.0f) {
                    Float retfloat = objects.put(obj,
                            new Float(FuzzyManager.round(dom)));

                    if (retfloat != null) {
                        return retfloat.floatValue();
                    } else {
                        return 0.0f;
                    }
                } else {
                    return remove(obj);
                }
            } else {
                throw new IllegalArgumentException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_INVALID_DEGREE_OF_MEMBERSHIP",
                        new Object[]{Float.toString(dom)}));
            }
        }
        return 0.0F;
    }

    /**
     * Assigns the objects the complementary degree of membership.
     */
    public synchronized void reciproce() {
        for (Iterator<E> it = objects.keySet().iterator(); it.hasNext();) {
            E obj = it.next();
            float newDoM = FuzzyManager.round(1.0f - objects.get(obj).floatValue());

            if (newDoM > 0.0f) {
                objects.put(obj,
                        new Float(newDoM));
            } else {
                objects.remove(obj);
            }
        }
    }

    /**
     * Deletes an object from the discrete fuzzy set.
     *
     * @param obj
     *            the object to be removed
     * @return the degree of membership before deleting
     */
    public synchronized float remove(final E obj) {
        Float degreeOfMembership = 0.0f;
        if (obj != null) {
            Float result = objects.remove(obj);
            if (result != null) {
                degreeOfMembership = result;
            }
        }
        return degreeOfMembership;
    }

    /**
     * Returns the size of this discrete fuzzy set.
     *
     * @return the number of objects which are part of the discrete fuzzy set
     */
    public synchronized int size() {
        return objects.size();
    }

    @Override
    public String toString() {
        return toString(false);
    }

    /**
     * Returns a textual representation of the discrete fuzzy set.
     * @param withObjects
     *            <code>true</code> if a textual representation of all objects is also needed
     * @return a string representation of the discrete fuzzy set
     */
    public String toString(final boolean withObjects) {
        if (withObjects) {
            return super.toString();
        } else {
            return FuzzyResourceManager.getString(this,
                    "DISCRETE_FUZZY_SET_WITHOUT_OBJECTS",
                    new Object[]{Integer.toString(objects.size())});
        }
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof DiscreteFuzzySet)) {
            return false;
        }

        // Are the objects equal?
        if (!objects.equals(((DiscreteFuzzySet) obj).objects)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = (13 * hash) + ((this.objects != null) ? this.objects.hashCode() : 0);

        return hash;
    }
}
