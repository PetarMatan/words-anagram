package test.beyonnex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.beyonnex.model.AnagramFunctions;
import test.beyonnex.service.AnagramCliService;
import test.beyonnex.service.AnagramService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

class AnagramCliServiceTest {

    private final AnagramService anagramService = mock(AnagramService.class);

    @BeforeEach
    void beforeEach() {
        reset(anagramService);
    }

    @Test
    void shouldInvokeIsAnagramGivenValidUserInput() {
        // given
        String input = "%s test estt\nexit".formatted(AnagramFunctions.IS_ANAGRAM.getFunctionName());
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var scanner = new Scanner(in);

        // when
        AnagramCliService.runReader(scanner, anagramService);

        // then
        then(anagramService).should().checkIsAnagram("test", "estt");
        then(anagramService).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldInvokeFindAllAnagramsGivenValidUserInput() {
        // given
        String input = "%s test\nexit".formatted(AnagramFunctions.FIND_ALL_ANAGRAMS.getFunctionName());
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var scanner = new Scanner(in);

        // when
        AnagramCliService.runReader(scanner, anagramService);

        // then
        then(anagramService).should().findAllAnagrams("test");
        then(anagramService).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldNotDoAnythingGivenInvalidInput() {
        // given
        String input = " \nexit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var scanner = new Scanner(in);

        // when
        AnagramCliService.runReader(scanner, anagramService);

        // then
        then(anagramService).shouldHaveNoInteractions();
    }

    @Test
    void shouldHandleAsUnsupportedFunctionGivenInvalidInput() {
        // given
        String input = "madeUpName\nexit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var scanner = new Scanner(in);

        // when
        AnagramCliService.runReader(scanner, anagramService);

        // then
        then(anagramService).shouldHaveNoInteractions();
    }

    @Test
    void shouldExitApplicationGivenExitInput() {
        // given
        String input = "exit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var scanner = new Scanner(in);

        // when
        AnagramCliService.runReader(scanner, anagramService);

        // then
        then(anagramService).shouldHaveNoInteractions();
    }

    @Test
    void shouldDoNothingGivenIsAnagramTooManyArguments() {
        // given
        String input = "%s test estt abc\nexit".formatted(AnagramFunctions.IS_ANAGRAM.getFunctionName());
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var scanner = new Scanner(in);

        // when
        AnagramCliService.runReader(scanner, anagramService);

        // then
        then(anagramService).shouldHaveNoInteractions();
    }

    @Test
    void shouldDoNothingGivenFindAllAnagramsTooManyArguments() {
        // given
        String input = "%s test estt\nexit".formatted(AnagramFunctions.FIND_ALL_ANAGRAMS.getFunctionName());
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var scanner = new Scanner(in);

        // when
        AnagramCliService.runReader(scanner, anagramService);

        // then
        then(anagramService).shouldHaveNoInteractions();
    }
}