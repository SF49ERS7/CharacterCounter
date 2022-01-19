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
     * Sets <code>enabledSettings</code> with the settings from the command-line.
     * @param args The settings to apply.
     */
    public static void applySettingsCLI(String[] args)
    {
        enabledSettings = new boolean[4];
        if (args.length != 0)
            for (String arg : args)
                switch (arg) {
                    case "-cmd", "-cli" -> enabledSettings[0] = true; //Whether to force a command-line interface
                    case "-fileio", "fileio", "compiledBuild" -> enabledSettings[1] = true; //Whether to enable the file IO, which is disabled by default in the CLI
                    case "-allvals", "allvals" -> enabledSettings[2] = true; //Whether to show empty values in the output
                    case "-gui", "ui" -> enabledSettings[3] = true; //Whether to force a GUI
                }
        if (GraphicsEnvironment.isHeadless() && !enabledSettings[3])
            enabledSettings[0] = true; //Even if the command line doesn't specify, we don't want to throw an exception unless the user really wants to
    }
    /**
     * Gets the settings.
     * @return The settings.
     */
    public static boolean[] getEnabledSettings()
    {
        return enabledSettings;
    }
    /**
     * Sets <code>enabledSettings</code> with the settings from the GUI input, and removes the arguments.
     * @param input The inputted text.
     * @return <code>input</code>, but with the arguments removed.
     */
    public static String applySettingsGUI(String input)
    {
        StringBuilder filteredInput = new StringBuilder(input);

        if (input.contains(":::nofileio:::")) //Whether to disable file IO, which is enabled by default in the GUI
        {
            enabledSettings[1] = true;
            filteredInput.delete(filteredInput.indexOf(":"), filteredInput.lastIndexOf(":"));
            filteredInput.deleteCharAt(filteredInput.indexOf(":"));
        }
        else if (input.contains(":::allvals:::")) //Whether to show empty values in the output
        {
            enabledSettings[2] = true;
            filteredInput.delete(filteredInput.indexOf(":"), filteredInput.lastIndexOf(":"));
            filteredInput.deleteCharAt(filteredInput.indexOf(":"));
        }
        return String.valueOf(filteredInput);
    }
}
