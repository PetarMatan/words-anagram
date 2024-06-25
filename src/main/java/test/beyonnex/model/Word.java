package test.beyonnex.model;

import test.beyonnex.exceptions.EmptyWordException;
import test.beyonnex.exceptions.WordSizeExceededException;

import java.util.HashMap;
import java.util.Map;

public class Word {

    public static final int MAX_TEXT_LENGTH = 100;
    private final String text;
    private Map<Character, Integer> histogram;

    public Word(String text) {
        if (text == null || text.isEmpty()) {
            throw new EmptyWordException();
        }

        if (text.length() > MAX_TEXT_LENGTH) {
            throw new WordSizeExceededException();
        }

        this.text = text.toLowerCase();
        setHistogram();
    }

    public boolean isAnagram(Word word) {
        if (!this.histogram.keySet().equals(word.histogram.keySet())) {
            return false;
        }

        return this.histogram.entrySet().stream()
                             .filter(entry -> !word.histogram.get(entry.getKey()).equals(entry.getValue()))
                             .findAny()
                             .isEmpty();
    }

    @Override
    public String toString() {
        return this.text;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Word word)) {
            return false;
        }

        return this.text.equals(word.getText());
    }

    @Override
    public int hashCode() {
        return this.text.hashCode();
    }

    public String getText() {
        return this.text;
    }

    private void setHistogram() {
        histogram = new HashMap<>();
        for (var letter : text.toCharArray()) {
            histogram.put(letter, histogram.getOrDefault(letter, 0) + 1);
        }
    }
}

