import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *  ThreadsFoNumOfLines is a class that extends Thread and is used to count the number of lines in a given file in a separate thread.
 *  @author Ben Dabush and Naana Shiponi
 *  @version 1.0.0 January 12, 2023
 */

public class ThreadsFoNumOfLines extends Thread {
    private String fileNames;
    private int countlines;

    /**
     * Getter for countlines
     * @return count of lines in the file
     */
    public int getCountlines() {
        return countlines;
    }

    /**
     * Constructor for the class
     * @param fileNames name of the file to count lines
     */
    public ThreadsFoNumOfLines(String fileNames){
        this.fileNames=fileNames;
    }

    /**
     * Method that counts the number of lines in the file
     */
    private void CountLinesInOneFile(){
        Path path = Paths.get(this.fileNames);
        int count=0;
        try {
            count += Files.lines(path).count();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.countlines=count;
    }

    /**
     * Overrides the run() method of the Thread class. This method calls the CountLinesInOneFile() method.
     */
    public void run() {
        CountLinesInOneFile();
    }
}