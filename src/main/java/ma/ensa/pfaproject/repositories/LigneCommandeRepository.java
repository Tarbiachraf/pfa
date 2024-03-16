package ma.ensa.pfaproject.repositories;

import ma.ensa.pfaproject.entities.Commande;
import ma.ensa.pfaproject.entities.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande,Long> {
    void deleteAllByCommande(Commande commande);

    List<LigneCommande> findByCommande(Commande commande);
}
