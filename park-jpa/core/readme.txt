Until now it works with H2.
I've not tested with other database, but I've setup other profiles with PostgreSQL and Oracle.

1-edit the following pom.xml properties(the actual value is mine):

<jdbc.url>jdbc:h2:~/bin/databases/h2/db</jdbc.url>

2-first create the database, invoke 'mvn test' with skipDataBaseCreation=false:

mvn clean  -DskipDataBaseCreation=false test

3-after that, only use skipDataBaseCreation=false when making some change on sql files:
--database objects
src/test/resources/sql/park.tab_en_us.sql
--constraints
src/test/resources/sql/park.con_en_us.sql
--load for tests
src/test/resources/sql/park.init.sql


More information:
--Original UML diagram
docs/03000001.png
--My DER diagram
docs/park-der.pdf
--Power Architect project file
docs/park.architect