import java.awt.GraphicsEnvironment;
/**
 * Stores settings for the program and allows other classes to read the settings.
 */
public class Config
{
    /**
     * Stores which UI is currently in use.
     */
    private static String runUI;
    /**
     * Stores whether to show all values in the output.
     */
    private static boolean showAllVals;
    /**
     * Stores whether to count input from a file, rather than from human input.
     */
    private static boolean countFromFile;
    /**
     * Stores the path to the file, if counting from file.
     */
    private static String pathToFile;
    /**
     * Stores the path to the folder containing the file, if counting from file.
     */
    private static String pathToFolder;
    /**
     * Stores whether to force the GUI to run, under any circumstances.
     */
    private static boolean forceGUI;
    /**
     * Stores whether to force the CLI to run, under any circumstances.
     */
    private static boolean forceCLI;
    /**
     * Sets <code>enabledSettings</code> with the settings from the command-line.
     *
     * @param args The settings to apply.
     */
    public static void applySettings(String[] args)
    {
        if (args.length != 0)
            if (args[0].equals("--help") || args[0].equals("-h"))
                CLI.displayHelp();
            for (int i = 0; i < args.length; i++)
                switch (args[i]) {
                    case "--cmd", "--cli" -> forceCLI = true; //Whether to force a command-line interface
                    case "--file" -> { //Whether to count input from a file
                        if (args.length >= 2)
                        {
                            countFromFile = true;
                            pathToFile = args[i + 1];
                        }
                        else
                            CLI.invalidArgsError();
                    }
                    case "--allvals" -> showAllVals = true; //Whether to show empty values in the output
                    case "--gui", "--ui" -> forceGUI = true; //Whether to force a GUI
                }
        if (GraphicsEnvironment.isHeadless() && !forceGUI)
            runUI = "CLI"; //Even if the command line doesn't specify, we don't want to throw an exception
        if (forceGUI)
            runUI = "GUI";
        if (forceCLI)
            runUI = "CLI";
        if (runUI == null)
            runUI = "GUI";
    }
    /**
     * Getter for <code>runUI</code>.
     *
     * @return <code>runUI</code>.
     */
    public static String getRunUI()
    {
        return runUI;
    }
    /**
     * Setter for <code>runUI</code>.
     * @param runningUI The UI to set the value to.
     */
    public static void setRunUI(String runningUI)
    {
        if (runningUI.equals("GUI") || runningUI.equals("CLI") || runningUI.equals("FileCounter"))
            Config.runUI = runningUI;
    }
    /**
     * Getter for <code>showAllVals</code>.
     * @return <code>showAllVals</code>.
     */
    public static boolean isShowAllVals()
    {
        return showAllVals;
    }
    /**
     * Getter for <code>countFromFile</code>.
     * @return <code>countFromFile</code>.
     */
    public static boolean isCountFromFile()
    {
        return countFromFile;
    }
    /**
     * Getter for <code>pathToFile</code>.
     * @return <code>pathToFile</code>.
     */
    public static String getPathToFile()
    {
        return pathToFile;
    }
    /**
     * Setter for <code>pathToFile</code>.
     * @param pathToFile What to set <code>pathToFile</code> to.
     */
    public static void setPathToFile(String pathToFile)
    {
        Config.pathToFile = pathToFile;
    }
    /**
     * Getter for <code>pathToFolder</code>.
     * @return <code>pathToFolder</code>.
     */
    public static String getPathToFolder()
    {
        return pathToFolder;
    }
    /**
     * Setter for <code>pathToFolder</code>.
     * @param pathToFolder <code>pathToFolder</code>.
     */
    public static void setPathToFolder(String pathToFolder)
    {
        Config.pathToFolder = pathToFolder;
    }
}