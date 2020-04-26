package umgc.city.team1.models;


import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umgc.city.team1.controllers.ZoningProjectController;

import java.util.UUID;

import static java.util.UUID.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Data
class CityTest {

    private CityUser cityUser;
    private String PasadenaStr = "Pasadena";
    public final static String unexpectedExStr = "Threw exception when not expected one";
    final static Logger logger = LoggerFactory.getLogger(ZoningProjectController.class);

    @Before
    public void createCityUser() {
        cityUser = new CityUser("John", "Doe", "umucCityTest@gamil.com", "test_password", fromString("8f64a3b3-39f4" +
                "-4ed0-8002-7e4273d45f34"));
        cityUser.setId(UUID.randomUUID());
    }

    @Test
    public void testCitySetAndGetId() {
        City city = new City(PasadenaStr, "FL", cityUser);
        UUID uuid = UUID.randomUUID();
        try {
            city.setId(uuid);
            assertEquals(city.getId(), uuid);
        } catch (Exception e2) {
            fail(unexpectedExStr);
            logger.error(unexpectedExStr);
        }
    }

    @Test
    public void testCitySetAndGetName() {
        City city = new City(PasadenaStr, "FL", cityUser);
        String cityName = "City of Pasadena";
        try {
            city.setName(cityName);
            assertEquals(city.getName(), cityName);
        } catch (Exception e2) {
            fail(unexpectedExStr);
            logger.error(unexpectedExStr);
        }
    }

    @Test
    public void testCitySetAndGetState() {
        City city = new City(PasadenaStr, "FL", cityUser);
        String stateName = "MD";
        try {
            city.setState(stateName);
            assertEquals(city.getState(), stateName);
        } catch (Exception e2) {
            fail(unexpectedExStr);
            logger.error(unexpectedExStr);
        }
    }
}
