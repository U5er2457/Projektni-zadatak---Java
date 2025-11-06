package entity;

public class NegativeNumberException extends RuntimeException {
    public NegativeNumberException() {
        super("Broj je negativan! Mora biti > 0");
    }

    public NegativeNumberException(String message) {
        super(message);
    }

    public NegativeNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeNumberException(Throwable cause) {
        super(cause);
    }
}
