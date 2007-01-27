/*******************************************************************************
 *
 *  Copyright (C) 2007  Uwe Weng
 *
 *  This file is part of JFuzzy, a library for processing fuzzy information.
 *
 *  JFuzzy is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  JFuzzy is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with JFuzzy; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  The license is also available at http://www.gnu.org/licenses/gpl.txt
 *
 ******************************************************************************/
package net.sourceforge.fuzzyservices.beans;

import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;


/**
 * This class represents an then-clause (consequent) of a rule according to
 * JavaBeans conventions.
 *
 * @see com.jfuzzy.core.Consequent
 *
 * @since 1.0
 * @author Uwe Weng
 */
public class ConsequentBean implements VetoableChangeListener, Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    //
    // Bound property names
    //

    /** Bound property name for <code>linguisticVariable</code>. */
    public static final String LINGUISTIC_VARIABLE_PROPERTY
            = "linguisticVariable";

    /** Bound property name for <code>linguisticTerm</code>. */
    public static final String LINGUISTIC_TERM_PROPERTY = "linguisticTerm";

    /** The linguistic variable of this consequent. */
    private LinguisticVariableBean linguisticVariable = null;

    /** The linguistic term which is part of the linguistic variable. */
    private LinguisticTermBean linguisticTerm = null;

    /** Support for any PropertyChangeListeners which have been registered. */
    private transient PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);

    /** Default <code>ConsequentBean</code> constructor. */
    public ConsequentBean() {
        // Do nothing
    }

    /**
     * Returns the linguistic term of this antecedent.
     * @return the <code>linguisticTerm</code> property
     * @see #setLinguisticTerm
     */
    public LinguisticTermBean getLinguisticTerm() {
        return linguisticTerm;
    }

    /**
     * Returns the linguistic variable of this antecedent.
     * @return the <code>linguisticVariable</code> property
     * @see #setLinguisticVariable
     */
    public LinguisticVariableBean getLinguisticVariable() {
        return linguisticVariable;
    }

    /**
     * Sets the linguistic term of this consequent.
     * @param lingTerm The new value for the property.
     * @exception NullPointerException if <code>linguisticVariable</code> is
     * not defined yet
     * @exception IllegalArgumentException if <code>linguisticTerm</code> is
     * not part of current linguistic variable property
     * @see #getLinguisticTerm
     */
    public void setLinguisticTerm(LinguisticTermBean lingTerm)
    throws NullPointerException, IllegalArgumentException {
        if ((lingTerm != null) &&
                (this.linguisticVariable.contains(lingTerm) == false)) {
            throw new IllegalArgumentException(FuzzyResourceManager.getString(
                    this, "EXCEPTION_UNKNOWN_LINGUISTIC_TERM", new Object[] {
                this.linguisticVariable.getName(), lingTerm.getName()
            }));
        }

        LinguisticTermBean oldValue = this.linguisticTerm;
        this.linguisticTerm = lingTerm;
        propertyChangeSupport.firePropertyChange(LINGUISTIC_TERM_PROPERTY,
                oldValue, lingTerm);
    }

    /**
     * Sets the linguistic variable of this consequent. Additionally, the
     * <code>linguisticTerm</code> property is
     * set to <code>null</code>.
     * @param lingVariable The new value for the property.
     * @see #getLinguisticVariable
     */
    public void setLinguisticVariable(LinguisticVariableBean lingVariable) {
        LinguisticVariableBean oldValue = this.linguisticVariable;
        this.linguisticVariable = lingVariable;
        propertyChangeSupport.firePropertyChange(LINGUISTIC_VARIABLE_PROPERTY,
                oldValue, lingVariable);
        // Delete linguistic Term
        setLinguisticTerm(null);
    }

    /**
     * This method gets called when a constrained property is changed.
     * @param     evt a <code>PropertyChangeEvent</code> object describing the
     * event source and the property that has changed.
     * @exception java.beans.PropertyVetoException if the recipient wishes the
     * property change to be rolled back.
     */
    public void vetoableChange(PropertyChangeEvent evt)
    throws PropertyVetoException {
        if ((evt.getSource() == this.linguisticVariable) &&
                (this.linguisticTerm != null)) {
            // Is lingusitic term already defined?
            boolean isDefined = false;
            Object newValue = evt.getNewValue();
            LinguisticTermBean[] newTerms = (LinguisticTermBean[]) newValue;

            if (newTerms != null) {
                for (int i = 0; i < newTerms.length; i++) {
                    if ((newTerms[i] != null) &&
                            (newTerms[i].equals(this.linguisticTerm) == true)) {
                        isDefined = true;

                        break;
                    }
                }
            }

            if (isDefined == false) {
                throw new PropertyVetoException(FuzzyResourceManager.getString(
                        this, "EXCEPTION_UNKNOWN_LINGUISTIC_TERM", new Object[] {
                    ((LinguisticVariableBean)evt.getSource()).getName(),
                    this.linguisticTerm.getName() }), evt);
            }
        }
    }

    /**
     * Adds a <code>PropertyChangeListener</code> to the listener list. The listener is registered for all properties. <p>
     * A <code>PropertyChangeEvent</code> will get fired in response to setting a bound property. <p>
     * @param listener  the <code>PropertyChangeListener</code> to be added
     */
    public synchronized void addPropertyChangeListener(final PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a <code>PropertyChangeListener</code> from the listener list.
     * This removes a <code>PropertyChangeListener</code> that was registered for all properties.
     * @param listener  the <code>PropertyChangeListener</code> to be removed
     */
    public synchronized void removePropertyChangeListener(final PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}


