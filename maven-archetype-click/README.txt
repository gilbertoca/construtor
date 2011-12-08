This archetype allows you to generate a template for a web application that uses Apache Click, based on the sample web application.

Create a project NOW using the remote repository

 mvn archetype:create -DarchetypeGroupId=com.google.constructor.archetypes \
    -DarchetypeArtifactId=maven-archetype-click \
    -DarchetypeVersion=1.0 \
    -DremoteRepositories=https://construtor.googlecode.com/svn/maven2/ \
    -DgroupId=myGroupId \
    -DartifactId=myArtifactId    

Installing the Archetype locally

Download the maven-archetype-click-1.0.jar file.

Then install it into your local repository:

 mvn install:install-file \
   -DarchetypeGroupId=com.google.constructor.archetypes \
   -DarchetypeArtifactId=maven-archetype-click \
   -DarchetypeVersion=1.0 \
   -Dpackaging=jar 
   -Dfile=PATH_TO_JAR_YOU_DOWNLOADED/maven-archetype-click-1.0.jar


Getting and building the archetype locally

Download the Maven Archetype Click source:
svn checkout http://construtor.googlecode.com/svn/trunk/maven-archetype-click mac

Navigate to the root folder and install the archetype locally with the following command:
mvn install
cd mac
mvn install


Using the Archetype (if it's already installed locally)

Once you have access to the archetype, you use it as you would any other Maven archetype to create a template/stub project.
 
mvn archetype:generate \
   -DarchetypeGroupId=com.google.constructor.archetypes \
   -DarchetypeArtifactId=maven-archetype-click \
   -DarchetypeVersion=1.0 \
   -DgroupId=com.mycompany \
   -DartifactId=myArtifactId \
   -DarchetypeCatalog=local



Running the sample webapp


After that you can move to the new project(starweb), test or run it:
cd starweb/
mvn jetty:run

To try the sample webapp, point your browser to http://localhost:8080.

or

mvn test


