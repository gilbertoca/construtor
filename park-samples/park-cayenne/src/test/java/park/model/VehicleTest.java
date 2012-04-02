package park.model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.NamedQuery;
import org.apache.cayenne.query.SelectQuery;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VehicleTest {

    private static ServerRuntime runtime;
    private static IDatabaseConnection connection;
    private static IDataSet dataset;

    @BeforeClass
    public static void init() throws Exception {
        //JPA-->emf = Persistence.createEntityManagerFactory("PU");
        //JPA-->em = emf.createEntityManager();
        
        //Create Cayenne ServerRuntime, uses it to get a connection and initializes DBUnit
        runtime = new ServerRuntime("cayenne-ParkDomain.xml");

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
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("vehicle-dataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
    }

    @AfterClass
    public static void close() throws SQLException, DatabaseUnitException {
        DatabaseOperation.DELETE.execute(connection, dataset);
        connection.close();
    }

    @Before
    public void cleanDB() throws Exception {
        // Cleans the database with DbUnit
        DatabaseOperation.REFRESH.execute(connection, dataset);
    }

    @Test
    public void GetVehicleById() {
        System.out.println("\nGetting an Vehicle by ID.\n");
        ObjectContext context = runtime.getContext();
        //JPA--> Vehicle v = em.find(Vehicle.class, "LC100");
        Vehicle v = Cayenne.objectForPK(context, Vehicle.class,"LC100");
        System.out.println("Object loaded: \n" + v);
        assertEquals(v.getColor(), "RED");
    }

    @Test
    public void findAll() throws Exception {
        System.out.println("\nGetting all Vehicle.\n");
        // Gets all the objects from the database
        //JPA-->Query query = em.createNamedQuery("Vehicle.findAll");
        //JPA-->int size = query.getResultList().size();
        ObjectContext context = runtime.getContext();
        SelectQuery query = new SelectQuery(Vehicle.class);
        List<Vehicle> entities = context.performQuery(query);
        int size = entities.size();
        assertEquals("Should have 3 Vehicle", 3, size);

        // Creates a new object and persists it
        Vehicle v = new Vehicle();
        v.setLicensePlate("LC103");
        v.setColor("RED");
        //JPA-->PriceTable pT = em.find(PriceTable.class, 100);
        PriceTable pT = Cayenne.objectForPK(context, PriceTable.class, 100);
        //JPA-->VehicleType vT = em.find(VehicleType.class, "CAR");
        VehicleType vT = Cayenne.objectForPK(context, VehicleType.class, "CAR");
        //JPA-->Customer c = em.find(Customer.class, 1000L);
        Customer c = Cayenne.objectForPK(runtime.getContext(), Customer.class, 1000L);
        
        System.out.println("Customer Object loaded: \n" + c);
        System.out.println("Customer's Person Object loaded: \n" + c.getPerson());
        //set relationships
        v.setCustomer(c);
        v.setPriceTable(pT);
        v.setVehicleType(vT);

        /* JPA persist
        tx.begin();
        em.persist(v);
        tx.commit();
        */
        context.registerNewObject(v);
        context.commitChanges();
        
        // Gets all the objects from the database(using the same query)
        //JPA-->size = query.getResultList().size();
        entities = context.performQuery(query);
        size = entities.size();        
        assertEquals("Should have 4 Vehicle", 4, size);

        /* JPA persist
        tx.begin();
        em.remove(v);
        tx.commit();
        */
        context.deleteObject(v);
        context.commitChanges();
        
        // Gets all the objects from the database(using the same query)
        //JPA-->size = query.getResultList().size();
        entities = context.performQuery(query);
        size = entities.size();                
        assertEquals("Should have 3 Vehicle", 3, size);
    }
    /**
     * Test of Vehicle.findByLicensePlate named query.
     */
    @Test
    public void findByLicensePlate() {
        System.out.println("\nGetting an Vehicle by Vehicle.findByLicensePlate.\n");
        //JPA-->Query query = em.createNamedQuery("Vehicle.findByLicensePlate");
        //JPA-->query.setParameter("licensePlate", "%LC1%");
        //JPA-->int size = query.getResultList().size();
        ObjectContext context = runtime.getContext();
        String[] keys = new String[]{"licensePlate"};
        Object[] values = new String[]{"%LC1%"};
        NamedQuery query = new NamedQuery("Vehicle.findByLicensePlate", keys, values);
        List entities = context.performQuery(query);
        assertEquals("Should have 3 Vehicle", 3, entities.size());
    }
    
    /**
     * Test of Vehicle.findByLicensePlateEJBQL named query.
     */
    @Test
    public void findByLicensePlateEJBQL() {
        System.out.println("\nGetting an Vehicle by Vehicle.findByLicensePlateEJBQL.\n");
        //JPA-->Query query = em.createNamedQuery("Vehicle.findByLicensePlate");
        //JPA-->query.setParameter("licensePlate", "%LC1%");
        //JPA-->int size = query.getResultList().size();        
        ObjectContext context = runtime.getContext();
        String[] keys = new String[]{"licensePlate"};
        Object[] values = new String[]{"%LC1%"};

        NamedQuery query = new NamedQuery("Vehicle.findByLicensePlateEJBQL", keys, values);
        List entities = context.performQuery(query);
        assertEquals("Should have 3 Vehicle", 3, entities.size());
    }   
    /**
     * Test of Vehicle.findInLicensePlate named query.
     */
    @Test
    public void findInLicensePlate() {
        System.out.println("\nGetting an Vehicle IN Vehicle.findInLicensePlate.\n");
        //JPA-->Query query = em.createNamedQuery("Vehicle.findInLicensePlate");
        //JPA-->query.setParameter("licensePlate", new String[]{"LC101","LC102"});
        //JPA-->int size = query.getResultList().size();
        ObjectContext context = runtime.getContext();
        NamedQuery query = new NamedQuery("Vehicle.findInLicensePlate", Collections.singletonMap("licensePlate", new String[]{"LC101","LC102"}));
        List entities = context.performQuery(query);
        assertEquals("Should have 2 Vehicle", 2, entities.size());
    }
    
}
