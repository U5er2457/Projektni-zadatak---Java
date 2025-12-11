package app.log;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * Jedan zapis u XML logu.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LogEntry {

    @XmlElement
    private String time;

    @XmlElement
    private String action;

    @XmlElement
    private String details;

    public LogEntry() {
    }

    public LogEntry(String time, String action, String details) {
        this.time = time;
        this.action = action;
        this.details = details;
    }

    public String getTime() {
        return time;
    }

    public String getAction() {
        return action;
    }

    public String getDetails() {
        return details;
    }
}
