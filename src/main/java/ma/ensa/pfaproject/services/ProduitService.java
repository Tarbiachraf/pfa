package ma.ensa.pfaproject.services;

import ma.ensa.pfaproject.dtos.ProduitDTO;
import ma.ensa.pfaproject.entities.Produit;

import java.util.List;

public interface ProduitService {
    public Produit createProduit(ProduitDTO newproduit);
    public Produit updateProduit(Long id, ProduitDTO updatedProduit);

    public void deleteProduit(Long id);
    public Produit getProduitById(Long id);
    public List<Produit> getAllProduit();

    public List<Produit> getAllProduitByNomContainingKey(String key);
    public List<Produit> getAllProduitByCategorie(Long categorieId);
    public List<Produit> getAllProduitByCategorie(String categorieNom);

    public List<Produit> getAllProduitByNomContainingKeyAndCategorieId(String key, Long id);



}
