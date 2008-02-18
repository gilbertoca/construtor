/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import com.gilbertoca.gfi.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import net.sourceforge.orbroker.Broker;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is an implementation of the Service Locator pattern. It is
 * used to looukup resources such as EJBHomes, JMS Destinations, etc.
 * This implementation uses the "singleton" strategy and also the "caching"
 * strategy.
 * This implementation is intended to be used on the web tier and
 * not on the ejb tier.
 * @author gilberto
 */
public class ResourceLocator {

    private transient final Logger log = LoggerFactory.getLogger(getClass());
    private InitialContext ic;
    //used to hold references to EJBHomes/JMS Resources for re-use
    private Map cache = Collections.synchronizedMap(new HashMap());
    private static ResourceLocator instance = new ResourceLocator();

    public static ResourceLocator getInstance() {
        return instance;
    }

    private ResourceLocator() throws ResourceLocatorException {
        try {
            ic = new InitialContext();
        } catch (Exception e) {
            throw new ResourceLocatorException(e);
        }
    }

    /**
     * This method obtains the datasource itself for a caller
     * @return the DataSource corresponding to the name parameter
     */
    public DataSource getDataSource(String dataSourceName) throws ResourceLocatorException {
        DataSource dataSource = (DataSource) cache.get(dataSourceName);
        if (dataSource == null) {
            try {
                dataSource = (DataSource) ic.lookup(dataSourceName);
                cache.put(dataSourceName, dataSource);
            } catch (Exception e) {
                throw new ResourceLocatorException(e);
            }
        }
        return dataSource;
    }

    /**
     * This method obtains the datasource itself for a caller
     * @return the DataSource
     */
    public DataSource getDataSource() throws ResourceLocatorException {
        log.info("getDBCPDataSource() started");
        DataSource dataSource = (DataSource) cache.get(Constants.DBCPDATASOURCE);
        if (dataSource == null) {
            InputStream is = null;
            Properties properties = new Properties();
            log.info("Loading DBCP properties file: " + Constants.DBCPCONFIGFILE);
            is = getClass().getResourceAsStream(Constants.DBCPCONFIGFILE);
            if (is == null) {
                throw new ResourceLocatorException("DBCP properties file not found: " + is);
            }
            try {
                properties.load(is);
                log.info("Loaded DBCP properties: " + properties);
                dataSource = (DataSource) BasicDataSourceFactory.createDataSource(properties);
                cache.put(Constants.DBCPDATASOURCE, dataSource);
                log.info("DBCP BasicDataSource created: " + dataSource);
            } catch (IOException ex) {
                log.debug("BUG: nao consegue carregar DBCP properties");
                ex.printStackTrace();
            } catch (Exception ex) {
                log.debug("BUG: nao consegue carregar o driver");
                ex.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        log.info("getDBCPDataSource() finished");
        return dataSource;
    }

    /**
     * This method obtains the broker itself for a caller
     * @return the Broker corresponding to the name parameter
     */
    public Broker getBroker(String brokerName) throws ResourceLocatorException {
        return getBroker(brokerName, null);
    }
    
    /**
     * This method obtains the broker itself for a caller
     * @return the Broker corresponding to the name parameter
     */
    public Broker getBroker(String brokerName, String schema) throws ResourceLocatorException {
        log.info("getBroker(String BrokerName) started");
        Broker broker = (Broker) cache.get(brokerName);
        if (broker == null) {
            log.info("Loading OrBroker xml file: " + brokerName);
            InputStream is = getClass().getResourceAsStream(brokerName);
            if (is == null) {
                throw new ResourceLocatorException("OrBroker xml file not found: " + is);
            }
            log.info("Loaded OrBroker xml file: " + is);
            try {
                broker = new Broker(is, getDataSource());
                if (schema != null){
                    broker.setTextReplacement("schema", schema);
                }
                cache.put(brokerName, broker);
                log.info("OrBroker created: " + broker);
            } catch (Exception e) {
                throw new ResourceLocatorException(e);
            }
        }
        log.info("getBroker(String BrokerName) finished");
        return broker;
    }

    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException ex) {
            log.debug("Failed getting connection");
            ex.printStackTrace();
            throw new ResourceLocatorException(ex);
        }
    }
}    
