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
}
