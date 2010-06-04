This archetype allows you to generate a template for a web application that uses Apache Click, based on the sample web application.

------------------------------------------
Getting and building the archetype locally
------------------------------------------
Download the Apache Click Archetype source:
svn checkout http://construtor.googlecode.com/svn/trunk/maven-archetype-click mac

Navigate to the root folder and install the archetype locally with the following command:
mvn install
cd mac
mvn install
	
(This supposes that maven 2.x is already installed in your system)

------------------------------------------
Creating a template for a web application
------------------------------------------
Use eclipse or netbeans and create a project using that archetype or execute the following command to create a template that uses Apache Click:

cd tmp
mvn archetype:generate -DarchetypeArtifactId=maven-archetype-click

------------------------------------------
Running the sample webapp
------------------------------------------

After that you can move to the new project, test or run it:
mvn jetty:run
mvn test


To try the sample webapp, point your browser to http://localhost:8080.