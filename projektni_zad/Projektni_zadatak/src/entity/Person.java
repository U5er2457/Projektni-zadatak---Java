package entity;

public abstract class Person implements Contactable{

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String oib;

    public Person(String firstName, String lastName, String email, String phoneNumber, String oib) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.oib = oib;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOib() {
        return oib;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString(){
        return firstName + " "  + lastName + " (" + email + ")" + " | " + oib;
    }
}
