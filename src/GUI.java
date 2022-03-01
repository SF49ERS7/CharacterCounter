import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.util.Objects.requireNonNullElse;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
 /**
 *  Contains all the <code>JOptionPane</code> calls in this program, making modularization easy.
 */
public class GUI
{
    /**
     * Displays a dialog box with a text prompt, asking for an input.
     * @return The input provided by the user.
     */
    public static String displayHIDInput()
    {
        String input = showInputDialog(null, "Enter the input to count\nAlternatively, type ':goback' to go back", "Input", JOptionPane.QUESTION_MESSAGE);
        return requireNonNullElse(input, ":goback");
    }
    /**
     * Displays a dialog box by taking <code>numsOfChars</code>, and passing it to <code>outputMessage()</code>.
     * @param numsOfChars An array with at least 29 rows that is ordered correctly.
     */
    public static void displayOutput(long[] numsOfChars)
    {
        showMessageDialog(null, Backend.outputMessage(numsOfChars), "Result", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Displays when the user fails to provide input.
     */
    public static void noInputError()
    {
        showMessageDialog(null, "Error: You gave no input\nPlease give an input and try again", "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Displays when the user provides a file that does not contain text.
     */
    public static void invalidInputError()
    {
        showMessageDialog(null, "Error: File either contains no data, or is unreadable.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Tests if the inputted data is empty.
     * @param input The inputted data.
     */
    public static boolean testForNoData(String input)
    {
        if (input.length() == 0)
        {
            invalidInputError();
            return true;
        }
        return false;
    }
    /**
     * Displays a <code>JFileChooser</code> prompt, asking for a file.
     * @return The file path.
     */
    public static String fileInputFromGui()
    {
        JFrame frame = new JFrame("Input");
        JFileChooser chooser = new JFileChooser(Config.getPathToFolder());
        frame.setSize(1, 1);
        frame.setVisible(true);
        frame.setVisible(false);
        chooser.setDialogTitle("Select the file to count from");
        int returnVal = chooser.showOpenDialog(frame);
        switch (returnVal) {
            case JFileChooser.APPROVE_OPTION -> {
                Config.setPathToFolder(Backend.formatPathToFolder(chooser.getSelectedFile().getAbsolutePath(), chooser.getSelectedFile().getName()));
                return chooser.getSelectedFile().getAbsolutePath();
            }
            case JFileChooser.CANCEL_OPTION -> {
                return ":goback";
            }
            case JFileChooser.ERROR_OPTION -> System.exit(1);
        }
        return ":quit";
    }
    /**
     * Runs after counting of a file is completed.
     */
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
        showMessageDialog(null, "Error: The file does not exist, or is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Runs the initial GUI.
     */
    public static void main()
    {
        for (long i = 0L; i < Long.MAX_VALUE; i++)
        {
            Object[] options = new Object[]{"From inputted text", "From a file", "Cancel"};
            int n = JOptionPane.showOptionDialog(null, "Welcome to Character Counter, version " + Backend.getProgramVersion() + "\nHow would you like to count?", "Input", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            switch (n) {
                case 0 -> hid();
                case 1 -> fileCounter();
                default -> System.exit(0);
            }
        }
    }
    /**
     * Runs the GUI that counts from human input.
     */
    public static void hid()
    {
        for (long i = 0L; i < Long.MAX_VALUE; i++)
        {
            String input = displayHIDInput();
            String parsedData = Backend.parseData(input);

            switch (parsedData) {
                case "" -> {
                    noInputError();
                    continue;
                }
                case ":goback" -> {
                    return;
                }
            }

            long[] numsOfChars = Backend.charCounter(parsedData);

            displayOutput(numsOfChars);
        }
    }
    /**
     * Runs the GUI that counts from a file on disk.
     */
    public static void fileCounter()
    {
        for (long i = 0; i < Long.MAX_VALUE; i++)
        {
            if (Config.getPathToFile() == null)
            {
                String input = fileInputFromGui();
                switch (input) {
                    case ":goback":
                        return;
                    case ":quit":
                        System.exit(0);
                    case "":
                        noInputError();
                        continue;
                }
                Config.setPathToFile(input);
            }
            File file = new File(Config.getPathToFile());
            if (!file.exists())
            {
                noFileError();
                resetFileInputPath();
                continue;
            }
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
