/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.fuzzyservices.swing;

/**
 *
 * @author Administrator
 */
public interface DiscreteFuzzySetModel {

    float getDegreeOfMembershipOf(Object object);

    Object getObjectAt(int rowIndex);

    void setDegreeOfMembershipAt(float degreeOfMembership, int rowIndex);

    void setObjectAt(Object aValue, int rowIndex);

    /**
     * Returns the number of objects in fuzzy set.
     * 
     * @return	the size of discrete fuzzy set.
     */
    int size();

    void addDiscreteFuzzySetModelListener(DiscreteFuzzySetModelListener l);

    void removeDiscreteFuzzySetModelListener(DiscreteFuzzySetModelListener l);
}
