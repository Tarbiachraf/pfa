package ma.ensa.pfaproject.repositories;

import ma.ensa.pfaproject.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Produit,Long> {
    // Recherche des produits par nom de catégorie
    List<Produit> findByCategorieProduitNomCategorie(String nomCategorie);

    // Recherche des produits par ID de catégorie
    List<Produit> findByCategorieProduitIdCategorie(Long idCategorie);

    Optional<Produit> findByRefProd(String refProd);
}
