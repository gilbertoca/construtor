/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import java.io.Serializable;
import java.util.Collection;

import net.sourceforge.orbroker.Broker;


/**
 * 
 * @author gilberto
 */
public interface Service<T, PK extends Serializable> {
    public Broker getBroker();
    public Class<T> getClassEntity();
    public Collection<T> getAll();
    /**
     * Busca por chave primária.
     * @param entityClass
     * @param primaryKey
     * @return a instância da entidade encontrada ou null se a entidade não existe
     * @throws IllegalArgumentException se o primeiro argumento não denota um tipo entidade 
     * ou o segundo argumento é nulo
     */
    public T find(PK primaryKey);
    public void insert(T entity);
    public void update(T entity);
    public void delete(T entity);

    public Collection<T> findByNamedQuery(String queryName);
}