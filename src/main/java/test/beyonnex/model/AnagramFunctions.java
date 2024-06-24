package test.beyonnex.model;

import java.util.Arrays;

public enum AnagramFunctions {

    IS_ANAGRAM("isAnagram"),
    FIND_ALL_ANAGRAMS("allAnagrams"),
    EXIT("exit"),
    UNSUPPORTED("unsupported");

    private final String functionName;

    AnagramFunctions(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return this.functionName;
    }

    public static AnagramFunctions ofName(String name) {
        return Arrays.stream(values())
                     .filter(anagramFunctions -> anagramFunctions.getFunctionName().equals(name))
                     .findFirst()
                     .orElse(AnagramFunctions.UNSUPPORTED);
    }
}
