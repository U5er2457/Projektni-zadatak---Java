package com.javafx.entity.exception;

/**
 * Baca se kada korisnik unese negativnu vrijednost ECTS bodova.
 *
 * @see RuntimeException
 */

public class NegativeEctsException extends RuntimeException {

    /**
     * Kreira novu iznimku s opisnom porukom.
     *
     * @param message opis problema zbog kojeg je iznimka bačena
     */

    public NegativeEctsException(String message) {
        super(message);
    }
}