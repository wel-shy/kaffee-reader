package uk.dwelsh.kaffee.api.payload;

import uk.dwelsh.kaffee.models.Coffee;

public class CoffeePayload {
    private Coffee type;

    public CoffeePayload(Coffee type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CoffeePayload{" +
                "type=" + type +
                '}';
    }
}
