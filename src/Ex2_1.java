import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.io.File; // Import the File class
import java.io.IOException; // Import the IOException class to handle errors
import java.util.concurrent.*;

/**

 Ex2_1 is a class that has 3 methods, createTextFiles(), getNumOfLines(), getNumOfLinesThreads() and getNumOfLinesThreadPool() that are used to create text files, count the number of lines in the files and measuring the time it takes to count the lines.
 @author naama shponi and ben dabush
 @version 1.0.0
 */

public class Ex2_1 {
    /**
     * Creates text files with random number of lines between 0 and bound
     * @param n number of files to create
     * @param seed seed for the random number generator
     * @param bound maximum number of lines in a file
     * @return array of file names
     */
    public static String[] createTextFiles(int n, int seed, int bound){
        String fileNames[]=new String[n];
        try {
            for (int i=0; i<n; i++) {
                fileNames[i] = "file_" + i + ".txt";
                File myObj = new File(fileNames[i]);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        FileWriter myWriter;
        Random rand = new Random(seed);
        for(int i = 0; i < n; i++) {
            int x = rand.nextInt(bound);
            try {
                myWriter = new FileWriter(fileNames[i]);
                for (int j=1;j<=x;j++){
                    myWriter.write("World Hello\n");
                }
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return fileNames;
    }

    /**
     * Counts the number of lines in the files given in fileNames
     * @param fileNames array of file names
     * @return total number of lines in the files
     */
    public static int getNumOfLines(String[] fileNames){
        int sumLines=0;
        long startTime = System.nanoTime();

        for(int i = 0; i < fileNames.length; i++) {
            Path path = Paths.get(fileNames[i]);
            try {

                sumLines += Files.lines(path).count();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("time:"+duration);
        return sumLines;
    }

    /**
     * Counts the number of lines in the files given in fileNames using threads
     * @param fileNames array of file names
     * @return total number of lines in the files
     * @throws InterruptedException
     */
    public static int getNumOfLinesThreads(String[] fileNames) throws InterruptedException {
        int sumLines=0;
        ThreadsFoNumOfLines arrOfThreads[]=new ThreadsFoNumOfLines[fileNames.length];
        long startTime = System.nanoTime();
        for(int i = 0; i < fileNames.length; i++) {
            ThreadsFoNumOfLines getNumLines=new ThreadsFoNumOfLines(fileNames[i]);
            arrOfThreads[i]=getNumLines;
            getNumLines.start();
        }

        for(int i = 0; i < fileNames.length; i++) {
            arrOfThreads[i].join();
            sumLines += arrOfThreads[i].getCountlines();
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("time:"+duration);
        return sumLines;
    }

    /**
     * Counts the number of lines in the files given in fileNames using thread pool
     * @param fileNames array of file names
     * @return total number of lines in the files
     * @throws Exception
     */
    public static int getNumOfLinesThreadPool(String[] fileNames) throws Exception {
        int sumLines=0;
        Future<Integer> futures[] =new Future[fileNames.length];
        ExecutorService executorService= Executors.newFixedThreadPool(fileNames.length/30);
        long startTime = System.nanoTime();

        for(int i = 0; i < fileNames.length; i++) {
            futures[i]= executorService.submit(new CallableFoNumOfLines(fileNames[i]));
        }
        for(int i = 0; i < fileNames.length; i++) {
            sumLines+= futures[i].get();
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("time:"+duration);
        return sumLines;
    }

    public static void main(String[] args) throws Exception {
        int seed = 1, n = 2000, bound = 4000;
        int normalCount,threadsCount,callableCount;
        String fileNames[];
        fileNames = createTextFiles(n,seed,bound);

        normalCount=getNumOfLines(fileNames);
        threadsCount=getNumOfLinesThreads(fileNames);
        callableCount=getNumOfLinesThreadPool(fileNames);

//        System.out.println("normalCount:"+normalCount+" threadsCount:"+threadsCount+" callableCount:"+callableCount);
    }


}

