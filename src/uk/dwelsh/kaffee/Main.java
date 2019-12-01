package uk.dwelsh.kaffee;

import uk.dwelsh.kaffee.api.KaffeeClient;
import uk.dwelsh.kaffee.models.RfidTag;
import uk.dwelsh.kaffee.models.User;
import uk.dwelsh.kaffee.store.RfidStore;
import uk.dwelsh.kaffee.store.UserStore;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Logger logger = new Logger("log.txt");
        RfidStore store = new RfidStore(String.format("%s/rfid_store.json", args[1]));
        UserStore userStore = new UserStore(String.format("%s/user_store.json", args[1]));

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine().trim();

            RfidTag rfid = store.get(t -> t.getTag().equals(input));

            logger.log(String.format("Scanned: %s - Status: %s", args[0], rfid == null ? "UNKNOWN" : "MATCH"));

            if (rfid == null) {
                return;
            }

            User user = userStore.get(u -> u.getEmail().equals(rfid.getUserId()));

            System.out.println(user);

            KaffeeClient api = new KaffeeClient();
            User oldUser = user;
            user.setSession(api.login(user));
            userStore.update(oldUser, user);

            api.LogCoffee(user.getSession().getToken(), rfid.getCoffee());
            logger.log(String.format("Logged an %s for %s", rfid.getCoffee(), rfid.getUserId()));
        }
    }
}
