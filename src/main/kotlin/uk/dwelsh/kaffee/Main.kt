
import uk.dwelsh.kaffee.api.KaffeeClient
import uk.dwelsh.kaffee.models.RfidTag
import uk.dwelsh.kaffee.models.User
import uk.dwelsh.kaffee.store.RfidStore
import uk.dwelsh.kaffee.store.UserStore
import java.util.*

fun main(args: Array<String>) {
    // Initialise stores
    val rfidStore = RfidStore(String.format("%s/rfid_store.json", args[0]))
    val userStore = UserStore(String.format("%s/user_store.json", args[0]))
    println("Created stores.")

    // Listen for an input.
    val sc = Scanner(System.`in`)

    while (sc.hasNext()) {
        val input = sc.next()
        println("received: $input")

        val rfid: RfidTag? = rfidStore.get { t -> t?.tag == input }
        println(String.format("Scanned: %s - Status: %s", input.trim(), if (rfid == null) "UNKNOWN" else "MATCH"))
        if (rfid == null) {
            println("RFID not recognised: $input")
            continue
        }

        println("Found: $rfid")

        val user: User? = userStore.get { u -> u?.email == rfid.userId }
        if (user == null) {
            println("Can not find details for user: " + rfid.userId)
            continue
        }

        println(user)

        val api = KaffeeClient()
        val oldUser = user
        user.session = api.login(user)
        userStore.update(oldUser, user)

        api.logCoffee(user.session.token, rfid.coffee)
        println(String.format("Logged an %s for %s", rfid.coffee, rfid.userId))
    }
}
