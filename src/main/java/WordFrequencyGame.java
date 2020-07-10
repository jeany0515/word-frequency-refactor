import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String INPUT_SEPARATOR = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {

        try {
            //split the input string with 1 to n pieces of spaces
            String[] arr = inputStr.split(INPUT_SEPARATOR);

            List<Word> wordList = new ArrayList<>();
            for (String s : arr) {
                Word word = new Word(s, 1);
                wordList.add(word);
            }

            //get the map for the next step of sizing the same word
            Map<String, List<Word>> map =getListMap(wordList);

            List<Word> list = new ArrayList<>();
            for (Map.Entry<String, List<Word>> entry : map.entrySet()) {
                Word word = new Word(entry.getKey(), entry.getValue().size());
                list.add(word);
            }


            wordList = list;

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

    private Map<String, List<Word>> getListMap(List<Word> wordList) {
        Map<String, List<Word>> map = new HashMap<>();
        for (Word word : wordList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(word.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(word);
                map.put(word.getValue(), arr);
            }
            else {
                map.get(word.getValue()).add(word);
            }
        }
        return map;
    }
}
