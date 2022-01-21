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
     * Stores whether the current run of the GUI changed a setting.
     */
    private static boolean changedASettingOnCurrentRun;
    /**
     * Sets <code>enabledSettings</code> with the settings from the command-line.
     *
     * @param args The settings to apply.
     */
    public static void applySettingsGlobal(String[] args)
    {
        enabledSettings = new boolean[5];
        if (args.length != 0)
            for (String arg : args)
            {
                switch (arg) {
                    case "-cmd", "-cli" -> runUI = "CLI"; //Whether to force a command-line interface
                    case "-fileio", "fileio" -> enabledSettings[1] = true; //Whether to enable the file IO, which is disabled by default in the CLI
                    case "-allvals", "allvals" -> enabledSettings[2] = true; //Whether to show empty values in the output
                    case "-gui", "ui" -> runUI = "GUI"; //Whether to force a GUI
                    case "compiledBuild" -> enabledSettings[4] = true; //Whether to disable file IO completely, used in compiled builds
                }
            }
        if (GraphicsEnvironment.isHeadless())
            runUI = "CLI"; //Even if the command line doesn't specify, we don't want to throw an exception
        if (runUI == null)
            runUI = "GUI";
        if (runUI.equals("GUI") && !enabledSettings[4])
            enabledSettings[1] = true;
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
     *
     * @param input The inputted text.
     * @return <code>input</code>, but with the arguments removed.
     */
    public static String applySettingsGUI(String input)
    {
        StringBuilder filteredInput = new StringBuilder(input);

//        if (input.contains(":::fileio:::")) //Whether to disable file IO, which is enabled by default in the GUI, except in compiled builds
//        {
//            if (!enabledSettings[4]) {
//                enabledSettings[1] = true;
//                filteredInput.delete(filteredInput.indexOf(":"), filteredInput.lastIndexOf(":"));
//                filteredInput.deleteCharAt(filteredInput.indexOf(":"));
//                if (enabledSettings[1])
//                    GUI.settingChanged("File output is now disabled.");
//                else
//                    GUI.settingChanged("File output is now enabled.");
//            }
//        }
        if (input.contains(":::allvals:::")) //Whether to show empty values in the output
        {
            enabledSettings[2] = !enabledSettings[2];
            filteredInput.delete(filteredInput.indexOf(":"), filteredInput.lastIndexOf(":"));
            filteredInput.deleteCharAt(filteredInput.indexOf(":"));
            if (enabledSettings[2])
                GUI.settingChanged("Empty variables now display.");
            else
                GUI.settingChanged("Empty variables no longer display");
        }

        return String.valueOf(filteredInput);
    }
    /**
     * A setter for <code>enabledSettings</code>.
     *
     * @param setting The setting ID to change.
     * @param value   The value to set it to.
     */
    public static void setEnabledSettingsManually(int setting, boolean value)
    {
        if (setting <= enabledSettings.length)
            enabledSettings[setting] = value;
    }

    public static String getRunUI()
    {
        return runUI;
    }

    public static void setRunUI(String runningUI)
    {
        if (runningUI.equals("GUI") || runningUI.equals("CLI"))
            Config.runUI = runningUI;
    }

    public static boolean getChangedASettingOnCurrentRun()
    {
        return changedASettingOnCurrentRun;
    }

    public static void setChangedASettingOnCurrentRun(boolean change)
    {
        changedASettingOnCurrentRun = change;
    }
}