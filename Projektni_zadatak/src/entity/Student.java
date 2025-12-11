package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Predstavlja studenta na sveučilištu, nasljeđuje {@link Person} i dodaje popis kolegija koje student sluša.
 */

public class Student extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jmbag;
    private Course course;
    private Integer year;
    private BigDecimal gpa;
    private Integer ects;
    private Set<SubjectName> subjects;

    public Student(){}

    /**
     * Koristi {@link StudentBuilder} za inicijalizaciju svih polja.
     * @param builder builder iz kojeg se preuzimaju vrijednosti polja
     */

    private Student(StudentBuilder builder) {
        super(builder.firstName, builder.lastName, builder.email, builder.phoneNumber, builder.oib);
        this.jmbag = builder.jmbag;
        this.course = builder.course;
        this.year = builder.year;
        this.gpa = builder.gpa;
        this.ects = builder.ects;
        this.subjects = builder.subjects;
    }
    /**
     * Vraća JMBAG studenta.
     *
     * @return JMBAG
     */
    public String getJmbag() {return jmbag;}
    /**
     * Vraća smjer studenta.
     *
     * @return smjer
     */
    public Course getCourse() {return course;}
    /**
     * Vraća trenutnu godinu studija.
     *
     * @return godina studija
     */
    public Integer getYear() {return year;}
    /**
     * Vraća prosjek ocjena studenta.
     *
     * @return prosjek
     */
    public BigDecimal getGpa() {return gpa;}
    /**
     * Vraća ukupan broj ECTS bodova koje je student ostvario.
     *
     * @return broj ECTS bodova
     */
    public Integer getEcts() {return ects;}
    /**
     * Vraća polje kolegija koje student sluša.
     *
     * @return polje kolegija
     */
    public Set<SubjectName> getSubjects() {return subjects;}
    /**
     * Vraća ispis studenta.
     *
     * @return ispis studenta
     */
    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", oib='" + oib + '\'' +
                ", jmbag='" + jmbag + '\'' +
                ", course='" + course + '\'' +
                ", year=" + year +
                ", gpa=" + gpa +
                ", ects=" + ects +
                ", subjects=" + subjects +
                '}';
    }

    /**
     * Implementira „builder pattern" za kreiranje objekata {@link Student}.
     */

    public static class StudentBuilder {

        String firstName;
        String lastName;
        String email;
        String phoneNumber;
        String oib;
        String jmbag;
        Course course;
        Integer year;
        BigDecimal gpa;
        Integer ects;
        Set<SubjectName> subjects = new HashSet<>();
        /**
         * Radi builder sa zadanim podacima.
         *
         * @param firstName ime studenta
         * @param lastName  prezime studenta
         * @param email     e-mail adresa studenta
         * @param jmbag     JMBAG studenta
         * @param oib       OIB studenta
         */
        public StudentBuilder(String firstName, String lastName,
                              String email, String jmbag, String oib) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.jmbag = jmbag;
            this.oib = oib;
        }
        /**
         * Postavlja telefonski broj u builderu.
         *
         * @param phoneNumber telefonski broj
         * @return referenca na {@code StudentBuilder}
         */
        public StudentBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        /**
         * Postavlja naziv studija u builderu.
         *
         * @param course naziv studija
         * @return referenca na {@code StudentBuilder}
         */
        public StudentBuilder course(Course course) {
            this.course = course;
            return this;
        }
        /**
         * Postavlja godinu studija u builderu.
         *
         * @param year godina studija
         * @return referenca na {@code StudentBuilder}
         */
        public StudentBuilder year(Integer year) {
            this.year = year;
            return this;
        }
        /**
         * Postavlja prosjek ocjena u builderu.
         *
         * @param gpa prosjek ocjena
         * @return referenca na {@code StudentBuilder}
         */
        public StudentBuilder gpa(BigDecimal gpa) {
            this.gpa = gpa;
            return this;
        }
        /**
         * Postavlja broj ECTS bodova u builderu.
         *
         * @param ects broj ECTS bodova
         * @return referenca na {@code StudentBuilder}
         */
        public StudentBuilder ects(Integer ects) {
            this.ects = ects;
            return this;
        }
        /**
         * Postavlja polje kolegija u builderu.
         *
         * @param subjects polje naziva kolegija
         * @return referenca na {@code StudentBuilder}
         */
        public StudentBuilder subjects(Set<SubjectName> subjects) {
            this.subjects = subjects;
            return this;
        }
        /**
         * Stvara novi objekt {@link Student} na temelju vrijednosti postavljenih u builderu.
         *
         * @return nova instanca klase {@link Student}
         */
        public Student build() {
            return new Student(this);
        }
    }
}
