/**
 * The main class in the program.
 */
public class Main
{
    /**
     * The only runnable method in the whole program.
     * @param args Any inputted command-line arguments.
     */
    public static void main(String[] args)
    {
        Config.applySettingsCLI(args);

        if (Config.getEnabledSettings()[0])
            CLI.main();
        else
            GUI.main();
    }
}
