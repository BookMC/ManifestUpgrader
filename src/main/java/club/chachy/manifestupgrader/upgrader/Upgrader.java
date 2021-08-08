package club.chachy.manifestupgrader.upgrader;

import com.google.gson.JsonObject;

public interface Upgrader {
    void upgrade(JsonObject object);
}
