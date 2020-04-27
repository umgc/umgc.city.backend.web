package umgc.city.team1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umgc.city.team1.controllers.ZoningProjectController;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;


@Data
@Entity
@NoArgsConstructor
@Table(name = "city_user")
public class CityUser implements Serializable {
    final static Logger logger = LoggerFactory.getLogger(ZoningProjectController.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Email
    @Column(name = "email_address")
    private String emailAddress;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "authorities_id")
    private UUID authoritiesId;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "cityUser")
    @JsonIgnore
    private City city;


    public CityUser(String firstName, String lastName, String emailAddress, String password, UUID authoritiesId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.authoritiesId = authoritiesId;
    }
}


