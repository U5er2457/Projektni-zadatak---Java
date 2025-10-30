package entity;

import java.math.BigDecimal;
import java.util.Arrays;

public class Student extends Person {
    private final String jmbag;
    private final String course;
    private final Integer year;
    private final BigDecimal gpa;
    private final Integer ects;
    private final String[] subjects;


    private Student(StudentBuilder builder) {
        super(builder.firstName, builder.lastName, builder.email, builder.phoneNumber, builder.oib);
        this.jmbag = builder.jmbag;
        this.course = builder.course;
        this.year = builder.year;
        this.gpa = builder.gpa;
        this.ects = builder.ects;
        this.subjects = builder.subjects;
    }

    String fullName() {
        return firstName + " " + lastName;
    }

    public String getJmbag() {
        return jmbag;
    }

    public String getCourse() {
        return course;
    }

    public Integer getYear() {
        return year;
    }

    public BigDecimal getGpa() {
        return gpa;
    }

    public Integer getEcts() {
        return ects;
    }

    public String[] getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jmbag='" + jmbag + '\'' +
                ", year=" + year +
                ", course='" + course + '\'' +
                ", gpa=" + gpa +
                ", ects=" + ects +
                ", subjects=" + subjects.length +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", oib='" + oib + '\'' +
                '}';
    }

    public static class StudentBuilder{

        private final String firstName;
        private final String lastName;
        private final String email;
        private final String jmbag;
        private final String oib;


        private String phoneNumber = "";
        private String course = "";
        private Integer year = 2025;
        private BigDecimal gpa = BigDecimal.ZERO;
        private Integer ects = 0;
        private String[] subjects = new String[]{""};

        public StudentBuilder(String firstName, String lastName, String email, String jmbag, String oib){
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.jmbag = jmbag;
            this.oib = oib;

        }

        public StudentBuilder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public StudentBuilder course(String course){
            this.course = course;
            return this;
        }

        public StudentBuilder year(Integer year){
            this.year = year;
            return this;
        }

        public StudentBuilder gpa(BigDecimal gpa){
            this.gpa = gpa;
            return this;
        }

        public StudentBuilder ects(Integer ects){
            this.ects = ects;
            return this;
        }

        public StudentBuilder subjects(String[] subjects){
            this.subjects = subjects;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }

}