package entity.Exception;

public class NegativeExamsException extends RuntimeException {
    public NegativeExamsException() {
        super("Broj kolokvija je negativan! Mora biti > 0");
    }

    public NegativeExamsException(String message) {
        super(message);
    }

    public NegativeExamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeExamsException(Throwable cause) {
        super(cause);
    }
}
