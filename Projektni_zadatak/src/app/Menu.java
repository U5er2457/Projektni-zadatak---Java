package app;

import entity.*;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Upravlja tokovima aplikacije kroz izbornik.
 * Omogućava korisniku interakciju s aplikacijom preko izbornika
 */

public class Menu {

    private static final Logger log = LoggerFactory.getLogger(Menu.class);
    private static final int max = 100;

    private final List<Subject> subjects = new ArrayList<>(max);
    private final List<Exam> exams = new ArrayList<>(max);
    private final List<Student> students = new ArrayList<>(max);
    private final List<Professor> professors = new ArrayList<>(max);

    private final SubjectInput subject = new SubjectInput(subjects);
    private final ExamInput exam = new ExamInput(exams);
    private final StudentInput student = new StudentInput(students);
    private final ProfessorInput professor = new ProfessorInput(professors);

    private final Print print = new Print(students, professors, subjects, exams);

    /**
     * Pokreće glavni izbornik i omogućava korisniku unos odabira sve dok se ne
     * odabere opcija za izlaz.
     */

    public void run() {

        Scanner sc = new Scanner(System.in);
        boolean notZero = true;

        while (notZero) {

            System.out.println("1) Dodaj kolegij");
            System.out.println("2) Dodaj kolokvij");
            System.out.println("3) Dodaj studenta");
            System.out.println("4) Dodaj profesora");
            System.out.println("5) Ispiši sve osobe");
            System.out.println("6) Student s najvećim prosjekom");
            System.out.println("7) Kolegij s najviše ECTS bodova");
            System.out.println("8) Najraniji kolokvij");
            System.out.println("9) Ispis studenata po prosjeku");
            System.out.println("10) Ispis svih odlikaša");
            System.out.println("0) Izlaz");

            String odabir = sc.nextLine();
            log.debug("Odabrana opcija: " + odabir);

            switch (odabir) {

                case "1" -> subject.addSubject(sc);
                case "2" -> exam.addExam(sc);
                case "3" -> student.addStudent(sc);
                case "4" -> professor.addProfessor(sc);
                case "5" -> print.allPeople();
                case "6" -> print.printBestStudent();
                case "7" -> print.printBestSubject();
                case "8" -> print.printEarliestExam();
                case "9" -> print.printStudentsByGpa(students);
                case "10" -> print.printExcellentStudents(students);
                case "0" -> notZero = false;

                default -> System.out.println("Neispravan odabir!");
            }
        }

        sc.close();
    }
}
