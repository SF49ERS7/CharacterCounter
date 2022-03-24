import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Handles the parsing of inputted data.
 */
public class Backend
{
    /**
     * Stores the program's version number
     */
    private static final String programVersion = "dev";
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
     * Takes the <code>data</code> parameter and calculates how many of each character there are in it.
     * @param data The input generated at the beginning of the program.
     * @return The number of each characters found.
     */
    public static long[] charCounter(String data)
    {
        long[] numsOfChars = new long[39];
        for (long num : numsOfChars)
            numsOfChars[(int) num] = 0;

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
                case '0' -> numsOfChars[26]++;
                case '1' -> numsOfChars[27]++;
                case '2' -> numsOfChars[28]++;
                case '3' -> numsOfChars[29]++;
                case '4' -> numsOfChars[30]++;
                case '5' -> numsOfChars[31]++;
                case '6' -> numsOfChars[32]++;
                case '7' -> numsOfChars[33]++;
                case '8' -> numsOfChars[34]++;
                case '9' -> numsOfChars[35]++;
                case ' ' -> numsOfChars[36]++;
                default -> numsOfChars[37]++;
            }
        numsOfChars[38] = data.length();
        return numsOfChars;
    }
    /**
     * Formats the <code>numsOfChars</code> parameter to fit each number of characters.
     * @param numsOfChars The array that tells how many of each character there are.
     * @return A concatenated <code>String</code> with everything formatted.
     */
    public static String outputMessage(long[] numsOfChars)
    {
        StringBuilder sb = new StringBuilder("Number of characters:\n");
        sb.append(filterValues("A: ", numsOfChars[0]));
        sb.append(filterValues("B: ", numsOfChars[1]));
        sb.append(filterValues("C: ", numsOfChars[2]));
        sb.append(filterValues("D: ", numsOfChars[3]));
        sb.append(filterValues("E: ", numsOfChars[4]));
        sb.append(filterValues("F: ", numsOfChars[5]));
        sb.append(filterValues("G: ", numsOfChars[6]));
        sb.append(filterValues("H: ", numsOfChars[7]));
        sb.append(filterValues("I: ", numsOfChars[8]));
        sb.append(filterValues("J: ", numsOfChars[9]));
        sb.append(filterValues("K: ", numsOfChars[10]));
        sb.append(filterValues("L: ", numsOfChars[11]));
        sb.append(filterValues("M: ", numsOfChars[12]));
        sb.append(filterValues("N: ", numsOfChars[13]));
        sb.append(filterValues("O: ", numsOfChars[14]));
        sb.append(filterValues("P: ", numsOfChars[15]));
        sb.append(filterValues("Q: ", numsOfChars[16]));
        sb.append(filterValues("R: ", numsOfChars[17]));
        sb.append(filterValues("S: ", numsOfChars[18]));
        sb.append(filterValues("T: ", numsOfChars[19]));
        sb.append(filterValues("U: ", numsOfChars[20]));
        sb.append(filterValues("V: ", numsOfChars[21]));
        sb.append(filterValues("W: ", numsOfChars[22]));
        sb.append(filterValues("X: ", numsOfChars[23]));
        sb.append(filterValues("Y: ", numsOfChars[24]));
        sb.append(filterValues("Z: ", numsOfChars[25]));
        if (Config.isShowNumsIndependently())
        {
            sb.append(filterValues("0: ", numsOfChars[26]));
            sb.append(filterValues("1: ", numsOfChars[27]));
            sb.append(filterValues("2: ", numsOfChars[28]));
            sb.append(filterValues("3: ", numsOfChars[29]));
            sb.append(filterValues("4: ", numsOfChars[30]));
            sb.append(filterValues("5: ", numsOfChars[31]));
            sb.append(filterValues("6: ", numsOfChars[32]));
            sb.append(filterValues("7: ", numsOfChars[33]));
            sb.append(filterValues("8: ", numsOfChars[34]));
            sb.append(filterValues("9: ", numsOfChars[35]));
        }
        else
            sb.append(filterValues("Numbers: ", numsOfChars[26] + numsOfChars[27] + numsOfChars[28] + numsOfChars[29] + numsOfChars[30] + numsOfChars[31] + numsOfChars[32] + numsOfChars[33] + numsOfChars[34] + numsOfChars[35]));
        sb.append(filterValues("Spaces: ", numsOfChars[36]));
        sb.append(filterValues("Other Characters: ", numsOfChars[37]));
        if (Config.isShowTotal())
            sb.append(filterValues("Total Characters: ", numsOfChars[38]));
        return String.valueOf(sb);
    }
    /**
     * Ensures that only non-zero values are displayed to the user.
     * @param prefix What character is being counted.
     * @param input The number of characters, pre-counted.
     * @return The filtered and formatted value.
     */
    public static String filterValues(String prefix, long input)
    {
        if (input == 0 && !Config.isShowEmptyVals())
            return "";
        else
            return prefix + format(input) + "\n";
    }
    /**
     * Formats an inputted number.
     * @param input The number to format.
     * @return The formatted number.
     */
    public static String format(long input)
    {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(input);
    }
    /**
     * Gets the path to the folder containing the file.
     * @param pathToFile The file path.
     * @param fileName The name of the file.
     * @return The folder path.
     */
    public static String formatPathToFolder(String pathToFile, String fileName)
    {
        StringBuilder sb = new StringBuilder(pathToFile);
        sb.delete(sb.indexOf(fileName), sb.length());
        return String.valueOf(sb);
    }
    /**
     * Writes the output to a file.
     * @param numsOfChars The array holding the counted characters.
     * @param pathToFile The path to the file.
     * @throws IOException If the directory is read-only.
     */
    public static void writeOutputToFile(long[] numsOfChars, String pathToFile) throws IOException
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy 'at' HH:mm:ss a zzz");

        FileWriter fw = new FileWriter(pathToFile);
        fw.write("#Character Counter, version " + getProgramVersion() + "\n");
        fw.write("#Generated on " + formatter.format(date) + "\n\n");
        fw.write(outputMessage(numsOfChars));
        fw.close();
    }
}
