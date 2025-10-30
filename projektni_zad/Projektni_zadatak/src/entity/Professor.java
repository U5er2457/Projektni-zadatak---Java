package entity;

import java.util.Arrays;

public class Professor extends Person{
    private String[] subjects;

    public Professor(String firstName, String lastName, String email, String phoneNumber, String oib, String[] subjects) {
        super(firstName, lastName, email, phoneNumber, oib);
        this.subjects = subjects;
    }

    String fullName() {
        return firstName + " " + lastName;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", oib='" + oib + '\'' +
                ", subjects=" + Arrays.toString(subjects) +
                '}';
    }
}