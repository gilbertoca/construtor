package park.model;

import java.sql.DriverManager;
import java.util.List;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectQuery;
import org.apache.cayenne.Cayenne;
import org.apache.cayenne.access.DataDomain;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.configuration.server.ServerRuntime;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
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
import static org.junit.Assert.*;


public class EmployeeTest {
    private static ServerRuntime runtime;
    private static IDatabaseConnection connection;
    private static IDataSet dataset;

    @BeforeClass
    public static void init() throws Exception {
        //emf = Persistence.createEntityManagerFactory("PU");
        //em = emf.createEntityManager();
        
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
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("employee-dataset.xml"));
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
     * Test of setVehicletype method, of class Vehicle.
     */
    @Test
    public void GetEmployeeById() {
        System.out.println("\nGetting an Employee by ID.\n");
        //Employee c = em.find(Employee.class, 1004L);
        Employee e = Cayenne.objectForPK(runtime.getContext(),Employee.class, 1004L);
        System.out.println("Object loaded: \n" + e);
        assertNotNull(e.getNaturalPerson());
    }

    @Test
    public void findAll() throws Exception {
        ObjectContext context = runtime.getContext();
        // Gets all the objects from the database
        //Query query = em.createNamedQuery("Employee.findAll");
        SelectQuery query = new SelectQuery(Employee.class);
        List classEntities = context.performQuery(query);
        
        assertEquals("Should have 1 employees", classEntities.size(), 1);

        // Creates a new object and persists it
        //Employee c = new Employee(1002, 3);
        Employee e = new Employee();
        //NaturalPerson nP = em.find(NaturalPerson.class, 1005L);
        NaturalPerson nP = Cayenne.objectForPK(context,NaturalPerson.class, 1005L);
        System.out.println("Foreign Key Object loaded: \n" + nP);
        //Setting the class attribute will need manual set of employee.id?
        //e.setNaturalPerson(nP); 
        //c.setId(lP.getId());
        e.setDtAdmission(new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1974"));
        //Parking p = em.find(Parking.class, 1001L);
        Parking p = Cayenne.objectForPK(context, Parking.class, 1000L);
        System.out.println("Foreign Key Object loaded: \n" + p);
        e.setParking(p);
        
        /* JPA code
        tx.begin();
        em.persist(c);
        tx.commit();
        */
        context.registerNewObject(e);
        context.commitChanges();

        // Gets all the objects from the database
        assertEquals("Should have 2 employees", context.performQuery(query).size(), 2);

        // Removes the object from the database
        /* JPA code
        tx.begin();
        em.remove(c);
        tx.commit();
        */
        context.deleteObject(e);
        context.commitChanges();

        // Gets all the objects from the database
        assertEquals("Should have 1 employees", context.performQuery(query).size(), 1);
    }
}
