/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import java.util.Collection;
import net.sourceforge.orbroker.Broker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author gilberto
 */
abstract public class Service {
    protected transient final Log log = LogFactory.getLog(getClass());
    abstract public Broker getBroker();
    protected Broker getBroker(String brokerName) {
        return ResourceLocator.getInstance().getBroker(brokerName);
    }
    abstract public Collection findAll();
    abstract public void insert(Object entity);
    abstract public void update(Object entity);
    abstract public void delete(Object entity);
    abstract public Collection findByNamedQuery(String queryName);
}
