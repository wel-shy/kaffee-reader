
import uk.dwelsh.kaffee.api.KaffeeClient
import uk.dwelsh.kaffee.models.RfidTag
import uk.dwelsh.kaffee.models.User
import uk.dwelsh.kaffee.store.RfidStore
import uk.dwelsh.kaffee.store.UserStore
import java.util.*

fun hidToString(bytes: ByteArray): String {
    val sb: StringBuilder = StringBuilder()
    val scanCodeMap = mapOf(
            0x04.toByte() to 'A',
            0x05.toByte() to 'B',
            0x06.toByte() to 'C',
            0x07.toByte() to 'D',
            0x08.toByte() to 'E',
            0x09.toByte() to 'F',
            0x0a.toByte() to 'G',
            0x0b.toByte() to 'H',
            0x0c.toByte() to 'I',
            0x0d.toByte() to 'J',
            0x0e.toByte() to 'K',
            0x0f.toByte() to 'L',
            0x10.toByte() to 'M',
            0x11.toByte() to 'N',
            0x12.toByte() to 'O',
            0x13.toByte() to 'P',
            0x14.toByte() to 'Q',
            0x15.toByte() to 'R',
            0x16.toByte() to 'S',
            0x17.toByte() to 'T',
            0x18.toByte() to 'U',
            0x19.toByte() to 'V',
            0x1a.toByte() to 'W',
            0x1b.toByte() to 'X',
            0x1c.toByte() to 'Y',
            0x1d.toByte() to 'Z',
            0x18.toByte() to '1',
            0x19.toByte() to '2',
            0x20.toByte() to '3',
            0x21.toByte() to '4',
            0x22.toByte() to '5',
            0x23.toByte() to '6',
            0x24.toByte() to '7',
            0x25.toByte() to '8',
            0x26.toByte() to '9',
            0x27.toByte() to '0'
    )

    println("read ${bytes.size} bytes")
    for (b in bytes) {
        val char = scanCodeMap[b];
        if (char != null) {
            sb.append(scanCodeMap[b])
        }
    }

    return sb.toString()
}


fun main(args: Array<String>) {
    // Initialise stores
    val rfidStore = RfidStore(String.format("%s/rfid_store.json", args[0]))
    val userStore = UserStore(String.format("%s/user_store.json", args[0]))

    // Listen for an input.
    val sc = Scanner(System.`in`)

    while (sc.hasNext()) {
        var input = sc.next()
        val inputBytes = input.toByteArray(Charsets.UTF_8)
        input = hidToString(inputBytes)

        println("Scanned: $input")

        val rfid: RfidTag? = rfidStore.get { t -> t?.tag == input }
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

        try {
            val api = KaffeeClient()
            val oldUser = user
            user.session = api.login(user)
            userStore.update(oldUser, user)

            api.logCoffee(user.session.token, rfid.coffee)
            println(String.format("Logged an %s for %s", rfid.coffee, rfid.userId))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
