package entity.Exception;

public class NegativeHourException extends RuntimeException {
    public NegativeHourException(String message) {
        super(message);
    }
}
