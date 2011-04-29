This archetype allows you to generate a template for a web application that uses Apache Click, based on the sample web application.

------------------------------------------
Getting and building the archetype locally
------------------------------------------
Download the Maven Archetype Click source:
svn checkout http://construtor.googlecode.com/svn/trunk/maven-archetype-click mac

Navigate to the root folder and install the archetype locally with the following command:
mvn install
cd mac
mvn install
	
(This supposes that maven 2.x is already installed in your system)

------------------------------------------
Creating a template for a web application
------------------------------------------
Use eclipse or netbeans and create a project(ie. starweb) using that archetype or execute the
following command to create a template that uses Apache Click:

cd tmp
mvn archetype:generate
(Now you need to choose the maven archetype click installed locally)
.
.
.
384: local -> maven-archetype-click (An archetype which generates a simplifed sample of the Apache Click Framework.)
Choose a number: 104: 384
Define value for property 'groupId': : company.com
Define value for property 'artifactId': : starweb
Define value for property 'version':  1.0-SNAPSHOT: :
Define value for property 'package':  company.com: :
Confirm properties configuration:
groupId: company.com
artifactId: starweb
version: 1.0-SNAPSHOT
package: company.com
 Y: :
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Old (1.x) Archetype: maven-archetype-click:1.0
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: groupId, Value: company.com
[INFO] Parameter: packageName, Value: company.com
[INFO] Parameter: package, Value: company.com
[INFO] Parameter: artifactId, Value: starweb
[INFO] Parameter: basedir, Value: /home/gilberto/dev/netbeans-config
[INFO] Parameter: version, Value: 1.0-SNAPSHOT


------------------------------------------
Running the sample webapp
------------------------------------------

After that you can move to the new project(starweb), test or run it:
cd starweb/
mvn jetty:run

To try the sample webapp, point your browser to http://localhost:8080.

or

mvn test


