package com.google.constructor.extras.security.shiro.jpa;

import com.google.constructor.extras.security.shiro.jpa.UserJPAService;
import com.google.constructor.extras.security.shiro.jpa.RoleJPAService;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import com.google.constructor.extras.orm.jpa.EntityManagerContext;
import com.google.constructor.extras.security.jpa.model.Role;
import com.google.constructor.extras.security.jpa.model.User;
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

public class UserJpaTest{
    private static UserJPAService userService;
    private static RoleJPAService roleService;
    private static IDatabaseConnection connection;
    private static IDataSet dataset;
    private static EntityManager em;

    @BeforeClass
    public static void initEntityManager() throws Exception {
        em = EntityManagerContext.getEntityManager();
        userService = new UserJPAService();
        roleService = new RoleJPAService();

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
    public void testUpdateUser() throws Exception, RollbackException {
        System.out.println("===========testUpdateUser======");
        User user = userService.find(-1L);

        user.setEmail("novo@com.br");
        userService.update(user);

        User userUpdated = userService.find(-1L);
        assertEquals("novo@com.br", userUpdated.getEmail());
    }

    @Test
    public void testAddUserRole() throws Exception {
        System.out.println("===========testAddUserRole======");
        User user = userService.find(-1L);
        //User has USER_ROLE and ADMIN_ROLE

        assertEquals(2, user.getRoles().size());

        Role role = roleService.getRoleByName("USER_ROLE");
        System.out.println("***** what happen if we add the same Role? "+user+"****");
        //Should threw a exception since we already have such record?
        //I don't think so, since we use HashSet it will return false and won't add nothing!
        //Changing the Role collection type, for example to List, will threw an database exception
        user.getRoles().add(role);
        userService.update(user);

        user = userService.find(-1L);
        assertEquals(2, user.getRoles().size());

        System.out.println("***** add the same role twice - should result in no additional role *****");
        user.getRoles().add(role);
        userService.update(user);
        System.out.println("***** what happen again? "+user+"****");
        //Should threw a exception since we already have such record?
        //I don't think so, since we use HashSet

        user = userService.find(-1L);
        assertEquals("more than 2 roles", 2, user.getRoles().size());

        user.getRoles().remove(role);
        userService.update(user);

        user = userService.find(-1L);
        assertEquals(1, user.getRoles().size());
    }

    @Test
    public void testAddAndRemoveUser() throws Exception {
        System.out.println("===========testAddAndRemoveUser======");
        User user = new User();
        user.setPassword("testpass");
        user.setUsername("Test");
        user.setEmail("testuser@appfuse.org");

        Role role = roleService.getRoleByName("USER_ROLE");
        System.out.println("***** getting role "+role+" for a new user. *****");
        assertNotNull(role);
        user.addRole(role);

        userService.insert(user);
        List<User> lU = (List<User>) userService.getAll();
        assertEquals(4, lU.size());

        System.out.println("***** Does the object was update or shoul I get it by find method? *****");
        assertNotNull(user.getId());

        user = userService.find(user.getId());
        System.out.println("***** should it use cache? " + user + "*****");
        assertEquals("testpass", user.getPassword());

        userService.delete(user.getId());

        System.out.println("***** what does happen if the entity doesn't exist? *****");
        user = userService.find(user.getId());
        assertNull(user);
    }

}
