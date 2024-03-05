package ma.ensa.pfaproject.services;

import ma.ensa.pfaproject.dtos.ProduitDTO;
import ma.ensa.pfaproject.entities.Produit;

import java.util.List;

public interface ProduitService {
    public Produit createProduit(ProduitDTO newproduit);
    public Produit updateProduit(ProduitDTO updatedProduit);

    public void deleteProduit(Long id);
    public Produit getProduitById(Long id);
    public List<Produit> getAllProduit();

}
