import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *  Contains all the <code>JOptionPane</code> calls in this program, making modularization easy.
 */
public class GUI
{
    /**
     * Displays a dialog box with a text prompt, asking for an input.
     * @return The input provided by the user.
     */
    public static String displayInput()
    {
        return JOptionPane.showInputDialog(null, "Welcome to Character Counter, version " + Backend.getProgramVersion() + "\nEnter the input to count, or type ':file' to count from a file", "Input", JOptionPane.QUESTION_MESSAGE);
    }
    /**
     * Displays a dialog box by taking <code>numsOfChars</code>, and passing it to <code>outputMessage()</code>.
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
     * Displays when the user provides a file that does not contain text.
     */
    public static void invalidInputError()
    {
        JOptionPane.showMessageDialog(null, "Error: File either contains no data, or is unreadable.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Tests if the inputted data is empty.
     * @param input The inputted data.
     */
    public static boolean testForNoData(String input)
    {
        boolean foundNoData = false;
        if (input.length() == 0)
        {
            GUI.invalidInputError();
            foundNoData = true;
        }
        return foundNoData;
    }
    public static String fileInputFromGui()
    {
        return JOptionPane.showInputDialog(null, "Welcome to Character Counter File, version " + Backend.getProgramVersion() + "\nEnter the path to the file you wish to count from\nAlternatively, type ':goback' to go back", "Input", JOptionPane.QUESTION_MESSAGE);
    }
    public static void resetFileInputPath()
    {
        if (!Config.isCountFromFile())
            Config.setPathToFile(null);
        else
            System.exit(0);
    }
    /**
     * Displays when the user provides a file path that doesn't work.
     */
    public static void noFileError()
    {
        JOptionPane.showMessageDialog(null, "Error: I could not find the file you specified.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Runs the GUI by counting from human input.
     */
    public static void hid()
    {
        for (long i = 0L; i < Long.MAX_VALUE; i++)
        {
            String input = displayInput();
            String parsedData = Backend.parseData(input);

            if (parsedData.equals(""))
            {
                noInputError();
                continue;
            }
            else if (parsedData.equals(":file"))
            {
                fileCounter();
                continue;
            }

            long[] numsOfChars = Backend.charCounter(parsedData);

            displayOutput(numsOfChars);
        }
    }
    /**
     * Runs the CLI by counting from a file on disk.
     */
    public static void fileCounter()
    {
        for (long i = 0; i < Long.MAX_VALUE; i++)
        {
            if (Config.getPathToFile() == null)
            {
                String input = fileInputFromGui();
                if (input.equals(":goback"))
                    return;
                else if (input.equals(":quit"))
                    System.exit(0);
                Config.setPathToFile(input);
            }
            File file = new File(Config.getPathToFile());
            Scanner inputFile;
            try {
                inputFile = new Scanner(file);
            } catch (FileNotFoundException e) {
                noFileError();
                resetFileInputPath();
                continue;
            }
            StringBuilder dataIn1Line = new StringBuilder();

            while (inputFile.hasNextLine())
                dataIn1Line.append(inputFile.nextLine());

            String input = String.valueOf(dataIn1Line);

            if (testForNoData(input))
            {
                resetFileInputPath();
                continue;
            }
            long[] numsOfChars = Backend.charCounter(input);

            displayOutput(numsOfChars);

            resetFileInputPath();
        }
    }
}
