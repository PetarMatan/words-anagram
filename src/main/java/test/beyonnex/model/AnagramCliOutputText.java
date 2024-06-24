package test.beyonnex.model;

import static test.beyonnex.model.AnagramFunctions.*;

public final class AnagramCliOutputText {

    public static final String APPLICATION_START_OUTPUT = """
            To check are two words anagram use %s. Example: %s Petar rateP.
            To find all anagrams for given word use %s. Example: %s Petar.
            To exit the application, enter %s
            """.formatted(IS_ANAGRAM.getFunctionName(), IS_ANAGRAM.getFunctionName(), FIND_ALL_ANAGRAMS.getFunctionName(), FIND_ALL_ANAGRAMS.getFunctionName(),
                          EXIT.getFunctionName());
    public static final String USER_EMPTY_INPUT = "No function name found. Please try again.";
    public static final String UNSUPPORTED_FUNCTION = "Function by name %s doesn't exist, try again. \n";
    public static final String WORD_ARE_ANAGRAM = "Provided words are anagram";
    public static final String WORDS_NOT_ANAGRAM = "Provided words are not anagram";
    public static final String ANAGRAM_WORDS = "All anagrams for provided word: %s%n";
}
