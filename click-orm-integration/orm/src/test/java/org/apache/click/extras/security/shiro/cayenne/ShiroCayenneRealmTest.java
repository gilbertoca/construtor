/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.click.extras.security.shiro.cayenne;

import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.conf.Configuration;
import org.apache.cayenne.access.DataDomain;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
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
public class ShiroCayenneRealmTest {

    private static UserCayenneService userService;
    private static IDatabaseConnection connection;
    private static IDataSet dataset;

    @BeforeClass
    public static void initDataContext() throws Exception {
        userService = new UserCayenneService();

        // Initializes DBUnit
        // I presume you've set the src/test/resources/jdbc.properties
        Properties configurationProperties = new Properties();
        configurationProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));

        DataDomain domain = Configuration.getSharedConfiguration().getDomain();
        DataNode node = domain.getNode(configurationProperties.getProperty("cayenne.nodeName"));
        connection = new DatabaseConnection(node.getDataSource().getConnection());

        // http://dbunit.sourceforge.net/faq.html#typefactory
        DatabaseConfig config = connection.getConfig();
        //How to get new instance of H2DataTypeFactory|OracleDataTypeFactory|PostgresqlDataTypeFactory
        IDataTypeFactory dataTypeFactory = (IDataTypeFactory) Class.forName(configurationProperties.getProperty("dbunit.dataTypeFactoryName")).newInstance();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, dataTypeFactory);
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("shiro-user-dataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
    }

    @AfterClass
    public static void closeDataContext() throws SQLException, DatabaseUnitException {
        //userService.getDataContext().clear?
        //before we close the connection

        DatabaseOperation.DELETE.execute(connection, dataset);
        connection.close();
    }

    @Test
    public void testGetAuthenticationInfo() {
        System.out.println("testGetAuthenticationInfo");
        ShiroCayenneRealm instance = new ShiroCayenneRealm();
        instance.setUserService(userService);
        //User doesn't exists
        assertNull(instance.doGetAuthenticationInfo(new UsernamePasswordToken("bruce@", "bruce")));
        //User exists
        assertNotNull(instance.doGetAuthenticationInfo(new UsernamePasswordToken("bruce@hotmail.com", "bruce")));
    }
}
