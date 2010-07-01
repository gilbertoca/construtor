This module represents the service(database access and bussines logic) layer, until now it works with H2 and Postgresql.

I've not tested with other database, but I've setup other Oracle profile.
I've setup it to work with EclipseLink and OpenJpa. Use the -P parameter to change it. EclipseLink is the default one.
Let's go:

1-edit the following park-jpa/pom.xml properties(the actual value is mine) if you want to change the H2 server type:

<jdbc.url>jdbc:h2:../parkDB;create=true;MODE=PostgreSQL;DB_CLOSE_DELAY=-1</jdbc.url>

2-first create the database schema(just for H2), invoke 'mvn -PinitSchema compile':
#For postgresql or other database, you need to create a database schema before starting.
#I've setup some script files to help on this task. They are in park-jpa/core/src/test/resources/sql.

 mvn -Ph2,eclipselink,initSchema compile

After that, only use 'mvn -Ph2,eclipselink,initSchema compile' when initializing the database again.

4-now you can test:

mvn test

5-to change the way the jpa engine drop/create the database object you need to edit the following park-jpa/core/pom.xml properties(the actual value is mine):

openjpa:
    <jpa.ddl_value>buildSchema(SchemaAction='drop,add',primaryKeys=true,foreignKeys=true,indexes=true)</jpa.ddl_value>
or
eclipselink:
    <jpa.ddl_value>create-tables</jpa.ddl_value>

You can change the properties through the command line:

mvn clean -Ph2,eclipselink install -Djpa.ddl_value=NONE

To know more take a look at the park-jpa/core/pom.xml profiles.

More information:
--Original UML diagram
docs/03000001.png
--My DER diagram
docs/park-der.pdf
--Power Architect project file
docs/park.architect
