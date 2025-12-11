package app.log;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper za listu zapisa loga koji je korijenski element u XML-u.
 */
@XmlRootElement(name = "log")
@XmlAccessorType(XmlAccessType.FIELD)
public class LogEntries {

    @XmlElement(name = "entry")
    private List<LogEntry> entries = new ArrayList<>();

    public LogEntries() {}

    public LogEntries(List<LogEntry> entries) {
        this.entries = entries;
    }

    public List<LogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<LogEntry> entries) {
        this.entries = entries;
    }
}
