package com.google.constructor.extras.security.shiro.cayenne;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import org.apache.cayenne.access.DataDomain;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.conf.Configuration;
import com.google.constructor.extras.security.cayenne.domain.Role;
import com.google.constructor.extras.security.cayenne.domain.User;
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
    public static void initDataContext() throws Exception {
        userService = new UserCayenneService();
        roleService = new RoleCayenneService();

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

        List<User> lU = (List<User>) userService.getAll();
        assertEquals(4, lU.size());

        System.out.println("***** Does the object was update or shoul I get it by find method? *****");
        assertNotNull(user.getId());

        user = userService.find(user.getId());
        System.out.println("***** should use cache? " + user + "*****");
        assertEquals("testpass", user.getPassword());

        userService.delete(user.getId());

        System.out.println("***** what does happen if the entity doesn't exist? *****");
        user = userService.find(user.getId());
        assertNull(user);
    }
    @Test
    public void testAddUserRole() throws Exception {
        System.out.println("===========testAddUserRole======");
        System.out.println("***** Checking # users *****");
        assertEquals(3, userService.getAll().size());

        System.out.println("***** Checking # roles *****");
        assertEquals(2, roleService.getAll().size());

        User user = userService.find(-2L);
        System.out.println("***** Checking # user -2L *****");
        assertNotNull(user);

        System.out.println("***** Checking # user -2L and roles *****");
        assertEquals(1, user.getRoles().size());

        Role roleADMIN = roleService.getRoleByName("ADMIN_ROLE");
        System.out.println("***** Checking # user -2L and roles, adding one more *****");
        user.addToRoles(roleADMIN);
        userService.update(user);
        System.out.println("*****Now user has USER_ROLE and ADMIN_ROLE**********"+user.getRoles());
        User userUpdated = userService.find(-2L);
        assertEquals(2, userUpdated.getRoles().size());

        System.out.println("***** Checking # user -2L and roles, removing the ADMIN_ROLE *****");
        //userUpdated.getRoles().remove(roleADMIN);
        userUpdated.removeFromRoles(roleADMIN);
        userService.update(user);
        System.out.println("***** Checking # user -2L and roles *****");
        assertEquals(1, user.getRoles().size());

    }

/*
    @Test(expected=CayenneRuntimeException.class)
    public void testAddUserRoleInvalid() throws Exception {
        System.out.println("===========testAddUserRoleInvalid======");
        User user = userService.find(-1L);
        
        System.out.println("*****User has USER_ROLE and ADMIN_ROLE**********"+user.getRoles().get(0));

        assertEquals(2, user.getRoles().size());

        Role role = roleService.getRoleByName("USER_ROLE");
        user.addToRoles(role);
        System.out.println("***** what happen if we add the same Role? " + user + "****");
        //Should threw a exception since we already have such record?
        //Yes, since cayenne uses List as default to all relationship
        userService.update(user);
    }*/
}
