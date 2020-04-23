package umgc.city.team1.models.outgoing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class EmailStatusTest {

    @Test
    void SetAndGetisSentTrue() {
        EmailStatus emailStatus = new EmailStatus();
        try {
            emailStatus.setSent(true);
            assertEquals(emailStatus.isSent(), true);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void SetAndGetisSentFalse() {
        EmailStatus emailStatus = new EmailStatus();
        try {
            emailStatus.setSent(false);
            assertEquals(emailStatus.isSent(), false);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetErrorMessage() {
        EmailStatus emailStatus = new EmailStatus();
        String errorMessage = "errorMessage errorMessage errorMessage";
        try {
            emailStatus.setErrorMessage(errorMessage);
            assertEquals(emailStatus.getErrorMessage(), errorMessage);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

}