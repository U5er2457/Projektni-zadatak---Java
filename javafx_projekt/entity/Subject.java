package com.javafx.project.javafx_projekt.entity;

import com.javafx.project.javafx_projekt.entity.Interface.Schedulable;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Predstavlja kolegij na sveučilištu.
 *
 * @param name naziv kolegija
 * @param ects broj ECTS bodova
 * @param dateTime datum i vrijeme održavanja kolegija
 * @param location lokacija održavanja kolegija
 * @param duration trajanje kolegija u minutama
 */

public record Subject(
        SubjectName name,
        Integer ects,
        LocalDateTime dateTime,
        String location,
        Integer duration
) implements Schedulable, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Radi novi kolegij sa zadanim podacima te uključuje validaciju podataka.
     *
     * @throws IllegalArgumentException ako je broj ECTS bodova ili
     *                                  trajanje manji od nule
     */
    public Subject {
        if (ects < 0) {
            throw new IllegalArgumentException("Broj ECTS bodova mora biti veći od 0!");
        }
        if (duration < 0) {
            throw new IllegalArgumentException("Broj minuta mora biti veći od 0!");
        }
    }
}