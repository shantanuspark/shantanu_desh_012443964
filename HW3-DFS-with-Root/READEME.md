
This README file explains how to use the program "To build a DFS spanning tree"
You have several options for running it...

---- RUN IN AN IDE ----

If you want to run the examples in an IDE, such as Eclipse, you should be able to import the entire the project as an existing project.
Once the import is successfull, go to run configuration and set a program argument(integer between 0-5) which defines the node to be used as root.


---- COMPILE AND RUN ON THE COMMAND LINE ----

To compile the program, navigate to the "DC_HW01\src\edu\dt" directory in the terminal or command prompt.
Run the command 

				javac *.java
				
This will compile the program and generate the class files.
Now, Navigate back to the src directory "DC_HW01\src" and run the command

				java java edu.dt.Main 0
				
Remeber to enter the command line argument, it is an integer between 0-5, which defines the node to be used as root.


---- COMPILE AND RUN ON THE COMMAND LINE ----

####### Initial Graph #######
Processor P2-> P0 P1 P5
Processor P5-> P2 P4
Processor P4-> P1 P5
Processor P0-> P1 P2 P3
Processor P1-> P0 P2 P4
Processor P3-> P0
####### Final Graph #######
Processor P2-> P5
Processor P5-> P4
Processor P4->
Processor P0-> P1 P3
Processor P1-> P2
Processor P3->
