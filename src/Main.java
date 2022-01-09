/**
 * The main class in the program.
 */
public class Main
{
    /**
     * Runs the GUI for the program, used in most cases
     * @param args The command-line arguments, used only for the file output prompt
     */
    public static void runGui(String[] args)
    {
        do {
            String input = GUI.displayInput();
            String data = Backend.parseData(input);
            if (input.equals(""))
            {
                GUI.noInputError();
                continue;
            }

            int[] numsOfChars = Backend.charCounter(data);

            GUI.displayOutput(numsOfChars);

            if (args.length == 0)
                GUI.promptForFileOutput(Backend.outputMessage(numsOfChars));
        } while (true);
    }
    /**
     * Runs the command-line interface, used rarely
     */
    public static void runCli()
    {
        String input = CLI.displayInput();
        String data = Backend.parseData(input);
        if (input.equals(""))
        {
            CLI.noInputError();
            System.exit(0);
        }

        int[] numsOfChars = Backend.charCounter(data);

        CLI.displayOutput(numsOfChars);
    }
    /**
     * The only runnable method in the whole program.
     * @param args Any inputted command-line arguments.
     */
    public static void main(String[] args)
    {
        if (args.length != 0 && args[0].equals("-cmd"))
            runCli();
        else
            runGui(args);
    }
}
