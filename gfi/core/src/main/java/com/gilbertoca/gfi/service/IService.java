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
public interface IService<T, PK extends Serializable> {
    public Broker getBroker();
    public Class<T> getClassEntity();
    public Collection<T> getAll();
    public boolean find(T entity);
    public void insert(T entity);
    public void update(T entity);
    public void delete(T entity);
    public Collection<T> findByNamedQuery(String queryName);
}