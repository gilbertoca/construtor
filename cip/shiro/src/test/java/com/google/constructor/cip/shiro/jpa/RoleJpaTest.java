package com.google.constructor.cip.shiro.jpa;

import com.google.constructor.cip.orm.jpa.EntityManagerContext;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.persistence.EntityManager;
import com.google.constructor.cip.security.jpa.model.Role;
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

public class RoleJpaTest{
    private static IRoleService roleService;
    private static IDatabaseConnection connection;
    private static IDataSet dataset;
    private static EntityManager em;

    @BeforeClass
    public static void initEntityManager() throws Exception {
        em = EntityManagerContext.getEntityManager();
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
        System.out.println("===========testGetRoleInvalid======");
        // should throw DataAccessException
        roleService.find(1000L);
    }

    @Test
    public void testGetRole() throws Exception {
        System.out.println("===========testGetUser======");
        Role role = roleService.find(-1L);

        assertNotNull(role);
        assertEquals(2, role.getPermissions().size());
    }

    @Test
    public void testFindByRoleNameInvalid() throws Exception {
        Role role = roleService.getRoleByName("anything");
        assertNull(role);
    }

    @Test
    public void testUpdateRole() throws Exception {
        Role role = roleService.getRoleByName("ADMIN_ROLE");
        assertNotNull(role);

        role.setDescription("test descr");
        roleService.update(role);
        
        Role roleUpdated = roleService.getRoleByName("ADMIN_ROLE");
        assertEquals("test descr", roleUpdated.getDescription());
    }

    @Test
    public void testAddAndRemoveRole() throws Exception {
        Role role = new Role("testrole");
        role.setDescription("new role descr");
        roleService.insert(role);

        Role roleInserted = roleService.getRoleByName("testrole");
        assertNotNull(roleInserted.getDescription());

        roleService.delete(roleInserted.getId());

        role = roleService.find(roleInserted.getId());
        assertNull(role);
    }

}
