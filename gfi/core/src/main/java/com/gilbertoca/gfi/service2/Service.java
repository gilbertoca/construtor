/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service2;

import java.io.Serializable;
import java.util.Collection;

import net.sourceforge.orbroker.Broker;
import net.sourceforge.orbroker.Query;
import net.sourceforge.orbroker.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gilbertoca.gfi.inventario2.model.Categoria;

/**
 * 
 * @author gilberto
 */
abstract public class Service<T, PK extends Serializable> {

    protected transient final Logger log = LoggerFactory.getLogger(getClass());

    abstract public Broker getBroker();

    protected Collection<T> findAll(Class classEntity){
    	log.debug("Realizando consulta retornando todos os registros, para a entidade: {} ",classEntity);
        Query qry = getBroker().startQuery();
        if (classEntity == null) {
            throw new IllegalArgumentException("ClassEntity não pode ser nulo!");
        }
        String sentenca = "get" + classEntity.getClass().getSimpleName();
        
        try {
            log.debug("Verificando parametros ..." +
                    "\nNome senteça SQL: {}", sentenca);
        	
            return findByNamedQuery(sentenca);
        } finally {
            qry.close();
        }    	
    }

    protected T findByPk(Class classEntity, PK pk){
    	log.debug("Realizando consulta por entidade. Identificador usado como parâmetro: {} ",pk);
        Query qry = getBroker().startQuery();
        if (classEntity == null) {
            throw new IllegalArgumentException("ClassEntity não pode ser nulo!");
        }

    	if (pk == null || pk.equals("")) {
            throw new IllegalArgumentException(
                    "Identificador não pode ser nulo!");
        }
        
        String parametro = classEntity.getClass().getSimpleName().substring(0, 1).toLowerCase() + classEntity.getClass().getSimpleName().substring(1);
        String sentenca = "get" + classEntity.getClass().getSimpleName();
        qry.setParameter(parametro, classEntity);
        try {
            log.debug("Verificando parametros ..." +
                    "\nQuery parameter: {}" +
                    "\nStament name   : {}" +
                    "\nArgumento: {}\n", new Object[] {parametro, sentenca, classEntity});
            return qry.selectOne(sentenca, classEntity);
        } finally {
            qry.close();
        }
    }

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

    protected void insert(T entity){
    	log.debug("Realizando inserção. Entidade usada como parâmetro: {} ",entity);    	
        Transaction txn = getBroker().startTransaction();
        if (entity == null) {
            throw new IllegalArgumentException("Entidade não pode ser nulo!");
        }
        String parametro = entity.getClass().getSimpleName().substring(0, 1).toLowerCase() + entity.getClass().getSimpleName().substring(1);
        String sentenca = "insert" + entity.getClass().getSimpleName();
        int recordsUpdated = 0;
        try {
            log.debug("Verificando parametros ..." +
                    "\nParâmetro: {}" +
                    "\nNome senteça SQL: {}", parametro, sentenca);
        	
            txn.setParameter(parametro, entity);
            recordsUpdated = txn.execute(sentenca);
            if (recordsUpdated != 1) {
                txn.rollback();
                log.debug("Problemas na inserção. Nº registros afetados: {} ",recordsUpdated);
            //throw new ThatsWeirdException();
            }
            txn.commit();
            log.debug("Inserção realizada com sucesso.");
        } finally {
            txn.close();
        }
    }

    protected void update(T entity){
    	log.debug("Realizando alteração. Entidade usada como parâmetro: {} ",entity);
        Transaction txn = getBroker().startTransaction();
        if (entity == null) {
            throw new IllegalArgumentException("Entidade não pode ser nulo!");
        }
        String parametro = entity.getClass().getSimpleName().substring(0, 1).toLowerCase() + entity.getClass().getSimpleName().substring(1);
        String sentenca = "update" + entity.getClass().getSimpleName();
        
        int recordsUpdated = 0;
        try {
            log.debug("Verificando parametros ..." +
                    "\nParâmetro: {}" +
                    "\nNome senteça SQL: {}", parametro, sentenca);
        	
            txn.setParameter(parametro, entity);
            recordsUpdated = txn.execute(sentenca);
            if (recordsUpdated != 1) {
                txn.rollback();
                log.debug("Problemas na alteração. Nº registros afetados: {} ",recordsUpdated);
            //throw new ThatsWeirdException();
            }
            txn.commit();
            log.debug("Alteração realizada com sucesso.");
        } finally {
            txn.close();
        }
    }

    protected void delete(T entity){
        log.debug("Realizando deleção. Entidade usada como parâmetro: {} ",entity);
        Transaction txn = getBroker().startTransaction();
        if (entity == null) {
            throw new IllegalArgumentException("Entidade não pode ser nulo!");
        }
        String parametro = entity.getClass().getSimpleName().substring(0, 1).toLowerCase() + entity.getClass().getSimpleName().substring(1);
        String sentenca = "delete" + entity.getClass().getSimpleName();
        
        int recordsUpdated = 0;
        try {
            log.debug("Verificando parametros ..." +
                    "\nParâmetro: {}" +
                    "\nNome senteça SQL: {}", parametro, sentenca);
        	
            txn.setParameter(parametro, entity);
            recordsUpdated = txn.execute("deleteCategoria");
            if (recordsUpdated != 1) {
                txn.rollback();
            	log.debug("Problemas na deleção. Nº registros afetados: {} ",recordsUpdated);                
            //throw new ThatsWeirdException();
            }else{
            	txn.commit();
            	log.debug("Deleção realizada com sucesso.");
            }
        } finally {
            txn.close();
        }
    }

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
