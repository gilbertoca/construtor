package park.model;

import org.apache.cayenne.access.DataDomain;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.configuration.server.ServerRuntime;
import java.sql.SQLException;
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

public class NaturalPersonTest {
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
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("natural-person-dataset.xml"));
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
     * Test of getNaturalPerson method, of class NaturalPerson.
     */
/*    @Test
    public void getNaturalPersonById() {
        System.out.println("\nGetting an Natural Person by ID.\n");
        NaturalPerson nP = em.find(NaturalPerson.class, 1000L);
        System.out.println("Object loaded: \n" + nP);
        assertNotNull(nP.getName());
    }

    @Test
    public void findByQuery() {
        TypedQuery<String> query = em.createQuery("SELECT n.name, n.address FROM NaturalPerson AS n", String.class);
        //query.setHint(QueryHints.RESULT_TYPE,ResultType.Map);
        List<String> results = query.getResultList();
        System.out.println("Object loaded: \n" + results);
    }

    @Test
    public void findAll() throws Exception {

        // Gets all the objects from the database
        Query query = em.createNamedQuery("NaturalPerson.findAll");
        assertEquals("Should have 3 natural persons", query.getResultList().size(), 3);

        // Creates a new object and persists it
        NaturalPerson nP = new NaturalPerson("NATURAL_PERSON1005", "ADDRESS1005", new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1974"), "LEGAL_DOCUMENT1005");
        tx.begin();
        em.persist(nP);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 4 natural persons", query.getResultList().size(), 4);

        // Removes the object from the database
        tx.begin();
        em.remove(nP);
        tx.commit();

        // Gets all the objects from the database
        assertEquals("Should have 3 natural persons", query.getResultList().size(), 3);
    }
    */
}
