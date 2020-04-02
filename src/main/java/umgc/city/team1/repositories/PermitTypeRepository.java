package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umgc.city.team1.models.PermitType;

import java.util.UUID;

@Repository
public interface PermitTypeRepository extends JpaRepository<PermitType, UUID> {
}
