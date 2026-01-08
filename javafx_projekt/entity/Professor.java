package com.javafx.project.javafx_projekt.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * Predstavlja profesora, nasljeđuje {@link Person} i dodaje popis kolegija koje profesor predaje.
 */
public class Professor extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private Set<SubjectName> subjects;

    public Professor(){}

    /**
     * Radi novog profesora sa zadanim podacima.
     *
     * @param firstName   ime profesora
     * @param lastName    prezime profesora
     * @param email       e-mail adresa profesora
     * @param phoneNumber telefonski broj profesora
     * @param oib         OIB profesora
     * @param subjects    nazivi kolegija koje profesor predaje
     */
    public Professor(String firstName, String lastName, String email,
                     String phoneNumber, String oib, Set<SubjectName> subjects) {
        super(firstName, lastName, email, phoneNumber, oib);
        this.subjects = subjects;
    }

    /**
     * Vraća puno ime profesora (ime prezime).
     *
     * @return puno ime profesora
     */
    String fullName() {
        return firstName + " " + lastName;
    }

    /**
     * Vraća polje kolegija koje profesor predaje.
     *
     * @return polje kolegija
     */
    public Set<SubjectName> getSubjects() {
        return subjects;
    }

    /**
     * Postavlja polje kolegija koje profesor predaje.
     *
     * @param subjects novo polje kolegija
     */
    public void setSubjects(Set<SubjectName> subjects) {
        this.subjects = subjects;
    }

    /**
     * Ispis profesora s osnovnim podacima.
     *
     * @return ispis profesora
     */
    @Override
    public String toString() {
        return "Professor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", oib='" + oib + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}