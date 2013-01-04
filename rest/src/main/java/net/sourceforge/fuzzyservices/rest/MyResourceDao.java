package net.sourceforge.fuzzyservices.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import net.sourceforge.fuzzyservices.beans.dao.DaoI;

/**
 * Dummy implementation for testing.
 * 
 * @author Uwe Weng
 */
public class MyResourceDao implements DaoI<MyResourceBean, Integer> {

    private static HashMap<Integer, MyResourceBean> datastore = new HashMap<>();

    @Override
    public MyResourceBean findById(Integer id) {
        return datastore.get(id);
    }

    @Override
    public List<MyResourceBean> findAll() {
        return new ArrayList<>(datastore.values());
    }

    @Override
    public void create(MyResourceBean data) throws EntityExistsException {
        MyResourceBean bean = datastore.get(data.getId());
        if (bean != null) {
            throw new EntityExistsException();
        }
        int id = (int) System.currentTimeMillis();
        data.setId(id);
        datastore.put(data.getId(), data);
    }

    @Override
    public MyResourceBean update(MyResourceBean data) throws EntityNotFoundException {
        MyResourceBean bean = datastore.get(data.getId());
        if (bean == null) {
            throw new EntityNotFoundException();
        }
        bean = datastore.put(data.getId(), data);
        return bean;
    }

    @Override
    public void remove(MyResourceBean data) throws EntityNotFoundException {
        MyResourceBean bean = datastore.get(data.getId());
        if (bean == null) {
            throw new EntityNotFoundException();
        }
        datastore.remove(data.getId());
    }

    @Override
    public void removeById(Integer id) throws EntityNotFoundException {
        MyResourceBean bean = datastore.get(id);
        if (bean == null) {
            throw new EntityNotFoundException();
        }
        datastore.remove(id);
    }

    @Override
    public Iterable<MyResourceBean> iterate(int offset, int max) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
