package uk.dwelsh.kaffee.api;

import com.google.gson.Gson;
import uk.dwelsh.kaffee.HttpClient;
import uk.dwelsh.kaffee.api.payload.CoffeePayload;
import uk.dwelsh.kaffee.api.payload.LoginPayload;
import uk.dwelsh.kaffee.models.Coffee;
import uk.dwelsh.kaffee.models.Session;
import uk.dwelsh.kaffee.models.User;

/**
 * Client for interfacing with the Kaffee API.
 */
public class KaffeeClient {
    private HttpClient client;
    private String baseUrl;

    /**
     * Get an instance of the KaffeeClient.
     */
    public KaffeeClient() throws Exception {
        this.client = new HttpClient();
        this.baseUrl = System.getenv("KAFFEE_URL");

        if (this.baseUrl == null) {
            throw new Exception("Could not get url");
        }
    }

    /**
     * Authenticate with username and password.
     * @param user - User to authenticate as.
     * @return - Returns a session.
     * @throws Exception - Throws on http exception.
     */
    public Session login(User user) throws Exception {
        Gson gson = new Gson();
        String payload = gson.toJson(new LoginPayload(user.getEmail(), user.getPassword()));
        String response = client.post(baseUrl + "/token/login", payload, null);
        return gson.fromJson(response, Session.class);
    }

    /**
     * Log a coffee with the api.
     * @param token - Session token.
     * @param coffee - Coffee type.
     * @throws Exception - Throws on http exception.
     */
    public void LogCoffee(String token, Coffee coffee) throws Exception {
        Gson gson = new Gson();
        String payload = gson.toJson(new CoffeePayload(coffee));
        String response = client.post(baseUrl + "/coffee", payload, token);
        System.out.println(response);
    }
}
