package umgc.city.team1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import umgc.city.team1.models.CityUser;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Data
class CityUserTest {
//    @Test
//    public void testCityUserSetEnabledTrue() {
//        CityUser cityUser = new CityUser();
//        try {
//            cityUser.setEnabled(true);
//            assertEquals(cityUser.getEnabled(),true);
//        } catch (Exception e2) {
//            fail("Threw exception when not expected one");
//        }
//    }
//
//    @Test
//    public void testCityUserSetEnabledFalse() {
//        CityUser cityUser = new CityUser();
//        try {
//            cityUser.setEnabled(false);
//            assertEquals(cityUser.getEnabled(),false);
//        } catch (Exception e2) {
//            fail("Threw exception when not expected one");
//        }
//    }
//
//
//    @Test
//    public void testCityUserSetAndGetId() {
//        CityUser cityUser = new CityUser();
//        UUID uuid = UUID.randomUUID();
//        try {
//            cityUser.setId(uuid);
//            assertEquals(cityUser.getId(),uuid);
//        } catch (Exception e2) {
//            fail("Threw exception when not expected one");
//        }
//    }
//
//    @Test
//    public void testCityUserSetEmailAddress() {
//        CityUser cityUser = new CityUser();
//        try {
//            cityUser.setEmailAddress("email@email.email");
//            assertEquals(cityUser.getEmailAddress(),"email@email.email");
//        } catch (Exception e2) {
//            fail("Threw exception when not expected one");
//        }
//    }

//    @Test
//    public void testCityUserSetAndGetPassword() {
//        CityUser cityUser = new CityUser();
//        String password = "password";
//        try {
//            cityUser.setPassword(password);
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            encoder.matches(password, cityUser.getPassword());
//        } catch (Exception e2) {
//            fail("Threw exception when not expected one");
//
//        }
//    }
//    @Test
//    public void testCityUserSetAndGetFirstName() {
//        CityUser cityUser = new CityUser();
//        try {
//            cityUser.setFirstName("ffname");
//            assertEquals(cityUser.getFirstName(),"ffname");
//        } catch (Exception e2) {
//            fail("Threw exception when not expected one");
//        }
//    }
//
//    @Test
//    public void testCityUserSetAndGetLastName() {
//        CityUser cityUser = new CityUser();
//        try {
//            cityUser.setLastName("llname");
//            assertEquals(cityUser.getLastName(),"llname");
//        } catch (Exception e2) {
//            fail("Threw exception when not expected one");
//        }
//    }
}
