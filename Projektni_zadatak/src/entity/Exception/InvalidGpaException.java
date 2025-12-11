package entity.Exception;

/**
 * Baca se kada korisnik unese prosjek ocjena studenta koji nije valjan.
 *
 * @see RuntimeException
 */

public class InvalidGpaException extends RuntimeException {

    /**
     * Kreira novu iznimku s opisnom porukom.
     *
     * @param message opis problema zbog kojeg je iznimka bačena
     */

    public InvalidGpaException(String message) {
        super(message);
    }
}
