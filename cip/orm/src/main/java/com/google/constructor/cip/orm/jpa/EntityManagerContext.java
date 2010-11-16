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

import javax.persistence.*;

/**
 * Provides a thread local JPA EntityManager context class. The JPA
 * configuration should be defined in class path file:
 * 
 * <pre class="codeConfig">
 *   /META-INF/persistence.xml
 * </pre>
 * 
 * Default persistence unit is "click". So persistence unit should be defined in persistence.xml:
 *
 * <pre class="codeConfig">
 *     &lt;persistence-unit name="click" ... gt;
 * </pre>
 * 
 * Alternatively, you may define persistence-unit name with
 * "click.jpa.persistenceUnit" System propertiy.
 * 
 * To support the EntityManagerContext class configure a
 * {@link EntityManagerFilter} in your web application.
 * 
 * @see EntityManagerFilter
 * @see JpaForm
 */
public class EntityManagerContext {

    /** The EntityManageractory. */
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    /** The ThreadLocal EntityManager holder. */
    private static final ThreadLocal ENTITY_MANAGER_HOLDER = new ThreadLocal();
    /** Default Persistence Unit */
    private static final String DEFAULT_PERSISTENCE_UNIT = "click";

    static {
        try {
            String persistenceUnit = System.getProperty("click.jpa.persistenceUnit");
            if (persistenceUnit == null) {
                persistenceUnit = DEFAULT_PERSISTENCE_UNIT;
            }
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(persistenceUnit);
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Get the EntityManager for the current Thread, creating one if neccessary.
     *
     * @return the EntityManager from the current Thread.
     */
    public static EntityManager getEntityManager() {
        EntityManager entityManager = (EntityManager) ENTITY_MANAGER_HOLDER.get();

        if (entityManager == null) {
            entityManager = getEntityManagerFactory().createEntityManager();
            ENTITY_MANAGER_HOLDER.set(entityManager);
        }

        return entityManager;
    }

    /**
     * Close the EntityManager held by the current Thread. The close EntityManager will also
     * be removed from the ThreadLocal variable.
     */
    public static void close() {
        EntityManager entityManager = (EntityManager) ENTITY_MANAGER_HOLDER.get();

        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }

        ENTITY_MANAGER_HOLDER.set(null);
    }

    /**
     * Return true if a EntityManager is open.
     *
     * @return true if a EntityManager is currently open.
     */
    public static boolean hasEntityManager() {
        return (ENTITY_MANAGER_HOLDER.get() != null);
    }

    /**
     * Return the EntityManagerFactory.
     *
     * @return the EntityManagerFactory
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }

    /**
     * Return the persistent instance of the given entity class with the given
     * identifier, or null if there is no such persistent instance. (If the
     * instance, or a proxy for the instance, is already associated with the
     * session, return that instance or proxy.) <p/> This method provides a
     * convenience wrapper around the corresponding <tt>EntityManager</tt> method.
     *
     * @param clazz
     *            a persistent class
     * @param id
     *            an identifier
     * @return a persistent instance or null
     */
    public static Object find(Class clazz, Serializable id) {
        return getEntityManager().find(clazz, id);
    }

    /**
     * Remove a persistent instance from the datastore. The argument may be an
     * instance associated with the receiving EntityManager or a transient instance
     * with an identifier associated with existing persistent state. This
     * operation cascades to associated instances if cascade delete is enabled.<p/>
     *  This method provides a convenience wrapper around the corresponding
     *  <tt>EntityManager</tt> method.
     *
     * @param object
     *            the instance to be removed
     */
    public static void remove(Object object) {
        getEntityManager().remove(object);
    }

    /**
     * Create a new instance of Query for the given query string. <p/> This
     * method provides a convenience wrapper around the corresponding
     * <tt>EntityManager</tt> method.
     *
     * @param queryString
     *            a Java Persistence query string
     * @return a Java Persistence query
     */
    public static Query createQuery(String queryString) {
        return getEntityManager().createQuery(queryString);
    }

    /**
     * Obtain an instance of Query for a named query string defined.<p/>
     * This method provides a convenience wrapper around the corresponding
     * <tt>EntityManager</tt> method.
     *
     * @param queryName
     *            the name of a query defined externally
     * @return a Java Persistence query
     */
    public static Query getNamedQuery(String queryName) {
        return getEntityManager().createNamedQuery(queryName);
    }

    /**
     * Rollback the Transaction.
     * This method provides a convenience wrapper around the corresponding
     * <tt>EntityManager</tt> method.
     */
    public static void rollback() {
        getEntityManager().getTransaction().rollback();
    }
}
