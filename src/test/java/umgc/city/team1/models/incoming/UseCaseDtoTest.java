package umgc.city.team1.models.incoming;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class UseCaseDtoTest {

    @Test
    void setAndGetCityId() {
        UseCaseDto useCaseDto = new UseCaseDto();
        UUID uuid = UUID.randomUUID();
        try {
            useCaseDto.setCityId(uuid);
            assertEquals(useCaseDto.getCityId(), uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetZoneId() {
        UseCaseDto useCaseDto = new UseCaseDto();
        UUID uuid = UUID.randomUUID();
        try {
            useCaseDto.setZoneId(uuid);
            assertEquals(useCaseDto.getZoneId(), uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetZoneSymbol() {
        UseCaseDto useCaseDto = new UseCaseDto();
        String symbol = "symbol";
        try {
            useCaseDto.setZoneSymbol(symbol);
            assertEquals(useCaseDto.getZoneSymbol(), symbol);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetZoneDescription() {
        UseCaseDto useCaseDto = new UseCaseDto();
        String zoneDescription = "ZoneDescription";
        try {
            useCaseDto.setZoneDescription(zoneDescription);
            assertEquals(useCaseDto.getZoneDescription(), zoneDescription);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetApplicationURL() {
        UseCaseDto useCaseDto = new UseCaseDto();
        String URL = "https://domain.anywhere/url";
        try {
            useCaseDto.setApplicationURL(URL);
            assertEquals(useCaseDto.getApplicationURL(), URL);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetProcedureURL() {
        UseCaseDto useCaseDto = new UseCaseDto();
        String URL = "https://domain.anywhere/url";
        try {
            useCaseDto.setProcedureURL(URL);
            assertEquals(useCaseDto.getProcedureURL(), URL);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetPermitName() {
        UseCaseDto useCaseDto = new UseCaseDto();
        String permitName = "permitName";
        try {
            useCaseDto.setPermitName(permitName);
            assertEquals(useCaseDto.getPermitName(), permitName);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetPermitDescription() {
        UseCaseDto useCaseDto = new UseCaseDto();
        String permitDescription = "permitDescription";
        try {
            useCaseDto.setPermitDescription(permitDescription);
            assertEquals(useCaseDto.getPermitDescription(), permitDescription);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetZoneLandUseId() {
        UseCaseDto useCaseDto = new UseCaseDto();
        UUID uuid = UUID.randomUUID();
        try {
            useCaseDto.setZoneLandUseId(uuid);
            assertEquals(useCaseDto.getZoneLandUseId(), uuid);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetZoneLandUseDescription() {
        UseCaseDto useCaseDto = new UseCaseDto();
        String ZoneLandUseDescription = "ZoneLandUseDescription";
        try {
            useCaseDto.setZoneLandUseDescription(ZoneLandUseDescription);
            assertEquals(useCaseDto.getZoneLandUseDescription(), ZoneLandUseDescription);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetGeneralStandardURL() {
        UseCaseDto useCaseDto = new UseCaseDto();
        String URL = "https://domain.anywhere/url";
        try {
            useCaseDto.setGeneralStandardURL(URL);
            assertEquals(useCaseDto.getGeneralStandardURL(), URL);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetGardenStandardURL() {
        UseCaseDto useCaseDto = new UseCaseDto();
        String URL = "https://domain.anywhere/url";
        try {
            useCaseDto.setGardenStandardURL(URL);
            assertEquals(useCaseDto.getGardenStandardURL(), URL);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetAdditionalStandardURL() {
        UseCaseDto useCaseDto = new UseCaseDto();
        String URL = "https://domain.anywhere/url";
        try {
            useCaseDto.setAdditionalStandardURL(URL);
            assertEquals(useCaseDto.getAdditionalStandardURL(), URL);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

    @Test
    void setAndGetFrontageAndFacadesStandardURL() {
        UseCaseDto useCaseDto = new UseCaseDto();
        String URL = "https://domain.anywhere/url";
        try {
            useCaseDto.setFrontageAndFacadesStandardURL(URL);
            assertEquals(useCaseDto.getFrontageAndFacadesStandardURL(), URL);
        } catch (Exception e2) {
            fail("Threw exception when not expected one");
        }
    }

}