import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static java.util.Objects.requireNonNullElse;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
/**
 *  Contains all the <code>JOptionPane</code> calls in this program, making modularization easy.
 */
public class GUI implements UI
{
    /**
     * Displays a dialog box with a text prompt, asking for an input.
     * @return The input provided by the user.
     */
    public static String displayHIDInput()
    {
        String input = showInputDialog(null, "Enter the input to count\nAlternatively, type ':goback' to go back", "Input", QUESTION_MESSAGE);
        return requireNonNullElse(input, ":goback");
    }
    /**
     * Displays a dialog box by taking <code>numsOfChars</code>, and passing it to <code>outputMessage()</code>.
     * @param numsOfChars An array with at least 29 rows that is ordered correctly.
     */
    public static void displayOutput(long[] numsOfChars)
    {
        showMessageDialog(null, Backend.outputMessage(numsOfChars), "Result", INFORMATION_MESSAGE);
    }
    /**
     * Displays when the user fails to provide input.
     */
    public static void noInputError()
    {
        showMessageDialog(null, "Error: You gave no input\nPlease give an input and try again", "Error", ERROR_MESSAGE);
    }
    /**
     * Displays when the user provides a file that does not contain text.
     */
    public static void invalidInputError()
    {
        showMessageDialog(null, "Error: File either contains no data, or is unreadable.", "Error", ERROR_MESSAGE);
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
     * Saves the counted output to a file
     * @param numsOfChars The array of counted characters.
     */
    public static void saveOutputToFile(long[] numsOfChars)
    {
        JFrame frame = new JFrame("Input");
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select where you wish to save the file output");
        frame.setSize(1,1);
        frame.setVisible(true);
        frame.setVisible(false);
        int returnVal = chooser.showSaveDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION)
            try {
                Backend.writeOutputToFile(numsOfChars, chooser.getSelectedFile().getAbsolutePath());
            } catch (IOException e) {
                showMessageDialog(null, "Error: Disk is write-protected");
            }
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
        showMessageDialog(null, "Error: The file does not exist, or is invalid.", "Error", ERROR_MESSAGE);
    }
    /**
     * Runs the initial GUI.
     */
    public static void main()
    {
        for (long i = 0L; i < Long.MAX_VALUE; i++)
        {
            Object[] options;
            if (Config.isShowSettingsButton())
                options = new Object[]{"From inputted text", "From a file","Settings", "Cancel"};
            else
                options = new Object[]{"From inputted text", "From a file", "Cancel"};
            int n = JOptionPane.showOptionDialog(null, "Welcome to Character Counter, version " + Backend.getProgramVersion() + "\nHow would you like to count?", "Input", YES_NO_CANCEL_OPTION, QUESTION_MESSAGE, null, options, options[0]);
            switch (n) {
                case 0 -> hid();
                case 1 -> fileCounter();
                case 2 -> {
                    if (Config.isShowSettingsButton())
                        settingsUI();
                    else
                        System.exit(0);
                }
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
            if (Config.isSendOutputToFile())
                saveOutputToFile(numsOfChars);
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
                switch (input)
                {
                    case ":goback" -> {
                        return;
                    }
                    case ":quit" -> System.exit(0);
                    case "" -> {
                        noInputError();
                        continue;
                    }
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
            if (Config.isSendOutputToFile())
                saveOutputToFile(numsOfChars);

            resetFileInputPath();
        }
    }
    /**
     * Runs the settings UI.
     */
    public static void settingsUI()
    {
        for (long i = 0; i < Long.MAX_VALUE; i++)
        {
            Object[] validSettings = new Object[]{"Show empty values", "Send output to a file", "Show total characters", "Count numbers individually"};
            String settingToChange = String.valueOf(showInputDialog(null, "Select the setting you wish to change, or click Cancel if you are done.", "Input", QUESTION_MESSAGE, null, validSettings, validSettings[0]));
            boolean whatSettingWasSetTo;
            switch (settingToChange) {
                case "Show empty values" -> {
                    Config.setShowEmptyVals(!Config.isShowEmptyVals());
                    whatSettingWasSetTo = Config.isShowEmptyVals();
                }
                case "Send output to a file" -> {
                    Config.setSendOutputToFile(!Config.isSendOutputToFile());
                    whatSettingWasSetTo = Config.isSendOutputToFile();
                }
                case "Show total characters" -> {
                    Config.setShowTotal(!Config.isShowTotal());
                    whatSettingWasSetTo = Config.isShowTotal();
                }
                case "Count numbers individually" -> {
                    Config.setShowNumsIndependently(!Config.isShowNumsIndependently());
                    whatSettingWasSetTo = Config.isShowNumsIndependently();
                }
                default -> {
                    return;
                }
            }
            showMessageDialog(null, settingToChange + " is now set to " + whatSettingWasSetTo + ".");
        }
    }
}
