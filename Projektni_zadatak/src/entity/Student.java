package entity;

import java.math.BigDecimal;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private String jmbag;
    private String oib;
    private String phoneNumber;
    private String course;
    private Integer year;
    private BigDecimal gpa;
    private Integer ects;
    private String[] subjects;


    public Student(String firstName, String lastName, String email, String jmbag, String oib, String phoneNumber, String course, Integer year, BigDecimal gpa, Integer ects, String[] subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jmbag = jmbag;
        this.oib = oib;
        this.phoneNumber = phoneNumber;
        this.course = course;
        this.year = year;
        this.gpa = gpa;
        this.ects = ects;
        this.subjects = subjects;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getGpa() {
        return gpa;
    }

    public void setGpa(BigDecimal gpa) {
        this.gpa = gpa;
    }

    public Integer getEcts() {
        return ects;
    }

    public void setEcts(Integer ects) {
        this.ects = ects;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }


}