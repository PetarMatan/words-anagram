package test.beyonnex.model;

import test.beyonnex.exceptions.*;

import java.util.*;

import static test.beyonnex.util.WordValidator.isCharacterValidLetter;

public class Word {

    public static final int MAX_TEXT_LENGTH = 100;
    private final String text;
    private Map<Character, Integer> histogram;
    private String identifier;

    public Word(String text) {
        if (text == null || text.isEmpty()) {
            throw new EmptyWordException();
        }

        if (text.length() > MAX_TEXT_LENGTH) {
            throw new WordSizeExceededException();
        }

        this.text = text.toLowerCase();
        setHistogram();
        setIdentifier();
    }

    public boolean isAnagramByHistogram(Word word) {    // when used -> O(m * n)
        if (!this.histogram.keySet().equals(word.histogram.keySet())) {
            return false;
        }

        return this.histogram.entrySet().stream()
                             .filter(entry -> !word.histogram.get(entry.getKey()).equals(entry.getValue()))
                             .findAny()
                             .isEmpty();
    }

    public boolean isAnagram(Word word) {   //when used -> O(m + n)
        return this.identifier.equals(word.identifier);
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

    private void setIdentifier() {
        final char ASCII_START = 'a';
        int[] identifier = new int[26];
        for (var letter : text.toCharArray()) {
            if (!isCharacterValidLetter(letter)) {
                throw new InvalidCharacterException(text);
            }
            identifier[letter - ASCII_START]++;
        }

        this.identifier = Arrays.toString(identifier);
    }
}

