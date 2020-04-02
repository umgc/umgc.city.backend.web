package umgc.city.team1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "zone_land_use")
public class ZoneLandUse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "land_use_name")
    private String land_use_name;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "zoneLandUse", cascade = CascadeType.ALL)
    private Set<AllowedLandUse> allowedLandUse;

}
