package entity;

public class User {
    private final String email;
    private final String password;
    private final String role;
    private final Person person;

    public User(String email, String password, String role, Person person) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.person = person;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", ime=" + person.getFirstName() +
                ", prezime=" + person.getLastName() +
                '}';
    }
}