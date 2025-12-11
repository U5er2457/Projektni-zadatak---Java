package entity.Exception;

/**
 * Baca se kada korisnik unese negativnu vrijednost prosjeka ocjena.
 *
 * @see RuntimeException
 */

public class NegativeGpaException extends RuntimeException {

    /**
     * Kreira novu iznimku s opisnom porukom.
     *
     * @param message opis problema zbog kojeg je iznimka bačena
     */

    public NegativeGpaException(String message) {
        super(message);
    }
}
