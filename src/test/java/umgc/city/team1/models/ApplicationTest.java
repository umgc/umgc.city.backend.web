package umgc.city.team1.models;

import org.junit.jupiter.api.Test;
import umgc.city.team1.models.Application;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ApplicationTest {

    @Test
    public void testApplicationSetAndGetId() {
        Application application = new Application();
        UUID uuid = UUID.randomUUID();
        try {
            application.setId(uuid);
            assertEquals(application.getId(), uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }


    @Test
    public void testPermitIdSetAndGetId() {
        Application application = new Application();
        UUID uuid = UUID.randomUUID();
        try {
            application.setPermitId(uuid);
            assertEquals(application.getPermitId(), uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testPermitIdSetAndGetName() {
        Application application = new Application();
        String applicationName = "application Name";
        try {
            application.setName(applicationName);
            assertEquals(application.getName(), applicationName);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testPermitIdSetAndGetApplicationURL() {
        Application application = new Application();
        String applicationURL = "https://domain.anywhere/url";
        try {
            application.setApplicationURL(applicationURL);
            assertEquals(application.getApplicationURL(), applicationURL);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }
}
