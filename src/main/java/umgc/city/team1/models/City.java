package umgc.city.team1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "city")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="state")
    private String state;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_user_id", referencedColumnName = "id")
    @JsonIgnore
    private CityUser cityUser;

    /* Create a new city with user input */
    public City(String name, String state, CityUser cityUser){
        this.name = name;
        this.state = state;
        this.cityUser = cityUser;
    }

    public City(Optional<City> orElseThrow) {}
}
