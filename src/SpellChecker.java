import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/* SpellChecker class
 * Given a string, SpellChecker checks for spelling errors.
 *
 */

public class SpellChecker
{
    private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray(); //Creates the alphabet to a character array for convenience in check functions
    private HashMap<String, Integer> dict;

    SpellChecker(HashMap<String, Integer> dict) //Upon creating the function require our dictionary
    {
        this.dict = dict;
    }

    public String Check(String word)
    {
        HashSet<String> listCheck = new HashSet<>(); //HashSet to ignore duplicates
        HashSet<String> finalListCheck = new HashSet<>(); //HashSet to ignore duplicates
        String output;

            if (dict.containsKey(word.toLowerCase())) output = word; //First check if the word is already in the dictionary
            else //If the word is not
            {
                listCheck.addAll(deletionCheck(true, word));        //Assume the distance is 1 and perform a check on all functions
                listCheck.addAll(transpositionCheck(true, word));   //
                listCheck.addAll(insertionCheck(true, word));       //
                listCheck.addAll(replacementCheck(true, word));     //

                if (listCheck.isEmpty()) //If our distance 1 check didn't give any results
                {
                    listCheck.addAll(deletionCheck(false, word));       //Collect all distance 1 checks
                    listCheck.addAll(transpositionCheck(false, word));  //
                    listCheck.addAll(insertionCheck(false, word));      //
                    listCheck.addAll(replacementCheck(false, word));    //

                    for(String candidate : listCheck)   //For all distance 1 checks
                    {
                        finalListCheck.addAll(deletionCheck(true, candidate));      //Perform a check on all functions for our candidate, giving distance 2 checks
                        finalListCheck.addAll(transpositionCheck(true, candidate)); //
                        finalListCheck.addAll(insertionCheck(true, candidate));     //
                        finalListCheck.addAll(replacementCheck(true, candidate));   //
                    }
                    output = priorityCheck(finalListCheck); //
                    if(output.isEmpty()) output = word; //If we haven't found anything, assume that it's an unknown word
                }
                else
                {
                    output = priorityCheck(listCheck); //Get all possible words and see which is the most used, use that for our output
                }


            }
            return output;
    }

    private String priorityCheck(HashSet<String> list) //Checks which words are the most frequent from a list of words in a dictionary
    {
        String priority = "";
        int frequency = 0;
        for(String word : list) //For all the words in the list
        {
            if(dict.get(word.toLowerCase())>frequency) //If the frequency is higher
            {
                priority = word;                            //Make this the priority word
                frequency = dict.get(word.toLowerCase());   //
            }
        }
        return priority;
    }


    private ArrayList<String> deletionCheck(boolean doTest, String input) //Checks for deletion
    {
        ArrayList<String> toCorrect = new ArrayList<>(); //Create one list for the checked possibilities
        ArrayList<String> toReturn = new ArrayList<>(); //Create one list for unchecked possibilities
            for (int i = 0; i < input.length(); i++)
            {

                String working = input.substring(0, i);              //try removing each char between the first and last
                working = working.concat(input.substring((i + 1)));  //

                if (dict.containsKey(working.toLowerCase()) && doTest) toCorrect.add(working); //If it's in the dictionary and we test it, add it to correct
                if(!doTest) toReturn.add(working);                                             //If we don't test it, add it to return
            }
            if(doTest) return toCorrect;
            else return toReturn;
    }

    private ArrayList<String> transpositionCheck(boolean doTest, String input) //Checks for swapped letters
    {
        ArrayList<String> toReturn = new ArrayList<>();
        ArrayList<String> toCorrect = new ArrayList<>();
        for (int i = 0; i < input.length() - 1; i++) //For every character
        {
            String working = input.substring(0, i);             //Swap the character with the one right from you
            working = working + input.charAt(i + 1);            //
            working = working + input.charAt(i);                //
            working = working.concat(input.substring((i + 2))); //

            if (dict.containsKey(working.toLowerCase()) && doTest) toCorrect.add(working);
            if(!doTest) toReturn.add(working);
        }
        if(doTest) return toCorrect;
        else return toReturn;
    }

    private ArrayList<String> insertionCheck(boolean doTest, String input) //Checks for missing letters in a word
    {
        ArrayList<String> toReturn = new ArrayList<>();
        ArrayList<String> toCorrect = new ArrayList<>();

        for (int i = 0; i < input.length() + 1; i++)
        {
            for(char c : alphabet)
            {
                String working = new StringBuilder(input).insert(i, c).toString(); //Inserts every letter of the alphabet in between every letter in the word
                if (dict.containsKey(working.toLowerCase()) && doTest) toCorrect.add(working);
                if(!doTest) toReturn.add(working);
            }
        }
        if(doTest) return toCorrect;
        else return toReturn;
    }

    private ArrayList<String> replacementCheck(boolean doTest, String input) //Checks for accidental replacement of a letter in a word
    {
        ArrayList<String> toReturn = new ArrayList<>();
        ArrayList<String> toCorrect = new ArrayList<>();

        for (int i = 0; i < input.length() - 1; i++)
        {
            for(char c : alphabet)
            {
                String working = input.replace(input.charAt(i), c);  //Replaces every letter of the alphabet in place of every letter in the word
                if (dict.containsKey(working.toLowerCase()) && doTest) toCorrect.add(working);
                if(!doTest) toReturn.add(working);
            }
        }
        if(doTest) return toCorrect;
        else return toReturn;
    }
}
