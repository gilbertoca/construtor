package org.apache.click.extras.security.shiro;

import org.apache.click.extras.security.shiro.jpa.UserJPAService;
import org.apache.click.extras.security.shiro.jpa.RoleJPAService;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import org.apache.click.extras.orm.jpa.EntityManagerContext;
import org.apache.click.extras.security.jpa.model.Role;
import org.apache.click.extras.security.jpa.model.User;
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
        userService.find(1000L);
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

        // verify that violation occurs when adding new user with same username
        //userUpdated.setId(null);

        // should throw some persistence Exception
        //userService.update(userUpdated);
    }

    @Test
    public void testAddUserRole() throws Exception {
        System.out.println("===========testAddUserRole======");
        User user = userService.find(-1L);
        assertEquals(2, user.getRoles().size());

        Role role = roleService.getRoleByName("USER_ROLE");
        user.addRole(role);
        userService.update(user);

        user = userService.find(-1L);
        assertEquals(2, user.getRoles().size());

        //add the same role twice - should result in no additional role
        user.addRole(role);
        userService.update(user);

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
        System.out.println("***** what does happen if we don't get id by find method? *****");
        assertNotNull(user.getId());

        user = userService.find(user.getId());
        System.out.println("***** get id by find method should use cache? "+user+"*****");
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
