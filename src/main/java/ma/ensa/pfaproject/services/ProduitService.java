package ma.ensa.pfaproject.services;

import ma.ensa.pfaproject.entities.Produit;

import java.util.List;
import java.util.Optional;

public interface ProduitService {
    public Produit createProduit(Produit newproduit);
    public Produit updateProduit(Produit updatedProduit);

    public boolean deleteProduit(Long id);
    public Optional<Produit> getProduitById(Long id);
    public List<Produit> getAllProduit();

}
