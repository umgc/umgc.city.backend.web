package umgc.city.team1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import umgc.city.team1.config.MailProperties;

@SpringBootApplication
@EnableJpaRepositories
@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class Team1Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(Team1Application.class);
    }

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(
                Team1Application.class);
        sa.run(args);
    }

    @RestController
    public static class DefaultResponseController {
        @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity handler() {
            return ResponseEntity.ok("Hello World! We Are Here");
        }

        @GetMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity frontEndTest() {
            return ResponseEntity.ok("Hello World! We Are Here");
        }
    }
}
