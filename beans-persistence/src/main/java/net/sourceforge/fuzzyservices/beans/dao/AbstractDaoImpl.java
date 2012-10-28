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

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Abstract class implementing the interface <code>DaoI<code/>.
 *
 * @see DaoI
 * 
 * @version 1.0
 * @author Uwe Weng
 */
public class AbstractDaoImpl<T, ID extends Serializable> implements DaoI<T, ID> {

    /**
     * Static reference to the Entity Manager Factory for the identity type/table.
     */
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("net.sourceforge.fuzzyservices");
    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Class of data objects.
     */
    protected final Class<T> dataClass;

    /**
     * Constructor, defining the type of all data objects
     * 
     * @param dataClass the type of all data objects
     */
    public AbstractDaoImpl(Class<T> dataClass) {
        this.dataClass = dataClass;
    }

    /**
     * Constructor, defining the type of all data objects 
     * and overwrite the default entity manager factory for fuzzy services.
     * 
     * @param dataClass the type of all data objects
     * @param emf the new entity manager factory
     */
    public AbstractDaoImpl(Class<T> dataClass, EntityManagerFactory emf) {
        this.dataClass = dataClass;
        AbstractDaoImpl.emf = emf;
    }

    @Override
    public T findById(final ID id) {
        T data = null;
        EntityManager em = emf.createEntityManager();
        try {
            data = em.find(dataClass, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
        return data;
    }

    @Override
    public List<T> findAll() {
        EntityManager em = emf.createEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(dataClass));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public void create(T data) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(data);
            em.getTransaction().commit();
        } catch (Exception e) {
            //em.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public T update(T data) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            T updatedData = em.merge(data);
            em.getTransaction().commit();
            return updatedData;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(T data) throws EntityNotFoundException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            T mergedData = em.merge(data);
            em.remove(mergedData);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public void removeById(final ID id) throws EntityNotFoundException {
        T data = findById(id);
        if (data != null) {
            remove(data);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public int size() {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(dataClass)));
            return em.createQuery(criteriaQuery).getSingleResult().intValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public Iterable<T> iterate(int offset, int max) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        cq.select(cq.from(dataClass));
        Root<T> c = cq.from(dataClass);
        cq.select(c);
        cq.orderBy(cb.asc(c.get("id")));
        return em.createQuery(cq).setFirstResult(offset).setMaxResults(max).getResultList();
    }
}

