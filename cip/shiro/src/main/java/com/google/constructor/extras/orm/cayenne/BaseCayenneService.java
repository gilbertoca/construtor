/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.constructor.extras.orm.cayenne;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import org.apache.cayenne.BaseContext;
import org.apache.cayenne.DataObjectUtils;
import org.apache.cayenne.ObjectId;
import org.apache.cayenne.Persistent;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.query.NamedQuery;
import org.apache.cayenne.query.SelectQuery;
import com.google.constructor.extras.orm.IService;
import org.apache.commons.lang.Validate;

/**
 *
 * @author Gilberto
 */
public class BaseCayenneService<T, PK extends Serializable> implements IService<T, PK> {

    private Class<T> classEntity;

    public BaseCayenneService(Class<T> classEntity) {
        super();
        Validate.notNull(classEntity, "Null ClassEntity parameter");
        this.classEntity = classEntity;
    }

    public Class<T> getClassEntity() {
        return classEntity;
    }

    /**
     * Return the thread local DataContext. If a DataContext not not bound to
     * the current thread, this method will create a new DataContext and bind
     * it to the thread.
     *
     * @return the thread local DataContext
     */
    public DataContext getDataContext() {
        try {
            return (DataContext) BaseContext.getThreadObjectContext();

        } catch (IllegalStateException ise) {
            DataContext dataContext = DataContext.createDataContext();
            BaseContext.bindThreadObjectContext(dataContext);
            return dataContext;
        }
    }

    public Collection<T> getAll() {
        Validate.notNull(getClassEntity(), "Null ClassEntity parameter");
        SelectQuery query = new SelectQuery(getClassEntity());
        return getDataContext().performQuery(query);
    }

    public void insert(T entity) {
        Validate.notNull(entity, "Null Entity parameter");
        if (((Persistent) entity).getObjectContext() == null) {
            getDataContext().registerNewObject(entity);
        }
        getDataContext().commitChanges();
    }

    public void insert(Collection<T> entities) {
        for (T entity : entities) {
            if (((Persistent) entity).getObjectContext() == null) {
                getDataContext().registerNewObject(entity);
            }
        }
        getDataContext().commitChanges();
    }

    public void update(T entity) {
        Validate.notNull(entity, "Null Entity parameter");
        if (((Persistent) entity).getObjectContext() == null) {
            getDataContext().registerNewObject(entity);
        }
        getDataContext().commitChanges();
    }

    public void update(Collection<T> entities) {
        for (T entity : entities) {
            if (((Persistent) entity).getObjectContext() == null) {
                getDataContext().registerNewObject(entity);
            }
        }
        getDataContext().commitChanges();
    }

    public void delete(PK pk) {
        Validate.notNull(pk, "Null pk parameter");
        getDataContext().deleteObject(find(pk));
        getDataContext().commitChanges();
    }

    public void delete(T entity) {
        Validate.notNull(entity, "Null Entity parameter");

        getDataContext().deleteObject(entity);
        getDataContext().commitChanges();
    }

    public T find(PK pk) {
        Validate.notNull(pk, "Null PK parameter");
        Validate.notNull(getClassEntity(), "Null ClassEntity parameter");

        return (T) DataObjectUtils.objectForPK(getDataContext(), getClassEntity(), pk);
    }

    public Collection<T> findByNamedQuery(String namedQuery, Map<String, ?> params) {
        Validate.notNull(namedQuery, "Null namedQuery parameter");
        Validate.notNull(params, "Null params parameter");
        NamedQuery query = new NamedQuery(namedQuery, params);
        return (Collection<T>) getDataContext().performQuery(query);
    }

}
