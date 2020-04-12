package umgc.city.team1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import umgc.city.team1.models.Zone;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Data
class ZoneTest {
    @Test
    public void testZoneSetAndGetId() {
        Zone zone = new Zone();
        UUID uuid = UUID.randomUUID();
        try {
            zone.setId(uuid);
            assertEquals(zone.getId(),uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

//    @Test
//    public void testCityIdSetAndGetId() {
//        Zone zone = new Zone();
//        UUID uuid = UUID.randomUUID();
//        try {
//            zone.setCityId(uuid);
//            assertEquals(zone.getCityId(),uuid);
//        } catch (Exception e2) {
//            fail("Threw exception when not expected one");
//        }
//    }
//
//    @Test
//    public void testZoneSymbolSetAndGetId() {
//        Zone zone = new Zone();
//        UUID uuid = UUID.randomUUID();
//        try {
//            zone.setCityId(uuid);
//            assertEquals(zone.getCityId(),uuid);
//        } catch (Exception e2) {
//            fail("Threw exception when not expected one");
//        }
//    }
    @Test
    public void testDescriptionSetAndGetId() {
        Zone zone = new Zone();
        String description = "description";
        try {
            zone.setDescription(description);
            assertEquals(zone.getDescription(),description);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }
}
