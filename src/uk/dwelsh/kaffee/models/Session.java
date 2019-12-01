package uk.dwelsh.kaffee.models;

public class Session {
    private String token;
    private String refreshToken;

    public Session(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "Session{" +
                "token='" + token + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
