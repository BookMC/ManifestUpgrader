package club.chachy.manifestupgrader.upgrader.version;

import club.chachy.manifestupgrader.upgrader.Upgrader;
import com.google.gson.JsonObject;

public class MinimumLaunchUpgrader implements Upgrader {
    private static final int MINIMUM_LAUNCHER_VERSION = 21;

    @Override
    public void upgrade(JsonObject object) {
        object.addProperty("minimumLauncherVersion", MINIMUM_LAUNCHER_VERSION);
    }
}
