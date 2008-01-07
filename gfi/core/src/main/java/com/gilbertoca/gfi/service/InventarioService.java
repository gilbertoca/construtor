/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import com.gilbertoca.gfi.Constants;
import com.gilbertoca.gfi.model.inventario.UnidadeMedida;
import java.util.Collection;
import net.sourceforge.orbroker.Broker;
import net.sourceforge.orbroker.Query;
import net.sourceforge.orbroker.Transaction;

/**
 *
 * @author gilberto
 */
public class InventarioService extends Service {

    @Override
    public Broker getBroker() {
        return ResourceLocator.getInstance().getBroker(Constants.ORBROKER_INVENTARIO);
    }

    //============== Unidade de Medida =======================
    @Override
    public Collection findAll() {
        Query qry = getBroker().startQuery();
        try {
            return findByNamedQuery("getUnidadeMedida");
        } finally {
            qry.close();
        }
    }

    @Override
    public void insert(Object entity) {
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
    public void update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection findByNamedQuery(String queryName) {
        Query qry = getBroker().startQuery();
        try {
            return qry.selectMany(queryName);
        } finally {
            qry.close();
        }
    }

    @Override
    public void delete(Object entity) {
        Transaction txn = getBroker().startTransaction();
        int recordsUpdated = 0;
        try {
            txn.setParameter("unidadeMedida", entity);
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
}
