package com.javafx.project.javafx_projekt.entity.Interface;

import java.time.LocalDateTime;
/**
 * Predstavlja entitet koji se može vremenski rasporediti (npr. kolegij, kolokvij, ...).
 */
public interface Schedulable {

    /**
     * Vraća datum i vrijeme održavanja događaja.
     *
     * @return datum i vrijeme događaja
     */

    LocalDateTime dateTime();

    /**
     * Vraća lokaciju na kojoj se događaj održava.
     *
     * @return lokacija događaja
     */

    String location();

    /**
     * Vraća trajanje događaja u minutama.
     *
     * @return trajanje u minutama
     */

    Integer duration();
}