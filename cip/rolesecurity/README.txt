First:
cd cip/rolesecurity

Database

NOTE: We use the shiroDB database as test and implementation example. Those config files (jpa) are in the src/test/resources folder.
      You can use the JPA implementation with your own setup or use the provide one as template.

To adjust the database parameters edit the following rolesecurity/pom.xml properties(the actual value is mine) if you want to change the H2 server type:

<jdbc.url>jdbc:h2:file:data/shiroDB;create=true;MODE=PostgreSQL;DB_CLOSE_DELAY=-1</jdbc.url>
After that you need to generate the database running:

mvn sql:execute,

which will create database objects necessary to JPA ORM.
To change the way the jpa engine drop/create the database object you need to edit the following cip/rolesecurity/pom.xml properties(the actual value is mine):

eclipselink:
    <jpa.ddl_value>NONE</jpa.ddl_value>

You can change the properties through the command line:

mvn clean install -Djpa.ddl_value=create-tables

Now you can test (into cip/rolesecurity):

mvn test