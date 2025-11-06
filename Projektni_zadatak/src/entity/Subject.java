package entity;

import java.time.LocalDateTime;

/**
 * Predstavlja kolegij na sveučilištu.
 */
public record Subject(
        String name,
        Integer ects,
        LocalDateTime dateTime,
        String location,
        Integer duration
) implements Schedulable {

    /**
     * Konstruira novi kolegij sa zadanim podacima te uključuje validaciju podataka.
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
