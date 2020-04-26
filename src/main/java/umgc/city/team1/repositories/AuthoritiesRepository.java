package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umgc.city.team1.models.Authorities;

import java.util.UUID;

/* Returns data from Authorities table */
public interface AuthoritiesRepository extends JpaRepository<Authorities, UUID> {
    @Query("SELECT a from Authorities as a where a.authority = :authority")
    Authorities getAuthorityByName(@Param("authority") String authority);
}
