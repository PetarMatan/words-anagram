package test.beyonnex;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import test.beyonnex.exceptions.*;
import test.beyonnex.model.Word;
import test.beyonnex.service.AnagramService;

import static org.assertj.core.api.BDDAssertions.catchThrowable;

class AnagramServiceTest {

    @Test
    void shouldCheckIsAnagramGivenValidAnagram() {
        // given
        var anagramResolver = new AnagramService();

        // when
        var result = anagramResolver.checkIsAnagram("test", "estt");

        // then
        BDDAssertions.then(result).isTrue();
    }

    @Test
    void shouldCheckIsAnagramGivenInvalidAnagram() {
        // given
        var anagramResolver = new AnagramService();

        // when
        var result = anagramResolver.checkIsAnagram("test", "teeess");

        // then
        BDDAssertions.then(result).isFalse();
    }

    @Test
    void shouldCheckIsAnagramGivenNullAsInput() {
        // given
        var anagramResolver = new AnagramService();

        // when
        var actual = catchThrowable(() -> anagramResolver.checkIsAnagram(null, null));

        // then
        BDDAssertions.then(actual).isInstanceOf(EmptyWordException.class);
    }

    @Test
    void shouldCheckIsAnagramGivenLargeTextAsInput() {
        // given
        var anagramResolver = new AnagramService();
        var largeText = """
                aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
                aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
                aaaaaaaa
                """;

        // when
        var actual = catchThrowable(() -> anagramResolver.checkIsAnagram("test", largeText));

        // then
        BDDAssertions.then(actual).isInstanceOf(WordSizeExceededException.class);
    }

    @Test
    void shouldFindAllAnagramsGivenValidInput() {
        // given
        var anagramResolver = new AnagramService();
        anagramResolver.checkIsAnagram("test", "estt");

        // when
        var actual = anagramResolver.findAllAnagrams("test");

        // then
        BDDAssertions.then(actual).containsExactly(new Word("estt"));
    }

    @Test
    void shouldNotFindAnagramsGivenValidInput() {
        // given
        var anagramResolver = new AnagramService();
        anagramResolver.checkIsAnagram("test", "estt");

        // when
        var actual = anagramResolver.findAllAnagrams("coding");

        // then
        BDDAssertions.then(actual).isEmpty();
    }

    @Test
    void shouldNotFindAnagramsGivenTooFewArguments() {
        // given
        var anagramResolver = new AnagramService();
        anagramResolver.checkIsAnagram("test", "estt");

        // when
        var actual = catchThrowable(() -> anagramResolver.findAllAnagrams(null));

        // then
        BDDAssertions.then(actual).isInstanceOf(EmptyWordException.class);
    }

    @Test
    void shouldThrowExceptionGivenInvalidCharacterWhenIsAnagramCheck() {
        // given
        var anagramResolver = new AnagramService();

        // when
        var actual = catchThrowable(() -> anagramResolver.checkIsAnagram("test", "es!t"));

        // then
        BDDAssertions.then(actual).isInstanceOf(InvalidCharacterException.class);
    }

    @Test
    void shouldThrowExceptionGivenInvalidCharacterWhenFindAllAnagrasCheck() {
        // given
        var anagramResolver = new AnagramService();
        anagramResolver.checkIsAnagram("test", "estt");

        // when
        var actual = catchThrowable(() -> anagramResolver.findAllAnagrams("test!"));

        // then
        BDDAssertions.then(actual).isInstanceOf(InvalidCharacterException.class);
    }
}