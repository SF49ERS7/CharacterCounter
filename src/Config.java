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
     * Stores whether to show the total number of characters in the output.
     */
    private static boolean showTotal = true;
    /**
     * Stores whether to show empty values in the output.
     */
    private static boolean showEmptyVals;
    /**
     * Stores whether to show counted numbers individually, rather than in one line.
     */
    private static boolean showNumsIndependently = true;
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
     * Stores whether to send the output to a file.
     */
    private static boolean sendOutputToFile;
    /**
     * Stores whether to count from a file on the web instead of on disk.
     */
    private static boolean countFromURL = false;
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
                case "--url", "--link" -> countFromURL = true;
                case "--allvals" -> showEmptyVals = true; //Whether to show empty values in the output
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
    /**
     * Getter for <code>sendOutputToFile</code>.
     * @return <code>sendOutputToFile</code>.
     */
    public static boolean isSendOutputToFile()
    {
        return sendOutputToFile;
    }
    /**
     * Setter for <code>sendOutputToFile</code>.
     * @param sendOutputToFile <code>sendOutputToFile</code>.
     */
    public static void setSendOutputToFile(boolean sendOutputToFile)
    {
        Config.sendOutputToFile = sendOutputToFile;
    }
    /**
     * Getter for <code>showTotal</code>.
     * @return <code>showTotal</code>.
     */
    public static boolean isShowTotal()
    {
        return showTotal;
    }
    /**
     * Getter for <code>showEmptyVals</code>.
     * @return <code>showEmptyVals</code>.
     */
    public static boolean isShowEmptyVals()
    {
        return showEmptyVals;
    }
    /**
     * Getter for <code>showNumsIndependently</code>.
     * @return <code>showNumsIndependently</code>.
     */
    public static boolean isShowNumsIndependently()
    {
        return showNumsIndependently;
    }
    /**
     * Setter for <code>showTotal</code>.
     */
    public static void setShowTotal(boolean showTotal)
    {
        Config.showTotal = showTotal;
    }
    /**
     * Setter for <code>showNumsIndependently</code>.
     */
    public static void setShowNumsIndependently(boolean showNumsIndependently)
    {
        Config.showNumsIndependently = showNumsIndependently;
    }
    /**
     * Setter for <code>showEmptyVals</code>.
     */
    public static void setShowEmptyVals(boolean showEmptyVals)
    {
        Config.showEmptyVals = showEmptyVals;
    }

    /**
     * Getter for <code>countFromURL</code>.
     * @return <code>countFromURL</code>.
     */
    public static boolean isCountFromURL()
    {
        return countFromURL;
    }
}