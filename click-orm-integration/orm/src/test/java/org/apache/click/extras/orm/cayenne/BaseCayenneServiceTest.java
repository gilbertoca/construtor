/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.click.extras.orm.cayenne;

import org.apache.cayenne.access.DataDomain;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.conf.Configuration;
import org.apache.click.extras.security.cayenne.domain.User;
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
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BaseCayenneServiceTest {

    private static BaseCayenneService<User, Long> userService;
    private static IDatabaseConnection connection;
    private static IDataSet dataset;

    @BeforeClass
    public static void initDataContext() throws Exception {
        userService = new BaseCayenneService<User, Long>(User.class);

        // Initializes DBUnit
        // I presume you've set the src/test/resources/jdbc.properties
        Properties configurationProperties = new Properties();
        configurationProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));

        DataDomain domain = Configuration.getSharedConfiguration().getDomain();
        DataNode node = domain.getNode(configurationProperties.getProperty("cayenne.nodeName"));
        connection = new DatabaseConnection(node.getDataSource().getConnection());

        // http://dbunit.sourceforge.net/faq.html#typefactory
        DatabaseConfig config = connection.getConfig();
        //How to get new instance of H2DataTypeFactory|OracleDataTypeFactory|PostgresqlDataTypeFactory
        IDataTypeFactory dataTypeFactory = (IDataTypeFactory) Class.forName(configurationProperties.getProperty("dbunit.dataTypeFactoryName")).newInstance();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, dataTypeFactory);
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("shiro-user-dataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
    }

    @AfterClass
    public static void closeDataContext() throws SQLException, DatabaseUnitException {
        //userService.getDataContext().clear?
        //before we close the connection
        DatabaseOperation.DELETE.execute(connection, dataset);
        connection.close();
    }

    @Test
    public void insertFindUpdateDelete() throws Exception {

        User user = new User();
        user.setUsername("gilberto@company.com");
        user.setPassword("12345");
        user.setEmail("gilberto@company.com");
        userService.insert(user);

        // Gets all the objects from the database
        assertEquals("Should have 5 User", 4, userService.getAll().size());

        // Removes the object from the database
        userService.delete(user);
        // Gets all the objects from the database
        assertEquals("Should have 4 User", 3, userService.getAll().size());
    }
}
