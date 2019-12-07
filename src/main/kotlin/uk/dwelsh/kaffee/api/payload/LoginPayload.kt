package uk.dwelsh.kaffee.api.payload

class LoginPayload(private val email: String, private val password: String) {
    override fun toString(): String {
        return "LoginPayload{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}'
    }

}