Programming Project: p1_40354020_172

What is it?
- - - - - - 
This programming project consists of a Java project for implementing, as well as testing for efficiency, four different strategies to construct an intersection of sets from randomly generated data sets given specific parameters. Strategies for intersecting the data sets are the following: P1, P2, P3 and P4. The first two follow a general algorithm scheme to construct the final set. The difference between them being the way Sets are implemented. We will be using Java implemented ArrayList and HashMap, respectively.  For P3 and P4, the HashMap implemented sets are used whenever sets are needed. The construction of the final set is done by counting the frequency of the elements of the sets and adding them to final set whenever it complies with our project’s demands. Each will have its own method for counting. Finally, an efficiency analysis is done with every strategy to determine which one is best for the job.

Getting started
- - - - - - - - 
How to compile and run in Command Prompt? Save the project on your computer.
From the terminal window, navigate to the directory containing your .java file using the cd command. For example,

	cd eclipse-workspace/P1_#########/src

Assuming the file is in the current working directory, type the javac commands below to compile all the packages.

	javac packageName1/*.java packageName2/*.java packageName3/*.java

Now your project should be compiled in the src folder.
Since Eclipse was used, compiling is not necessary since the project is already compiled in the bin folder included.

Once you have your compiled project, the next step is to run it. First go to the directory containg your project. For example,
	
	cd eclipse-workspace/P1_#########

Now, run the project with the following command.
	If your class files are already in the bin folder:

		java –classpath bin packageName.MainClassName 

	If your class files are in the src folder as explained above:

		java –classpath src packageName.MainClassName

Running the tests
- - - - - - - - - 
When running the program, first step is to generate the data that will be used. Folder should already include pre-generated files. If changes are to be made, the command is as follows:
	
	java -classpath ./bin p1MainClasses/FilesGeneratorMain n m size

Where the parameters n, m and size are the number of data generators, number of data sets produced per data generator and the sum of the sizes of all data sets, respectively. The empty parameters defaults in n = 20, m = 50 and size = 50000. The output of this command will be saved to the folder inputFiles inside the main project folder.

To run the main program, the command is the following:
	
	java -classpath ./bin p1MainClasses/Part1Main strategy

Where the parameter strategy is the strategy to use for intersecting of sets. Must range from 1 to 4. Empty parameter defaults in the execution of the four strategies.

The time analysis of the four strategies can be executed by the following command:
	
	java -classpath ./bin p1MainClasses/Part2Main n m isize fsize istep rep

Where the six values following the class name are:
	n – number of companies				fsize – final size for experimentations
	m – number of crime events			istep – increment of sizes
	isize – initial size for experimentations	rep – repetitions of each size		
The output of this command will be saved to the folder experimentalResults inside the main project folder.

 
Name and description of the projects and sub-modules
- - - - - - - - - - - - - - - - - - - - - - - - - - 
p1_40354020_172

package: dataGenerator
	DataGenerator.java – generates the random data used for testing
        DataReader.java – reads the data from the files created by the data generator

package: experimentalClasses 
        ExperimentController.java – calculates the estimated execution time of the strategies
	StrategiesTimeCollection.java - 

package: interfaces 
	IntersectionFinder.java – interface for set intersection finders
	MySet.java – interface for sets

package: mySetImplementations        
        AbstractMySet.java – abstract class for sets
	Set1.java – sets implemented using ArrayList 
	Set2.java – sets implemented using HashMap

package: p1MainClasses
       	FilesGeneratorMain.java – creates de files containing the generated data
	Part1Main.java – main class of the project
	Part2Main.java – main class for testing time efficiency

package: setIntersectionFinders
        AbstractIntersectionFinder.java – abstract class for intersection finders
	P1P2Strategy.java – class implementing strategies P1 and P2 of intersection finders
	P3Strategy.java - class implementing strategies P3 of intersection finders
	P4Strategy.java - class implementing strategies P4 of intersection finders

package: testerClasses
        DataGeneratorTester.java – test for the data generator
	StrategyTester.java – test for the strategies of intersection finders

Built with
- - - - - -
Eclipse – IDE used

Author
- - - 
Jean C. Merced Cádiz

Acknowledgements
- - - - - - - - 
Special thanks to professor Dr. Pedro I. Rivera Vega for providing a significant amount of the code as a guide for this Data Structures project assignment. Also, thanks to laboratory instructor Kelvin Roche for taking from his time to help when needed.






