package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umgc.city.team1.models.Zone;
import umgc.city.team1.models.incoming.UseCaseDto;
import umgc.city.team1.models.outgoing.MapZone;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/* Returns data from Zone table */
@Repository
public interface ZoneRepository extends JpaRepository<Zone, UUID> {
    @Query("SELECT z from Zone as z where z.id = :zoneId")
    Zone getZoneById(@Param("zoneId") UUID zoneId);

    @Query("SELECT z from Zone as z where z.city.id = :cityId")
    List<Zone> findAllByCity(@Param("cityId") UUID cityId);

    @Query("SELECT z from Zone as z where z.zoneSymbol = :zoneSymbol and z.city.id = :cityId")
    Zone getZoneSymbolAndCityId(@Param("zoneSymbol") String zoneSymbol, @Param("cityId") UUID cityId);

    @Query("SELECT new umgc.city.team1.models.incoming.UseCaseDto(c.id, z.id, z.zoneSymbol, z.description, " +
            "alu.applicationUrl, alu.procedureUrl, alu.permitName, alu.permitDescription, " +
            "alu.zoneLandUse.id, zlu.description, ds.gardenStandardsURL, ds.generalStandardsURL, " +
            "ds.additionalStandardsURL, ds.frontageAndFacadesStandardsURL) FROM Zone as z" +
            " INNER JOIN City as c ON c.id = z.city.id" +
            " INNER JOIN AllowedLandUse as alu ON alu.zone.id = z.id" +
            " INNER JOIN ZoneLandUse as zlu ON alu.zoneLandUse.id = zlu.id" +
            " INNER JOIN DevelopmentStandards as ds on ds.zone.id = z.id" +
            " WHERE z.id = :zoneId")
    List<UseCaseDto> findUseCaseByZoneId(@Param("zoneId") UUID zoneId);

    @Query("SELECT new umgc.city.team1.models.incoming.UseCaseDto(c.id, z.id, z.zoneSymbol, z.description, " +
            "alu.applicationUrl, alu.procedureUrl, alu.permitName, alu.permitDescription, " +
            "alu.zoneLandUse.id, zlu.description, ds.gardenStandardsURL, ds.generalStandardsURL, " +
            "ds.additionalStandardsURL, ds.frontageAndFacadesStandardsURL) FROM Zone as z" +
            " INNER JOIN City as c ON c.id = z.city.id" +
            " INNER JOIN AllowedLandUse as alu ON alu.zone.id = z.id" +
            " INNER JOIN ZoneLandUse as zlu ON alu.zoneLandUse.id = zlu.id" +
            " INNER JOIN DevelopmentStandards as ds on ds.zone.id = z.id" +
            " WHERE z.city.id = :cityId")
    List<UseCaseDto> findUseCaseByCityId(@Param("cityId") UUID cityId);

    @Query("SELECT new umgc.city.team1.models.outgoing.MapZone(z.zoneSymbol, z.description, " +
            "ds.generalStandardsURL, ds.additionalStandardsURL, ds.gardenStandardsURL, ds.frontageAndFacadesStandardsURL" +
            ") FROM Zone as z" +
            " INNER JOIN DevelopmentStandards as ds ON ds.zone.id = z.id" +
            " WHERE z.zoneSymbol = :zoneCode")
    MapZone findUseCaseByZoneSymbol(@Param("zoneCode") String zoneCode);


}
