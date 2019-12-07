package uk.dwelsh.kaffee.store

import com.google.gson.Gson
import uk.dwelsh.kaffee.models.User

class UserStore(path: String?) : BaseStore<User?>(path!!) {
    override fun initialiseStore() {
        store = Gson().fromJson(file, Array<User>::class.java).toList()
    }
}