package com.javafx.project.javafx_projekt.input;

import com.javafx.project.javafx_projekt.entity.Student;
import com.javafx.project.javafx_projekt.entity.Professor;
import com.javafx.project.javafx_projekt.entity.Subject;
import com.javafx.project.javafx_projekt.entity.Exam;
import jakarta.json.bind.*;

import java.nio.file.*;
import java.util.*;

/**
 * Spremanje i učitavanje entiteta u/iz JSON datoteka.
 */
public class JSONStorage {

    public static List<Student> loadStudents() {
        Path studentPath = Paths.get("students.json");

        try {
            Jsonb jsonb = JsonbBuilder.create();

            if (!Files.exists(studentPath)) {
                return new ArrayList<>();
            }

            String json = Files.readString(studentPath);

            List<Student> students = jsonb.fromJson(
                    json,
                    new ArrayList<Student>() {}.getClass().getGenericSuperclass()
            );

            return students;

        } catch (Exception e) {
            System.err.println("Pogreška pri čitanju students.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveStudents(List<Student> students) {
        try {
            Jsonb jsonb = JsonbBuilder.create();

            String json = jsonb.toJson(students);

            Files.writeString(Paths.get("students.json"), json);

        } catch (Exception e) {
            System.err.println("Pogreška pri zapisivanju students.json: " + e.getMessage());
        }
    }

    public static List<Professor> loadProfessors() {
        Path professorPath = Paths.get("professors.json");
        try {
            Jsonb jsonb = JsonbBuilder.create();

            if (!Files.exists(professorPath)) {
                return new ArrayList<>();
            }

            String json = Files.readString(professorPath);

            List<Professor> professors = jsonb.fromJson(
                    json,
                    new ArrayList<Professor>() {}.getClass().getGenericSuperclass()
            );

            return professors;

        } catch (Exception e) {
            System.err.println("Pogreška pri čitanju professors.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveProfessors(List<Professor> professors) {
        try {
            Jsonb jsonb = JsonbBuilder.create();

            String json = jsonb.toJson(professors);

            Files.writeString(Paths.get("professors.json"), json);

        } catch (Exception e) {
            System.err.println("Pogreška pri zapisivanju professors.json: " + e.getMessage());
        }
    }

    public static List<Subject> loadSubjects() {
        Path subjectPath = Paths.get("subjects.json");
        try {
            Jsonb jsonb = JsonbBuilder.create();

            if (!Files.exists(subjectPath)) {
                return new ArrayList<>();
            }

            String json = Files.readString(subjectPath);

            List<Subject> subjects = jsonb.fromJson(
                    json,
                    new ArrayList<Subject>() {}.getClass().getGenericSuperclass()
            );

            return subjects;

        } catch (Exception e) {
            System.err.println("Pogreška pri čitanju subjects.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveSubjects(List<Subject> subjects) {
        try {
            Jsonb jsonb = JsonbBuilder.create();

            String json = jsonb.toJson(subjects);

            Files.writeString(Paths.get("subjects.json"), json);

        } catch (Exception e) {
            System.err.println("Pogreška pri zapisivanju subjects.json: " + e.getMessage());
        }
    }

    public static List<Exam> loadExams() {
        Path examPath = Paths.get("exams.json");

        try {
            Jsonb jsonb = JsonbBuilder.create();

            if (!Files.exists(examPath)) {
                return new ArrayList<>();
            }

            String json = Files.readString(examPath);

            List<Exam> exams = jsonb.fromJson(
                    json,
                    new ArrayList<Exam>() {}.getClass().getGenericSuperclass()
            );

            return exams;

        } catch (Exception e) {
            System.err.println("Pogreška pri čitanju exams.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveExams(List<Exam> exams) {
        try {
            Jsonb jsonb = JsonbBuilder.create();

            String json = jsonb.toJson(exams);

            Files.writeString(Paths.get("exams.json"), json);

        } catch (Exception e) {
            System.err.println("Pogreška pri zapisivanju exams.json: " + e.getMessage());
        }
    }
}