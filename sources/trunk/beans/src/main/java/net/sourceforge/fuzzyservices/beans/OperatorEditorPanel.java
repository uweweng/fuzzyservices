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
package net.sourceforge.fuzzyservices.beans;

import net.sourceforge.fuzzyservices.swing.OperatorModel;

/**
 * Panel for editing operator property.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class OperatorEditorPanel extends javax.swing.JPanel {

    /** Creates new form OperatorEditorPanel */
    public OperatorEditorPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        operatorPanel = new net.sourceforge.fuzzyservices.swing.OperatorPanel();

        setLayout(new java.awt.BorderLayout());

        operatorPanel.setName("operatorPanel"); // NOI18N
        add(operatorPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    net.sourceforge.fuzzyservices.swing.OperatorPanel operatorPanel;
    // End of variables declaration//GEN-END:variables
    protected OperatorModel operatorModel;
    public static final String OPERATOR_MODEL_PROPERTY = "operatorModel";

    /**
     * Get the value of operatorModel
     *
     * @return the value of operatorModel
     */
    public OperatorModel getOperatorModel() {
        return operatorModel;
    }

    /**
     * Set the value of operatorModel
     *
     * @param operatorModel new value of operatorModel
     */
    public void setOperatorModel(OperatorModel operatorModel) {
        OperatorModel oldOperatorModel = operatorModel;
        this.operatorModel = operatorModel;
        firePropertyChange(OPERATOR_MODEL_PROPERTY, oldOperatorModel, operatorModel);
        operatorPanel.setModel(operatorModel);
    }

    /**
     * Constructs a panel that is initialized with <code>operatorModel</code> as the model.
     * @param operatorModel the model for the panel
     */
    public OperatorEditorPanel(OperatorModel operatorModel) {
        this();
        setOperatorModel(operatorModel);
    }

}
