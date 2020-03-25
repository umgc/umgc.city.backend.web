package umgc.city.team1.models;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="state")
    private String state;

    @OneToOne(cascade = CascadeType.ALL)
    private CityUser cityUser;

    public City(String name, String state, CityUser cityUser){
        this.name = name;
        this.state = state;
        this.cityUser = cityUser;

    }
}
