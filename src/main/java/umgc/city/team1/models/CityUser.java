package umgc.city.team1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city_user")
public class CityUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "password")
    private String password;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

}
