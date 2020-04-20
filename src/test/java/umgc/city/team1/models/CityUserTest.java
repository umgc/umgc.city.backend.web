package umgc.city.team1.models;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Data
class CityUserTest {
    @Test
    public void testCityUserSetAndGetId() {
        CityUser cityUser = new CityUser();
        UUID uuid = UUID.randomUUID();
        try {
            cityUser.setId(uuid);
            assertEquals(cityUser.getId(), uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testCityUserSetAndGetFirstName() {
        CityUser cityUser = new CityUser();
        try {
            cityUser.setFirstName("ffname");
            assertEquals(cityUser.getFirstName(), "ffname");
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testCityUserSetAndGetLastName() {
        CityUser cityUser = new CityUser();
        try {
            cityUser.setLastName("llname");
            assertEquals(cityUser.getLastName(), "llname");
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testCityUserSetAndGetEmailAddress() {
        CityUser cityUser = new CityUser();
        try {
            cityUser.setEmailAddress("email@email.email");
            assertEquals(cityUser.getEmailAddress(), "email@email.email");
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
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
            fail("Threw exception when not expected one");
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
            fail("Threw exception when not expected one");

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
            fail("Threw exception when not expected one");
        }
    }
}
