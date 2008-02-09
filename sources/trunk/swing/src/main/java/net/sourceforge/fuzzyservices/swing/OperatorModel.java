/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.fuzzyservices.swing;

import java.util.List;

/**
 *
 * @author Administrator
 */
public interface OperatorModel {

    String[] getOperatorNames();
    String getSelectedOperatorName();
    void setSelectedOperatorName(String name);
    float getParameter();
    void setParameter(float parameter);
    
    void addOperatorModelListener(OperatorModelListener l);

    void removeOperatorModelListener(OperatorModelListener l);
    
}
