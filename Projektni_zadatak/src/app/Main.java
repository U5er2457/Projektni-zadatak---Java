package app;

import entity.*;
import entity.Exception.*;
import entity.Interface.Schedulable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Demonstrira rad programa s kolegijima, ispitima, studentima i profesorima te pruža izbornik za jednostavnost korištenja.
 */

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final int max = 100;

    /**
     * Početak programa.
     * Kreira početne podatke, prikazuje izbornik te obrađuje korisničke unose.
     *
     */

    static void main() {

        log.info("Pokrenut program!");

        Scanner sc = new Scanner(System.in);

        List<Subject> subjects = new ArrayList<>(max);

        List<Exam> exams = new ArrayList<>(max);

        List<Student> students = new ArrayList<>(max);

        List<Professor> professors = new ArrayList<>(max);

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
            System.out.println("9) Ispis studenata po prosjeku");
            System.out.println("10) Ispis svih odlikaša");
            System.out.println("0) Izlaz");

            String odabir = sc.nextLine();

            log.debug("Odabrana opcija: " + odabir);

            switch (odabir){
                case "1" -> {
                    log.info("Odabrano: dodavanje kolegija.");
                    if (subjects.size() >= max) {
                        log.warn("Nema mjesta za novi kolegij.");
                        System.out.println("Nema mjesta za nove kolegije!");
                    }
                    SubjectName subject = readNonEmptySubject(sc, "Naziv kolegija: ");
                    Integer ects = inputPositiveEcts(sc, "Broj ECTS bodova: ");
                    LocalDateTime date = inputDate(sc);
                    String location = readNonEmptyString(sc, "Lokacija: ");
                    Integer duration = inputPositiveDuration(sc, "Trajanje u minutama: ");

                    try {
                        Subject s = new Subject(subject, ects, date, location, duration);
                        subjects.add(s);
                        log.info("Dodan kolegij: {}", s);
                    } catch (NegativeSubjectsException e) {
                        log.error("Greška pri kreiranju kolegija.", e);
                        System.out.println("Greška: " + e.getMessage());
                    }
                }

                case "2" -> {
                    log.info("Odabrano: dodavanje kolokvija.");
                    if (exams.size() >= max) {
                        log.warn("Nema mjesta za novi kolokvij.");
                        System.out.println("Nema mjesta za nove kolokvije!");
                    }
                    SubjectName subject = readNonEmptySubject(sc, "Naziv kolegija: ");
                    LocalDateTime date = inputDate(sc);
                    String location = readNonEmptyString(sc, "Lokacija: ");
                    Integer duration = inputPositiveDuration(sc, "Trajanje u minutama: ");

                    try {
                        Exam e = new Exam(subject, date, location, duration);
                        exams.add(e);
                        log.info("Dodan kolokvij: {}", e);
                    } catch (NegativeExamsException e) {
                        log.error("Greška pri kreiranju kolokvija.", e);
                        System.out.println("Greška: " + e.getMessage());
                    }
                }

                case "3" -> {
                    log.info("Odabrano: dodavanje studenta.");
                    if (students.size() >= max) {
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
                    System.out.println("1) Računarstvo");
                    System.out.println("2) Informatika");
                    System.out.println("3) Elektrotehnika");
                    System.out.println("4) Graditeljstvo");
                    System.out.println("5) Mehatronika");
                    System.out.println("6) Strojarstvo");
                    Integer smjerNum = sc.nextInt();
                    sc.nextLine();

                    Course course = null;

                    switch (smjerNum){
                        case 1 -> course = Course.Racunarstvo;
                        case 2 -> course = Course.Informatika;
                        case 3 -> course = Course.Elektrotehnika;
                        case 4 -> course = Course.Graditeljstvo;
                        case 5 -> course = Course.Mehatronika;
                        case 6 -> course = Course.Strojarstvo;
                        default -> System.out.println("Neispravan odabir!");
                    }

                    if(course != null){
                        log.info("Odabran smjer studenta: " + course);
                    }

                    Integer year = inputPositiveYear(sc, "Godina studija: ");

                    BigDecimal gpa = inputGpa(sc);
                    Integer ects = inputPositiveEcts(sc, "Ukupni ECTS bodovi: ");

                    System.out.println("Kolegiji: ");
                    System.out.println("Unesite broj kolegija koje student sluša: ");
                    Integer numStudentSub = sc.nextInt();
                    Set<SubjectName> sub = new HashSet<>();
                    switch (year){
                        case 1 -> {
                            for (Integer i = 0; i < numStudentSub; i++){
                                System.out.println("Kolegiji za 1. godinu:");
                                System.out.println("1) Digitalna logika");
                                System.out.println("2) Engleski jezik 1");
                                System.out.println("3) Kineziološka kultura 1");
                                System.out.println("4) Matematika 1");
                                System.out.println("5) Primjena računala");
                                System.out.println("6) Programiranje");
                                System.out.println("7) Engleski jezik 2");
                                System.out.println("8) Kineziološka kultura 2");
                                System.out.println("9) Matematika 2");
                                System.out.println("10) Objektno orijentirano programiranje");
                                System.out.println("11) Osnove elektrotehnike i elektronike");
                                System.out.println("12) Uvod u web tehnologije");
                                Integer kolNum = sc.nextInt();
                                sc.nextLine();

                                switch (kolNum){
                                    case 1 -> sub.add(SubjectName.Digitalna_logika);
                                    case 2 -> sub.add(SubjectName.Eng1);
                                    case 3 -> sub.add(SubjectName.KK1);
                                    case 4 -> sub.add(SubjectName.Mat1);
                                    case 5 -> sub.add(SubjectName.Primjena_rac);
                                    case 6 -> sub.add(SubjectName.Prog);
                                    case 7 -> sub.add(SubjectName.Eng2);
                                    case 8 -> sub.add(SubjectName.KK2);
                                    case 9 -> sub.add(SubjectName.Mat2);
                                    case 10 -> sub.add(SubjectName.OOP);
                                    case 11 -> sub.add(SubjectName.OE);
                                    case 12 -> sub.add(SubjectName.UWT);
                                    default -> System.out.println("Neispravan odabir!");
                                }
                            }
                        }
                        case 2 -> {
                            for (Integer i = 0; i < numStudentSub; i++){
                                System.out.println("Kolegiji za 2. godinu:");
                                System.out.println("1) Algoritmi i strukture podataka");
                                System.out.println("2) Arhitektura računala");
                                System.out.println("3) Kineziološka kultura 3");
                                System.out.println("4) Operacijski sustavi");
                                System.out.println("5) Programiranje u jeziku java");
                                System.out.println("6) Vjerojatnost i statistika");
                                System.out.println("7) Baze podataka");
                                System.out.println("8) Kineziološka kultura 4");
                                System.out.println("9) Metodologija poslovnih procesa");
                                System.out.println("10) Računalne mreže");
                                System.out.println("11) Uvod u UNIX sustave");
                                System.out.println("12) Numerička matematika");
                                System.out.println("13) Oblikovanje e literature");
                                System.out.println("14) Programiranje web aplikacija");
                                Integer kolNum = sc.nextInt();
                                sc.nextLine();

                                switch (kolNum){
                                    case 1 -> sub.add(SubjectName.ASP);
                                    case 2 -> sub.add(SubjectName.Arhitektura_rac);
                                    case 3 -> sub.add(SubjectName.KK3);
                                    case 4 -> sub.add(SubjectName.OS);
                                    case 5 -> sub.add(SubjectName.Prog_java);
                                    case 6 -> sub.add(SubjectName.VIS);
                                    case 7 -> sub.add(SubjectName.Baze);
                                    case 8 -> sub.add(SubjectName.KK4);
                                    case 9 -> sub.add(SubjectName.Metodologija);
                                    case 10 -> sub.add(SubjectName.Mreze);
                                    case 11 -> sub.add(SubjectName.UNIX);
                                    case 12 -> sub.add(SubjectName.NumMat);
                                    case 13 -> sub.add(SubjectName.E_literatura);
                                    case 14 -> sub.add(SubjectName.Prog_web);
                                    default -> System.out.println("Neispravan odabir!");
                                }
                            }
                        }
                        case 3 -> {
                            for (Integer i = 0; i < numStudentSub; i++){
                                System.out.println("Kolegiji za 3. godinu:");
                                System.out.println("1) Metodologija stručnog i istraživačkog rada");
                                System.out.println("2) Administracija računalnih mreža");
                                System.out.println("3) Administriranje UNIX sustava");
                                System.out.println("4) Mrežne usluge");
                                System.out.println("5) Napredne baze podataka");
                                System.out.println("6) Napredne tehnike programiranja");
                                System.out.println("7) Napredno JavaScript programiranje");
                                System.out.println("8) Napredno programiranje u jeziku Python");
                                System.out.println("9) Oblikovanje web stranica");
                                System.out.println("10) Računala za nadzor i upravljanje tehnickim procesima");
                                System.out.println("11) Razvoj računalnih igara");
                                System.out.println("12) Sigurnost računalnih sustava");
                                System.out.println("13) Duboko učenje");
                                System.out.println("14) Napredne teme računalnih mreža");
                                System.out.println("15) Razvoj aplikacija na Android platformi");
                                System.out.println("16) Razvoj web aplikacija u ASP.NET MVC tehnologiji");
                                System.out.println("17) Stručna praksa");
                                System.out.println("18) Web aplikacije u Javi");
                                System.out.println("19) Završni rad");
                                Integer kolNum = sc.nextInt();
                                sc.nextLine();

                                switch (kolNum){
                                    case 1 -> sub.add(SubjectName.MSIR);
                                    case 2 -> sub.add(SubjectName.Mreze_admin);
                                    case 3 -> sub.add(SubjectName.UNIX_admin);
                                    case 4 -> sub.add(SubjectName.Mrezne_usluge);
                                    case 5 -> sub.add(SubjectName.Napredne_baze);
                                    case 6 -> sub.add(SubjectName.Prog_nap);
                                    case 7 -> sub.add(SubjectName.JavaScript);
                                    case 8 -> sub.add(SubjectName.Python);
                                    case 9 -> sub.add(SubjectName.OblikovanjeWeb);
                                    case 10 -> sub.add(SubjectName.RNUTP);
                                    case 11 -> sub.add(SubjectName.Rac_igre);
                                    case 12 -> sub.add(SubjectName.SRS);
                                    case 13 -> sub.add(SubjectName.Duboko_ucenje);
                                    case 14 -> sub.add(SubjectName.Mreze_nap);
                                    case 15 -> sub.add(SubjectName.Android);
                                    case 16 -> sub.add(SubjectName.ASP_NET);
                                    case 17 -> sub.add(SubjectName.Praksa);
                                    case 18 -> sub.add(SubjectName.WebJava);
                                    case 19 -> sub.add(SubjectName.Zavrsni);
                                    default -> System.out.println("Neispravan odabir!");
                                }
                            }
                        }

                    }
                    log.info("Odabrani kolegiji studenta: " + sub);

                    Student s = new Student.StudentBuilder(firstName, lastName, email, jmbag, oib)
                            .phoneNumber(telefon)
                            .course(course)
                            .year(year)
                            .gpa(gpa)
                            .ects(ects)
                            .subjects(sub)
                            .build();

                    students.add(s);
                    log.info("Dodan student: {}", s);
                }

                case "4" -> {
                    log.info("Odabrano: dodavanje profesora.");
                    if (professors.size() >= max) {
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
                    System.out.println("Kolegiji koje profesor predaje: ");
                    Set<SubjectName> subProf = new HashSet<>();
                    System.out.println("Unesite broj kolegija koje profesor predaje: ");
                    Integer numOfSub = sc.nextInt();

                    for (Integer i = 0; i < numOfSub; i++) {
                        System.out.println("1) Metodologija stručnog i istraživačkog rada");
                        System.out.println("2) Administracija računalnih mreža");
                        System.out.println("3) Administriranje UNIX sustava");
                        System.out.println("4) Mrežne usluge");
                        System.out.println("5) Napredne baze podataka");
                        System.out.println("6) Napredne tehnike programiranja");
                        System.out.println("7) Napredno JavaScript programiranje");
                        System.out.println("8) Napredno programiranje u jeziku Python");
                        System.out.println("9) Oblikovanje web stranica");
                        System.out.println("10) Računala za nadzor i upravljanje tehnickim procesima");
                        System.out.println("11) Razvoj računalnih igara");
                        System.out.println("12) Sigurnost računalnih sustava");
                        System.out.println("13) Duboko učenje");
                        System.out.println("14) Napredne teme računalnih mreža");
                        System.out.println("15) Razvoj aplikacija na Android platformi");
                        System.out.println("16) Razvoj web aplikacija u ASP.NET MVC tehnologiji");
                        System.out.println("17) Stručna praksa");
                        System.out.println("18) Web aplikacije u Javi");
                        System.out.println("19) Završni rad");
                        Integer kolProf = sc.nextInt();
                        sc.nextLine();

                        switch (kolProf) {
                            case 1 -> subProf.add(SubjectName.ASP);
                            case 2 -> subProf.add(SubjectName.Arhitektura_rac);
                            case 3 -> subProf.add(SubjectName.KK3);
                            case 4 -> subProf.add(SubjectName.OS);
                            case 5 -> subProf.add(SubjectName.Prog_java);
                            case 6 -> subProf.add(SubjectName.VIS);
                            case 7 -> subProf.add(SubjectName.Baze);
                            case 8 -> subProf.add(SubjectName.KK4);
                            case 9 -> subProf.add(SubjectName.Metodologija);
                            case 10 -> subProf.add(SubjectName.Mreze);
                            case 11 -> subProf.add(SubjectName.UNIX);
                            case 12 -> subProf.add(SubjectName.NumMat);
                            case 13 -> subProf.add(SubjectName.E_literatura);
                            case 14 -> subProf.add(SubjectName.Prog_web);
                            case 15 -> subProf.add(SubjectName.Digitalna_logika);
                            case 16 -> subProf.add(SubjectName.Eng1);
                            case 17 -> subProf.add(SubjectName.KK1);
                            case 18 -> subProf.add(SubjectName.Mat1);
                            case 19 -> subProf.add(SubjectName.Primjena_rac);
                            case 20 -> subProf.add(SubjectName.Prog);
                            case 21 -> subProf.add(SubjectName.Eng2);
                            case 22 -> subProf.add(SubjectName.KK2);
                            case 23 -> subProf.add(SubjectName.Mat2);
                            case 24 -> subProf.add(SubjectName.OOP);
                            case 25 -> subProf.add(SubjectName.OE);
                            case 26 -> subProf.add(SubjectName.UWT);
                            case 27 -> subProf.add(SubjectName.MSIR);
                            case 28 -> subProf.add(SubjectName.Mreze_admin);
                            case 29 -> subProf.add(SubjectName.UNIX_admin);
                            case 30 -> subProf.add(SubjectName.Mrezne_usluge);
                            case 31 -> subProf.add(SubjectName.Napredne_baze);
                            case 32 -> subProf.add(SubjectName.Prog_nap);
                            case 33 -> subProf.add(SubjectName.JavaScript);
                            case 34 -> subProf.add(SubjectName.Python);
                            case 35 -> subProf.add(SubjectName.OblikovanjeWeb);
                            case 36 -> subProf.add(SubjectName.RNUTP);
                            case 37 -> subProf.add(SubjectName.Rac_igre);
                            case 38 -> subProf.add(SubjectName.SRS);
                            case 39 -> subProf.add(SubjectName.Duboko_ucenje);
                            case 40 -> subProf.add(SubjectName.Mreze_nap);
                            case 41 -> subProf.add(SubjectName.Android);
                            case 42 -> subProf.add(SubjectName.ASP_NET);
                            case 43 -> subProf.add(SubjectName.Praksa);
                            case 44 -> subProf.add(SubjectName.WebJava);
                            case 45 -> subProf.add(SubjectName.Zavrsni);
                            default -> System.out.println("Neispravan odabir!");
                        }
                    }
                    log.info("Odabrani kolegiji profesora: " + subProf);

                    Professor p = new Professor(firstName, lastName, email, phoneNumber, oib, subProf);
                    professors.add(p);
                    log.info("Dodan profesor: {}", p);
                }

                case "5" -> {
                    log.info("Odabrano: ispis svih osoba.");
                    System.out.println("\n--- Svi studenti ---");
                    if (students.isEmpty()) {
                        System.out.println("Nema unesenih studenata.");
                    } else {
                        for (int i = 0; i < students.size(); i++) {
                            log.trace("Ispis studenta na indeksu {}", i);
                            Comparator<Student> byLastNameFristName =
                                    Comparator.comparing(Student::getLastName)
                                            .thenComparing(Student::getFirstName);

                            students.stream()
                                    .sorted(byLastNameFristName)
                                    .forEach(System.out::println);
                        }
                    }

                    System.out.println("\n--- Svi profesori ---");
                    if (professors.isEmpty()) {
                        System.out.println("Nema unesenih profesora.");
                    } else {
                        for (int i = 0; i < professors.size(); i++) {
                            log.trace("Ispis profesora na indeksu {}", i);
                            Comparator<Professor> byLastNameFristName =
                                    Comparator.comparing(Professor::getLastName)
                                            .thenComparing(Professor::getFirstName);

                            professors.stream()
                                    .sorted(byLastNameFristName)
                                    .forEach(System.out::println);
                        }
                    }
                }

                case "6" -> {
                    log.info("Odabrano: student s najvećim prosjekom.");
                    if (students.isEmpty()) {
                        System.out.println("Nema unesenih studenata.");
                    } else {
                        students.sort(Comparator.comparing(Student::getGpa));
                        System.out.println("Student s najvećim prosjekom: " + students.getLast());
                    }
                }

                case "7" -> {
                    log.info("Odabrano: kolegij s najviše ECTS.");
                    if (subjects.isEmpty()) {
                        System.out.println("Nema unesenih kolegija.");
                    } else {
                        subjects.sort(Comparator.comparingInt(Subject::ects));
                        System.out.println("Kolegij s najviše bodova: " + subjects.getLast());
                    }
                }

                case "8" -> {
                    log.info("Odabrano: najraniji kolokvij.");
                    Schedulable minExam = null;

                    for (Exam exam : exams) {
                        if (exam.dateTime().isBefore(minExam.dateTime())) {
                            minExam = exam;
                        }
                    }

                    if (minExam == null) {
                        System.out.println("Nema unesenih događaja.");
                    } else {
                        System.out.println("Najraniji kolokvij: " + minExam);
                    }
                }

                case "9" -> {
                    log.info("Odabrano: Ispis studenata po prosjeku.");
                    Map<BigDecimal, List<Student>> studentsByGpa =
                            students.stream()
                                    .collect(Collectors.groupingBy(
                                            s -> (BigDecimal) s.getGpa()
                                    ));

                    studentsByGpa.forEach((gpa, st) -> {
                        System.out.println("Prosjek " + gpa + ":");
                        st.forEach(s -> System.out.println("  " + s));
                    });
                }

                case "10" -> {
                    log.info("Odabrano: Ispis svih odlikaša.");
                    Map<Boolean, List<Student>> part =
                            students.stream()
                                    .collect(Collectors.partitioningBy(
                                            s -> BigDecimal.valueOf(4.5).compareTo(s.getGpa()) <= 0
                                    ));

                    System.out.println("Odlikaši:");
                    part.get(true).forEach(System.out::println);
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

    private static int inputPositiveYear(Scanner sc, String out) {
        while (true) {
            try {
                int year = inputInt(sc, out);
                if (year <= 0) {
                    throw new NegativeYearException("Vrijednost godine mora biti veći pozitivan broj.");
                }
                return year;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                log.warn("Neispravan unos godine: {}", e.getMessage());
            } catch (NegativeYearException e) {
                System.out.println(e.getMessage());
                log.warn("Unesen negativan ili nula kod unosa godine.", e);
            }
        }
    }

    private static int inputPositiveEcts(Scanner sc, String out) {
        while (true) {
            try {
                int ects = inputInt(sc, out);
                if (ects <= 0) {
                    throw new NegativeEctsException("Vrijednost ECTS bodova mora biti veći pozitivan broj.");
                }
                return ects;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                log.warn("Neispravan unos ECTS bodova: {}", e.getMessage());
            } catch (NegativeEctsException e) {
                System.out.println(e.getMessage());
                log.warn("Unesen negativan ili nula kod unosa ECTS bodova.", e);
            }
        }
    }

    private static int inputPositiveDuration(Scanner sc, String out) {
        while (true) {
            try {
                int duration = inputInt(sc, out);
                if (duration <= 0) {
                    throw new NegativeDurationException("Vrijednost trajanja kolegija / kolokvija mora biti veći pozitivan broj.");
                }
                return duration;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                log.warn("Neispravan unos trajanja kolegija / kolokvija bodova: {}", e.getMessage());
            } catch (NegativeDurationException e) {
                System.out.println(e.getMessage());
                log.warn("Unesen negativan ili nula kod unosa trajanja kolegija / kolokvija.", e);
            }
        }
    }

    private static int inputPositiveMonth(Scanner sc, String out) {
        while (true) {
            try {
                int m = inputInt(sc, out);
                if (m <= 0) {
                    throw new NegativeMonthException("Broj mjeseci mora biti veći pozitivan broj.");
                }
                return m;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                log.warn("Neispravan unos mjeseca: {}", e.getMessage());
            } catch (NegativeMonthException e) {
                System.out.println(e.getMessage());
                log.warn("Unesen negativan ili nula kod unosa mjeseca.", e);
            }
        }
    }

    private static int inputPositiveDay(Scanner sc, String out) {
        while (true) {
            try {
                int d = inputInt(sc, out);
                if (d <= 0) {
                    throw new NegativeDayException("Broj dana mora biti veći pozitivan broj.");
                }
                return d;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                log.warn("Neispravan unos dana: {}", e.getMessage());
            } catch (NegativeDayException e) {
                System.out.println(e.getMessage());
                log.warn("Unesen negativan ili nula kod unosa dana.", e);
            }
        }
    }

    private static int inputPositiveHour(Scanner sc, String out) {
        while (true) {
            try {
                int h = inputInt(sc, out);
                if (h <= 0) {
                    throw new NegativeHourException("Broj sati mora biti veći pozitivan broj.");
                }
                return h;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                log.warn("Neispravan unos sati: {}", e.getMessage());
            } catch (NegativeHourException e) {
                System.out.println(e.getMessage());
                log.warn("Unesen negativan ili nula kod unosa sati.", e);
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

    private static SubjectName readNonEmptySubject(Scanner sc, String out) {
        while (true) {
            System.out.println(out);
            String input = sc.nextLine();
            SubjectName s = SubjectName.valueOf(input);
            if (!s.toString().isEmpty()) {
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
                    throw new NegativeGpaException("Prosjek ne može biti negativan.");
                }
                if (gpa.compareTo(BigDecimal.ONE) < 0){
                    throw new InvalidGpaException("Prosjek ne može biti manji od 1.0!");
                }
                return gpa;
            } catch (NumberFormatException e) {
                System.out.println("Neispravan format prosjeka. Unesite decimalni broj.");
                log.warn("Neispravan format prosjeka!");
            } catch (NegativeGpaException e) {
                System.out.println(e.getMessage());
                log.warn("Prosjek ne može biti negativan!", e);
            }
        }
    }

    private static LocalDateTime inputDate(Scanner sc) {
        System.out.println("Unesite datum i vrijeme:");
        int godina = inputPositiveYear(sc, "Godina: ");
        int mjesec = inputPositiveMonth(sc, "Mjesec: ");
        int dan = inputPositiveDay(sc, "Dan: ");
        int sat = inputPositiveHour(sc, "Sat: ");
        int minute = inputInt(sc, "Minute: ");
        return LocalDateTime.of(godina, mjesec, dan, sat, minute);
    }

}