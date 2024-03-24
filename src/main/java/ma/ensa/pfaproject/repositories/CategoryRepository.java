package ma.ensa.pfaproject.repositories;

import ma.ensa.pfaproject.entities.CategorieProduit;
import ma.ensa.pfaproject.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public interface CategoryRepository extends JpaRepository<CategorieProduit,Long> {

    @Query("SELECT c from CategorieProduit c where c.nomCategorie LIKE %:key%")
    List<CategorieProduit> getAllCategorieProduitsByNomContainingKey(String key);
}
