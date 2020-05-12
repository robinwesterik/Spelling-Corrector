import java.util.Scanner;

/* Interaction class
 * Handles user interaction with the program
 *
 */

public class Interaction
{
    public static String askInputPath()
    {
        System.out.println("Please enter the input file:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String askOutputPath()
    {
        System.out.println("Please enter the output file:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void pathWrong(String path)
    {
        System.out.println(path+" path is incorrect. Quitting now..");
    }

    public static void done()
    {
        System.out.println("Spelling correction completed.");
    }
}
