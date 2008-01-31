/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import java.io.Serializable;
import java.util.Collection;
import net.sourceforge.orbroker.Broker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author gilberto
 */
abstract public class Service<T, PK extends Serializable> {
    protected transient final Log log = LogFactory.getLog(getClass());
    abstract public Broker getBroker();
    protected Broker getBroker(String brokerName) {
        return ResourceLocator.getInstance().getBroker(brokerName);
    }
    abstract public Collection<T> findAll();
    abstract public T findByPk(PK pk);
    abstract public void insert(T entity);
    abstract public void update(T entity);
    abstract public void deleteAll();
    abstract public void deleteByPk(PK pk);
    abstract public Collection<T> findByNamedQuery(String queryName);
}
