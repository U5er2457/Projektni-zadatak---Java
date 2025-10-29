package entity;

public class User {
    private String email;
    private String password;
    private String role;
    private static final Integer numberOfUsers = 10;

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static void sortemail(User[] u){
        System.out.println("Email-ovi sortirani abecedno (A-Z):");
        for (Integer i = 0; i < u.length; i++) {
            for (Integer j = i + 1; j < u.length; j++) {
                if (u[j].getEmail().compareTo(u[i].getEmail()) < 0) {
                    User temp = u[i];
                    u[i] = u[j];
                    u[j] = temp;
                }
            }
            System.out.println(u[i].getEmail());
        }

    }

    }
