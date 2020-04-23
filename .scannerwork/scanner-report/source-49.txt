package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import umgc.city.team1.models.Authorities;

import java.util.UUID;

public interface AuthoritiesRepository extends JpaRepository<Authorities, UUID> {
}
