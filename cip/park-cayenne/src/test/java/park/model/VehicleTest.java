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
    private static ServerRuntime runtime;
    private static IDatabaseConnection connection;
    private static IDataSet dataset;

    @BeforeClass
    public static void init() throws Exception {
        // I presume you've set the src/test/resources/jdbc.properties
        Properties configurationProperties = new Properties();
        configurationProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
        
        //Create Cayenne ServerRuntime, uses it to get a connection and initializes DBUnit
        runtime = new ServerRuntime("cayenne-ParkDomain.xml");
        
        DataDomain domain = runtime.getDataDomain();
        DataNode node = domain.getNode(configurationProperties.getProperty("cayenne.nodeName"));
        connection = new DatabaseConnection(node.getDataSource().getConnection());

        // http://dbunit.sourceforge.net/faq.html#typefactory
        DatabaseConfig config = connection.getConfig();
        //How to get new instance of H2DataTypeFactory|OracleDataTypeFactory|PostgresqlDataTypeFactory
        IDataTypeFactory dataTypeFactory = (IDataTypeFactory) Class.forName(configurationProperties.getProperty("dbunit.dataTypeFactoryName")).newInstance();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, dataTypeFactory);
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("vehicle-dataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
        
    }

    @AfterClass
    public static void close() throws SQLException, DatabaseUnitException {
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
        System.out.println("\nGetting an Vehicle by ID.\n");
        Vehicle v = em.find(Vehicle.class, "LC100");
        System.out.println("Object loaded: \n" + v);
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
        System.out.println("Customer Object loaded: \n" + c);
        System.out.println("Customer's Person Object loaded: \n" + c.getPerson());
        //set relationships
        v.setCustomer(c);
        v.setPriceTable(pT);
        v.setVehicleType(vT);
        
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
