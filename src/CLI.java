import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * The class for the command-line interface.
 */
public class CLI
{
    /**
     * Defines the <code>Scanner</code> variable for this class.
     */
    private static final Scanner keyboard = new Scanner(System.in);
    /**
     * Asks the user for the input to count.
     * @return The prompted input.
     */
    public static String displayInput()
    {
        System.out.println("Welcome to Character Counter version " + Backend.getProgramVersion() + "\nEnter the input to count, or type ':quit' to exit");
        return keyboard.nextLine();
    }
    /**
     * Displays help.
     */
    public static void displayHelp()
    {
        System.out.println("Commands:\n");
        System.out.println("\t-h, --help\t\t\tDisplays this message.\n");
        System.out.println("\t--allvals\t\t\tDisplay empty values in the output\n");
        System.out.println("\t--file \"/path/to/file.txt\"\tCounts the characters from a file rather than from your input.\n");
        System.out.println("\t--cmd, --cli\t\t\tForce the command-line to run. Recommended if using these commands.\n");
        System.out.println("\t--gui, --ui\t\t\tForce the GUI to run. WARNING: The program will crash if you do this in a headless environment.\n");
        System.exit(0);
    }
    /**
     * Displays when the user provides no input.
     */
    public static void noInputError()
    {
        System.out.println("Error: You gave no input. Please give an input and try again");
    }
    /**
     * Displays when the user provides a file that does not contain text.
     */
    public static void invalidInputError()
    {
        System.out.println("Error: File either contains no data, or is unreadable.");
    }
    /**
     * Displays when the user provides a file path that doesn't work.
     */
    public static void noFileError()
    {
        System.out.println("Error: I could not find the file you specified.");
    }
    /**
     * Tests if the inputted data is empty.
     * @param input The inputted data.
     */
    public static void testForNoData(String input)
    {
        if (input.length() == 0)
        {
            CLI.invalidInputError();
            System.exit(1);
        }
    }
    /**
     * Outputs a message by taking <code>numsOfChars</code>, and passing it to <code>outputMessage()</code>.
     * @param numsOfChars An array with at least 29 rows that is ordered correctly.
     */
    public static void displayOutput(long[] numsOfChars)
    {
        System.out.println(Backend.outputMessage(numsOfChars));
    }
    /**
     * Runs the CLI by counting from human input.
     */
    public static void hid()
    {
        Config.setRunUI("CLI");

        String input = displayInput();
        String data = Backend.parseData(input);
        if (input.equals(""))
        {
            noInputError();
            System.exit(1);
        }

        long[] numsOfChars = Backend.charCounter(data);

        displayOutput(numsOfChars);
    }
    /**
     * Runs the CLI by counting from a file on disk.
     */
    public static void fileCounter()
    {
        System.out.println("Welcome to Character Counter version " + Backend.getProgramVersion());

        File file = new File(Config.getPathToFile());
        Scanner inputFile = null;
        try {
            inputFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            noFileError();
            System.exit(1);
        }
        StringBuilder dataIn1Line = new StringBuilder();

        while (inputFile.hasNextLine())
            dataIn1Line.append(inputFile.nextLine());

        String input = String.valueOf(dataIn1Line);
        testForNoData(input);
        long[] numsOfChars = Backend.charCounter(input);

        displayOutput(numsOfChars);
        System.exit(0);
    }

}
