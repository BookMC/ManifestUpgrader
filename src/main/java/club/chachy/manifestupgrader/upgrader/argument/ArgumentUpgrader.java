package club.chachy.manifestupgrader.upgrader.argument;

import club.chachy.manifestupgrader.upgrader.Upgrader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ArgumentUpgrader implements Upgrader {
    @Override
    public void upgrade(JsonObject object) {
        if (object.has("minecraftArguments")) {

            JsonObject arguments = new JsonObject();
            JsonArray game = new JsonArray();
            JsonArray gameRules = getLocalRules("game");

            String minecraftArguments = object.get("minecraftArguments").getAsString();
            object.remove("minecraftArguments"); // Remove once we've got our data we want

            for (String arg : minecraftArguments.split(" ")) {
                game.add(arg);
            }

            game.addAll(gameRules);
            arguments.add("game", game);
            arguments.add("jvm", getLocalRules("jvm"));
            object.add("arguments", arguments);
        }
    }

    private JsonArray getLocalRules(String type) {
        try (InputStream stream = this.getClass().getResourceAsStream("/upgrader/latest.base.json")) {
            if (stream == null) {
                throw new IllegalStateException("Could not locate /upgrader/latest.base.json, unable to rebuild arguments.");
            }
            try (InputStreamReader reader = new InputStreamReader(stream)) {
                return JsonParser.parseReader(reader)
                    .getAsJsonObject()
                    .getAsJsonObject("arguments")
                    .getAsJsonArray(type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JsonArray();
    }
}
