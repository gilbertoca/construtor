/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import java.io.Serializable;
import java.util.Collection;

import net.sourceforge.orbroker.Broker;
import net.sourceforge.orbroker.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author gilberto
 */
abstract public class Service<T, PK extends Serializable> {

    protected transient final Logger log = LoggerFactory.getLogger(getClass());

    abstract public Broker getBroker();

    abstract public Collection<T> findAll();

    abstract public T findByPk(PK pk);

    protected boolean findByPk(T entity) {
    	log.debug("Realizando consulta por entidade. Identificador usado como parâmetro: {} ",entity);
        Query qry = getBroker().startQuery();
        if (entity == null) {
            throw new IllegalArgumentException("Entidade não pode ser nulo!");
        }
        String parametro = entity.getClass().getSimpleName().substring(0, 1).toLowerCase() + entity.getClass().getSimpleName().substring(1);
        String sentenca = "get" + entity.getClass().getSimpleName();
        qry.setParameter(parametro, entity);
        try {
            log.debug("Verificando parametros ..." +
                    "\nQuery parameter: {}" +
                    "\nStament name   : {}" +
                    "\nArgumento: {}\n", new Object[] {parametro, sentenca, entity});
            return qry.selectOne(sentenca, entity);
        } finally {
            qry.close();
        }
    }

    abstract public void insert(T entity);

    abstract public void update(T entity);

    abstract public void deleteAll();

    abstract public void deleteByPk(PK pk);

    protected Collection<T> findByNamedQuery(String queryName){
        Query qry = getBroker().startQuery();
        try {
            return qry.selectMany(queryName);
        } finally {
            qry.close();
        }
    }
    protected Broker getBroker(String brokerName) {
        return ResourceLocator.getInstance().getBroker(brokerName);
    }
    protected Broker getBroker(String brokerName, String schema) {
        return ResourceLocator.getInstance().getBroker(brokerName, schema);
    }

    abstract public Collection<T> findLike(T entity);
}
