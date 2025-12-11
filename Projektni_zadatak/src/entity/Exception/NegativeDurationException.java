package entity.Exception;

/**
 * Baca se kada korisnik unese negativnu vrijednost trajanja.
 *
 * @see RuntimeException
 */

public class NegativeDurationException extends RuntimeException {

    /**
     * Kreira novu iznimku s opisnom porukom.
     *
     * @param message opis problema zbog kojeg je iznimka bačena
     */

    public NegativeDurationException(String message) {
        super(message);
    }
}
