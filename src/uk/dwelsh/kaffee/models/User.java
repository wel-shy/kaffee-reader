package uk.dwelsh.kaffee.models;

public class User {
    private String email;
    private String password;
    private Session session;

    public User(String email, String password, Session session) {
        this.email = email;
        this.password = password;
        this.session = session;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", session=" + session +
                '}';
    }
}
