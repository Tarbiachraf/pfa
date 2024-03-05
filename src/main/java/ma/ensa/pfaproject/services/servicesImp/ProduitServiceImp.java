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
        throw new RessourceNotFoundException(ResourceTypeConstant.PRODUCT,id, ErrorMessages.ProductNotFoundMessage);

    }

    @Override
    public Produit getProduitById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new RessourceNotFoundException(ResourceTypeConstant.PRODUCT,id, ErrorMessages.ProductNotFoundMessage));
    }

    @Override
    public List<Produit> getAllProduit() {
        return productRepository.findAll();
    }
}
