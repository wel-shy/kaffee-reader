package uk.dwelsh.kaffee.store;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class BaseStore<T> implements IStore<T> {
    private final String path;
    List<T> store;

    BaseStore(String path) {
        this.path = path;
        this.initialiseStore();
    }

    String getFile() {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(this.path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return "[]";
        }

        return contentBuilder.toString();
    }

    public abstract void initialiseStore();

    private void persist() {
        File f = new File(this.path);
        String path = f.getAbsolutePath();

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            String content = new Gson().toJson(this.store);
            bw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T get(Predicate<? super T> searchLambda) {
        Optional<T> rfid = this.store.stream().filter(searchLambda).findFirst();
        return rfid.orElse(null);
    }

    public void update(T old, T newVal) {
        this.store = this.store.stream().map(x -> x.equals(old) ? newVal : x).collect(Collectors.toList());
        this.persist();
    }
}
