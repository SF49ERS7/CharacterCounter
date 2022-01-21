import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Handles the parsing of inputted data, as well as the aforementioned outputting to a text file.
 */
public class Backend
{
    /**
     * Stores the program's version number
     */
    private static final String programVersion = "0.3.2-alpha";
    /**
     * Getter for <code>programVersion</code>.
     * @return The program's version.
     */
    public static String getProgramVersion()
    {
        return programVersion;
    }
    /** Takes the <code>input</code> parameter and determines if it is the quit command. It also sets it to lowercase, allowing it to be parsed properly.
     * @param input The <code>String</code> to be parsed.
     * @return The parsed string, set to lowercase.
     */
    public static String parseData(String input)
    {
        try {
            if (input.equals(":quit"))
                System.exit(0);
        } catch (NullPointerException e) {
            System.exit(0);
        }
        return input.toLowerCase();
    }
    /**
     * Sends the <code>output</code> parameter to a file on the disk.
     * @param output The data to be sent to the disk.
     */
    public static void sendToTextFile(String output)
    {
        try {
            Date date = new Date(); // This object contains the current date value
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy 'at' HH:mm:ss");

            FileWriter outputFile = new FileWriter("output.txt");
            outputFile.write("#Character Counter File Output, by SF49ERS7\n#Version " + getProgramVersion() + "\n#Generated on " + formatter.format(date) + "\n\n" + output);
            outputFile.close();
        } catch (IOException e) {
            if (Config.getRunUI().equals("GUI"))
                GUI.displayFileError();
            else
                CLI.displayFileError();
        }
    }
    /**
     * Takes the <code>data</code> parameter and calculates how many of each character there are in it.
     * @param data The input generated at the beginning of the program.
     * @return The number of each characters found.
     */
    public static int[] charCounter(String data)
    {
        int[] numsOfChars = new int[29];
        for (int num : numsOfChars)
            numsOfChars[num] = 0;

        for (int i = 0; i < data.length(); i++)
            switch (data.charAt(i)) {
                case 'a' -> numsOfChars[0]++;
                case 'b' -> numsOfChars[1]++;
                case 'c' -> numsOfChars[2]++;
                case 'd' -> numsOfChars[3]++;
                case 'e' -> numsOfChars[4]++;
                case 'f' -> numsOfChars[5]++;
                case 'g' -> numsOfChars[6]++;
                case 'h' -> numsOfChars[7]++;
                case 'i' -> numsOfChars[8]++;
                case 'j' -> numsOfChars[9]++;
                case 'k' -> numsOfChars[10]++;
                case 'l' -> numsOfChars[11]++;
                case 'm' -> numsOfChars[12]++;
                case 'n' -> numsOfChars[13]++;
                case 'o' -> numsOfChars[14]++;
                case 'p' -> numsOfChars[15]++;
                case 'q' -> numsOfChars[16]++;
                case 'r' -> numsOfChars[17]++;
                case 's' -> numsOfChars[18]++;
                case 't' -> numsOfChars[19]++;
                case 'u' -> numsOfChars[20]++;
                case 'v' -> numsOfChars[21]++;
                case 'w' -> numsOfChars[22]++;
                case 'x' -> numsOfChars[23]++;
                case 'y' -> numsOfChars[24]++;
                case 'z' -> numsOfChars[25]++;
                case '1' -> numsOfChars[26]++;
                case '2' -> numsOfChars[26]++;
                case '3' -> numsOfChars[26]++;
                case '4' -> numsOfChars[26]++;
                case '5' -> numsOfChars[26]++;
                case '6' -> numsOfChars[26]++;
                case '7' -> numsOfChars[26]++;
                case '8' -> numsOfChars[26]++;
                case '9' -> numsOfChars[26]++;
                case '0' -> numsOfChars[26]++;
                case ' ' -> numsOfChars[27]++;
                default -> numsOfChars[28]++;
            }
        return numsOfChars;
    }
    /**
     * Formats the <code>numsOfChars</code> parameter to fit each number of characters.
     * @param numsOfChars The array that tells how many of each character there are.
     * @return A concatenated <code>String</code> with everything formatted.
     */
    public static String outputMessage(int[] numsOfChars)
    {
        if (Config.getEnabledSettings()[2])
            return "Number of characters:\n" +
                    "A: " + numsOfChars[0] + "\n" +
                    "B: " + numsOfChars[1] + "\n" +
                    "C: " + numsOfChars[2] + "\n" +
                    "D: " + numsOfChars[3] + "\n" +
                    "E: " + numsOfChars[4] + "\n" +
                    "F: " + numsOfChars[5] + "\n" +
                    "G: " + numsOfChars[6] + "\n" +
                    "H: " + numsOfChars[7] + "\n" +
                    "I: " + numsOfChars[8] + "\n" +
                    "J: " + numsOfChars[9] + "\n" +
                    "K: " + numsOfChars[10] + "\n" +
                    "L: " + numsOfChars[11] + "\n" +
                    "M: " + numsOfChars[12] + "\n" +
                    "N: " + numsOfChars[13] + "\n" +
                    "O: " + numsOfChars[14] + "\n" +
                    "P: " + numsOfChars[15] + "\n" +
                    "Q: " + numsOfChars[16] + "\n" +
                    "R: " + numsOfChars[17] + "\n" +
                    "S: " + numsOfChars[18] + "\n" +
                    "T: " + numsOfChars[19] + "\n" +
                    "U: " + numsOfChars[20] + "\n" +
                    "V: " + numsOfChars[21] + "\n" +
                    "W: " + numsOfChars[22] + "\n" +
                    "X: " + numsOfChars[23] + "\n" +
                    "Y: " + numsOfChars[24] + "\n" +
                    "Z: " + numsOfChars[25] + "\n" +
                    "Numbers: " + numsOfChars[26] + "\n" +
                    "Spaces: " + numsOfChars[27] + "\n" +
                    "Other Characters: " + numsOfChars[28];
        else
            return "Number of characters:\n" +
                filterValues("A: ", numsOfChars[0], false) +
                filterValues("B: ", numsOfChars[1], false) +
                filterValues("C: ", numsOfChars[2], false) +
                filterValues("D: ", numsOfChars[3], false) +
                filterValues("E: ", numsOfChars[4], false) +
                filterValues("F: ", numsOfChars[5], false) +
                filterValues("G: ", numsOfChars[6], false) +
                filterValues("H: ", numsOfChars[7], false) +
                filterValues("I: ", numsOfChars[8], false) +
                filterValues("J: ", numsOfChars[9], false) +
                filterValues("K: ", numsOfChars[10], false) +
                filterValues("L: ", numsOfChars[11], false) +
                filterValues("M: ", numsOfChars[12], false) +
                filterValues("N: ", numsOfChars[13], false) +
                filterValues("O: ", numsOfChars[14], false) +
                filterValues("P: ", numsOfChars[15], false) +
                filterValues("Q: ", numsOfChars[16], false) +
                filterValues("R: ", numsOfChars[17], false) +
                filterValues("S: ", numsOfChars[18], false) +
                filterValues("T: ", numsOfChars[19], false) +
                filterValues("U: ", numsOfChars[20], false) +
                filterValues("V: ", numsOfChars[21], false) +
                filterValues("W: ", numsOfChars[22], false) +
                filterValues("X: ", numsOfChars[23], false) +
                filterValues("Y: ", numsOfChars[24], false) +
                filterValues("Z: ", numsOfChars[25], false) +
                filterValues("Numbers: ", numsOfChars[26], false) +
                filterValues("Spaces: ", numsOfChars[27], false) +
                filterValues("Other Characters: ", numsOfChars[28], true);
    }
    /**
     * Ensures that only non-zero values are displayed to the user.
     * @param prefix What character is being counted.
     * @param input The number of characters, pre-counted.
     * @param isAtTheEnd Determines if the value should have a carriage return or not.
     * @return The filtered value.
     */
    public static String filterValues(String prefix, int input, boolean isAtTheEnd)
    {
        if (input == 0 && !Config.getEnabledSettings()[2])
            return "";
        else if (!isAtTheEnd)
            return prefix + input + "\n";
        else
            return prefix + input;
    }
}
