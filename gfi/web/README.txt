GFI - Financial Management and Inventory

There are two modules in this project. The first is "core" and is meant to 
contain Services(rules of business, including mechanism of persistence). The second is "web",
any file related to web layer. Using a modular design is recommended
when you plan to use the module "core" in multiple applications, or if plans
have multiple customers for the same backend (core).

To start, please complete the following steps:

Set up your environment for development:

1. Download and install JDK 5 + (make sure the environment variable JAVA_HOME points to JDK, not JRE).
2. Download and install the database http://db.apache.org/derby/ Derby, then
start it in client/server mode (/bin/linux startNetworkServer.sh, /bin/startNetworkServer.bat win32)
Attention:
-- Launching the database in the specified directory will create the database in that directory.
-- An alternative is to switch to a directory you want to store the database and begin the process
that directory. Example:
gilberto@sugep-dmasi15:~> cd bin/databases/
gilberto@sugep-dmasi15:~/bin/databases> ln -s /home/gilberto/bin/db-derby-10.3.1.4-bin/bin/startNetworkServer start
gilberto@sugep-dmasi15:~/bin/databases> ./start
Security manager installed using the Basic server security policy.
Apache Derby Network Server - 10.3.1.4 - (561794) started and ready to accept connections on port 1527 at 

-- The more practical in my opnion is the definition/specification of a system property (derby.system.home)
which indicates the location of the database or databases. Example:
- Dderby.system.home =/home/user/bin/database
3. Download and install Maven 2.0.9 +.

Running the application:

1. In an area/directory intended for development (/home/user/dev - Linux, C:\Dev - win32),
get the project through a control of versions - subversion, example: tortoiseSVN - client,
example using the command prompt: svn checkout http://construtor.googlecode.com/svn/trunk/gfi gfi

2. In the command prompt, cd into gfi/core directory and run "mvn sql:execute". Executing
that goal (target) will create the database with the preliminary objects  and should be executed
only to initiate the project or creating a new object in the database.

3. After, run "mvn test." Getting confirmation of success at the execution, proceed with the installation of artifact (jar): mvn install.

4. In the command prompt, cd into gfi/web directory and run "mvn jetty:run". Now you can
access the application via the address http://localhost:8080/gfi-web/, more specifically
http://localhost:8080/gfi-web/table-unidade-medida.htm

5. More information can be found at:

     http://code.google.com/p/construtor/