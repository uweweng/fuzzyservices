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

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.event.ChangeEvent;


/**
 *
 * @author Uwe Weng
 */
public class LinguisticVariablePanel extends javax.swing.JPanel
    implements LinguisticVariableModelListener {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    /** Bound property name for <code>model</code>. */
    public static final String MODEL_PROPERTY = "model";
    private static final int OFFSET_X = 30;
    private static final int OFFSET_Y = 30;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * Holds value of property model.
     */
    private LinguisticVariableModel model = new DefaultLinguisticVariableModel();

    /**
     * Holds value of property pointsize.
     */
    private int pointsize = 8;

    /**
     * Holds value of property mousePoint.
     */
    private java.awt.Point mousePoint;

    /**
     * Holds value of property minX.
     */
    private float minX = 0.0f;

    /**
     * Holds value of property mousePressed.
     */
    private boolean mousePressed = false;

    /**
     * Holds value of property mouseMoved.
     */
    private boolean mouseMoved = false;

    /**
     * Holds value of property maxX.
     */
    private float maxX = 1.0f;

    /**
     * Holds value of property selectedX.
     */
    private float selectedX;

    /** Creates new form LinguisticVariablePanel */
    public LinguisticVariablePanel() {
        super();
        initComponents();
    }

    /** Creates new form LinguisticVariablePanel
     * @param model model for this component
     */
    public LinguisticVariablePanel(LinguisticVariableModel model) {
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
        setName("Form"); // NOI18N
        addMouseListener(new java.awt.event.MouseAdapter() {
                public final void mouseClicked(java.awt.event.MouseEvent event) {
                    formMouseClicked(event);
                }

                public final void mousePressed(java.awt.event.MouseEvent event) {
                    formMousePressed(event);
                }

                public final void mouseReleased(java.awt.event.MouseEvent event) {
                    formMouseReleased(event);
                }
            });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                public final void mouseDragged(java.awt.event.MouseEvent event) {
                    formMouseDragged(event);
                }

                public final void mouseMoved(java.awt.event.MouseEvent event) {
                    formMouseMoved(event);
                }
            });
        setLayout(null);
    } // </editor-fold>//GEN-END:initComponents

    private void formMouseDragged(java.awt.event.MouseEvent event) { //GEN-FIRST:event_formMouseDragged

        Point mousePoint = event.getPoint();

        /* TODO
        if ((model != null) && (mousePressed == true))  {
            mouseMoved = true;
            int width = getWidth();
            int height = getHeight();
            if ((mousePoint.x >= OFFSET_X) && (mousePoint.y >= OFFSET_Y) && (mousePoint.x <= (width - OFFSET_X)) && (mousePoint.y <= (height - OFFSET_Y)) ) {
                setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
                // Selektierten Punkt verschieben,
                model.removePointAt(selectedX);
                model.addPoint(scalePointToX(mousePoint), scalePointToDegreeOfMembership(mousePoint));
                selectedX = scalePointToX(mousePoint);
                repaint();
            }
        }
         */
    } //GEN-LAST:event_formMouseDragged

    private void formMouseMoved(java.awt.event.MouseEvent event) { //GEN-FIRST:event_formMouseMoved
        mousePoint = event.getPoint();

        // Ueberpruefen, ob Mauszeiger auf einen Punkt zeigt.
        // Canvas wird ggf. automatisch aktualisiert.
        boolean dummy = isEntrySelectedByMouse(mousePoint);

        int width = getWidth();
        int height = getHeight();
        repaint();

        // Wenn der Mauszeiger innerhalb des Koordinatenkreuzes bewegt wird,
        // dann wird der Zeiger zum Fadenkreuz, sonst normal.
        if ((mousePoint.x >= OFFSET_X) && (mousePoint.x <= (width - OFFSET_X)) &&
                (mousePoint.y >= OFFSET_Y) &&
                (mousePoint.y <= (height - OFFSET_Y))) {
            if (dummy) {
                setCursor(java.awt.Cursor.getPredefinedCursor(
                        java.awt.Cursor.MOVE_CURSOR));
            } else {
                setCursor(java.awt.Cursor.getPredefinedCursor(
                        java.awt.Cursor.CROSSHAIR_CURSOR));
            }
        } else {
            setCursor(java.awt.Cursor.getDefaultCursor());
        }
    } //GEN-LAST:event_formMouseMoved

    private void formMousePressed(java.awt.event.MouseEvent event) { //GEN-FIRST:event_formMousePressed

        Point mousePoint = event.getPoint();

        /* TODO
        if ((model != null) && (model.isEnabled() == true)) {
            // Ueberpruefen, ob Mauszeiger auf einen Punkt zeigt.
            if ( isEntrySelectedByMouse(mousePoint) == true) {
                mousePressed = true;
            }
            repaint();
        }
         */
    } //GEN-LAST:event_formMousePressed

    private void formMouseClicked(java.awt.event.MouseEvent event) { //GEN-FIRST:event_formMouseClicked
        mousePoint = event.getPoint();

        /* TODO
        if ((model != null) && (model.isEnabled() == true)) {
            if ( isEntrySelectedByMouse(mousePoint) == true) {
                model.removePointAt(selectedX);
                selectedX = Float.NaN;
            } else {
                // Membership function is possibly extended by a new point.
                if ((mousePoint.x >= OFFSET_X) && (mousePoint.y >= OFFSET_Y) && (mousePoint.x <= (getWidth() - OFFSET_X)) && (mousePoint.y <= (getHeight() - OFFSET_Y))) {
                    float x = scalePointToX(mousePoint);
                    float dom = scalePointToDegreeOfMembership(mousePoint);
                    model.addPoint(x, dom);
                    selectedX = x;
                }
            }
            repaint();
        }
         */
    } //GEN-LAST:event_formMouseClicked

    private void formMouseReleased(java.awt.event.MouseEvent event) { //GEN-FIRST:event_formMouseReleased

        Point mousePoint = event.getPoint();
        mousePressed = false;

        /* TODO
        if ((model != null) && (model.isEnabled() == true)) {
            // If mouse is moved within coordinate system
            // the cursor changes the appearance
            int width = getWidth();
            int height = getHeight();
            if ((mousePoint.x >= OFFSET_X) && (mousePoint.x <= (width - OFFSET_X)) && (mousePoint.y >= OFFSET_Y) && (mousePoint.y <= (height - OFFSET_Y))) {
                setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.CROSSHAIR_CURSOR));
            } else {
                setCursor(java.awt.Cursor.getDefaultCursor());
            }
        } else {
            setCursor(java.awt.Cursor.getDefaultCursor());
        }
         */
    } //GEN-LAST:event_formMouseReleased

    @Override
    public final void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        // Using "Double Buffering"
        Image offScreenImage = this.createImage(width, height);
        Graphics offScreenGraphics = offScreenImage.getGraphics();
        //        Graphics offScreenGraphics = g;

        // Clear area
        offScreenGraphics.clearRect(0, 0, width, height);

        // Draw coordinate system.
        offScreenGraphics.drawLine(OFFSET_X, OFFSET_Y - 7, OFFSET_X,
            height - OFFSET_Y); // y-axis
        offScreenGraphics.drawLine(OFFSET_X, height - OFFSET_Y,
            width - OFFSET_X + 7, height - OFFSET_Y); // x-axis
        offScreenGraphics.drawLine(OFFSET_X - 5, OFFSET_Y - 2, OFFSET_X,
            OFFSET_Y - 7); // Borrow at y-axis
        offScreenGraphics.drawLine(OFFSET_X + 5, OFFSET_Y - 2, OFFSET_X,
            OFFSET_Y - 7);
        offScreenGraphics.drawLine(OFFSET_X - 2, OFFSET_Y, OFFSET_X + 2,
            OFFSET_Y); // Line at 1.0
        offScreenGraphics.drawLine(width - OFFSET_X + 2, height - OFFSET_Y - 5,
            width - OFFSET_X + 7, height - OFFSET_Y); // Pfeil an x-Achse
        offScreenGraphics.drawLine(width - OFFSET_X + 2, height - OFFSET_Y + 5,
            width - OFFSET_X + 7, height - OFFSET_Y);
        offScreenGraphics.drawLine(width - OFFSET_X, height - OFFSET_Y - 2,
            width - OFFSET_X, height - OFFSET_Y + 2); // Line at maxX
                                                      // Description
                                                      // offScreenGraphics.setFont(getFont());

        FontMetrics fm = offScreenGraphics.getFontMetrics();
        offScreenGraphics.drawString("1.0",
            OFFSET_X - 10 - fm.stringWidth("1.0"),
            OFFSET_Y + (fm.getAscent() / 2));
        offScreenGraphics.drawString("0.0",
            OFFSET_X - 10 - fm.stringWidth("0.0"),
            height - OFFSET_Y + (fm.getAscent() / 2));
        offScreenGraphics.drawString((new Float(minX)).toString(),
            OFFSET_X - (fm.stringWidth((new Float(minX)).toString()) / 2),
            height - OFFSET_Y + 10 + fm.getAscent());
        offScreenGraphics.drawString((new Float(maxX)).toString(),
            width - OFFSET_X -
            (fm.stringWidth((new Float(maxX)).toString()) / 2),
            height - OFFSET_X + 10 + fm.getAscent());

        /* TODO
                // Draw polygon.
                if (model != null) {

                    float[] xValues = model.getXValues();
                    if ((xValues != null) && (xValues.length > 0)) {

                        Point point = null;
                        Point lastPoint = null;

                        // Close left side
                        point = scaleEntryToPoint(xValues[0], model.getDegreeOfMembership(xValues[0]));
                        offScreenGraphics.drawLine(point.x, point.y, point.x, height - OFFSET_Y);

                        for (int i = 0; i < xValues.length; i++) {
                            point = scaleEntryToPoint(xValues[i], model.getDegreeOfMembership(xValues[i]));
                            if (xValues[i] == selectedX) {
                                // Draw filled rectangle around selected Point
                                offScreenGraphics.fillRect(point.x - (pointsize / 2), point.y - (pointsize / 2), pointsize, pointsize);
                            } else {
                                offScreenGraphics.drawRect(point.x - (pointsize / 2), point.y - (pointsize / 2), pointsize, pointsize);
                            }
                            if ((i > 0) && (lastPoint != null)) {
                                // Punkte miteinander verbinden.
                                offScreenGraphics.drawLine(lastPoint.x, lastPoint.y, point.x, point.y);
                            }
                            lastPoint = point;
                        }

                        if (xValues.length > 1) {
                            // Close right side
                            point = scaleEntryToPoint(xValues[xValues.length - 1], model.getDegreeOfMembership(xValues[xValues.length - 1]));
                            offScreenGraphics.drawLine(point.x, point.y, point.x, getHeight() - OFFSET_Y);
                        }
                    }
                }
                */

        // Draw info box with coordinates
        if ((mousePoint != null) &&
                ((mousePoint.x >= OFFSET_X) &&
                (mousePoint.x <= (width - OFFSET_X)) &&
                (mousePoint.y >= OFFSET_Y) &&
                (mousePoint.y <= (height - OFFSET_Y)))) {
            // distance between info box and cursor
            int dX = 5;
            int dY = 5;
            int dLineX = 4;
            int dLineY = 4;
            int x = mousePoint.x;
            int y = mousePoint.y;
            String text = "(" + scalePointToX(mousePoint) + "," +
                scalePointToDegreeOfMembership(mousePoint) + ")";

            // Die genaue Position der infobox ermitteln.
            if ((mousePoint.x + dX + fm.stringWidth(text) + 10) >= width) {
                dX = dX * (-1);
                x = x - (fm.stringWidth(text) + 10);
                dLineX = dLineX * (-1);
            }

            if ((mousePoint.y + dY + fm.getAscent() + 10) >= (height -
                    OFFSET_Y)) {
                dY = dY * (-1);
                y = y - (fm.getAscent() + 10);
                dLineY = dLineY * (-1);
            }

            offScreenGraphics.setColor(getBackground());
            offScreenGraphics.fillRect(x + dX, y + dY,
                fm.stringWidth(text) + 10, fm.getAscent() + 10);
            offScreenGraphics.setColor(getForeground());
            offScreenGraphics.drawLine(mousePoint.x, mousePoint.y,
                mousePoint.x + dX, mousePoint.y + dY + dLineY);
            offScreenGraphics.drawLine(mousePoint.x, mousePoint.y,
                mousePoint.x + dX + dLineX, mousePoint.y + dY);
            offScreenGraphics.drawRect(x + dX, y + dY,
                fm.stringWidth(text) + 10, fm.getAscent() + 10);
            offScreenGraphics.drawString(text, x + dX + 5, y + dY + 15);
        }

        // Draw image on screen
        g.drawImage(offScreenImage, 0, 0, this);
    }

    /**
     * Getter for property model.
     * @return Value of property model.
     */
    public LinguisticVariableModel getModel() {
        return this.model;
    }

    /**
     * Setter for property model.
     * @param model New value of property model.
     */
    public final void setModel(LinguisticVariableModel model) {
        LinguisticVariableModel oldModel = this.model;

        if (oldModel != null) {
            oldModel.removeLinguisticVariableModelListener(this);
        }

        this.model = model;
        firePropertyChange(MODEL_PROPERTY, oldModel, model);

        if (model != null) {
            model.addLinguisticVariableModelListener(this);
        }

        if (oldModel != model) {
            // Adjusting min/max x and repaint
            setMinX(minX);
            setMaxX(maxX);
            repaint();
        }
    }

    public final void stateChanged(ChangeEvent e) {
        Object source = e.getSource();

        if ((source != null) && (source == model)) {
            repaint();
        }
    }

    /**
     * Getter for property pointsize.
     * @return Value of property pointsize.
     */
    public int getPointsize() {
        return this.pointsize;
    }

    /**
     * Setter for property pointsize.
     * @param pointsize New value of property pointsize.
     */
    public final void setPointsize(int pointsize) {
        this.pointsize = pointsize;
    }

    /**
     * Getter for property minX.
     * @return Value of property minX.
     */
    public final float getMinX() {
        return this.minX;
    }

    /**
     * Setter for property minX.
     * @param newMinX New value of property minX.
     */
    public final void setMinX(float newMinX) {
        float oldMinX = this.minX;
        this.minX = newMinX;
        /* TODO
        if (model != null) {
            float[] x = model.getXValues();
            if (x != null) {
                for (int i = 0; i < x.length; i++) {
                    if (x[i] < minX) {
                        minX = x[i];
                    }
                }
            }
            if (maxX == minX) {
                minX = minX - 4;
            }
        }
         */
        firePropertyChange("minX", new Float(oldMinX), new Float(minX));
    }

    /**
     * Getter for property maxX.
     * @return Value of property maxX.
     */
    public final float getMaxX() {
        return this.maxX;
    }

    /**
     * Setter for property maxX.
     * @param newMaxX New value of property maxX.
     */
    public final void setMaxX(float newMaxX) {
        float oldMaxX = this.maxX;
        this.maxX = newMaxX;
        /* TODO
                if (model != null) {
                    float[] x = model.getXValues();
                    if (x != null) {
                        for (int i = 0; i < x.length; i++) {
                            if (x[i] > maxX) {
                                maxX = x[i];
                            }
                        }
                    }
                    if (maxX == minX) {
                        maxX = maxX + 4;
                    }
                }
         */
        firePropertyChange("maxX", new Float(oldMaxX), new Float(maxX));
    }

    private java.awt.Point scaleEntryToPoint(final float x,
        final float degreeOfMembership) {
        float span = maxX - minX;

        return new Point((int) (OFFSET_X +
            ((x - minX) / span * (getWidth() - (2 * (OFFSET_X))))),
            (int) (OFFSET_Y +
            ((1.0f - degreeOfMembership) * (getHeight() - (2 * (OFFSET_X))))));
    }

    private float scalePointToX(Point p) {
        float span = maxX - minX;

        return (minX +
        (span / (getWidth() - (2 * (OFFSET_X))) * (p.x - OFFSET_X)));
    }

    private float scalePointToDegreeOfMembership(Point p) {
        return (1.0f -
        (1.0f / (getHeight() - (2 * (OFFSET_Y))) * (p.y - OFFSET_Y)));
    }

    private boolean isEntrySelectedByMouse(Point p) {
        /* TODO
        if (model != null) {
            float[] xValues = model.getXValues();
            if ((xValues != null) && (xValues.length > 0)) {
                for (int i = 0; i < xValues.length; i++) {
                    if ( isPointInRectangle(p, convertEntryToRectangle(xValues[i], model.getDegreeOfMembership(xValues[i]))) ) {
                        if ( xValues[i] != selectedX ) {
                            selectedX = xValues[i];
                        }
                        return true;
                    }
                }
            }
        }
         */
        return false;
    }

    /**
     * Vergroessert einen Punkt von einer Zugehoerigkeitsfunktion, nachdem er auf Bildschirmkoordinaten umgerechnet worden ist.
     * @param entry ein Punkt von einer Zugehoerigkeitsfunktion
     * @return ein Rechteck auf dem Bildschirm, dessen Mittelpunkt den Koordinaten des Punktes <code>entry</code> entspricht
     * @see #pointsize
     */
    private Rectangle convertEntryToRectangle(float x, float degreeOfMembership) {
        Point p = scaleEntryToPoint(x, degreeOfMembership);

        return new Rectangle(p.x - (pointsize / 2), p.y - (pointsize / 2),
            pointsize, pointsize);
    }

    /**
     * Ueberprueft, ob die Maus in ein Rechteck zeigt, welches einem Punkt aus einer Zugehoerigkeitsfunktion entspricht.
     * @return <code>true</code>, falls der Mauszeiger in einem Rechteck zeigt, welches einem Punkt von einer Zugehoerigkeitsfunktion entspricht, sonst <code>false</code>
     */
    private boolean isPointInRectangle(Point p, Rectangle rect) {
        return ((p.x >= rect.x) && (p.y >= rect.y) &&
        (p.x <= (rect.x + rect.width)) && (p.y <= (rect.y + rect.height)));
    }

    @Override
    public final void linguisticVariableChanged(LinguisticVariableModelEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
