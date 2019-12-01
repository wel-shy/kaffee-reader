package uk.dwelsh.kaffee.models;

public class RfidTag {
    private String userId;
    private String tag;
    private Coffee coffee;

    public RfidTag(String userId, String tag, Coffee coffee) {
        this.userId = userId;
        this.tag = tag;
        this.coffee = coffee;
    }

    public String getUserId() {
        return userId;
    }

    public String getTag() {
        return tag;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    @Override
    public String toString() {
        return "RfidTag{" +
                "userId='" + userId + '\'' +
                ", tag='" + tag + '\'' +
                ", coffee=" + coffee +
                '}';
    }
}
