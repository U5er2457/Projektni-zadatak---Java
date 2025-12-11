package app.input;

import app.JSONStorage;
import entity.Professor;
import entity.SubjectName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Unosi profesore.
 */

public class ProfessorInput {

    private static final Logger log = LoggerFactory.getLogger(ProfessorInput.class);

    private final List<Professor> professors;

    /**
     * Inicijalizira rad s profesorima.
     *
     * @param professors lista postojećih profesora
     */

    public ProfessorInput(List<Professor> professors) {
        this.professors = professors;
    }

    /**
     * Unosi nove profesore.
     *
     * @param sc Scanner za unos
     */

    public void addProfessor(Scanner sc) {

        if (professors.size() >= 100) {
            System.out.println("Nema mjesta za nove profesore!");
            return;
        }

        log.info("Odabrano: dodavanje profesora.");

        String firstName = InputValidation.readNonEmptyString(sc, "Ime: ");
        String lastName = InputValidation.readNonEmptyString(sc, "Prezime: ");
        String email = InputValidation.readEmailWithRetry(sc, "Email: ");
        System.out.println("Broj mobitela: ");
        String phone = sc.nextLine();
        System.out.println("OIB: ");
        String oib = sc.nextLine();

        System.out.println("Unesite broj kolegija koje profesor predaje: ");
        int num = sc.nextInt();
        sc.nextLine();

        Set<SubjectName> sub = new HashSet<>();

        for (int i = 0; i < num; i++) {
            System.out.println("Odaberite kolegij (1-45): ");
            int kol = sc.nextInt();
            sc.nextLine();

            SubjectName[] values = SubjectName.values();
            if (kol >= 1 && kol <= values.length) {
                sub.add(values[kol - 1]);
            } else {
                System.out.println("Neispravan odabir!");
            }
        }

        log.info("Odabrani kolegiji profesora: {}", sub);

        Professor p = new Professor(firstName, lastName, email, phone, oib, sub);
        professors.add(p);
        log.info("Dodan profesor: {}", p);
        JSONStorage.saveProfessors(professors);
    }
}
