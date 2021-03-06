/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.google.constructor.cip.orm.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.google.constructor.cip.orm.IService;
import org.apache.commons.lang.Validate;

public class BaseJPAService<T, PK extends Serializable> implements IService<T, PK> {

    /** The EntityManager. */
    protected EntityManager entityManager;
    private Class<T> classEntity;

    public BaseJPAService(Class<T> classEntity) {
        super();
        Validate.notNull(classEntity, "Null ClassEntity parameter");
        this.classEntity = classEntity;
    }

    public BaseJPAService(Class<T> classEntity, boolean autoCommit) {
        super();
        Validate.notNull(classEntity, "Null ClassEntity parameter");
        this.classEntity = classEntity;
    }

    public Class<T> getClassEntity() {
        return classEntity;
    }

    /**
     * Return the <tt>EntityManager</tt>. If the EntityManager is not
     * defined this method will obtain a EntityManager from the
     * {@link EntityManagerContext}.
     * <p/>
     * Applications using alternative <tt>EntityManager</tt> sources should
     * set the user's session using the {@link #setEntityManager(EntityManager)} method.
     *
     * @return the form EntityManager
     */
    public EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = EntityManagerContext.getEntityManager();
        }
        return entityManager;
    }

    /**
     * Set the user's <tt>EntityManager</tt>.
     *
     * @param entityManager the user's EntityManager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(T entity) {
        Validate.notNull(entity, "Null Entity parameter");
        EntityTransaction tx = getEntityManager().getTransaction();
        tx.begin();
        getEntityManager().persist(entity);
        tx.commit();
    }

    public void insert(Collection<T> entities) {
        EntityTransaction tx = getEntityManager().getTransaction();
        tx.begin();
        for (T entity : entities) {
            getEntityManager().persist(entity);
        }
        tx.commit();
    }

    public void update(T entity) {
        update(entity, false);
    }

    public void update(T entity, boolean refresh) {
        Validate.notNull(entity, "Null Entity parameter");
        EntityTransaction tx = getEntityManager().getTransaction();
        tx.begin();
        getEntityManager().merge(entity);
        tx.commit();
    }

    public void update(Collection<T> entities) {
        EntityTransaction tx = getEntityManager().getTransaction();
        tx.begin();
        for (T entity : entities) {
            getEntityManager().merge(entity);
        }
        tx.commit();
    }

    public void delete(PK pk) {
        Validate.notNull(pk, "Null pk parameter");
        EntityTransaction tx = getEntityManager().getTransaction();
        tx.begin();
        getEntityManager().remove(find(pk));
        tx.commit();
    }

    public void delete(T entity) {
        Validate.notNull(entity, "Null Entity parameter");
        EntityTransaction tx = getEntityManager().getTransaction();
        tx.begin();
        getEntityManager().remove(entity);
        tx.commit();
    }

    public void delete(Collection<T> entities) {
        EntityTransaction tx = getEntityManager().getTransaction();
        tx.begin();
        for (T entity : entities) {
            getEntityManager().remove(entity);
        }
        tx.commit();
    }

    public List<T> getAll() {
        Validate.notNull(getClassEntity(), "Null ClassEntity parameter");
        CriteriaBuilder cB = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cQ = cB.createQuery(getClassEntity());
        Root<T> t = cQ.from(getClassEntity());
        return getEntityManager().createQuery(cQ).getResultList();

    }

    public T find(PK pk) {
        Validate.notNull(pk, "Null PK parameter");
        Validate.notNull(getClassEntity(), "Null ClassEntity parameter");
        T entity = (T) getEntityManager().find(getClassEntity(), pk);
        if (entity == null) {
            //log.warn("Uh oh, '" + this.classEntity + "' object with id '" + pk + "' not found...");
            //throw new EntityNotFoundException("entity: "+getClassEntity()+"primary key: "+pk);
        }
        return entity;
    }

    public List findByQuery(String queryString) {
        return findByQuery(queryString, (Object[]) null);
    }

    public List findByQuery(String queryString, Object... values) {
        Validate.notNull(queryString, "Null queryString parameter");
        Query queryObject = getEntityManager().createNamedQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i + 1, values[i]);
            }
        }
        return queryObject.getResultList();
    }

    public List findByNamedQuery(String namedQuery) {
        return findByNamedQuery(namedQuery, (Map) null);
    }

    public List findByNamedQuery(String namedQuery, Map<String, ?> params) {
        Validate.notNull(namedQuery, "Null namedQuery parameter");
        Query queryObject = getEntityManager().createNamedQuery(namedQuery);
        if (params != null) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                queryObject.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return queryObject.getResultList();
    }
}
