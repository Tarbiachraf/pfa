package ma.ensa.pfaproject.services.servicesImp;

import ma.ensa.pfaproject.entities.Produit;
import ma.ensa.pfaproject.repositories.ProductRepository;
import ma.ensa.pfaproject.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImp implements ProduitService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Produit createProduit(Produit newproduit) {
        return productRepository.save(newproduit);
    }

    @Override
    public Produit updateProduit(Produit updatedProduit) {
        Produit produit = productRepository.findById(updatedProduit.getIdProduit()).get();
        if(produit!=null){
            produit.setRefProd(updatedProduit.getRefProd());
            produit.setDetails(updatedProduit.getDetails());
            produit.setNomProd(updatedProduit.getNomProd());
            produit.setPrixUnitaireHT(updatedProduit.getPrixUnitaireHT());
            return productRepository.save(produit);
        }
        return produit;
    }


    @Override
    public boolean deleteProduit(Long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Produit> getProduitById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Produit> getAllProduit() {
        return productRepository.findAll();
    }
}
