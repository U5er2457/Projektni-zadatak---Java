package entity.Exception;

public class NegativeMonthException extends RuntimeException {
    public NegativeMonthException(String message) {
        super(message);
    }
}
