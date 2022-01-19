import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * The class for the rarely-used command-line interface.
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
     * Thrown when the user provides no input.
     */
    public static void noInputError()
    {
        System.out.println("Error: You gave no input. Please give an input and try again");
    }
    /**
     * Displays when the file output request fails due to a write-protected disk. Rarely, if ever, used.
     */
    public static void displayFileError()
    {
        System.out.println("Error: Your disk is write-protected, so I cannot send the output to a file");
    }

    /**
     * Outputs a message by taking the <code>numsOfChars</code> parameter, and passing it to the <code>outputMessage()</code> method.
     * @param numsOfChars An array with at least 29 rows that is ordered correctly.
     */
    public static void displayOutput(int[] numsOfChars)
    {
        System.out.println(Backend.outputMessage(numsOfChars));
    }

    /**
     *  Prompts the user if they would like to send the contents of <code>displayOutput</code> to a file on the disk.
     * @param output The data to be written to the disk.
     */
    public static void promptForFileOutput(String output)
    {
        System.out.println("Do you wish to send the previous output to a file? Type 'true' if you do and 'false' if you don't");
        boolean result = false;
        try {
            result = keyboard.nextBoolean();
        } catch (InputMismatchException e) {
            System.out.println("That's not a valid input, so I take that as a no");
        }
        if (result)
            Backend.sendToTextFile(output, false);
    }
    /**
     * Primary method for the CLI.
     */
    public static void main()
    {
        String input = displayInput();
        String data = Backend.parseData(input);
        if (input.equals(""))
        {
            noInputError();
            System.exit(0);
        }

        int[] numsOfChars = Backend.charCounter(data);

        displayOutput(numsOfChars);

        if (Config.getEnabledSettings()[1])
            promptForFileOutput(Backend.outputMessage(numsOfChars));
    }
}
