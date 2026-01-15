package com.javafx.entity.exception;

/**
 * Baca se kada korisnik unese negativnu vrijednost kolegija.
 *
 * @see RuntimeException
 */

public class NegativeSubjectsException extends RuntimeException {

    /**
     * Kreira novu iznimku s opisnom porukom.
     *
     * @param message opis problema zbog kojeg je iznimka bačena
     */

    public NegativeSubjectsException(String message) {
        super(message);
    }

}