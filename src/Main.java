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
            if (Config.isCountFromFile())
                CLI.fileCounter();
            else
                CLI.hid();
        else
            if (Config.isCountFromFile())
                GUI.fileCounter();
            else
                GUI.main();
    }
}
