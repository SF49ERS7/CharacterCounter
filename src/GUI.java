import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
/**
 * This is the new GUI for Character Counter, which is based on <code>JFrame</code>.
 */
public class GUI extends JFrame
{
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
        JMenu unsupportedOptions = new JMenu("Unsupported options");
        JMenuItem goToLegacyUI = new JMenuItem("Revert to legacy UI");
        JMenuItem advancedOptions = new JMenuItem("Advanced options");
        unsupportedOptions.add(goToLegacyUI);
        unsupportedOptions.add(advancedOptions);
        settings.add(unsupportedOptions);

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
        JButton countFromURL = new JButton("Count from URL");
        JButton countFromInput = new JButton("Count from input");
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
        goToLegacyUI.addActionListener(event -> {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to revert to the legacy GUI?\nIt is no longer supported, and it might be broken", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.OK_OPTION)
            {
                super.setVisible(false);
                Config.setRunUI("GUI_Legacy");
                GUI_Legacy.main();
            }
        });
        advancedOptions.addActionListener(event -> {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to use this menu?\nAny settings you change here are NOT supported and may break the program.\nAdditionally, there is no documentation provided.", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.OK_OPTION)
            {
                String input = JOptionPane.showInputDialog(this, "Enter your advanced options here", "Input", JOptionPane.QUESTION_MESSAGE);
                String[] splitInput = input.split(" ");
                Config.applySettings(splitInput);
            }
        });
        helpOnWeb.addActionListener(event -> {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI("https://github.com/SF49ERS7/CharacterCounter/wiki"));
            } catch (IOException | URISyntaxException e) {
                JOptionPane.showMessageDialog(this, "Error: Could not find a browser", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (UnsupportedOperationException e) {
                JOptionPane.showMessageDialog(this, "Error: Your OS doesn't support opening links", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        checkForUpdates.addActionListener(event -> {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI("https://github.com/SF49ERS7/CharacterCounter/releases"));
            } catch (IOException | URISyntaxException e) {
                JOptionPane.showMessageDialog(this, "Error: Could not find a browser", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (UnsupportedOperationException e) {
                JOptionPane.showMessageDialog(this, "Error: Your OS doesn't support opening links", "Error", JOptionPane.ERROR_MESSAGE);
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

            //MENU BAR//
            JMenuBar menuBarCounting = new JMenuBar();
            JButton count = new JButton("Count");
            JButton exitButton = new JButton("Return");
            count.addActionListener(event2 -> {
                String parsedData = Backend.parseData(textArea.getText());

                if (parsedData.equals(""))
                {
                    GUI_Legacy.noInputError();
                    return;
                }

                long[] numsOfChars = Backend.charCounter(parsedData);

                GUI_Legacy.displayOutput(numsOfChars);
                if (Config.isSendOutputToFile())
                    GUI_Legacy.saveOutputToFile(numsOfChars);
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
                GUI_Legacy.noInputError();
                return;
            }
            String input;
            try {
                StringBuilder pageContentsBuilder = new StringBuilder(0);
                URL url = new URL(linkToCountFrom);
                URLConnection urlConnection = url.openConnection();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                for (String needle = bufferedReader.readLine(); needle != null; needle = bufferedReader.readLine())
                    pageContentsBuilder.append(needle).append("\n");
                bufferedReader.close();
                input = pageContentsBuilder.toString();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error: The input was not a valid URL, or the URL contained no data", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            long[] numsOfChars = Backend.charCounter(input);

            GUI_Legacy.displayOutput(numsOfChars);

            if (Config.isSendOutputToFile())
                GUI_Legacy.saveOutputToFile(numsOfChars);
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
                    case "" -> GUI_Legacy.noInputError();
                }
                Config.setPathToFile(input);
            }
            File file = new File(Config.getPathToFile());
            if (!file.exists())
            {
                GUI_Legacy.noFileError();
                GUI_Legacy.resetFileInputPath();
                return;
            }
            Scanner inputFile;
            try {
                inputFile = new Scanner(file);
            } catch (FileNotFoundException e) {
                GUI_Legacy.noFileError();
                GUI_Legacy.resetFileInputPath();
                return;
            }
            StringBuilder dataIn1Line = new StringBuilder();

            while (inputFile.hasNextLine())
                dataIn1Line.append(inputFile.nextLine());

            String input = String.valueOf(dataIn1Line);

            if (GUI_Legacy.testForNoData(input))
                GUI_Legacy.resetFileInputPath();
            long[] numsOfChars = Backend.charCounter(input);

            GUI_Legacy.displayOutput(numsOfChars);
            if (Config.isSendOutputToFile())
                GUI_Legacy.saveOutputToFile(numsOfChars);

            GUI_Legacy.resetFileInputPath();
        });
    }
    /**
     * Main method for <code>GUI</code>.
     */
    public static void hid()
    {
        GUI gui = new GUI();
    }
}
