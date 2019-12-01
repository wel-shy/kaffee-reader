package uk.dwelsh.kaffee.store;

import java.io.FileNotFoundException;
import java.util.function.Predicate;

public interface IStore<T> {
    void initialiseStore() throws FileNotFoundException;
    T get(Predicate<? super T> searchLambda);
}
