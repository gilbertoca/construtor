package park.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StayTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;
    private static IDatabaseConnection connection;
    private static IDataSet dataset;

    @BeforeClass
    public static void initEntityManager() throws Exception {
        emf = Persistence.createEntityManagerFactory("PU");
        em = emf.createEntityManager();

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
        IDataTypeFactory dataTypeFactory = (IDataTypeFactory) Class.forName(configurationProperties.getProperty("dbunit.dataTypeFactoryName")).newInstance();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, dataTypeFactory);
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("stay-dataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
    }

    @AfterClass
    public static void closeEntityManager() throws SQLException, DatabaseUnitException {
        em.close();
        emf.close();
        //before we close the connection
        DatabaseOperation.DELETE.execute(connection, dataset);
        connection.close();
    }

    @Before
    public void initTransaction() {
        tx = em.getTransaction();
    }

    @Before
    public void cleanDB() throws Exception {
        // Cleans the database with DbUnit
        DatabaseOperation.REFRESH.execute(connection, dataset);
    }

    /**
     * Test of getStay method, of class Stay.
     */
    @Test
    public void getStayById() {
        System.out.println("\nGetting an Stay by ID.\n");
        Stay s = em.find(Stay.class, 100);
        System.out.println("Object loaded: \n" + s);
        assertNotNull(s.getStatus());
    }

    @Test
    public void findAll() throws Exception {

        // Gets all the objects from the database
        Query query = em.createNamedQuery("Stay.findAll");
        assertEquals("Should have 2 stays", query.getResultList().size(), 2);

        // Creates a new object and persists it
        Stay s = new Stay();
        s.setDtEntrance(new SimpleDateFormat("dd/MM/yyyy").parse("03/02/2010"));
        s.setHrEntrance(new SimpleDateFormat("hh:mm:ss").parse("10:00:02"));
        Employee employee = em.find(Employee.class, 1004L);
        s.setEmployeeEntrance(employee);
        s.setParking(employee.getParking());
        s.setVehicle(em.find(Vehicle.class, "LC102"));
        tx.begin();
        em.persist(s);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 3 stays", query.getResultList().size(), 3);

        // Removes the object from the database
        tx.begin();
        em.remove(s);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 2 stays", query.getResultList().size(), 2);
    }

    @Test
    public void romeveBidirectional() throws Exception {
        System.out.println("\nGetting an Vehicle by ID.\n");
        Vehicle v = em.find(Vehicle.class, "LC100");
        System.out.println("Object loaded: \n" + v);
        assertEquals(v.getColor(), "RED");
        assertEquals("Should have 2 stays", v.getStays().size(), 2);
        for (Stay s : v.getStays()) {
            if (s.getId().equals(100)) {
                v.getStays().remove(s);
                s.setVehicle(null);
            }
        }
        //Another way:
        //Stay s = em.find(Stay.class, 100);
        //v.getStays().remove(s);
        //s.setVehicle(null);

        tx.begin();
        em.merge(v);
        tx.commit();
        assertEquals("Should have 1 stays", v.getStays().size(), 1);
    }
}
