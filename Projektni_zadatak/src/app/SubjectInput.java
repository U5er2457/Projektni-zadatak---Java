package app;

import entity.Exception.NegativeSubjectsException;
import entity.Subject;
import entity.SubjectName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Upravlja unosom novih kolegija.
 */

public class SubjectInput {

    private static final Logger log = LoggerFactory.getLogger(SubjectInput.class);

    private final List<Subject> subjects;

    public SubjectInput(List<Subject> subjects) {
        this.subjects = subjects;
    }

    /**
     * Rukuje unosom novog kolegija preko korisničkog unosa.
     * Poziva sve metode validacije iz {@link InputValidation}.
     * @param sc Scanner za unos
     */

    public void addSubject(Scanner sc) {

        if (subjects.size() >= 100) {
            System.out.println("Nema mjesta za nove kolegije!");
            return;
        }

        log.info("Odabrano: dodavanje kolegija.");

        SubjectName subject = InputValidation.readNonEmptySubject(sc, "Naziv kolegija: ");
        Integer ects = InputValidation.inputPositiveEcts(sc, "Broj ECTS bodova: ");
        LocalDateTime date = InputValidation.inputDate(sc);
        String location = InputValidation.readNonEmptyString(sc, "Lokacija: ");
        Integer duration = InputValidation.inputPositiveDuration(sc, "Trajanje u minutama: ");

        try {
            Subject s = new Subject(subject, ects, date, location, duration);
            subjects.add(s);
            log.info("Dodan kolegij: {}", s);
        } catch (NegativeSubjectsException e) {
            log.error("Greška pri kreiranju kolegija.", e);
            System.out.println("Greška: " + e.getMessage());
        }
    }
}
