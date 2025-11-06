package entity;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() {
        super("Email adresa nije ispravna!");
    }

    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEmailException(Throwable cause) {
        super(cause);
    }
}
