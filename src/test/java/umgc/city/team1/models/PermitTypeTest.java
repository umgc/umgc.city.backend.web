package umgc.city.team1.models;

import org.junit.jupiter.api.Test;
import umgc.city.team1.models.PermitType;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PermitTypeTest {


    @Test
    public void testPermitTypeSetAndGetId() {
        PermitType permitType = new PermitType();
        UUID uuid = UUID.randomUUID();
        try {
            permitType.setId(uuid);
            assertEquals(permitType.getId(),uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testAllowedLandUseIdSetAndGetId() {
        PermitType permitType = new PermitType();
        UUID uuid = UUID.randomUUID();
        try {
            permitType.setAllowedLandUseId(uuid);
            assertEquals(permitType.getAllowedLandUseId(),uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testDescriptionSetAndGetId() {
        PermitType permitType = new PermitType();
        String description = "description";
        try {
            permitType.setDescription(description);
            assertEquals(permitType.getDescription(),description);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }
    @Test
    public void testNameSetAndGetId() {
        PermitType permitType = new PermitType();
        String name = "description";
        try {
            permitType.setName(name);
            assertEquals(permitType.getName(),name);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    public void testGeneralGtandardsURLSetAndGet() {
        PermitType permitType = new PermitType();
        String URL = "https://domain.anywhere/url";
        try {
            permitType.setProcedureURL(URL);
            assertEquals(permitType.getProcedureURL(),URL);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

}