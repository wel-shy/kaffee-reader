package uk.dwelsh.kaffee.api.payload;

public class LoginPayload {
    private String email;
    private String password;

    public LoginPayload(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginPayload{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
