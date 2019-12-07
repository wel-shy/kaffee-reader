package uk.dwelsh.kaffee.store

import com.google.gson.Gson
import uk.dwelsh.kaffee.models.RfidTag

class RfidStore(path: String) : BaseStore<RfidTag?>(path) {
    override fun initialiseStore() {
        store = Gson().fromJson(file, Array<RfidTag>::class.java).toList()
    }
}