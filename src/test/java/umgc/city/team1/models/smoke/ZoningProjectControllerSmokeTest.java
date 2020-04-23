package umgc.city.team1.models.smoke;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import umgc.city.team1.controllers.ZoningProjectController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ZoningProjectControllerSmokeTest {

    @Autowired
    private ZoningProjectController zoningProjectController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(zoningProjectController).isNotNull();
    }
}
