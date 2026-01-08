package com.javafx.project.javafx_projekt.entity.Exception;

/**
 * Baca se kada korisnik unese negativnu vrijednost sati.
 *
 * @see RuntimeException
 */

public class NegativeHourException extends RuntimeException {

    /**
     * Kreira novu iznimku s opisnom porukom.
     *
     * @param message opis problema zbog kojeg je iznimka bačena
     */

    public NegativeHourException(String message) {
        super(message);
    }
}