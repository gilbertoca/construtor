package park.model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
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
        connection = new DatabaseConnection(em.unwrap(java.sql.Connection.class));

        // http://dbunit.sourceforge.net/faq.html#typefactory
        DatabaseConfig dbConfig = connection.getConfig();
        dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());

        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("legal-entity-dataset.xml"));
    }

    @AfterClass
    public static void closeEntityManager() throws SQLException {
        em.close();
        emf.close();
        connection.close();
    }

    @Before
    public void initTransaction() {
        tx = em.getTransaction();
    }

    @Before
    public void cleanDB() throws Exception {
        // Cleans the database with DbUnit
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
    }

    /**
     * Test of setVehicletype method, of class Vehicle.
     */
    @Test
    public void GetLegalEntityById() {
        log.debug("\nGetting an Legal Person by ID.\n");
        LegalEntity lP = em.find(LegalEntity.class, 1002);
        log.debug("Object loaded: \n" + lP);
        assertNotNull(lP.getName());
    }

    @Test
    public void findAll() throws Exception {

        // Gets all the objects from the database
        Query query = em.createNamedQuery(LegalEntity.FIND_ALL);
        assertEquals("Should have 3 LegalEntity", query.getResultList().size(), 3);

        // Creates a new object and persists it
        LegalEntity lP = new LegalEntity("LegalEntity", "address", new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1974"), "TAXPAYERS100");
        tx.begin();
        em.persist(lP);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 4 LegalEntity", query.getResultList().size(), 4);

        // Removes the object from the database
        tx.begin();
        em.remove(lP);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 3 LegalEntity", query.getResultList().size(), 3);
    }
}
