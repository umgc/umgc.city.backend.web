package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umgc.city.team1.models.CityUser;
import umgc.city.team1.models.ZoneLandUse;

import java.util.UUID;

@Repository
public interface ZoneLandUseRepository extends JpaRepository<ZoneLandUse, UUID> {
}
