package app.log;

import jakarta.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Zadužen za čitanje i pisanje XML loga.
 */
public class LogManager {

    private static final String LOG_FILE_NAME = "log.xml";

    /**
     * Učitava log iz XML datoteke u listu.
     */
    public static List<LogEntry> loadLog() {
        try {
            File xmlFile = new File(LOG_FILE_NAME);
            if (!xmlFile.exists()) {
                return new ArrayList<>();
            }

            JAXBContext context = JAXBContext.newInstance(LogEntries.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            LogEntries logEntries = (LogEntries) unmarshaller.unmarshal(xmlFile);

            if (logEntries.getEntries() == null) {
                return new ArrayList<>();
            }

            return new ArrayList<>(logEntries.getEntries());

        } catch (JAXBException e) {
            System.err.println("Greška pri čitanju XML loga: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Sprema trenutnu listu log zapisa u XML datoteku.
     */
    public static void saveLog(List<LogEntry> entries) {
        try {
            JAXBContext context = JAXBContext.newInstance(LogEntries.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            LogEntries wrapper = new LogEntries(entries);
            File xmlFile = new File(LOG_FILE_NAME);

            marshaller.marshal(wrapper, xmlFile);

        } catch (JAXBException e) {
            System.err.println("Greška pri zapisivanju XML loga: " + e.getMessage());
        }
    }

    /**
     * Ispisuje sadržaj log-a na ekran.
     */
    public static void printLog() {
        List<LogEntry> entries = loadLog();

        if (entries.isEmpty()) {
            System.out.println("Nema zapisa u XML logu.");
            return;
        }

        System.out.println("--- XML log ---");
        for (LogEntry e : entries) {
            System.out.println(e.getTime() + " | " + e.getAction() + " | " + e.getDetails());
        }
    }
}
