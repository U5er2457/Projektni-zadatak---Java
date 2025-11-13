package entity.Exception;

public class NegativeSubjectsException extends RuntimeException {
    public NegativeSubjectsException() {
        super("Broj kolegija je negativan! Mora biti > 0!");
    }

    public NegativeSubjectsException(String message) {
        super(message);
    }

    public NegativeSubjectsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeSubjectsException(Throwable cause) {
        super(cause);
    }
}
