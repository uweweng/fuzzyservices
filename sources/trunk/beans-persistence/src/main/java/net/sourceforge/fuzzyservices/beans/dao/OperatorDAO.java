/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.fuzzyservices.beans.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.sourceforge.fuzzyservices.beans.Operator;

/**
 * Data Access Object for persisting operator beans.
 * 
 * @since 1.0
 * @author Uwe Weng
 */
public final class OperatorDAO {

    /**
     * Static reference to the Entity Manager Factory for the identity type/table.
     */
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("net.sourceforge.fuzzyservices.test");

    /**
     * Creates a new operator in database.
     * @param operator new operator
     */
    public void persistOperator(final Operator operator) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(operator);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Returns operator identified by <code>id</code>.
     * @param id the (technical) identifier
     * @return selected operator
     */
    public Operator getOperator(final Integer id) {
        Operator operator = null;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            operator = em.find(Operator.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return operator;
    }
}
