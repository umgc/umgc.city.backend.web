package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import umgc.city.team1.models.DevelopmentStandards;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DevelopmentStandardsRepository extends JpaRepository<DevelopmentStandards, UUID> {
    @Query("SELECT dev from DevelopmentStandards as dev where dev.zone.id = :zoneId")
    Optional<DevelopmentStandards> findByZoneId(UUID zoneId);
}
