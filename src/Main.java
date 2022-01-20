/**
 * The main class in the program.
 */
public class Main
{
    /**
     * Runs the program.
     * @param args Any inputted command-line arguments.
     */
    public static void main(String[] args)
    {
        Config.applySettingsGlobal(args);

        if (Config.getRunUI().equals("CLI"))
            CLI.main();
        else
            GUI.main();
    }
}
