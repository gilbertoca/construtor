/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.google.constructor.extras.security.shiro.jpa;

import com.google.constructor.extras.security.shiro.jpa.UserJPAService;
import com.google.constructor.extras.security.shiro.jpa.IUserService;
import com.google.constructor.extras.security.shiro.jpa.ShiroJPARealm;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.persistence.EntityManager;
import com.google.constructor.extras.orm.jpa.EntityManagerContext;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gilberto
 */
public class ShiroJPARealmTest {
    private static IUserService userService;
    private static IDatabaseConnection connection;
    private static IDataSet dataset;
    private static EntityManager em;

    @BeforeClass
    public static void initEntityManager() throws Exception {
        em = EntityManagerContext.getEntityManager();
        userService = new UserJPAService();

        // Initializes DBUnit
        // For now, getting connection from one JPA provider is impossible
        // connection = new DatabaseConnection(em.unwrap(java.sql.Connection.class));
        // So, let's take a work around ...

        // I presume you've set the src/test/resources/jdbc.properties
        Properties configurationProperties = new Properties();
        configurationProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));

        //Let's create a new connection to work with DBUnit
        Class.forName(configurationProperties.getProperty("jdbc.driverClassName"));
        connection = new DatabaseConnection(DriverManager.getConnection(
                configurationProperties.getProperty("jdbc.url"),
                configurationProperties.getProperty("jdbc.username"),
                configurationProperties.getProperty("jdbc.password")));

        // http://dbunit.sourceforge.net/faq.html#typefactory
        DatabaseConfig config = connection.getConfig();
        //How to get new instance of H2DataTypeFactory|OracleDataTypeFactory|PostgresqlDataTypeFactory
        IDataTypeFactory dataTypeFactory = (IDataTypeFactory)Class.forName(configurationProperties.getProperty("dbunit.dataTypeFactoryName")).newInstance();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, dataTypeFactory);
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("shiro-user-dataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
    }

    @AfterClass
    public static void closeEntityManager() throws SQLException, DatabaseUnitException {
        EntityManagerContext.close();
        //before we close the connection
        DatabaseOperation.DELETE.execute(connection, dataset);
        connection.close();
    }

    @Test
    public void testGetAuthenticationInfo() {
        System.out.println("testGetAuthenticationInfo");
        ShiroJPARealm instance = new ShiroJPARealm();
        instance.setUserService(userService);
        //User doesn't exists
        assertNull(instance.doGetAuthenticationInfo(new UsernamePasswordToken("bruce@", "bruce")));
        //User exists
        assertNotNull(instance.doGetAuthenticationInfo(new UsernamePasswordToken("bruce@hotmail.com", "bruce")));
    }

}