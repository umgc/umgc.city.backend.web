package umgc.city.team1.models;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umgc.city.team1.controllers.ZoningProjectController;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Data
class CityUserTest {

    private String unexpectedExStr = "Threw exception when not expected one";
    final static Logger logger = LoggerFactory.getLogger(ZoningProjectController.class);

    @Test
    public void testCityUserSetAndGetId() {
        CityUser cityUser = new CityUser();
        UUID uuid = UUID.randomUUID();
        try {
            cityUser.setId(uuid);
            assertEquals(cityUser.getId(), uuid);
        } catch (Exception e2) {
            fail(unexpectedExStr);
            logger.error(unexpectedExStr);
        }
    }

    @Test
    public void testCityUserSetAndGetFirstName() {
        CityUser cityUser = new CityUser();
        try {
            cityUser.setFirstName("ffname");
            assertEquals(cityUser.getFirstName(), "ffname");
        } catch (Exception e2) {
            fail(unexpectedExStr);
            logger.error(unexpectedExStr);
        }
    }

    @Test
    public void testCityUserSetAndGetLastName() {
        CityUser cityUser = new CityUser();
        try {
            cityUser.setLastName("llname");
            assertEquals(cityUser.getLastName(), "llname");
        } catch (Exception e2) {
            fail(unexpectedExStr);
            logger.error(unexpectedExStr);
        }
    }

    @Test
    public void testCityUserSetAndGetEmailAddress() {
        CityUser cityUser = new CityUser();
        try {
            cityUser.setEmailAddress("email@email.email");
            assertEquals(cityUser.getEmailAddress(), "email@email.email");
        } catch (Exception e2) {
            fail(unexpectedExStr);
        }
    }

    @Test
    public void testCityUserSetCity() {
        CityUser cityUser = new CityUser();
        City city = new City();
        try {
            cityUser.setCity(city);
            assertEquals(cityUser.getCity(), city);
        } catch (Exception e2) {
            fail(unexpectedExStr);
        }
    }

    @Test
    public void testCityUserSetAndGetPassword() {
        CityUser cityUser = new CityUser();
        String password = "password";
        try {
            cityUser.setPassword(password);
            assertEquals(cityUser.getPassword(), password);
        } catch (Exception e2) {
            fail(unexpectedExStr);
            logger.error(unexpectedExStr);

        }
    }

    @Test
    public void testCityUserSetAndGetAuthoritiesId() {
        CityUser cityUser = new CityUser();
        UUID uuid = UUID.randomUUID();
        try {
            cityUser.setAuthoritiesId(uuid);
            assertEquals(cityUser.getAuthoritiesId(), uuid);
        } catch (Exception e2) {
            fail(unexpectedExStr);
            logger.error(unexpectedExStr);
        }
    }
}
