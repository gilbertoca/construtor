First:
svn checkout http://construtor.googlecode.com/svn/trunk/cip cip

Project structure(click-integration-project):

cip (you are here)
|
------orm (trying to integrate the ORM's in the Apache Click)
|
------shiro (click shiro integration - using orm integration)
|
------park-jpa (park click application example - using JPA directly in Click Page Controler)
|
------park-cayenne (park click application example - using CAYENNE directly in Click Page Controler)
|
------rolesecurity (role security example - using shiro integration[orm JPA])
|
------docs (park modeling)

Note:
- to work with the shiro example you need to install orm;
- PARK-* --> they independents projects
- to know about each one, go to the sub-folder and read the README.txt file.
