/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gilbertoca.gfi.service;

import com.gilbertoca.gfi.Constants;
import java.util.Collection;
import net.sourceforge.orbroker.Broker;

/**
 *
 * @author gilberto
 */
public class InventarioService extends Service{

    @Override
    public Broker getBroker(String BrokerName) {
        return ResourceLocator.getInstance().getBroker(Constants.ORBROKER_INVENTARIO);
    }

    //============== Unidade de Medida =======================
    @Override
    public Collection findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(Object entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection findByNamedQuery(String queryName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
