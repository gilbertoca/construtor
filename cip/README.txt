First:
svn checkout http://construtor.googlecode.com/svn/trunk/cip cip

Project structure(click-integration-project):

cip (you are here)
|
------orm (click orm integration)
|
------shiro (click shiro integration - using orm integration)
|
------park (park click application example - using orm[JPA])
|
------park-cayenne (park click application example - using orm[CAYENNE])
|
------rolesecurity (role security example - using shiro integration[JPA])
|
------docs (park modeling)

Note:
- to work with the examples you need to install orm;
- to know about each one, go to the sub-folder and read the README.txt file.
