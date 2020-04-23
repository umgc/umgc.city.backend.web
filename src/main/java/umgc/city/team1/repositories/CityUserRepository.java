package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umgc.city.team1.models.CityUser;

import java.util.List;
import java.util.UUID;

@Repository
public interface CityUserRepository extends JpaRepository<CityUser, UUID> {

    @Query("SELECT u from CityUser as u where u.emailAddress = :emailAddress")
    CityUser getUserByEmail(@Param("emailAddress") String emailAddress);

    @Query("SELECT u from CityUser as u where u.firstName = :firstName and u.lastName = :lastName")
    CityUser getUserByName(@Param ("firstName") String firstName, @Param("lastName") String lastName);
}


