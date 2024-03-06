package ma.ensa.pfaproject.services;

import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.entities.Commande;
import ma.ensa.pfaproject.entities.LigneCommande;

import java.util.List;

public interface LigneCommandeService {
    public LigneCommande createLigneCommande(LigneCommande ligneCommande);
    public LigneCommande updateLigneCommande(LigneCommande UpdatedLigneCommande);

    public void deleteLigneCommande(Long id);
    public LigneCommande getLigneCommandeById(Long id);
    public List<LigneCommande> getAllCommandes();
}
