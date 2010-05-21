package park.model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

public class LegalEntityTest {

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
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("legal-entity-dataset.xml"));
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
    public void GetLegalEntityById() {
        log.debug("\nGetting an Legal Person by ID.\n");
        LegalEntity lP = em.find(LegalEntity.class, 1002L);
        log.debug("Object loaded: \n" + lP);
        assertNotNull(lP.getName());
    }
    @Test
    public void GetLegalEntityByName() {
        log.debug("\nGetting an Legal Entity by name.\n");
        // Gets all the objects from the database
        Query query = em.createNamedQuery("LegalEntity.findByName");
        query.setParameter("name", "%1002%");
        List list = query.getResultList();
        log.debug("Object loaded: \n" + list);
        assertEquals("Should have 1 LegalEntity", list.size(), 1);
    }

    @Test
    public void findAll() throws Exception {

        // Gets all the objects from the database
        Query query = em.createNamedQuery("LegalEntity.findAll");
        assertEquals("Should have 2 LegalEntity", query.getResultList().size(), 2);

        // Creates a new object and persists it
        LegalEntity lP = new LegalEntity("LegalEntity", "address", new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1974"), "TAXPAYERS100");
        tx.begin();
        em.persist(lP);
        tx.commit();
                
        // Gets all the objects from the database
        assertEquals("Should have 3 LegalEntity", query.getResultList().size(), 3);

        // Creates a new one object with default constructor and persists it
        LegalEntity lP2 = new LegalEntity();
        lP2.setName("LegalEntity2");
        lP2.setAddress("address2");
        lP2.setDtFoundation(new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1974"));
        lP2.setTaxpayersId("TAXPAYERS1002");
        tx.begin();
        em.persist(lP2);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 4 LegalEntity", query.getResultList().size(), 4);

        // Removes the object from the database
        tx.begin();
        em.remove(lP);
        em.remove(lP2);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 2 LegalEntity", query.getResultList().size(), 2);
    }
}
