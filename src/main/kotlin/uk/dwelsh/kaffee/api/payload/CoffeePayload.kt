package uk.dwelsh.kaffee.api.payload

import uk.dwelsh.kaffee.models.Coffee

class CoffeePayload(private val type: Coffee) {
    override fun toString(): String {
        return "CoffeePayload{" +
                "type=" + type +
                '}'
    }

}