package app;

import app.data.BackupData;
import entity.*;
import java.io.*;
import java.util.List;

/**
 * Omogućuje spremanje i učitavanje backupa u binarnu datoteku.
 */
public class BackupManager {

    public static final String BACKUP_FILE_NAME = "backup.bin";

    /**
     * Serijalizira sve liste u backup.bin.
     */
    public static void saveBackup(List<Student> students,
                                  List<Professor> professors,
                                  List<Subject> subjects,
                                  List<Exam> exams) {

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(BACKUP_FILE_NAME))) {

            BackupData data = new BackupData(students, professors, subjects, exams);
            out.writeObject(data);

            System.out.println("Backup uspješno spremljen u " + BACKUP_FILE_NAME);

        } catch (IOException ex) {
            System.err.println("Greška pri spremanju backupa: " + ex.getMessage());
        }
    }

    /**
     * Deserijalizira backup.bin i vraća objekt BackupData.
     */
    public static BackupData loadBackup() {

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(BACKUP_FILE_NAME))) {

            BackupData data = (BackupData) in.readObject();

            System.out.println("Backup uspješno učitan iz " + BACKUP_FILE_NAME);
            return data;

        } catch (IOException ex) {
            System.err.println("Greška pri učitavanju backupa: " + ex.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            System.err.println("Greška: klasa nije pronađena - " + ex.getMessage());
            return null;
        }
    }
}
