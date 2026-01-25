package com.javafx.entity.exception;

/**
 * Baca se kada korisnik unese negativnu vrijednost godine.
 *
 * @see RuntimeException
 */

public class NegativeYearException extends RuntimeException {

    /**
     * Kreira novu iznimku s opisnom porukom.
     *
     * @param message opis problema zbog kojeg je iznimka bačena
     */

    public NegativeYearException(String message) {
        super(message);
    }
}