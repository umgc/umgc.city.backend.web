package umgc.city.team1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umgc.city.team1.models.AllowedLandUse;
import umgc.city.team1.models.Zone;
import umgc.city.team1.models.ZoneLandUse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/* Returns data from Allowed Land Use table */
@Repository
public interface AllowedLandUseRepository extends JpaRepository<AllowedLandUse, UUID> {
    Page<AllowedLandUse> findByZoneId(UUID zoneId, Pageable pageable);

    @Query("SELECT alu from AllowedLandUse as alu where alu.zone.id = :zoneId")
    List<AllowedLandUse> findAllByZone(@Param("zoneId") UUID zoneId);

    @Query("SELECT alu from AllowedLandUse as alu where alu.zone.id = :zoneId and alu.zoneLandUse.id = :zoneLandUseId")
    Optional<AllowedLandUse> findAllByZoneandZoneLandUse(@Param("zoneId") UUID zoneId, @Param("zoneLandUseId") UUID zoneLandUseId);

}




