import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/* DictHandler class
 * Handles the creation of a dictionary
 *
 */

public class DictHandler
{

    public void get(Path path, HashMap<String, Integer> dict) //Given a path, handles creating a dictionary
    {
        try(var br = Files.newBufferedReader(path)) //Try creating a reader upon the given path
        {
            String line;
            while ((line = br.readLine()) != null) //While our input is not EOF
            {
                List<Character> text = line.chars().mapToObj((i) -> (char) i).collect(Collectors.toList()); //Stream the input to a text list

                for(int pos = 0; pos < text.size()-1; pos++) //For every position in the text
                {
                    char c = text.get(pos);     //Get the current and upcoming character
                    char c2 = text.get(pos+1);  //

                    if((c == 32 || c == 10  || c == 9) && ((c2 > 64 && c2 < 91)||(c2 > 96 && c2 < 123))) //If word is found (first space, then character)
                    {
                        StringBuilder word = new StringBuilder("");
                        int wordsize = 0;
                        //Determine the word size by checking for a character that ends the word (space, comma, etc.)
                        for(int i = pos+1; (text.get(i) != 32)&&(text.get(i) != 9)&&(text.get(i) != 10)&&(text.get(i) != 33)&&(text.get(i) != 34)&&(text.get(i) != 63)&&(text.get(i) != 46)&&(text.get(i) != 44)&&(text.get(i) != 41); i++) wordsize++;
                        for(int i = pos+1; i < pos+1+wordsize; i++) //For the detected word
                        {
                            word.append(Character.toLowerCase(text.get(i))); //Append the characters to a word string, lower casing the characters
                        }
                        if(dict.containsKey(word.toString())) //If the word already exists in the dictionary
                        {
                            dict.replace(word.toString(), dict.get(word.toString())+1); //++ the frequency of the word
                        }
                        else //If the word does not already exist
                        {
                            dict.put(word.toString(), 1); //Put the new word in the dictionary with frequency 1
                        }
                    }

                }
            }

        }
        catch(IOException e) //If the path is wrong, let the user know
        {
            Interaction.pathWrong("Dictionary");
        }
    }

}

