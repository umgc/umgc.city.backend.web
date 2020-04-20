package umgc.city.team1.models;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Data
class ZoneLandUseTest {
    @Test
    public void testZoneLandUseSetAndGetId() {
        ZoneLandUse zoneLandUse = new ZoneLandUse();
        UUID uuid = UUID.randomUUID();
        try {
            zoneLandUse.setId(uuid);
            assertEquals(zoneLandUse.getId(), uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testZoneLandUseSetAndGetDescription() {
        ZoneLandUse zoneLandUse = new ZoneLandUse();
        String description = "Description";
        try {
            zoneLandUse.setDescription(description);
            assertEquals(zoneLandUse.getDescription(), description);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testZoneLandUseSetAndGetCity() {
        ZoneLandUse zoneLandUse = new ZoneLandUse();
        City city = new City();
        String cityName = "cityName";
        city.setName(cityName);
        try {
            zoneLandUse.setCity(city);
            assertEquals(zoneLandUse.getCity(), city);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

}
