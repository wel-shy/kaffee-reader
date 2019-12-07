package uk.dwelsh.kaffee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Logger {
    private final String path;

    Logger(String path) {
        this.path = path;
    }

    void log(String msg) {
        try(FileWriter fw = new FileWriter(new File(path).getAbsolutePath(), true)) {
            fw.write(Utils.getTimestamp() + " - " + msg + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
