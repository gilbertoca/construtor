package park.model;


import java.sql.SQLException;
import java.util.Properties;


import org.apache.cayenne.access.DataDomain;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.configuration.server.ServerRuntime;
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

public class StayTest {
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
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("stay-dataset.xml"));
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


    /**
     * Test of getStay method, of class Stay.
     */
 /*   @Test
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
    */
}
