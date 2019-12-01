package uk.dwelsh.kaffee.store;

import com.google.gson.Gson;
import uk.dwelsh.kaffee.models.User;

import java.util.Arrays;

public class UserStore extends BaseStore<User>{

    public UserStore(String path) {
        super(path);
    }

    @Override
    public void initialiseStore() {
        this.store = Arrays.asList(new Gson().fromJson(getFile(), User[].class)) ;
    }
}
