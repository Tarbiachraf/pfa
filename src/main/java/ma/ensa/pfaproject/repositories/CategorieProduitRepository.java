package ma.ensa.pfaproject.repositories;

import ma.ensa.pfaproject.entities.CategorieProduit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieProduitRepository extends JpaRepository<CategorieProduit,Long> {
}
