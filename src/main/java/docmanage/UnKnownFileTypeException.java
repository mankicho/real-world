package docmanage;

public class UnKnownFileTypeException extends RuntimeException {

    public UnKnownFileTypeException() {
    }

    public UnKnownFileTypeException(String message) {
        super(message);
    }
}
