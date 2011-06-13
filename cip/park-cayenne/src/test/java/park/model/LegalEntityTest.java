package park.model;

import org.apache.cayenne.query.NamedQuery;
import org.apache.cayenne.Cayenne;
import org.apache.cayenne.access.DataDomain;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.Query;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import org.apache.cayenne.query.EJBQLQuery;
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
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("legal-entity-dataset.xml"));
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
    public void GetLegalEntityById() {
        System.out.println("\nGetting an Legal Person by ID.\n");
        //LegalEntity lP = em.find(LegalEntity.class, 1002L);
        LegalEntity lP = Cayenne.objectForPK(runtime.getContext(),LegalEntity.class, 1002L);
        System.out.println("Object loaded: \n" + lP);
        assertNotNull(lP.getName());
    }
    @Test
    public void DeleteById() {
        System.out.println("\nDeleting Legal Entity by ID.\n");
        //Query query = em.createNamedQuery("LegalEntity.deleteById");
        //query.setParameter("id", 1010L);
        EJBQLQuery queryObject = new EJBQLQuery("LegalEntity.deleteById");
        queryObject.setParameter(null, queryObject);
        //JPA code
        /*
        tx.begin();
        int del = 0;
        del = query.executeUpdate();
        tx.commit();
        */
        assertEquals(del, 1);
    }
    @Test
    public void GetLegalEntityByName() {
        System.out.println("\nGetting an Legal Entity by name.\n");
        // Gets all the objects from the database
        Query query = em.createNamedQuery("LegalEntity.findByName");
        query.setParameter("name", "%1002%");
        List list = query.getResultList();
        System.out.println("Object loaded: \n" + list);
        assertEquals("Should have 1 LegalEntity", list.size(), 1);
    }

    @Test
    public void findAll() throws Exception {

        // Gets all the objects from the database
        Query query = em.createNamedQuery("LegalEntity.findAll");
        assertEquals("Should have 3 LegalEntity", query.getResultList().size(), 3);

        // Creates a new object and persists it
        LegalEntity lP = new LegalEntity("LegalEntity", "address", new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1974"), "TAXPAYERS100");
        tx.begin();
        em.persist(lP);
        tx.commit();
                
        // Gets all the objects from the database
        assertEquals("Should have 4 LegalEntity", query.getResultList().size(), 4);

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
        assertEquals("Should have 5 LegalEntity", query.getResultList().size(), 5);

        // Removes the object from the database
        tx.begin();
        em.remove(lP);
        em.remove(lP2);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 3 LegalEntity", query.getResultList().size(), 3);
    }
}
