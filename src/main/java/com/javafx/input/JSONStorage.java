package com.javafx.input;

import com.javafx.entity.Student;
import com.javafx.entity.Professor;
import com.javafx.entity.Subject;
import com.javafx.entity.Exam;
import jakarta.json.bind.*;

import java.nio.file.*;
import java.util.*;

/**
 * Spremanje i učitavanje entiteta u/iz JSON datoteka.
 */
public class JSONStorage {

    private JSONStorage(){}

    public static List<Student> loadStudents() {
        Path studentPath = Paths.get("students.json");

        try {
            try (Jsonb jsonb = JsonbBuilder.create()) {

                if (!Files.exists(studentPath)) {
                    return new ArrayList<>();
                }

                String json = Files.readString(studentPath);

                return jsonb.fromJson(
                        json,
                        ArrayList.class
                );
            }

        } catch (Exception e) {
            System.err.println("Pogreška pri čitanju students.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveStudents(List<Student> students) {
        try {
            String json;
            try (Jsonb jsonb = JsonbBuilder.create()) {

                json = jsonb.toJson(students);
            }

            Files.writeString(Paths.get("students.json"), json);

        } catch (Exception e) {
            System.err.println("Pogreška pri zapisivanju students.json: " + e.getMessage());
        }
    }

    public static List<Professor> loadProfessors() {
        Path professorPath = Paths.get("professors.json");
        try {
            try (Jsonb jsonb = JsonbBuilder.create()) {

                if (!Files.exists(professorPath)) {
                    return new ArrayList<>();
                }

                String json = Files.readString(professorPath);

                return jsonb.fromJson(
                        json,
                        ArrayList.class
                );
            }

        } catch (Exception e) {
            System.err.println("Pogreška pri čitanju professors.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveProfessors(List<Professor> professors) {
        try {
            String json;
            try (Jsonb jsonb = JsonbBuilder.create()) {

                json = jsonb.toJson(professors);
            }

            Files.writeString(Paths.get("professors.json"), json);

        } catch (Exception e) {
            System.err.println("Pogreška pri zapisivanju professors.json: " + e.getMessage());
        }
    }

    public static List<Subject> loadSubjects() {
        Path subjectPath = Paths.get("subjects.json");
        try {
            try (Jsonb jsonb = JsonbBuilder.create()) {

                if (!Files.exists(subjectPath)) {
                    return new ArrayList<>();
                }

                String json = Files.readString(subjectPath);

                return jsonb.fromJson(
                        json,
                        ArrayList.class
                );
            }

        } catch (Exception e) {
            System.err.println("Pogreška pri čitanju subjects.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveSubjects(List<Subject> subjects) {
        try {
            String json;
            try (Jsonb jsonb = JsonbBuilder.create()) {

                json = jsonb.toJson(subjects);
            }

            Files.writeString(Paths.get("subjects.json"), json);

        } catch (Exception e) {
            System.err.println("Pogreška pri zapisivanju subjects.json: " + e.getMessage());
        }
    }

    public static List<Exam> loadExams() {
        Path examPath = Paths.get("exams.json");

        try {
            try (Jsonb jsonb = JsonbBuilder.create()) {

                if (!Files.exists(examPath)) {
                    return new ArrayList<>();
                }

                String json = Files.readString(examPath);

                return jsonb.fromJson(
                        json,
                        ArrayList.class
                );
            }

        } catch (Exception e) {
            System.err.println("Pogreška pri čitanju exams.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveExams(List<Exam> exams) {
        try {
            String json;
            try (Jsonb jsonb = JsonbBuilder.create()) {

                json = jsonb.toJson(exams);
            }

            Files.writeString(Paths.get("exams.json"), json);

        } catch (Exception e) {
            System.err.println("Pogreška pri zapisivanju exams.json: " + e.getMessage());
        }
    }
}