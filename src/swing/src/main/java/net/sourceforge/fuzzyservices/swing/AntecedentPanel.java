/*
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
public class AntecedentPanel extends javax.swing.JPanel {
    // End of variables declaration//GEN-END:variables
    /** Bound property name for <code>model</code>. */
    public static final String MODEL_PROPERTY = "model";

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton addButton;
    net.sourceforge.fuzzyservices.swing.AntecedentTable antecedentTable;
    javax.swing.JPanel buttonPanel;
    javax.swing.JButton removeButton;
    javax.swing.JScrollPane tableScrollPane;
    private RuleModel model = new DefaultRuleModel();

    /** Creates new form AntecedentPanel */
    public AntecedentPanel() {
        super();
        initComponents();
    }

    /** Creates new form AntecedentPanel
     * @param model model for this component
     */
    public AntecedentPanel(RuleModel model) {
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
        antecedentTable = new net.sourceforge.fuzzyservices.swing.AntecedentTable();
        buttonPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(mydesktopapplication.MyDesktopApplication.class)
                                                                                               .getContext()
                                                                                               .getResourceMap(AntecedentPanel.class);
        setBorder(javax.swing.BorderFactory.createTitledBorder(
                resourceMap.getString("Form.border.title"))); // NOI18N
        setName("Form"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        tableScrollPane.setName("tableScrollPane"); // NOI18N

        antecedentTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                    { null, null, null, null },
                    { null, null, null, null },
                    { null, null, null, null },
                    { null, null, null, null }
                }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
        antecedentTable.setName("antecedentTable"); // NOI18N
        tableScrollPane.setViewportView(antecedentTable);

        add(tableScrollPane, java.awt.BorderLayout.CENTER);

        buttonPanel.setName("buttonPanel"); // NOI18N
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(mydesktopapplication.MyDesktopApplication.class)
                                                                              .getContext()
                                                                              .getActionMap(AntecedentPanel.class,
                this);
        addButton.setAction(actionMap.get("addAntecedent")); // NOI18N
        addButton.setName("addButton"); // NOI18N
        buttonPanel.add(addButton);

        removeButton.setAction(actionMap.get("removeAntecedent")); // NOI18N
        removeButton.setName("removeButton"); // NOI18N
        buttonPanel.add(removeButton);

        add(buttonPanel, java.awt.BorderLayout.PAGE_END);
    } // </editor-fold>//GEN-END:initComponents

    @Action
    public final void addAntecedent() {
    }

    @Action
    public final void removeAntecedent() {
    }

    /**
     * Defines a new model for this component.
     * @param model The new value for the property.
     * @see #getModel
     */
    public final void setModel(RuleModel model) {
        RuleModel oldModel = this.model;
        this.model = model;
        firePropertyChange(MODEL_PROPERTY, oldModel, model);
    }

    /**
     * Returns the model of this component.
     * @return the <code>model</code> property
     * @see #setModel
     */
    public RuleModel getModel() {
        return model;
    }
}
