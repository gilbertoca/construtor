/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.click.extras.orm.cayenne;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import org.apache.cayenne.BaseContext;
import org.apache.cayenne.CayenneRuntimeException;
import org.apache.cayenne.DataObject;
import org.apache.cayenne.DataObjectUtils;
import org.apache.cayenne.ObjectId;
import org.apache.cayenne.Persistent;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.map.DbAttribute;
import org.apache.cayenne.map.DbEntity;
import org.apache.cayenne.map.ObjEntity;
import org.apache.cayenne.query.NamedQuery;
import org.apache.cayenne.query.ObjectIdQuery;
import org.apache.cayenne.query.SelectQuery;
import org.apache.click.extras.orm.IService;
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
    protected DataContext getDataContext() {
        try {
            return (DataContext) BaseContext.getThreadObjectContext();

        } catch (IllegalStateException ise) {
            DataContext dataContext = DataContext.createDataContext();
            BaseContext.bindThreadObjectContext(dataContext);
            return dataContext;
        }
    }

    /**
     * Perform a query returning the data object specified by the
     * class and the primary key value. If the refresh parameter is true a
     * database query will be performed, otherwise the a query against the
     * object cache will be performed first.
     *
     * @param dataObjectClass the data object class to retrieve
     * @param id the data object primary key
     * @param refresh the refresh the object cache mode
     * @return the data object for the given class and id
     */
    protected DataObject getObjectForPK(Class dataObjectClass, Object id, boolean refresh) {
        Validate.notNull(dataObjectClass, "Null dataObjectClass parameter.");

        ObjEntity objEntity =
                getDataContext().getEntityResolver().lookupObjEntity(dataObjectClass);

        if (objEntity == null) {
            throw new CayenneRuntimeException("Unmapped DataObject Class: "
                    + dataObjectClass.getName());
        }

        String pkName = getPkName(dataObjectClass);

        ObjectId objectId = new ObjectId(objEntity.getName(), pkName, id);

        int refreshMode = (refresh) ? ObjectIdQuery.CACHE_REFRESH : ObjectIdQuery.CACHE;

        ObjectIdQuery objectIdQuery = new ObjectIdQuery(objectId, false, refreshMode);

        return (DataObject) DataObjectUtils.objectForQuery(getDataContext(), objectIdQuery);
    }

    /**
     * Return the database primary key column name for the given data object.
     *
     * @param dataObjectClass the class of the data object
     * @return the primary key column name
     */
    protected String getPkName(Class dataObjectClass) {
        Validate.notNull(dataObjectClass, "Null dataObjectClass parameter.");

        ObjEntity objEntity =
                getDataContext().getEntityResolver().lookupObjEntity(dataObjectClass);

        if (objEntity == null) {
            throw new CayenneRuntimeException("Unmapped DataObject Class: "
                    + dataObjectClass.getName());
        }

        DbEntity dbEntity = objEntity.getDbEntity();
        if (dbEntity == null) {
            throw new CayenneRuntimeException("No DbEntity for ObjEntity: "
                    + objEntity.getName());
        }

        Collection pkAttributes = dbEntity.getPrimaryKeys();
        if (pkAttributes.size() != 1) {
            throw new CayenneRuntimeException("PK contains "
                    + pkAttributes.size()
                    + " columns, expected 1.");
        }

        DbAttribute attr = (DbAttribute) pkAttributes.iterator().next();

        return attr.getName();
    }

    public Collection<T> getAll() {
        Validate.notNull(getClassEntity(), "Null ClassEntity parameter");
        SelectQuery query = new SelectQuery(getClassEntity());
        return getDataContext().performQuery(query);
    }

    public void insert(T entity) {
        Validate.notNull(entity, "Null Entity parameter");
        if (((Persistent) entity).getObjectContext() == null) {
            getDataContext().registerNewObject((DataObject) entity);
        }
        getDataContext().commitChanges();
    }

    public void insert(Collection<T> entities) {
        for (T entity : entities) {
            if (((Persistent) entity).getObjectContext() == null) {
                getDataContext().registerNewObject((DataObject) entity);
            }
        }
        getDataContext().commitChanges();
    }

    public void update(T entity) {
        Validate.notNull(entity, "Null Entity parameter");
        if (((Persistent) entity).getObjectContext() == null) {
            getDataContext().registerNewObject((DataObject) entity);
        }
        getDataContext().commitChanges();
    }

    public void update(Collection<T> entities) {
        for (T entity : entities) {
            if (((Persistent) entity).getObjectContext() == null) {
                getDataContext().registerNewObject((DataObject) entity);
            }
        }
        getDataContext().commitChanges();
    }

    public void delete(PK pk) {
        Validate.notNull(pk, "Null pk parameter");
        getDataContext().deleteObject(find(pk));
    }

    public void delete(T entity) {
        Validate.notNull(entity, "Null Entity parameter");

        getDataContext().deleteObject(entity);
        getDataContext().commitChanges();
    }

    public T find(PK pk) {
        Validate.notNull(pk, "Null PK parameter");
        Validate.notNull(getClassEntity(), "Null ClassEntity parameter");

        return (T) getObjectForPK(getClassEntity(), pk, true);
    }

    public boolean find(T entity) {
        Validate.notNull(entity, "Null Entity parameter");
        Validate.notNull(getClassEntity(), "Null ClassEntity parameter");
        return (DataObjectUtils.objectForPK(getDataContext(), (ObjectId) DataObjectUtils.pkForObject((Persistent) entity)) == null) ? true : false;
    }

    public Collection<T> findByNamedQuery(String namedQuery, Map<String, ?> params) {
        Validate.notNull(namedQuery, "Null namedQuery parameter");
        Validate.notNull(params, "Null params parameter");
        NamedQuery query = new NamedQuery(namedQuery, params);
        return (Collection<T>) getDataContext().performQuery(query);
    }

}
