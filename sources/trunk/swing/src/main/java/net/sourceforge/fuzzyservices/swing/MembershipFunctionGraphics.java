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

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


/**
 *
 * @author Uwe Weng
 */
public class MembershipFunctionGraphics {
    /**
     * Holds value of property model.
     */
    private MembershipFunctionModel model;

    /**
     * Holds value of property alignmentX.
     */
    private int alignmentX = 30;

    /**
     * Holds value of property alignmentY.
     */
    private int alignmentY = 30;

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
     * Utility field used by bound properties.
     */
    private java.beans.PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    /**
     * Holds value of property maxX.
     */
    private float maxX = 1.0f;

    /**
     * Holds value of property width.
     */
    private int width;

    /**
     * Holds value of property height.
     */
    private int height;

    /**
     * Holds value of property selectedX.
     */
    private float selectedX;

    public final void paintMembershipFunction(Graphics g) {
        // Using "Double Buffering"
        //        Image offScreenImage = g.createImage(width, height);
        //        Graphics offScreenGraphics = offScreenImage.getGraphics();
        Graphics offScreenGraphics = g;

        // Clear area
        offScreenGraphics.clearRect(0, 0, width, height);

        // Draw coordinate system.
        offScreenGraphics.drawLine(alignmentX, alignmentY - 7, alignmentX,
            height - alignmentY); // y-Achse
        offScreenGraphics.drawLine(alignmentX, height - alignmentY,
            width - alignmentX + 7, height - alignmentY); // x-Achse
        offScreenGraphics.drawLine(alignmentX - 5, alignmentY - 2, alignmentX,
            alignmentY - 7); // Pfeil an y-Achse
        offScreenGraphics.drawLine(alignmentX + 5, alignmentY - 2, alignmentX,
            alignmentY - 7);
        offScreenGraphics.drawLine(alignmentX - 2, alignmentY, alignmentX + 2,
            alignmentY); // Querstrich bei 1.0
        offScreenGraphics.drawLine(width - alignmentX + 2,
            height - alignmentY - 5, width - alignmentX + 7, height -
            alignmentY); // Pfeil an x-Achse
        offScreenGraphics.drawLine(width - alignmentX + 2,
            height - alignmentY + 5, width - alignmentX + 7, height -
            alignmentY);
        offScreenGraphics.drawLine(width - alignmentX, height - alignmentY - 2,
            width - alignmentX, height - alignmentY + 2); // Querstrich bei maxX
                                                          // Description
                                                          // offScreenGraphics.setFont(getFont());

        FontMetrics fm = offScreenGraphics.getFontMetrics();
        offScreenGraphics.drawString("1.0",
            alignmentX - 10 - fm.stringWidth("1.0"),
            alignmentY + (fm.getAscent() / 2));
        offScreenGraphics.drawString("0.0",
            alignmentX - 10 - fm.stringWidth("0.0"),
            height - alignmentY + (fm.getAscent() / 2));
        offScreenGraphics.drawString((new Float(minX)).toString(),
            alignmentX - (fm.stringWidth((new Float(minX)).toString()) / 2),
            height - alignmentY + 10 + fm.getAscent());
        offScreenGraphics.drawString((new Float(maxX)).toString(),
            width - alignmentX -
            (fm.stringWidth((new Float(maxX)).toString()) / 2),
            height - alignmentY + 10 + fm.getAscent());

        // Draw polygon.
        if (model != null) {
            float[] xValues = model.getXValues();

            if ((xValues != null) && (xValues.length > 0)) {
                Point point = null;
                Point lastPoint = null;

                // Close left side
                point = scaleEntryToPoint(xValues[0],
                        model.getDegreeOfMembership(xValues[0]));
                offScreenGraphics.drawLine(point.x, point.y, point.x,
                    height - alignmentY);

                for (int i = 0; i < xValues.length; i++) {
                    point = scaleEntryToPoint(xValues[i],
                            model.getDegreeOfMembership(xValues[i]));

                    if (xValues[i] == selectedX) {
                        // Draw filled rectangle around selected Point
                        offScreenGraphics.fillRect(point.x - (pointsize / 2),
                            point.y - (pointsize / 2), pointsize, pointsize);
                    } else {
                        offScreenGraphics.drawRect(point.x - (pointsize / 2),
                            point.y - (pointsize / 2), pointsize, pointsize);
                    }

                    if ((i > 0) && (lastPoint != null)) {
                        // Punkte miteinander verbinden.
                        offScreenGraphics.drawLine(lastPoint.x, lastPoint.y,
                            point.x, point.y);
                    }

                    lastPoint = point;
                }

                if (xValues.length > 1) {
                    // Close right side
                    point = scaleEntryToPoint(xValues[xValues.length - 1],
                            model.getDegreeOfMembership(
                                xValues[xValues.length - 1]));
                    offScreenGraphics.drawLine(point.x, point.y, point.x,
                        height - alignmentY);
                }

                // Defuzzified value.
                float meanX = model.getDefuzzifiedValue();

                if (Float.isNaN(meanX) == false) {
                    Color c = g.getColor();
                    c = c.darker();
                    c = c.darker();
                    c = c.darker();
                    offScreenGraphics.setColor(c);

                    Point pTop = scaleEntryToPoint(meanX, 1.0f);
                    Point pBottom = scaleEntryToPoint(meanX, 0.0f);
                    offScreenGraphics.drawLine(pTop.x, pTop.y - 10, pBottom.x,
                        pBottom.y);
                    // Label
                    offScreenGraphics.drawString((new Float(meanX)).toString(),
                        pTop.x -
                        (fm.stringWidth((new Float(meanX)).toString()) / 2),
                        5 + fm.getAscent());
                }
            }
        }

        // Draw info box with coordinates
        if ((mousePoint != null) &&
                ((mousePoint.x >= alignmentX) &&
                (mousePoint.x <= (width - alignmentX)) &&
                (mousePoint.y >= alignmentY) &&
                (mousePoint.y <= (height - alignmentY)))) {
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
                    alignmentY)) {
                dY = dY * (-1);
                y = y - (fm.getAscent() + 10);
                dLineY = dLineY * (-1);
            }

            //            offScreenGraphics.setColor(getBackground());
            offScreenGraphics.fillRect(x + dX, y + dY,
                fm.stringWidth(text) + 10, fm.getAscent() + 10);
            //            offScreenGraphics.setColor(getForeground());
            offScreenGraphics.drawLine(mousePoint.x, mousePoint.y,
                mousePoint.x + dX, mousePoint.y + dY + dLineY);
            offScreenGraphics.drawLine(mousePoint.x, mousePoint.y,
                mousePoint.x + dX + dLineX, mousePoint.y + dY);
            offScreenGraphics.drawRect(x + dX, y + dY,
                fm.stringWidth(text) + 10, fm.getAscent() + 10);
            offScreenGraphics.drawString(text, x + dX + 5, y + dY + 15);
        }

        // Draw image on screen
        // g.drawImage(offScreenImage, 0, 0, this);
    }

    /**
     * Getter for property model.
     * @return Value of property model.
     */
    public MembershipFunctionModel getModel() {
        return this.model;
    }

    /**
     * Setter for property model.
     * @param model New value of property model.
     */
    public final void setModel(MembershipFunctionModel model) {
        MembershipFunctionModel oldModel = this.model;
        this.model = model;
        propertyChangeSupport.firePropertyChange("model", oldModel, model);
        // Adjusting minX and maxX if required
        setMinX(minX);
        setMaxX(maxX);
    }

    /**
     * Getter for property alignmentX.
     * @return Value of property alignmentX.
     */
    public int getAlignmentX() {
        return this.alignmentX;
    }

    /**
     * Setter for property alignmentX.
     * @param alignmentX New value of property alignmentX.
     */
    public final void setAlignmentX(int alignmentX) {
        float oldAlignmentX = this.alignmentX;
    }

    /**
     * Getter for property alignmentY.
     * @return Value of property alignmentY.
     */
    public int getAlignmentY() {
        return this.alignmentY;
    }

    /**
     * Setter for property alignmentY.
     * @param alignmentY New value of property alignmentY.
     */
    public final void setAlignmentY(int alignmentY) {
        this.alignmentY = alignmentY;
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
     * Getter for property mousePoint.
     * @return Value of property mousePoint.
     */
    public java.awt.Point getMousePoint() {
        return this.mousePoint;
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     * @param l The listener to add.
     */
    public final void addPropertyChangeListener(java.beans.PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    /**
     * Removes a PropertyChangeListener from the listener list.
     * @param l The listener to remove.
     */
    public final void removePropertyChangeListener(
        java.beans.PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
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

        if (model != null) {
            float[] x = model.getXValues();

            for (int i = 0; i < x.length; i++) {
                if (x[i] < minX) {
                    minX = x[i];
                }
            }

            if (maxX == minX) {
                minX = minX - 4;
            }
        }

        propertyChangeSupport.firePropertyChange("minX", new Float(oldMinX),
            new Float(minX));
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

        if (model != null) {
            float[] x = model.getXValues();

            for (int i = 0; i < x.length; i++) {
                if (x[i] > maxX) {
                    maxX = x[i];
                }
            }

            if (maxX == minX) {
                maxX = maxX + 4;
            }
        }

        propertyChangeSupport.firePropertyChange("maxX", new Float(oldMaxX),
            new Float(maxX));
    }

    /**
     * Getter for property width.
     * @return Value of property width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Setter for property width.
     * @param width New value of property width.
     */
    public final void setWidth(int width) {
        int oldWidth = this.width;
        this.width = width;
        propertyChangeSupport.firePropertyChange("width",
            new Integer(oldWidth), new Integer(width));
    }

    /**
     * Getter for property height.
     * @return Value of property height.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Setter for property height.
     * @param height New value of property height.
     */
    public final void setHeight(int height) {
        int oldHeight = this.height;
        this.height = height;
        propertyChangeSupport.firePropertyChange("height",
            new Integer(oldHeight), new Integer(height));
    }

    /**
     * Getter for property selectedX.
     * @return Value of property selectedX.
     */
    public final float getSelectedX() {
        return this.selectedX;
    }

    private static java.awt.Point scaleEntryToPoint(final float x,
        final float degreeOfMembership) {
        return null;
    }

    public final float scalePointToX(Point p) {
        float span = maxX - minX;

        return (minX +
        (span / (width - (2 * alignmentX)) * (p.x - alignmentX)));
    }

    public final float scalePointToDegreeOfMembership(Point p) {
        return (1.0f -
        (1.0f / (height - (2 * alignmentY)) * (p.y - alignmentY)));
    }

    public final boolean isEntrySelectedByMouse(Point p) {
        if (model != null) {
            float[] xValues = model.getXValues();

            if ((xValues != null) && (xValues.length > 0)) {
                for (int i = 0; i < xValues.length; i++) {
                    if (isPointInRectangle(p,
                                convertEntryToRectangle(xValues[i],
                                    model.getDegreeOfMembership(xValues[i])))) {
                        if (xValues[i] != selectedX) {
                            setSelectedX(xValues[i]);
                        }

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Rectangle convertEntryToRectangle(float x, float degreeOfMembership) {
        Point p = scaleEntryToPoint(x, degreeOfMembership);

        return new Rectangle(p.x - (pointsize / 2), p.y - (pointsize / 2),
            pointsize, pointsize);
    }

    public final boolean isPointInRectangle(Point p, Rectangle rect) {
        return ((p.x >= rect.x) && (p.y >= rect.y) &&
        (p.x <= (rect.x + rect.width)) && (p.y <= (rect.y + rect.height)));
    }

    /**
     * Setter for property selectedX.
     * @param selectedX New value of property selectedX.
     */
    public final void setSelectedX(float selectedX) {
        this.selectedX = selectedX;
    }
}
