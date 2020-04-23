package umgc.city.team1.models.outgoing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class EmailInfoTest {

    @Test
    void setAndGetRecipientEmail() {
        EmailInfo emailInfo = new EmailInfo();
        String email = "email@email.email";
        try {
            emailInfo.setRecipientEmail(email);
            assertEquals(emailInfo.getRecipientEmail(), email);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetRecipientName() {
        EmailInfo emailInfo = new EmailInfo();
        String name = "ffname mmname llname";
        try {
            emailInfo.setRecipientName(name);
            assertEquals(emailInfo.getRecipientName(), name);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetSenderEmail() {
        EmailInfo emailInfo = new EmailInfo();
        String email = "email@email.email";
        try {
            emailInfo.setSenderEmail(email);
            assertEquals(emailInfo.getSenderEmail(), email);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetSenderName() {
        EmailInfo emailInfo = new EmailInfo();
        String name = "ffname mmname llname";
        try {
            emailInfo.setSenderName(name);
            assertEquals(emailInfo.getSenderName(), name);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetSubject() {
        EmailInfo emailInfo = new EmailInfo();
        String subject = "subject subject subject";
        try {
            emailInfo.setSubject(subject);
            assertEquals(emailInfo.getSubject(), subject);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetBody() {
        EmailInfo emailInfo = new EmailInfo();
        String body = "body";
        for (int i = 0; i < 20; i++) {
            body += body;
        }
        try {
            emailInfo.setBody(body);
            assertEquals(emailInfo.getBody(), body);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetContentType() {
        EmailInfo emailInfo = new EmailInfo();
        String contentType = "contentType";
        try {
            emailInfo.setContentType(contentType);
            assertEquals(emailInfo.getContentType(), contentType);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

}