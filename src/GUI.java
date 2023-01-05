import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import static javax.swing.JOptionPane.*;

/**
 * This is the new GUI for Character Counter, which is based on <code>JFrame</code>.
 */
public class GUI extends JFrame
{
    public static JPopupMenu popupMenu = new JPopupMenu();
    /**
     * Constructor for the GUI.
     */
    private GUI()
    {
        super.setSize(854, 480);
        super.setTitle("Character Counter");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        drawUI();
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }
    /**
     * Builds the main panel.
     */
    private void drawUI()
    {
        //MENU BAR//
        JMenuBar menuBar = new JMenuBar();

        //FILE MENU//
        JMenu file1 = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        file1.add(exit);

        //SETTINGS MENU//
        JMenu settings = new JMenu("Settings");
        //SHOW EMPTY VALUES
        JCheckBox showEmptyValues = new JCheckBox("Show empty values");
        showEmptyValues.setSelected(Config.isShowEmptyVals());
        settings.add(showEmptyValues);
        //SEND OUTPUT TO A FILE
        JCheckBox sendOutputToAFile = new JCheckBox("Send the contents of the output to a file");
        sendOutputToAFile.setSelected(Config.isSendOutputToFile());
        settings.add(sendOutputToAFile);
        //SHOW TOTAL CHARACTERS
        JCheckBox showTotalCharacters = new JCheckBox("Show total number of characters");
        showTotalCharacters.setSelected(Config.isShowTotal());
        settings.add(showTotalCharacters);
        //COUNT NUMBERS INDIVIDUALLY
        JCheckBox countNumbersIndividually = new JCheckBox("Count the numbers individually");
        countNumbersIndividually.setSelected(Config.isShowNumsIndependently());
        settings.add(countNumbersIndividually);
        settings.addSeparator();
        //UNSUPPORTED OPTIONS
        JMenuItem advancedOptions = new JMenuItem("Advanced options...");
        settings.add(advancedOptions);

        //HELP MENU//
        JMenu help = new JMenu("Help");
        JMenuItem helpOnWeb = new JMenuItem("View Help");
        JMenuItem checkForUpdates = new JMenuItem("Check for updates...");
        JMenuItem about = new JMenuItem("About CharacterCounter...");
        help.add(helpOnWeb);
        help.add(checkForUpdates);
        help.add(about);

        //ROOT//
        menuBar.add(file1);
        menuBar.add(settings);
        menuBar.add(help);
        super.add(menuBar, BorderLayout.NORTH);

        //MAIN PANEL//
        JPanel centerPanel = new JPanel();
        BorderLayout borderLayout1 = new BorderLayout();
        centerPanel.setLayout(borderLayout1);

        //LABEL PANEL
        JPanel labelPanel = new JPanel();
        BorderLayout borderLayout2 = new BorderLayout();
        labelPanel.setLayout(borderLayout2);
        JLabel textPane = new JLabel("How would you like to count?", SwingConstants.CENTER);
        labelPanel.add(textPane, BorderLayout.CENTER);
        centerPanel.add(labelPanel, BorderLayout.NORTH);

        //BUTTON PANEL//
        JPanel buttonPanel = new JPanel();
        JButton countFromFile = new JButton("Count from file");
        countFromFile.setToolTipText("Click this button to count characters from a file on your hard drive");
        JButton countFromURL = new JButton("Count from URL");
        countFromURL.setToolTipText("Click this button and enter a URL to count characters from a file on the web.");
        JButton countFromInput = new JButton("Count from input");
        countFromInput.setToolTipText("Click this button to open a text box to type into to count from.");
        buttonPanel.add(countFromFile, 0);
        buttonPanel.add(countFromURL, 1);
        buttonPanel.add(countFromInput, 2);
        centerPanel.add(buttonPanel);

        //ROOT//
        super.add(centerPanel, BorderLayout.CENTER);

        //LISTENERS//
        exit.addActionListener(event -> System.exit(0));
        showEmptyValues.addActionListener(event2 -> Config.setShowEmptyVals(showEmptyValues.isSelected()));
        sendOutputToAFile.addActionListener(event2 -> Config.setSendOutputToFile(sendOutputToAFile.isSelected()));
        showTotalCharacters.addActionListener(event2 -> Config.setShowTotal(showTotalCharacters.isSelected()));
        countNumbersIndividually.addActionListener(event2 -> Config.setShowNumsIndependently(countNumbersIndividually.isSelected()));
        advancedOptions.addActionListener(event -> {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to use this menu?\nAny settings you change here are NOT supported and may break the program.\nAdditionally, there is no documentation provided.", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.OK_OPTION)
            {
                String input = JOptionPane.showInputDialog(this, "Enter your advanced options here", "Input", JOptionPane.QUESTION_MESSAGE);
                if (input.equals("legacygui"))
                {
                    int result2 = JOptionPane.showConfirmDialog(this, "Are you sure you want to revert to the legacy GUI?\nIt is no longer supported, and it might be broken", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (result2 == JOptionPane.OK_OPTION)
                    {
                        super.setVisible(false);
                        Config.setRunUI("GUI_Legacy");
                        GUI_Legacy.main();
                    }
                }
                String[] splitInput = input.split(" ");
                Config.applySettings(splitInput);
            }
        });
        helpOnWeb.addActionListener(event -> {
            Desktop desktop = Desktop.getDesktop();
            URI uri;
            try {
                uri = new URI("https://github.com/SF49ERS7/CharacterCounter/wiki");
            } catch (URISyntaxException e) {
                return;
            }
            try {
                desktop.browse(uri);
            } catch (UnsupportedOperationException e) {
                try {
                    Process process = Runtime.getRuntime().exec(new String[] {
                            "xdg-open", uri.toString()
                    });
                    process.waitFor();
                    process.destroy();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error: Your OS doesn't support opening links", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (InterruptedException ignored) {
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error: Could not find a browser", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        checkForUpdates.addActionListener(event -> {
            Desktop desktop = Desktop.getDesktop();
            URI uri;
            try {
                uri = new URI("https://github.com/SF49ERS7/CharacterCounter/releases");
            } catch (URISyntaxException e) {
                return;
            }
            try {
                desktop.browse(uri);
            } catch (UnsupportedOperationException e) {
                try {
                    Process process = Runtime.getRuntime().exec(new String[] {
                            "xdg-open", uri.toString()
                    });
                    process.waitFor();
                    process.destroy();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error: Your OS doesn't support opening links", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (InterruptedException ignored) {
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error: Could not find a browser", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        about.addActionListener(event -> JOptionPane.showMessageDialog(this, "Character Counter, by SF49ERS7\nVersion " + Backend.getProgramVersion(), "Version Information", JOptionPane.INFORMATION_MESSAGE));
        countFromInput.addActionListener(event -> {
            //ROOT//
            JFrame inputCountingPanel = new JFrame("Input");
            inputCountingPanel.setSize(854, 480);
            inputCountingPanel.setLocationRelativeTo(null);

            //TEXT AREA//
            JTextArea textArea = new JTextArea();

            textArea.setLineWrap(true);
            JScrollPane scrollPane = new JScrollPane(textArea);
            inputCountingPanel.add(scrollPane, BorderLayout.CENTER);

            //RIGHT CLICK MENU//
            JMenuItem cut = new JMenuItem("Cut");
            cut.addActionListener(event2 -> textArea.cut());
            popupMenu.add(cut);
            JMenuItem copy = new JMenuItem("Copy");
            popupMenu.add(copy);
            copy.addActionListener(event2 -> textArea.copy());
            JMenuItem paste = new JMenuItem("Paste");
            paste.addActionListener(event2 -> textArea.paste());
            popupMenu.add(paste);
            popupMenu.addSeparator();
            JMenuItem selectAll = new JMenuItem("Select All");
            selectAll.addActionListener(event2 -> textArea.selectAll());
            popupMenu.add(selectAll);
            MouseListener popupListener = new RightClickListener();
            textArea.addMouseListener(popupListener);

            //MENU BAR//
            JMenuBar menuBarCounting = new JMenuBar();
            JButton count = new JButton("Count");
            count.setToolTipText("Click this button when you are finished typing.");
            JButton exitButton = new JButton("Return");
            exitButton.setToolTipText("Click this button to go back to the main menu.");
            count.addActionListener(event2 -> {
                String parsedData = Backend.parseData(textArea.getText());

                if (parsedData.equals(""))
                {
                    noInputError();
                    return;
                }

                long[] numsOfChars = Backend.charCounter(parsedData);

                displayOutput(numsOfChars);
                if (Config.isSendOutputToFile())
                    saveOutputToFile(numsOfChars);
                textArea.setText("");
            });
            exitButton.addActionListener(event2 -> inputCountingPanel.dispose());
            menuBarCounting.add(count);
            menuBarCounting.add(exitButton);
            inputCountingPanel.add(menuBarCounting, BorderLayout.NORTH);

            //END//
            inputCountingPanel.setVisible(true);
        });
        countFromURL.addActionListener(event -> {
            String linkToCountFrom = JOptionPane.showInputDialog(this, "Enter the URL of the document to count", "Input", JOptionPane.QUESTION_MESSAGE);
            if (linkToCountFrom == null)
                return;
            else if (linkToCountFrom.equals(""))
            {
                noInputError();
                return;
            }
            String input;
            try {
                input = Backend.getInputFromURL(linkToCountFrom);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error: The input was not a valid URL, or the URL contained no data", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            long[] numsOfChars = Backend.charCounter(input);

            displayOutput(numsOfChars);

            if (Config.isSendOutputToFile())
                saveOutputToFile(numsOfChars);
        });
        countFromFile.addActionListener(event -> {
            if (Config.getPathToFile() == null)
            {
                String input = "";
                JFileChooser chooser = new JFileChooser(Config.getPathToFolder());
                chooser.setDialogTitle("Select the file to count from");
                int returnVal = chooser.showOpenDialog(this);
                switch (returnVal) {
                    case JFileChooser.APPROVE_OPTION -> {
                        Config.setPathToFolder(Backend.formatPathToFolder(chooser.getSelectedFile().getAbsolutePath(), chooser.getSelectedFile().getName()));
                        input = chooser.getSelectedFile().getAbsolutePath();
                    }
                    case JFileChooser.ERROR_OPTION -> System.exit(1);
                    default -> input = ":goback";
                }

                switch (input)
                {
                    case ":goback" -> {
                        return;
                    }
                    case ":quit" -> System.exit(0);
                    case "" -> noInputError();
                }
                Config.setPathToFile(input);
            }
            File file = new File(Config.getPathToFile());
            if (!file.exists())
            {
                noFileError();
                resetFileInputPath();
                return;
            }
            Scanner inputFile;
            try {
                inputFile = new Scanner(file);
            } catch (FileNotFoundException e) {
                noFileError();
                resetFileInputPath();
                return;
            }
            StringBuilder dataIn1Line = new StringBuilder();

            while (inputFile.hasNextLine())
                dataIn1Line.append(inputFile.nextLine());

            String input = String.valueOf(dataIn1Line);

            if (testForNoData(input)) {
                resetFileInputPath();
                return;
            }
            long[] numsOfChars = Backend.charCounter(input);

            displayOutput(numsOfChars);
            if (Config.isSendOutputToFile())
                saveOutputToFile(numsOfChars);

            resetFileInputPath();
        });
    }
    /**
     * Main method for <code>GUI</code>.
     */
    public static void hid()
    {
        GUI gui = new GUI();
    }
    /**
     * Displays a dialog box by taking <code>numsOfChars</code>, and passing it to <code>outputMessage()</code>.
     * @param numsOfChars An array with at least 29 rows that is ordered correctly.
     */
    public static void displayOutput(long[] numsOfChars)
    {
        JOptionPane.showMessageDialog(null, Backend.outputMessage(numsOfChars), "Result", INFORMATION_MESSAGE);
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
    private static boolean testForNoData(String input)
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
    private void saveOutputToFile(long[] numsOfChars)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select where you wish to save the file output");
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
            try {
                Backend.writeOutputToFile(numsOfChars, chooser.getSelectedFile().getAbsolutePath());
            } catch (IOException e) {
                showMessageDialog(this, "Error: Disk is write-protected");
            }
    }
    /**
     * Displays when the user provides a file path that doesn't work.
     */
    private void noFileError()
    {
        showMessageDialog(this, "Error: The file does not exist, or is invalid.", "Error", ERROR_MESSAGE);
    }
    /**
     * Runs after counting of a file is completed.
     */
    private void resetFileInputPath()
    {
        if (!Config.isCountFromFile())
            Config.setPathToFile(null);
        else
            System.exit(0);
    }
}
