Until now it works with H2.
I've not tested with other database, but I've setup other profiles with PostgreSQL and Oracle.
I've setup it to work with EclipseLink and OpenJpa. Use the -P parameter to change it. EclipseLink is the default one.
Let's go:

1-edit the following park-jpa/pom.xml properties(the actual value is mine) if you want to change the H2 server type:

<jdbc.url>jdbc:h2:parkDB;create=true;MODE=PostgreSQL;DB_CLOSE_DELAY=-1</jdbc.url>

2-first create the database schema(just tested with H2 and PostgreSQL), invoke 'mvn -PinitSchema compile':

mvn -PinitSchema compile

After that, only use 'mvn -PinitSchema compile' when initializing the database again.

4-now you can test:

mvn test


More information:
--Original UML diagram
docs/03000001.png
--My DER diagram
docs/park-der.pdf
--Power Architect project file
docs/park.architect