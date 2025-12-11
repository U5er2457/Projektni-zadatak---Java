package app;

import app.input.ExamInput;
import app.input.ProfessorInput;
import app.input.StudentInput;
import app.input.SubjectInput;
import app.log.LogEntry;
import app.log.LogManager;
import app.data.BackupData;
import entity.*;

import java.time.LocalDateTime;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Upravlja tokovima aplikacije kroz izbornik.
 * Omogućava korisniku interakciju s aplikacijom preko izbornika
 */

public class Menu {

    private static final Logger log = LoggerFactory.getLogger(Menu.class);
    private final List<LogEntry> logEntries = LogManager.loadLog();

    private final List<Subject> subjects = JSONStorage.loadSubjects();
    private final List<Exam> exams = JSONStorage.loadExams();
    private final List<Student> students = JSONStorage.loadStudents();
    private final List<Professor> professors = JSONStorage.loadProfessors();

    private final SubjectInput subject = new SubjectInput(subjects);
    private final ExamInput exam = new ExamInput(exams);
    private final StudentInput student = new StudentInput(students);
    private final ProfessorInput professor = new ProfessorInput(professors);

    private final Print print = new Print(students, professors, subjects, exams);

    private void logAction(String action, String details) {
        LogEntry entry = new LogEntry(
                LocalDateTime.now().toString(),
                action,
                details
        );
        logEntries.add(entry);
        LogManager.saveLog(logEntries);
    }


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
            System.out.println("11) Spremi backup u backup.bin");
            System.out.println("12) Učitaj backup iz backup.bin");
            System.out.println("13) Ispiši XML log");
            System.out.println("0) Izlaz");

            String odabir = sc.nextLine();
            log.debug("Odabrana opcija: {}", odabir);
            logAction("IZBORNIK", "Odabrana opcija: " + odabir);

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
                case "11" -> {
                    BackupManager.saveBackup(students, professors, subjects, exams);
                    logAction("BACKUP_SAVE", "Spremanje backup.bin");
                }
                case "12" -> {
                    logAction("BACKUP_LOAD", "Pokušaj učitavanja backupa");
                    BackupData backup = BackupManager.loadBackup();
                    if (backup != null) {
                        students.clear();
                        students.addAll(backup.getStudents());

                        professors.clear();
                        professors.addAll(backup.getProfessors());

                        subjects.clear();
                        subjects.addAll(backup.getSubjects());

                        exams.clear();
                        exams.addAll(backup.getExams());

                        JSONStorage.saveStudents(students);
                        JSONStorage.saveProfessors(professors);
                        JSONStorage.saveSubjects(subjects);
                        JSONStorage.saveExams(exams);

                        System.out.println("Podaci su zamijenjeni podacima iz backup datoteke.");
                        logAction("BACKUP_LOAD_OK", "Backup uspješno učitan i primijenjen");
                    } else {
                        System.out.println("Nije moguće učitati backup (provjeri postoji li " +
                                BackupManager.BACKUP_FILE_NAME + ").");
                        logAction("BACKUP_LOAD_FAIL", "Učitavanje backupa nije uspjelo");
                    }
                }
                case "13" -> {
                    logAction("LOG_PRINT", "Ispis XML loga");
                    LogManager.printLog();
                }
                case "0" -> notZero = false;
                default -> System.out.println("Neispravan odabir!");
            }
        }

        sc.close();
    }
}
