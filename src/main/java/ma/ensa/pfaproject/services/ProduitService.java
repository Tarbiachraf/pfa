package ma.ensa.pfaproject.services;

import ma.ensa.pfaproject.entities.Produit;

import java.util.List;
import java.util.Optional;

public interface ProduitService {
    public Produit createProduit(Produit newproduit);
    public Produit updateProduit(Produit updatedProduit);

    public void deleteProduit(Long id);
    public Produit getProduitById(Long id);
    public List<Produit> getAllProduit();

}
