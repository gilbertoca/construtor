package org.apache.click.extras.security.shiro.cayenne;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.cayenne.CayenneRuntimeException;
import org.apache.click.extras.security.cayenne.domain.Role;
import org.apache.click.extras.security.cayenne.domain.User;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserCayenneTest {

    private static UserCayenneService userService;
    private static RoleCayenneService roleService;
    private static IDatabaseConnection connection;
    private static IDataSet dataset;

    @BeforeClass
    public static void initEntityManager() throws Exception {
        userService = new UserCayenneService();
        roleService = new RoleCayenneService();

        // Initializes DBUnit
        // For now, getting connection from one Cayenne provider is impossible
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
        IDataTypeFactory dataTypeFactory = (IDataTypeFactory) Class.forName(configurationProperties.getProperty("dbunit.dataTypeFactoryName")).newInstance();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, dataTypeFactory);
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("shiro-user-dataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
    }

    @AfterClass
    public static void closeEntityManager() throws SQLException, DatabaseUnitException {
        //before we close the connection
        DatabaseOperation.DELETE.execute(connection, dataset);
        connection.close();
    }

    @Test
    public void testGetUserInvalid() throws Exception {
        System.out.println("===========testGetUserInvalid======");
        // should throw DataAccessException
        User user = userService.find(1000L);
        assertNull(user);
    }

    @Test
    public void testGetUser() throws Exception {
        System.out.println("===========testGetUser======");
        User user = userService.find(-1L);

        assertNotNull(user);
        assertEquals(2, user.getRoles().size());
        //assertTrue(user.isEnabled());
    }

    @Test
    public void testGetUserByName() throws Exception {
        System.out.println("===========testGetUserByName======");
        User user = userService.getUserByName("bruce@hotmail.com");

        assertNotNull(user);
        assertEquals(2, user.getRoles().size());
        //assertTrue(user.isEnabled());
    }

    @Test
    public void testGetUserPassword() throws Exception {
        System.out.println("===========testGetUserPassword======");
        User user = userService.find(-1L);
        String password = user.getPassword();
        assertNotNull(password);
        System.out.println("password: " + password);
    }

    @Test
    public void testUpdateUser() throws Exception {
        System.out.println("===========testUpdateUser======");
        User user = userService.find(-1L);

        user.setEmail("novo@com.br");
        userService.update(user);

        User userUpdated = userService.find(-1L);
        assertEquals("novo@com.br", userUpdated.getEmail());
    }

    @Test(expected=CayenneRuntimeException.class)
    public void testAddUserRole() throws Exception {
        System.out.println("===========testAddUserRole======");
        User user = userService.find(-1L);
        //User has USER_ROLE and ADMIN_ROLE
        assertEquals(2, user.getRoles().size());

        Role role = roleService.getRoleByName("USER_ROLE");
        user.addToRoles(role);
        System.out.println("***** what happen if we add the same Role? " + user + "****");
        //Should threw a exception since we already have such record?
        //Yes, since cayenne uses List as default to all relationship
        userService.update(user);
    }

    @Test
    public void testAddAndRemoveUser() throws Exception {
        System.out.println("===========testAddAndRemoveUser======");
        User user = new User();
        user.setPassword("testpass");
        user.setUsername("Test");
        user.setEmail("testuser@appfuse.org");

        Role role = roleService.getRoleByName("USER_ROLE");
        System.out.println("***** getting role " + role + " for a new user. *****");
        assertNotNull(role);
        user.addToRoles(role);

        userService.insert(user);
        System.out.println("***** Does the object was update or shoul I get it by find method? *****");
        assertNotNull(user.getId());

        user = userService.find(user.getId());
        System.out.println("***** should use cache? " + user + "*****");
        assertEquals("testpass", user.getPassword());

        userService.delete(user.getId());

        System.out.println("***** what does happen if the entity doesn't exist? *****");
        userService.find(user.getId());
    }

    @Test
    public void testUserExists() throws Exception {
        System.out.println("===========testUserExists======");
        User user = new User();
        user.setId(-1L);
        boolean b = userService.find(user);
        assertTrue(b);
    }

    @Test
    public void testUserNotExists() throws Exception {
        System.out.println("===========testUserNotExists======");
        User user = new User();
        user.setId(Long.MIN_VALUE);
        boolean b = userService.find(user);
        assertFalse(b);
    }
}
