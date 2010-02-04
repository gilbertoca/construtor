This archetype allows you to generate a template for a web application that uses Click, based on the sample web application.

------------------------------------------
Getting and building the archetype locally
------------------------------------------
Download the Click archetype source.
Navigate to the root folder and install the archetype locally with the following command:

		mvn install
	
(This supposes that maven 2.x is already installed in your system)

------------------------------------------
Creating a template for a web application
------------------------------------------
Execute the following command to create a template that uses Struts:

		mvn archetype:create 	-DarchetypeGroupId=maven-archetype-click
													-DarchetypeArtifactId=maven-archetype-Click	\ 
  												-DarchetypeVersion=1.0-SNAPSHOT								\
  												-DgroupId=<my.groupid> 												\
  												-DartifactId=<my-artifactId>

<my.groupId> and <my-artifactId> pertains to the information about the new project you want to create.

------------------------------------------
Running the sample webapp
------------------------------------------
The sample webapp allows the user to enter his name to display a customized "Hello" greeting.

Run it using the command:

		mvn jetty:run
		
To try the sample webapp, point your browser to http://localhost:8080.