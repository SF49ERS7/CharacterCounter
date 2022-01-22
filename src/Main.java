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
        Config.applySettings(args);

        if (Config.getRunUI().equals("CLI"))
            CLI.main();
        else if (Config.getRunUI().equals("FileCounter"))
            FileCounter.main();
        else
            GUI.main();
    }
}
