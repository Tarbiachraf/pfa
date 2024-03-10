package ma.ensa.pfaproject.repositories;

import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("SELECT c from Client c where c.nomClient LIKE %:key%")
    List<Client> getAllClientsByNomContainingKey(String key);

}
