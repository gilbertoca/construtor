package park.model.orm;

import park.model.orm.Parking;
import park.model.orm.Employee;
import park.model.orm.NaturalPerson;
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
import static org.junit.Assert.*;


public class EmployeeTest {

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
        IDataTypeFactory dataTypeFactory = (IDataTypeFactory)Class.forName(configurationProperties.getProperty("dbunit.dataTypeFactoryName")).newInstance();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, dataTypeFactory);
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("employee-dataset.xml"));
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
     * Test of setVehicletype method, of class Vehicle.
     */
    @Test
    public void GetEmployeeById() {
        System.out.println("\nGetting an Employee by ID.\n");
        Employee c = em.find(Employee.class, 1004L);
        System.out.println("Object loaded: \n" + c);
        assertNotNull(c.getNaturalPerson());
    }

    @Test
    public void findAll() throws Exception {

        // Gets all the objects from the database
        Query query = em.createNamedQuery("Employee.findAll");
        assertEquals("Should have 1 employees", query.getResultList().size(), 1);

        // Creates a new object and persists it
        //Employee c = new Employee(1002, 3);
        Employee c = new Employee();
        NaturalPerson nP = em.find(NaturalPerson.class, 1005L);
        System.out.println("Foreign Key Object loaded: \n" + nP);
        c.setNaturalPerson(nP); //Setting the class attribute will need manual set of customer.id?
        //c.setId(lP.getId());
        c.setDtAdmission(new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1974"));
        Parking p = em.find(Parking.class, 1001L);
        System.out.println("Foreign Key Object loaded: \n" + p);

        c.setParking(p);
        tx.begin();
        em.persist(c);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 2 employees", query.getResultList().size(), 2);

        // Removes the object from the database
        tx.begin();
        em.remove(c);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 1 employees", query.getResultList().size(), 1);
    }
}
