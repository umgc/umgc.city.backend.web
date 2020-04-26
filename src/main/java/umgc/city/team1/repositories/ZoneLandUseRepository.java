package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umgc.city.team1.models.ZoneLandUse;

import java.util.Optional;
import java.util.UUID;

/* Returns data from Zone Land Use table */
@Repository
public interface ZoneLandUseRepository extends JpaRepository<ZoneLandUse, UUID> {
    @Query("SELECT zlu from ZoneLandUse as zlu where zlu.description = :zoneLandUseDescription and zlu.city.id = :cityId")
    Optional<ZoneLandUse> findByZoneLandUseByDescription(@Param("zoneLandUseDescription") String zoneLandUseDescription, @Param("cityId") UUID cityId);
}
