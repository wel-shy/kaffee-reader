package uk.dwelsh.kaffee.store

import com.google.gson.Gson
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

abstract class BaseStore<T>(private val path: String) : IStore<T> {
    @JvmField
    var store: List<T>? = null
    val file: String
        get() {
            val contentBuilder = StringBuilder()
            try {
                Files.lines(Paths.get(path), StandardCharsets.UTF_8).use { stream -> stream.forEach { s: String? -> contentBuilder.append(s).append("\n") } }
            } catch (e: IOException) {
                e.printStackTrace()
                return "[]"
            }
            return contentBuilder.toString()
        }

    abstract override fun initialiseStore()

    private fun persist() {
        val f = File(path)
        val path = f.absolutePath
        try {
            BufferedWriter(FileWriter(path)).use { bw ->
                val content = Gson().toJson(store)
                bw.write(content)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun get(search: (T) -> Boolean): T? {
        return store!!.find(search);
    }

    fun update(old: T, newVal: T) {
        store = store!!.stream().map { x: T -> if (x == old) newVal else x }.collect(Collectors.toList())
        persist()
    }

    init {
        initialiseStore()
    }
}