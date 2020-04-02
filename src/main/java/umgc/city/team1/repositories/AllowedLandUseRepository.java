package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umgc.city.team1.models.AllowedLandUse;
import umgc.city.team1.models.ZoneLandUse;

import java.util.UUID;

@Repository
public interface AllowedLandUseRepository extends JpaRepository<AllowedLandUse, UUID> {
}
