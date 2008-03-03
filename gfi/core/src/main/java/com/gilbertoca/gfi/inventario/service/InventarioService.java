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

import com.gilbertoca.gfi.inventario2.model.Item;
import com.gilbertoca.gfi.service.Service;

/**
 *
 * @author gilberto
 */
public class InventarioService extends Service<Item, Integer> {

    @Override
    public Broker getBroker() {
        return getBroker(Constants.ORBROKER_INVENTARIO, "gfi");
    }

    //============== Item =======================
    @Override
    public Collection<Item> findAll() {
        Query qry = getBroker().startQuery();
        try {
            return findByNamedQuery("getItem");
        } finally {
            qry.close();
        }
    }

    @Override
    public Item findByPk(Integer pk) {
        if (pk == null || pk.equals("")) {
            throw new IllegalArgumentException(
                    "Identificador n�o pode ser nulo!");
        }
        Query qry = getBroker().startQuery();
        try {
            qry.setParameter("unidadeMedida.cdUnidadeMedida", pk);
            qry.setParameter("ByPk", pk);
            return (Item) qry.selectOne("getUnidadeMedida");
        } finally {
            qry.close();
        }
    }
    @Override
    public Collection<Item> findLike(Item entity) {
        Query qry = getBroker().startQuery();
        try {
            qry.setParameter("unidadeMedida.cdUnidadeMedida", "%" + entity.getNomeItem() + "%");
            qry.setParameter("like", "ok");
            return qry.selectMany("getUnidadeMedida");
        } finally {
            qry.close();
        }
    }

    @Override
    public void insert(Item entity) {
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
    public void update(Item entity) {
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
    public void deleteByPk(Integer pk) {
        Transaction txn = getBroker().startTransaction();
        int recordsUpdated = 0;
        try {
            txn.setParameter("unidadeMedida.cdUnidadeMedida", pk);
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

    public void delete(Item entity) {
         deleteByPk(entity.getCdItem());
    }
}
