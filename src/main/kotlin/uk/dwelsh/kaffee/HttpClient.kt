package uk.dwelsh.kaffee

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.util.*

class HttpClient {
    private val client = OkHttpClient()
    @Throws(Exception::class)
    fun post(url: String?, json: String?, token: String?): String {
        val body = RequestBody.create(JSON, json!!)
        val request = Request.Builder()
                .url(url!!)
                .post(body)
                .addHeader("Authorization", "Bearer $token")
                .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw Exception("" + response.code)
            }
            return Objects.requireNonNull(response.body)!!.string()
        }
    }

    companion object {
        private val JSON: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()
    }
}