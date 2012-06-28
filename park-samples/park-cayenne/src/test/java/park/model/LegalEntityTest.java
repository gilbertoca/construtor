package park.model;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Properties;
import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.QueryResponse;
import org.apache.cayenne.access.DataDomain;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.EJBQLQuery;
import org.apache.cayenne.query.NamedQuery;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LegalEntityTest {

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
        LegalEntity lP = Cayenne.objectForPK(runtime.getContext(), LegalEntity.class, 1002L);
        System.out.println("Object loaded: \n" + lP);
        assertNotNull(lP.getName());
    }

    @Test
    public void DeleteById() {
        System.out.println("\n///// deleteObjects ////Getting an Legal Person by ID.\n");
        //LegalEntity lP = em.find(LegalEntity.class, 1010L);
        ObjectContext context = runtime.getContext();
        LegalEntity lP = Cayenne.objectForPK(context, LegalEntity.class, 1010L);
        System.out.println("Object loaded: \n" + lP);
        assertNotNull(lP.getName());
        context.deleteObjects(lP);
        context.commitChanges();
        lP = Cayenne.objectForPK(context, LegalEntity.class, 1010L);
        assertNull(lP);
    }
    
    @Test
    public void NamedDeleteByIdEJBQLQuery() {
        System.out.println("\n///// EJBQLQuery ////Deleting Legal Entity by ID.\n");
        //JPA-->Query query = em.createNamedQuery("LegalEntity.deleteById");
        //JPA-->query.setParameter("id", 1010L);
        //JPA-->int del = 0;
        //JPA-->del = query.executeUpdate();
        
        ObjectContext context = runtime.getContext();
        //NamedQuery query = new NamedQuery("LegalEntity.deleteByIdEJBQL", Collections.singletonMap("id", new Long[]{1010L}));        
        EJBQLQuery query = new EJBQLQuery("delete from LegalEntity l where l.id = :id");
        query.setParameter("id", 1010L);
        QueryResponse result = context.performGenericQuery(query);
        context.commitChanges();
        
        int[] count = result.firstUpdateCount();
        
        assertTrue(count.length > 0);
    }
    @Test
    public void NamedDeleteByIdNamedQuery() {
        System.out.println("\n///// NamedQuery //// Deleting Legal Entity by ID.\n");
        //JPA-->Query query = em.createNamedQuery("LegalEntity.deleteById");
        //JPA-->query.setParameter("id", 1010L);
        //JPA-->int del = 0;
        //JPA-->del = query.executeUpdate();
        
        ObjectContext context = runtime.getContext();
        String[] keys = new String[]{"id"};
        Object[] values = new Long[]{1010L};
        NamedQuery query = new NamedQuery("LegalEntity.deleteById", keys, values);
        
        QueryResponse result = context.performGenericQuery(query);
        context.commitChanges();
        
        int[] count = result.firstUpdateCount();
        
        assertTrue(count.length > 0);
    }    
}
