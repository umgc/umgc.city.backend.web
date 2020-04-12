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
@Table(name = "zone")
public class Zone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "zone_symbol")
    private String zoneSymbol;

    @Column(name= "description")
    private String description;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private Set<AllowedLandUse> allowedLandUse;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "zone")
    private DevelopmentStandards developmentStandards;

}
