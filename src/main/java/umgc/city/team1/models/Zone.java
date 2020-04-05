package umgc.city.team1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @NotNull
    @Length(max = 20)
    @Column(name = "zone_symbol")
    private String zoneSymbol;

    @NotNull
    @Column(name= "description")
    private String description;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private Set<AllowedLandUse> allowedLandUse;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "zone")
    private DevelopmentStandards developmentStandards;

}
