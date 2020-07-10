public class Word {
    private String value;
    private int count;

    public Word(String word, int count) {
        this.value = word;
        this.count = count;
    }

    public String getValue() {
        return this.value;
    }

    public int getCount() {
        return this.count;
    }
}
