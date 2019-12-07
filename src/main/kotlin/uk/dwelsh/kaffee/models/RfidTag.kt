package uk.dwelsh.kaffee.models

class RfidTag(val userId: String, val tag: String, val coffee: Coffee) {

    override fun toString(): String {
        return "RfidTag{" +
                "userId='" + userId + '\'' +
                ", tag='" + tag + '\'' +
                ", coffee=" + coffee +
                '}'
    }

}