import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
        super.setVisible(true);
    }
    /**
     * Builds the main panel.
     */
    private void drawUI()
    {
        //MENU BAR//

        JMenuBar menuBar = new JMenuBar();

        JMenu file1 = new JMenu("File");
        JMenuItem settings = new JMenuItem("Settings");
        JMenuItem exit = new JMenuItem("Exit");
        file1.add(settings);
        file1.add(exit);

        JMenu help = new JMenu("Help");
        JMenuItem helpOnWeb = new JMenuItem("View Help");
        JMenuItem about = new JMenuItem("About");
        help.add(helpOnWeb);
        help.add(about);

        menuBar.add(file1);
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
        GridLayout gridLayout = new GridLayout(1, 2);
        buttonPanel.setLayout(gridLayout);
        JButton countFromFile = new JButton("Count from file");
        JButton countFromInput = new JButton("Count from input");
        buttonPanel.add(countFromFile, 0);
        buttonPanel.add(countFromInput, 1);
        centerPanel.add(buttonPanel);

        //ROOT//
        super.add(centerPanel, BorderLayout.CENTER);

        //LISTENERS//
        settings.addActionListener(event -> GUI_Legacy.settingsUI());
        exit.addActionListener(event -> System.exit(0));
        about.addActionListener(event -> JOptionPane.showMessageDialog(this, "Character Counter, by SF49ERS7\nVersion " + Backend.getProgramVersion()));
        helpOnWeb.addActionListener(event -> {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI("https://github.com/SF49ERS7/CharacterCounter/wiki"));
            } catch (IOException | URISyntaxException e) {
                JOptionPane.showMessageDialog(this, "Error: Could not find a browser", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        countFromInput.addActionListener(event -> GUI_Legacy.hid());
        countFromFile.addActionListener(event -> {
            for (long i = 0; i < Long.MAX_VALUE; i++)
            {
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
                        case "" -> {
                            GUI_Legacy.noInputError();
                            continue;
                        }
                    }
                    Config.setPathToFile(input);
                }
                File file = new File(Config.getPathToFile());
                if (!file.exists())
                {
                    GUI_Legacy.noFileError();
                    GUI_Legacy.resetFileInputPath();
                    continue;
                }
                Scanner inputFile;
                try {
                    inputFile = new Scanner(file);
                } catch (FileNotFoundException e) {
                    GUI_Legacy.noFileError();
                    GUI_Legacy.resetFileInputPath();
                    continue;
                }
                StringBuilder dataIn1Line = new StringBuilder();

                while (inputFile.hasNextLine())
                    dataIn1Line.append(inputFile.nextLine());

                String input = String.valueOf(dataIn1Line);

                if (GUI_Legacy.testForNoData(input))
                {
                    GUI_Legacy.resetFileInputPath();
                    continue;
                }
                long[] numsOfChars = Backend.charCounter(input);

                GUI_Legacy.displayOutput(numsOfChars);
                if (Config.isSendOutputToFile())
                    GUI_Legacy.saveOutputToFile(numsOfChars);

                GUI_Legacy.resetFileInputPath();
            }
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
