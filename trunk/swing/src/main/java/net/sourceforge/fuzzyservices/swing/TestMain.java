/*
 * TestMain.java
 *
 * Created on 13. November 2007, 19:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package net.sourceforge.fuzzyservices.swing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 *
 * @author Uwe Weng
 */
public class TestMain {
    /** Creates a new instance of TestMain */
    public TestMain() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
                public final void run() {
                    JFrame f = new JFrame("Membership Function Demo");
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    f.add(new OperatorPanel());
                    f.setSize(250, 250);
                    f.setVisible(true);
                }
            });
    }
}
