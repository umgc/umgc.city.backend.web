package umgc.city.team1.SecurityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;
import java.sql.SQLException;

@EnableWebSecurity
@Configuration
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    final DataSource dataSource;

    /* Assign datasource which located in application-dev.yml */
    public SecurityConfiguration(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;    }

     /*Encode the raw password using PasswordEncoder */

    /*@Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    } */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    /*Get username, password, and authority from database tables */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email_address,password,enabled "
                        + "from city_user "
                        + "where email_address = ?")
                .authoritiesByUsernameQuery("select email_address,authority "
                        + "from authorities "
                        + "where email_address = ?");
    }


    /*Authenticated users who access /admin/ and /admin/* paths */
    @Override
    protected  void configure(HttpSecurity http) throws Exception {
        /* Permit access to all pages                      */
        /* Authenticate access to admin page and sub pages */
        http.authorizeRequests()
                .antMatchers("/admin/**").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();  // Create a login form

        /* Create logout page */
        http.logout();

        /* Disable Cross Site Request Forgery */
        http.csrf().disable();
    }
}
