First:

svn checkout http://construtor.googlecode.com/svn/trunk/park-jpa park-jpa

and

cd park-jpa/core


Database

This module represents the service(database access and bussines logic) layer, until now it works with H2 and Postgresql.
I've not tested with other database, but I've setup other Oracle profile.
H2 is the default one.

To adjust the database parameters edit the following park-jpa/pom.xml properties(the actual value is mine) if you want to change the H2 server type:

<jdbc.url>jdbc:h2:../parkDB;create=true;MODE=PostgreSQL;DB_CLOSE_DELAY=-1</jdbc.url>


JPA

I've setup it to work with EclipseLink and OpenJpa. Use the -P parameter to change it.
EclipseLink is the default one.



Now you can test (into park-jpa/core):

mvn test

To change the way the jpa engine drop/create the database object you need to edit the following park-jpa/core/pom.xml properties(the actual value is mine):

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
