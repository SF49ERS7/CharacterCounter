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
     * Outputs a message by taking the <code>numsOfChars</code> parameter, and passing it to the <code>outputMessage()</code> method.
     * @param numsOfChars An array with at least 29 rows that is ordered correctly.
     */
    public static void displayOutput(long[] numsOfChars)
    {
        System.out.println(Backend.outputMessage(numsOfChars));
    }
    /**
     * Runs the CLI.
     */
    public static void main()
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
}
