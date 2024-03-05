package ma.ensa.pfaproject.services.servicesImp;

import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.dtos.CommandeDTO;
import ma.ensa.pfaproject.entities.Commande;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.mapper.CommandMapper;
import ma.ensa.pfaproject.repositories.CommandeRepository;
import ma.ensa.pfaproject.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeServiceImp implements CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private CommandMapper commandMapper;
    @Override
    public Commande createCommande(CommandeDTO newCommandeDto) {
        Commande commande = commandMapper.toCommande(newCommandeDto);
        return commandeRepository.save(commande);
    }

    @Override
    public Commande updateCommande(CommandeDTO updatedCommandeDto) {
        Commande Updatedcommande = commandMapper.toCommande(updatedCommandeDto);
        Commande commande = commandeRepository.findById(Updatedcommande.getIdCommande()).orElseThrow(()-> new RessourceNotFoundException(ResourceTypeConstant.COMMANDE,Updatedcommande.getIdCommande(), ErrorMessages.CommandeNotFoundMessage));
        commande.setDateCommande(Updatedcommande.getDateCommande());
        commande.setMontantTotal(Updatedcommande.getMontantTotal());
        commande.setStatusCde(Updatedcommande.getStatusCde());
        commande.setLigneCommandes(Updatedcommande.getLigneCommandes());
        return commande;
    }

    @Override
    public void deleteCommande(Long id) {
        if(commandeRepository.existsById(id)){
            commandeRepository.deleteById(id);
        }
        else{
            throw new RessourceNotFoundException(ResourceTypeConstant.COMMANDE,id, ErrorMessages.CommandeNotFoundMessage);
        }
    }

    @Override
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElseThrow(()->new RessourceNotFoundException(ResourceTypeConstant.COMMANDE,id, ErrorMessages.CommandeNotFoundMessage));
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }
}
