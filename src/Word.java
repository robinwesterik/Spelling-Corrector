/* Word class
 * Defines a word
 *
 */

public class Word
{
    private String word;
    private int beginRange;
    private int endRange;

    public Word(String word, int beginRange, int endRange) //A word contains a string, begin range, and end range in the text
    {
        this.word = word;
        this.beginRange = beginRange;
        this.endRange = endRange;
    }

    public int getBeginRange() {
        return beginRange;
    }

    public int getEndRange() {
        return endRange;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }
}
