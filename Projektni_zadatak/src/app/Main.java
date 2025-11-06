package app;

import entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Demonstrira rad programa s kolegijima, ispitima, studentima i profesorima te pruža izbornik za jednostavnost korištenja.
 */

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);
    private static final int max = 100;

    /**
     * Početak programa.
     * Kreira početne podatke, prikazuje izbornik te obrađuje korisničke unose.
     *
     * @param args argumenti komandne linije (ne koriste se)
     */

    static void main() {

        log.info("Pokrenut program!");

        Scanner sc = new Scanner(System.in);

        Subject[] subjects = new Subject[max];
        int numOfSubjects = 0;

        Exam[] exams = new Exam[max];
        int numOfExams = 0;

        Student[] students = new Student[max];
        int numOfStudents = 0;

        Professor[] professors = new Professor[max];
        int numOfProfessors = 0;

        boolean notZero = true;

        while(notZero){
            System.out.println("1) Dodaj kolegij");
            System.out.println("2) Dodaj kolokvij");
            System.out.println("3) Dodaj studenta");
            System.out.println("4) Dodaj profesora");
            System.out.println("5) Ispiši sve osobe");
            System.out.println("6) Student s najvećim prosjekom");
            System.out.println("7) Kolegij s najviše ECTS bodova");
            System.out.println("8) Najraniji kolokvij");
            System.out.println("0) Izlaz");

            String odabir = sc.nextLine();

            log.debug("Odabrana opcija: ", odabir);

            switch (odabir){
                case "1" -> {
                    log.info("Odabrano: dodavanje kolegija.");
                    if (numOfSubjects >= max) {
                        log.warn("Nema mjesta za novi kolegij.");
                        System.out.println("Nema mjesta za nove kolegije!");
                    }
                    String subject = readNonEmptyString(sc, "Naziv kolegija: ");
                    Integer ects = inputPositiveInt(sc, "Broj ECTS bodova: ");
                    LocalDateTime date = inputDate(sc);
                    String location = readNonEmptyString(sc, "Lokacija: ");
                    Integer duration = inputPositiveInt(sc, "Trajanje u minutama: ");

                    try {
                        Subject s = new Subject(subject, ects, date, location, duration);
                        subjects[numOfSubjects] = s;
                        numOfSubjects++;
                        log.info("Dodan kolegij: {}", s);
                    } catch (NegativeNumberException e) {
                        log.error("Greška pri kreiranju kolegija.", e);
                        System.out.println("Greška: " + e.getMessage());
                    }
                }

                case "2" -> {
                    log.info("Odabrano: dodavanje kolokvija.");
                    if (numOfExams >= max) {
                        log.warn("Nema mjesta za novi kolokvij.");
                        System.out.println("Nema mjesta za nove kolokvije!");
                    }
                    String subject = readNonEmptyString(sc, "Naziv kolegija: ");
                    LocalDateTime date = inputDate(sc);
                    String location = readNonEmptyString(sc, "Lokacija: ");
                    Integer duration = inputPositiveInt(sc, "Trajanje u minutama: ");

                    try {
                        Exam e = new Exam(subject, date, location, duration);
                        exams[numOfExams] = e;
                        numOfExams++;
                        log.info("Dodan kolokvij: {}", e);
                    } catch (NegativeNumberException e) {
                        log.error("Greška pri kreiranju kolokvija.", e);
                        System.out.println("Greška: " + e.getMessage());
                    }
                }

                case "3" -> {
                    log.info("Odabrano: dodavanje studenta.");
                    if (numOfStudents >= max) {
                        log.warn("Nema mjesta za novog studenta.");
                        System.out.println("Nema mjesta za nove studente!");
                    }

                    String firstName = readNonEmptyString(sc, "Ime: ");
                    String lastName = readNonEmptyString(sc, "Prezime: ");
                    String email = readEmailWithRetry(sc, "Email: "); // CHECKED #2
                    System.out.println("JMBAG: ");
                    String jmbag = sc.nextLine();
                    System.out.println("OIB: ");
                    String oib = sc.nextLine();
                    System.out.println("Broj mobitela: ");
                    String telefon = sc.nextLine();
                    System.out.println("Studijski program / smjer: ");
                    String course = sc.nextLine();
                    Integer year = inputPositiveInt(sc, "Godina studija: ");

                    BigDecimal gpa = inputGpa(sc);
                    Integer ects = inputPositiveInt(sc, "Ukupni ECTS bodovi: ");

                    System.out.println("Predmeti (odvojeni zarezom): ");
                    String[] studentSubjects = sc.nextLine().split(",");

                    Student s = new Student.StudentBuilder(firstName, lastName, email, jmbag, oib)
                            .phoneNumber(telefon)
                            .course(course)
                            .year(year)
                            .gpa(gpa)
                            .ects(ects)
                            .subjects(studentSubjects)
                            .build();

                    students[numOfStudents] = s;
                    numOfStudents++;
                    log.info("Dodan student: {}", s);
                }

                case "4" -> {
                    log.info("Odabrano: dodavanje profesora.");
                    if (numOfProfessors >= max) {
                        log.warn("Nema mjesta za novog profesora.");
                        System.out.println("Nema mjesta za nove profesore!");
                    }

                    String firstName = readNonEmptyString(sc, "Ime: ");
                    String lastName = readNonEmptyString(sc, "Prezime: ");
                    String email = readEmailWithRetry(sc, "Email: ");
                    System.out.println("Broj mobitela: ");
                    String phoneNumber = sc.nextLine();
                    System.out.println("OIB: ");
                    String oib = sc.nextLine();
                    System.out.println("Kolegiji koje predaje (odvojeni zarezom): ");
                    String[] professorSubjects = sc.nextLine().split(",");

                    Professor p = new Professor(firstName, lastName, email, phoneNumber, oib, professorSubjects);
                    professors[numOfProfessors] = p;
                    numOfProfessors++;
                    log.info("Dodan profesor: {}", p);
                }

                case "5" -> {
                    log.info("Odabrano: ispis svih osoba.");
                    System.out.println("\n--- Svi studenti ---");
                    if (numOfStudents == 0) {
                        System.out.println("Nema unesenih studenata.");
                    } else {
                        for (int i = 0; i < numOfStudents; i++) {
                            log.trace("Ispis studenta na indeksu {}", i);
                            System.out.println(students[i]);
                        }
                    }

                    System.out.println("\n--- Svi profesori ---");
                    if (numOfProfessors == 0) {
                        System.out.println("Nema unesenih profesora.");
                    } else {
                        for (int i = 0; i < numOfProfessors; i++) {
                            log.trace("Ispis profesora na indeksu {}", i);
                            System.out.println(professors[i]);
                        }
                    }
                }

                case "6" -> {
                    log.info("Odabrano: student s najvećim prosjekom.");
                    if (numOfStudents == 0) {
                        System.out.println("Nema unesenih studenata.");
                    } else {
                        Student maxStudent = students[0];
                        for (int i = 1; i < numOfStudents; i++) {
                            log.trace("Usporedba prosjeka: {} i {}",
                                    maxStudent.getGpa(), students[i].getGpa());
                            if (students[i].getGpa().compareTo(maxStudent.getGpa()) > 0) {
                                maxStudent = students[i];
                            }
                        }
                        System.out.println("Student s najvećim prosjekom: " + maxStudent);
                    }
                }

                case "7" -> {
                    log.info("Odabrano: kolegij s najviše ECTS.");
                    if (numOfSubjects == 0) {
                        System.out.println("Nema unesenih kolegija.");
                    } else {
                        Subject maxSub = subjects[0];
                        for (int i = 1; i < numOfSubjects; i++) {
                            log.trace("Usporedba ECTS bodova: {} i {}",
                                    maxSub.ects(), subjects[i].ects());
                            if (subjects[i].ects() > maxSub.ects()) {
                                maxSub = subjects[i];
                            }
                        }
                        System.out.println("Kolegij s najviše bodova: " + maxSub);
                    }
                }

                case "8" -> {
                    log.info("Odabrano: najraniji kolokvij.");
                    Schedulable minExam = null;

                    for (int i = 0; i < numOfExams; i++) {
                        if (exams[i].dateTime().isBefore(minExam.dateTime())) {
                            minExam = exams[i];
                        }
                    }

                    if (minExam == null) {
                        System.out.println("Nema unesenih događaja.");
                    } else {
                        System.out.println("Najraniji kolokvij: " + minExam);
                    }
                }

                case "0" -> {
                    log.info("Odabrano: izlaz");
                    notZero = false;
                }

                default -> {
                    log.warn("Neispravan odabir izbornika: {}", odabir);
                    System.out.println("Neispravan odabir!");
                }
            }
        }
        log.info("Završen program!");
        sc.close();
    }

    private static int inputPositiveInt(Scanner sc, String out) {
        while (true) {
            try {
                int broj = inputInt(sc, out);
                if (broj <= 0) {
                    throw new NegativeNumberException("Vrijednost mora biti veći pozitivan broj.");
                }
                return broj;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                log.warn("Neispravan numerički unos: {}", e.getMessage());
            } catch (NegativeNumberException e) {
                System.out.println(e.getMessage());
                log.warn("Unesen negativan ili nula gdje nije dozvoljeno.", e);
            }
        }
    }

    private static int inputInt(Scanner sc, String out) throws InvalidInputException {
        System.out.println(out);
        String line = sc.nextLine();
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Unos mora biti cijeli broj. Pokušajte ponovno.");
        }
    }

    private static String readEmailWithRetry(Scanner sc, String out) {
        while (true) {
            try {
                return readEmail(sc, out);
            } catch (InvalidEmailException e) {
                System.out.println(e.getMessage());
                log.warn("Neispravan email: {}", e.getMessage());
            }
        }
    }

    private static String readEmail(Scanner sc, String out) throws InvalidEmailException {
        System.out.println(out);
        String email = sc.nextLine();
        if (!email.contains("@")) {
            throw new InvalidEmailException("Email mora sadržavati znak '@'.");
        }
        return email;
    }

    private static String readNonEmptyString(Scanner sc, String out) {
        while (true) {
            System.out.println(out);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("Unos ne smije biti prazan.");
        }
    }

    private static BigDecimal inputGpa(Scanner sc) {
        while (true) {
            System.out.println("Prosjek: ");
            String input = sc.nextLine().trim().replace(',', '.');
            try {
                BigDecimal gpa = new BigDecimal(input);
                if (gpa.compareTo(BigDecimal.ZERO) < 0) {
                    throw new NegativeNumberException("Prosjek ne može biti negativan.");
                }
                return gpa;
            } catch (NumberFormatException e) {
                System.out.println("Neispravan format prosjeka. Unesite decimalni broj.");
                log.warn("Neispravan format prosjeka!");
            } catch (NegativeNumberException e) {
                System.out.println(e.getMessage());
                log.warn("Prosjek ne može biti negativan!", e);
            }
        }
    }

    private static LocalDateTime inputDate(Scanner sc) {
        System.out.println("Unesite datum i vrijeme:");
        int godina = inputPositiveInt(sc, "Godina: ");
        int mjesec = inputPositiveInt(sc, "Mjesec: ");
        int dan = inputPositiveInt(sc, "Dan: ");
        int sat = inputPositiveInt(sc, "Sat: ");
        int minute = inputInt(sc, "Minute: ");
        return LocalDateTime.of(godina, mjesec, dan, sat, minute);
    }

}