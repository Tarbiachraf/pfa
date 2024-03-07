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
        List<LigneCommande> ligneCommandes = newCommandeDto.getLigneCommande();
        Commande commande = commandMapper.toCommande(newCommandeDto);
        Commande savedCommande = commandeRepository.save(commande);
        for(LigneCommande l:ligneCommandes){
            l.setCommande(savedCommande);
            ligneCommandeRepository.save(l);
        }
        return savedCommande;
    }

    @Override
    public Commande updateCommande(CommandeDTO updatedCommandeDto) {
        Commande Updatedcommande = commandMapper.toCommande(updatedCommandeDto);
        Commande commande = commandeRepository.findById(Updatedcommande.getIdCommande()).orElseThrow(()-> new RessourceNotFoundException(ResourceTypeConstant.COMMANDE,Updatedcommande.getIdCommande(), ErrorMessages.CommandeNotFoundMessage));
        commande.setDateCommande(Updatedcommande.getDateCommande());
        commande.setMontantTotal(Updatedcommande.getMontantTotal());
        commande.setStatusCde(Updatedcommande.getStatusCde());

        commande.getLigneCommandes().clear();
        commande.setLigneCommandes(Updatedcommande.getLigneCommandes());
        return commandeRepository.save(commande);
    }

    @Override
    public void deleteCommande(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException(ResourceTypeConstant.COMMANDE, id, ErrorMessages.CommandeNotFoundMessage));

        ligneCommandeRepository.deleteById(commande.getIdCommande());

        commandeRepository.delete(commande);
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
