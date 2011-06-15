First:
svn checkout http://construtor.googlecode.com/svn/trunk/park-samples park-samples

Project structure:
park-samples: parente pom --> we are using maven project inheritance
|_ docs (park modeling)
|_ park-cayenne: 
			sub-project --> we use the tag <parent> pointing to park-samples
			park-cayenne (park click application example - using CAYENNE directly in Click Page Controler)
|_ park-jpa:
			sub-project --> we use the tag <parent> pointing to park-samples
			park-jpa (park click application example - using JPA directly in Click Page Controler)
|
|_ park-multi-module:parente pom --> we are using maven inheritance and aggregation
			sub-project --> we use the tag <parent> pointing to park-samples and add the <modules> tag to manage the core and web modules
|_ __core:sub-project --> we use the tag <parent> pointing to park-multi-module
|_ __web:sub-project --> we use the tag <parent> pointing to park-multi-module

Note:
- PARK-* --> they independents projects
- to know about each one, go to the sub-folder and read the README.txt file.
