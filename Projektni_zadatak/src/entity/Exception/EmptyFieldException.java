package entity.Exception;

/**
 * Baca se kada korisnik ne unese podatke za neko polje.
 *
 * @see RuntimeException
 */

public class EmptyFieldException extends RuntimeException {

    /**
     * Kreira novu iznimku s opisnom porukom.
     *
     * @param message opis problema zbog kojeg je iznimka bačena
     */

    public EmptyFieldException(String message) {
        super(message);
    }
}
