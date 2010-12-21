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
package com.google.constructor.cip.orm;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * General interface for use with the persistence mechanism.
 * Establishes essential methods signature for database transactions.
 *
 */
public interface IService<T, PK extends Serializable> {

    void insert(T entity);
    void insert(Collection<T> entities);
    void update(T entity);
    void update(T entity, boolean refresh);
    void update(Collection<T> entities);
    void delete(PK pk);
    void delete(T entity);
    void delete(Collection<T> entities);
    /**
     * Generic method used to retrieve entities of a particular class.
     * This particular class is defined in the constructor of the implementation of this interface.
     *
     * @return Collection The
     */
    Collection<T> getAll();

    /**
     * Find by primary key. Search for an entity of the specified class and primary key.
     * If the entity instance is contained in the persistence context, it is returned from there.
     * @param primaryKey  primary key
     * @return the found entity instance or null if the entity does not exist.
     */
    T find(PK pk);
    /**
     * Execute a query for persistent instances.
     * @param queryString a query expressed in JPA's query language
     * @return List the List of persistent instances
     */
    List findByQuery(final String queryString);
    /**
     * Execute a query for persistent instances.
     * @param queryString a query expressed in JPA's query language
     * @param values  the values of the positional parameters
     * @return List the List of persistent instances
     */
    List findByQuery(final String queryString, final Object... values);
    /**
     * Find by named query.
     * @param namedQuery Named query: annotated or in xml file
     * @param params Map with key and value matching named parameters and theirs values.
     * @return List
     */
    List findByNamedQuery(final String namedQuery);

    /**
     * Find by query using named parameters.
     * @param <T>
     * @param namedQuery Named query: annotated or in xml file
     * @param params Map with key and value matching named parameters and theirs values.
     * @return Collection<T>
     */
    List findByNamedQuery(final String namedQuery, final Map<String, ?> params);
}