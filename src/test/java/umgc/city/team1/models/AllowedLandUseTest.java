package umgc.city.team1.models;

import org.junit.jupiter.api.Test;
import umgc.city.team1.models.AllowedLandUse;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AllowedLandUseTest {

    @Test
    public void testAllowedLandUseSetAndGetId() {
        AllowedLandUse allowedLandUse = new AllowedLandUse();
        UUID uuid = UUID.randomUUID();
        try {
            allowedLandUse.setId(uuid);
            assertEquals(allowedLandUse.getId(),uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testAllowedLandUseSetAndGetZoneId() {
        AllowedLandUse allowedLandUse = new AllowedLandUse();
        UUID uuid = UUID.randomUUID();
        try {
            allowedLandUse.setZoneId(uuid);
            assertEquals(allowedLandUse.getZoneId(),uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testAllowedLandUseSetAndGetZoneLaneUseId() {
        AllowedLandUse allowedLandUse = new AllowedLandUse();
        UUID uuid = UUID.randomUUID();
        try {
            allowedLandUse.setZoneLaneUseId(uuid);
            assertEquals(allowedLandUse.getZoneLaneUseId(),uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }
}