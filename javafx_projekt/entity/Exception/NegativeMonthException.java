package com.javafx.project.javafx_projekt.entity.Exception;

/**
 * Baca se kada korisnik unese negativnu vrijednost mjeseca.
 *
 * @see RuntimeException
 */

public class NegativeMonthException extends RuntimeException {

    /**
     * Kreira novu iznimku s opisnom porukom.
     *
     * @param message opis problema zbog kojeg je iznimka bačena
     */

    public NegativeMonthException(String message) {
        super(message);
    }
}