package app;

import entity.*;
import entity.Interface.Schedulable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Generira razne izvještaje nad studentima, profesorima, kolegijima i kolokvijima.
 */

public class Print {

    private static final Logger log = LoggerFactory.getLogger(Print.class);

    private final List<Student> students;
    private final List<Professor> professors;
    private final List<Subject> subjects;
    private final List<Exam> exams;

    /**
     * Inicijalizira referencu na korištene liste.
     *
     * @param students   lista studenata
     * @param professors lista profesora
     * @param subjects   lista kolegija
     * @param exams      lista kolokvija
     */

    public Print(List<Student> students, List<Professor> professors,
                         List<Subject> subjects, List<Exam> exams) {
        this.students = students;
        this.professors = professors;
        this.subjects = subjects;
        this.exams = exams;
    }

    /**
     * Ispisuje sve studente i sve profesore sortirane po prezimenu i imenu.
     */

    public void allPeople() {

        System.out.println("\n--- Svi studenti ---");
        if (students.isEmpty()) {
            System.out.println("Nema unesenih studenata.");
        } else {
            students.stream()
                    .sorted(Comparator.comparing(Student::getLastName)
                            .thenComparing(Student::getFirstName))
                    .forEach(System.out::println);
        }

        System.out.println("\n--- Svi profesori ---");
        if (professors.isEmpty()) {
            System.out.println("Nema unesenih profesora.");
        } else {
            professors.stream()
                    .sorted(Comparator.comparing(Professor::getLastName)
                            .thenComparing(Professor::getFirstName))
                    .forEach(System.out::println);
        }
    }

    /**
     * Pronalazi i ispisuje studenta s najvećim prosjekom ocjena.
     */

    public void printBestStudent() {

        if (students.isEmpty()) {
            System.out.println("Nema unesenih studenata.");
            return;
        }

        students.sort(Comparator.comparing(Student::getGpa));
        System.out.println("Student s najvećim prosjekom: " + students.getLast());
    }

    /**
     * Pronalazi i ispisuje kolegij s najvećim brojem ECTS bodova.
     */

    public void printBestSubject() {

        if (subjects.isEmpty()) {
            System.out.println("Nema unesenih kolegija.");
            return;
        }

        subjects.sort(Comparator.comparingInt(Subject::ects));
        System.out.println("Kolegij s najviše bodova: " + subjects.getLast());
    }

    /**
     * Pronalazi najraniji kolokvij prema datumu održavanja.
     */

    public void printEarliestExam() {

        if (exams.isEmpty()) {
            System.out.println("Nema unesenih događaja.");
            return;
        }

        Optional<Schedulable> minExam = Optional.empty();

        for (Exam exam : exams) {
            if (minExam.isEmpty() || exam.dateTime().isBefore(minExam.get().dateTime())) {

                minExam = Optional.of(exam);
            }
        }


        System.out.println("Najraniji kolokvij: " + minExam);
    }

    /**
     * Grupira studente prema GPA vrijednosti i ispisuje grupe.
     */

    public void printStudentsByGpa(List<? extends Student> students) {

        Map<BigDecimal, List<Student>> byGpa =
                students.stream()
                        .collect(Collectors.groupingBy(Student::getGpa));

        byGpa.forEach((gpa, list) -> {
            System.out.println("Prosjek " + gpa + ":");
            list.forEach(s -> System.out.println("  " + s));
        });
    }

    /**
     * Ispisuje sve studente s prosjekom 4.5 ili višim (odlikaše).
     */

    public void printExcellentStudents(List <? extends Student> students) {

        Map<Boolean, List<Student>> part =
                students.stream()
                        .collect(Collectors.partitioningBy(
                                s -> BigDecimal.valueOf(4.5).compareTo(s.getGpa()) <= 0
                        ));

        System.out.println("Odlikaši:");
        part.get(true).forEach(System.out::println);
    }
}
