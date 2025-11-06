package entity;

/**
 * Predstavlja osobu s osnovnim identifikacijskim i kontaktnim podacima.
 */

public abstract class Person implements Contactable{

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String oib;

    /**
     * Konstruira novu osobu sa zadanim podacima.
     *
     * @param firstName   ime osobe
     * @param lastName    prezime osobe
     * @param email       e-mail adresa osobe
     * @param phoneNumber telefonski broj osobe
     * @param oib         OIB osobe
     */

    public Person(String firstName, String lastName, String email, String phoneNumber, String oib) {

        if(firstName.isBlank()){
            throw new EmptyFieldException("Ime ne smije biti prazno!");
        }
        if(lastName.isBlank()){
            throw new EmptyFieldException("Prezime ne smije biti prazno!");
        }
        if(email.isBlank()){
            throw new EmptyFieldException("Email ne smije biti prazan!");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.oib = oib;
    }

    /**
     * Vraća ime osobe.
     *
     * @return ime
     */

    public String getFirstName() {
        return firstName;
    }

    /**
     * Vraća prezime osobe.
     *
     * @return prezime
     */

    public String getLastName() {
        return lastName;
    }

    /**
     * Vraća OIB osobe.
     *
     * @return OIB
     */

    public String getOib() {
        return oib;
    }

    /**
     * Vraća e-mail adresu osobe.
     *
     * @return e-mail adresa
     */

    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Vraća telefonski broj osobe.
     *
     * @return telefonski broj
     */

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Vraća ispis osobe u obliku:
     * "ime prezime (email) | OIB".
     *
     * @return ispis osobe
     */

    @Override
    public String toString(){
        return firstName + " "  + lastName + " (" + email + ")" + " | " + oib;
    }
}
