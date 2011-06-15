First:
svn checkout http://construtor.googlecode.com/svn/trunk/park-samples park-samples

and

cd park-jpa


Database

Until now it works with H2 and Postgresql.
H2 is the default one.
To adjust the database parameters edit the following park-jpa/pom.xml properties(the actual value is mine) if you want to change the H2 server type:

<jdbc.url>jdbc:h2:file:data/parkDB;create=true;MODE=PostgreSQL;DB_CLOSE_DELAY=-1</jdbc.url>

JPA

I've setup it to work with EclipseLink and OpenJpa. Use the -P parameter to change it.
EclipseLink is the default one.
Before do a test you need to create the database:

mvn sql:execute -Peclipselink,sqlmaven
Here we are using the sql maven plugin to create the database schema, but you can use the JPA provider to do that work(see below).

Now you can test (into park-jpa/):

mvn test

To change the way the jpa engine drop/create the database object you need to edit the following park-jpa/pom.xml properties(the actual value is mine):

openjpa:
    <jpa.ddl_value>buildSchema(SchemaAction='drop,add',primaryKeys=true,foreignKeys=true,indexes=true)</jpa.ddl_value>
or
eclipselink:
    <jpa.ddl_value>create-tables</jpa.ddl_value>

You can change the properties through the command line:

mvn clean -Ph2,eclipselink install -Djpa.ddl_value=NONE

To know more take a look at the park-jpa/pom.xml profiles.

More information:
--Original UML diagram
park-samples/docs/03000001.png
--My DER diagram
park-samples/docs/power.architect/park-der.pdf
--Power Architect project file
park-samples/docs/park.architect
