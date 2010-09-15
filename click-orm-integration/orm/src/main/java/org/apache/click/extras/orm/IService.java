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
package org.apache.click.extras.orm;

import java.io.Serializable;
import java.util.Collection;
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
    void update(Collection<T> entities);
    void delete(PK pk);
    /**
     * Generic method used to retrieve entities of a particular class.
     * This particular class is defined in the constructor of the implementation of this interface.
     *
     * @return Collection The
     */
    Collection<T> getAll();

    /**
     * Here we use the find by primary key method as well, but using the
     * EntityManagerFactory().getPersistenceUnitUtil().getIdentifier(Entity) method
     * to discovery the entity's id, searching for an entity of the specified class and primary key.
     * If the entity instance is contained in the persistence context, it is returned from there.
     * @param entity  the entity
     * @return true if found entity instance or false if the entity does not exist.
     */
    boolean find(T entity);

    /**
     * Find by primary key. Search for an entity of the specified class and primary key.
     * If the entity instance is contained in the persistence context, it is returned from there.
     * @param primaryKey  primary key
     * @return the found entity instance or null if the entity does not exist.
     */
    T find(PK pk);

    /**
     * Find by query using named parameters.
     * @param <T>
     * @param namedQuery Named query: annotated or in xml file
     * @param params Map with key and value matching named parameters and theirs values.
     * @return Collection<T>
     */
    Collection<T> findByNamedQuery(final String namedQuery, final Map<String, ?> params);
}