package umgc.city.team1.smoke;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import umgc.city.team1.contollers.ZoningProjectController;

@SpringBootTest
public class ZoningProjectControllerSmokeTest {

    @Autowired
    private ZoningProjectController zoningProjectController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(zoningProjectController).isNotNull();
    }
}