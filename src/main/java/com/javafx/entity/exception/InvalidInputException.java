package com.javafx.entity.exception;

/**
 * Baca se kada korisnik unese podatke koji ne zadovoljavaju očekivani format, raspon ili vrstu podataka.
 *
 * @see RuntimeException
 */

public class InvalidInputException extends RuntimeException {

    /**
     * Kreira novu iznimku s opisnom porukom.
     *
     * @param message opis problema zbog kojeg je iznimka bačena
     */

    public InvalidInputException(String message) {
        super(message);
    }
}