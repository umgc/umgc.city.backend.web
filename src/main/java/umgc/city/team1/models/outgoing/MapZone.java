package umgc.city.team1.models.outgoing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapZone {
    private String zoneSymbol;
    private String description;
    private String generalStandardsURL;
    private String additionalStandardsURL;
    private String gardenStandardsURL;
    private String frontageAndFacadesStandardsURL;
}
