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
        if (args.length != 0 && args[0].equals("-cmd"))
            CLI.main();
        else
            GUI.main(args);
    }
}
