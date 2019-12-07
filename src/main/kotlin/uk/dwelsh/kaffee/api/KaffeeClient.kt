package uk.dwelsh.kaffee.api

import com.google.gson.Gson
import uk.dwelsh.kaffee.HttpClient
import uk.dwelsh.kaffee.api.payload.CoffeePayload
import uk.dwelsh.kaffee.api.payload.LoginPayload
import uk.dwelsh.kaffee.models.Coffee
import uk.dwelsh.kaffee.models.Session
import uk.dwelsh.kaffee.models.User

/**
 * Client for interfacing with the Kaffee API.
 */
class KaffeeClient {
    private val client: HttpClient = HttpClient()
    private val baseUrl: String? = System.getenv("KAFFEE_URL")
    /**
     * Authenticate with username and password.
     * @param user - User to authenticate as.
     * @return - Returns a session.
     * @throws Exception - Throws on http exception.
     */
    @Throws(Exception::class)
    fun login(user: User): Session {
        val gson = Gson()
        val payload = gson.toJson(LoginPayload(user.email, user.password))
        val response = client.post("$baseUrl/token/login", payload, null)
        return gson.fromJson(response, Session::class.java)
    }

    /**
     * Log a coffee with the api.
     * @param token - Session token.
     * @param coffee - Coffee type.
     * @throws Exception - Throws on http exception.
     */
    @Throws(Exception::class)
    fun logCoffee(token: String?, coffee: Coffee?) {
        if(token == null) throw Error("Token must be set")

        val gson = Gson()
        val payload = gson.toJson(CoffeePayload(coffee!!))
        val response = client.post("$baseUrl/coffee", payload, token)
        println(response)
    }

    /**
     * Get an instance of the KaffeeClient.
     */
    init {
        if (baseUrl == null) {
            throw Exception("Could not get url")
        }
    }
}