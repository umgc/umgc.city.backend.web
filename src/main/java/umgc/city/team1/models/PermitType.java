package umgc.city.team1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permit_type")
public class PermitType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @NotNull
    @Length(max = 100)
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "procedure_url")
    private String procedureURL;

    @ManyToOne
    @JoinColumn(name= "allowed_land_use_id")
    private AllowedLandUse allowedLandUse;

    @OneToMany(mappedBy = "permitType", cascade = CascadeType.ALL)
    private Set<Application> application;
}

