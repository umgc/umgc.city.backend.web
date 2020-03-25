package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umgc.city.team1.models.City;

import java.util.UUID;

@Repository
    public interface CityRepository extends JpaRepository<City, UUID> {
    }
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    public CityRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }
//
//    public void insertCity(String city, String state) {
//        String query = "INSERT INTO city (name, state) VALUES (:name, :state)";
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
//                .addValue("name", city).addValue("state", state);
//        try {
//            namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
//        } catch (DataAccessException e) {
//            System.out.println(e.toString());
//        }
//    }

