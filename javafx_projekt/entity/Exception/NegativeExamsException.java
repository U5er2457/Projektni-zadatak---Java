package com.javafx.project.javafx_projekt.entity.Exception;

/**
 * Baca se kada korisnik unese negativnu vrijednost kolokvija.
 *
 * @see RuntimeException
 */

public class NegativeExamsException extends RuntimeException {

    /**
     * Kreira novu iznimku s opisnom porukom.
     *
     * @param message opis problema zbog kojeg je iznimka bačena
     */

    public NegativeExamsException(String message) {
        super(message);
    }
}