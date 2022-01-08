import javax.swing.JOptionPane;
/**
 *  Contains all the <code>JOptionPane</code> calls in this program, making it easy to modularize the program.
 */
public class GUI
{
    /**
     * Displays a dialog box with a text prompt, asking for an input.
     * @return The input provided by the user
     */
    public static String displayInput()
    {
        return JOptionPane.showInputDialog(null, "Enter the input to count\nAlternatively, type ':quit' to exit", "Input", JOptionPane.QUESTION_MESSAGE);
    }
    /**
     * Displays when the file output request fails due to a write-protected disk. Rarely, if ever, used.
     */
    public static void displayFileError()
    {
        JOptionPane.showMessageDialog(null, "Error: Your disk is write-protected, so I cannot send the output to a file", "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Displays a dialog box by taking the <code>numsOfChars</code> parameter, and passing it to the <code>outputMessage()</code> method.
     * @param numsOfChars An array with at least 29 rows that is ordered correctly
     */
    public static void displayOutput(int[] numsOfChars)
    {
        JOptionPane.showMessageDialog(null,
                Backend.outputMessage(numsOfChars),
                "Result",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    /**
     * Prompts the user if they would like to send the contents of <code>displayOutput</code> to a file on the disk.
     * @param output The data to be written to the disk.
     */
    public static void promptForFileOutput(String output)
    {
        int result = JOptionPane.showConfirmDialog(null, "Do you wish to send the previous output to a file?", "Output Question", JOptionPane.YES_NO_OPTION);
        if (result == 0)
            Backend.sendToTextFile(output);
    }
    /**
     * Sent when a user fails to provide input.
     */
    public static void noInputError()
    {
        JOptionPane.showMessageDialog(null, "Error: You gave no input\nPlease give an input and try again", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
