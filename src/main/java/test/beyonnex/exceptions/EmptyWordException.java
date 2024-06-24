package test.beyonnex.exceptions;

public class EmptyWordException extends RuntimeException {

    private final static String ERROR_LOG = "Input word cannot be empty";

    public EmptyWordException() {
        super(ERROR_LOG);
    }
}
