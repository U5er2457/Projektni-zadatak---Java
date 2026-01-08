package com.javafx.project.javafx_projekt.entity.Exception;

/**
 * Baca se kada korisnik unese e-mail adresu koja nije valjana.
 *
 * @see RuntimeException
 */

public class InvalidEmailException extends RuntimeException {

    /**
     * Kreira novu iznimku s opisnom porukom.
     *
     * @param message opis problema zbog kojeg je iznimka bačena
     */

    public InvalidEmailException(String message) {
        super(message);
    }
}