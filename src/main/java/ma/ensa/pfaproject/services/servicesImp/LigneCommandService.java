package ma.ensa.pfaproject.services.servicesImp;

import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.entities.Commande;
import ma.ensa.pfaproject.entities.LigneCommande;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.repositories.CommandeRepository;
import ma.ensa.pfaproject.repositories.LigneCommandeRepository;
import ma.ensa.pfaproject.services.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigneCommandService implements LigneCommandeService {

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Override
    public LigneCommande createLigneCommande(LigneCommande ligneCommande) {
        return ligneCommandeRepository.save(ligneCommande);
    }

    @Override
    public LigneCommande updateLigneCommande(LigneCommande updatedLigneCommande) {
        LigneCommande ligneCommande = ligneCommandeRepository.findById(updatedLigneCommande.getId()).orElseThrow(()->{
            return new RessourceNotFoundException(ResourceTypeConstant.LIGNECOMMAND,updatedLigneCommande.getId(), ErrorMessages.LigneCommandNotFoundMessage);
        });
        ligneCommande.setQuantite(updatedLigneCommande.getQuantite());
        ligneCommande.setProduit(updatedLigneCommande.getProduit());
        return ligneCommande;
    }

    @Override
    public void deleteLigneCommande(Long id) {
        if(ligneCommandeRepository.existsById(id)){
            ligneCommandeRepository.deleteById(id);
        }
        else{
            throw new RessourceNotFoundException(ResourceTypeConstant.LIGNECOMMAND,id,ErrorMessages.LigneCommandNotFoundMessage);
        }
    }

    @Override
    public LigneCommande getLigneCommandeById(Long id) {
        return ligneCommandeRepository.findById(id).orElseThrow(()->new RessourceNotFoundException(ResourceTypeConstant.LIGNECOMMAND,id,ErrorMessages.LigneCommandNotFoundMessage));
    }
    @Override
    public List<LigneCommande> getAllLigneCommandeById(Long id) {
        Commande commande = commandeRepository.findById(id).orElseThrow(()->new RessourceNotFoundException(ResourceTypeConstant.COMMANDE,id,ErrorMessages.CommandeNotFoundMessage));
        List<LigneCommande> ligneCommandeList = ligneCommandeRepository.findByCommande(commande);
        return ligneCommandeList;
    }

    @Override
    public List<LigneCommande> getAllCommandes() {
        return ligneCommandeRepository.findAll();
    }
}