import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/* TextHandler class
 * Handles text files, converting text files in usable format for the program and vice versa.
 *
 */

public class TextHandler
{

    public ArrayList<Character> get(Path iPath) //Reads input from text file, outputs it in the desired char format
    {
        ArrayList<Character> inputText = new ArrayList<>();

        try(var br = Files.newBufferedReader(iPath)) //Try creating a reader upon the given path
        {
            int input;
            while((input = br.read()) != -1) //While our input is not EOF
            {
                inputText.add((char)input); //Add the input to our character array
            }
            inputText.add((char)32); //Adds spaces for later formatting
            inputText.add(0, (char)32); //

            return inputText;

        }
        catch(IOException e) //If the path is wrong, let the user know
        {
            Interaction.pathWrong("Input");
        }
        return null;
    }

    public void put(Path oPath, ArrayList<Character> outputText) //Writes input to text file
    {
        try(var bw = Files.newBufferedWriter(oPath)) //Try creating a writer upon the given path
        {
            outputText.remove(0); //Removes the formatting done in the get function
            outputText.remove(outputText.size()-1); //

            for (Character character : outputText) //for each character in the output text
            {
                bw.write((int) character); //Write it to the text file
            }

        }
        catch(IOException e) //If the path is wrong let the user know
        {
            Interaction.pathWrong("Output");
        }
    }
}

