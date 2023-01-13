 # OOP_part_1
 

## Overview
This project is a simple Java program that demonstrates the use of different methods for creating text files and counting
the number of lines in them. The program creates a specified number of text files, each containing a random number of lines.
It then counts the total number of lines in all the files using three different methods: a sequential method, a method using threads,
and a method using a thread pool. The program compares the time taken by each method to count the lines and prints the results to the
console. This project can be used as a starting point for more complex text file processing tasks and to compare the time efficiency
of different methods. However, it is important to note that the actual performance will depend on the number of files, resources available
on the machine, and the size of the files. Proper testing and optimization should be done before using the code in production

## Diagram of the classes:
                            ---------------------------------------------------------------------------------
                           |                                                                                 |
                           |                      +---------------------------+                              |
                           |                      |    Ex2_1                  |                              |
                           |                      +---------------------------+                              |
                           |                      |                           |                              |
                           |                      +---------------------------+                              |
                           |                      | +createTextFiles()        |                              |  
                           |                      | +getNumOfLines()          |                              |
                           |                      | +getNumOfLinesThreads()   |                              |  
                           |                      | +getNumOfLinesThreadPool()|                              |
                           |                      +---------------------------+                              |
                           |                           /               \                                     |
                           |                          /                 \                                    |
                           |              +-------------------+    +--------------------+                    |
                           |              |ThreadsFoNumOfLines|    |CallableFoNumOfLines|                    |
                           |              +-------------------+    +--------------------+                    |
                           |              | -fileName:String  |    | -fileName:String   |                    |
                           |              |                   |    |                    |                    |
                           |              +-------------------+    +--------------------+                    |
                           |              | +run()            |    | +call()            |                    |
                           |              +-------------------+    +--------------------+                    |
                           |                                                                                 |
                            ---------------------------------------------------------------------------------

## Code Structure
The project consists of three Java classes:

1. Ex2_1.java: This class contains the main method and the following helper methods:

createTextFiles(int n, int seed, int bound): Creates n text files named "file_0.txt" to "file_n-1.txt".
Each file will contain a random number of lines, where each line contains the text "World Hello".

getNumOfLines(String[] fileNames): Counts the number of lines in all the text files created in a sequential manner and returns the total count.
It also prints the time taken to count the lines.

getNumOfLinesThreads(String[] fileNames): Counts the number of lines in all the text files created using threads and returns the total count.
It also prints the time taken to count the lines.

getNumOfLinesThreadPool(String[] fileNames): Counts the number of lines in all the text files created using
thread pool and returns the total count. It also prints the time taken to count the lines.

2. CallableFoNumOfLines.java: This class is used in the getNumOfLinesThreadPool method in Ex2_1.java.

It implements the Callable interface and counts the number of lines in a single text file in a threaded manner.
It takes in a single parameter in its constructor, a String representing the name of the file whose number of lines are to be counted.

3. ThreadsFoNumOfLines.java: This class is used in the getNumOfLinesThreads method in Ex2_1.java. It extends the Thread class and counts the number

of lines in a single text file in a threaded manner. It takes in a single parameter in its constructor, a String representing the name of the file whose
number of lines are to be counted.

## Conclusion
In this project, we have created text files and counted the number of lines in them using three different methods, getNumOfLines,
getNumOfLinesThreads and getNumOfLinesThreadPool. We will assume before running the tests that getNumOfLinesThreadPool method is more efficient
than getNumOfLinesThreads method and both are more efficient than the getNumOfLines method.
but to our surprise we received that getNumOfLinesThreadPool and getNumOfLinesThreads were about the same time,


                                               
getNumOfLines time: 118587220                  
                                               
getNumOfLinesThreads time: 957879700           
                                               
getNumOfLinesThreadPool time: 740942400        
                                               

this is due to the fact that we defined the amount of threads in getNumOfLinesThreadPool to be the number of files and because creating a thread takes a lot of resources
There was not an improvement in times, but if we divide the number of threads by 20 in getNumOfLinesThreadPool we will get that the results are as what we expected at the beginning.

                                               
getNumOfLines time: 1818281700                 
                                               
getNumOfLinesThreads time: 1187790800          
                                               
getNumOfLinesThreadPool time: 387295100        
                                               

This project can be used as a starting point for more complex text fileprocessing tasks and to compare the time efficiency of different methods.

                            

                                             
                                              
                                      

