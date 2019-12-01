package uk.dwelsh.kaffee;

import okhttp3.*;

import java.util.Objects;

public class HttpClient {
    private static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    public String post(String url, String json, String token) throws Exception {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", "Bearer " + token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("" + response.code());
            }
            return Objects.requireNonNull(response.body()).string();
        }
    }
}
