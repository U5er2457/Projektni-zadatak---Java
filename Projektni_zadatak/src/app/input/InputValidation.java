package app.input;

import entity.Exception.*;
import entity.SubjectName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Sadrži sve metode za validaciju korisničkog unosa.
 */

public class InputValidation {

    private static final Logger log = LoggerFactory.getLogger(InputValidation.class);

    /**
     * Traži unos godine i osigurava da je vrijednost pozitivan cijeli broj.
     *
     * @param sc  Scanner za unos
     * @param out Tekst koji se ispisuje korisniku
     * @return pozivitna godina
     */

    public static int inputPositiveYear(Scanner sc, String out) {
        while (true) {
            try {
                int year = inputInt(sc, out);
                if (year <= 0) {
                    throw new NegativeYearException("Vrijednost godine mora biti veći pozitivan broj.");
                }
                return year;
            } catch (InvalidInputException | NegativeYearException e) {
                System.out.println(e.getMessage());
                log.warn("Unesen negativan broj godine: {}", e.getMessage());
            }
        }
    }

    /**
     * Traži unos ECTS bodova i provjerava da je broj pozitivan.
     *
     * @param sc  Scanner za unos
     * @param out Tekst poruke
     * @return broj ECTS bodova
     */

    public static int inputPositiveEcts(Scanner sc, String out) {
        while (true) {
            try {
                int ects = inputInt(sc, out);
                if (ects <= 0) {
                    throw new NegativeEctsException("Vrijednost ECTS bodova mora biti veći pozitivan broj.");
                }
                return ects;
            } catch (InvalidInputException | NegativeEctsException e) {
                System.out.println(e.getMessage());
                log.warn("Unesen negativan broj ECTS bodova: {}", e.getMessage());
            }
        }
    }

    /**
     * Traži unos trajanja kolegija/kolokvija i provjerava da je pozitivan.
     *
     * @param sc  Scanner
     * @param out Poruka
     * @return trajanje u minutama
     */

    public static int inputPositiveDuration(Scanner sc, String out) {
        while (true) {
            try {
                int duration = inputInt(sc, out);
                if (duration <= 0) {
                    throw new NegativeDurationException("Vrijednost trajanja mora biti veći pozitivan broj.");
                }
                return duration;
            } catch (InvalidInputException | NegativeDurationException e) {
                System.out.println(e.getMessage());
                log.warn("Unesen negativan broj minuta: {}", e.getMessage());
            }
        }
    }

    /**
     * Traži unos mjeseca i provjerava da je pozitivan.
     *
     * @param sc  Scanner
     * @param out Poruka
     * @return pozitivan broj mjeseca
     */

    public static int inputPositiveMonth(Scanner sc, String out) {
        while (true) {
            try {
                int m = inputInt(sc, out);
                if (m <= 0) {
                    throw new NegativeMonthException("Broj mjeseci mora biti veći pozitivan broj.");
                }
                return m;
            } catch (InvalidInputException | NegativeMonthException e) {
                System.out.println(e.getMessage());
                log.warn("Unesen negativan broj mjeseca: {}", e.getMessage());
            }
        }
    }

    /**
     * Traži unos dana i provjerava da je pozitivan.
     *
     * @param sc  Scanner
     * @param out Poruka
     * @return pozitivan broj dana
     */

    public static int inputPositiveDay(Scanner sc, String out) {
        while (true) {
            try {
                int d = inputInt(sc, out);
                if (d <= 0) {
                    throw new NegativeDayException("Broj dana mora biti veći pozitivan broj.");
                }
                return d;
            } catch (InvalidInputException | NegativeDayException e) {
                System.out.println(e.getMessage());
                log.warn("Unesen negativan broj dana: {}", e.getMessage());
            }
        }
    }

    /**
     * Traži unos sati i provjerava da je pozitivan.
     *
     * @param sc  Scanner
     * @param out Tekst poruke
     * @return pozitivan broj sati
     */

    public static int inputPositiveHour(Scanner sc, String out) {
        while (true) {
            try {
                int h = inputInt(sc, out);
                if (h <= 0) {
                    throw new NegativeHourException("Broj sati mora biti veći pozitivan broj.");
                }
                return h;
            } catch (InvalidInputException | NegativeHourException e) {
                System.out.println(e.getMessage());
                log.warn("Unesen negativan broj sata: {}", e.getMessage());
            }
        }
    }

    /**
     * Traži unos cijelog broja.
     *
     * @param sc  Scanner
     * @param out Poruka za unos
     * @return uneseni cijeli broj
     * @throws InvalidInputException ako korisnik unese ne-brojčanu vrijednost
     */

    public static int inputInt(Scanner sc, String out) throws InvalidInputException {
        System.out.println(out);
        String line = sc.nextLine();
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Unos mora biti cijeli broj.");
        }
    }

    /**
     * Ponovljeno traži unos e-mail adrese sve dok korisnik ne unese valjani format.
     *
     * @param sc  Scanner
     * @param out Poruka
     * @return valjana email adresa
     */

    public static String readEmailWithRetry(Scanner sc, String out) {
        while (true) {
            try {
                return readEmail(sc, out);
            } catch (InvalidEmailException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Provjerava je li email valjan.
     *
     * @param sc  Scanner
     * @param out Poruka
     * @return uneseni email
     * @throws InvalidEmailException ako email ne sadrži '@'
     */

    public static String readEmail(Scanner sc, String out) throws InvalidEmailException {
        System.out.println(out);
        String email = sc.nextLine();
        if (!email.contains("@")) {
            throw new InvalidEmailException("Email mora sadržavati '@'.");
        }
        return email;
    }

    /**
     * Traži unos koji ne smije biti prazan.
     *
     * @param sc  Scanner
     * @param out Poruka
     * @return neprazan string
     */

    public static String readNonEmptyString(Scanner sc, String out) {
        while (true) {
            System.out.println(out);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Unos ne smije biti prazan.");
        }
    }

    /**
     * Traži unos SubjectName enum vrijednosti koji ne smije biti prazan.
     *
     * @param sc  Scanner
     * @param out Poruka
     * @return SubjectName vrijednost
     */

    public static SubjectName readNonEmptySubject(Scanner sc, String out) {
        while (true) {
            System.out.println(out);
            try {
                return SubjectName.valueOf(sc.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Neispravno ime kolegija.");
            }
        }
    }

    /**
     * Traži unos prosjeka (GPA) i provjerava ispravnost.
     *
     * @param sc Scanner
     * @return prosjek kao BigDecimal
     */

    public static BigDecimal inputGpa(Scanner sc) {
        while (true) {
            System.out.println("Prosjek: ");
            String input = sc.nextLine().trim().replace(',', '.');
            try {
                BigDecimal gpa = new BigDecimal(input);
                if (gpa.compareTo(BigDecimal.ZERO) < 0) throw new NegativeGpaException("Prosjek ne može biti negativan.");
                if (gpa.compareTo(BigDecimal.ONE) < 0) throw new InvalidGpaException("Prosjek ne može biti manji od 1.0!");
                return gpa;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Traži unos datuma i vremena.
     *
     * @param sc Scanner
     * @return LocalDateTime objekt
     */

    public static LocalDateTime inputDate(Scanner sc) {
        int godina = inputPositiveYear(sc, "Godina: ");
        int mjesec = inputPositiveMonth(sc, "Mjesec: ");
        int dan = inputPositiveDay(sc, "Dan: ");
        int sat = inputPositiveHour(sc, "Sat: ");
        int minute = inputInt(sc, "Minute: ");
        return LocalDateTime.of(godina, mjesec, dan, sat, minute);
    }
}
