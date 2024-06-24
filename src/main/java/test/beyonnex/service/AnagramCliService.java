package test.beyonnex.service;

import test.beyonnex.model.AnagramFunctions;

import java.util.Scanner;

import static test.beyonnex.model.AnagramCliOutputText.*;

public final class AnagramCliService {

    private static final String DELIMITER = " ";

    public static void runReader(Scanner scanner, AnagramService anagramService) {
        boolean running = true;

        while (running) {
            System.out.println(APPLICATION_START_OUTPUT);
            String[] input = scanner.nextLine().split(DELIMITER);
            if (input.length < 1) {
                System.out.println(USER_EMPTY_INPUT);
                continue;
            }

            AnagramFunctions function = AnagramFunctions.ofName(input[0]);
            try {
                switch (function) {
                    case IS_ANAGRAM:
                        isAnagram(input, anagramService);
                        break;
                    case FIND_ALL_ANAGRAMS:
                        findAllAnagrams(input, anagramService);
                        break;
                    case EXIT:
                        running = false;
                        break;
                    case UNSUPPORTED:
                        System.out.printf(UNSUPPORTED_FUNCTION, input[0]);
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void isAnagram(String[] input, AnagramService anagramService) {
        if (input.length != 3) {
            System.out.println("Incorrect arguments provided, try again.");
            return;
        }

        var isAnagram = anagramService.checkIsAnagram(input[1], input[2]);
        System.out.println(isAnagram ? WORD_ARE_ANAGRAM : WORDS_NOT_ANAGRAM);
    }

    private static void findAllAnagrams(String[] input, AnagramService anagramService) {
        if (input.length != 2) {
            System.out.println("Incorrect arguments provided, try again.");
            return;
        }

        System.out.printf(ANAGRAM_WORDS, anagramService.findAllAnagrams(input[1]));
    }
}
