First:
svn checkout http://construtor.googlecode.com/svn/trunk/swing-labs swing-labs

Project structure:
swing-labs: parente pom --> we are using maven project inheritance
|_ docs (swing docs)
|_ lab01-java-io: 
			sub-project --> we use the tag <parent> pointing to swing-labs
			lab01-java-io (how to read and write to/from text files)
|_ lab00-[..]: 
			sub-project --> we use the tag <parent> pointing to swing-labs
			lab02-[...] (how to ...)

Note:
- to know about each one, go to the sub-folder and read the README.txt file.
