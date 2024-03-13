package ma.ensa.pfaproject.mapper;

import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.dtos.ProduitDTO;
import ma.ensa.pfaproject.entities.CategorieProduit;
import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.entities.Produit;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.repositories.CategoryRepository;
import ma.ensa.pfaproject.repositories.ClientRepository;
import ma.ensa.pfaproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProduitMapper {
//    private final ClientRepository clientRepository;
//
//    @Autowired
//    public CommandMapper(ClientRepository clientRepository) {
//        this.clientRepository = clientRepository;
//    }

    private final CategoryRepository categoryRepository;


    @Autowired
    public ProduitMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ProduitDTO toProduitDTO(Produit produit){
        if(produit==null){
            return null;
        }
        return ProduitDTO.builder()
                .category(produit.getCategorieProduit().getIdCategorie())
                .nomProd(produit.getNomProd())
                .details(produit.getDetails())
                .prixUnitaireHT(produit.getPrixUnitaireHT())
                .refProd(produit.getRefProd())
                .build();
    }
    public Produit toProduit(ProduitDTO produitDTO){

        CategorieProduit categorieProduit = categoryRepository.findById(produitDTO.getCategory()).get();


        if(produitDTO==null){
            return null;
        }


        return Produit.builder()
                .categorieProduit(categorieProduit)
                .nomProd(produitDTO.getNomProd())
                .details(produitDTO.getDetails())
                .prixUnitaireHT(produitDTO.getPrixUnitaireHT())
                .refProd(produitDTO.getRefProd())
                .build();
    }
}
