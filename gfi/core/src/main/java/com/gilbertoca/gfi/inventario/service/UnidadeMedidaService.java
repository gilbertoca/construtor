/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.inventario.service;

import java.util.Collection;

import net.sourceforge.orbroker.Broker;
import net.sourceforge.orbroker.ConstraintException;
import net.sourceforge.orbroker.Query;
import net.sourceforge.orbroker.Transaction;

import com.gilbertoca.gfi.Constants;
import com.gilbertoca.gfi.inventario2.model.UnidadeMedida;
import com.gilbertoca.gfi.service.Service;

/**
 *
 * @author gilberto
 */
public class UnidadeMedidaService extends Service<UnidadeMedida, String> {

    @Override
    public Broker getBroker() {
        return getBroker(Constants.ORBROKER_INVENTARIO, "gfi");
    }

    //============== Unidade de Medida =======================
    @Override
    public Collection<UnidadeMedida> findAll() {
        Query qry = getBroker().startQuery();
        try {
            return findByNamedQuery("getUnidadeMedida");
        } finally {
            qry.close();
        }
    }

    @Override
    public UnidadeMedida findByPk(String pk) {
        if (pk == null || pk.equals("")) {
            throw new IllegalArgumentException(
                    "Identificador não pode ser nulo!");
        }
        Query qry = getBroker().startQuery();
        try {
            qry.setParameter("unidadeMedida.cdUnidadeMedida", pk);
            qry.setParameter("ByPk", pk);
            return (UnidadeMedida) qry.selectOne("getUnidadeMedida");
        } finally {
            qry.close();
        }
    }
    @Override
    public Collection<UnidadeMedida> findLike(UnidadeMedida entity) {
    	log.debug("Realizando consulta. Entidade usada como parâmetro: {} ",entity);
        Query qry = getBroker().startQuery();
        try {
            qry.setParameter("unidadeMedida.descricaoUnidade", "%" + entity.getDescricaoUnidade() + "%");
            qry.setParameter("like", "ok");
            return qry.selectMany("getUnidadeMedida");
        } finally {
            qry.close();
        }
    }

    @Override
    public void insert(UnidadeMedida entity) {
    	log.debug("Realizando inserção. Entidade usada como parâmetro: {} ",entity);    	
        Transaction txn = getBroker().startTransaction();
        int recordsUpdated = 0;
        try {
            txn.setParameter("unidadeMedida", entity);
            recordsUpdated = txn.execute("insertUnidadeMedida");
            if (recordsUpdated != 1) {
                txn.rollback();
                log.debug("Problemas na inserção. Nº registros afetados: {} ",recordsUpdated);
                //throw new ConstraintException("");
            }
            txn.commit();
            log.debug("Inserção realizada com sucesso.");
        } finally {
            txn.close();
        }
    }

    @Override
    public void update(UnidadeMedida entity) {
    	log.debug("Realizando alteração. Entidade usada como parâmetro: {} ",entity);
        Transaction txn = getBroker().startTransaction();
        int recordsUpdated = 0;
        try {
            txn.setParameter("unidadeMedida", entity);
            recordsUpdated = txn.execute("updateUnidadeMedida");
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

    @Override
    public void deleteByPk(String pk) {
        log.debug("Realizando deleção. Identificador usado como parâmetro: {} ",pk);
        Transaction txn = getBroker().startTransaction();
        int recordsUpdated = 0;
        try {
            txn.setParameter("cdUnidadeMedida", pk);
            recordsUpdated = txn.execute("deleteUnidadeMedida");
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

    public void delete(UnidadeMedida entity) {
         deleteByPk(entity.getCdUnidadeMedida());
    }
}
