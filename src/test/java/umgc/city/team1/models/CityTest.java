package umgc.city.team1.models;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    @Test
    public void testCitySetAndGetId() {
        City city = new City();
        UUID uuid = UUID.randomUUID();
        try {
            city.setId(uuid);
            assertEquals(city.getId(),uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testCitySetAndGetName() {
        City city = new City();
        String cityName = "City of ...";
        try {
            city.setName(cityName);
            assertEquals(city.getName(),cityName);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testCitySetAndGetState() {
        City city = new City();
        String stateName = "State of ...";
        try {
            city.setState(stateName);
            assertEquals(city.getState(),stateName);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }
}