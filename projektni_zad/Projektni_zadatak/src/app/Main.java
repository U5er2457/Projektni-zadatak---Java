package app;

import entity.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    static void main(String[] args) {

        Subject prog = new Subject("Programiranje u jeziku Java", 5, LocalDateTime.of(2025, 10, 1, 13, 0), "Predavaona A, Borongaj", 60);
        Subject os = new Subject("Operacijski sustavi", 3, LocalDateTime.of(2024, 12, 5, 12, 0), "Predavaona A, Borongaj", 90);
        Subject asp = new Subject("Algoritmi i strukture podataka", 4, LocalDateTime.of(2025, 3, 21, 8, 0), "Predavaona A, Borongaj", 60);
        Subject eng = new Subject("Engleski jezik", 2, LocalDateTime.of(2025, 11, 8, 14, 30), "Predavaona B, Borongaj", 60);
        Subject mat = new Subject("Matematika", 3, LocalDateTime.of(2024, 6, 12, 11, 45), "Predavaona A, Borongaj", 90);
        Subject oop = new Subject("Objektno orijentirano programiranje", 7, LocalDateTime.of(2025, 11, 23, 9, 0), "Predavaona B, Borongaj", 90);

        Student student1 = new Student.StudentBuilder(
                "Ivan", "Horvat", "ihorvat@tvz.hr", "0246591467", "57246783510"
        )
                .phoneNumber("091-638-9371")
                .course("Racunarstvo")
                .year(2)
                .gpa(BigDecimal.valueOf(3.4))
                .ects(45)
                .subjects(new String[]{"Objektno orijentirano programiranje", "Engleski jezik", "Matematika", "Operacijski sustavi"})
                .build();

        Student student2 = new Student.StudentBuilder(
                "Ana", "Anić", "aanic@tvz.hr", "0538168250", "58132603740"
        )
                .phoneNumber("098-825-8570")
                .course("Informatika")
                .year(1)
                .gpa(BigDecimal.valueOf(3.2))
                .ects(30)
                .subjects(new String[]{"Objektno orijentirano programiranje", "Algoritmi i strukture podataka", "Operacijski sustavi"})
                .build();

        Professor profesor1 = new Professor("Marko", "Markić", "mmarkic@tvz.hr", "098-727-1952", "68251159690",
                new String[]{"Algoritmi i strukture podataka", "Operacijski sustavi"});

        Professor profesor2 = new Professor("Ivan", "Ivić", "iivic@tvz.hr", "091-627-8728", "96249051767",
                new String[]{"Matematika"});

        System.out.println("--STUDENTI--");

        System.out.println("Student1:");

        System.out.printf(student1.getFirstName() + " " + student1.getLastName() + " (" + student1.getEmail() + ") " + " | " + student1.getJmbag());

        System.out.println("\nKolegiji:");

        for (Integer i = 0; i < student1.getSubjects().length; i++) {
            System.out.println(student1.getSubjects()[i] + "\n");
        }

        System.out.println("Student2:");

        System.out.printf(student2.getFirstName() + " " + student2.getLastName() + " (" + student2.getEmail() + ") " + " | " + student2.getJmbag());

        System.out.println("\nKolegiji:");

        for (Integer i = 0; i < student2.getSubjects().length; i++) {
            System.out.println(student1.getSubjects()[i] + "\n");
        }

        System.out.println("--PROFESORI--");

        System.out.println("Profesor1:");

        System.out.printf(profesor1.getFirstName() + " " + profesor1.getLastName() + " (" + profesor1.getEmail() + ") " + " | " + profesor1.getOib());

        System.out.println("\nKolegiji:");

        for (Integer i = 0; i < profesor1.getSubjects().length; i++) {
            System.out.println(profesor1.getSubjects()[i] + "\n");
        }

        System.out.println("Profesor2:");

        System.out.printf(profesor2.getFirstName() + " " + profesor2.getLastName() + " (" + profesor2.getEmail() + ") " + " | " + profesor2.getOib());

        System.out.println("\nKolegiji:");

        for (Integer i = 0; i < profesor2.getSubjects().length; i++) {
            System.out.println(profesor2.getSubjects()[i] + "\n");
        }

        Exam matKolokvij = new Exam("Matematika", LocalDateTime.of(2025, 12, 15, 8, 0), "Predavaona B, Borongaj", 120);
        Exam osKolokvij = new Exam("Operacijski sustavi", LocalDateTime.of(2025, 11, 25, 10, 0), "Predavaona B, Borongaj", 90);
        Exam engKolokvij = new Exam("Engleski jezik", LocalDateTime.of(2025, 10, 20, 9, 0), "Predavaona B, Borongaj", 90);

        Schedulable[] schedule = new Schedulable[9];
        schedule[0] = oop;
        schedule[1] = prog;
        schedule[2] = mat;
        schedule[3] = eng;
        schedule[4] = os;
        schedule[5] = asp;
        schedule[6] = matKolokvij;
        schedule[7] = osKolokvij;
        schedule[8] = engKolokvij;

        Student[] studenti = {student1, student2};
        Subject[] kolegiji = {oop, prog, mat, eng, os, asp};


        Scanner sc = new Scanner(System.in);

        System.out.println("Želite li ispisati studenta s najvišim prosjekom (1) ili kolegij s najviše ECTS bodova (2)?");
        String odabir = sc.nextLine();

        if ("1".equals(odabir)) {
            Student maxProsjek = null;

            maxProsjek = studenti[0];
            for (Integer i = 1; i < studenti.length; i++) {
                if (studenti[i].getGpa().compareTo(maxProsjek.getGpa()) > 0) {
                    maxProsjek = studenti[i];
                }
            }
            System.out.println("\nStudent sa najvišim prosjekom: " + maxProsjek);
        } else if ("2".equals(odabir)) {
            Subject maxEcts = null;
            maxEcts = kolegiji[0];
            for (Integer i = 1; i < kolegiji.length; i++) {
                if (kolegiji[i].ects() > maxEcts.ects()) {
                    maxEcts = kolegiji[i];
                }
            }
            System.out.println("\nKolegij s najviše ECTS bodova: " + maxEcts);
        } else {
            System.out.println("\nNeispravan unos! Dozvoljeni unosi: 1 i 2");
        }
    }
}