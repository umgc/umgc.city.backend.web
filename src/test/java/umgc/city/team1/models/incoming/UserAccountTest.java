package umgc.city.team1.models.incoming;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class UserAccountTest {

    @Test
    void setAndGetFirstName() {
        UserAccount userAccount = new UserAccount();
        String name = "ffname";
        try {
            userAccount.setFirstName(name);
            assertEquals(userAccount.getFirstName(), name);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetLastName() {
        UserAccount userAccount = new UserAccount();
        String name = "llname";
        try {
            userAccount.setLastName(name);
            assertEquals(userAccount.getLastName(), name);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetEmail() {
        UserAccount userAccount = new UserAccount();
        String email = "email@email.email";
        try {
            userAccount.setEmail(email);
            assertEquals(userAccount.getEmail(), email);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetCity() {
        UserAccount userAccount = new UserAccount();
        String city = "ccity";
        try {
            userAccount.setCity(city);
            assertEquals(userAccount.getCity(), city);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetState() {
        UserAccount userAccount = new UserAccount();
        String state = "sstate";
        try {
            userAccount.setState(state);
            assertEquals(userAccount.getState(), state);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetPassword() {
        UserAccount userAccount = new UserAccount();
        String password = "password";
        try {
            userAccount.setPassword(password);
            assertEquals(userAccount.getPassword(), password);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetAuthoritiesId() {
        UserAccount userAccount = new UserAccount();
        UUID uuid = UUID.randomUUID();
        try {
            userAccount.setAuthoritiesId(uuid);
            assertEquals(userAccount.getAuthoritiesId(), uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

}