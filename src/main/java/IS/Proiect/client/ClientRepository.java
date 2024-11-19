package IS.Proiect.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("SELECT c FROM Client c where c.firstName = ?1")
    Optional<Client> findClientByFirstName(String firstName);
}
