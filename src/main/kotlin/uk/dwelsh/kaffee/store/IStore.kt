package uk.dwelsh.kaffee.store

import java.io.FileNotFoundException

interface IStore<T> {
    @Throws(FileNotFoundException::class)
    fun initialiseStore()

    operator fun get(search: (T) -> Boolean): T?
}