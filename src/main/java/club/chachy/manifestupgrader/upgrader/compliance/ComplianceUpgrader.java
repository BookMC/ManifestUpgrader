package club.chachy.manifestupgrader.upgrader.compliance;

import club.chachy.manifestupgrader.upgrader.Upgrader;
import com.google.gson.JsonObject;

public class ComplianceUpgrader implements Upgrader {
    private static final int COMPLIANCE_LEVEL = 1;

    @Override
    public void upgrade(JsonObject object) {
        object.addProperty("complianceLevel", COMPLIANCE_LEVEL);
    }
}
