package park.model;

import java.sql.DriverManager;
import org.dbunit.DatabaseUnitException;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

public class VehicleTest {

    protected final Log log = LogFactory.getLog(getClass());
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;
    private static IDatabaseConnection connection;
    private static IDataSet dataset;

    @BeforeClass
    public static void initEntityManager() throws Exception {
        emf = Persistence.createEntityManagerFactory("parkPU");
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
                configurationProperties.getProperty("jdbc.password")), configurationProperties.getProperty("dbunit.schema"));

        // http://dbunit.sourceforge.net/faq.html#typefactory
        DatabaseConfig config = connection.getConfig();
        //How to get new instance of H2DataTypeFactory|OracleDataTypeFactory|PostgresqlDataTypeFactory
        IDataTypeFactory dataTypeFactory = (IDataTypeFactory)Class.forName(configurationProperties.getProperty("dbunit.dataTypeFactoryName")).newInstance();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, dataTypeFactory);
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("vehicle-dataset.xml"));
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
        // REFRESH the database with DbUnit
        DatabaseOperation.REFRESH.execute(connection, dataset);
    }

    /**
     * Test of setVehicletype method, of class Vehicle.
     */
    @Test
    public void GetVehicleById() {
        log.debug("\nGetting an Vehicle by ID.\n");
        Vehicle v = em.find(Vehicle.class, "LC100");
        log.debug("Object loaded: \n" + v);
        assertEquals(v.getColor(), "RED");
    }

    @Test
    public void findAll() throws Exception {

        // Gets all the objects from the database
        Query query = em.createNamedQuery("Vehicle.findAll");
        assertEquals("Should have 3 Vehicle", query.getResultList().size(), 3);
        
        // Creates a new object and persists it
        Vehicle v = new Vehicle();
        v.setLicensePlate("LC103");
        v.setColor("RED");
        PriceTable pT = em.find(PriceTable.class, 100);
        VehicleType vT = em.find(VehicleType.class, "CAR");
        Customer c = em.find(Customer.class, 1000L);
        log.debug("Customer Object loaded: \n" + c);
        log.debug("Customer's Person Object loaded: \n" + c.getPerson());
        //set relationships
        v.setCustomer(c);
        v.setPriceTable(pT);
        v.setVtype(vT);
        
        tx.begin();
        em.persist(v);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 4 Vehicle", query.getResultList().size(), 4);

        // Removes the object from the database
        tx.begin();
        em.remove(v);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 3 Vehicle", query.getResultList().size(), 3);
    }
}
