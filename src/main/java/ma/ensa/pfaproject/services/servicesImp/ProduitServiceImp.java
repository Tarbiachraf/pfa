package ma.ensa.pfaproject.services.servicesImp;

import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.dtos.ProduitDTO;
import ma.ensa.pfaproject.dtos.ProduitResponse;
import ma.ensa.pfaproject.entities.Produit;
import ma.ensa.pfaproject.exceptions.ResourceAlreadyExistException;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.mapper.ProduitMapper;
import ma.ensa.pfaproject.mapper.ProduitMapperForResponse;
import ma.ensa.pfaproject.repositories.ProductRepository;
import ma.ensa.pfaproject.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImp implements ProduitService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProduitMapper produitMapper;

    @Autowired
    private ProduitMapperForResponse produitMapperForResponse;

    @Override
    public Produit createProduit(ProduitDTO newproduit) {
        if(productRepository.existsByRefProd(newproduit.getRefProd())){
            throw new ResourceAlreadyExistException(ResourceTypeConstant.PRODUCT,newproduit.getRefProd(),ErrorMessages.ProductAlreadyExistMessage);
        }
        Produit produit = produitMapper.toProduit(newproduit);
        return productRepository.save(produit);
    }

    @Override
    public Produit updateProduit(Long id, ProduitDTO updatedProduit) {
        Produit produit = productRepository.findById(id).get();
        Produit produit1 = produitMapper.toProduit(updatedProduit);
        if(produit!=null){
            produit.setRefProd(updatedProduit.getRefProd());
            produit.setDetails(updatedProduit.getDetails());
            produit.setNomProd(updatedProduit.getNomProd());
            produit.setPrixUnitaireHT(updatedProduit.getPrixUnitaireHT());
            produit.setCategorieProduit(produit1.getCategorieProduit());

            return productRepository.save(produit);
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
    public List<ProduitResponse> getAllProduit() {
        List<Produit> produitList = productRepository.findAll();
        if(produitList == null){
            return Collections.emptyList();
        }
        return produitList.stream().map(produit -> produitMapperForResponse.toProduitResponse(produit)).collect(Collectors.toList());
    }

    @Override
    public List<ProduitResponse> getAllProduitByNomContainingKey(String key) {
        List<Produit> produitList = productRepository.getAllProduitsByNomContainingKey(key);
        if(produitList == null){
            return Collections.emptyList();
        }
        return produitList.stream().map(produit -> produitMapperForResponse.toProduitResponse(produit)).collect(Collectors.toList());

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
    public List<ProduitResponse> getAllProduitByCategorie(String categorieNom) {
        List<Produit> produitList = productRepository.findByCategorieProduitNomCategorie(categorieNom);
        if(produitList == null){
            return Collections.emptyList();
        }
        return produitList.stream().map(produit -> produitMapperForResponse.toProduitResponse(produit)).collect(Collectors.toList());
    }

    @Override
    public List<ProduitResponse> getAllProduitByNomContainingKeyAndCategorieId(String key, Long id) {
        List<Produit> produitList = null;
        if (key != null && id != null && id != 0) {
            produitList = productRepository.getAllProduitsByNomContainingKeyAndCategorie(key, id);
        } else if (key != null && (id == null || id == 0)) {
            produitList = productRepository.getAllProduitsByNomContainingKey(key);
        } else if (key == null && id != null && id != 0) {
            produitList = productRepository.getAllProduitsByCategorie(id);
        } else {
            produitList = productRepository.findAll();
        }
        if (produitList == null) {
            return Collections.emptyList();
        }
        return produitList.stream().map(produit -> produitMapperForResponse.toProduitResponse(produit)).collect(Collectors.toList());
    }


}
