First:
svn checkout http://construtor.googlecode.com/svn/trunk/swing-labs/lab01-java-io lab01-java-io

Project structure:
swing-labs: parente pom --> we are using maven project inheritance
|_ docs (swing docs)
|_ lab01-java-io(WE ARE HERE): 
			sub-project --> we use the tag <parent> pointing to swing-labs
			lab01-java-io (how to read and write to/from text files)
