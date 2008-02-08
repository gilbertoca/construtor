/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import java.io.Serializable;
import java.util.Collection;

import net.sourceforge.orbroker.Broker;
import net.sourceforge.orbroker.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gilbertoca.gfi.inventario2.model.UnidadeMedida;

/**
 * 
 * @author gilberto
 */
abstract public class Service<T, PK extends Serializable> {
	protected transient final Log log = LogFactory.getLog(getClass());

	abstract public Broker getBroker();

	abstract public Collection<T> findAll();

	abstract public T findByPk(PK pk);

	protected boolean findByPk(T entity) {
		Query qry = getBroker().startQuery();
		if (entity == null) {
			throw new IllegalArgumentException("Entidade não pode ser nulo!");
		}
		String parametro = entity.getClass().getSimpleName().substring(0, 1).toLowerCase()+entity.getClass().getSimpleName().substring(1);
		String sentenca = "get" + entity.getClass().getSimpleName()	+ "ByPk";
		qry.setParameter(parametro,	entity);
		try {
			log.info("findByPk(T) --> Verificando parametros:"+
					"\nQuery parameter: "+parametro+
					"\nStament name   :"+sentenca+
					"\n--> Verificando argumentos:\n"+entity);
			return qry.selectOne(sentenca, entity);
		} finally {
			qry.close();
		}
	}

	abstract public void insert(T entity);

	abstract public void update(T entity);

	abstract public void deleteAll();

	abstract public void deleteByPk(PK pk);

	abstract public Collection<T> findByNamedQuery(String queryName);

	protected Broker getBroker(String brokerName) {
		return ResourceLocator.getInstance().getBroker(brokerName);
	}

	abstract public Collection<T> findLike(T entity);
}
