
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/* Main class
 * Orchestrates a Spelling Corrector
 *
 */

public class Main
{
    public static void main(String[] args)
    {
        var iPath = Paths.get(Interaction.askInputPath());
        var oPath = Paths.get(Interaction.askOutputPath());
        var wikiPath = Paths.get("eng_wikipedia_2016_300K-sentences.txt");
        var newsPath = Paths.get("eng_news_2016_300K-sentences.txt");

        var dict = new HashMap<String, Integer>();
        var text = new ArrayList<Character>();
        var words = new ArrayList<Word>();

        TextHandler textHandler = new TextHandler();
        WordHandler wordHandler = new WordHandler(words);
        DictHandler dictHandler = new DictHandler();
        SpellChecker spellChecker = new SpellChecker(dict);

        dictHandler.get(wikiPath, dict);
        text = textHandler.get(iPath);
        wordHandler.wordFinder(text);
        wordHandler.wordChecker(spellChecker);
        wordHandler.wordPlacer(text);
        textHandler.put(oPath, text);

        Interaction.done();
    }
}
