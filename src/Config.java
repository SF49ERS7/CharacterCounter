import java.awt.GraphicsEnvironment;
/**
 * Stores settings for the program and allows other classes to read the settings.
 */
public class Config
{
    /**
     * Holds the settings.
     */
    private static boolean[] enabledSettings;
    /**
     * Stores which UI is currently in use.
     */
    private static String runUI;
    /**
     * Sets <code>enabledSettings</code> with the settings from the command-line.
     *
     * @param args The settings to apply.
     */
    public static void applySettings(String[] args)
    {
        enabledSettings = new boolean[3];
        if (args.length != 0)
            if (args[0].equals("--help") || args[0].equals("-h"))
                CLI.displayHelp();
            for (int i = 0; i < args.length; i++)
            {
                switch (args[i])
                {
                    case "--cmd", "--cli" -> runUI = "CLI"; //Whether to force a command-line interface
                    case "--file" -> { //Whether to count input from a file
                        runUI = "FileCounter";
                        FileCounter.setPathToFile(args[i + 1]);
                    }
                    case "-allvals" -> enabledSettings[2] = true; //Whether to show empty values in the output
                    case "--gui", "--ui" -> runUI = "GUI"; //Whether to force a GUI
                }
            }
        if (GraphicsEnvironment.isHeadless())
            runUI = "CLI"; //Even if the command line doesn't specify, we don't want to throw an exception
        if (runUI == null)
            runUI = "GUI";
    }
    /**
     * Gets the settings.
     *
     * @return The settings.
     */
    public static boolean[] getEnabledSettings()
    {
        return enabledSettings;
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
}