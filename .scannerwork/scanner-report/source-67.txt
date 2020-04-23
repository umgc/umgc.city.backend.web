package umgc.city.team1.models;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Data
class AllowedLandUseTest {

    @Test
    public void testAllowedLandUseSetAndGetId() {
        AllowedLandUse allowedLandUse = new AllowedLandUse();
        UUID uuid = UUID.randomUUID();
        try {
            allowedLandUse.setId(uuid);
            assertEquals(allowedLandUse.getId(), uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testAllowedLandUseSetAndGetZoneId() {
        UUID zoneId = UUID.randomUUID();
        Zone zone = new Zone();
        zone.setId(zoneId);
        AllowedLandUse allowedLandUse = new AllowedLandUse();
        allowedLandUse.setZone(zone);
        try {
            assertEquals(allowedLandUse.getZone().getId(), zoneId);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

//    @Test
//    public void testAllowedLandUseSetAndGetZoneLaneUseId() {
//        UUID
//        AllowedLandUse allowedLandUse = new AllowedLandUse();
//        UUID uuid = UUID.randomUUID();
//        try {
//            allowedLandUse.setZoneLaneUseId(uuid);
//            assertEquals(allowedLandUse.getZoneLaneUseId(),uuid);
//        } catch (Exception e2) {
//            fail("Threw exception when not expected one");
//        }
//    }
}
