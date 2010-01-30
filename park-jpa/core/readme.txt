Until now it works with H2.
I've not tested with other database, but I've setup other profiles with PostgreSQL and Oracle.

1-edit the following park-jpa/pom.xml properties(the actual value is mine):

<jdbc.url>jdbc:h2:parkDB;create=true;MODE=PostgreSQL;DB_CLOSE_DELAY=-1</jdbc.url>

2-first create the database schema(just tested with H2 and PostgreSQL), invoke 'mvn -PinitSchema compile':

mvn -PinitSchema compile

3-after that, only use 'mvn -PinitSchema compile' when initializing the database again


More information:
--Original UML diagram
docs/03000001.png
--My DER diagram
docs/park-der.pdf
--Power Architect project file
docs/park.architect