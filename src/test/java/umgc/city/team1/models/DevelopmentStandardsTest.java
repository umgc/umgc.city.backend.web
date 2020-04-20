package umgc.city.team1.models;


import lombok.Data;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Data
class DevelopmentStandardsTest {
    @Test
    public void testDevelopmentStandardsSetAndGetId() {
        DevelopmentStandards developmentStandards = new DevelopmentStandards();
        UUID uuid = UUID.randomUUID();
        try {
            developmentStandards.setId(uuid);
            assertEquals(developmentStandards.getId(), uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testDevelopmentStandardsSetAndGetZone() {
        DevelopmentStandards developmentStandards = new DevelopmentStandards();
        Zone zone = new Zone();
        try {
            developmentStandards.setZone(zone);
            assertEquals(developmentStandards.getZone(), zone);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testDevelopmentStandardsSetAndGetZZZZ() {
        DevelopmentStandards developmentStandards = new DevelopmentStandards();
        String URL = "https://domain.anywhere/url";
        try {
            developmentStandards.setGeneralStandardsURL(URL);
            assertEquals(developmentStandards.getGeneralStandardsURL(), URL);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testDevelopmentStandardsAdditionalStandardsURLSetAndGet() {
        DevelopmentStandards developmentStandards = new DevelopmentStandards();
        String URL = "https://domain.anywhere/url";
        try {
            developmentStandards.setAdditionalStandardsURL(URL);
            assertEquals(developmentStandards.getAdditionalStandardsURL(), URL);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testDevelopmentStandardsGardenStandardsURLSetAndGet() {
        DevelopmentStandards developmentStandards = new DevelopmentStandards();
        String URL = "https://domain.anywhere/url";
        try {
            developmentStandards.setGardenStandardsURL(URL);
            assertEquals(developmentStandards.getGardenStandardsURL(), URL);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testDevelopmentStandardsFrontageAndFacadesStandardsURLSetAndGet() {
        DevelopmentStandards developmentStandards = new DevelopmentStandards();
        String URL = "https://domain.anywhere/url";
        try {
            developmentStandards.setFrontageAndFacadesStandardsURL(URL);
            assertEquals(developmentStandards.getFrontageAndFacadesStandardsURL(), URL);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }
}
