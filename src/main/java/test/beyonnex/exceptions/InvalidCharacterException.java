package test.beyonnex.exceptions;

public class InvalidCharacterException extends RuntimeException {

    private final static String ERROR_LOG = "Input word %s can only have letters";

    public InvalidCharacterException(String word) {
        super(ERROR_LOG.formatted(word));
    }
}
