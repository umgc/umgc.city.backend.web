package umgc.city.team1.models;


import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;

import static java.util.UUID.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Data
class CityTest {

    private CityUser cityUser;

    @Before
    public void createCityUser(){
        cityUser = new CityUser("John", "Doe", "umucCityTest@gamil.com", "test_password", fromString("8f64a3b3-39f4-4ed0-8002-7e4273d45f34") );
        cityUser.setId(UUID.randomUUID());
    }

    @Test
    public void testCitySetAndGetId() {
        City city = new City("Pasadena", "FL", cityUser);
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
        City city = new City("Pasadena", "FL", cityUser);
        String cityName = "City of Pasadena";
        try {
            city.setName(cityName);
            assertEquals(city.getName(),cityName);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testCitySetAndGetState() {
        City city = new City("Pasadena", "FL", cityUser);
        String stateName = "MD";
        try {
            city.setState(stateName);
            assertEquals(city.getState(),stateName);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }
}
