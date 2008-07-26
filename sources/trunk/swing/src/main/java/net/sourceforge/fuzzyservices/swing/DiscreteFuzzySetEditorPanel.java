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
package net.sourceforge.fuzzyservices.swing;

import org.jdesktop.application.Action;


/**
 *
 * @author Uwe Weng
 */
public class DiscreteFuzzySetEditorPanel extends javax.swing.JPanel {
    // End of variables declaration//GEN-END:variables
    /** Bound property name for <code>model</code>. */
    public static final String MODEL_PROPERTY = "model";

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton addButton;
    javax.swing.JPanel buttonPanel;
    javax.swing.JButton clearButton;
    net.sourceforge.fuzzyservices.swing.DiscreteFuzzySetTable discreteFuzzySetTable;
    javax.swing.JButton editButton;
    javax.swing.JButton removeButton;
    javax.swing.JScrollPane tableScrollPane;
    private DiscreteFuzzySetModel model = new DefaultDiscreteFuzzySetModel();

    /** Creates new form DiscreteFuzzySetEditorPanel */
    public DiscreteFuzzySetEditorPanel() {
        super();
        initComponents();
    }

    /** Creates new form DiscreteFuzzySetEditorPanel
     * @param model model for this component
     */
    public DiscreteFuzzySetEditorPanel(DiscreteFuzzySetModel model) {
        this();
        setModel(model);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        tableScrollPane = new javax.swing.JScrollPane();
        discreteFuzzySetTable = new net.sourceforge.fuzzyservices.swing.DiscreteFuzzySetTable();
        buttonPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        setName("Form"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        tableScrollPane.setName("tableScrollPane"); // NOI18N

        discreteFuzzySetTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                    { null, null, null, null },
                    { null, null, null, null },
                    { null, null, null, null },
                    { null, null, null, null }
                }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
        discreteFuzzySetTable.setName("discreteFuzzySetTable"); // NOI18N
        tableScrollPane.setViewportView(discreteFuzzySetTable);

        add(tableScrollPane, java.awt.BorderLayout.CENTER);

        buttonPanel.setName("buttonPanel"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(mydesktopapplication.MyDesktopApplication.class)
                                                                              .getContext()
                                                                              .getActionMap(DiscreteFuzzySetEditorPanel.class,
                this);
        addButton.setAction(actionMap.get("addObject")); // NOI18N
        addButton.setName("addButton"); // NOI18N

        editButton.setAction(actionMap.get("editObject")); // NOI18N
        editButton.setName("editButton"); // NOI18N

        removeButton.setAction(actionMap.get("removeObject")); // NOI18N
        removeButton.setName("removeButton"); // NOI18N

        clearButton.setAction(actionMap.get("clearDiscreteFuzzySet")); // NOI18N
        clearButton.setName("clearButton"); // NOI18N

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(buttonPanelLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
                                                              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                buttonPanelLayout.createSequentialGroup().addContainerGap()
                                 .addGroup(buttonPanelLayout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(editButton,
                        javax.swing.GroupLayout.Alignment.LEADING,
                        javax.swing.GroupLayout.PREFERRED_SIZE, 71,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(addButton,
                        javax.swing.GroupLayout.Alignment.LEADING,
                        javax.swing.GroupLayout.PREFERRED_SIZE, 71,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(clearButton,
                        javax.swing.GroupLayout.Alignment.LEADING,
                        javax.swing.GroupLayout.PREFERRED_SIZE, 71,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(removeButton,
                        javax.swing.GroupLayout.Alignment.LEADING,
                        javax.swing.GroupLayout.PREFERRED_SIZE, 71,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                 .addGap(17, 17, 17)));
        buttonPanelLayout.setVerticalGroup(buttonPanelLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(buttonPanelLayout.createSequentialGroup()
                                                                                       .addComponent(addButton,
                    javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                       .addComponent(editButton,
                    javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                       .addComponent(removeButton,
                    javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                       .addComponent(clearButton,
                    javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                    javax.swing.GroupLayout.PREFERRED_SIZE).addGap(190, 190, 190)));

        add(buttonPanel, java.awt.BorderLayout.LINE_END);
    } // </editor-fold>//GEN-END:initComponents

    @Action
    public final void clearDiscreteFuzzySet() {
    }

    @Action
    public final void removeObject() {
    }

    @Action
    public final void editObject() {
    }

    @Action
    public final void addObject() {
    }

    /**
     * Defines a new model for this component.
     * @param model The new value for the property.
     * @see #getModel
     */
    public final void setModel(DiscreteFuzzySetModel model) {
        DiscreteFuzzySetModel oldModel = this.model;
        this.model = model;
        firePropertyChange(MODEL_PROPERTY, oldModel, model);
    }

    /**
     * Returns the model of this component.
     * @return the <code>model</code> property
     * @see #setModel
     */
    public DiscreteFuzzySetModel getModel() {
        return model;
    }
}
