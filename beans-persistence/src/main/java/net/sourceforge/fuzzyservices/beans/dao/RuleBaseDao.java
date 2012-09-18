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
package net.sourceforge.fuzzyservices.beans.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import net.sourceforge.fuzzyservices.beans.RuleBase;

/**
 * This class defines Data Access Object layer for rule bases.
 *
 * @version 1.0
 * @author Uwe Weng
 */
public class RuleBaseDao extends AbstractDaoImpl<RuleBase, Integer> {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    public RuleBaseDao() {
        super(RuleBase.class);
    }

    public RuleBaseDao(EntityManagerFactory emf) {
        super(RuleBase.class, emf);
    }

    /**
     * Retrieves a rule base associated with a specific name.
     * 
     * @param name Identifier that matches a specific row in the database to find and return.
     * @return The rule base that has the name field which equals name or null if no matches.
     */
    public RuleBase findByName(final String name) {
        EntityManager em = emf.createEntityManager();
        RuleBase data = null;
        try {
            Query query = em.createNamedQuery("findRuleBaseByName");
            query.setParameter("name", name);
            data = (RuleBase) query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
        return data;
    }
}
