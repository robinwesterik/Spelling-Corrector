import java.util.ArrayList;
import java.util.stream.Collectors;

/* WordHandler class
 * Handles the manipulation of words in a text
 *
 */

public class WordHandler
{
    private ArrayList<Word> words;

    public WordHandler(ArrayList<Word> words)
    {
        this.words = words;
    }

    public void wordFinder(ArrayList<Character> text) //Finds words in a text
    {
        for(int pos = 0; pos < text.size()-1; pos++) //For every position in the text
        {
            char c = text.get(pos);     //Get the current and upcoming character
            char c2 = text.get(pos+1);  //

            if((c == 32 || c == 10  || c == 9) && ((c2 > 64 && c2 < 91)||(c2 > 96 && c2 < 123))) //If word is found (first space, then character)
            {
                StringBuilder word = new StringBuilder("");
                int wordsize = 0;
                //Determine the word size by checking for a character that ends the word (space, comma, etc.)
                for(int i = pos+1; (text.get(i) != 32)&&(text.get(i) != 9)&&(text.get(i) != 10)&&(text.get(i) != 33)&&(text.get(i) != 34)&&(text.get(i) != 63)&&(text.get(i) != 46)&&(text.get(i) != 44)&&(text.get(i) != 41); i++) wordsize++; //Determine the word size
                for(int i = pos+1; i < pos+1+wordsize; i++) //For the detected word
                {
                    word.append(text.get(i)); //Append the characters to a word string
                }
                words.add(new Word(word.toString(), pos+1, pos+1+wordsize)); //Adds the words to the list, giving the string, beginrange, and endrange
            }

        }
    }

    public void wordPlacer(ArrayList<Character> outputText) //Puts the words in a text
    {
        for(int word = words.size() - 1; word >= 0; word--) //For every word, coming from top
        {
            outputText.subList(words.get(word).getBeginRange(), words.get(word).getEndRange()).clear(); //Clear the original word
            outputText.addAll(words.get(word).getBeginRange(), words.get(word).toString().chars().mapToObj((i) -> (char) i).collect(Collectors.toList())); //Put the new word in
        }
    }

    public void wordChecker(SpellChecker spellChecker) //Checks the words for spelling mistakes
    {
        for(Word word : words) //For all the words
        {
            String correctedWord = spellChecker.Check(word.toString()); //Corrects the word
            if (correctedWord != null) //if the word is valid
            {
                word.setWord(correctedWord); //Set the new corrected word
            }
        }
    }
}
