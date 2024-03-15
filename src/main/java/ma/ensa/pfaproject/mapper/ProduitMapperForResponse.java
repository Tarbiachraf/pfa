package ma.ensa.pfaproject.mapper;

import ma.ensa.pfaproject.dtos.ProduitDTO;
import ma.ensa.pfaproject.dtos.ProduitResponse;
import ma.ensa.pfaproject.entities.CategorieProduit;
import ma.ensa.pfaproject.entities.Produit;
import ma.ensa.pfaproject.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProduitMapperForResponse {
    private final CategoryRepository categoryRepository;


    @Autowired
    public ProduitMapperForResponse(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ProduitResponse toProduitResponse(Produit produit){
        if(produit==null){
            return null;
        }
        return ProduitResponse.builder()
                .idProduit(produit.getIdProduit())
                .categoryName(produit.getCategorieProduit().getNomCategorie())
                .nomProd(produit.getNomProd())
                .details(produit.getDetails())
                .prixUnitaireHT(produit.getPrixUnitaireHT())
                .refProd(produit.getRefProd())
                .build();
    }
    public Produit toProduit(ProduitResponse produitResponse){

        CategorieProduit categorieProduit = categoryRepository.findById(produitResponse.getIdProduit()).get();


        if(produitResponse==null){
            return null;
        }


        return Produit.builder()
                .categorieProduit(categorieProduit)
                .nomProd(produitResponse.getNomProd())
                .details(produitResponse.getDetails())
                .prixUnitaireHT(produitResponse.getPrixUnitaireHT())
                .refProd(produitResponse.getRefProd())
                .build();
    }
}
