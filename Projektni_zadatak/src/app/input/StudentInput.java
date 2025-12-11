package app.input;

import app.JSONStorage;
import entity.Course;
import entity.Student;
import entity.SubjectName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.*;

/**
 * Unosi nove studenate.
 * Sadrži sve metode za unos studenta, uključujući unos osobnih
 * podataka, studijskog programa, godine studija, prosjeka, ECTS bodova
 * i odabir kolegija prema godini studija.
 */

public class StudentInput {

    private static final Logger log = LoggerFactory.getLogger(StudentInput.class);
    private final List<Student> students;

    /**
     * Inicijalizira listu studenata u koju se dodaju novi studenti.
     * @param students lista postojećih studenata
     */

    public StudentInput(List<Student> students) {this.students = students;}

    /**
     * Obrađuje unos studenta iz konzole.
     * Prikuplja sve potrebne podatke o studentu, validira podatke
     * pomoću {@link InputValidation}, omogućava odabir kolegija ovisno
     * o godini studija te kreira objekt {@link Student}.
     * @param sc Scanner za čitanje korisničkog unosa
     */

    public void addStudent(Scanner sc) {

        if (students.size() >= 100) {
            System.out.println("Nema mjesta za nove studente!");
            return;
        }

        log.info("Odabrano: dodavanje studenta.");

        String firstName = InputValidation.readNonEmptyString(sc, "Ime: ");
        String lastName = InputValidation.readNonEmptyString(sc, "Prezime: ");
        String email = InputValidation.readEmailWithRetry(sc, "Email: ");
        System.out.println("JMBAG: ");
        String jmbag = sc.nextLine();
        System.out.println("OIB: ");
        String oib = sc.nextLine();
        System.out.println("Broj mobitela: ");
        String phone = sc.nextLine();
        System.out.println("Studijski program / smjer: ");
        System.out.println("1) Računarstvo");
        System.out.println("2) Informatika");
        System.out.println("3) Elektrotehnika");
        System.out.println("4) Graditeljstvo");
        System.out.println("5) Mehatronika");
        System.out.println("6) Strojarstvo");

        Integer smjerNum = sc.nextInt();
        sc.nextLine();

        Optional<Course> course = switch (smjerNum) {
            case 1 -> Optional.of(Course.Racunarstvo);
            case 2 -> Optional.of(Course.Informatika);
            case 3 -> Optional.of(Course.Elektrotehnika);
            case 4 -> Optional.of(Course.Graditeljstvo);
            case 5 -> Optional.of(Course.Mehatronika);
            case 6 -> Optional.of(Course.Strojarstvo);
            default -> Optional.empty();
        };

        if (course.isEmpty()) {
            System.out.println("Neispravan odabir!");
            return;
        }

        log.info("Odabran smjer studenta: {}", course);
        Integer year = InputValidation.inputPositiveYear(sc, "Godina studija: ");
        BigDecimal gpa = InputValidation.inputGpa(sc);
        Integer ects = InputValidation.inputPositiveEcts(sc, "Ukupni ECTS bodovi: ");
        System.out.println("Unesite broj kolegija koje student sluša: ");
        Integer numStudentSub = sc.nextInt();
        sc.nextLine();

        Set<SubjectName> sub = new HashSet<>();

        switch (year) {
            case 1 -> fillYear1(sc, numStudentSub, sub);
            case 2 -> fillYear2(sc, numStudentSub, sub);
            case 3 -> fillYear3(sc, numStudentSub, sub);
        }

        log.info("Odabrani kolegiji studenta: {}", sub);

        Student s = new Student.StudentBuilder(firstName, lastName, email, jmbag, oib)
                .phoneNumber(phone)
                .course(course.get())
                .year(year)
                .gpa(gpa)
                .ects(ects)
                .subjects(sub)
                .build();

        students.add(s);
        log.info("Dodan student: {}", s);
        JSONStorage.saveStudents(students);
    }

    /**
     * Dodaje kolegije za studente 1. godine prema korisničkom unosu.
     * @param sc   Scanner za unos
     * @param num  broj kolegija koje student odabire
     * @param sub  skup u koji se spremaju odabrani kolegiji
     */

    private void fillYear1(Scanner sc, int num, Set<SubjectName> sub) {
        for (int i = 0; i < num; i++) {
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
            System.out.println("11) OE");
            System.out.println("12) UWT");

            int kol = sc.nextInt();
            sc.nextLine();

            switch (kol) {
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

    /**
     * Dodaje kolegije za studente 2. godine prema korisničkom unosu.
     * @param sc   Scanner
     * @param num  broj kolegija
     * @param sub  skup kolegija
     */

    private void fillYear2(Scanner sc, int num, Set<SubjectName> sub) {
        for (int i = 0; i < num; i++) {
            System.out.println("Kolegiji za 2. godinu:");
            System.out.println("1) ASP");
            System.out.println("2) Arhitektura računala");
            System.out.println("3) KK3");
            System.out.println("4) OS");
            System.out.println("5) Prog u javi");
            System.out.println("6) VIS");
            System.out.println("7) Baze");
            System.out.println("8) KK4");
            System.out.println("9) Metodologija");
            System.out.println("10) Mreže");
            System.out.println("11) UNIX");
            System.out.println("12) NumMat");
            System.out.println("13) E-litr");
            System.out.println("14) ProgWeb");

            int kol = sc.nextInt();
            sc.nextLine();

            switch (kol) {
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

    /**
     * Dodaje kolegije za studente 3. godine prema korisničkom unosu.
     * @param sc   Scanner
     * @param num  broj kolegija
     * @param sub  skup odabranih kolegija
     */

    private void fillYear3(Scanner sc, int num, Set<SubjectName> sub) {
        for (int i = 0; i < num; i++) {
            System.out.println("Kolegiji za 3. godinu:");
            System.out.println("1) MSIR");
            System.out.println("2) Mreže admin");
            System.out.println("3) UNIX admin");
            System.out.println("4) Mrežne usluge");
            System.out.println("5) Napredne baze");
            System.out.println("6) Napredne tehnike prog");
            System.out.println("7) JavaScript");
            System.out.println("8) Python");
            System.out.println("9) OblikovanjeWeb");
            System.out.println("10) RNUTP");
            System.out.println("11) Rač igre");
            System.out.println("12) SRS");
            System.out.println("13) Duboko učenje");
            System.out.println("14) Mreže napredne");
            System.out.println("15) Android");
            System.out.println("16) ASP.NET");
            System.out.println("17) Praksa");
            System.out.println("18) WebJava");
            System.out.println("19) Završni");

            int kol = sc.nextInt();
            sc.nextLine();

            switch (kol) {
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
