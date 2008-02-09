/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.fuzzyservices.swing;

import java.util.Collection;

/**
 *
 * @author Administrator
 */
public interface FactBaseModel {

    String getName();

    void setName(String name);

    Collection<FactModel> getFacts();

    void addFactBaseModelListener(FactBaseModelListener l);

    void removeFactBaseModelListener(FactBaseModelListener l);
}
