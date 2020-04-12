package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Authorities extends JpaRepository<Application, UUID> {
}
