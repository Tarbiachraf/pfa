package ma.ensa.pfaproject.services.servicesImp;

import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.dtos.ProduitDTO;
import ma.ensa.pfaproject.entities.Produit;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.mapper.ProduitMapper;
import ma.ensa.pfaproject.repositories.ProductRepository;
import ma.ensa.pfaproject.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProduitServiceImp implements ProduitService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProduitMapper produitMapper;

    @Override
    public Produit createProduit(ProduitDTO newproduit) {
        Produit produit = produitMapper.toProduit(newproduit);
        return productRepository.save(produit);
    }

    @Override
    public Produit updateProduit(ProduitDTO updatedProduit) {
        Produit produit = productRepository.findByRefProd(updatedProduit.getRefProd()).get();
        if(produit!=null){
            Produit produit1 = produitMapper.toProduit(updatedProduit);
//            produit.setRefProd(updatedProduit.getRefProd());
//            produit.setDetails(updatedProduit.getDetails());
//            produit.setNomProd(updatedProduit.getNomProd());
//            produit.setPrixUnitaireHT(updatedProduit.getPrixUnitaireHT());
//            produit.setCategorieProduit(updatedProduit.getCategory());

            return productRepository.save(produit1);
        }
        throw new RessourceNotFoundException(ResourceTypeConstant.PRODUCT,updatedProduit.getRefProd(), ErrorMessages.ProductNotFoundMessage);

        //return produit;
    }


    @Override
    public void deleteProduit(Long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        }
        else {
            throw new RessourceNotFoundException(ResourceTypeConstant.PRODUCT, id, ErrorMessages.ProductNotFoundMessage);
        }
    }

    @Override
    public Produit getProduitById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new RessourceNotFoundException(ResourceTypeConstant.PRODUCT,id, ErrorMessages.ProductNotFoundMessage));
    }

    @Override
    public List<Produit> getAllProduit() {
        List<Produit> produitList = productRepository.findAll();
        if(produitList == null){
            return Collections.emptyList();
        }
        return produitList;
    }

    @Override
    public List<Produit> getAllProduitByNomContainingKey(String key) {
        List<Produit> produitList = productRepository.getAllProduitsByNomContainingKey(key);
        if(produitList == null){
            return Collections.emptyList();
        }
        return produitList;

    }

    @Override
    public List<Produit> getAllProduitByCategorie(Long categorieId) {
        List<Produit> produitList = productRepository.findByCategorieProduitIdCategorie(categorieId);
        if(produitList == null){
            return Collections.emptyList();
        }
        return produitList;
    }

    @Override
    public List<Produit> getAllProduitByCategorie(String categorieNom) {
        List<Produit> produitList = productRepository.findByCategorieProduitNomCategorie(categorieNom);
        if(produitList == null){
            return Collections.emptyList();
        }
        return produitList;
    }

    @Override
    public List<Produit> getAllProduitByNomContainingKeyAndCategorieId(String key, Long id) {
        List<Produit> produitList = productRepository.getAllProduitsByNomContainingKeyAndCategorie(key,id);
        if(produitList == null){
            return Collections.emptyList();
        }
        return produitList;
    }


}
