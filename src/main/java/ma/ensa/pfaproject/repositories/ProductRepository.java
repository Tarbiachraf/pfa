package ma.ensa.pfaproject.repositories;

import ma.ensa.pfaproject.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Produit,Long> {

}
