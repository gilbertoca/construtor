/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import com.gilbertoca.gfi.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import net.sourceforge.orbroker.Broker;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    private transient final Log log = LogFactory.getLog(getClass());
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
        InputStream is = null;
        log.info("getDBCPDataSource() started");
        DataSource dataSource = (DataSource) cache.get(Constants.DBCPDATASOURCE);
        if (dataSource == null) {

            log.info("Loading DBCP properties");

            Properties properties = new Properties();
            is = getClass().getResourceAsStream(Constants.DBCPCONFIGFILE);
            if (is == null) {
                throw new ResourceLocatorException("DBCP properties file not found: " + is);
            }
            try {
                properties.load(is);
                log.info("Loaded DBCP properties: " + properties);
                dataSource = (DataSource) BasicDataSourceFactory.createDataSource(properties);
                cache.put(Constants.DBCPDATASOURCE, dataSource);
                log.info("Loaded DBCP BasicDataSource: " + dataSource);
            } catch (IOException ex) {
                log.debug("BUG: nao consegue DBCP properties");
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
     * This method obtains the datasource itself for a caller
     * @return the Broker corresponding to the name parameter
     */
    public Broker getBroker(String BrokerName) throws ResourceLocatorException {
        InputStream is = null;
        Broker broker = (Broker) cache.get(BrokerName);
        if (broker == null) {
            try {
                is = getClass().getResourceAsStream(BrokerName);
                broker = new Broker(is, getDataSource());
                cache.put(BrokerName, broker);
            } catch (Exception e) {
                throw new ResourceLocatorException(e);
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return broker;
    }
    
}    
