/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.inventario.service;

import com.gilbertoca.gfi.Constants;
import java.util.Collection;
import net.sourceforge.orbroker.Broker;
import net.sourceforge.orbroker.Query;
import net.sourceforge.orbroker.Transaction;

import com.gilbertoca.gfi.inventario2.model.UnidadeMedida;
import com.gilbertoca.gfi.service.Service;

/**
 *
 * @author gilberto
 */
public class UnidadeMedidaService extends Service<UnidadeMedida, String> {

    @Override
    public Broker getBroker() {
        return getBroker(Constants.ORBROKER_INVENTARIO);
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
    public Collection<UnidadeMedida> findByNamedQuery(String queryName) {
        Query qry = getBroker().startQuery();
        try {
            return qry.selectMany(queryName);
        } finally {
            qry.close();
        }
    }

    @Override
    public UnidadeMedida findByPk(String pk) {
        if (pk == null || pk.equals("")) {
            throw new IllegalArgumentException(
                    "Identificador n�o pode ser nulo!");
        }
        Query qry = getBroker().startQuery();
        try {
            qry.setParameter("cdUnidadeMedida", pk);
            return (UnidadeMedida) qry.selectOne("getUnidadeMedidaByCdUnidadeMedida");
        } finally {
            qry.close();
        }
    }

    @Override
    public void insert(UnidadeMedida entity) {
        Transaction txn = getBroker().startTransaction();
        int recordsUpdated = 0;
        try {
            txn.setParameter("unidadeMedida", entity);
            recordsUpdated = txn.execute("insertUnidadeMedida");
            if (recordsUpdated != 1) {
                txn.rollback();
            //throw new ThatsWeirdException();
            }
            txn.commit();
        } finally {
            txn.close();
        }
    }

    @Override
    public void update(UnidadeMedida entity) {
        Transaction txn = getBroker().startTransaction();
        int recordsUpdated = 0;
        try {
            txn.setParameter("unidadeMedida", entity);
            recordsUpdated = txn.execute("updateUnidadeMedida");
            if (recordsUpdated != 1) {
                txn.rollback();
            //throw new ThatsWeirdException();
            }
            txn.commit();
        } finally {
            txn.close();
        }
    }

    @Override
    public void deleteAll() {
        Transaction txn = getBroker().startTransaction();
        int recordsUpdated = 0;
        try {
            recordsUpdated = txn.execute("deleteUnidadeMedida");
            if (recordsUpdated != 1) {
                txn.rollback();
            //throw new ThatsWeirdException();
            }
            txn.commit();
        } finally {
            txn.close();
        }
    }

    @Override
    public void deleteByPk(String pk) {
        Transaction txn = getBroker().startTransaction();
        int recordsUpdated = 0;
        try {
            txn.setParameter("cdUnidadeMedida", pk);
            recordsUpdated = txn.execute("deleteUnidadeMedidaByCdUnidadeMedida");
            if (recordsUpdated != 1) {
                txn.rollback();
            //throw new ThatsWeirdException();
            }
            txn.commit();
        } finally {
            txn.close();
        }
    }

    public void delete(UnidadeMedida entity) {
        Transaction txn = getBroker().startTransaction();
        int recordsUpdated = 0;
        try {
            txn.setParameter("unidadeMedida", entity);
            recordsUpdated = txn.execute("deleteUnidadeMedidaById");
            if (recordsUpdated != 1) {
                txn.rollback();
            //throw new ThatsWeirdException();
            }
            txn.commit();
        } finally {
            txn.close();
        }
    }
}
