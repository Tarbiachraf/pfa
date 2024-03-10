package ma.ensa.pfaproject.repositories;

import ma.ensa.pfaproject.entities.Commande;
import ma.ensa.pfaproject.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {
    List<Commande> findByClientCommandeNomClient(String nomCategorie);

    List<Commande> findByClientCommandeIdClient(Long idCategorie);


}
