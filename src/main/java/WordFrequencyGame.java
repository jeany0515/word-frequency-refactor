import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String INPUT_SEPARATOR = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {

        try {
            List<Word> wordList = getDistinctWords(inputStr);

            wordList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            StringJoiner joiner = new StringJoiner("\n");
            for (Word w : wordList) {
                String s = w.getValue() + " " +w.getWordCount();
                joiner.add(s);
            }
            return joiner.toString();
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<Word> getDistinctWords(String inputStr) {
        List<String> words = Arrays.asList(inputStr.split(INPUT_SEPARATOR));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());

        List<Word> wordList = new ArrayList<>();
        distinctWords.forEach(distinctWord -> {
            int wordCount = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            Word word = new Word(distinctWord, wordCount);
            wordList.add(word);
        });

        return wordList;
    }

}
