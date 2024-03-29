package ma.ensa.pfaproject.services.servicesImp;

import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.dtos.CommandeDTO;
import ma.ensa.pfaproject.entities.Commande;
import ma.ensa.pfaproject.entities.LigneCommande;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.mapper.CommandMapper;
import ma.ensa.pfaproject.repositories.CommandeRepository;
import ma.ensa.pfaproject.repositories.LigneCommandeRepository;
import ma.ensa.pfaproject.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CommandeServiceImp implements CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    private LigneCommandService ligneCommandService;

    @Autowired
    private CommandMapper commandMapper;
    @Override
    public Commande createCommande(CommandeDTO newCommandeDto) {
        Commande commande = commandMapper.toCommande(newCommandeDto);
        Commande savedCommande = commandeRepository.save(commande);
        List<LigneCommande> ligneCommandes = commande.getLigneCommandes();
        for(LigneCommande l:ligneCommandes){
            l.setCommande(savedCommande);
            ligneCommandeRepository.save(l);
        }
        return savedCommande;
    }

    @Override
    public Commande updateCommande(Long commandeId,CommandeDTO updatedCommandeDto) {
        Commande updatedcommande = commandMapper.toCommande(updatedCommandeDto);
        Commande commande = commandeRepository.findById(commandeId).orElseThrow(()-> new RessourceNotFoundException(ResourceTypeConstant.COMMANDE,commandeId, ErrorMessages.CommandeNotFoundMessage));

//        commande.setMontantTotal(updatedcommande.getMontantTotal());
        commande.setStatusCde(updatedcommande.getStatusCde());

        commande.setDateReglement(updatedcommande.getDateReglement());

        commande.getLigneCommandes().clear();
        commande.setLigneCommandes(updatedcommande.getLigneCommandes());
        return commandeRepository.save(commande);
    }

    @Override
    public void deleteCommande(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException(ResourceTypeConstant.COMMANDE, id, ErrorMessages.CommandeNotFoundMessage));

        ligneCommandeRepository.deleteAllByCommande(commande);

        commandeRepository.delete(commande);
    }

    @Override
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElseThrow(()->new RessourceNotFoundException(ResourceTypeConstant.COMMANDE,id, ErrorMessages.CommandeNotFoundMessage));
    }

    @Override
    public List<Commande> getAllCommandes() {
        List<Commande> commandeList = commandeRepository.findAll();
        if(commandeList == null){
            return Collections.emptyList();
        }
        return commandeList;
    }

    @Override
    public List<Commande> getAllCommandesByClientNom(String clientNom) {
        List<Commande> commandeList = commandeRepository.getAllCommandesByClientContainingKey(clientNom);
        if(commandeList == null){
            return Collections.emptyList();
        }
        return commandeList;
    }

    @Override
    public List<Commande> getAllCommandesByClientId(Long clientId) {
        List<Commande> commandeList = commandeRepository.findAll();
        if(commandeList == null){
            return Collections.emptyList();
        }
        return commandeList;
    }
}
