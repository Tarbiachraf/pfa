package ma.ensa.pfaproject.repositories;

import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT p from Produit p where p.nomProd LIKE %:key%")
    List<Produit> getAllProduitsByNomContainingKey(String key);

    //rechercher par key et nom du catégorie
    @Query("SELECT p FROM Produit p JOIN p.categorieProduit c WHERE p.nomProd LIKE %:key% AND c.nomCategorie = :nomCategorie")
    List<Produit> getAllProduitsByNomContainingKeyAndCategorie(@Param("key") String key, @Param("nomCategorie") String nomCategorie);

    //rechercher par key et nom du catégorie
    @Query("SELECT p FROM Produit p JOIN p.categorieProduit c WHERE p.nomProd LIKE %:key% AND c.idCategorie = :idCategorie")
    List<Produit> getAllProduitsByNomContainingKeyAndCategorie(@Param("key") String key, @Param("idCategorie") Long idCategorie);

    @Query("SELECT p FROM Produit p JOIN p.categorieProduit c WHERE p.nomProd LIKE %:key% OR c.nomCategorie = :nomCategorie")
    List<Produit> getAllProduitsByNomContainingKeyOrCategorie(@Param("key") String key, @Param("nomCategorie") String nomCategorie);

    //rechercher par key ou nom du catégorie
    @Query("SELECT p FROM Produit p JOIN p.categorieProduit c WHERE p.nomProd LIKE %:key% OR c.idCategorie = :idCategorie")
    List<Produit> getAllProduitsByNomContainingKeyOrCategorie(@Param("key") String key, @Param("idCategorie") Long idCategorie);

    boolean existsByRefProd(String ref);
}
