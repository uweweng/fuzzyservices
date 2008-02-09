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

import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;
import net.sourceforge.fuzzyservices.core.AbstractOperator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


/**
 * Instances of <code>DiscreteFuzzySet</code> represent a fuzzy set with discrete objects
 * of one type. The membership to this set is specified by a number between 0.0 and 1.0.
 * It is the so-called degree of membership.
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class DiscreteFuzzySet implements java.lang.Cloneable,
    java.io.Serializable {
    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Set of objects (as key) with its degree of membership as value
     */
    protected Hashtable objects = new Hashtable();

    /**
     * Only objects of this type are allowed.
     */
    protected Class objecttype;

    /**
     * Constructs a discrete fuzzy set of type <code>anObjecttype</code>.
     *
     * @param anObjecttype
     *            class as type definition for objects which belong to the discrete fuzzy set
     * @exception NullPointerException
     *                if object type is <code>null</code>
     */
    public DiscreteFuzzySet(Class anObjecttype) throws NullPointerException{
        if (anObjecttype != null)
            this.objecttype = anObjecttype;
        else
            throw new NullPointerException();
    }

    /**
     * Constructs a discrete fuzzy set with <code>obj</code> as first object,
     * which is member of the set. Further objects has to be of the same type.
     *
     * @param obj
     *            the object
     * @param dom
     *            the degree of membership of <code>obj</code>
     * @exception NullPointerException
     *                if obj is <code>null</code>
     * @exception IllegalArgumentException
     *                if <code>dom</code> is not in <tt>[0,1]</tt>
     */
    public DiscreteFuzzySet(Object obj, float dom) throws NullPointerException, IllegalArgumentException{
        this.objecttype = obj.getClass();

        if ((dom >= 0.0f) && (dom <= 1.0f)) {
            objects.put(obj, new Float(FuzzyManager.round(dom)));
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_DEGREE_OF_MEMBERSHIP",
                    new Object[] { Float.toString(dom) }));
    }

    /**
     * Adds an object, if the degree of membership is greater than 0.
     *
     * @param obj
     *            the object
     * @param dom
     *            the degree of membership of <code>obj</code>
     * @exception NullPointerException
     *                if obj is <code>null</code>
     * @exception IllegalArgumentException
     *                if <code>dom</code> is not in <tt>[0,1]</tt>
     */
    public synchronized void add(Object obj, float dom) throws NullPointerException,IllegalArgumentException{
        if (objecttype.isInstance(obj)) {
            if ((dom >= 0.0f) && (dom <= 1.0f)) {
                if (dom > 0.0f) {
                    objects.put(obj, new Float(FuzzyManager.round(dom)));
                }
            } else
                throw new IllegalArgumentException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_INVALID_DEGREE_OF_MEMBERSHIP",
                        new Object[] { Float.toString(dom) }));
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_DISCRETE_FUZZY_SET_TYPE",
                    new Object[] { obj.getClass().toString() }));
    }

    /** Deletes all objects from the fuzzy set. */
    public synchronized void clear() {
        objects.clear();
    }

    /**
     * Creates and returns a copy of this discrete fuzzy set.
     *
     * @return a copy of this discrete fuzzy set
     */
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
     * Combines two discrete fuzzy sets to a new discrete fuzzy set.
     *
     * @param dfs1 The first operand
     * @param dfs2 The second operand
     * @param op the operator for combining
     * @return the result of this operation. It is a new discrete fuzzy set.
     * @exception NullPointerException if <code>op</code> is <code>null</code>
     * @see AbstractOperator#combine
     */
    public static DiscreteFuzzySet combine(DiscreteFuzzySet dfs1,
        DiscreteFuzzySet dfs2, AbstractOperator op) throws NullPointerException{
        return op.combine(dfs1, dfs2);
    }

    /**
     * Combines this instance with a second discrete fuzzy sets to a new discrete fuzzy set.
     *
     * @param dfs The second operand
     * @param op the operator for combining
     * @return the result of this operation. It is a new discrete fuzzy set.
     * @exception NullPointerException if <code>op</code> is <code>null</code>
     * @see AbstractOperator#combine
     */
    public synchronized DiscreteFuzzySet combine(DiscreteFuzzySet dfs,
        AbstractOperator op) throws NullPointerException{
        return op.combine(this, dfs);
    }

    /** Concentrates the discrete fuzzy set. That means, degree of memberships  are squared.*/
    public synchronized void concentrate() {
        Enumeration e = objects.keys();

        while (e.hasMoreElements()) {
            Object key = e.nextElement();
            objects.put(key,
                new Float(FuzzyManager.round(
                        (float) Math.pow(
                            ((Float) objects.get(key)).floatValue(), 2.0))));
        }
    }

    /**
     * Checks whether a object already belongs to the discrete fuzzy set.
     *
     * @param obj
     *            the object
     * @return <code>true</code> if <code>obj</code> is part of the set
     */
    public synchronized boolean contains(Object obj) {
        return (getDegreeOfMembership(obj) > 0.0f);
    }

    /** Dilates the fuzzy set. It is extracted a root.*/
    public synchronized void dilate() {
        Enumeration e = objects.keys();

        while (e.hasMoreElements()) {
            Object key = e.nextElement();
            objects.put(key,
                new Float(FuzzyManager.round(
                        (float) Math.pow(
                            ((Float) objects.get(key)).floatValue(), (1.0 / 2.0)))));
        }
    }

    /**
     * Returns all objects of the discrete fuzzy set as enumeration.
     *
     * @return an enumeration of all objects with a degree of membership > 0.0
     */
    public synchronized Enumeration elements() {
        return objects.keys();
    }

    /**
     * Returns all objects which degree of membership is greater than or equal to <code>alpha</code>.
     *
     * @param alpha
     *            the limit for degree of membership
     * @return an array with objects which degree of membership >= <code>alpha</code>
     * @exception IllegalArgumentException
     *                if <code>alpha</code> is not in interval <tt>[0,1]</tt>
     * @see #getStrictAlphaCut
     */
    public synchronized Object[] getAlphaCut(float alpha) throws IllegalArgumentException{
        if ((alpha >= 0.0) && (alpha <= 1.0f)) {
            Object key;
            Vector vector = new Vector(objects.size());
            Enumeration e = objects.keys();

            while (e.hasMoreElements()) {
                key = e.nextElement();

                if (((Float) objects.get(key)).floatValue() >= alpha)
                    // Object fulfills the alpha cut
                    vector.addElement(key);
            }

            // Copying all identified objects into an array
            int size = vector.size();

            if (size > 0) {
                Object[] retarray = new Object[size];
                vector.copyInto(retarray);

                return retarray;
            }

            return null;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_ALPHA",
                    new Object[] { Float.toString(alpha) }));
    }

    /**
     * Returns all objects which degree of membership is greater than <code>alpha</code>.
     *
     * @param alpha
     *            the limit for degree of membership
     * @return an array with objects which degree of membership > <code>alpha</code>
     * @exception IllegalArgumentException
     *                if <code>alpha</code> is not in interval <tt>[0,1]</tt>
     * @see #getAlphaCut
     */
    public synchronized Object[] getStrictAlphaCut(float alpha) throws IllegalArgumentException{
        if ((alpha >= 0.0) && (alpha <= 1.0f)) {
            Object key;
            Vector vector = new Vector(objects.size());
            Enumeration e = objects.keys();

            while (e.hasMoreElements()) {
                key = e.nextElement();

                if (((Float) objects.get(key)).floatValue() > alpha)
                    // Object fulfills the alpha cut
                    vector.addElement(key);
            }

            // Copying all identified objects into an array
            int size = vector.size();

            if (size > 0) {
                Object[] retarray = new Object[size];
                vector.copyInto(retarray);

                return retarray;
            }

            return null;
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_ALPHA",
                    new Object[] { Float.toString(alpha) }));
    }

    /**
     * Returns the type of all objects belonging to this discrete fuzzy set.
     *
     * @return the object type
     */
    public synchronized Class getClassOfObjects() {
        return objecttype;
    }

    /**
     * Returns the degree of membership of an object.
     *
     * @param obj
     *            the object
     * @return the degree ob membership of the object
     * @exception IllegalArgumentException
     *                if <code>obj</code> is not of the same type as all other objects of this fuzzy set
     */
    public synchronized float getDegreeOfMembership(Object obj) throws IllegalArgumentException{
        if (obj != null) {
            if (objecttype.isInstance(obj)) {
                Float DoM = (Float) objects.get(obj);

                if (DoM == null)
                    return 0.0f;
                else

                    return DoM.floatValue();
            } else
                throw new IllegalArgumentException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_INVALID_DISCRETE_FUZZY_SET_TYPE",
                        new Object[] { obj.getClass().toString() }));
        }

        return 0.0f;
    }

    /**
     * Returns the highest degree of membership over all objects in this fuzzy set. It is a value in interval <tt>[0,1]</tt>.
     *
     * @return the highest value
     */
    public synchronized float getMaximumDegreeOfMembership() {
        float maxDoM = 0.0f;
        Enumeration e = objects.keys();

        while (e.hasMoreElements()) {
            Object key = e.nextElement();

            if (((Float) objects.get(key)).floatValue() > maxDoM)
                maxDoM = ((Float) objects.get(key)).floatValue();
        }

        return maxDoM;
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
     *            the new degree of membership
     * @return the old degree of membership if <code>obj</code> is already member of the set, <code>NaN</code> otherwise
     * @exception NullPointerException
     *                if <code>obj</code> is <code>null</code>
     * @exception IllegalArgumentException
     *                if <code>obj</code> is not of the same type as all other objects of this fuzzy set, or
     *                if <code>dom</code> is not in interval <tt>[0,1]</tt>
     */
    public synchronized float put(Object obj, float dom) throws NullPointerException, IllegalArgumentException {
        if (objecttype.isInstance(obj)) {
            if ((dom >= 0.0f) && (dom <= 1.0f)) {
                if (dom > 0.0f) {
                    Object retfloat = objects.put(obj,
                            new Float(FuzzyManager.round(dom)));

                    if (retfloat != null)
                        return ((Float) retfloat).floatValue();
                    else

                        return 0.0f;
                } else

                    return remove(obj);
            } else
                throw new IllegalArgumentException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_INVALID_DEGREE_OF_MEMBERSHIP",
                        new Object[] { Float.toString(dom) }));
        } else
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_INVALID_DISCRETE_FUZZY_SET_TYPE",
                    new Object[] { obj.getClass().toString() }));
    }

    /**
     * Assigns the objects the complementary degree of membership.
     */
    public synchronized void reciproce() {
        Enumeration e = objects.keys();

        while (e.hasMoreElements()) {
            Object key = e.nextElement();
            float newDoM = FuzzyManager.round(1.0f -
                    ((Float) objects.get(key)).floatValue());

            if (newDoM > 0.0f)
                objects.put(key, new Float(newDoM));
            else
                objects.remove(key);
        }
    }

    /**
     * Deletes an object from the discrete fuzzy set.
     *
     * @param obj
     *            the object to be removed
     * @return the degree of membership before deleting
     * @exception IllegalArgumentException
     *                if <code>obj</code> is not of the same type as all other objects of this fuzzy set
     */
    public synchronized float remove(Object obj) throws IllegalArgumentException {
        if (obj != null) {
            if (objecttype.isInstance(obj)) {
                Object retfloat = objects.remove(obj);

                if (retfloat != null)
                    return ((Float) retfloat).floatValue();
                else

                    return 0.0f;
            } else
                throw new IllegalArgumentException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_INVALID_DISCRETE_FUZZY_SET_TYPE",
                        new Object[] { obj.getClass().toString() }));
        } else

            return Float.NaN;
    }

    /**
     * Returns the size of this discrete fuzzy set.
     *
     * @return the number of objects which are part of the discrete fuzzy set
     */
    public synchronized int size() {
        return objects.size();
    }

    /**
     * Returns a textual representation of the discrete fuzzy set
     * @return a string representation of the discrete fuzzy set
     */
    public String toString() {
        return toString(false);
    }

    /**
     * Returns a textual representation of the discrete fuzzy set
     * @param withObjects
     *            <code>true</code> if a textual representation of all objects is also needed
     * @return a string representation of the discrete fuzzy set
     */
    public String toString(final boolean withObjects) {
        if (withObjects)
            return super.toString();
        else

            return FuzzyResourceManager.getString(this, "DISCRETE_FUZZY_SET_WITHOUT_OBJECTS",
                new Object[] { Integer.toString(objects.size()) });
    }

    /**
     * Indicates whether some other object is "equal to" this discrete fuzzy set.
     * @param obj the reference object with which to compare
     * @return <code>true</code> if this discrete fuzzy set is the same as the <code>obj</code> argument, <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof DiscreteFuzzySet)) {
            // Are the objects equal?
            if (!objects.equals(((DiscreteFuzzySet) obj).objects)) {
                Enumeration elements = ((DiscreteFuzzySet) obj).objects.keys();

                // Are the degree of memberships equal?
                while (elements.hasMoreElements()) {
                    Object key = elements.nextElement();

                    if (getDegreeOfMembership(key) != ((DiscreteFuzzySet) obj).getDegreeOfMembership(
                                key))
                        return false;
                }

                elements = objects.keys();

                while (elements.hasMoreElements()) {
                    Object key = elements.nextElement();

                    if (getDegreeOfMembership(key) != ((DiscreteFuzzySet) obj).getDegreeOfMembership(
                                key))
                        return false;
                }
            }

            return true;
        }

        return false;
    }
}
