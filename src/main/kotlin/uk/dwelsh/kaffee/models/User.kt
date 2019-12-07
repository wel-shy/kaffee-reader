package uk.dwelsh.kaffee.models

class User(val email: String, val password: String, var session: Session) {

    override fun toString(): String {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", session=" + session +
                '}'
    }

}