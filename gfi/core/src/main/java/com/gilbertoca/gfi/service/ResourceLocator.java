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
import java.util.logging.Level;

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
	static final String ORBROKER_LOG_LEVEL = "log.level";
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
    	log.debug("Obtendo fonte de dados (DBCP DataSource) existente");
        DataSource dataSource = (DataSource) cache.get(Constants.DBCPDATASOURCE);
        if (dataSource == null) {
            InputStream is = null;
            Properties properties = new Properties();
            log.debug("Obtendo fonte de dados (DataSource), atraves de arquivo de propriedades: {}",Constants.DBCPCONFIGFILE);
            is = getClass().getResourceAsStream(Constants.DBCPCONFIGFILE);
            if (is == null) {
                throw new ResourceLocatorException("Arquivo de propriedades não encontrado! ");
            }
            try {
                log.debug("Carregando arquivo de propriedades ...");
            	properties.load(is);
                dataSource = (DataSource) BasicDataSourceFactory.createDataSource(properties);
                cache.put(Constants.DBCPDATASOURCE, dataSource);
                log.debug("Fonte de dados (DBCP DataSource) criada: {}", dataSource);
            } catch (IOException ex) {
                log.debug("ERRO: não foi possível carregar arquivo de propriedades");
                ex.printStackTrace();
            } catch (Exception ex) {
                log.debug("ERRO: não foi possível a criação da fonte de dados (DBCP DataSource)");
                ex.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
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
    	log.debug("Obtendo mecanismo de persistência (OrBroker) existente.");
        Broker broker = (Broker) cache.get(brokerName);
        if (broker == null) {
            log.debug("Obtendo mecanismo de persistência (OrBroker), através do arquivo: {} e schema: {}", brokerName, schema);            
            InputStream is = getClass().getResourceAsStream(brokerName);
            if (is == null) {
                throw new ResourceLocatorException("Arquivo xml não encontrado! ");
            }
            try {
                log.debug("Carregando arquivo xml ...");
                broker = new Broker(is, getDataSource());
                if (schema != null){
                    broker.setTextReplacement("schema", schema);
                }
                /*
                 * Ativando/Desativando o logging especifico do ORBROKER
                 * WARNING: Used for internally caught exceptions not considered severe.</li>
                 * INFO: Shows transaction boundaries and SQL statements executed.</li>
                 * CONFIG: Information about driver support and configuration setup</li>
                 * FINE: Shows dynamic SQL statement after parsing.</li>
                 */
                
                String level = System.getProperty(ORBROKER_LOG_LEVEL);
                if (level == null) {
                	Broker.setLoggingLevel(Level.OFF);
                }else{
                	log.debug("Ativando OrBroker logging com severidade: {}", level);
               		Broker.setLoggingLevel(Level.parse(level.toUpperCase()));
                }    
                cache.put(brokerName, broker);
                log.debug("Mecanismo de persistência (OrBroker) criado: {}", broker);
            } catch (Exception ex) {
                log.debug("ERRO: não foi possível a criação do mecanismo de persistência (OrBroker)");
                ex.printStackTrace();
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
