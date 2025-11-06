package entity;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException() {
        super("Polje za unos je prazno!");
    }

    public EmptyFieldException(String message) {
        super(message);
    }

    public EmptyFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyFieldException(Throwable cause) {
        super(cause);
    }
}
