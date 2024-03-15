package ma.ensa.pfaproject.services;

import ma.ensa.pfaproject.dtos.ProduitDTO;
import ma.ensa.pfaproject.dtos.ProduitResponse;
import ma.ensa.pfaproject.entities.Produit;

import java.util.List;

public interface ProduitService {
    public Produit createProduit(ProduitDTO newproduit);
    public Produit updateProduit(Long id, ProduitDTO updatedProduit);

    public void deleteProduit(Long id);
    public Produit getProduitById(Long id);
    public List<ProduitResponse> getAllProduit();

    public List<ProduitResponse> getAllProduitByNomContainingKey(String key);
    public List<Produit> getAllProduitByCategorie(Long categorieId);
    public List<ProduitResponse> getAllProduitByCategorie(String categorieNom);

    public List<ProduitResponse> getAllProduitByNomContainingKeyAndCategorieId(String key, Long id);



}
