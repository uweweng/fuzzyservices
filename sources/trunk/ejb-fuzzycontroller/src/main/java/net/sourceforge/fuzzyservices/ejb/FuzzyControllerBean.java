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
package net.sourceforge.fuzzyservices.ejb;


import java.rmi.RemoteException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * EJB implementation of a fuzzy controller for approximate reasoning.
 * It supports both core objects and JavaBeans.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class FuzzyControllerBean implements SessionBean {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Session context for this bean instance.
     */
    private SessionContext context;

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">;
    /**
     * @param aContext session context for this bean instance
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    @Override
    public final void setSessionContext(SessionContext aContext) {
        context = aContext;
    }

    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    @Override
    public final void ejbActivate() {
    }

    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    @Override
    public final void ejbPassivate() {
    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    @Override
    public final void ejbRemove() {
    }

    // </editor-fold>;
    /**
     * Inference method for fuzzy JavaBeans.
     * @return a new fact base with the result of this operation
     * @throws RemoteException when any technical issue happened
     * @param ruleBase the rule base
     * @param factBase the fact base
     * @param linguisticVariables the linguistic variables
     */
    public net.sourceforge.fuzzyservices.beans.FactBase performApproximateReasoning(
            final net.sourceforge.fuzzyservices.beans.RuleBase ruleBase, final net.sourceforge.fuzzyservices.beans.FactBase factBase,
            final net.sourceforge.fuzzyservices.beans.LinguisticVariable[] linguisticVariables) {
        return new net.sourceforge.fuzzyservices.beans.FuzzyController().performApproximateReasoning(ruleBase,
                factBase, linguisticVariables);
    }

    /**
     * Inference method for core fuzzy components.
     * @return a new fact base with the result of this operation
     * @throws RemoteException when any technical issue happened
     * @param ruleBase the rule base
     * @param factBase the fact base
     * @param linguisticVariables the linguistic variables
     */
    public net.sourceforge.fuzzyservices.core.FactBase performApproximateReasoning(final net.sourceforge.fuzzyservices.core.RuleBase ruleBase,
            final net.sourceforge.fuzzyservices.core.FactBase factBase, final net.sourceforge.fuzzyservices.core.LinguisticVariable[] linguisticVariables) {
        return net.sourceforge.fuzzyservices.core.impl.FuzzyControllerImpl.getInstance().performApproximateReasoning(ruleBase,
                factBase, linguisticVariables);
    }
}
