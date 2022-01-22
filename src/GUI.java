import javax.swing.JOptionPane;
/**
 *  Contains all the <code>JOptionPane</code> calls in this program, making modularization easy
 */
public class GUI
{
    /**
     * Displays a dialog box with a text prompt, asking for an input.
     * @return The input provided by the user.
     */
    public static String displayInput()
    {
        return JOptionPane.showInputDialog(null, "Welcome to Character Counter version " + Backend.getProgramVersion() + "\nEnter the input to count", "Input", JOptionPane.QUESTION_MESSAGE);
    }
    /**
     * Displays a dialog box by taking the <code>numsOfChars</code> parameter, and passing it to the <code>outputMessage()</code> method.
     * @param numsOfChars An array with at least 29 rows that is ordered correctly.
     */
    public static void displayOutput(long[] numsOfChars)
    {
        JOptionPane.showMessageDialog(null, Backend.outputMessage(numsOfChars), "Result", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Displays when the user fails to provide input.
     */
    public static void noInputError()
    {
        JOptionPane.showMessageDialog(null, "Error: You gave no input\nPlease give an input and try again", "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Runs the GUI.
     */
    public static void main()
    {
        Config.setRunUI("GUI");

        for (long i = 0L; i < Long.MAX_VALUE; i++)
        {
            String input = displayInput();
            String parsedData = Backend.parseData(input);

            if (parsedData.equals(""))
            {
                noInputError();
                continue;
            }

            long[] numsOfChars = Backend.charCounter(parsedData);

            displayOutput(numsOfChars);
        }
    }
}
