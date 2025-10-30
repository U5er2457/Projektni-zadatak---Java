package entity;

import java.time.LocalDateTime;

public final class Exam implements Schedulable {
    private final String subject;
    private final LocalDateTime dateTime;
    private final String location;
    private final Integer duration;

    public Exam(String subject, LocalDateTime dateTime, String location, Integer duration) {
        this.subject = subject;
        this.dateTime = dateTime;
        this.location = location;
        this.duration = duration;
    }

    public String getSubject() {
        return subject;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }

    public Integer getDuration() {
        return duration;
    }


    @Override
    public LocalDateTime dateTime() {
        return dateTime;
    }

    @Override
    public String location() {
        return location;
    }

    @Override
    public Integer duration() {
        return duration;
    }

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
