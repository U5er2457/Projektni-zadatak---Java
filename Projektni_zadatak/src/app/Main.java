package app;

import entity.Professor;
import entity.Student;
import entity.User;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    private static final Integer numberOfUsers = 5;
    private static final Integer numberOfSubjects = 5;
    public static Integer numberOfStudents = 0;
    public static Integer numberOfProfessors = 0;

    static void main() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Unesite podatke o korisnicima:");

        User[] u = generateUsers(sc);


        Integer num = 1;

        for (User user : u) {
            System.out.println(num + ". " + user.getEmail() + " " + user.getPassword());
            num++;
        }

        User.sortemail(u);

        System.out.println("-- PRIJAVA --");
        System.out.println("E-mail:");
        String email = sc.nextLine();
        System.out.println("Lozinka:");
        String pass = sc.nextLine();

        String odg;
        odg = "DA";
        for (Integer i = 0; i < u.length; i++) {
            if (u[i].getEmail().equals(email) && u[i].getPassword().equals(pass)) {
                System.out.println("Unesite podatke o korisniku:");
                if ("S".equals(u[i].getRole())) {
                    numberOfStudents++;
                    Student[] s = generateStudents(sc);

                    Integer Num = 1;

                    for (Student student : s) {
                        System.out.println(Num + ". " + student.getFirstName() + " " + student.getLastName() + " | " + student.getJmbag());
                        Num++;
                    }
                } else if ("P".equals(u[i].getRole())) {
                    numberOfProfessors++;
                    Professor[] p = generateProfessors(sc);

                    Integer Num = 1;

                    for (Professor professor : p) {
                        System.out.println(Num + ". " + professor.getFirstName() + " " + professor.getLastName() + " | " + professor.getEmail());
                        Num++;
                    }
                } else {
                    System.out.println("Neispravan unos!");
                }
                }

        }
        System.out.println("Nastaviti s unosom podataka? (DA / NE)");
        odg = sc.nextLine();

        do{
            if ("NE".equals(odg)){
                break;
            }
            else{
                System.out.println("Neispravan unos odgovora!");
                System.out.println("Nastaviti s unosom podataka? (DA / NE)");
                odg = sc.nextLine();
            }
        }while (!"DA".equals(odg) || !"NE".equals(odg));


    }
        private static User[] generateUsers (Scanner sc){

            User[] users = new User[numberOfUsers];

            for (Integer i = 0; i < numberOfUsers; i++) {
                System.out.println("Unesite email " + (i + 1) + ". korisnika:");
                String email = sc.nextLine();

                System.out.println("Unesite lozinku " + (i + 1) + ". korisnika:");
                String password = sc.nextLine();

                System.out.println("Jeste li student(S) ili profesor(P)?");
                String role = sc.nextLine();
                User newUser = new User(email, password, role);
                users[i] = newUser;

            }
            return users;
        }

        private static Student[] generateStudents (Scanner sc){
            Student[] students = new Student[numberOfStudents];

            for (Integer i = 0; i < numberOfStudents; i++) {
                System.out.println("Unesite ime studenta:");
                String firstName = sc.nextLine();

                System.out.println("Unesite prezime studenta:");
                String lastName = sc.nextLine();

                System.out.println("Unesite email studenta:");
                String email = sc.nextLine();

                System.out.println("Unesite jmbag studenta:");
                String jmbag = sc.nextLine();

                System.out.println("Unesite oib studenta:");
                String oib = sc.nextLine();

                System.out.println("Unesite broj mobitela studenta:");
                String phoneNumber = sc.nextLine();

                System.out.println("Unesite smjer studenta:");
                String course = sc.nextLine();

                System.out.println("Unesite godinu studija studenta:");
                Integer year = sc.nextInt();

                System.out.println("Unesite prosjek ocjena studenta:");
                BigDecimal gpa = sc.nextBigDecimal();

                System.out.println("Unesite broj ects bodova studenta:");
                Integer ects = sc.nextInt();

                System.out.println("Unesite kolegije studenta:");
                String[] subjects = {"", "", "", "", ""};
                for (Integer j = 0; j < numberOfSubjects; j++) {
                    subjects[i] = sc.nextLine();
                }

                Student newStudent = new Student(firstName, lastName, email, jmbag, oib, phoneNumber, course, year, gpa, ects, subjects);
                students[i] = newStudent;

            }
            return students;
        }

        private static Professor[] generateProfessors (Scanner sc){
            Professor[] professors = new Professor[numberOfProfessors];

            for (Integer i = 0; i < numberOfProfessors; i++) {
                System.out.println("Unesite ime profesora:");
                String firstName = sc.nextLine();

                System.out.println("Unesite prezime profesora:");
                String lastName = sc.nextLine();

                System.out.println("Unesite email profesora:");
                String email = sc.nextLine();

                System.out.println("Unesite broj mobitela profesora:");
                String phoneNumber = sc.nextLine();

                System.out.println("Unesite kolegije profesora:");
                String[] subjects = {"", "", "", "", ""};
                for (Integer j = 0; j < numberOfSubjects; j++) {
                    subjects[i] = sc.nextLine();
                }

                Professor newProfessor = new Professor(firstName, lastName, email, phoneNumber, subjects);
                professors[i] = newProfessor;

            }
            return professors;
        }

    }
