# restaurant-simulator

The given repository contains eight files:

1. Main.java
2. Restaurant.java
3. Diner.java
4. Cook.java
5. Tables.java
6. BurgerMaker.java
7. FriesMaker.java
8. CokeMachine.java

Main.java is the main file of the given project. The threads for each process i.e, for each diner and cook are created in Restaurant.java. A class file has been created for each resource and each process. Diner.java and Cook.java are the files for the processes whereas BurgerMaker.java, FriesMaker.java, CokeMachine.java, and Tables.java are the files for the resources. The Diner and Cook classes contain methods for each and every task performed by them. 

The tasks run by Diner and Cook processes are mentioned below:

Diner:

dinerArrives() - To log the arrival of the diner
tableAssigned() - To assign a table to the diner
orderFood() - To place an oder for the food
waitForFood() - To wait for the food
eatFood - To log the eating time of the diner
leaveRestaurant() - To log the departure of the diner
allDinersDone() - To indicate if all the diners are served

Cook:

selectDiner() - To assign a cook to the diner
makeFood() - To log the usage of different machines by different cooks at the restaurant


When the main file Main.java is executed using the makefile, all the subsequent and required classes and files are initialized. I am giving the input file using the File() module from java.io in Main.java file. For convenience, the output for the given input file is printed on the terminal.

==================================================================================================================================================================================

I am taking sample input file input-3.txt from the given testcases and the following would the output for it:

Sample Input:

2
2
1
1,1,0,0
2,1,1,1

Sample Output:

00:01 - Diner 1 arrives.
00:01 - Diner 1 is seated at table 1.
00:01 - Cook 1 processes Diner 1's order.
00:01 - Cook 1 uses the burger machine.
00:02 - Diner 2 arrives.
00:02 - Diner 2 is seated at table 2.
00:06 - Cook 1 processes Diner 2's order.
00:06 - Cook 1 uses the burger machine.
00:06 - Diner 1's order is ready. Diner 1 starts eating.
00:11 - Cook 1 uses the fries machine.
00:14 - Cook 1 uses the coke machine.
00:15 - Diner 2's order is ready. Diner 2 starts eating.
00:36 - Diner 1 finishes. Diner 1 leaves the restaurant.
00:45 - Diner 2 finishes. Diner 2 leaves the restaurant.
00:45 - The last diner leaves the restaurant.

==================================================================================================================================================================================

Instructions to run the code:

1. The command 'make' can be given in the terminal to initialize and run the project and the command 'make clean' can be given to clean the class files. 
2. If you are running the project in an IDE, use the following commands to get the output printed on the console:
	a. javac *.java
	b. java Main
3. I am hardcoding the location of the input file in Main.java file. So whenever you need to try running for a new test file, you need to change the file location there.
4. As I am hardcoding the location of the test file, it is advised to perform make clean before running each and every testcase.


Along with the code, I am submitting the Makefile, README file, and sample test inputs and the outputs produced by my code.
