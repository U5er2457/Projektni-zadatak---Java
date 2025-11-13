package entity.Exception;

public class NegativeYearException extends RuntimeException {
    public NegativeYearException(String message) {
        super(message);
    }
}
