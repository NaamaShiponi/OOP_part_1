import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

/**
 CallableFoNumOfLines is a class that implements the Callable interface and is used to count the number of lines in a given file.
 @author Ben Dabush and Naana Shiponi
 @version 1.0.0 January 12, 2023
 */
public class CallableFoNumOfLines implements Callable<Integer> {
    private String fileNames;

    /**
     * Constructor for the class
     * @param fileNames name of the file to count lines
     */
    public CallableFoNumOfLines(String fileNames){
        this.fileNames=fileNames;
    }

    /**
     * The method that counts the number of lines in the file and returns the count
     * @return count of lines in the file
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        Path path = Paths.get(this.fileNames);
        int count=0;
        try {
            count += Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}