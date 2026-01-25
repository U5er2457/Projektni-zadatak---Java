package com.javafx.entity.exception;

/**
 * Baca se kada korisnik unese negativnu vrijednost dana.
 *
 * @see RuntimeException
 */

public class NegativeDayException extends RuntimeException {

    /**
     * Kreira novu iznimku s opisnom porukom.
     *
     * @param message opis problema zbog kojeg je iznimka bačena
     */

    public NegativeDayException(String message) {
        super(message);
    }
}