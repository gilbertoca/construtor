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

public class NaturalPersonTest {

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

        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("natural-person-dataset.xml"));
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
    public void GetNaturalPersonById() {
        log.debug("\nGetting an Natural Person by ID.\n");
        NaturalPerson nP = em.find(NaturalPerson.class, 1000);
        log.debug("Object loaded: \n" + nP);
        assertNotNull(nP.getName());
    }

    @Test
    public void findAll() throws Exception {

        // Gets all the objects from the database
        Query query = em.createNamedQuery(NaturalPerson.FIND_ALL);
        assertEquals("Should have 2 artists", query.getResultList().size(), 2);

        // Creates a new object and persists it
        NaturalPerson nP = new NaturalPerson("NATURAL_PERSON1005", "ADDRESS1005", new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1974"), "LEGAL_DOCUMENT1005");
        tx.begin();
        em.persist(nP);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 3 artists", query.getResultList().size(), 3);

        // Removes the object from the database
        tx.begin();
        em.remove(nP);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 2 artists", query.getResultList().size(), 2);
    }
}
