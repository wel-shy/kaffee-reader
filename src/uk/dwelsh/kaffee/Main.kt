package uk.dwelsh.kaffee;

import uk.dwelsh.kaffee.api.KaffeeClient
import uk.dwelsh.kaffee.models.RfidTag
import uk.dwelsh.kaffee.store.RfidStore
import uk.dwelsh.kaffee.store.UserStore
import java.util.*

fun main(args: Array<String>) {
    println("Starting reader.")

    // Initialise store.
    val logger = Logger("log.txt")
    println("Created logger.")
    val store = RfidStore(String.format("%s/rfid_store.json", args[0]))
    val userStore = UserStore(String.format("%s/user_store.json", args[0]))

    println("Created stores.");

    // Listen for an input.
    val sc = Scanner("/dev/hidraw0")
    while (sc.hasNext()) {
        println("Waiting...")

        val input = sc.nextLine().trim()
        val rfid: RfidTag? = store.get { t -> t.tag == input }
        logger.log(String.format("Scanned: %s - Status: %s", input.trim(), if (rfid == null) "UNKNOWN" else "MATCH"));

        if (rfid == null) {
            continue
        }

        println(rfid.toString());

        val user = userStore.get { u -> u.email == rfid.userId }

        println(user)

        val api = KaffeeClient()
        val oldUser = user
        user.session = api.login(user)
        userStore.update(oldUser, user)

        api.LogCoffee(user.session.token, rfid.coffee)
        logger.log(String.format("Logged an %s for %s", rfid.coffee, rfid.userId))
    }
}
