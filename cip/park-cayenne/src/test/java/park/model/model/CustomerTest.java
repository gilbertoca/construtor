package park.model.model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.access.DataDomain;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.query.Query;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerTest {
    private static ObjectContext em = DataContext.getThreadObjectContext();
    private static IDatabaseConnection connection;
    private static IDataSet dataset;

    @BeforeClass
    public static void initEntityManager() throws Exception {
        DataDomain domain = Configuration.getSharedConfiguration().getDomain();
        DataNode node = domain.getNode(configurationProperties.getProperty("cayenne.nodeName"));
        connection = new DatabaseConnection(node.getDataSource().getConnection());


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
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("customer-dataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
    }

    @AfterClass
    public static void closeEntityManager() throws SQLException, DatabaseUnitException {
        DatabaseOperation.DELETE.execute(connection, dataset);
        connection.close();
    }

    /**
     * Test of setVehicletype method, of class Vehicle.
     */
    @Test
    public void GetCustomerById() {
        System.out.println("\nGetting an Natural Person by ID.\n");
        Customer c = em.find(Customer.class, 1000L);
        System.out.println("Object loaded: \n" + c);
        assertNotNull(c.getPerson());
    }

    /**
     * Test of setVehicletype method, of class Vehicle.
     */
    @Test
    public void GetCustomerLookUp() {
        System.out.println("\nGetting all Customer using a lookup class .\n");
        List<CustomerLookUp> result =
                em.createQuery("SELECT new park.model.orm.dto.CustomerLookUp(c.id, p.name) FROM Customer c JOIN c.person p", CustomerLookUp.class).getResultList();
        System.out.println("Object loaded: \n" + result);
        assertEquals("Should have 2 customers", result.size(), 2);
    }

    @Test
    public void findAll() throws Exception {

        // Gets all the objects from the database
        Query query = em.createNamedQuery("Customer.findAll");
        assertEquals("Should have 2 customers", query.getResultList().size(), 2);

        // Creates a new object and persists it
        //Customer c = new Customer(1002, 3);
        Customer c = new Customer();
        LegalEntity lP = em.find(LegalEntity.class, 1003L);
        System.out.println("Foreign Ket Object loaded: \n" + lP);
        c.setPerson(lP); //Setting the class attribute will need manual set of customer.id?
        //c.setId(lP.getId());
        c.setPaymentDay(3);
        tx.begin();
        em.persist(c);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 3 customers", query.getResultList().size(), 3);

        // Removes the object from the database
        tx.begin();
        em.remove(c);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 2 customers", query.getResultList().size(), 2);
    }
}
