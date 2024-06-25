package test.beyonnex.exceptions;

import static test.beyonnex.model.Word.MAX_TEXT_LENGTH;

public class WordSizeExceededException extends RuntimeException {

    private final static String ERROR_LOG = "Input word size is over %s".formatted(MAX_TEXT_LENGTH);

    public WordSizeExceededException() {
        super(ERROR_LOG);
    }
}
