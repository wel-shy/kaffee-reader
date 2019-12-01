package uk.dwelsh.kaffee.api;

import com.google.gson.Gson;
import uk.dwelsh.kaffee.HttpClient;
import uk.dwelsh.kaffee.api.payload.CoffeePayload;
import uk.dwelsh.kaffee.api.payload.LoginPayload;
import uk.dwelsh.kaffee.models.Coffee;
import uk.dwelsh.kaffee.models.Session;
import uk.dwelsh.kaffee.models.User;

public class KaffeeApi {
    private HttpClient client;
    private String baseUrl = "http://localhost:5000/api";

    public KaffeeApi() {
        this.client = new HttpClient();
    }

    public Session login(User user) throws Exception {
        Gson gson = new Gson();
        String payload = gson.toJson(new LoginPayload(user.getEmail(), user.getPassword()));
        String response = client.post(baseUrl + "/token/login", payload, null);
        return gson.fromJson(response, Session.class);
    }

    public void LogCoffee(String token, Coffee coffee) throws Exception {
        Gson gson = new Gson();
        String payload = gson.toJson(new CoffeePayload(coffee));
        String response = client.post(baseUrl + "/coffee", payload, token);
        System.out.println(response);
    }
}
