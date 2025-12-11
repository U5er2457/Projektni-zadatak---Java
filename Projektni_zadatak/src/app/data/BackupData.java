package app.data;

import entity.Student;
import entity.Professor;
import entity.Subject;
import entity.Exam;

import java.io.Serializable;
import java.util.List;

/**
 * Sadrži sve podatke koje spremamo u backup.bin.
 */
public class BackupData implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Student> students;
    private List<Professor> professors;
    private List<Subject> subjects;
    private List<Exam> exams;

    public BackupData() {
    }

    public BackupData(List<Student> students,
                      List<Professor> professors,
                      List<Subject> subjects,
                      List<Exam> exams) {
        this.students = students;
        this.professors = professors;
        this.subjects = subjects;
        this.exams = exams;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<Exam> getExams() {
        return exams;
    }
}
