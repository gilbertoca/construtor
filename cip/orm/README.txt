First:
cd cip/orm

Database

NOTE: We use the shiroDB database as test and implementation example. Those config files (both cayenne and jpa) are in the src/test/resources folder.
      You can use the Cayenne or JPA implementation with your own setup or use the provide one as template.

This module represents the service(database access and bussines logic) layer, until now it works with H2.
To adjust the database parameters edit the following orm/pom.xml properties(the actual value is mine) if you want to change the H2 server type:

<jdbc.url>jdbc:h2:file:data/shiroDB;create=true;MODE=PostgreSQL;DB_CLOSE_DELAY=-1</jdbc.url>
After that you need to generate the database running:

mvn sql:execute,

which will create database objects necessary to both JPA and Cayenne ORM.

JPA

I've setup it to work with EclipseLink.
Now you can test (into cip/orm):

mvn test

To change the way the jpa engine drop/create the database object you need to edit the following cip/orm/pom.xml properties(the actual value is mine):

eclipselink:
    <jpa.ddl_value>NONE</jpa.ddl_value>

You can change the properties through the command line:

mvn clean install -Djpa.ddl_value=create-tables

Cayenne

I've setup the cayenne maven plugin so that we can config it easy: mvn cayenne-modeler:run .
You need change the database access properties and classpath (tools-preferences->local datasources).
The orm/pom.xml properties has no effect on Cayenne since it is configured separately.
