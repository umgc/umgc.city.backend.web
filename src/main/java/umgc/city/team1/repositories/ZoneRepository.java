package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umgc.city.team1.models.Zone;
import umgc.city.team1.models.incoming.UseCaseDto;

import java.util.List;
import java.util.UUID;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, UUID> {

    @Query("SELECT z from Zone as z where z.city.id = :cityId")
    List<Zone> findAllByCity(@Param("cityId") UUID cityId);

    @Query("SELECT new umgc.city.team1.models.incoming.UseCaseDto(z.id, z.zoneSymbol, z.description, " +
            "alu.applicationUrl, alu.procedureUrl, alu.permitName, alu.permitDescription, " +
            "alu.zoneLandUse.id, zlu.description, ds.gardenStandardsURL, ds.generalStandardsURL, " +
            "ds.additionalStandardsURL, ds.frontageAndFacadesStandardsURL) FROM Zone as z" +
            " INNER JOIN AllowedLandUse as alu ON alu.zone.id = z.id" +
            " INNER JOIN ZoneLandUse as zlu ON alu.zoneLandUse.id = zlu.id" +
            " INNER JOIN DevelopmentStandards as ds on ds.zone.id = z.id" +
            " WHERE z.id = :zoneId")
    List<UseCaseDto> findUseCaseByZoneId(@Param("zoneId") UUID zoneId);


}
