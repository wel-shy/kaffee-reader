package uk.dwelsh.kaffee.models

class Session(val token: String, private val refreshToken: String) {

    override fun toString(): String {
        return "Session{" +
                "token='" + token + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}'
    }

}