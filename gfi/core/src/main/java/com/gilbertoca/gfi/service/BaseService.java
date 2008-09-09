/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import java.io.Serializable;
import java.util.Collection;

import net.sourceforge.orbroker.Broker;
import net.sourceforge.orbroker.Query;
import net.sourceforge.orbroker.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Esta classe é uma implementação padrão da interface IService. Fornece uma implementação básica 
 * para todos os métodos dessa interface. O prerequisito para uso dessa classe é a existência de uma
 * Unidade de Persistência (gerenciada pelo mecanismo OrBroker) e a definição nessa mesma unidade, das
 * entidades a serem usadas nas operações implementadas.
 * <p/>
 * Como demonstração, suponha a existência de uma entidade <tt>UnidadeMedida</tt> já definida  
 * <p/>
 * <pre class="codeJava">
 *	public class BaseServiceTest {
 *
 *	private BaseService&lt;UnidadeMedida, String&gt; uS = new BaseService&lt;UnidadeMedida, String&gt;(UnidadeMedida.class);
 *
 *	public void testInsertUnidadeMedida() {
 *	int size = uS.getAll().size();		
 *	UnidadeMedida entity = new UnidadeMedida("MT3", "Metros");
 *	uS.insert(entity);
 *	Collection&lt;UnidadeMedida&gt; result = uS.getAll();
 *	System.out.println(result);
 *	assertTrue(result.size() > size);
 *	}
 *	   ..
 *	} </pre>
 * <p>
 * A necessidade em passar um tipo Class da prórpria entidade, está relacionada a uma carateristica dos genéricos em java: erasure.
 * Essa caraterística está bem descrita nesse blog: http://blog.caelum.com.br/2006/10/29/brincando-com-generics-o-bizarregenericdao/
 * </p> 
 * @author Gilberto Caetano de Andrade
 */
public class BaseService<T, PK extends Serializable> implements IService<T, PK>{
	protected String brokerName;
	private Class<T> classEntity;
    protected transient final Logger log = LoggerFactory.getLogger(getClass());

    public BaseService(Class<T> classEntity) {
		super();
		this.classEntity = classEntity;
	}
    public BaseService(Class<T> classEntity, String brokerName) {
		super();
		this.classEntity = classEntity;
		this.brokerName = brokerName;
	}

	public Class<T> getClassEntity() {return classEntity;}

	public Collection<T> getAll(){
    	log.debug("Realizando consulta retornando todos os registros, para a entidade: {} ",getClassEntity());

        if (getClassEntity() == null) {
            throw new IllegalArgumentException("ClassEntity não pode ser nulo!");
        }
        Query qry = getBroker().startQuery();        
        String sentenca = "get" + getClassEntity().getSimpleName();
        
        try {
            log.debug("Verificando parametros ..." +
                    "\nNome senteça SQL: {}", sentenca);
        	
            return findByNamedQuery(sentenca);
        } finally {
            qry.close();
        }    	
    }


    public boolean find(T entity) {
    	log.debug("Realizando consulta por entidade. Identificador usado como parâmetro: {} ",entity);

        if (entity == null) {
            throw new IllegalArgumentException("Entidade não pode ser nulo!");
        }
        Query qry = getBroker().startQuery();        
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

    public void insert(T entity) throws InsertException{
    	log.debug("Realizando inserção. Entidade usada como parâmetro: {} ",entity);    	

        if (entity == null) {
            throw new IllegalArgumentException("Entidade não pode ser nulo!");
        }
        Transaction txn = getBroker().startTransaction();        
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
                throw new InsertException("Incapaz de realizar inserção!");
            }
            txn.commit();
            log.debug("Inserção realizada com sucesso.");
        } finally {
            txn.close();
        }
    }

    public void update(T entity){
    	log.debug("Realizando alteração. Entidade usada como parâmetro: {} ",entity);

        if (entity == null) {
            throw new IllegalArgumentException("Entidade não pode ser nulo!");
        }
        Transaction txn = getBroker().startTransaction();        
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

    public void delete(T entity){
        log.debug("Realizando deleção. Entidade usada como parâmetro: {} ",entity);

        if (entity == null) {
            throw new IllegalArgumentException("Entidade não pode ser nulo!");
        }
        Transaction txn = getBroker().startTransaction();        
        String parametro = entity.getClass().getSimpleName().substring(0, 1).toLowerCase() + entity.getClass().getSimpleName().substring(1);
        String sentenca = "delete" + entity.getClass().getSimpleName();
        
        int recordsUpdated = 0;
        try {
            log.debug("Verificando parametros ..." +
                    "\nParâmetro: {}" +
                    "\nNome senteça SQL: {}", parametro, sentenca);
        	
            txn.setParameter(parametro, entity);
            recordsUpdated = txn.execute(sentenca);
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

	public Collection<T> findLike(String likeColumn, String likeValue) {
    	log.debug("Realizando consulta retornando registros, para entidade {} estejam no filtro: columa {} e valor {} ", new Object[] {getClassEntity(), likeColumn, likeValue});

        if (getClassEntity() == null) {
            throw new IllegalArgumentException("ClassEntity não pode ser nulo!");
        }
    	if (likeColumn == null || likeColumn.equals("")) {
            throw new IllegalArgumentException(
                    "likeColumn não pode ser nulo!");
        }
    	if (likeValue == null || likeValue.equals("")) {
            throw new IllegalArgumentException(
                    "likeValue não pode ser nulo!");
        }
        Query qry = getBroker().startQuery();    	
    	String parametro = "likeValue";
        String sentenca = "get" + getClassEntity().getSimpleName();
        qry.setTextReplacement("likeColumn", likeColumn);
        qry.setParameter(parametro, likeValue);        
        try {
            log.debug("Verificando parametros ..." +
            		"\nTextReplacement   : {}" +
                    "\nQuery parameter: {}" +
                    "\nStament name   : {}" +
                    "\nArgumento: {}\n", new Object[] {likeColumn, parametro, sentenca, likeValue});
            return qry.selectMany(sentenca);
        } finally {
            qry.close();
        }    	
	}
    
    public Collection<T> findByNamedQuery(String queryName){
        Query qry = getBroker().startQuery();
        try {
            return qry.selectMany(queryName);
        } finally {
            qry.close();
        }
    }
    public Broker getBroker() {
        return ResourceLocator.getInstance().getBroker(brokerName, "gfi");
    }
    public Broker getBroker(String brokerName) {
        return ResourceLocator.getInstance().getBroker(brokerName, "gfi");
    }    
}
