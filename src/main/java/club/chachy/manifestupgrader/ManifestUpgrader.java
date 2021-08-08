package club.chachy.manifestupgrader;

import club.chachy.manifestupgrader.upgrader.Upgrader;
import club.chachy.manifestupgrader.upgrader.argument.ArgumentUpgrader;
import club.chachy.manifestupgrader.upgrader.compliance.ComplianceUpgrader;
import club.chachy.manifestupgrader.upgrader.version.MinimumLaunchUpgrader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class ManifestUpgrader {
    private static final List<Upgrader> upgraders = Arrays.asList(
        new ArgumentUpgrader(),
        new ComplianceUpgrader(),
        new MinimumLaunchUpgrader()
    );

    public static JsonObject upgrade(JsonObject origin) {
        JsonObject copied = origin.deepCopy();

        for (Upgrader upgrader : upgraders) {
            upgrader.upgrade(copied);
        }

        return copied;
    }
}
