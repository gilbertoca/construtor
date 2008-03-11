/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.inventario.service;

import java.util.Collection;

import net.sourceforge.orbroker.Broker;
import net.sourceforge.orbroker.Query;
import net.sourceforge.orbroker.Transaction;

import com.gilbertoca.gfi.Constants;
import com.gilbertoca.gfi.inventario2.model.Categoria;
import com.gilbertoca.gfi.service.Service;

/**
 *
 * @author gilberto
 */
public class CategoriaService extends Service<Categoria, Integer> {

    @Override
    public Broker getBroker() {
        return getBroker(Constants.ORBROKER_INVENTARIO, "gfi");
    }

    //============== Unidade de Medida =======================
    @Override
    public Collection<Categoria> findAll() {
        Query qry = getBroker().startQuery();
        try {
            return findByNamedQuery("getCategoria");
        } finally {
            qry.close();
        }
    }

    @Override
    public Categoria findByPk(Integer pk) {
        if (pk == null || pk.equals("")) {
            throw new IllegalArgumentException(
                    "Identificador não pode ser nulo!");
        }
        Query qry = getBroker().startQuery();
        try {
            qry.setParameter("categoria.cdCategoria", pk);
            qry.setParameter("ByPk", pk);
            return (Categoria) qry.selectOne("getCategoria");
        } finally {
            qry.close();
        }
    }
    @Override
    public Collection<Categoria> findLike(Categoria entity) {
        Query qry = getBroker().startQuery();
        try {
            qry.setParameter("categoria.cdCategoria", "%" + entity.getCdCategoria() + "%");
            qry.setParameter("categoria.descricaoUnidade", "%" + entity.getDescricaoCategoria() + "%");
            qry.setParameter("like", "ok");
            return qry.selectMany("getCategoria");
        } finally {
            qry.close();
        }
    }

    @Override
    public void insert(Categoria entity) {
    	log.debug("Realizando inserção. Entidade usada como parâmetro: {} ",entity);    	
        Transaction txn = getBroker().startTransaction();
        int recordsUpdated = 0;
        try {
            txn.setParameter("categoria", entity);
            recordsUpdated = txn.execute("insertCategoria");
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

    @Override
    public void update(Categoria entity) {
    	log.debug("Realizando alteração. Entidade usada como parâmetro: {} ",entity);
        Transaction txn = getBroker().startTransaction();
        int recordsUpdated = 0;
        try {
            txn.setParameter("categoria", entity);
            recordsUpdated = txn.execute("updateCategoria");
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
    public void deleteByPk(Integer pk) {
        log.debug("Realizando deleção. Identificador usado como parâmetro: {} ",pk);
        Transaction txn = getBroker().startTransaction();
        int recordsUpdated = 0;
        try {
            txn.setParameter("cdCategoria", pk);
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

    public void delete(Categoria entity) {
         deleteByPk(entity.getCdCategoria());
    }
}
