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
 * Esta classe é uma implementação do padrão Service Locator. É usada para pesquisa e obtenção
 * de recursos como EJBHomes, Destinos JMS, DataSources, Mecanismos de Persistência(OrBroker), etc.
 * Essa implementação usa a estratégia "singleton", como também a estratégia "caching".
 * Essa implementação pode ser usada nas camada web e service.
 * 
 * @author Gilberto Caetano de Andrade
 */
public class ResourceLocator {
    private transient final Logger log = LoggerFactory.getLogger(getClass());
    /**
     * Contexto local usado para pesquisa e obtenção de recursos por nome.
     */
    private InitialContext ic;
    /**
     * Usado para manter referências a recursos que consomem bastante processo e memória: EJBHomes/JMS/DataSource.
     * Sendo os mesmos reusados. 
     */
    private Map<String, Object> cache = Collections.synchronizedMap(new HashMap<String, Object>());
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
     * Obtém uma Fonte de Dados (DataSource) através do mecanismo de nome de recursos(JNDI), usando
     * o nome do recurso como parâmetro.
     * 
     * @param dataSourceName o nome do recurso.
     * @return a Fonte de Dados(DataSource)
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
     * Obtém uma Fonte de Dados (DataSource) local através do mecanismo de pooling DBCP.
     * 
     * @return a Fonte de Dados(DataSource)
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
     * Este método obtém um objeto broker através do nome de arquivo.
     * 
     * @param brokerName nome do arquivo - Unidade de Persitência - mantido pelo mecanismo OrBroker.
     * @return o Broker correspondente ao parâmetro.
     */
    public Broker getBroker(String brokerName) throws ResourceLocatorException {
    	//Assume brokerName como schema
        return getBroker(brokerName, null);
    }
    
    /**
     * Este método obtém um objeto broker através do nome de arquivo e schema apropriado.
     * 
     * @param brokerName nome do arquivo - Unidade de Persitência - mantido pelo mecanismo OrBroker, se omitido,
     * será lançado a exceção IllegalArgumentException.
     * @param schema corresponde ao schema a ser usado pelo mecanismo de persistẽncia OrBroker, se omitido,
     * assume-se brokerName como schema.
     * @return o Broker correspondente ao parâmetro.
     */
    public Broker getBroker(String brokerName, String schema) throws ResourceLocatorException {
    	log.debug("Obtendo mecanismo de persistência (OrBroker) existente.");
    	if (brokerName == null || brokerName.equals("")) {
            throw new IllegalArgumentException(
                    "Nome do arquivo - Unidade de Persistência - não pode ser nulo!");
        }    	
    	
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
                if (schema != null && !schema.equals("")){
                    broker.setTextReplacement("schema", schema);
                }else{
                	//Assume brokerName como schema
                	broker.setTextReplacement("schema", brokerName);
                }
                
                /*
                 * Ativando/Desativando o logging especifico do ORBROKER
                 * WARNING: Used for internally caught exceptions not considered severe.</li>
                 * INFO: Shows transaction boundaries and SQL statements executed.</li>
                 * CONFIG: Information about driver support and configuration setup</li>
                 * FINE: Shows dynamic SQL statement after parsing.</li>
                 */
                
                String level = System.getProperty(Constants.ORBROKER_LOG_LEVEL);
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
    /**
     * Obtém uma conexão da Fonte de Dados (DataSource) local (DBCP).
     * 
     * @return uma conexão com banco de dados ativa.
     */
    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException ex) {
            log.debug("Falha na obtençao da conexão.");
            ex.printStackTrace();
            throw new ResourceLocatorException(ex);
        }
    }
}    
