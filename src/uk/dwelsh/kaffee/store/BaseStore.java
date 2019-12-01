package uk.dwelsh.kaffee.store;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

abstract class BaseStore<T> implements IStore<T> {
    private final String path;
    protected List<T> store;

    BaseStore(String path) {
        this.path = path;
        this.initialiseStore();
    }

    public String getFile() {
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

//    @Override
//    public void persist() throws IOException {
//        File f = new File(this.path);
//        String path = f.getAbsolutePath();
//
//        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
//        for (T t : this.store) {
//            bw.write(serialiseModel(t) + System.getProperty("line.separator"));
//        }
//
//        bw.close();
//    }

    public T get(Predicate<? super T> searchLambda) {
        Optional<T> rfid = this.store.stream().filter(searchLambda).findFirst();
        return rfid.orElse(null);
    }
}
