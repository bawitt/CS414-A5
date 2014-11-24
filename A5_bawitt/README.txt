A5 - Benjamin Witt

External jar dependencies : 
	a5server-bawitt.jar
	a5client-bawitt.jar
	jodatime library is used. It is included in the JARs.

Run from command line :
	-Two JARs listed above are needed to run the program from command line.
	-The path to the JARs between the asterisks will vary depending on location and the port for localhost can change as needed
	-Steps from command line are as follows:
	1. java -jar *path_a5server-bawitt.jar* localhost 8080
	2. java -jar *path_a5client-bawitt.jar* localhost 8080

Usernames, passwords, and default values :
	default username : test
	default password : 1234
	starting number of spaces : 5

Strong/weak/missing : 
I feel like my user interface could be better with use of more complex swing design. This was my first experience with swing and I focused mostly on making it work and not on being fancy.  I spent a lot of time on error checking and making sure that erroneous input processing is avoided so I feel like this is a strength. If I had more time, I would have liked to link the program to a database.  This is also something I am not too familiar with but I have been working on a project at work with C# and MS SQL Server so with this experience, I could probably make a solid attempt at it now.

Patterns/Refactorings:
I attempted to use the facade pattern during development. Refactorings included creating interfaces, reorganizing class structure, renaming classes/methods/variables, and separating the UI from the design.
