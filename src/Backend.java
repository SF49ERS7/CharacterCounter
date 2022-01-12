import java.io.FileWriter;
import java.io.IOException;
/**
 * Handles the parsing of inputted data, as well as the aforementioned outputting to a text file.
 */
public class Backend
{
    /** Takes the <code>input</code> parameter and determines if it is the quit command. It also sets it to lowercase, allowing it to be parsed properly.
     * @param input The <code>String</code> to be parsed
     * @return The parsed string, set to lowercase
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
     * @param output The data to be sent to the disk
     */
    public static void sendToTextFile(String output, boolean isGui)
    {
        try {
            FileWriter outputFile = new FileWriter("output.txt");
            outputFile.write(output);
            outputFile.close();
        } catch (IOException e) {
            if (isGui)
                GUI.displayFileError();
            else
                CLI.displayFileError();
        }
    }

    /**
     * This method takes the <code>data</code> parameter and calculates how many of each character there are in it.
     * @param data The input generated at the beginning of the program
     * @return The number of each characters found
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
     * This method formats the <code>numsOfChars</code> parameter to fit each number of characters.
     * @param numsOfChars The array that tells how many of each character there are
     * @return A concatenated string with everything formatted
     */
    public static String outputMessage(int[] numsOfChars)
    {
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
    }
}
