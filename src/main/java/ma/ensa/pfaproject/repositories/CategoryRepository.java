package ma.ensa.pfaproject.repositories;

import ma.ensa.pfaproject.entities.CategorieProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;

@Repository
public interface CategoryRepository extends JpaRepository<CategorieProduit,Long> {
    public CategorieProduit findByNomCategorie(String nomCategorie);
}
