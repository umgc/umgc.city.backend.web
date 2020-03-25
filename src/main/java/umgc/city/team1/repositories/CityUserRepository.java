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
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    public CityUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }
//
//    public void insertCityUser(String firstName, String lastName, String email, String password) {
//        String query = "INSERT INTO city_user (first_name, last_name, email_address, password)" +
//                 "VALUES (:firstName, :lastName, :email_address, :password )";
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
//                .addValue("firstName", firstName).addValue("lastName", lastName)
//                .addValue("email_address", email).addValue("password", password);
//        try {
//            namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
//        } catch (DataAccessException e) {
//            System.out.println(e.toString());
//        }
//    }

