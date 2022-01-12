import java.util.Scanner;
/**
 * The class for the rarely-used command-line interface
 */
public class CLI
{
    private static Scanner keyboard = new Scanner(System.in);
    /**
     * Asks the user for the input to count.
     * @return The prompted input.
     */
    public static String displayInput()
    {
        System.out.println("Enter the input to count, or type ':quit' to exit");
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
     * Outputs a message by taking the <code>numsOfChars</code> parameter, and passing it to the <code>outputMessage()</code> method.
     * @param numsOfChars An array with at least 29 rows that is ordered correctly
     */
    public static void displayOutput(int[] numsOfChars)
    {
        System.out.println(Backend.outputMessage(numsOfChars));
    }
    /**
     * Runnable method for the CLI
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
    }
}
