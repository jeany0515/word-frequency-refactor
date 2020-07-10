import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String INPUT_SEPARATOR = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {

        try {
            List<Word> wordList = getDistinctWords(sentence);

            List<Word> sortedList = wordList.stream()
                    .sorted((word1, word2) -> word2.getCount() - word1.getCount())
                    .collect(Collectors.toList());

            return convertWordFrequencyString(sortedList).toString();
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private StringJoiner convertWordFrequencyString(List<Word> sortedList) {
        StringJoiner joiner = new StringJoiner("\n");
        sortedList.forEach(word -> {
            String frequencyString = String.format("%s %s", word.getValue(), word.getCount());
            joiner.add(frequencyString);
        });

        return joiner;
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
