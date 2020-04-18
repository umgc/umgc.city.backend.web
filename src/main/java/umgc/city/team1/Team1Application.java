package umgc.city.team1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import umgc.city.team1.config.MailProperties;

@SpringBootApplication
@EnableJpaRepositories
@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class Team1Application {

    public static void main(String[] args) {
        SpringApplication.run(Team1Application.class, args);
    }
}
