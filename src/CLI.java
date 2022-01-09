import java.util.Scanner;

public class CLI
{
    private static Scanner keyboard = new Scanner(System.in);

    public static String displayInput()
    {
        System.out.println("Enter the input to count, or type ':quit' to exit");
        return keyboard.nextLine();
    }

    public static void noInputError()
    {
        System.out.println("Error: You gave no input. Please give an input and try again");
    }

    public static void displayOutput(int[] numsOfChars)
    {
        System.out.println(Backend.outputMessage(numsOfChars));
    }
}
