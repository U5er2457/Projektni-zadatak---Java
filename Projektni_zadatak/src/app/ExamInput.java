package app;

import entity.Exception.NegativeExamsException;
import entity.Exam;
import entity.SubjectName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


/**
 * Unosi nove kolokvije.
 */

public class ExamInput {

    private static final Logger log = LoggerFactory.getLogger(ExamInput.class);

    private final List<Exam> exams;

    /**
     * Inicijalizira servis za rad s kolokvijima.
     *
     * @param exams lista postojećih kolokvija
     */

    public ExamInput(List<Exam> exams) {
        this.exams = exams;
    }

    /**
     * Rukuje unosom novog kolokvija.
     * <p>
     * Koristi validacijske metode iz {@link InputValidation}.
     *
     * @param sc Scanner za unos
     */

    public void addExam(Scanner sc) {

        if (exams.size() >= 100) {
            System.out.println("Nema mjesta za nove kolokvije!");
            return;
        }

        log.info("Odabrano: dodavanje kolokvija.");

        SubjectName subject = InputValidation.readNonEmptySubject(sc, "Naziv kolegija: ");
        LocalDateTime date = InputValidation.inputDate(sc);
        String location = InputValidation.readNonEmptyString(sc, "Lokacija: ");
        Integer duration = InputValidation.inputPositiveDuration(sc, "Trajanje u minutama: ");

        try {
            Exam e = new Exam(subject, date, location, duration);
            exams.add(e);
            log.info("Dodan kolokvij: {}", e);
        } catch (NegativeExamsException e) {
            log.error("Greška pri kreiranju kolokvija.", e);
            System.out.println("Greška: " + e.getMessage());
        }
    }
}
