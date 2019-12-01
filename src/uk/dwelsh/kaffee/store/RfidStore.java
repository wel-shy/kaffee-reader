package uk.dwelsh.kaffee.store;

import com.google.gson.Gson;
import uk.dwelsh.kaffee.models.RfidTag;
import java.util.Arrays;

public class RfidStore extends BaseStore<RfidTag> {
    public RfidStore(String path) {
        super(path);
    }

    @Override
    public void initialiseStore() {
        this.store = Arrays.asList(new Gson().fromJson(getFile(), RfidTag[].class)) ;
    }
}
