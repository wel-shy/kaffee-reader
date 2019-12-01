package uk.dwelsh.kaffee;

import uk.dwelsh.kaffee.api.KaffeeApi;
import uk.dwelsh.kaffee.models.Coffee;
import uk.dwelsh.kaffee.models.RfidTag;
import uk.dwelsh.kaffee.models.Session;
import uk.dwelsh.kaffee.models.User;
import uk.dwelsh.kaffee.store.RfidStore;
import uk.dwelsh.kaffee.store.UserStore;

public class Main {

    public static void main(String[] args) throws Exception {
        RfidStore store = new RfidStore(args[1] + "/rfid_store.json");
        Logger logger = new Logger("log.txt");

        RfidTag rfid = store.get(t -> t.getTag().equals(args[0]));
        String template = "%s - Scanned: %s - Status: %s";
        logger.log(String.format(template, Utils.getTimestamp(), args[0], rfid == null ? "UNKNOWN" : "MATCH"));

        if (rfid == null) {
            throw new Exception("RFID not recognised");
        }

        System.out.println(rfid);

        UserStore userStore = new UserStore(args[1] + "/user_store.json");
        User user = userStore.get(u -> u.getEmail().equals(rfid.getUserId()));

        System.out.println(user);

        KaffeeApi api = new KaffeeApi();
        Session session = api.login(user);
        System.out.println(session);

        api.LogCoffee(session.getToken(), Coffee.Americano);
        logger.log("Logged an Americano");
    }
}
