import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * The class for counting characters from a file on disk.
 */
public class FileCounter
{
    /**
     * The path to the file.
     */
    private static String pathToFile;
    /**
     * Runs the file counter.
     */
    public static void main()
    {
        File file = new File(pathToFile);
        Scanner inputFile = null;
        try {
            inputFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
            System.exit(1);
        }
        StringBuilder dataIn1Line = new StringBuilder();

        while (inputFile.hasNextLine())
            dataIn1Line.append(inputFile.nextLine());

        String input = String.valueOf(dataIn1Line);
        testForNoData(input);
        long[] numsOfChars = Backend.charCounter(input);

        System.out.println(Backend.outputMessage(numsOfChars));
    }
    /**
     * Tests if the inputted data is empty.
     * @param input The inputted data.
     */
    public static void testForNoData(String input)
    {
        if (input.length() == 0)
        {
            System.out.println("Error: File either contains no data, or is unreadable.");
            System.exit(1);
        }
    }
    /**
     * Setter for <code>pathToFile</code>.
     * @param pathToFile The path to the file.
     */
    public static void setPathToFile(String pathToFile)
    {
        FileCounter.pathToFile = pathToFile;
    }
}
