package umgc.city.team1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Table;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "allowed_land_use")
public class AllowedLandUse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "permit_name")
    @NotNull
    private String permitName;

    @Column(name ="permit_description")
    @NotNull
    private String permitDescription;

    @Column(name = "procedure_url")
    @NotNull
    private String procedureUrl;

    @Column(name = "application_url")
    @NotNull
    private String applicationUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zone_land_use_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ZoneLandUse zoneLandUse;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zone_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Zone zone;

    public AllowedLandUse(String permitName, String permitDescription, String procedureUrl, String applicationUrl,
                          ZoneLandUse zoneLandUse, Zone zone){
        this.permitName = permitName;
        this.permitDescription = permitDescription;
        this.procedureUrl = procedureUrl;
        this.applicationUrl = applicationUrl;
        this.zoneLandUse = zoneLandUse;
        this.zone = zone;
    }


}
