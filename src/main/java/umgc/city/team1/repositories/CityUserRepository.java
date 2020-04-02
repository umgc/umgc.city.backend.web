package umgc.city.team1.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import umgc.city.team1.models.City;
import umgc.city.team1.models.CityUser;

import java.util.UUID;

@Repository
public interface CityUserRepository extends JpaRepository<CityUser, UUID> {
}


