package uk.dwelsh.kaffee;

import java.text.SimpleDateFormat;
import java.util.Date;

class Utils {
    static String getTimestamp() {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return sdf.format(new Date());
    }
}
