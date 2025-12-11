package entity;

import entity.Interface.Schedulable;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Predstavlja kolokvij na sveučilištu.
 */
public final class Exam implements Schedulable, Serializable {

    private static final long serialVersionUID = 1L;

    private SubjectName subject;
    private LocalDateTime dateTime;
    private String location;
    private Integer duration;

    public Exam(){}

    /**
     * Radi novi kolokvij sa zadanim podacima.
     *
     * @param subject  naziv kolegija iz kojeg se piše kolokvij
     * @param dateTime datum i vrijeme održavanja kolokvija
     * @param location lokacija kolokvija
     * @param duration trajanje kolokvija u minutama
     */
    public Exam(SubjectName subject, LocalDateTime dateTime,
                String location, Integer duration) {
        this.subject = subject;
        this.dateTime = dateTime;
        this.location = location;
        this.duration = duration;
    }

    /**
     * Vraća naziv kolegija iz kojeg se piše kolokvij.
     *
     * @return naziv kolegija
     */
    public SubjectName getSubject() {
        return subject;
    }

    /**
     * Vraća datum i vrijeme održavanja kolokvija.
     *
     * @return datum i vrijeme kolokvija
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Vraća lokaciju kolokvija.
     *
     * @return lokacija
     */
    public String getLocation() {
        return location;
    }

    /**
     * Vraća trajanje kolokvija u minutama.
     *
     * @return trajanje u minutama
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Implementacija metode iz sučelja {@link Schedulable} koja vraća datum i vrijeme događaja.
     *
     * @return datum i vrijeme kolokvija
     */
    @Override
    public LocalDateTime dateTime() {
        return dateTime;
    }

    /**
     * Implementacija metode iz sučelja {@link Schedulable} koja vraća lokaciju događaja.
     *
     * @return lokacija kolokvija
     */
    @Override
    public String location() {
        return location;
    }

    /**
     * Implementacija metode iz sučelja {@link Schedulable} koja vraća trajanje događaja u minutama.
     *
     * @return trajanje kolokvija u minutama
     */
    @Override
    public Integer duration() {
        return duration;
    }

    /**
     * Vraća ispis kolokvija.
     *
     * @return ispis kolokvija
     */
    @Override
    public String toString() {
        return "Exam{" +
                "subject='" + subject + '\'' +
                ", dateTime=" + dateTime +
                ", location='" + location + '\'' +
                ", duration=" + duration +
                '}';
    }
}
