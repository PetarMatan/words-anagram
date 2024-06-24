package test.beyonnex.service;

import test.beyonnex.model.Word;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AnagramService {

    private final Set<Word> wordSet;

    public AnagramService() {
        this.wordSet = new HashSet<>();
    }

    public boolean checkIsAnagram(String firstText, String secondText) {
        var firstWord = new Word(firstText);
        var secondWord = new Word(secondText);
        this.wordSet.add(firstWord);
        this.wordSet.add(secondWord);

        return firstWord.isAnagram(secondWord);
    }

    public Set<Word> findAllAnagrams(String text) {
        var newWord = new Word(text);
        return this.wordSet.stream()
                           .filter(existingWord -> !existingWord.getText().equals(newWord.getText()))
                           .filter(existingWord -> existingWord.isAnagram(newWord))
                           .collect(Collectors.toSet());
    }
}
