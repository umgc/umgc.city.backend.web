package umgc.city.team1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;


@Data
@Entity
@NoArgsConstructor
@Table(name = "city_user")
public class CityUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @NotEmpty
    @Length(max = 30)
    @Column(name = "password")
    private String password;

    @Email
    @Length(max = 100)
    @Column(name = "email_address")
    private String emailAddress;

    @Length(max = 50)
    @Column(name="first_name")
    private String firstName;

    @Length(max = 50)
    @Column(name="last_name")
    private String lastName;

<<<<<<< Updated upstream
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "cityUser")
    private City city;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "cityUser")
    private Authorities authorities;

    public CityUser(String firstName, String lastName, String emailAddress, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }


=======
    private Boolean enabled;
>>>>>>> Stashed changes

}
