package ma.ensa.pfaproject.mapper;

import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.dtos.CommandeDTO;
import ma.ensa.pfaproject.dtos.LigneCommandeDto;
import ma.ensa.pfaproject.entities.Client;
import ma.ensa.pfaproject.entities.Commande;
import ma.ensa.pfaproject.entities.LigneCommande;
import ma.ensa.pfaproject.entities.Produit;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.repositories.ClientRepository;
import ma.ensa.pfaproject.repositories.CommandeRepository;
import ma.ensa.pfaproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
public class LigneCommandMapper {

    private final ProductRepository productRepository;

    @Autowired
    public LigneCommandMapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public LigneCommandeDto toLigneCommandeDTO(LigneCommande ligneCommande) {
        return LigneCommandeDto.builder()
                .idProduit(ligneCommande.getProduit().getIdProduit())
                .quantite(ligneCommande.getQuantite())
                .build();
    }

    public LigneCommande toLigneCommande(LigneCommandeDto ligneCommandeDto) {
        Produit produit = productRepository.findById(ligneCommandeDto.getIdProduit())
                .orElseThrow(() -> new RessourceNotFoundException(ResourceTypeConstant.LIGNECOMMAND,ligneCommandeDto.getIdProduit(),ErrorMessages.LigneCommandNotFoundMessage));
        return LigneCommande.builder()
                .produit(produit)
                .quantite(ligneCommandeDto.getQuantite())
                .build();
    }
}
